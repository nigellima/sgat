package Validators;

import entities.Tipo;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("tipo")
public class TipoValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Tipo tipo = (Tipo) value;
        if(tipo==null){
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidTipo"), 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidTipo_Detail")));
        }
        
    }
    
}
