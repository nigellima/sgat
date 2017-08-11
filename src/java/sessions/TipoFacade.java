/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class TipoFacade extends AbstractFacade<Tipo> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoFacade() {
        super(Tipo.class);
    }
    
    public Tipo findById(int value){
        TypedQuery query = em.createNamedQuery("Tipo.findById", Tipo.class);
        query.setParameter("id", value);
        return (Tipo) query.getSingleResult();
    }
    
    public Tipo findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Tipo.findByDescricao", Tipo.class);
        query.setParameter("descricao", value);
        return (Tipo) query.getSingleResult();
    }
    
}
