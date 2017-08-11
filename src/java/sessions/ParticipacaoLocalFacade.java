/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ParticipacaoLocal;
import entities.ParticipacaoPessoa;
import entities.Ubs;
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
public class ParticipacaoLocalFacade extends AbstractFacade<ParticipacaoLocal> {
      
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ParticipacaoLocalFacade(){
        super(ParticipacaoLocal.class);
    }  
    
    
    public ParticipacaoLocal findByParticipacao(ParticipacaoPessoa part){
        TypedQuery query = em.createNamedQuery("ParticipacaoPessoa.findByParticipacao", ParticipacaoPessoa.class);
        query.setParameter("participacao", part);
        return (ParticipacaoLocal) returnSingleValue(query);
    }
    
    public List<ParticipacaoLocal> findByUbs(Ubs ubs){
        TypedQuery query = em.createNamedQuery("ParticipacaoLocal.findByUbs", ParticipacaoLocal.class);
        query.setParameter("ubs", ubs);
        return query.getResultList();
    }
}
