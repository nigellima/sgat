/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Estado;
import entities.Municipio;
import entities.Pessoa;
import entities.ProfGeral;
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
import jsf.util.LazyProfGeralDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sessions.CboFacade;
import sessions.EstadoFacade;
import sessions.MunicipioFacade;
import sessions.PessoaFacade;
import sessions.ProfGeralFacade;

/**
 *
 * @author lucasmaia.huufma
 */
@ManagedBean(name = "profgeralController")
@SessionScoped
public class ProfGeralController implements Serializable {

    
    private ProfGeral current;
    private DataModel items = null;
    private jsf.util.PaginationHelper pagination;
    private LazyDataModel<ProfGeral> lazyModel;
    public boolean profCreated = false;
    private String cboCodigo;
    private Integer estadoId;
    private Integer municipioId;
    private List<Estado> estados;
    private List<Municipio> municipios;
    
    @EJB
    private ProfGeralFacade ejbProfGeral;
    @EJB
    private EstadoFacade ejbEstado;
    @EJB
    private MunicipioFacade ejbMunicipio;
    @EJB
    private CboFacade ejbCbo;
    
        
    @PostConstruct
    public void init(){
        lazyModel = new LazyProfGeralDataModel(ejbProfGeral);
        System.out.println("init");
    }
    
    public LazyDataModel<ProfGeral> getLazyModel() {
        return lazyModel;
    }
    
    public ProfGeralController() {
    }

    public ProfGeral getSelected() {
        if (current == null) {
            current = new ProfGeral();
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

    public String getCboCodigo() {
        return cboCodigo;
    }

    public void setCboCodigo(String cboCodigo) {
        this.cboCodigo = cboCodigo;
    }
    
    //=============================================================================================
    
    public void initAll(ProfGeral p){
        if(p!=null){
            cboCodigo = p.getCbo().getCodigo();
            municipioId = p.getMunicipio().getIbge();
            estadoId = p.getMunicipio().getUf().getId();
            updateMunicipioList();
        }
    }
    
    public void resetAll(){
        cboCodigo = null;
        municipioId = 0;
        estadoId = 0;
        municipios = new ArrayList<>();
        current = new ProfGeral();
    }
    
    public void updateMunicipioList(){
        if(estadoId!=0){
            System.out.println("Updating Municipio List..");
            municipios = ejbMunicipio.findByUf(estadoId);
            System.out.println("municipioListSize: " + municipios.size());
        }
    }

    public String prepareCreate() {
        resetAll();
        return "/profGerais/create";
    }    
    
    public String prepareCreate(Pessoa p){        
        resetAll();
        current.setPessoa(p);
        return "/profGerais/create";
    }

    public String create() {
        try {      
            
            current.setMunicipio(ejbMunicipio.findByIbge(municipioId));
            current.setCbo(ejbCbo.findByCodigo(cboCodigo));
            
            if(current.getCbo()==null||current.getMunicipio()==null){
                throw new Exception("Todos os campos são obrigatórios.");
            }
            
            ejbProfGeral.create(current);
            JsfUtil.addSuccessMessage("Profissao cadastrada com sucesso!", "formProfGeral");
            return "/cadastros/index";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfGeral");
            return null;
        }
    }
    
    public String update() {
        try {      
            current.setMunicipio(ejbMunicipio.findByIbge(municipioId));
            current.setCbo(ejbCbo.findByCodigo(cboCodigo));
            ejbProfGeral.edit(current);
            JsfUtil.addSuccessMessage("Profissao atualizada com sucesso!", "formProfGeral");
            return "/profGeral/view";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfGeral");
            return null;
        }
    }
    
    public String delete() {
        try {      
            ejbProfGeral.remove(current);
            JsfUtil.addSuccessMessage("Profissao removida com sucesso!", "formProfGeral");
            return "/cadastros/index";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.toString(), "formProfGeral");
            return null;
        }
    }
    
    private void recreateModel() {
        items = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbProfGeral.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbProfGeral.findAll(), true);
    }

    public ProfGeral getProfGeral(java.lang.Integer id) {
        return ejbProfGeral.find(id);
    }
    
    public jsf.util.PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new jsf.util.PaginationHelper(1000) {

                @Override
                public int getItemsCount() {
                    return ejbProfGeral.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(ejbProfGeral.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        this.current = ((ProfGeral) event.getObject());
    }
    
    public String viewSelected(Pessoa pessoa){
        if(pessoa!=null){
            ProfGeral p = ejbProfGeral.getByPessoa(pessoa);
            if(p!=null){
                System.out.println(p);
                this.current = p;
                return "/profGerais/view";
            }
            return prepareCreate(pessoa);
        }
        System.out.println("Pessoa enviada eh nula!!!");
        return null;
    }
    
}
