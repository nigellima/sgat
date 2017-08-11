package sessions;

import entities.Instituicao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class InstituicaoFacade extends AbstractFacade<Instituicao> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public InstituicaoFacade(){
        super(Instituicao.class);
    }
    
    public Instituicao findById(int value){
        TypedQuery query = em.createNamedQuery("Instituicao.findById", Instituicao.class);
        query.setParameter("id", value);
        return (Instituicao) query.getSingleResult();
    }
    
    public List<Instituicao> findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Instituicao.findByDescricao", Instituicao.class);
        query.setParameter("descricao", value);
        return (List<Instituicao>) query.getResultList();
    }
    
}
