package service;

import entities.Cbo;
import entities.Municipio;
import entities.Pessoa;
import entities.Profissional;
import entities.Ubs;
import java.util.ArrayList;
import java.util.List;

public class JSONProfissional extends JSONConverter {
    
    public JSONProfissional(String source){
        super(source);
    }
    
    public Pessoa getPessoa(JSONItem item, List<Pessoa> pessoas){        
        String nomeCompleto = item.getStringValue("profissional_nome");
        String nome = nomeCompleto.substring(0, nomeCompleto.indexOf(" "));
        String sobreNome = nomeCompleto.substring(nomeCompleto.indexOf(" ") + 1);
        String cpf = item.getStringValue("profissional_cpf");
               
        for(Pessoa p : pessoas){
            if(p.getCpf()!=null|| !p.getCpf().equals("")){
                if(cpf.equals(p.getCpf()))
                    return p;
            }
            else {
                if(nome.trim().equalsIgnoreCase(p.getNome().trim())&&sobreNome.trim().equalsIgnoreCase(p.getSobrenome().trim())){
                    return p;
                }
            }
        }
        
        return null;
    }
    
    public Ubs getUbs(int ubsCnes, List<Ubs> ubsList) {        
        for(Ubs u : ubsList){
            if(ubsCnes == u.getCnes()){
                return u;
            }
        }
        return null;
    }
    
    private Municipio getMunicipio(int ibge, List<Municipio> municipios){
        
        for(Municipio mun : municipios){
            if(ibge == mun.getIbge()){
                return mun;
            }
        }
        
        return null;
    } 
    
    private Cbo getCbo(String codigo, List<Cbo> cbos){
        for(Cbo cbo : cbos){
            if(codigo.equalsIgnoreCase(cbo.getCodigo())){
                return cbo;
            }
        }
        
        return null;
    }
    
    public List<Profissional> toProfissional(List<Pessoa> pessoas, List<Ubs> ubsList, List<Municipio> municipios, List<Cbo> cbos){
        List<Profissional> profissionais = new ArrayList<>();
        Profissional profissional;
        JSONItem novoItem = getNextItem();
        
        while(novoItem!=null){     
            profissional = new Profissional();
            profissional.setCns(novoItem.getStringValue("rofissional_codigo_cns"));
            profissional.setPessoa(getPessoa(novoItem, pessoas));
            profissional.setEquipe(novoItem.getStringValue("vinculo_equipe_saude_codigo_ine"));
            profissional.setUbs(getUbs(novoItem.getIntValue("vinculo_estabelecimento_codigo_cnes"), ubsList));
            profissional.setCbo(getCbo(novoItem.getStringValue("vinculo_profissao_codigo_cbo"), cbos));
            profissional.setMunicipio(getMunicipio(novoItem.getIntValue("vinculo_municipio_codigo_ibge"), municipios));
            profissional.setResidente(novoItem.getBoolValue("eh_residente"));
            profissional.setPerceptor(novoItem.getBoolValue("eh_perceptor"));
            
            if(profissional.getPessoa()!=null&&profissional.getUbs()!=null&&profissional.getCbo()!=null&&profissional.getMunicipio()!=null)
                profissionais.add(profissional);  
            
            novoItem = getNextItem();
        }
        
        return profissionais;
    }
}
