/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Atividade;
import entities.Modalidade;
import entities.Pessoa;
import entities.Status;
import entities.Tipo;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author HUUFMA
 */
@Stateless
public class AtividadeFacade extends AbstractFacade<entities.Atividade> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AtividadeFacade(){
        super(entities.Atividade.class);
    }
    
    public Atividade findById(int id){
        TypedQuery query = em.createNamedQuery("Atividade.findById", entities.Atividade.class);
        query.setParameter("id", id);
        return (Atividade) returnSingleValue(query);
    }
    
    public List<Atividade> findByStatus(Status status){
        TypedQuery query = em.createNamedQuery("Atividade.findByStatus", Atividade.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
    
    
    public List<Atividade> findByMonth(String monthYear)
    {
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Atividade> cq = cb.createQuery(Atividade.class);
       Root<Atividade> myObj = cq.from(Atividade.class);
       Predicate predicate = cb.like(myObj.<String>get("dt"), "%"+monthYear+"%");
       cq.where(predicate);
       cq.select(myObj);
       TypedQuery query = em.createQuery(cq);
       
       return query.getResultList();
    }
    
    public List<Atividade> findByModerador(Pessoa moderador){
        TypedQuery query = em.createNamedQuery("Atividade.findByModerador", Atividade.class);
        query.setParameter("moderador", moderador);
        return query.getResultList();
    }
    
    public Date firstDate()
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Atividade> cq = cb.createQuery(Atividade.class);
        Root<Atividade> myObj = cq.from(Atividade.class);
        cq.orderBy(cb.asc(myObj.get("dt")));        
        TypedQuery query =  em.createQuery(cq);
        List<Atividade> atvs = query.getResultList();
        System.out.print( "datas " + atvs.get(0).getDt());
        
        return null;
    }
    
    public Date lastDate()
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Atividade> cq = cb.createQuery(Atividade.class);
        Root<Atividade> myObj = cq.from(Atividade.class);
        cq.orderBy(cb.desc(myObj.get("dt")));        
        TypedQuery query =  em.createQuery(cq);
        List<Atividade> atvs = query.getResultList();
        System.out.print( "ultima data " + atvs.get(0).getDt());
        
        return null;
    }
    
    public List<Atividade> findByDay(String format)
    {
       Query query = em.createQuery("SELECT a FROM Atividade a JOIN Status s WHERE a.dt LIKE '%"+format+"%' AND s.id = 4");

       return query.getResultList();
    }
    
    
    public int countAtividadesrealizadasByTipo(Tipo tipo, Integer status)
    {
//       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//       CriteriaQuery<Atividade> cq = cb.createQuery(Atividade.class);
//       Root<Atividade> myObj = cq.from(Atividade.class);
//       
//       Predicate predicate = cb.and(cb.equal(myObj.<Integer>get("tipo"), tipoId), cb.equal(myObj.<Integer>get("status"), 4));
//       cq.where(predicate);
//       
//       cq.select(myObj);
//       TypedQuery query = em.createQuery(cq);
        Status s = new Status();
        s.setId(status);
        
        TypedQuery query = em.createNamedQuery("Atividade.countByTipo", Atividade.class);
        query.setParameter("tipo", tipo);
        query.setParameter("status", s);
        
//       Query query = em.createQuery("SELECT COUNT(a) \n" +
//                                    "FROM Atividade a JOIN\n" +
//                                    "Tipo t JOIN Status s\n" +
//                                    "WHERE s.id = 4 AND t.id =" + tipoId);

       return query.getResultList().size();
    }
    
    public List<Atividade> getFilteredAtividades(Date dt1, Date dt2, Integer status)
    {
        Status s = new Status();
        s.setId(status);
        
        TypedQuery query = em.createNamedQuery("Atividade.findBetweenDateAndStatus", Atividade.class);
        query.setParameter("status", s);
        query.setParameter("dt1", dt1);
        query.setParameter("dt2", dt2);
        
        List<Atividade> atvs = query.getResultList();
        return atvs;
    }
    
    public List<Atividade> getFilteredAtividades(Date dt1, Date dt2)
    {        
        TypedQuery query = em.createNamedQuery("Atividade.findBetweenDate", Atividade.class);
        query.setParameter("dt1", dt1);
        query.setParameter("dt2", dt2);
        
        List<Atividade> atvs = query.getResultList();
        return atvs;
    }
}
