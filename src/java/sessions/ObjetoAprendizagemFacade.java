/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ObjetoAprendizagem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class ObjetoAprendizagemFacade extends AbstractFacade<ObjetoAprendizagem> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetoAprendizagemFacade() {
        super(ObjetoAprendizagem.class);
    }
    
}
