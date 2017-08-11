/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Municipio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    public Municipio findByIbge(int ibge){
        TypedQuery query = em.createNamedQuery("Municipio.findByIbge", Municipio.class);
        query.setParameter("ibge", ibge);
        return returnSingleValue(query);
    }
    
    public List<Municipio> findByUf(int uf){
        TypedQuery query = em.createNamedQuery("Municipio.findByUf", Municipio.class);
        query.setParameter("uf", uf);
        return query.getResultList();
    }
    
    public List<Municipio> findByDescription(String desc){
        Query query = em.createQuery("SELECT m FROM Municipio m WHERE m.nome LIKE :desc");
        query.setParameter("desc", "%"+desc+"%");
        System.out.println("Trying to fetch... %"+desc+"%");
        return query.getResultList();
    }
    
}
