/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pessoa;
import entities.Profissional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucasmaia.huufma
 */
@Stateless
public class ProfissionalFacade extends AbstractFacade<Profissional> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfissionalFacade() {
        super(Profissional.class);
    }
    
    public Profissional getByCNS(String cns){
        cns = cns.trim();
        TypedQuery<Profissional> query = em.createNamedQuery("Profissional.findByCns", Profissional.class);
        query.setParameter("cns", cns);
        return (Profissional) returnSingleValue(query);
    } 
    
    public Profissional getByPessoa(Pessoa pessoa){
        TypedQuery<Profissional> query = em.createNamedQuery("Profissional.findByPessoa", Profissional.class);
        query.setParameter("pessoa", pessoa);
        return (Profissional) returnSingleValue(query);
    }    
}
