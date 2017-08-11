/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Autentificacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
@Stateless
public class AutentificacaoFacade extends AbstractFacade<Autentificacao> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AutentificacaoFacade(){
        super(Autentificacao.class);
    }
    
    public Autentificacao FindByCode(String codigo){
        TypedQuery query = em.createNamedQuery("Autentificacao.findByCodigo", Autentificacao.class);
        query.setParameter("codigo", codigo);
        return (Autentificacao) returnSingleValue(query);
    }
}
