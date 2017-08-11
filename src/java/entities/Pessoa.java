/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findBySobrenome", query = "SELECT p FROM Pessoa p WHERE p.sobrenome = :sobrenome"),
    @NamedQuery(name = "Pessoa.findByCompleteName", query = "SELECT p FROM Pessoa p WHERE  trim(p.nome) LIKE :nome AND trim(p.sobrenome) LIKE :sobrenome"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email"),
    @NamedQuery(name = "Pessoa.findByCelular", query = "SELECT p FROM Pessoa p WHERE p.celular = :celular"),
    @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo")})
public class Pessoa implements Serializable, Comparable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Presenca> presencaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "revisor")
    private Collection<EdicaoVideo> edicaoVideoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editor")
    private Collection<EdicaoVideo> edicaoVideoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apoioTecnico")
    private Collection<EdicaoVideo> edicaoVideoCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<ProfSaude> profSaudeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<ProfGeral> profGeralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Autentificacao> autentificacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<ParticipacaoViagem> participacaoViagemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavel")
    private List<Solicitacao> solicitacaoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 15)
    @Column(name = "cpf")
    private String cpf;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 20)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Profissional> profissionalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Funcionario> funcionarioList;
    @OneToMany(mappedBy = "moderador")
    private List<Atividade> atividadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Palestras> palestrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<ParticipacaoPessoa> participacaoPessoaList;
    public static Comparator pessoaCmp;

    public Pessoa() {
        pessoaCmp = new PessoaComparator();
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

    public Pessoa(Integer id, String nome, String sobrenome, String cpf, String email, String celular, String sexo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public List<Profissional> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<Profissional> profissionalList) {
        this.profissionalList = profissionalList;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @XmlTransient
    public List<Atividade> getAtividadeList() {
        return atividadeList;
    }

    public void setAtividadeList(List<Atividade> atividadeList) {
        this.atividadeList = atividadeList;
    }

    @XmlTransient
    public List<Palestras> getPalestrasList() {
        return palestrasList;
    }

    public void setPalestrasList(List<Palestras> palestrasList) {
        this.palestrasList = palestrasList;
    }

    @XmlTransient
    public List<ParticipacaoPessoa> getParticipacaoPessoaList() {
        return participacaoPessoaList;
    }

    public void setParticipacaoPessoaList(List<ParticipacaoPessoa> participacaoPessoaList) {
        this.participacaoPessoaList = participacaoPessoaList;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Pessoa[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Solicitacao> getSolicitacaoList() {
        return solicitacaoList;
    }

    public void setSolicitacaoList(List<Solicitacao> solicitacaoList) {
        this.solicitacaoList = solicitacaoList;
    }

    @XmlTransient
    public List<ParticipacaoViagem> getParticipacaoViagemList() {
        return participacaoViagemList;
    }

    public void setParticipacaoViagemList(List<ParticipacaoViagem> participacaoViagemList) {
        this.participacaoViagemList = participacaoViagemList;
    }

    @XmlTransient
    public List<Autentificacao> getAutentificacaoList() {
        return autentificacaoList;
    }

    public void setAutentificacaoList(List<Autentificacao> autentificacaoList) {
        this.autentificacaoList = autentificacaoList;
    }
    
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if(!(o instanceof Pessoa))
            throw new ClassCastException("Pessoa object expected.");
        
        String u1 = this.getCpf();
        String u2 = ((Pessoa) o).getCpf();
        
        return u1.compareTo(u2);
    }
    
    public class PessoaComparator implements Comparator<Pessoa> {
        @Override
        public int compare(Pessoa t, Pessoa other) {
            return t.compareTo(other);
        }
    }

    @XmlTransient
    public List<ProfSaude> getProfSaudeList() {
        return profSaudeList;
    }

    public void setProfSaudeList(List<ProfSaude> profSaudeList) {
        this.profSaudeList = profSaudeList;
    }

    @XmlTransient
    public List<ProfGeral> getProfGeralList() {
        return profGeralList;
    }

    public void setProfGeralList(List<ProfGeral> profGeralList) {
        this.profGeralList = profGeralList;
    }

    @XmlTransient
    public Collection<EdicaoVideo> getEdicaoVideoCollection() {
        return edicaoVideoCollection;
    }

    public void setEdicaoVideoCollection(Collection<EdicaoVideo> edicaoVideoCollection) {
        this.edicaoVideoCollection = edicaoVideoCollection;
    }

    @XmlTransient
    public Collection<EdicaoVideo> getEdicaoVideoCollection1() {
        return edicaoVideoCollection1;
    }

    public void setEdicaoVideoCollection1(Collection<EdicaoVideo> edicaoVideoCollection1) {
        this.edicaoVideoCollection1 = edicaoVideoCollection1;
    }

    @XmlTransient
    public Collection<EdicaoVideo> getEdicaoVideoCollection2() {
        return edicaoVideoCollection2;
    }

    public void setEdicaoVideoCollection2(Collection<EdicaoVideo> edicaoVideoCollection2) {
        this.edicaoVideoCollection2 = edicaoVideoCollection2;
    }

    @XmlTransient
    public List<Presenca> getPresencaList() {
        return presencaList;
    }

    public void setPresencaList(List<Presenca> presencaList) {
        this.presencaList = presencaList;
    }

}
