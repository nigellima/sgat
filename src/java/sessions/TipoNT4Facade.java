/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TipoNt4;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class TipoNT4Facade extends AbstractFacade<TipoNt4> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoNT4Facade() {
        super(TipoNt4.class);
    }
    
    public TipoNt4 findById(int value){
        TypedQuery query = em.createNamedQuery("Tipo.findById", TipoNt4.class);
        query.setParameter("id", value);
        return returnSingleValue(query);
    }
    
    public TipoNt4 findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Tipo.findByDescricao", TipoNt4.class);
        query.setParameter("descricao", value);
        return returnSingleValue(query);
    }
    
}
