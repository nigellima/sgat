/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TipoObjetoApr;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class TipoObjetoAprFacade extends AbstractFacade<TipoObjetoApr> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoObjetoAprFacade() {
        super(TipoObjetoApr.class);
    }
    
}
