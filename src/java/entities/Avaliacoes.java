/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nigel
 */
@Entity
@Table(name = "avaliacoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacoes.findAll", query = "SELECT a FROM Avaliacoes a")
    , @NamedQuery(name = "Avaliacoes.findById", query = "SELECT a FROM Avaliacoes a WHERE a.id = :id")
    , @NamedQuery(name = "Avaliacoes.findByResposta", query = "SELECT a FROM Avaliacoes a WHERE a.resposta = :resposta")
    , @NamedQuery(name = "Avaliacoes.findByData", query = "SELECT a FROM Avaliacoes a WHERE a.data = :data")
    , @NamedQuery(name = "Avaliacoes.findByIp", query = "SELECT a FROM Avaliacoes a WHERE a.ip = :ip")
    , @NamedQuery(name = "Avaliacoes.findByComposedKey", query = "SELECT a FROM Avaliacoes a WHERE a.presenca = :presenca AND a.pergunta = :pergunta")
    , @NamedQuery(name = "Avaliacoes.findByPresenca", query = "SELECT a FROM Avaliacoes a WHERE a.presenca = :presenca")})
public class Avaliacoes implements Serializable {

    @JoinColumn(name = "presenca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Presenca presenca;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "resposta")
    private String resposta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ip")
    private String ip;
    @JoinColumn(name = "pergunta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Perguntas pergunta;

    public Avaliacoes() {
    }

    public Avaliacoes(Integer id) {
        this.id = id;
    }

    public Avaliacoes(Integer id, String resposta, Date data, String ip) {
        this.id = id;
        this.resposta = resposta;
        this.data = data;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Perguntas getPergunta() {
        return pergunta;
    }

    public void setPergunta(Perguntas pergunta) {
        this.pergunta = pergunta;
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
        if (!(object instanceof Avaliacoes)) {
            return false;
        }
        Avaliacoes other = (Avaliacoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Avaliacoes[ id=" + id + " ]";
    }

    public Presenca getPresenca() {
        return presenca;
    }

    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
    }
    
}
