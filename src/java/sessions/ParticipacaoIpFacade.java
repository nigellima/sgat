/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ParticipacaoIp;
import entities.ParticipacaoPessoa;
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
public class ParticipacaoIpFacade extends AbstractFacade<ParticipacaoIp> {
    
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ParticipacaoIpFacade(){
        super(ParticipacaoIp.class);
    }
    
    
    
    public ParticipacaoIp findByParticipacao(ParticipacaoPessoa part){
        TypedQuery query = em.createNamedQuery("ParticipacaoIp.findByParticipacao", ParticipacaoPessoa.class);
        query.setParameter("participacao", part);
        return (ParticipacaoIp) returnSingleValue(query);
    }
}
