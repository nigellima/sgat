package sessions;


import entities.Atividade;
import entities.Funcionario;
import entities.Palestras;
import entities.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PalestrasFacade extends AbstractFacade<Palestras> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PalestrasFacade(){
        super(Palestras.class);
    }  
    
    public List<Palestras> findByAtividade(Atividade atv)
    {
        TypedQuery query = em.createNamedQuery("Palestras.findByAtividade", Palestras.class);
        query.setParameter("atividade", atv);
        List<Palestras> palestras = query.getResultList();
        if(palestras != null)
            return palestras;
        else
            return null;
    }
    
    public List<Palestras> findByPessoa (Pessoa pessoa)
    {
        TypedQuery query = em.createNamedQuery("Palestras.findByPessoa", Palestras.class);
        query.setParameter("pessoa", pessoa);
        List<Palestras> palestras = query.getResultList();
        if(palestras != null)
            return palestras;
        else
            return null;
    }
}
