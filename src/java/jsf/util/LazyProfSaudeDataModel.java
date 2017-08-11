/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.ProfSaude;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sessions.ProfSaudeFacade;

/**
 *
 * @author HUUFMA
 */
public class LazyProfSaudeDataModel extends LazyDataModel<ProfSaude>{
    private List<ProfSaude> datasource;
    
    ProfSaudeFacade profSaudeFacade;
    
    public LazyProfSaudeDataModel(ProfSaudeFacade pFacade) 
    {
        this.profSaudeFacade = pFacade;
    }
    
    @Override
    public ProfSaude getRowData(String rowKey) 
    {
        for(ProfSaude prof : datasource) 
        {            
            if(prof.getId()==Integer.parseInt(rowKey))
            {
                return prof;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(ProfSaude prof)
    {
        return prof.getId();
    }
    

    @Override
    public List<ProfSaude> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) 
    {
        List<ProfSaude> data = new ArrayList<>();
        try{
            this.datasource =  profSaudeFacade.getAll(first, pageSize, sortField, sortOrder, filters);//pessoaFacade.findRangePagination(range);

            
            for(ProfSaude prof : datasource) 
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
        this.setRowCount(profSaudeFacade.countTotal());
        
        return data;
    }
}
