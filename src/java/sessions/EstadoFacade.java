/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Estado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }
    
    public List<Estado> findByDescription(String desc){
        Query query = em.createQuery("SELECT e FROM Estado e WHERE e.descricao LIKE :desc");
        query.setParameter("desc", "%"+desc+"%");
        System.out.println("Trying to fetch... %"+desc+"%");
        return query.getResultList();
    }
    
}
