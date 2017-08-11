package Converters;

import entities.Modalidade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.ModalidadeFacade;

@FacesConverter(value = "modalidadeConverter")
public class ModalidadeConverter implements Converter {

    @EJB
    private ModalidadeFacade facade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            if(!value.isEmpty()){
                Modalidade modalidade = facade.findByDescricao(value);
                return modalidade;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            try {
                Modalidade modalidade = (Modalidade) value;
                return modalidade.getDescricao();
            }
            catch(Exception ex){
                return ex.toString();
            }
        }
        return "";
    }
    
}
