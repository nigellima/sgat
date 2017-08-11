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
@Table(name = "edicao_video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdicaoVideo.findAll", query = "SELECT e FROM EdicaoVideo e"),
    @NamedQuery(name = "EdicaoVideo.findById", query = "SELECT e FROM EdicaoVideo e WHERE e.id = :id"),
    @NamedQuery(name = "EdicaoVideo.findByAtividade", query = "SELECT e FROM EdicaoVideo e WHERE e.atividade = :atividade"),
    @NamedQuery(name = "EdicaoVideo.findByDataYoutube", query = "SELECT e FROM EdicaoVideo e WHERE e.dataYoutube = :dataYoutube"),
    @NamedQuery(name = "EdicaoVideo.findByDataAres", query = "SELECT e FROM EdicaoVideo e WHERE e.dataAres = :dataAres"),
    @NamedQuery(name = "EdicaoVideo.findByUrlYoutube", query = "SELECT e FROM EdicaoVideo e WHERE e.urlYoutube = :urlYoutube"),
    @NamedQuery(name = "EdicaoVideo.findByUrlAres", query = "SELECT e FROM EdicaoVideo e WHERE e.urlAres = :urlAres")})
public class EdicaoVideo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data_youtube")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataYoutube;
    @Column(name = "data_ares")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAres;
    @Size(min = 0, max = 255)
    @Column(name = "url_youtube")
    private String urlYoutube;
    @Size(min = 0, max = 255)
    @Column(name = "url_ares")
    private String urlAres;
    @JoinColumn(name = "revisor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa revisor;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;
    @JoinColumn(name = "editor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa editor;
    @JoinColumn(name = "apoio_tecnico", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa apoioTecnico;

    public EdicaoVideo() {
    }

    public EdicaoVideo(Integer id) {
        this.id = id;
    }

    public EdicaoVideo(Integer id, Date dataYoutube, Date dataAres, String urlYoutube, String urlAres) {
        this.id = id;
        this.dataYoutube = dataYoutube;
        this.dataAres = dataAres;
        this.urlYoutube = urlYoutube;
        this.urlAres = urlAres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataYoutube() {
        return dataYoutube;
    }

    public void setDataYoutube(Date dataYoutube) {
        this.dataYoutube = dataYoutube;
    }

    public Date getDataAres() {
        return dataAres;
    }

    public void setDataAres(Date dataAres) {
        this.dataAres = dataAres;
    }

    public String getUrlYoutube() {
        return urlYoutube;
    }

    public void setUrlYoutube(String urlYoutube) {
        this.urlYoutube = urlYoutube;
    }

    public String getUrlAres() {
        return urlAres;
    }

    public void setUrlAres(String urlAres) {
        this.urlAres = urlAres;
    }

    public Pessoa getRevisor() {
        return revisor;
    }

    public void setRevisor(Pessoa revisor) {
        this.revisor = revisor;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Pessoa getEditor() {
        return editor;
    }

    public void setEditor(Pessoa editor) {
        this.editor = editor;
    }

    public Pessoa getApoioTecnico() {
        return apoioTecnico;
    }

    public void setApoioTecnico(Pessoa apoioTecnico) {
        this.apoioTecnico = apoioTecnico;
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
        if (!(object instanceof EdicaoVideo)) {
            return false;
        }
        EdicaoVideo other = (EdicaoVideo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EdicaoVideo[ id=" + id + " ]";
    }
    
}
