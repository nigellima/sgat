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
import moodle.entities.MdlUserEnrolments;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlUserEnrolmentsFacade extends AbstractFacade<MdlUserEnrolments> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlUserEnrolmentsFacade() {
        super(MdlUserEnrolments.class);
    }
    
    public List<MdlUserEnrolments> findByEnrol(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlUserEnrolments.findByEnrolid", MdlUserEnrolments.class);
        query.setParameter("enrolid", id);
        return query.getResultList();
    }
}
