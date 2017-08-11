/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "viagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viagem.findAll", query = "SELECT v FROM Viagem v"),
    @NamedQuery(name = "Viagem.findById", query = "SELECT v FROM Viagem v WHERE v.id = :id"),
    @NamedQuery(name = "Viagem.findByMotivo", query = "SELECT v FROM Viagem v WHERE v.motivo = :motivo"),
    @NamedQuery(name = "Viagem.findByPercurso", query = "SELECT v FROM Viagem v WHERE v.percurso = :percurso"),
    @NamedQuery(name = "Viagem.findByDtSaida", query = "SELECT v FROM Viagem v WHERE v.dtSaida = :dtSaida"),
    @NamedQuery(name = "Viagem.findByDtChegada", query = "SELECT v FROM Viagem v WHERE v.dtChegada = :dtChegada"),
    @NamedQuery(name = "Viagem.findByDtPreenchimento", query = "SELECT v FROM Viagem v WHERE v.dtPreenchimento = :dtPreenchimento")})
public class Viagem implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viagem")
    private List<ParticipacaoViagem> participacaoViagemList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "percurso")
    private String percurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_saida")
    @Temporal(TemporalType.DATE)
    private Date dtSaida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_chegada")
    @Temporal(TemporalType.DATE)
    private Date dtChegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_preenchimento")
    @Temporal(TemporalType.DATE)
    private Date dtPreenchimento;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public Viagem() {
    }

    public Viagem(Integer id) {
        this.id = id;
    }

    public Viagem(Integer id, String motivo, String percurso, Date dtSaida, Date dtChegada, Date dtPreenchimento) {
        this.id = id;
        this.motivo = motivo;
        this.percurso = percurso;
        this.dtSaida = dtSaida;
        this.dtChegada = dtChegada;
        this.dtPreenchimento = dtPreenchimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    public Date getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(Date dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Date getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(Date dtChegada) {
        this.dtChegada = dtChegada;
    }

    public Date getDtPreenchimento() {
        return dtPreenchimento;
    }

    public void setDtPreenchimento(Date dtPreenchimento) {
        this.dtPreenchimento = dtPreenchimento;
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
        if (!(object instanceof Viagem)) {
            return false;
        }
        Viagem other = (Viagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Viagem[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ParticipacaoViagem> getParticipacaoViagemList() {
        return participacaoViagemList;
    }

    public void setParticipacaoViagemList(List<ParticipacaoViagem> participacaoViagemList) {
        this.participacaoViagemList = participacaoViagemList;
    }
    
}
