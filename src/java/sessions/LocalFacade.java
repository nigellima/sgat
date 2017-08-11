
package sessions;

import entities.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LocalFacade extends AbstractFacade<Local> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public LocalFacade(){
        super(Local.class);
    }
    
    public Local findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Local.findByDescricao", Local.class);
        query.setParameter("descricao", value);
        return (Local) query.getSingleResult();
    }
        
}
