/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "atividade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atividade.findAll", query = "SELECT a FROM Atividade a"),
    @NamedQuery(name = "Atividade.findById", query = "SELECT a FROM Atividade a WHERE a.id = :id"),
    @NamedQuery(name = "Atividade.findByTema", query = "SELECT a FROM Atividade a WHERE a.tema = :tema"),
    @NamedQuery(name = "Atividade.findByDescricao", query = "SELECT a FROM Atividade a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Atividade.findByDt", query = "SELECT a FROM Atividade a WHERE a.dt = :dt"),
    @NamedQuery(name = "Atividade.findByHrInicio", query = "SELECT a FROM Atividade a WHERE a.hrInicio = :hrInicio"),
    @NamedQuery(name = "Atividade.findByHrTermino", query = "SELECT a FROM Atividade a WHERE a.hrTermino = :hrTermino"),
    @NamedQuery(name = "Atividade.findByStatus", query = "SELECT a FROM Atividade a WHERE a.status = :status"),
    @NamedQuery(name = "Atividade.findByModerador", query = "SELECT a FROM Atividade a WHERE a.moderador = :moderador"),
    @NamedQuery(name = "Atividade.countByTipo", query = "SELECT a FROM Atividade a WHERE a.tipo = :tipo AND a.status = :status"),
    @NamedQuery(name = "Atividade.findBetweenDateAndStatus", query = "SELECT a FROM Atividade a WHERE a.dt BETWEEN :dt1 AND :dt2 AND a.status = :status"),
    @NamedQuery(name = "Atividade.findBetweenDate", query = "SELECT a FROM Atividade a WHERE a.dt BETWEEN :dt1 AND :dt2")})
