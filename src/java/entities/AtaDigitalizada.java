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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HUUFMA
 */
@Entity
@Table(name = "ata_digitalizada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtaDigitalizada.findAll", query = "SELECT a FROM AtaDigitalizada a"),
    @NamedQuery(name = "AtaDigitalizada.findById", query = "SELECT a FROM AtaDigitalizada a WHERE a.id = :id"),
    @NamedQuery(name = "AtaDigitalizada.findByNomeArquivo", query = "SELECT a FROM AtaDigitalizada a WHERE a.nomeArquivo = :nomeArquivo"),
    @NamedQuery(name = "AtaDigitalizada.findByAta", query = "SELECT a FROM AtaDigitalizada a WHERE a.ata = :ata")})
public class AtaDigitalizada implements Serializable {

    @Size(max = 100)
    @Column(name = "nomeArquivoSlide")
    private String nomeArquivoSlide;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nomeArquivo")
    private String nomeArquivo;
    
    @JoinColumn(name = "ata", referencedColumnName = "id")
    @OneToOne(optional = false)
    private AtaNts ata;

    public AtaDigitalizada() {
    }

    public AtaDigitalizada(Integer id) {
        this.id = id;
    }

    public AtaDigitalizada(Integer id, String nomeArquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public AtaNts getAta() {
        return ata;
    }

    public void setAta(AtaNts ata) {
        this.ata = ata;
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
        if (!(object instanceof AtaDigitalizada)) {
            return false;
        }
        AtaDigitalizada other = (AtaDigitalizada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AtaDigitalizada[ id=" + id + " ]";
    }

    

    public String getNomeArquivoSlide() {
        return nomeArquivoSlide;
    }

    public void setNomeArquivoSlide(String nomeArquivoSlide) {
        this.nomeArquivoSlide = nomeArquivoSlide;
    }
    
}
