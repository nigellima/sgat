package Converters;

import entities.Pessoa;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.PessoaFacade;

@FacesConverter(value = "pessoaConverter")
public class PessoaConverter implements Converter {

    @EJB
    private PessoaFacade pessoaFacade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            Pessoa pessoa = pessoaFacade.findPessoaById(Integer.parseInt(value));
            System.out.println(pessoa.getNome());
            return pessoa;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Pessoa pessoa = (Pessoa) value;
            System.out.println("string1");
            return String.valueOf(pessoa.getId());
            
        }
        return null;
    }
    
}
