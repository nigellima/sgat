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
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "prof_geral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfGeral.findAll", query = "SELECT p FROM ProfGeral p"),
    @NamedQuery(name = "ProfGeral.findById", query = "SELECT p FROM ProfGeral p WHERE p.id = :id"),
    @NamedQuery(name = "ProfGeral.findByPessoa", query = "SELECT p FROM ProfGeral p WHERE p.pessoa = :pessoa")})
public class ProfGeral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "cbo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cbo cbo;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "municipio", referencedColumnName = "ibge")
    @ManyToOne(optional = false)
    private Municipio municipio;
    public ProfGeral() {
    }
    public ProfGeral(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Cbo getCbo() {
        return cbo;
    }
    public void setCbo(Cbo cbo) {
        this.cbo = cbo;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
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
        if (!(object instanceof ProfGeral)) {
            return false;
        }
        ProfGeral other = (ProfGeral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entities.ProfGeral[ id=" + id + " ]";
    }
    
}