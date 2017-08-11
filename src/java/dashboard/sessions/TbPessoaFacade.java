/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.sessions;

import dashboard.entities.TbPessoa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class TbPessoaFacade extends AbstractFacade<TbPessoa> {

    @PersistenceContext(unitName = "DASHBOARD_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TbPessoaFacade() {
        super(TbPessoa.class);
    }
    
    public TbPessoa findByCpf(String value){
        TypedQuery query = em.createNamedQuery("TbPessoa.findByCpf", TbPessoa.class);
        query.setParameter("cpf", value);
        return (TbPessoa)returnSingleValue(query);
    }
    
}
