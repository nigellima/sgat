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
@Table(name = "prof_saude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfSaude.findAll", query = "SELECT p FROM ProfSaude p"),
    @NamedQuery(name = "ProfSaude.findById", query = "SELECT p FROM ProfSaude p WHERE p.id = :id"),
    @NamedQuery(name = "ProfSaude.findByCns", query = "SELECT p FROM ProfSaude p WHERE p.cns = :cns"),
    @NamedQuery(name = "ProfSaude.findByEquipe", query = "SELECT p FROM ProfSaude p WHERE p.equipe = :equipe"),
    @NamedQuery(name = "ProfSaude.findByPessoa", query = "SELECT p FROM ProfSaude p WHERE p.pessoa = :pessoa")})
public class ProfSaude implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cns")
    private String cns;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "equipe")
    private String equipe;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "ubs", referencedColumnName = "cnes")
    @ManyToOne(optional = false)
    private Ubs ubs;

    public ProfSaude() {
    }

    public ProfSaude(Integer id) {
        this.id = id;
    }

    public ProfSaude(Integer id, String cns, String equipe) {
        this.id = id;
        this.cns = cns;
        this.equipe = equipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Ubs getUbs() {
        return ubs;
    }

    public void setUbs(Ubs ubs) {
        this.ubs = ubs;
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
        if (!(object instanceof ProfSaude)) {
            return false;
        }
        ProfSaude other = (ProfSaude) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProfSaude[ id=" + id + " ]";
    }
    
}
