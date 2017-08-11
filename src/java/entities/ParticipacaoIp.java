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
@Table(name = "participacao_ip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipacaoIp.findAll", query = "SELECT p FROM ParticipacaoIp p"),
    @NamedQuery(name = "ParticipacaoIp.findById", query = "SELECT p FROM ParticipacaoIp p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipacaoIp.findByIp", query = "SELECT p FROM ParticipacaoIp p WHERE p.ip = :ip"),
    @NamedQuery(name = "ParticipacaoIp.findByParticipacao", query = "SELECT p FROM ParticipacaoIp p WHERE p.participacao = :participacao")})
public class ParticipacaoIp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ip")
    private String ip;
    @JoinColumn(name = "participacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ParticipacaoPessoa participacao;

    public ParticipacaoIp() {
    }

    public ParticipacaoIp(Integer id) {
        this.id = id;
    }

    public ParticipacaoIp(Integer id, String ip) {
        this.id = id;
        this.ip = ip;
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

    public ParticipacaoPessoa getParticipacao() {
        return participacao;
    }

    public void setParticipacao(ParticipacaoPessoa participacao) {
        this.participacao = participacao;
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
        if (!(object instanceof ParticipacaoIp)) {
            return false;
        }
        ParticipacaoIp other = (ParticipacaoIp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ParticipacaoIp[ id=" + id + " ]";
    }
    
}
