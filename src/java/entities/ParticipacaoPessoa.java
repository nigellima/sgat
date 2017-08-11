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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "participacao_pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipacaoPessoa.findAll", query = "SELECT p FROM ParticipacaoPessoa p"),
    @NamedQuery(name = "ParticipacaoPessoa.findById", query = "SELECT p FROM ParticipacaoPessoa p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipacaoPessoa.findByPessoa", query = "SELECT p FROM ParticipacaoPessoa p WHERE p.pessoa = :pessoa"),
    @NamedQuery(name = "ParticipacaoPessoa.findByComposedKey", query = "SELECT p FROM ParticipacaoPessoa p WHERE p.pessoa = :pessoa AND p.atividade = :atividade"),
    @NamedQuery(name = "ParticipacaoPessoa.findByAtividade", query = "SELECT p FROM ParticipacaoPessoa p WHERE p.atividade = :atividade")})
public class ParticipacaoPessoa implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participacao")
    private List<ParticipacaoIp> participacaoIpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participacao")
    private List<ParticipacaoLocal> participacaoLocalList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public ParticipacaoPessoa() {
    }

    public ParticipacaoPessoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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
        if (!(object instanceof ParticipacaoPessoa)) {
            return false;
        }
        ParticipacaoPessoa other = (ParticipacaoPessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ParticipacaoPessoa[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ParticipacaoLocal> getParticipacaoLocalList() {
        return participacaoLocalList;
    }

    public void setParticipacaoLocalList(List<ParticipacaoLocal> participacaoLocalList) {
        this.participacaoLocalList = participacaoLocalList;
    }

    @XmlTransient
    public List<ParticipacaoIp> getParticipacaoIpList() {
        return participacaoIpList;
    }

    public void setParticipacaoIpList(List<ParticipacaoIp> participacaoIpList) {
        this.participacaoIpList = participacaoIpList;
    }
    
}
