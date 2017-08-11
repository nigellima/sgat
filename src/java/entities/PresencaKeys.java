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
 * @author nigel
 */
@Entity
@Table(name = "presenca_keys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PresencaKeys.findAll", query = "SELECT p FROM PresencaKeys p")
    , @NamedQuery(name = "PresencaKeys.findById", query = "SELECT p FROM PresencaKeys p WHERE p.id = :id")
    , @NamedQuery(name = "PresencaKeys.findByKey", query = "SELECT p FROM PresencaKeys p WHERE p.keyAuth = :keyAuth")
    , @NamedQuery(name = "PresencaKeys.findByPresenca", query = "SELECT p FROM PresencaKeys p WHERE p.presenca = :presenca")})
public class PresencaKeys implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "used")
    private boolean used;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "key_auth")
    private String keyAuth;
    @JoinColumn(name = "presenca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Presenca presenca;

    public PresencaKeys() {
    }

    public PresencaKeys(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Presenca getPresenca() {
        return presenca;
    }

    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
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
        if (!(object instanceof PresencaKeys)) {
            return false;
        }
        PresencaKeys other = (PresencaKeys) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PresencaKeys[ id=" + id + " ]";
    }

    public String getKeyAuth() {
        return keyAuth;
    }

    public void setKeyAuth(String keyAuth) {
        this.keyAuth = keyAuth;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
}
