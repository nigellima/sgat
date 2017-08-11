/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "cbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cbo.findAll", query = "SELECT c FROM Cbo c"),
    @NamedQuery(name = "Cbo.findByCodigo", query = "SELECT c FROM Cbo c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Cbo.findByNome", query = "SELECT c FROM Cbo c WHERE c.nome = :nome")})
public class Cbo implements Serializable, Comparable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cbo")
    private List<ProfGeral> profGeralList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cbo")
    private List<Profissional> profissionalList;
    public static Comparator cboCmp;

    public Cbo() {
        cboCmp = new CboComparator();
    }

    public Cbo(String codigo) {
        this.codigo = codigo;
    }

    public Cbo(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Profissional> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<Profissional> profissionalList) {
        this.profissionalList = profissionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cbo)) {
            return false;
        }
        Cbo other = (Cbo) object;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "entities.Cbo[ codigo=" + codigo + " ]";
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Cbo))
            throw new ClassCastException("Cbo object expected.");
        
        String u1 = this.getCodigo();
        String u2 = ((Cbo) o).getCodigo();
        
        return u1.compareTo(u2);
    }
    
    public class CboComparator implements Comparator<Cbo> {
        @Override
        public int compare(Cbo t, Cbo other) {
            return t.compareTo(other);
        }
    }

    @XmlTransient
    public List<ProfGeral> getProfGeralList() {
        return profGeralList;
    }

    public void setProfGeralList(List<ProfGeral> profGeralList) {
        this.profGeralList = profGeralList;
    }
    
}
