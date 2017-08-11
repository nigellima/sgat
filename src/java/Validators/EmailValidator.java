package Validators;

import entities.Modalidade;
import entities.Pessoa;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import sessions.PessoaFacade;

@FacesValidator("email")
public class EmailValidator implements Validator {

    @EJB
    private PessoaFacade ejbFacade;

    public PessoaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PessoaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
     
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        
        
        if(!isValidEmailAddress(email)){
             
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/BundlePessoa").getString("InvalidEmail"), 
                    ResourceBundle.getBundle("/resources/BundlePessoa").getString("InvalidEmail_Details")));
        }
        else if(equalsEmail(email))
        {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                    ResourceBundle.getBundle("/resources/BundlePessoa").getString("InvalidEqualsEmail"), 
                    ResourceBundle.getBundle("/resources/BundlePessoa").getString("InvalidEqualsEmail")));
        }
        
    }
 
    
    public static boolean isValidEmailAddress(String email) 
    {
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
          result = false;
        }
        return result;
    }
    
    private boolean equalsEmail(String email)
    {
        Pessoa p = getEjbFacade().getByEmail(email);
        //System.out.println(p);
        //System.out.println("email inserido "+p.getEmail());
        if(p != null)
           return true;
        else
           return false;
    }
}
