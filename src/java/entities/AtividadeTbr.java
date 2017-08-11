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
@Table(name = "atividade_tbr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadeTbr.findAll", query = "SELECT a FROM AtividadeTbr a"),
    @NamedQuery(name = "AtividadeTbr.findById", query = "SELECT a FROM AtividadeTbr a WHERE a.id = :id"),
    @NamedQuery(name = "AtividadeTbr.findByFinalidade", query = "SELECT a FROM AtividadeTbr a WHERE a.finalidade = :finalidade"),
    @NamedQuery(name = "AtividadeTbr.findByAtividade", query = "SELECT a FROM AtividadeTbr a WHERE a.atividade = :atividade")})
public class AtividadeTbr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finalidade")
    private String finalidade;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public AtividadeTbr() {
    }

    public AtividadeTbr(Integer id) {
        this.id = id;
    }

    public AtividadeTbr(Integer id, String finalidade) {
        this.id = id;
        this.finalidade = finalidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
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
        if (!(object instanceof AtividadeTbr)) {
            return false;
        }
        AtividadeTbr other = (AtividadeTbr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AtividadeTbr[ id=" + id + " ]";
    }
    
}
