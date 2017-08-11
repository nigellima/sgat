package service;

import entities.Cbo;
import java.util.ArrayList;
import java.util.List;

public class JSONCbo extends JSONConverter {
    
    public JSONCbo(String source){
        super(source);
    }
    
    public List<Cbo> toCbo(){
        List<Cbo> cbos = new ArrayList<>();
        JSONItem novoItem = getNextItem();
        Cbo cbo;
        
        while(novoItem!=null){
            cbo = new Cbo();
            cbo.setCodigo(novoItem.getStringValue("codigo"));
            cbo.setNome(novoItem.getStringValue("nome"));
            
            cbos.add(cbo);
            novoItem = getNextItem();
        }
        
        return cbos;
    }
}
