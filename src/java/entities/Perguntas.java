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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "perguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perguntas.findAll", query = "SELECT p FROM Perguntas p")
    , @NamedQuery(name = "Perguntas.findById", query = "SELECT p FROM Perguntas p WHERE p.id = :id")
    , @NamedQuery(name = "Perguntas.findByDescricao", query = "SELECT p FROM Perguntas p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Perguntas.findByDescricaoId", query = "SELECT p FROM Perguntas p WHERE p.descricaoId = :descricaoId")
    , @NamedQuery(name = "Perguntas.findByTipoInput", query = "SELECT p FROM Perguntas p WHERE p.tipoInput = :tipoInput")})
public class Perguntas implements Serializable {

    @JoinColumn(name = "tipo_avaliacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoAvaliacao tipoAvaliacao;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descricaoId")
    private String descricaoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_input")
    private String tipoInput;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta")
    private List<Avaliacoes> avaliacoesList;

    public Perguntas() {
    }

    public Perguntas(Integer id) {
        this.id = id;
    }

    public Perguntas(Integer id, String descricao, String descricaoId, String tipoInput) {
        this.id = id;
        this.descricao = descricao;
        this.descricaoId = descricaoId;
        this.tipoInput = tipoInput;
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

    public String getDescricaoId() {
        return descricaoId;
    }

    public void setDescricaoId(String descricaoId) {
        this.descricaoId = descricaoId;
    }

    public String getTipoInput() {
        return tipoInput;
    }

    public void setTipoInput(String tipoInput) {
        this.tipoInput = tipoInput;
    }

    @XmlTransient
    public List<Avaliacoes> getAvaliacoesList() {
        return avaliacoesList;
    }

    public void setAvaliacoesList(List<Avaliacoes> avaliacoesList) {
        this.avaliacoesList = avaliacoesList;
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
        if (!(object instanceof Perguntas)) {
            return false;
        }
        Perguntas other = (Perguntas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Perguntas[ id=" + id + " ]";
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }
    
}
