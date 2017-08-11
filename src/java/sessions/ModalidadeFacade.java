/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Modalidade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class ModalidadeFacade extends AbstractFacade<Modalidade> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModalidadeFacade() {
        super(Modalidade.class);
    }
    
    public Modalidade findById(int value){
        TypedQuery query = em.createNamedQuery("Modalidade.findById", Modalidade.class);
        query.setParameter("id", value);
        return returnSingleValue(query);
    }
    
    public Modalidade findByDescricao(String value){
        TypedQuery query = em.createNamedQuery("Modalidade.findByDescricao", Modalidade.class);
        query.setParameter("descricao", value);
        System.out.println(value);
        return returnSingleValue(query);
    }
}
