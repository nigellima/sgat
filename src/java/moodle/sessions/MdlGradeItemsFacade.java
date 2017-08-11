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
import moodle.entities.MdlGradeItems;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlGradeItemsFacade extends AbstractFacade<MdlGradeItems> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlGradeItemsFacade() {
        super(MdlGradeItems.class);
    }
    
    public List<MdlGradeItems> findByCourse(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlGradeItems.findByCourseid", MdlGradeItems.class);
        query.setParameter("courseid", id);
        return query.getResultList();
    }
}
