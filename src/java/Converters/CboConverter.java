package Converters;

import entities.Cbo;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.CboFacade;

@FacesConverter(value = "cboConverter")
public class CboConverter implements Converter {

    @EJB
    private CboFacade facade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            Cbo cbo = facade.findByCodigo(value);
            return cbo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Cbo cbo = (Cbo) value;
            return cbo.getNome();
        }
        return "";
    }
    
}