public class Atividade implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Presenca> presencaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<ObjetoAprendizagem> objetoAprendizagemList;

    @Size(max = 255)
    @Column(name = "cod_decs")
    private String codDecs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private Collection<EdicaoVideo> edicaoVideoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Autentificacao> autentificacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Certificados> certificadosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "tema")
    private String tema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 510)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt")
    @Temporal(TemporalType.DATE)
    private Date dt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hr_inicio")
    @Temporal(TemporalType.TIME)
    private Date hrInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hr_termino")
    @Temporal(TemporalType.TIME)
    private Date hrTermino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<AtaNts> ataNtsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Viagem> viagemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Conexao> conexaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<AtaReuniao> ataReuniaoList;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "local", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local local;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipo tipo;
    @JoinColumn(name = "modalidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modalidade modalidade;
    @JoinColumn(name = "moderador", referencedColumnName = "id")
    @ManyToOne
    private Pessoa moderador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Palestras> palestrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<Solicitacao> solicitacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<AtividadeProblema> atividadeProblemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<ParticipacaoPessoa> participacaoPessoaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<ParticipacaoInstituicao> participacaoInstituicaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<AtividadeTbr> atividadeTbrList;

    public Atividade() {
    }

    public Atividade(Integer id) {
        this.id = id;
    }

    public Atividade(Integer id, String tema, String descricao, Date dt, Date hrInicio, Date hrTermino) {
        this.id = id;
        this.tema = tema;
        this.descricao = descricao;
        this.dt = dt;
        this.hrInicio = hrInicio;
        this.hrTermino = hrTermino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Date getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(Date hrInicio) {
        this.hrInicio = hrInicio;
    }

    public Date getHrTermino() {
        return hrTermino;
    }

    public void setHrTermino(Date hrTermino) {
        this.hrTermino = hrTermino;
    }

    @XmlTransient
    public List<AtaNts> getAtaNtsList() {
        return ataNtsList;
    }

    public void setAtaNtsList(List<AtaNts> ataNtsList) {
        this.ataNtsList = ataNtsList;
    }

    @XmlTransient
    public List<Viagem> getViagemList() {
        return viagemList;
    }

    public void setViagemList(List<Viagem> viagemList) {
        this.viagemList = viagemList;
    }

    @XmlTransient
    public List<Conexao> getConexaoList() {
        return conexaoList;
    }

    public void setConexaoList(List<Conexao> conexaoList) {
        this.conexaoList = conexaoList;
    }

    @XmlTransient
    public List<AtaReuniao> getAtaReuniaoList() {
        return ataReuniaoList;
    }

    public void setAtaReuniaoList(List<AtaReuniao> ataReuniaoList) {
        this.ataReuniaoList = ataReuniaoList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Pessoa getModerador() {
        return moderador;
    }

    public void setModerador(Pessoa moderador) {
        this.moderador = moderador;
    }

    @XmlTransient
    public List<Palestras> getPalestrasList() {
        return palestrasList;
    }

    public void setPalestrasList(List<Palestras> palestrasList) {
        this.palestrasList = palestrasList;
    }

    @XmlTransient
    public List<Solicitacao> getSolicitacaoList() {
        return solicitacaoList;
    }

    public void setSolicitacaoList(List<Solicitacao> solicitacaoList) {
        this.solicitacaoList = solicitacaoList;
    }

    @XmlTransient
    public List<AtividadeProblema> getAtividadeProblemaList() {
        return atividadeProblemaList;
    }

    public void setAtividadeProblemaList(List<AtividadeProblema> atividadeProblemaList) {
        this.atividadeProblemaList = atividadeProblemaList;
    }

    @XmlTransient
    public List<ParticipacaoPessoa> getParticipacaoPessoaList() {
        return participacaoPessoaList;
    }

    public void setParticipacaoPessoaList(List<ParticipacaoPessoa> participacaoPessoaList) {
        this.participacaoPessoaList = participacaoPessoaList;
    }

    @XmlTransient
    public List<ParticipacaoInstituicao> getParticipacaoInstituicaoList() {
        return participacaoInstituicaoList;
    }

    public void setParticipacaoInstituicaoList(List<ParticipacaoInstituicao> participacaoInstituicaoList) {
        this.participacaoInstituicaoList = participacaoInstituicaoList;
    }

    @XmlTransient
    public List<AtividadeTbr> getAtividadeTbrList() {
        return atividadeTbrList;
    }

    public void setAtividadeTbrList(List<AtividadeTbr> atividadeTbrList) {
        this.atividadeTbrList = atividadeTbrList;
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
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+ id;
    }

    @XmlTransient
    public List<Certificados> getCertificadosList() {
        return certificadosList;
    }

    public void setCertificadosList(List<Certificados> certificadosList) {
        this.certificadosList = certificadosList;
    }

    @XmlTransient
    public List<Autentificacao> getAutentificacaoList() {
        return autentificacaoList;
    }

    public void setAutentificacaoList(List<Autentificacao> autentificacaoList) {
        this.autentificacaoList = autentificacaoList;
    }

    @XmlTransient
    public Collection<EdicaoVideo> getEdicaoVideoCollection() {
        return edicaoVideoCollection;
    }

    public void setEdicaoVideoCollection(Collection<EdicaoVideo> edicaoVideoCollection) {
        this.edicaoVideoCollection = edicaoVideoCollection;
    }

    public void setCodDecs(String codDecs) {
        this.codDecs = codDecs;
    }

    public String getCodDecs() {
        return codDecs;
    }

    @XmlTransient
    public List<ObjetoAprendizagem> getObjetoAprendizagemList() {
        return objetoAprendizagemList;
    }

    public void setObjetoAprendizagemList(List<ObjetoAprendizagem> objetoAprendizagemList) {
        this.objetoAprendizagemList = objetoAprendizagemList;
    }

    @XmlTransient
    public List<Presenca> getPresencaList() {
        return presencaList;
    }

    public void setPresencaList(List<Presenca> presencaList) {
        this.presencaList = presencaList;
    }
}
