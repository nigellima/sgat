/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.sessions;

import dashboard.entities.MunicipiosCoordenadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nlima.huufma
 */
@Stateless
public class MunicipiosCoordenadasFacade extends AbstractFacade<MunicipiosCoordenadas> {

    @PersistenceContext(unitName = "DASHBOARD_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosCoordenadasFacade() {
        super(MunicipiosCoordenadas.class);
    }
    
    public MunicipiosCoordenadas findByCodigo(int codigo)
    {
        TypedQuery query = em.createNamedQuery("MunicipiosCoordenadas.findByIbge", MunicipiosCoordenadas.class);
        query.setParameter("ibge", codigo);
        return returnSingleValue(query);
    }
}
