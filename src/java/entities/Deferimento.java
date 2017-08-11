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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "deferimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deferimento.findAll", query = "SELECT d FROM Deferimento d"),
    @NamedQuery(name = "Deferimento.findById", query = "SELECT d FROM Deferimento d WHERE d.id = :id"),
    @NamedQuery(name = "Deferimento.findByDtHr", query = "SELECT d FROM Deferimento d WHERE d.dtHr = :dtHr")})
public class Deferimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_hr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHr;
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "solicitacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Solicitacao solicitacao;

    public Deferimento() {
    }

    public Deferimento(Integer id) {
        this.id = id;
    }

    public Deferimento(Integer id, Date dtHr) {
        this.id = id;
        this.dtHr = dtHr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtHr() {
        return dtHr;
    }

    public void setDtHr(Date dtHr) {
        this.dtHr = dtHr;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
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
        if (!(object instanceof Deferimento)) {
            return false;
        }
        Deferimento other = (Deferimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Deferimento[ id=" + id + " ]";
    }
    
}
