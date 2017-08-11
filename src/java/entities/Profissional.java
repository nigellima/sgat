/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.io.Serializable;
import java.util.Comparator;
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
@Table(name = "profissional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profissional.findAll", query = "SELECT p FROM Profissional p"),
    @NamedQuery(name = "Profissional.findById", query = "SELECT p FROM Profissional p WHERE p.id = :id"),
    @NamedQuery(name = "Profissional.findByCns", query = "SELECT p FROM Profissional p WHERE p.cns = :cns"),
    @NamedQuery(name = "Profissional.findByEquipe", query = "SELECT p FROM Profissional p WHERE p.equipe = :equipe"),
    @NamedQuery(name = "Profissional.findByResidente", query = "SELECT p FROM Profissional p WHERE p.residente = :residente"),
    @NamedQuery(name = "Profissional.findByPerceptor", query = "SELECT p FROM Profissional p WHERE p.perceptor = :perceptor"),
    @NamedQuery(name = "Profissional.findByPessoa", query = "SELECT p FROM Profissional p WHERE p.pessoa = :pessoa")})
public class Profissional implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cns")
    private String cns;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "equipe")
    private String equipe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "residente")
    private boolean residente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "perceptor")
    private boolean perceptor;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "ubs", referencedColumnName = "cnes")
    @ManyToOne(optional = false)
    private Ubs ubs;
    @JoinColumn(name = "municipio", referencedColumnName = "ibge")
    @ManyToOne(optional = false)
    private Municipio municipio;
    @JoinColumn(name = "cbo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cbo cbo;
    public static Comparator profCmp;
    public Profissional() {
        profCmp = new ProfissionalComparator();
    }
    public Profissional(Integer id) {
        this.id = id;
    }
    public Profissional(Integer id, String cns, String equipe, boolean residente, boolean perceptor) {
        this.id = id;
        this.cns = cns;
        this.equipe = equipe;
        this.residente = residente;
        this.perceptor = perceptor;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCns() {
        return cns;
    }
    public void setCns(String cns) {
        this.cns = cns;
    }
    public String getEquipe() {
        return equipe;
    }
    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
    public boolean getResidente() {
        return residente;
    }
    public void setResidente(boolean residente) {
        this.residente = residente;
    }
    public boolean getPerceptor() {
        return perceptor;
    }
    public void setPerceptor(boolean perceptor) {
        this.perceptor = perceptor;
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
    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public Cbo getCbo() {
        return cbo;
    }
    public void setCbo(Cbo cbo) {
        this.cbo = cbo;
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
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
    @Override
    public String toString() {
        return "entities.Profissional[ id=" + id + " ]";
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Profissional))
            throw new ClassCastException("Profissional object expected.");
        
        String u1 = this.getCns();
        String u2 = ((Profissional) o).getCns();
        
        return u1.compareTo(u2);
    }
    
    public class ProfissionalComparator implements Comparator<Profissional> {
        @Override
        public int compare(Profissional t, Profissional other) {
            return t.compareTo(other);
        }
    }
    
}