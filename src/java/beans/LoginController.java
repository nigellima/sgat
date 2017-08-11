package beans;

import Validators.LoginValidator;

import entities.Funcionario;
import entities.Pessoa;
import entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.JsfUtil;
import service.SessionContext;
import sessions.FuncionarioFacade;
import sessions.PessoaFacade;
import sessions.UsuarioFacade;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController  implements Serializable{
    
    private Usuario usuario = new Usuario();
    private Funcionario funcionario = new Funcionario();
    private Pessoa pessoa =  new Pessoa();
    private String email ="";
    private String password = "";
    private boolean loggedIn;
    
    
    @EJB
    private UsuarioFacade ebjUsuario;
    @EJB
    private FuncionarioFacade ejbFuncionario;
    @EJB
    private PessoaFacade ejbPessoa;
    
    //GETTERS AND SETTERS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public UsuarioFacade getEbjUsuario() {
        return ebjUsuario;
    }

    public void setEbjUsuario(UsuarioFacade ebjUsuario) {
        this.ebjUsuario = ebjUsuario;
    }

    public FuncionarioFacade getEjbFuncionario() {
        return ejbFuncionario;
    }

    public void setEjbFuncionario(FuncionarioFacade ejbFuncionario) {
        this.ejbFuncionario = ejbFuncionario;
    }

    public PessoaFacade getEjbPessoa() {
        return ejbPessoa;
    }

    public void setEjbPessoa(PessoaFacade ejbPessoa) {
        this.ejbPessoa = ejbPessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String logIn()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pessoa = getEjbPessoa().getByEmail(email);  
            System.out.println(pessoa);
            if (pessoa != null){
                List<Funcionario> funcionarios = getEjbFuncionario().findByPessoa(pessoa);
                System.out.println(funcionarios);
                if(funcionarios!=null && !funcionarios.isEmpty()){
                    funcionario = funcionarios.get(funcionarios.size()-1);
                    System.out.println(funcionario);
                }
                if (funcionario != null)
                {
                    usuario = getEbjUsuario().getByFuncionario(funcionario);
                    System.out.println(usuario);
                    if(usuario != null)
                    {
                        if(LoginValidator.validLogin(usuario, password))
                        {
                            System.out.println("logging " + context.toString());
                            loggedIn = true;
                            SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                            return "/public/home.xhtml?faces-redirect=true";
                        }
                        else {
                            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/resources/BundleLogin").getString("UserPasswordIncorrect"));
                            FacesContext.getCurrentInstance().validationFailed();
                        }
                    }
                }
            } 
        }
        catch(Exception ex){
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("/resources/BundleLogin").getString("UserNotFound"));
        return null;
    }
    
    public String logOut()
    {
        return prepareNewLogin();
    }
    
    
    private String prepareNewLogin()
    {
        SessionContext.getInstance().encerrarSessao();
        this.loggedIn = false;
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("/resources/BundleLogin").getString("SuccessfullLogOut"));
        return "/index" + "?faces-redirect=true";
    }
}
