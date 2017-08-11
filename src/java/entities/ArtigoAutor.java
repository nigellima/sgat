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
 * @author nigel
 */
@Entity
@Table(name = "artigo_autor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtigoAutor.findAll", query = "SELECT a FROM ArtigoAutor a")
    , @NamedQuery(name = "ArtigoAutor.findById", query = "SELECT a FROM ArtigoAutor a WHERE a.id = :id")
    , @NamedQuery(name = "ArtigoAutor.findByArtigo", query = "SELECT a FROM ArtigoAutor a WHERE a.artigo = :artigo")
    , @NamedQuery(name = "ArtigoAutor.findByPessoa", query = "SELECT a FROM ArtigoAutor a WHERE a.pessoa = :pessoa")})
public class ArtigoAutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "artigo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Artigo artigo;

    public ArtigoAutor() {
    }

    public ArtigoAutor(Integer id) {
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

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
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
        if (!(object instanceof ArtigoAutor)) {
            return false;
        }
        ArtigoAutor other = (ArtigoAutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ArtigoAutor[ id=" + id + " ]";
    }
    
}
