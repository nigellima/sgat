/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "tipo_nt4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNt4.findAll", query = "SELECT t FROM TipoNt4 t"),
    @NamedQuery(name = "TipoNt4.findById", query = "SELECT t FROM TipoNt4 t WHERE t.id = :id"),
    @NamedQuery(name = "TipoNt4.findByDescricao", query = "SELECT t FROM TipoNt4 t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TipoNt4.findByNorma", query = "SELECT t FROM TipoNt4 t WHERE t.norma = :norma")})
public class TipoNt4 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2500)
    @Column(name = "norma")
    private String norma;
    @OneToMany(mappedBy = "tipoNt4")
    private List<Tipo> tipoList;

    public TipoNt4() {
    }

    public TipoNt4(Integer id) {
        this.id = id;
    }

    public TipoNt4(Integer id, String descricao, String norma) {
        this.id = id;
        this.descricao = descricao;
        this.norma = norma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    @XmlTransient
    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
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
        if (!(object instanceof TipoNt4)) {
            return false;
        }
        TipoNt4 other = (TipoNt4) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoNt4[ id=" + id + " ]";
    }
    
}
