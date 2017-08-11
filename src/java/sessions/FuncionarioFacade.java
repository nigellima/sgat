
package sessions;

import entities.Funcionario;
import entities.Instituicao;
import entities.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FuncionarioFacade extends AbstractFacade<Funcionario> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public FuncionarioFacade(){
        super(Funcionario.class);
    }
    
    public List<Funcionario> findByPessoa(Pessoa pessoa){
        TypedQuery query = em.createNamedQuery("Funcionario.findByPessoa", Funcionario.class);
        query.setParameter("pessoa", pessoa);
        return query.getResultList();
    }
    
    public List<Funcionario> findByInstituicao(Instituicao instituicao){
        TypedQuery query = em.createNamedQuery("Funcionario.findByInstituicao", Funcionario.class);
        query.setParameter("instituicao", instituicao);
        return query.getResultList();
    }
    
    public Funcionario findById(int id){
        TypedQuery query = em.createNamedQuery("Funcionario.findById", Funcionario.class);
        query.setParameter("id", id);
        return (Funcionario) query.getSingleResult();
    }
    
}
