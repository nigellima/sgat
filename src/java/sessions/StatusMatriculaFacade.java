/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.StatusMatricula;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nigel
 */
@Stateless
public class StatusMatriculaFacade extends AbstractFacade<StatusMatricula> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusMatriculaFacade() {
        super(StatusMatricula.class);
    }
    
    public StatusMatricula findById(Integer id)
    {
        TypedQuery query = em.createNamedQuery("StatusMatricula.findById", StatusMatricula.class);
        query.setParameter("id", id);
        return returnSingleValue(query);
    }
}
