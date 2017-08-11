package beans;

import entities.Pessoa;
import jsf.util.JsfUtil;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import jsf.util.LazyPessoaDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sessions.PessoaFacade;

@ManagedBean(name = "pessoaController")
@SessionScoped
public class PessoaController implements Serializable {

    private Pessoa current;
    private DataModel items = null;
    
    private jsf.util.PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private PessoaFacade ejbFacade;
    public boolean pessoaCreated = false;
    
    private LazyDataModel<Pessoa> lazyModel;
    
     @PostConstruct
    public void init()
    {
        lazyModel = new LazyPessoaDataModel(ejbFacade);
        System.out.println("init");
    }
    
    public LazyDataModel<Pessoa> getLazyModel() {
        return lazyModel;
    }
    
    public PessoaController() {
    }

    public Pessoa getSelected() {
        if (current == null) {
            current = new Pessoa();
        }
        return current;
    }

    private PessoaFacade getFacade() {
        return ejbFacade;
    }

    public boolean isPessoaCreated() {
        return pessoaCreated;
    }

    public void setPessoaCreated(boolean pessoaCreated) {
        this.pessoaCreated = pessoaCreated;
    }
    
    public String prepareCreate() {
        current = new Pessoa();
        return "/pessoas/create";
    }

    public String create() {
        try {            
            current.setSobrenome(current.getSobrenome().trim());
            
            Pessoa aux = getFacade().getByCPF(current.getCpf());
            if(aux!=null)
                if(!aux.equals(current))
                    throw new Exception("CPF já cadastrado por outra pessoa! " + aux.getNome() + " " + aux.getSobrenome());
            
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Pessoa cadasttrada com sucesso!", "formPessoa");
            return "/pessoas/view";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formPessoa");
            return null;
        }
    }

    public String update() {
        try {            
            current.setSobrenome(current.getSobrenome().trim());
            
            Pessoa aux = getFacade().getByEmail(current.getEmail());
            if(aux!=null)
                if(!aux.equals(current))
                    throw new Exception("Email já cadastrado por outra pessoa! " + aux.getNome() + " " + aux.getSobrenome());
            
            aux = getFacade().getByCPF(current.getCpf());
            if(aux!=null)
                if(!aux.equals(current))
                    throw new Exception("CPF já cadastrado por outra pessoa! " + aux.getNome() + " " + aux.getSobrenome());
                        
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Pessoa editada com sucesso!", "formPessoa");
            return "view.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/BundlePessoa").getString("PersistenceErrorOccured"), "formPessoa");
            return null;
        }
    }

    private void recreateModel() {
        items = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Pessoa getPessoa(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
     public jsf.util.PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new jsf.util.PaginationHelper(1000) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public void onRowSelect(SelectEvent event)
    {
        this.current = ((Pessoa) event.getObject());
    }
    
    public String viewSelected(Pessoa p){
        System.out.println(p);
        this.current = p;
        return "/pessoas/view";
    }
}
