/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import moodle.entities.MdlUser;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlUserFacade extends AbstractFacade<MdlUser> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlUserFacade() {
        super(MdlUser.class);
    }
    
    public MdlUser findById(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlUser.findById", MdlUser.class);
        query.setParameter("id", id);
        return returnSingleValue(query);
    }
    
}
