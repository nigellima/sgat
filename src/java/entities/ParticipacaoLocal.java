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
 * @author Lucas
 */
@Entity
@Table(name = "participacao_local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipacaoLocal.findAll", query = "SELECT p FROM ParticipacaoLocal p"),
    @NamedQuery(name = "ParticipacaoLocal.findById", query = "SELECT p FROM ParticipacaoLocal p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipacaoLocal.findByUbs", query = "SELECT p FROM ParticipacaoLocal p WHERE p.ubs = :ubs"),
    @NamedQuery(name = "ParticipacaoLocal.findByMunicipio", query = "SELECT p FROM ParticipacaoLocal p WHERE p.local = :local"),
    @NamedQuery(name = "ParticipacaoPessoa.findByParticipacao", query = "SELECT p FROM ParticipacaoLocal p WHERE p.participacao = :participacao")})
public class ParticipacaoLocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "participacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ParticipacaoPessoa participacao;
    @JoinColumn(name = "ubs", referencedColumnName = "cnes")
    @ManyToOne
    private Ubs ubs;
    @JoinColumn(name = "local", referencedColumnName = "ibge")
    @ManyToOne(optional = false)
    private Municipio local;
    public ParticipacaoLocal() {
    }
    public ParticipacaoLocal(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ParticipacaoPessoa getParticipacao() {
        return participacao;
    }
    public void setParticipacao(ParticipacaoPessoa participacao) {
        this.participacao = participacao;
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
        if (!(object instanceof ParticipacaoLocal)) {
            return false;
        }
        ParticipacaoLocal other = (ParticipacaoLocal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entities.ParticipacaoLocal[ id=" + id + " ]";
    }
    
}