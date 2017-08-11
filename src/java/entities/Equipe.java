/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
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
@Table(name = "equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e"),
    @NamedQuery(name = "Equipe.findById", query = "SELECT e FROM Equipe e WHERE e.id = :id"),
    @NamedQuery(name = "Equipe.findByCodigoIne", query = "SELECT e FROM Equipe e WHERE e.codigoIne = :codigoIne"),
    @NamedQuery(name = "Equipe.findByNome", query = "SELECT e FROM Equipe e WHERE e.nome = :nome")})
public class Equipe implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo_ine")
    private String codigoIne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "ubs", referencedColumnName = "cnes")
    @ManyToOne(optional = false)
    private Ubs ubs;
    public static Comparator equipeCmp;

    public Equipe() {
        equipeCmp = new EquipeComparator();
    }

    public Equipe(Integer id) {
        this.id = id;
    }

    public Equipe(Integer id, String codigoIne, String nome) {
        this.id = id;
        this.codigoIne = codigoIne;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoIne() {
        return codigoIne;
    }

    public void setCodigoIne(String codigoIne) {
        this.codigoIne = codigoIne;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        return Objects.equals(this.codigoIne, other.codigoIne);
    }

    @Override
    public String toString() {
        return "entities.Equipe[ id=" + id + " ]";
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Equipe))
            throw new ClassCastException("Equipe object expected.");
        
        String u1 = this.getCodigoIne();
        String u2 = ((Equipe) o).getCodigoIne();
        
        return u1.compareTo(u2);
    }
    
    public class EquipeComparator implements Comparator<Equipe> {
        @Override
        public int compare(Equipe t, Equipe other) {
            return t.compareTo(other);
        }
    }
    
}
