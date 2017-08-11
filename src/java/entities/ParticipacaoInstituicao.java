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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "participacao_instituicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipacaoInstituicao.findAll", query = "SELECT p FROM ParticipacaoInstituicao p"),
    @NamedQuery(name = "ParticipacaoInstituicao.findById", query = "SELECT p FROM ParticipacaoInstituicao p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipacaoInstituicao.findByAtividade", query = "SELECT p FROM ParticipacaoInstituicao p WHERE p.atividade = :atividade")})
public class ParticipacaoInstituicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "instituicao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instituicao instituicao;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public ParticipacaoInstituicao() {
    }

    public ParticipacaoInstituicao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
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
        if (!(object instanceof ParticipacaoInstituicao)) {
            return false;
        }
        ParticipacaoInstituicao other = (ParticipacaoInstituicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ParticipacaoInstituicao[ id=" + id + " ]";
    }
    
}
