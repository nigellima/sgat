/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nlima.huufma
 */
@Entity
@Table(name = "tb_unidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUnidade.findAll", query = "SELECT t FROM TbUnidade t"),
    @NamedQuery(name = "TbUnidade.findByCodigo", query = "SELECT t FROM TbUnidade t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TbUnidade.findByNome", query = "SELECT t FROM TbUnidade t WHERE t.nome = :nome"),
    @NamedQuery(name = "TbUnidade.findByCnes", query = "SELECT t FROM TbUnidade t WHERE t.cnes = :cnes"),
    @NamedQuery(name = "TbUnidade.findByTipo", query = "SELECT t FROM TbUnidade t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TbUnidade.findByEndereco", query = "SELECT t FROM TbUnidade t WHERE t.endereco = :endereco"),
    @NamedQuery(name = "TbUnidade.findByTelefone", query = "SELECT t FROM TbUnidade t WHERE t.telefone = :telefone"),
    @NamedQuery(name = "TbUnidade.findByIbge", query = "SELECT t FROM TbUnidade t WHERE t.ibge = :ibge"),
    @NamedQuery(name = "TbUnidade.findByAtivo", query = "SELECT t FROM TbUnidade t WHERE t.ativo = :ativo")})
public class TbUnidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnes")
    private int cnes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ibge")
    private int ibge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private int ativo;

    public TbUnidade() {
    }

    public TbUnidade(Integer codigo) {
        this.codigo = codigo;
    }

    public TbUnidade(Integer codigo, String nome, int cnes, int tipo, String endereco, String telefone, int ibge, int ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnes = cnes;
        this.tipo = tipo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ibge = ibge;
        this.ativo = ativo;
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

    public int getCnes() {
        return cnes;
    }

    public void setCnes(int cnes) {
        this.cnes = cnes;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIbge() {
        return ibge;
    }

    public void setIbge(int ibge) {
        this.ibge = ibge;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
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
        if (!(object instanceof TbUnidade)) {
            return false;
        }
        TbUnidade other = (TbUnidade) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dashboard.entities.TbUnidade[ codigo=" + codigo + " ]";
    }
    
}
