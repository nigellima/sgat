/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Endereco;
import entities.Estado;
import entities.Municipio;
import entities.Pessoa;
import entities.ProfSaude;
import entities.Ubs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import jsf.util.JsfUtil;
import jsf.util.LazyProfSaudeDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sessions.EstadoFacade;
import sessions.MunicipioFacade;
import sessions.ProfSaudeFacade;
import sessions.UbsFacade;

/**
 *
 * @author lucasmaia.huufma
 */
@ManagedBean(name = "profsaudeController")
@SessionScoped
public class ProfSaudeController implements Serializable {

    
    private ProfSaude current;
    private DataModel items = null;
    private jsf.util.PaginationHelper pagination;
    private LazyDataModel<ProfSaude> lazyModel;
    public boolean profCreated = false;
    private Integer estadoId;
    private Integer municipioId;
    private Integer ubsId;
    private List<Estado> estados;
    private List<Municipio> municipios;
    private List<Ubs> unidades;
    
    @EJB
    private ProfSaudeFacade ejbProfSaude;
    @EJB
    private EstadoFacade ejbEstado;
    @EJB
    private MunicipioFacade ejbMunicipio;
    @EJB
    private UbsFacade ejbUbs;
    
        
    @PostConstruct
    public void init(){
        lazyModel = new LazyProfSaudeDataModel(ejbProfSaude);
        System.out.println("init");
    }
    
    public LazyDataModel<ProfSaude> getLazyModel() {
        return lazyModel;
    }
    
    public ProfSaudeController() {
    }

    public ProfSaude getSelected() {
        if (current == null) {
            current = new ProfSaude();
        }
        return current;
    }
        
    public boolean isProfCreated() {
        return profCreated;
    }

    public void setProfCreated(boolean profCreated) {
        this.profCreated = profCreated;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }

    public Integer getUbsId() {
        return ubsId;
    }

    public void setUbsId(Integer ubsId) {
        this.ubsId = ubsId;
    }

    public List<Estado> getEstados() {
        estados = ejbEstado.findAll();
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Ubs> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Ubs> unidades) {
        this.unidades = unidades;
    }
    
    //=============================================================================================
    
    public void initAll(ProfSaude p){
        if(p!=null){
            estadoId = p.getUbs().getEndereco().getMunicipio().getUf().getId();
            municipioId = p.getUbs().getEndereco().getMunicipio().getIbge();
            ubsId = p.getUbs().getCnes();
            updateMunicipioList();
            updateUbsList();
        }
    }
    
    public void resetAll(){
        municipioId = 0;
        estadoId = 0;
        municipios = new ArrayList<>();
        unidades = new ArrayList<>();
        current = new ProfSaude();
    }
    
    public void updateMunicipioList(){
        if(estadoId!=0){
            System.out.println("Updating Municipio List..");
            municipios = ejbMunicipio.findByUf(estadoId);
            System.out.println("municipioListSize: " + municipios.size());
        }
    }
    
    public void updateUbsList(){
        if(municipioId!=0){
            Municipio m = ejbMunicipio.findByIbge(municipioId);
            System.out.println("Updating Ubs List..");
            unidades = ejbUbs.findByMunicipio(m);
            System.out.println("unidadesListSize: " + unidades.size());
        }
    }

    public String prepareCreate() {
        resetAll();
        return "/profSaude/create";
    }    
    
    public String prepareCreate(Pessoa p){        
        resetAll();
        current.setPessoa(p);
        return "/profSaude/create";
    }

    public String create() {
        try {      
            current.setUbs(ejbUbs.findByCnes(ubsId));
            if(current.getUbs()==null)
                throw new Exception("Ubs deve ser informada.");
            current.setEquipe("null");
            ejbProfSaude.create(current);
            JsfUtil.addSuccessMessage("Vínculo cadastrado com sucesso!", "formProfSaude");
            return "/cadastros/index";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfSaude");
            return null;
        }
    }
    
    public String update() {
        try {      
            current.setUbs(ejbUbs.findByCnes(ubsId));
            if(current.getUbs()==null)
                throw new Exception("Ubs deve ser informada.");
            ejbProfSaude.edit(current);
            JsfUtil.addSuccessMessage("Vínculo atualizado com sucesso!", "formProfSaude");
            return "/profSaude/view";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfSaude");
            return null;
        }
    }
    
    public String delete() {
        try {      
            ejbProfSaude.remove(current);
            JsfUtil.addSuccessMessage("Vínculo removido com sucesso!", "formProfSaude");
            return "/cadastros/index";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfSaude");
            return null;
        }
    }
    
    private void recreateModel() {
        items = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbProfSaude.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbProfSaude.findAll(), true);
    }

    public ProfSaude getProfSaude(java.lang.Integer id) {
        return ejbProfSaude.find(id);
    }
    
    public jsf.util.PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new jsf.util.PaginationHelper(1000) {

                @Override
                public int getItemsCount() {
                    return ejbProfSaude.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(ejbProfSaude.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
    
    public void onRowSelect(SelectEvent event){
        this.current = ((ProfSaude) event.getObject());
    }
    
    public String viewSelected(Pessoa pessoa){
        if(pessoa!=null){
            ProfSaude p = ejbProfSaude.getByPessoa(pessoa);
            if(p!=null){
                System.out.println(p);
                this.current = p;
                return "/profSaude/view";
            }
            return prepareCreate(pessoa);
        }
        System.out.println("Pessoa enviada eh nula!!!");
        return null;
    }
    
}
