package Converters;

import entities.Tipo;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.TipoFacade;

@FacesConverter(value = "tipoConverter")
public class TipoConverter implements Converter {

    @EJB
    private TipoFacade facade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            Tipo tipo = facade.findByDescricao(value);
            return tipo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Tipo tipo = (Tipo) value;
            return tipo.getDescricao();
        }
        return "";
    }
    
}
