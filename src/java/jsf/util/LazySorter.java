/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Atividade;
import entities.Pessoa;
import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author HUUFMA
 */
public class LazySorter implements Comparator<Atividade>{
  
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
    @Override
    public int compare(Atividade o1, Atividade o2) {
       try {
            //Object value1 = Pessoa.class.getField(this.sortField).get(o1);
            Field field1 =  Atividade.class.getDeclaredField(this.sortField);
            field1.setAccessible(true);
            System.out.println("sorter " + field1);
            Object value1 = field1.get(o1);
            
            Field field2 =  Atividade.class.getDeclaredField(this.sortField);
            field1.setAccessible(true);
            Object value2 = field1.get(o2);
          
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
    
}
