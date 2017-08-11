/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import entities.Pessoa;
import java.util.ResourceBundle;
import jsf.util.JsfUtil;

/**
 *
 * @author HUUFMA
 */
public class PessoaValidator {

    public PessoaValidator() {
    }
    
    boolean isValidPessoa(Pessoa p)
    {
        
        return true;
    }
    
    
    private boolean isValidNome(String nome)
    {
        if(nome.contains(" ") && !nome.matches(".*\\d.*"))
        {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/resources/BundlePessoa").getString("ValidateNomeError"), "nome-message-output");
            return false;
        }
        
        return true;
    }
        
}
