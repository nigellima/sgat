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
 * @author lucas
 */
@Entity
@Table(name = "presenca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presenca.findAll", query = "SELECT p FROM Presenca p"), 
    @NamedQuery(name = "Presenca.findById", query = "SELECT p FROM Presenca p WHERE p.id = :id"), 
    @NamedQuery(name = "Presenca.findByIp", query = "SELECT p FROM Presenca p WHERE p.ip = :ip"),
    @NamedQuery(name = "Presenca.findByAtv", query = "SELECT p FROM Presenca p WHERE p.atividade = :atividade"),
    @NamedQuery(name = "Presenca.findByPessoa", query = "SELECT p FROM Presenca p WHERE p.pessoa = :pessoa"),
    @NamedQuery(name = "Presenca.findByComposedKey", query = "SELECT p FROM Presenca p WHERE p.pessoa = :pessoa AND p.atividade = :atividade"),
    @NamedQuery(name = "Presenca.findByLocal", query = "SELECT p FROM Presenca p WHERE p.local = :local"),
    @NamedQuery(name = "Presenca.findDistinctIps", query = "SELECT DISTINCT (p.ip) FROM Presenca p WHERE p.atividade = :atividade"),
    @NamedQuery(name = "Presenca.findDistinctCities", query = "SELECT DISTINCT (p.local) FROM Presenca p WHERE p.atividade = :atividade")})
public class Presenca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "ip")
    private String ip;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "ubs", referencedColumnName = "cnes")
    @ManyToOne
    private Ubs ubs;
    @JoinColumn(name = "local", referencedColumnName = "ibge")
    @ManyToOne
    private Municipio local;

    public Presenca() {
    }

    public Presenca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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

    public Municipio getLocal() {
        return local;
    }

    public void setLocal(Municipio local) {
        this.local = local;
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
        if (!(object instanceof Presenca)) {
            return false;
        }
        Presenca other = (Presenca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Presenca[ id=" + id + " ]";
    }

    
}
