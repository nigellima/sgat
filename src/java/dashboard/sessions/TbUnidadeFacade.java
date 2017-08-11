/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.sessions;

import dashboard.entities.TbUnidade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class TbUnidadeFacade extends AbstractFacade<TbUnidade> {

    @PersistenceContext(unitName = "DASHBOARD_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TbUnidadeFacade() {
        super(TbUnidade.class);
    }
    
    public TbUnidade findByCodigo(int codigo)
    {
        TypedQuery query = em.createNamedQuery("TbUnidade.findByCodigo", TbUnidade.class);
        query.setParameter("codigo", codigo);
        return returnSingleValue(query);
    }
}