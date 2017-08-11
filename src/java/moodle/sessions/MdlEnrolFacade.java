/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import moodle.entities.MdlEnrol;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlEnrolFacade extends AbstractFacade<MdlEnrol> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    public MdlEnrolFacade() {
        super(MdlEnrol.class);
    }
    
    public List<MdlEnrol> findByCourse(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlEnrol.findByCourseid", MdlEnrol.class);
        query.setParameter("courseid", id);
        return query.getResultList();
    }
}
