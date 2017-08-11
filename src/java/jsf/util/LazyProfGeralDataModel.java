/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Pessoa;
import entities.ProfGeral;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sessions.ProfGeralFacade;

/**
 *
 * @author HUUFMA
 */
public class LazyProfGeralDataModel extends LazyDataModel<ProfGeral>{
    private List<ProfGeral> datasource;
    
    ProfGeralFacade profGeralFacade;
    
    public LazyProfGeralDataModel(ProfGeralFacade pFacade) 
    {
        this.profGeralFacade = pFacade;
    }
    
    @Override
    public ProfGeral getRowData(String rowKey) 
    {
        for(ProfGeral prof : datasource) 
        {            
            if(prof.getId()==Integer.parseInt(rowKey))
            {
                return prof;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(ProfGeral prof)
    {
        return prof.getId();
    }
    

    @Override
    public List<ProfGeral> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) 
    {
        List<ProfGeral> data = new ArrayList<>();
        try{
            this.datasource =  profGeralFacade.getAll(first, pageSize, sortField, sortOrder, filters);//pessoaFacade.findRangePagination(range);

            
            for(ProfGeral prof : datasource) 
            {
                data.add(prof);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(profGeralFacade.countTotal());
        
        return data;
    }
}
