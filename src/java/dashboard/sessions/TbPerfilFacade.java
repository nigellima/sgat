/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.sessions;

import dashboard.entities.TbPerfil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class TbPerfilFacade extends AbstractFacade<TbPerfil> {

    @PersistenceContext(unitName = "DASHBOARD_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TbPerfilFacade() {
        super(TbPerfil.class);
    }
    
    public TbPerfil findByPessoa(int codigo)
    {
        TypedQuery query = em.createNamedQuery("TbPerfil.findByPessoa", TbPerfil.class);
        query.setParameter("pessoa", codigo);
        return returnSingleValue(query);
    }
}
