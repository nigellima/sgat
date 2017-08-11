package Validators;

import entities.Funcionario;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("funcionario")
public class FuncionarioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Funcionario fun = (Funcionario) value;
        if(fun==null){
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidFuncionario"), 
                    ResourceBundle.getBundle("/resources/ValidationMessages").getString("InvalidFuncionario_Detail")));
        }
        
    }
    
}
