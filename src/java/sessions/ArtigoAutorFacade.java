/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Artigo;
import entities.ArtigoAutor;
import entities.Pessoa;
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
public class ArtigoAutorFacade extends AbstractFacade<ArtigoAutor> {

    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtigoAutorFacade() {
        super(ArtigoAutor.class);
    }
    
    public List<ArtigoAutor> findByArtigo(Artigo artigo)
    {
        TypedQuery query = em.createNamedQuery("ArtigoAutor.findByArtigo", ArtigoAutor.class);
        query.setParameter("artigo", artigo);
        return query.getResultList();
    }
    
    public List<ArtigoAutor> findByAutor(Pessoa pessoa)
    {
        TypedQuery query = em.createNamedQuery("ArtigoAutor.findByPessoa", ArtigoAutor.class);
        query.setParameter("pessoa", pessoa);
        return query.getResultList();
    }
    
}
