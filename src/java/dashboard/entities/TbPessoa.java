/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nlima.huufma
 */
@Entity
@Table(name = "tb_pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPessoa.findAll", query = "SELECT t FROM TbPessoa t"),
    @NamedQuery(name = "TbPessoa.findByCodigo", query = "SELECT t FROM TbPessoa t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TbPessoa.findByNome", query = "SELECT t FROM TbPessoa t WHERE t.nome = :nome"),
    @NamedQuery(name = "TbPessoa.findBySexo", query = "SELECT t FROM TbPessoa t WHERE t.sexo = :sexo"),
//    @NamedQuery(name = "TbPessoa.findByNascimento", query = "SELECT t FROM TbPessoa t WHERE t.nascimento = :nascimento"),
    @NamedQuery(name = "TbPessoa.findByTelefone", query = "SELECT t FROM TbPessoa t WHERE t.telefone = :telefone"),
    @NamedQuery(name = "TbPessoa.findByCelular", query = "SELECT t FROM TbPessoa t WHERE t.celular = :celular"),
    @NamedQuery(name = "TbPessoa.findByCelular2", query = "SELECT t FROM TbPessoa t WHERE t.celular2 = :celular2"),
    @NamedQuery(name = "TbPessoa.findByEmail", query = "SELECT t FROM TbPessoa t WHERE t.email = :email"),
    @NamedQuery(name = "TbPessoa.findByEmail2", query = "SELECT t FROM TbPessoa t WHERE t.email2 = :email2"),
    @NamedQuery(name = "TbPessoa.findByCpf", query = "SELECT t FROM TbPessoa t WHERE t.cpf = :cpf"),
    @NamedQuery(name = "TbPessoa.findByCns", query = "SELECT t FROM TbPessoa t WHERE t.cns = :cns"),
    @NamedQuery(name = "TbPessoa.findByOutrodoc", query = "SELECT t FROM TbPessoa t WHERE t.outrodoc = :outrodoc"),
    @NamedQuery(name = "TbPessoa.findByIbge", query = "SELECT t FROM TbPessoa t WHERE t.ibge = :ibge"),
    @NamedQuery(name = "TbPessoa.findByObservacoes", query = "SELECT t FROM TbPessoa t WHERE t.observacoes = :observacoes"),
    @NamedQuery(name = "TbPessoa.findByEscolaridade", query = "SELECT t FROM TbPessoa t WHERE t.escolaridade = :escolaridade")})
public class TbPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo")
    private int sexo;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "nascimento")
//    @Temporal(TemporalType.DATE)
//    private Date nascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "celular2")
    private String celular2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "email2")
    private String email2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cns")
    private String cns;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "outrodoc")
    private String outrodoc;
    @Column(name = "ibge")
    private Integer ibge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "observacoes")
    private String observacoes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "escolaridade")
    private int escolaridade;

    public TbPessoa() {
    }

    public TbPessoa(Integer codigo) {
        this.codigo = codigo;
    }

    public TbPessoa(Integer codigo, String nome, int sexo, Date nascimento, String telefone, String celular, String celular2, String email, String email2, String cpf, String cns, String outrodoc, String observacoes, int escolaridade) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        //this.nascimento = nascimento;
        this.telefone = telefone;
        this.celular = celular;
        this.celular2 = celular2;
        this.email = email;
        this.email2 = email2;
        this.cpf = cpf;
        this.cns = cns;
        this.outrodoc = outrodoc;
        this.observacoes = observacoes;
        this.escolaridade = escolaridade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

//    public Date getNascimento() {
//        return nascimento;
//    }
//
//    public void setNascimento(Date nascimento) {
//        this.nascimento = nascimento;
//    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getOutrodoc() {
        return outrodoc;
    }

    public void setOutrodoc(String outrodoc) {
        this.outrodoc = outrodoc;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(int escolaridade) {
        this.escolaridade = escolaridade;
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
        if (!(object instanceof TbPessoa)) {
            return false;
        }
        TbPessoa other = (TbPessoa) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dashboard.entities.TbPessoa[ codigo=" + codigo + " ]";
    }
    
}
