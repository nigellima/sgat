package sessions;

import entities.AtaNts;
import entities.Atividade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class AtaNtsFacade extends AbstractFacade<AtaNts> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AtaNtsFacade(){
        super(AtaNts.class);
    }
    
    public AtaNts findById(int id){
        TypedQuery query = em.createNamedQuery("AtaNts.findById", AtaNts.class);
        query.setParameter("id", id);
        return (AtaNts) query.getSingleResult();
    }
    
    public AtaNts findByAtividade(Atividade atv)
    {
        TypedQuery query = em.createNamedQuery("AtaNts.findByAtividade", AtaNts.class);
        query.setParameter("atividade", atv);
        return returnSingleValue(query);
    }
}
