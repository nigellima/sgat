/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.sessions;

import entities.Atividade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import moodle.entities.MdlCourse;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlCourseFacade extends AbstractFacade<MdlCourse> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlCourseFacade() {
        super(MdlCourse.class);
    }
    
    
    public List<MdlCourse> findAll()
    {
        TypedQuery query = em.createNamedQuery("MdlCourse.findAll", MdlCourse.class);
        return query.getResultList();
    }
}
