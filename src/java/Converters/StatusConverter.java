package Converters;

import entities.Status;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.StatusFacade;

@FacesConverter(value = "statusConverter")
public class StatusConverter implements Converter {

    @EJB
    private StatusFacade facade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            Status val = facade.findByDescricao(value);
            return val;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Status val = (Status) value;
            return val.getDescricao();
        }
        return "";
    }
    
}
