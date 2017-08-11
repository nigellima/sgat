/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import entities.Funcionario;
import entities.Pessoa;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jsf.util.AtividadeGenero;
import sessions.FuncionarioFacade;
import sessions.PessoaFacade;

/**
 *
 * @author HUUFMA
 */
@FacesConverter(value = "atividadeGeneroConverter")
public class AtividadeGeneroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        System.out.println("Converter " +value);
        AtividadeGenero tmp = null;
        if(value != null && !value.isEmpty())
        {
            int intValue = Integer.parseInt(value);
            System.out.println("If " +intValue);
            
            if(intValue == AtividadeGenero.TODAS)
                tmp = new AtividadeGenero(AtividadeGenero.TODAS, "Todas as atividades");
            else if(intValue == AtividadeGenero.NTS)
                tmp =  new AtividadeGenero(AtividadeGenero.NTS, "NTS");
            else if(intValue == AtividadeGenero.TBR)    
                tmp =  new AtividadeGenero(AtividadeGenero.TBR, "TBR");
        }
        return tmp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            AtividadeGenero atv = (AtividadeGenero) value;
            return ""+atv.getGenero();
        }
        return "";
    }
}
