package service;

import entities.Endereco;
import entities.Estado;
import entities.Municipio;
import java.util.ArrayList;
import java.util.List;

public class JSONMunicipio extends JSONConverter {
        
    public JSONMunicipio(String source) {
        super(source);
    }
    
    private Estado getMunicipioUf(String ufStr, List<Estado> estados){        
        for(Estado estado : estados){
            if(ufStr.equalsIgnoreCase(estado.getSigla().trim())){
                return estado;
            }
        }
        
        return null;
    }
    
    public List<Municipio> toMunicipio(List<Estado> estados) {
        List<Municipio> municipios = new ArrayList<>();
        Municipio mun;
        JSONItem novoItem = getNextItem();
        
        while(novoItem!=null){      
            mun = new Municipio();
            mun.setIbge(novoItem.getIntValue("codigo"));
            mun.setNome(novoItem.getStringValue("nome"));
            mun.setUf(getMunicipioUf(novoItem.getStringValue("uf"), estados));
            mun.setEnderecoList(new ArrayList<Endereco>());
            
            municipios.add(mun);
            
            novoItem = getNextItem();
        }
        
        return municipios;
    }
    
}
