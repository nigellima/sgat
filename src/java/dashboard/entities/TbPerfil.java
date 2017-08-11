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
@Table(name = "tb_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPerfil.findAll", query = "SELECT t FROM TbPerfil t"),
    @NamedQuery(name = "TbPerfil.findByCodigo", query = "SELECT t FROM TbPerfil t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TbPerfil.findByPessoa", query = "SELECT t FROM TbPerfil t WHERE t.pessoa = :pessoa"),
    @NamedQuery(name = "TbPerfil.findByCbo", query = "SELECT t FROM TbPerfil t WHERE t.cbo = :cbo"),
    @NamedQuery(name = "TbPerfil.findByRegistroprof", query = "SELECT t FROM TbPerfil t WHERE t.registroprof = :registroprof"),
    @NamedQuery(name = "TbPerfil.findByTipoProfissional", query = "SELECT t FROM TbPerfil t WHERE t.tipoProfissional = :tipoProfissional"),
    @NamedQuery(name = "TbPerfil.findByAtuacao", query = "SELECT t FROM TbPerfil t WHERE t.atuacao = :atuacao"),
    @NamedQuery(name = "TbPerfil.findByAtivo", query = "SELECT t FROM TbPerfil t WHERE t.ativo = :ativo"),
    @NamedQuery(name = "TbPerfil.findByEquipe", query = "SELECT t FROM TbPerfil t WHERE t.equipe = :equipe")})
public class TbPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pessoa")
    private int pessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "cbo")
    private String cbo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "registroprof")
    private String registroprof;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_profissional")
    private int tipoProfissional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atuacao")
    private int atuacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private int ativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipe")
    private int equipe;

    public TbPerfil() {
    }

    public TbPerfil(Integer codigo) {
        this.codigo = codigo;
    }

    public TbPerfil(Integer codigo, int pessoa, String cbo, String registroprof, int tipoProfissional, int atuacao, int ativo, int equipe) {
        this.codigo = codigo;
        this.pessoa = pessoa;
        this.cbo = cbo;
        this.registroprof = registroprof;
        this.tipoProfissional = tipoProfissional;
        this.atuacao = atuacao;
        this.ativo = ativo;
        this.equipe = equipe;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public String getRegistroprof() {
        return registroprof;
    }

    public void setRegistroprof(String registroprof) {
        this.registroprof = registroprof;
    }

    public int getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(int tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public int getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(int atuacao) {
        this.atuacao = atuacao;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getEquipe() {
        return equipe;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
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
        if (!(object instanceof TbPerfil)) {
            return false;
        }
        TbPerfil other = (TbPerfil) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dashboard.entities.TbPerfil[ codigo=" + codigo + " ]";
    }
    
}
