package beans;

import entities.Instituicao;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;
import jsf.util.JsfUtil;
import sessions.InstituicaoFacade;

@ManagedBean(name = "instituicaoController")
@ViewScoped
public class InstituicaoController implements Serializable {
    
    private Instituicao instituicao = new Instituicao();
    
    @EJB
    private InstituicaoFacade ejbInstituicao;

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public InstituicaoFacade getEjbInstituicao() {
        return ejbInstituicao;
    }

    public void setEjbInstituicao(InstituicaoFacade ejbInstituicao) {
        this.ejbInstituicao = ejbInstituicao;
    }
    
    public void prepareCreate(){
        setInstituicao(new Instituicao());
    }
    
    public String saveInstituicao(){
        try {
           
            List<Instituicao> tmp = null;
            try{
                tmp = getEjbInstituicao().findByDescricao(getInstituicao().getDescricao());
                System.out.println(tmp);
            }
            catch(NoResultException nre)
            {
               //do nothing
            }
            if(!tmp.isEmpty())
            {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/resources/BundleAtividade").getString("Instituicao_Error"), "formInstituicao");
                return null;
            }
            getEjbInstituicao().create(getInstituicao());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/BundleAtividade").getString("Instituicao_Saved"), "formInstituicao");
            prepareCreate();
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/BundleAtividade").getString("Instituicao_Error"), "formInstituicao");
        }
        
        return ResourceBundle.getBundle("/resources/BundlePaths").getString("Create_Conexao_Form");
    }
    
    
}
