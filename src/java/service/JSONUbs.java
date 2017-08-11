package service;

import entities.Endereco;
import entities.Municipio;
import entities.Ubs;
import java.util.ArrayList;
import java.util.List;

public class JSONUbs extends JSONConverter {

    public JSONUbs(String source){
        super(source);
    }

    private Endereco getUbsEndereco(JSONItem item, List<Municipio> municipios) {
        Endereco end = new Endereco();
        
        end.setLogradouro(item.getStringValue("logradouro"));
        end.setNum(item.getStringValue("num_endereco"));
        end.setCep(item.getStringValue("cep"));
        end.setTelefone(item.getStringValue("telefone"));
        end.setMunicipio(getMunicipio(item.getIntValue("codigo_municipio"), municipios));
        
        return end;
    }
    
    private Municipio getMunicipio(int ibge, List<Municipio> municipios){
        
        for(Municipio mun : municipios){
            if(ibge == mun.getIbge()){
                return mun;
            }
        }
        
        return null;
    }
    
    public List<Ubs> toUbs(List<Municipio> municipios){
        List<Ubs> ubsList = new ArrayList<>();
        Ubs ubs;
        JSONItem novoItem = getNextItem();
        
        while(novoItem!=null){
            ubs = new Ubs();
            ubs.setCnes(novoItem.getIntValue("codigo_cnes"));
            ubs.setNome(novoItem.getStringValue("nome"));
            ubs.setEndereco(getUbsEndereco(novoItem, municipios));
            
            ubsList.add(ubs);
            novoItem = getNextItem();
        }
        
        
        return ubsList;
    }
    
    
}
