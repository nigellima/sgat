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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIbge", query = "SELECT m FROM Municipio m WHERE m.ibge = :ibge"),
    @NamedQuery(name = "Municipio.findByNome", query = "SELECT m FROM Municipio m WHERE m.nome = :nome"),
    @NamedQuery(name = "Municipio.findByUf", query = "SELECT m FROM Municipio m WHERE m.uf.id = :uf")})
public class Municipio implements Serializable, Comparable {

    @OneToMany(mappedBy = "local")
    private List<Presenca> presencaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    private List<ProfGeral> profGeralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "local")
    private List<ParticipacaoLocal> participacaoLocalList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ibge")
    private Integer ibge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    private List<Profissional> profissionalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    private List<Endereco> enderecoList;
    @JoinColumn(name = "uf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estado uf;
    public static Comparator munCmp;

    public Municipio() {
        munCmp = new MunicipioComparator();
    }

    public Municipio(Integer ibge) {
        this.ibge = ibge;
    }

    public Municipio(Integer ibge, String nome) {
        this.ibge = ibge;
        this.nome = nome;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
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

    @XmlTransient
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public Estado getUf() {
        return uf;
    }

    public void setUf(Estado uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ibge != null ? ibge.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        return Objects.equals(this.ibge, other.ibge);
    }

    @Override
    public String toString() {
        return "entities.Municipio[ ibge=" + ibge + " ]";
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Municipio))
            throw new ClassCastException("Municipio object expected.");
        
        int u1 = this.getIbge();
        int u2 = ((Municipio) o).getIbge();
        
        if(u1 < u2)
            return -1;
        
        if(u1 > u2)
            return 1;
        
        return 0;
    }
    
    public class MunicipioComparator implements Comparator<Municipio> {
        @Override
        public int compare(Municipio t, Municipio other) {
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
    public List<ProfGeral> getProfGeralList() {
        return profGeralList;
    }

    public void setProfGeralList(List<ProfGeral> profGeralList) {
        this.profGeralList = profGeralList;
    }

    @XmlTransient
    public List<Presenca> getPresencaList() {
        return presencaList;
    }

    public void setPresencaList(List<Presenca> presencaList) {
        this.presencaList = presencaList;
    }
    
}
