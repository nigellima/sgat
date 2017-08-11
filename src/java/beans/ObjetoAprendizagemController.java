/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.jcraft.jsch.SftpException;
import entities.Atividade;
import entities.ObjetoAprendizagem;
import entities.Palestras;
import entities.ParticipacaoLocal;
import entities.ParticipacaoPessoa;
import entities.Pessoa;
import entities.ProfGeral;
import entities.TipoObjetoApr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.DateUtils;
import jsf.util.JsfUtil;
import jsf.util.ObjetoAprendizagemUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import sessions.AtividadeFacade;
import sessions.ObjetoAprendizagemFacade;
import sessions.PalestrasFacade;
import sessions.TipoObjetoAprFacade;

/**
 *
 * @author nlima.huufma
 */
@ManagedBean(name = "objetoAprendizagemController")
@SessionScoped
public class ObjetoAprendizagemController implements Serializable {

    ObjetoAprendizagem objeto;
    List<TipoObjetoApr> tipos;
    List<Atividade> atvidadesRelacionadas;
    Atividade atividade, selectedAtividade;
    int atividadeRelacionadaID = 0, tipo = 0;
    
    boolean plataforma, areas, avasus, redeSoc, outros;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Atividade> getAtvidadesRelacionadas() {
        return atvidadesRelacionadas;
    }

    public void setAtvidadesRelacionadas(List<Atividade> atvidadesRelacionadas) {
        this.atvidadesRelacionadas = atvidadesRelacionadas;
    }

    public int getAtividadeRelacionadaID() {
        return atividadeRelacionadaID;
    }

    public void setAtividadeRelacionadaID(int atividadeRelacionadaID) {
        this.atividadeRelacionadaID = atividadeRelacionadaID;
    }

    public Atividade getSelectedAtividade() {
        return selectedAtividade;
    }

    public void setSelectedAtividade(Atividade selectedAtividade) {
        this.selectedAtividade = selectedAtividade;
    }

    
    public boolean isPlataforma() {
        return plataforma;
    }

    public void setPlataforma(boolean plataforma) {
        this.plataforma = plataforma;
    }

    public boolean isAreas() {
        return areas;
    }

    public void setAreas(boolean areas) {
        this.areas = areas;
    }

    public boolean isAvasus() {
        return avasus;
    }

    public void setAvasus(boolean avasus) {
        this.avasus = avasus;
    }

    public boolean isRedeSoc() {
        return redeSoc;
    }

    public void setRedeSoc(boolean redeSoc) {
        this.redeSoc = redeSoc;
    }

    public boolean isOutros() {
        return outros;
    }

    public void setOutros(boolean outros) {
        this.outros = outros;
    }
    
