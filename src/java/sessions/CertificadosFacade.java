
package sessions;

import entities.Atividade;
import entities.Certificados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */

@Stateless
public class CertificadosFacade extends AbstractFacade<Certificados> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CertificadosFacade(){
        super(Certificados.class);
    }
    
    public Certificados findById(int id){
        TypedQuery query = em.createNamedQuery("Certificados.findById", Certificados.class);
        query.setParameter("id", id);
        return (Certificados) query.getSingleResult();
    }
    
    public Certificados findByAtividade(Atividade atv){
        TypedQuery query = em.createNamedQuery("Certificados.findByAtividade", Certificados.class);
        query.setParameter("atividade", atv);
        return (Certificados) returnSingleValue(query);
    }
    
}
