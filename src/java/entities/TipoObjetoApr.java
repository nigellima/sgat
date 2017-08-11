/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nlima.huufma
 */
@Entity
@Table(name = "tipo_objeto_apr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoObjetoApr.findAll", query = "SELECT t FROM TipoObjetoApr t"),
    @NamedQuery(name = "TipoObjetoApr.findById", query = "SELECT t FROM TipoObjetoApr t WHERE t.id = :id"),
    @NamedQuery(name = "TipoObjetoApr.findByDescricao", query = "SELECT t FROM TipoObjetoApr t WHERE t.descricao = :descricao")})
public class TipoObjetoApr implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
    private List<ObjetoAprendizagem> objetoAprendizagemList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "descricao")
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tipo")
    private ObjetoAprendizagem objetoAprendizagem;

    public TipoObjetoApr() {
    }

    public TipoObjetoApr(Integer id) {
        this.id = id;
    }

    public TipoObjetoApr(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public ObjetoAprendizagem getObjetoAprendizagem() {
        return objetoAprendizagem;
    }

    public void setObjetoAprendizagem(ObjetoAprendizagem objetoAprendizagem) {
        this.objetoAprendizagem = objetoAprendizagem;
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
        if (!(object instanceof TipoObjetoApr)) {
            return false;
        }
        TipoObjetoApr other = (TipoObjetoApr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoObjetoApr[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ObjetoAprendizagem> getObjetoAprendizagemList() {
        return objetoAprendizagemList;
    }

    public void setObjetoAprendizagemList(List<ObjetoAprendizagem> objetoAprendizagemList) {
        this.objetoAprendizagemList = objetoAprendizagemList;
    }
    
}
