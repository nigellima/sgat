package sessions;

import entities.AtaDigitalizada;
import entities.AtaNts;
import entities.Atividade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class AtaDigitalizadaFacade extends AbstractFacade<AtaDigitalizada> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AtaDigitalizadaFacade(){
        super(AtaDigitalizada.class);
    }
    
    public AtaDigitalizada findById(int id){
        TypedQuery query = em.createNamedQuery("AtaDigitalizada.findById", AtaDigitalizada.class);
        query.setParameter("id", id);
        return (AtaDigitalizada) query.getSingleResult();
    }
    
    public AtaDigitalizada findByAta(AtaNts ata)
    {
        TypedQuery query = em.createNamedQuery("AtaDigitalizada.findByAta", AtaDigitalizada.class);
        query.setParameter("ata", ata);
        return returnSingleValue(query);
    }
}
