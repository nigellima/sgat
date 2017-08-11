
package sessions;

import entities.Status;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class StatusFacade extends AbstractFacade<Status> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusFacade() {
        super(Status.class);
    }
    
    public Status findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Status.findByDescricao", Status.class);
        query.setParameter("descricao", value);
        return (Status) query.getSingleResult();
    }
    
}
