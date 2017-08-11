package sessions;

import entities.Atividade;
import entities.AtividadeTbr;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AtividadeTbrFacade extends AbstractFacade<AtividadeTbr> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtividadeTbrFacade() {
        super(AtividadeTbr.class);
    }
    
    public AtividadeTbr findByAtividade(Atividade atv)
    {
        TypedQuery query = em.createNamedQuery("AtividadeTbr.findByAtividade", AtividadeTbr.class);
        query.setParameter("atividade", atv);
        return returnSingleValue(query);
    }
    
    public AtividadeTbr findByFinalidade(String finalidade)
    {
        TypedQuery query = em.createNamedQuery("AtividadeTbr.findByFinalidade", AtividadeTbr.class);
        query.setParameter("finalidade", finalidade);
        return returnSingleValue(query);
    }
}
