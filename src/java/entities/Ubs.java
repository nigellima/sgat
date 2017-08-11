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
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "ubs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubs.findAll", query = "SELECT u FROM Ubs u"),
    @NamedQuery(name = "Ubs.findByCnes", query = "SELECT u FROM Ubs u WHERE u.cnes = :cnes"),
    @NamedQuery(name = "Ubs.findByNome", query = "SELECT u FROM Ubs u WHERE u.nome = :nome")})
public class Ubs implements Serializable, Comparable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubs")
    private List<ProfSaude> profSaudeList;
    @OneToMany(mappedBy = "ubs")
    private List<ParticipacaoLocal> participacaoLocalList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnes")
    private Integer cnes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubs")
    private List<Profissional> profissionalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubs")
    private List<Equipe> equipeList;
    public static Comparator ubsCmp;

    public Ubs() {
        ubsCmp = new UbsComparator();
    }

    public Comparator getUbsCmp() {
        return ubsCmp;
    }

    public Ubs(Integer cnes) {
        this.cnes = cnes;
    }

    public Ubs(Integer cnes, String nome) {
        this.cnes = cnes;
        this.nome = nome;
    }

    public Integer getCnes() {
        return cnes;
    }

    public void setCnes(Integer cnes) {
        this.cnes = cnes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @XmlTransient
    public List<Profissional> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<Profissional> profissionalList) {
        this.profissionalList = profissionalList;
    }

    @XmlTransient
    public List<Equipe> getEquipeList() {
        return equipeList;
    }

    public void setEquipeList(List<Equipe> equipeList) {
        this.equipeList = equipeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnes != null ? cnes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubs)) {
            return false;
        }
        Ubs other = (Ubs) object;
        return Objects.equals(this.cnes, other.cnes);
    }

    @Override
    public String toString() {
        return "entities.Ubs[ cnes=" + cnes + " ]";
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Ubs))
            throw new ClassCastException("Ubs object expected.");
        
        int u1 = this.getCnes();
        int u2 = ((Ubs) o).getCnes();
        
        if(u1 < u2)
            return -1;
        
        if(u1 > u2)
            return 1;
        
        return 0;
    }
    
    public class UbsComparator implements Comparator<Ubs> {
        @Override
        public int compare(Ubs t, Ubs other) {
            return t.compareTo(other);
        }
    }

    @XmlTransient
    public List<ParticipacaoLocal> getParticipacaoLocalList() {
        return participacaoLocalList;
    }

    public void setParticipacaoLocalList(List<ParticipacaoLocal> participacaoLocalList) {
        this.participacaoLocalList = participacaoLocalList;
    }

    @XmlTransient
    public List<ProfSaude> getProfSaudeList() {
        return profSaudeList;
    }

    public void setProfSaudeList(List<ProfSaude> profSaudeList) {
        this.profSaudeList = profSaudeList;
    }
    
    
}
