/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Endereco;
import entities.Municipio;
import entities.Ubs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class UbsFacade extends AbstractFacade<Ubs> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbsFacade() {
        super(Ubs.class);
    }
    
    public Ubs findByCnes(int cnes){
        TypedQuery query = em.createNamedQuery("Ubs.findByCnes", Ubs.class);
        query.setParameter("cnes", cnes);
        return (Ubs) returnSingleValue(query);
    }
    
    public List<Ubs> findByMunicipio(Municipio m){
        List<Ubs> all = this.findAll();
        List<Ubs> query = new ArrayList<>();
        for(Ubs u : all){
            if(u.getEndereco().getMunicipio().equals(m))
                query.add(u);
        }
        return query;
    }
    
}
