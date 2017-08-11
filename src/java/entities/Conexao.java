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
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "conexao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conexao.findAll", query = "SELECT c FROM Conexao c"),
    @NamedQuery(name = "Conexao.findById", query = "SELECT c FROM Conexao c WHERE c.id = :id"),
    @NamedQuery(name = "Conexao.findByDescricao", query = "SELECT c FROM Conexao c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Conexao.findBySala", query = "SELECT c FROM Conexao c WHERE c.sala = :sala"),
    @NamedQuery(name = "Conexao.findByAtividade", query = "SELECT c FROM Conexao c WHERE c.atividade = :atividade"),
    @NamedQuery(name = "Conexao.findByResponsavel", query = "SELECT c FROM Conexao c WHERE c.responsavel = :responsavel")})
public class Conexao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "sala")
    private String sala;
    @JoinColumn(name = "responsavel", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Funcionario responsavel;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public Conexao() {
    }

    public Conexao(Integer id) {
        this.id = id;
    }

    public Conexao(Integer id, String descricao, String sala) {
        this.id = id;
        this.descricao = descricao;
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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
        if (!(object instanceof Conexao)) {
            return false;
        }
        Conexao other = (Conexao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Conexao[ id=" + id + " ]";
    }
    
}
