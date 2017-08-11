/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.DocumentoOficial;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author HUUFMA
 */
@Stateless
public class DocumentoOficialFacade extends AbstractFacade<DocumentoOficial> {
    @PersistenceContext(unitName = "SINTS_MAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoOficialFacade() {
        super(DocumentoOficial.class);
    }
    
    public DocumentoOficial getLastItem(int type){
        TypedQuery<DocumentoOficial> query = em.createNamedQuery("DocumentoOficial.findByTipo", DocumentoOficial.class);
        query.setParameter("tipo", type);
        List<DocumentoOficial> list = query.getResultList();
        List<DocumentoOficial> currentYearDocs = new ArrayList<DocumentoOficial>();
        
        Date dt = new Date();
        
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.format(dt); 
        cal.setTime(dt);
        int current_year = cal.get(Calendar.YEAR);
        
        for(DocumentoOficial d : list)
        {
            Date date = d.getDtDoc();

            cal.setTime(date);
            int current_doc_year = cal.get(Calendar.YEAR);
            if(current_doc_year == current_year)
                currentYearDocs.add(d);
        }
        
        if(!currentYearDocs.isEmpty())        
            return currentYearDocs.get(currentYearDocs.size() - 1);
        return null;    
    }
    
    public int getLastNumber(int type)
    {
        TypedQuery<DocumentoOficial> query = em.createNamedQuery("DocumentoOficial.findByTipo", DocumentoOficial.class);
        query.setParameter("tipo", type);
        List<DocumentoOficial> list = query.getResultList();
        
        return list.size();
    }
}
