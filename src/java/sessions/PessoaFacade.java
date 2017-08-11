/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pessoa;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class PessoaFacade extends AbstractFacade<Pessoa> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
    }
    
    public List<Pessoa> findRangePagination(int[] range) {
        Query q = em.createQuery("SELECT p FROM Pessoa p");
        q.setFirstResult(range[0]);
        q.setMaxResults(range[1]);
        return q.getResultList();
    }
    
    public List<Pessoa> findRangePagination(int first, int maxResult) {
        Query q = em.createQuery("SELECT p FROM Pessoa p");
        q.setFirstResult(first);
        q.setMaxResults(maxResult);
        return q.getResultList();
    }
    
    private Predicate getFilterCondition(CriteriaBuilder cb, Root<Pessoa> myObj, Map<String, Object> filters) {
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
    
    public Pessoa findPessoaById(Integer id)
    {
        TypedQuery query = em.createNamedQuery("Pessoa.findById", Pessoa.class);
        query.setParameter("id", id);
        System.out.println((Pessoa) query.getSingleResult());
        return (Pessoa) query.getSingleResult();
    }
    
    public List<Pessoa> getAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
        Root<Pessoa> myObj = cq.from(Pessoa.class);
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
        Query query = em.createQuery("select COUNT(p) from Pessoa p");
        
        Number result =(Number) query.getSingleResult();
        return result.intValue();
    }    
    
    public Pessoa findByCompleteName(String value){
        String nome = value.substring(0, value.indexOf(" ")).trim();
        String sobrenome = value.substring(value.indexOf(" ")).trim();
        
        System.out.println("------ TENTANTO ACHAR ---> |" + nome + "| " + " |" + sobrenome + "| <---");
        
        TypedQuery query = em.createNamedQuery("Pessoa.findByCompleteName", Pessoa.class);
        query.setParameter("nome", nome);
        query.setParameter("sobrenome", sobrenome);
        
        System.out.println("RESULTADOOOOO: -->" + ((Pessoa) query.getSingleResult()).toString());
        
        return (Pessoa) query.getSingleResult();
        
    }
  
    public Pessoa getByEmail(String email){
        try{
            email = email.trim();
            TypedQuery<Pessoa> query = em.createNamedQuery("Pessoa.findByEmail", Pessoa.class);
            query.setParameter("email", email);
            return (Pessoa) query.getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }
    }   
  
    public Pessoa getByCPF(String cpf){
        cpf = cpf.trim();
        TypedQuery<Pessoa> query = em.createNamedQuery("Pessoa.findByCpf", Pessoa.class);
        query.setParameter("cpf", cpf);
        return (Pessoa) returnSingleValue(query);
    }    
}
