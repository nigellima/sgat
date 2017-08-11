
package service;

import entities.Equipe;
import entities.Ubs;
import java.util.ArrayList;
import java.util.List;

public class JSONEquipe extends JSONConverter {

    public JSONEquipe(String source){
        super(source);
    }

    private Ubs getEquipeUbs(int ubsCnes, List<Ubs> ubsList) {        
        for(Ubs u : ubsList){
            if(ubsCnes == u.getCnes()){
                return u;
            }
        }
        
        return null;
    }
    
    public List<Equipe> toEquipe(List<Ubs> ubsList){
        List<Equipe> equipes = new ArrayList<>();
        Equipe equipe;
        JSONItem novoItem = getNextItem();
        
        while(novoItem!=null){
            equipe = new Equipe();
            equipe.setCodigoIne(novoItem.getStringValue("codigo_ine"));
            equipe.setNome(novoItem.getStringValue("nome"));
            equipe.setUbs(getEquipeUbs(novoItem.getIntValue("estabelecimento_cnes"), ubsList));
            
            equipes.add(equipe);
            novoItem = getNextItem();
        }
        
        return equipes;
    }
    
}