    public List<TipoObjetoApr> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoObjetoApr> tipos) {
        this.tipos = tipos;
    }
    
    public ObjetoAprendizagem getObjeto() {
        return objeto;
    }

    public void setObjeto(ObjetoAprendizagem objeto) {
        this.objeto = objeto;
    }

    public ObjetoAprendizagemFacade getEjbObjeto() {
        return ejbObjeto;
    }

    public void setEjbObjeto(ObjetoAprendizagemFacade ejbObjeto) {
        this.ejbObjeto = ejbObjeto;
    }
    
    /**
     * Creates a new instance of ObjetoAprendizagemController
     */
    @EJB
    ObjetoAprendizagemFacade ejbObjeto;
    
    @EJB 
    TipoObjetoAprFacade ejbTipo;
    
    @EJB
    AtividadeFacade ejbAtividade;
    
    @EJB
    PalestrasFacade ejbPalestras;
    
    public ObjetoAprendizagemController() {
    }
 
    public String prepareCreate()
    {
        atividadeRelacionadaID = 0;
        tipos = ejbTipo.findAll();
        System.out.println(tipos.toString());
        objeto = new ObjetoAprendizagem();
        atividade = new Atividade();
        selectedAtividade = new Atividade();
        plataforma = areas = avasus = redeSoc = outros = false;
        atvidadesRelacionadas = new ArrayList();
        return "/forms/view_create_objeto.xhtml";
    }
    
    public String create()
    {
        System.out.println("chamando?");
        try{
            
            objeto.setDispAres((areas)? 1 : 0);
            objeto.setDispAvasus((avasus)? 1 : 0);
            objeto.setDispOutros((outros)? 1 : 0);
            objeto.setDispRedeSocial((redeSoc)? 1 : 0);
            objeto.setDispTelessaude((plataforma)? 1 : 0);
            objeto.setAtividade(atividade);
            TipoObjetoApr tipoOb = new TipoObjetoApr();
            tipoOb.setId(tipo);
            objeto.setTipo(tipoOb);
            
            if(objeto.getUrlYoutube() != null && !objeto.getUrlYoutube().equals(""))
            {
                objeto.setNumAcesso(ObjetoAprendizagemUtils.findVideoViewCount(objeto.getUrlYoutube()));
            }
            
            ejbObjeto.create(objeto);
            
            FacesContext.getCurrentInstance().addMessage("formObjetoApr:formMsg", new FacesMessage("Objeto de aprendizagem salvo com sucesso!"));
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return prepareCreate();
    }
    
    public void findAtividadesRelacionadas()
    {
        atvidadesRelacionadas = new ArrayList();
        atividade = new Atividade();
        Atividade main = ejbAtividade.findById(atividadeRelacionadaID);
        if(main == null)
            return;
        atvidadesRelacionadas.add(main);
        
        for(Palestras pal : ejbPalestras.findByAtividade(main))
        {
            System.out.println("atv: " + pal.getPessoa().getNome());
            Atividade aux = new Atividade();
            aux.setModerador(pal.getPessoa());
            aux.setTema(pal.getTema());
            aux.setCodDecs(main.getCodDecs());
            aux.setId(main.getId());
            atvidadesRelacionadas.add(aux);
        }
    }
    
    
	
    
    public void onRowSelect(SelectEvent event) {
        this.atividade  = ((Atividade)event.getObject());
        objeto = new ObjetoAprendizagem();
        this.objeto.setTema(atividade.getTema());
        this.objeto.setTemaDecs(atividade.getCodDecs());
        System.out.println(objeto.getTema()+" "+ atividade.getCodDecs());
    }
    
    public String prepareEdit(ObjetoAprendizagem selected)
    {
        objeto = selected;
        areas = (objeto.getDispAres() == 1) ? true : false;
        avasus = (objeto.getDispAvasus()== 1) ? true : false;
        outros = (objeto.getDispOutros()== 1) ? true : false;
        redeSoc = (objeto.getDispRedeSocial()== 1) ? true : false;
        plataforma = (objeto.getDispTelessaude()== 1) ? true : false;
        plataforma = (objeto.getDispTelessaude()== 1) ? true : false;
        atividade = objeto.getAtividade();
        tipo = objeto.getTipo().getId();
        
                
        return "view_edit_objeto.xhtml";
    }
    
    public String edit()
    {
        try{
            
            objeto.setDispAres((areas)? 1 : 0);
            objeto.setDispAvasus((avasus)? 1 : 0);
            objeto.setDispOutros((outros)? 1 : 0);
            objeto.setDispRedeSocial((redeSoc)? 1 : 0);
            objeto.setDispTelessaude((plataforma)? 1 : 0);
            objeto.setAtividade(atividade);
            TipoObjetoApr tipoOb = new TipoObjetoApr();
            tipoOb = ejbTipo.find(objeto.getTipo().getId());
            objeto.setTipo(tipoOb);
            System.out.println("deu certo?");
            if(objeto.getUrlYoutube() != null && !objeto.getUrlYoutube().equals(""))
            {
                objeto.setNumAcesso(ObjetoAprendizagemUtils.findVideoViewCount(objeto.getUrlYoutube()));
            }
            
            ejbObjeto.edit(objeto);
            
            FacesContext.getCurrentInstance().addMessage("formObjetoApr:formMsg", new FacesMessage("Objeto de aprendizagem editado com sucesso!"));
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return "/forms/view_list_objeto_aprendizagem.xhtml";
    }
    
    public void prepareList() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException {
        
        ObjetoAprendizagemUtils.findVideoViewCount("https://www.youtube.com/watch?v=N7IOI8oYMDw");
        
        JSONArray jSONArray = new JSONArray();
        
        List<ObjetoAprendizagem> objetos = ejbObjeto.findAll();
        
        for(ObjetoAprendizagem o : objetos){
            System.out.println(o.getId());
            JSONObject j = new JSONObject();

            j.put("id", o.getId());
            j.put("idAtividade", o.getAtividade().getId());
            j.put("tema", o.getTema());
            j.put("decs", o.getTemaDecs());
            j.put("finalidade", o.getFinalidade());
            j.put("tipo", o.getTipo().getDescricao());
            j.put("data",  DateUtils.dateFormatBR(o.getData()));
            j.put("url", o.getUrlAres());
            if(o.getUrlYoutube() != null && !o.getUrlYoutube().equals(""))
                j.put("url_youtube", o.getUrlYoutube());
            j.put("acessos", o.getNumAcesso());
            
                
            jSONArray.put(j);
        }
        
        String jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        System.out.println("Filling data.json with objects information... ");
        //return "/forms/view_list_objeto_aprendizagem.xhtml";
    }
    
    public String refreshObjetosViewCount() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException
    {
        for(ObjetoAprendizagem obj : ejbObjeto.findAll())
        {
            try
            {
                String url = obj.getUrlYoutube();
                if(url!= null && !url.equals(""))
                    obj.setNumAcesso(ObjetoAprendizagemUtils.findVideoViewCount(url));
                
                ejbObjeto.edit(obj);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        prepareList();
        
        return null;
    }
}
