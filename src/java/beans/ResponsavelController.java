package beans;

import entities.Funcionario;
import entities.Instituicao;
import entities.Pessoa;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import jsf.util.JsfUtil;
import sessions.FuncionarioFacade;
import sessions.PessoaFacade;

@ManagedBean(name = "responsavelController")
@ViewScoped
public class ResponsavelController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Funcionario responsavel;
    private String pessoaNome;
    private Integer instituicaoId;
    
    @EJB
    private FuncionarioFacade ejbResponsavel;
    @EJB
    private PessoaFacade ejbPessoa;
    
    public ResponsavelController(){
        responsavel = new Funcionario();
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Integer instituicao) {
        this.instituicaoId = instituicao;
    }
    
    
    
    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public FuncionarioFacade getEjbResponsavel() {
        return ejbResponsavel;
    }

    public void setEjbResponsavel(FuncionarioFacade ejbResponsavel) {
        this.ejbResponsavel = ejbResponsavel;
    }

    public PessoaFacade getEjbPessoa() {
        return ejbPessoa;
    }

    public void setEjbPessoa(PessoaFacade ejbPessoa) {
        this.ejbPessoa = ejbPessoa;
    }
    
    public List<Pessoa> getPessoas(){
        return getEjbPessoa().findAll();
    }
    
    public void updateInstituicao(ValueChangeEvent event){
        Instituicao inst = (Instituicao) event.getNewValue();
        getResponsavel().setInstituicao(inst);
    }
    
    public void prepareCreate(){
        setResponsavel(new Funcionario());
    }
    
    public String saveResponsavel(){
        try {
            Instituicao inst = new Instituicao();
            inst.setId(instituicaoId);
            getResponsavel().setInstituicao(inst);
            getEjbResponsavel().create(responsavel);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/BundleAtividade").getString("Responsavel_Saved"), "formResponsavel");
            prepareCreate();
            return "view_create_conexao";
        }
        catch(Exception ex){
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/BundleAtividade").getString("Responsavel_Error"), "formResponsavel");
        }
        
        return ResourceBundle.getBundle("/resources/BundlePaths").getString("Create_Conexao_Form");
    }
    
    
}
