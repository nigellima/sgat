/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Atividade;
import entities.Pessoa;
import entities.Solicitacao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sessions.PessoaFacade;
import sessions.SolicitacaoFacade;

/**
 *
 * @author HUUFMA
 */
public class LazySolicitacaoDataModel extends LazyDataModel<Atividade>{
    private List<Atividade> datasource;
    
    
    public LazySolicitacaoDataModel() 
    {
       // this.solicitacaoFacade = sFacade;
    }

    
    public LazySolicitacaoDataModel(List<Atividade> source) 
    {
       this.datasource = source;
    }
    
    
    public void setDatasource(List<Atividade> atvSource)
    {
        this.datasource = atvSource;
    }
    
    @Override
    public Atividade getRowData(String rowKey) 
    {
        for(Atividade atv : datasource) 
        {            
            if(atv.getId()==Integer.parseInt(rowKey))
            {
                return atv;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Atividade atv)
    {
        return atv.getId();
    }
    

    @Override
    public List<Atividade> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) 
    {
        List<Atividade> data = new ArrayList<Atividade>();
 
        //filter
        for(Atividade atv : datasource) {
            boolean match = true;
            System.out.println("filtros "+filters);
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    System.out.println("filtro no contador " + it.next());
                    try {
                        String filterProperty = it.next();
                        if(filterProperty.contains("modalidade.descricao"))
                        {
                            System.out.println("true");
                            if(atv.getModalidade().getDescricao().contains(String.valueOf(filters.get(filterProperty))))
                            {
                                System.out.println("true");
                            }
                        }
                        
                        Object filterValue = filters.get(filterProperty);
                       
                        String fieldValue = String.valueOf(atv.getClass().getField(filterProperty).get(atv));
                       
                        if(filterValue == null || fieldValue.contains(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(atv);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
