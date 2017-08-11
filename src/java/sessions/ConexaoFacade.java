package sessions;

import entities.Atividade;
import entities.Conexao;
import entities.Funcionario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ConexaoFacade extends AbstractFacade<Conexao> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ConexaoFacade(){
        super(Conexao.class);
    }
    
    public Conexao getLastDescription(Funcionario funcionario){
        TypedQuery query = em.createNamedQuery("Conexao.findByResponsavel", Conexao.class);
        query.setParameter("responsavel", funcionario);
        if(!query.getResultList().isEmpty()){
            Conexao conexao = new Conexao();
            for(Object con : query.getResultList()){
                conexao = (Conexao) con;
            }
            return conexao;
        }
        return null; 
    }
    
    public Conexao findByAtividade(Atividade atividade){
        TypedQuery query = em.createNamedQuery("Conexao.findByAtividade", Conexao.class);
        query.setParameter("atividade", atividade);
        return returnSingleValue(query);
    }
    
    public List<Conexao> findListByAtividade(Atividade atv)
    {
        TypedQuery query = em.createNamedQuery("Conexao.findByAtividade", Conexao.class);
        query.setParameter("atividade", atv);
        List<Conexao> conexoes = query.getResultList();
        if(conexoes != null)
            return conexoes;
        else
            return null;
    }
}
