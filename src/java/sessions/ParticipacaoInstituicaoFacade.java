package sessions;


import entities.Atividade;
import entities.Funcionario;
import entities.Palestras;
import entities.ParticipacaoInstituicao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ParticipacaoInstituicaoFacade extends AbstractFacade<ParticipacaoInstituicao> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ParticipacaoInstituicaoFacade(){
        super(ParticipacaoInstituicao.class);
    }  
    
    public List<ParticipacaoInstituicao> findByAtividade(Atividade atv)
    {
        TypedQuery query = em.createNamedQuery("ParticipacaoInstituicao.findByAtividade", ParticipacaoInstituicao.class);
        query.setParameter("atividade", atv);
        return query.getResultList();
    }
}
