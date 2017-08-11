/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Presenca;
import entities.PresencaKeys;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nigel
 */
@Stateless
public class PresencaKeysFacade extends AbstractFacade<PresencaKeys> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresencaKeysFacade() {
        super(PresencaKeys.class);
    }
    
    public PresencaKeys getByPresenca(Presenca presenca)
    {
        TypedQuery query = em.createNamedQuery("PresencaKeys.findByPresenca", PresencaKeys.class);
        query.setParameter("presenca", presenca);
        return returnSingleValue(query);
    }
}
