/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucasmaia.huufma
 */
@Entity
@Table(name = "ata_nts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtaNts.findAll", query = "SELECT a FROM AtaNts a ORDER BY a.atividade.dt"),
    @NamedQuery(name = "AtaNts.findById", query = "SELECT a FROM AtaNts a WHERE a.id = :id"),
    @NamedQuery(name = "AtaNts.findByQtdPresentes", query = "SELECT a FROM AtaNts a WHERE a.qtdPresentes = :qtdPresentes"),
    @NamedQuery(name = "AtaNts.findByMaisInfo", query = "SELECT a FROM AtaNts a WHERE a.maisInfo = :maisInfo"),
    @NamedQuery(name = "AtaNts.findByAtividade", query = "SELECT a FROM AtaNts a WHERE a.atividade = :atividade")})
public class AtaNts implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ata")
    private List<AtaDigitalizada> ataDigitalizadaList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_pontos")
    private int qtdPontos;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_presentes")
    private int qtdPresentes;
    @Basic(optional = false)
    @Size(min = 0, max = 255)
    @Column(name = "mais_info")
    private String maisInfo;
    @JoinColumn(name = "atividade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atividade atividade;

    public AtaNts() {
    }

    public AtaNts(Integer id) {
        this.id = id;
    }

    public AtaNts(Integer id, int qtdPresentes, String maisInfo) {
        this.id = id;
        this.qtdPresentes = qtdPresentes;
        this.maisInfo = maisInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQtdPresentes() {
        return qtdPresentes;
    }

    public void setQtdPresentes(int qtdPresentes) {
        this.qtdPresentes = qtdPresentes;
    }

    public String getMaisInfo() {
        return maisInfo;
    }

    public void setMaisInfo(String maisInfo) {
        this.maisInfo = maisInfo;
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
        if (!(object instanceof AtaNts)) {
            return false;
        }
        AtaNts other = (AtaNts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AtaNts[ id=" + id + " ]";
    }

    public int getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(int qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    @XmlTransient
    public List<AtaDigitalizada> getAtaDigitalizadaList() {
        return ataDigitalizadaList;
    }

    public void setAtaDigitalizadaList(List<AtaDigitalizada> ataDigitalizadaList) {
        this.ataDigitalizadaList = ataDigitalizadaList;
    }

}
