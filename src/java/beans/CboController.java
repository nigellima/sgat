/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Cbo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.CboFacade;

/**
 *
 * @author lucasmaia.huufma
 */
@ManagedBean(name = "cboController")
@SessionScoped
public class CboController implements Serializable {
        
    @EJB
    private CboFacade facade;
    private List<Cbo> list;
       
    public CboController() {
    }

    public CboFacade getFacade() {
        return facade;
    }

    public List<Cbo> getList() {
        list = getFacade().findAll();
        return list;
    }

    public void setList(List<Cbo> list) {
        this.list = list;
    }
}
