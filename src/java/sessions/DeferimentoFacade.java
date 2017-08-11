package sessions;

import entities.Deferimento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeferimentoFacade extends AbstractFacade<Deferimento> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeferimentoFacade() {
        super(Deferimento.class);
    }
}
