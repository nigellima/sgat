/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Avaliacoes;
import entities.Perguntas;
import entities.Presenca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nigel
 */
@Stateless
public class AvaliacoesFacade extends AbstractFacade<Avaliacoes> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvaliacoesFacade() {
        super(Avaliacoes.class);
    }
    
    public Avaliacoes findByComposedKey(Presenca presenca, int pergunta){
        Perguntas perg = new Perguntas();
        perg.setId(pergunta);
        TypedQuery query = em.createNamedQuery("Avaliacoes.findByComposedKey", Avaliacoes.class);
        query.setParameter("presenca", presenca);
        query.setParameter("pergunta", perg);
        return (Avaliacoes) returnSingleValue(query);
    }
    
    public List<Avaliacoes> findByPresenca(Presenca presenca){
        TypedQuery query = em.createNamedQuery("Avaliacoes.findByPresenca", Avaliacoes.class);
        query.setParameter("presenca", presenca);
        return query.getResultList();
    }
}
