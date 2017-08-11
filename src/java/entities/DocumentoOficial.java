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
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "documento_oficial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoOficial.findAll", query = "SELECT d FROM DocumentoOficial d"),
    @NamedQuery(name = "DocumentoOficial.findById", query = "SELECT d FROM DocumentoOficial d WHERE d.id = :id"),
    @NamedQuery(name = "DocumentoOficial.findByDtDoc", query = "SELECT d FROM DocumentoOficial d WHERE d.dtDoc = :dtDoc"),
    @NamedQuery(name = "DocumentoOficial.findByNum", query = "SELECT d FROM DocumentoOficial d WHERE d.num = :num"),
    @NamedQuery(name = "DocumentoOficial.findByTipo", query = "SELECT d FROM DocumentoOficial d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "DocumentoOficial.findByCorpo", query = "SELECT d FROM DocumentoOficial d WHERE d.corpo = :corpo")})
public class DocumentoOficial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_doc")
    @Temporal(TemporalType.DATE)
    private Date dtDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private int num;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20000)
    @Column(name = "corpo")
    private String corpo;
    @JoinColumn(name = "setor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Destinatario setor;

    public DocumentoOficial() {
    }

    public DocumentoOficial(Integer id) {
        this.id = id;
    }

    public DocumentoOficial(Integer id, Date dtDoc, int num, int tipo, String corpo) {
        this.id = id;
        this.dtDoc = dtDoc;
        this.num = num;
        this.tipo = tipo;
        this.corpo = corpo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtDoc() {
        return dtDoc;
    }

    public void setDtDoc(Date dtDoc) {
        this.dtDoc = dtDoc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Destinatario getSetor() {
        return setor;
    }

    public void setSetor(Destinatario setor) {
        this.setor = setor;
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
        if (!(object instanceof DocumentoOficial)) {
            return false;
        }
        DocumentoOficial other = (DocumentoOficial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentoOficial[ id=" + id + " ]";
    }
    
}
