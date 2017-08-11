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
import moodle.entities.MdlGradeGrades;

/**
 *
 * @author nigel
 */
@Stateless
public class MdlGradeGradesFacade extends AbstractFacade<MdlGradeGrades> {

    @PersistenceContext(unitName = "MOODLE_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MdlGradeGradesFacade() {
        super(MdlGradeGrades.class);
    }
    
    
            
    public List<MdlGradeGrades> findByUser(long id)
    {
        TypedQuery query = em.createNamedQuery("MdlGradeGrades.findByUserid", MdlGradeGrades.class);
        query.setParameter("userid", id);
        return query.getResultList();
    }
    
}
