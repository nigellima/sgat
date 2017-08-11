/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Atividade;
import entities.ParticipacaoPessoa;
import entities.Pessoa;
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
public class ParticipacaoPessoaFacade extends AbstractFacade<ParticipacaoPessoa> {
      
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ParticipacaoPessoaFacade(){
        super(ParticipacaoPessoa.class);
    }  
    
    public ParticipacaoPessoa findByComposedKey(Pessoa pessoa, Atividade atividade){
        TypedQuery query = em.createNamedQuery("ParticipacaoPessoa.findByComposedKey", ParticipacaoPessoa.class);
        query.setParameter("pessoa", pessoa);
        query.setParameter("atividade", atividade);
        return returnSingleValue(query);
    }
    
    public List<ParticipacaoPessoa> findByAtividade(Atividade atv){
        TypedQuery query = em.createNamedQuery("ParticipacaoPessoa.findByAtividade", ParticipacaoPessoa.class);
        query.setParameter("atividade", atv);
        return query.getResultList();
    }
    
    public List<ParticipacaoPessoa> findByPessoa(Pessoa pessoa){
        TypedQuery query = em.createNamedQuery("ParticipacaoPessoa.findByPessoa", ParticipacaoPessoa.class);
        query.setParameter("pessoa", pessoa);
        return query.getResultList();
    }
}
