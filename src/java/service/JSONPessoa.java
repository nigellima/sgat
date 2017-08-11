package service;

import entities.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class JSONPessoa extends JSONConverter {

    public JSONPessoa(String source) {
        super(source);
    }
    
    public List<Pessoa> toPessoa(){
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa; 
        JSONItem novoItem = getNextItem();
        
        while(novoItem!=null){       
            pessoa = new Pessoa();
            String nome = novoItem.getStringValue("profissional_nome");

            pessoa.setNome(nome.substring(0, nome.indexOf(" ")));
            pessoa.setSobrenome(nome.substring(nome.indexOf(" ") + 1));
            pessoa.setCpf(novoItem.getStringValue("profissional_cpf"));
            pessoa.setEmail("");
            pessoa.setCelular("");
            pessoa.setSexo(novoItem.getStringValue("profissional_sexo"));
                     
            pessoas.add(pessoa);
            novoItem = getNextItem();
        }
        
        return pessoas;
    }
    
}
