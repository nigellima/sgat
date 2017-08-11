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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nigel
 */
@Entity
@Table(name = "tipo_avaliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAvaliacao.findAll", query = "SELECT t FROM TipoAvaliacao t")
    , @NamedQuery(name = "TipoAvaliacao.findById", query = "SELECT t FROM TipoAvaliacao t WHERE t.id = :id")
    , @NamedQuery(name = "TipoAvaliacao.findByDescricao", query = "SELECT t FROM TipoAvaliacao t WHERE t.descricao = :descricao")})
public class TipoAvaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAvaliacao")
    private List<Perguntas> perguntasList;

    public TipoAvaliacao() {
    }

    public TipoAvaliacao(Integer id) {
        this.id = id;
    }

    public TipoAvaliacao(Integer id, String descricao) {
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

    @XmlTransient
    public List<Perguntas> getPerguntasList() {
        return perguntasList;
    }

    public void setPerguntasList(List<Perguntas> perguntasList) {
        this.perguntasList = perguntasList;
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
        if (!(object instanceof TipoAvaliacao)) {
            return false;
        }
        TipoAvaliacao other = (TipoAvaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoAvaliacao[ id=" + id + " ]";
    }
    
}
