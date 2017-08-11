/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pessoa;
import entities.ProfSaude;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class ProfSaudeFacade extends AbstractFacade<ProfSaude> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public ProfSaudeFacade() {
        super(ProfSaude.class);
    }
    
    public ProfSaude getByCNS(String cns){
        cns = cns.trim();
        TypedQuery<ProfSaude> query = em.createNamedQuery("ProfSaude.findByCns", ProfSaude.class);
        query.setParameter("cns", cns);
        return (ProfSaude) returnSingleValue(query);
    } 
    
    public ProfSaude getByPessoa(Pessoa pessoa){
        TypedQuery<ProfSaude> query = em.createNamedQuery("ProfSaude.findByPessoa", ProfSaude.class);
        query.setParameter("pessoa", pessoa);
        return (ProfSaude) returnSingleValue(query);
    } 
        
    public List<ProfSaude> findRangePagination(int[] range) {
        Query q = em.createQuery("SELECT p FROM ProfSaude p");
        q.setFirstResult(range[0]);
        q.setMaxResults(range[1]);
        return q.getResultList();
    }
    
    private Predicate getFilterCondition(CriteriaBuilder cb, Root<ProfSaude> myObj, Map<String, Object> filters) {
        Predicate filterCondition = cb.conjunction();
        
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
           
            String value = String.valueOf(filter.getValue());
            if (!filter.getValue().equals("")) {
                javax.persistence.criteria.Path<String> path = myObj.get(filter.getKey());                 
                 value = value.trim();
                filterCondition = cb.and(filterCondition, cb.like(path, "%"+value+"%"));
            }
        }
        return filterCondition;
    }
    
    public List<ProfSaude> getAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ProfSaude> cq = cb.createQuery(ProfSaude.class);
        Root<ProfSaude> myObj = cq.from(ProfSaude.class);
        cq.where(getFilterCondition(cb, myObj, filters)); 
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(myObj.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(myObj.get(sortField)));
            }
        }
        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
        
    }
       
    public int countTotal()
    {
        Query query = em.createQuery("select COUNT(p) from ProfSaude p");
        
        Number result =(Number) query.getSingleResult();
        return result.intValue();
    }  
}
