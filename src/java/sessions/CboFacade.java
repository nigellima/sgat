/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cbo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class CboFacade extends AbstractFacade<Cbo> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CboFacade() {
        super(Cbo.class);
    }
    
    public Cbo findByCodigo(String codigo){
        TypedQuery query = em.createNamedQuery("Cbo.findByCodigo", Cbo.class);
        query.setParameter("codigo", codigo);
        return (Cbo) returnSingleValue(query);
    }
    
}
