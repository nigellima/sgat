/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Pessoa;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import jsf.util.PaginationHelper;
import sessions.PessoaFacade;

/**
 *
 * @author HUUFMA
 */
public class LazyPessoaDataModel extends LazyDataModel<Pessoa>{
    private List<Pessoa> datasource;
    
    PessoaFacade pessoaFacade;
    
    public LazyPessoaDataModel(PessoaFacade pFacade) 
    {
        this.pessoaFacade = pFacade;
    }
    
    @Override
    public Pessoa getRowData(String rowKey) 
    {
        for(Pessoa pessoa : datasource) 
        {            
            if(pessoa.getId()==Integer.parseInt(rowKey))
            {
                return pessoa;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Pessoa pessoa)
    {
        return pessoa.getId();
    }
    

    @Override
    public List<Pessoa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) 
    {
        List<Pessoa> data = new ArrayList<>();
        try{
            this.datasource =  pessoaFacade.getAll(first, pageSize, sortField, sortOrder, filters);//pessoaFacade.findRangePagination(range);

            
            for(Pessoa pessoa : datasource) 
            {
                data.add(pessoa);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(pessoaFacade.countTotal());
        
        return data;
    }
}
