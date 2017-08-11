/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.sessions;

import dashboard.entities.Processos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class ProcessosFacade extends AbstractFacade<Processos> {

    @PersistenceContext(unitName = "DASHBOARD_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessosFacade() {
        super(Processos.class);
    }
    
    public List<Processos> findByMonth(String monthYear)
    {
       String[] fields = monthYear.split("-");
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Processos> cq = cb.createQuery(Processos.class);
       Root<Processos> myObj = cq.from(Processos.class);
       Predicate predicate = cb.and(cb.like(myObj.<String>get("soldtenvresp"), "%"+fields[1]+"/"+fields[0]+"%"),
                             cb.notLike(myObj.<String>get("soldtenvio"), "")/*, 
                             cb.like(myObj.<String>get("soldtenvresp"), "%"+fields[1]+"/"+fields[0]+"%")*/);
       //Predicate predicate2 = cb.isNotNull(myObj.<String>get("soldtenvresp"));
       cq.where(predicate);
       cq.select(myObj);
       TypedQuery query = em.createQuery(cq);
       
       return query.getResultList();
    }
}
