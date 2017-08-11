/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import moodle.entities.MdlCourseCompletionCriteria;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlCourseCompletionCriteriaFacade extends AbstractFacade<MdlCourseCompletionCriteria> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlCourseCompletionCriteriaFacade() {
        super(MdlCourseCompletionCriteria.class);
    }
    
    public MdlCourseCompletionCriteria findByCourse(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlCourseCompletionCriteria.findByCourse", MdlCourseCompletionCriteria.class);
        query.setParameter("course", id);
        List<MdlCourseCompletionCriteria> results = null;//new ArrayList();
        results = query.getResultList();
        System.out.println(id);
        if(!results.isEmpty())
            return results.get(0);
        return null;
    }
    
}
