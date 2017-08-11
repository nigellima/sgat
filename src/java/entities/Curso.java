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
 * @author nigel
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id")
    , @NamedQuery(name = "Curso.findByAvaCursoId", query = "SELECT c FROM Curso c WHERE c.avaCursoId = :avaCursoId")
    , @NamedQuery(name = "Curso.findByDataInicio", query = "SELECT c FROM Curso c WHERE c.dataInicio = :dataInicio")
    , @NamedQuery(name = "Curso.findByDataTermino", query = "SELECT c FROM Curso c WHERE c.dataTermino = :dataTermino")
    , @NamedQuery(name = "Curso.findByVagasOfertadas", query = "SELECT c FROM Curso c WHERE c.vagasOfertadas = :vagasOfertadas")
    , @NamedQuery(name = "Curso.findByTema", query = "SELECT c FROM Curso c WHERE c.tema = :tema")
    , @NamedQuery(name = "Curso.findByCodDecs", query = "SELECT c FROM Curso c WHERE c.codDecs = :codDecs")
    , @NamedQuery(name = "Curso.findByCargaHoraria", query = "SELECT c FROM Curso c WHERE c.cargaHoraria = :cargaHoraria")
    , @NamedQuery(name = "Curso.checkExistance", query = "SELECT c FROM Curso c WHERE c.avaCursoId = :avaCursoId AND c.dataInicio = :dataInicio")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ava_curso_id")
    private int avaCursoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_termino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vagas_ofertadas")
    private int vagasOfertadas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "tema")
    private String tema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cod_decs")
    private String codDecs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_horaria")
    private int cargaHoraria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Aluno> alunoList;

    public Curso() {
    }

    public Curso(Integer id) {
        this.id = id;
    }

    public Curso(Integer id, int avaCursoId, Date dataInicio, Date dataTermino, int vagasOfertadas, String tema, String codDecs, int cargaHoraria) {
        this.id = id;
        this.avaCursoId = avaCursoId;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.vagasOfertadas = vagasOfertadas;
        this.tema = tema;
        this.codDecs = codDecs;
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAvaCursoId() {
        return avaCursoId;
    }

    public void setAvaCursoId(int avaCursoId) {
        this.avaCursoId = avaCursoId;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getVagasOfertadas() {
        return vagasOfertadas;
    }

    public void setVagasOfertadas(int vagasOfertadas) {
        this.vagasOfertadas = vagasOfertadas;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getCodDecs() {
        return codDecs;
    }

    public void setCodDecs(String codDecs) {
        this.codDecs = codDecs;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @XmlTransient
    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Curso[ id=" + id + " ]";
    }
    
}
