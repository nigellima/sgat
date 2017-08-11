/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nlima.huufma
 */
@Entity
@Table(name = "objeto_aprendizagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetoAprendizagem.findAll", query = "SELECT o FROM ObjetoAprendizagem o"),
    @NamedQuery(name = "ObjetoAprendizagem.findById", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoAprendizagem.findByData", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.data = :data"),
    @NamedQuery(name = "ObjetoAprendizagem.findByDispTelessaude", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.dispTelessaude = :dispTelessaude"),
    @NamedQuery(name = "ObjetoAprendizagem.findByDispAres", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.dispAres = :dispAres"),
    @NamedQuery(name = "ObjetoAprendizagem.findByDispAvasus", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.dispAvasus = :dispAvasus"),
    @NamedQuery(name = "ObjetoAprendizagem.findByDispRedeSocial", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.dispRedeSocial = :dispRedeSocial"),
    @NamedQuery(name = "ObjetoAprendizagem.findByDispOutros", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.dispOutros = :dispOutros"),
    @NamedQuery(name = "ObjetoAprendizagem.findByTemaDecs", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.temaDecs = :temaDecs"),
    @NamedQuery(name = "ObjetoAprendizagem.findByNumAcesso", query = "SELECT o FROM ObjetoAprendizagem o WHERE o.numAcesso = :numAcesso")})
public class ObjetoAprendizagem implements Serializable {

    @Size(max = 500)
    @Column(name = "url_youtube")
    private String urlYoutube;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "tema")
    private String tema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "finalidade")
    private String finalidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "url_ares")
    private String urlAres;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_telessaude")
    private int dispTelessaude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_ares")
    private int dispAres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_avasus")
    private int dispAvasus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_rede_social")
    private int dispRedeSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_outros")
    private int dispOutros;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "tema_decs")
    private String temaDecs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_acesso")
    private int numAcesso;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @OneToOne(optional = false)
    private TipoObjetoApr tipo;

    public ObjetoAprendizagem() {
    }

    public ObjetoAprendizagem(Integer id) {
        this.id = id;
    }

    public ObjetoAprendizagem(Integer id, Date data, int dispTelessaude, int dispAres, int dispAvasus, int dispRedeSocial, int dispOutros, String temaDecs, int numAcesso) {
        this.id = id;
        this.data = data;
        this.dispTelessaude = dispTelessaude;
        this.dispAres = dispAres;
        this.dispAvasus = dispAvasus;
        this.dispRedeSocial = dispRedeSocial;
        this.dispOutros = dispOutros;
        this.temaDecs = temaDecs;
        this.numAcesso = numAcesso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDispTelessaude() {
        return dispTelessaude;
    }

    public void setDispTelessaude(int dispTelessaude) {
        this.dispTelessaude = dispTelessaude;
    }

    public int getDispAres() {
        return dispAres;
    }

    public void setDispAres(int dispAres) {
        this.dispAres = dispAres;
    }

    public int getDispAvasus() {
        return dispAvasus;
    }

    public void setDispAvasus(int dispAvasus) {
        this.dispAvasus = dispAvasus;
    }

    public int getDispRedeSocial() {
        return dispRedeSocial;
    }

    public void setDispRedeSocial(int dispRedeSocial) {
        this.dispRedeSocial = dispRedeSocial;
    }

    public int getDispOutros() {
        return dispOutros;
    }

    public void setDispOutros(int dispOutros) {
        this.dispOutros = dispOutros;
    }

    public String getTemaDecs() {
        return temaDecs;
    }

    public void setTemaDecs(String temaDecs) {
        this.temaDecs = temaDecs;
    }

    public int getNumAcesso() {
        return numAcesso;
    }

    public void setNumAcesso(int numAcesso) {
        this.numAcesso = numAcesso;
    }

    public TipoObjetoApr getTipo() {
        return tipo;
    }

    public void setTipo(TipoObjetoApr tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetoAprendizagem)) {
            return false;
        }
        ObjetoAprendizagem other = (ObjetoAprendizagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ObjetoAprendizagem[ id=" + id + " ]";
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getUrlAres() {
        return urlAres;
    }

    public void setUrlAres(String urlAres) {
        this.urlAres = urlAres;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public String getUrlYoutube() {
        return urlYoutube;
    }

    public void setUrlYoutube(String urlYoutube) {
        this.urlYoutube = urlYoutube;
    }
    
}
