/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;


import entities.Atividade;
import entities.EdicaoVideo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class EdicaoVideoFacade extends AbstractFacade<EdicaoVideo> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    public EdicaoVideoFacade() {
         super(entities.EdicaoVideo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EdicaoVideo findById(int id){
        TypedQuery query = em.createNamedQuery("EdicaoVideo.findById", EdicaoVideo.class);
        query.setParameter("id", id);
        return (EdicaoVideo) query.getSingleResult();
    }
    
    public EdicaoVideo findByAtividade(Atividade atv){
        TypedQuery query = em.createNamedQuery("EdicaoVideo.findByAtividade", EdicaoVideo.class);
        query.setParameter("atividade", atv);
        return returnSingleValue(query);
    }
}
