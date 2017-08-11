/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Atividade;
import entities.Municipio;
import entities.Pessoa;
import entities.Presenca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
@Stateless
public class PresencaFacade extends AbstractFacade<Presenca> {
      
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PresencaFacade(){
        super(entities.Presenca.class);
    }  
    
    public Presenca findByComposedKey(Pessoa pessoa, Atividade atividade){
        TypedQuery query = em.createNamedQuery("Presenca.findByComposedKey", entities.Presenca.class);
        query.setParameter("pessoa", pessoa);
        query.setParameter("atividade", atividade);
        return returnSingleValue(query);
    }
    
    public List<Presenca> findByAtividade(Atividade atv){
        TypedQuery query = em.createNamedQuery("Presenca.findByAtv", entities.Presenca.class);
        query.setParameter("atividade", atv);
        return query.getResultList();
    }
    
    public List<Presenca> findByPessoa(Pessoa pessoa){
        TypedQuery query = em.createNamedQuery("Presenca.findByPessoa", entities.Presenca.class);
        query.setParameter("pessoa", pessoa);
        return query.getResultList();
    }
    
    public List<Presenca> findByLocal(Municipio local){
        TypedQuery query = em.createNamedQuery("Presenca.findByLocal", entities.Presenca.class);
        query.setParameter("local", local);
        return query.getResultList();
    }
    
    public List<Presenca> findDistinctIps(Atividade atv){
        TypedQuery query = em.createNamedQuery("Presenca.findDistinctIps", entities.Presenca.class);
        query.setParameter("atividade", atv);        
        return query.getResultList();
    }
    
    public List<Municipio> findDistinctCities(Atividade atv){
        TypedQuery query = em.createNamedQuery("Presenca.findDistinctCities", entities.Presenca.class);
        query.setParameter("atividade", atv);        
        return query.getResultList();
    }
}
