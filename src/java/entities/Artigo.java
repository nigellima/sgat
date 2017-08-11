/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nigel
 */
@Entity
@Table(name = "artigo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigo.findAll", query = "SELECT a FROM Artigo a")
    , @NamedQuery(name = "Artigo.findById", query = "SELECT a FROM Artigo a WHERE a.id = :id")
    , @NamedQuery(name = "Artigo.findByPdfPath", query = "SELECT a FROM Artigo a WHERE a.pdfPath = :pdfPath")
    , @NamedQuery(name = "Artigo.findByLocalPub", query = "SELECT a FROM Artigo a WHERE a.localPub = :localPub")
    , @NamedQuery(name = "Artigo.findByLink", query = "SELECT a FROM Artigo a WHERE a.link = :link")
    , @NamedQuery(name = "Artigo.findByData", query = "SELECT a FROM Artigo a WHERE a.data = :data")})
public class Artigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    
    @Size(min = 1, max = 1024)
    @Column(name = "pdf_path")
    private String pdfPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "local_pub")
    private String localPub;
    @Basic(optional = false)
    
    
    @Column(name = "link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artigo")
    private List<ArtigoAutor> artigoAutorList;
    @JoinColumn(name = "resumo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Abstract resumo;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatusArtigo status;
    @JoinColumn(name = "abstract", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Abstract abstract1;

    public Artigo() {
    }

    public Artigo(Integer id) {
        this.id = id;
    }

    public Artigo(Integer id, String pdfPath, String localPub, String link, Date data) {
        this.id = id;
        this.pdfPath = pdfPath;
        this.localPub = localPub;
        this.link = link;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getLocalPub() {
        return localPub;
    }

    public void setLocalPub(String localPub) {
        this.localPub = localPub;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public List<ArtigoAutor> getArtigoAutorList() {
        return artigoAutorList;
    }

    public void setArtigoAutorList(List<ArtigoAutor> artigoAutorList) {
        this.artigoAutorList = artigoAutorList;
    }

    public Abstract getResumo() {
        return resumo;
    }

    public void setResumo(Abstract resumo) {
        this.resumo = resumo;
    }

    public StatusArtigo getStatus() {
        return status;
    }

    public void setStatus(StatusArtigo status) {
        this.status = status;
    }

    public Abstract getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(Abstract abstract1) {
        this.abstract1 = abstract1;
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
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Artigo[ id=" + id + " ]";
    }
    
}
