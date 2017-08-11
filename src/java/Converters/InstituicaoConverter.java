package Converters;

import entities.Instituicao;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.InstituicaoFacade;

@FacesConverter(value = "instituicaoConverter")
public class InstituicaoConverter implements Converter {

    @EJB
    private InstituicaoFacade facade;
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
           // List<Instituicao> inst = facade.findByDescricao(value);
            //return inst;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Instituicao inst = (Instituicao) value;
            return inst.getDescricao();
        }
        return "";
    }
    
}
