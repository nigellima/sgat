/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "atividade_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadeProblema.findAll", query = "SELECT a FROM AtividadeProblema a"),
    @NamedQuery(name = "AtividadeProblema.findById", query = "SELECT a FROM AtividadeProblema a WHERE a.id = :id"),
    @NamedQuery(name = "AtividadeProblema.findByDescricao", query = "SELECT a FROM AtividadeProblema a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "AtividadeProblema.findBySolucao", query = "SELECT a FROM AtividadeProblema a WHERE a.solucao = :solucao")})
public class AtividadeProblema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "solucao")
    private String solucao;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;
    @JoinColumn(name = "problema", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Problema problema;

    public AtividadeProblema() {
    }

    public AtividadeProblema(Integer id) {
        this.id = id;
    }

    public AtividadeProblema(Integer id, String descricao, String solucao) {
        this.id = id;
        this.descricao = descricao;
        this.solucao = solucao;
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

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
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
        if (!(object instanceof AtividadeProblema)) {
            return false;
        }
        AtividadeProblema other = (AtividadeProblema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AtividadeProblema[ id=" + id + " ]";
    }
    
}
