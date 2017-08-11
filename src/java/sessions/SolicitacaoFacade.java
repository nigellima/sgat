/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Atividade;
import entities.Pessoa;
import entities.Solicitacao;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 *
 * @author HUUFMA
 */
@Stateless
public class SolicitacaoFacade extends AbstractFacade<Solicitacao> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitacaoFacade() {
        super(Solicitacao.class);
    }
    
    public Solicitacao findById(int id){
        TypedQuery query = em.createNamedQuery("Solicitacao.findById", Solicitacao.class);
        query.setParameter("id", id);
        
        return returnSingleValue(query);
    }
    
    public Solicitacao findByAtividade(Atividade atividade){
        TypedQuery query = em.createNamedQuery("Solicitacao.findByAtividade", Solicitacao.class);
        query.setParameter("atividade", atividade);
        return returnSingleValue(query);
    }
    
    
    
     public int countTotal()
    {
        Query query = em.createQuery("select COUNT(s) from Solicitacao s");
        
        Number result =(Number) query.getSingleResult();
        return result.intValue();
    }    
}
