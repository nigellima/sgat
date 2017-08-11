package Validators;

import entities.Instituicao;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("instituicao")
public class InstituicaoValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Instituicao inst = (Instituicao) value;
        if(inst==null){
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidInstituicao"), 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidInstituicao_Detail")));
        }
        
    }
    
}
