package Converters;

import entities.Local;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.LocalFacade;

@FacesConverter(value = "localConverter")
public class LocalConverter implements Converter {

    @EJB
    private LocalFacade facade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            Local local = facade.findByDescricao(value);
            return local;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Local local = (Local) value;
            return local.getDescricao();
        }
        return "";
    }
    
}
