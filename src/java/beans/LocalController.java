/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Estado;
import entities.Municipio;
import entities.ProfGeral;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.EstadoFacade;
import sessions.MunicipioFacade;

/**
 *
 * @author lucasmaia.huufma
 */
@ManagedBean(name = "localController")
@SessionScoped
public class LocalController implements Serializable {
    
    private Estado currentEstado;
    
    @EJB
    private MunicipioFacade munFacade;
    private List<Municipio> municipios;
    
    @EJB
    private EstadoFacade estFacade;
    private List<Estado> estados;
    
    public LocalController() {
    }

    public Estado getCurrentEstado() {
        return currentEstado;
    }

    public void setCurrentEstado(Estado currentEstado) {
        this.currentEstado = currentEstado;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Estado> getEstados() {
        estados = estFacade.findAll();
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
    
    public void initAll(ProfGeral p){
        currentEstado = p.getMunicipio().getUf();
        updateMunicipioList();
    }
    
    public void updateMunicipioList(){
        System.out.println("Ajax de Municipio chamado!!!");
        if(currentEstado!=null){
            System.out.println("Updating Municipio List.. Estado: " + currentEstado);
            municipios = munFacade.findByUf(currentEstado.getId());
            if(municipios!=null)
                System.out.println("municipioListSize: " + municipios.size());
            else
                municipios = new ArrayList<>();
        }
    }
    
}
