package Validators;

import entities.Modalidade;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("modalidade")
public class ModalidadelValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Modalidade modalidade = (Modalidade) value;
        if(modalidade==null){
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidModalidade"), 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidModalidade_Detail")));
        }
        
    }
    
}
