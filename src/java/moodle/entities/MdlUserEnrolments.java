/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nigel
 */
@Entity
@Table(name = "mdl_user_enrolments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlUserEnrolments.findAll", query = "SELECT m FROM MdlUserEnrolments m")
    , @NamedQuery(name = "MdlUserEnrolments.findById", query = "SELECT m FROM MdlUserEnrolments m WHERE m.id = :id")
    , @NamedQuery(name = "MdlUserEnrolments.findByStatus", query = "SELECT m FROM MdlUserEnrolments m WHERE m.status = :status")
    , @NamedQuery(name = "MdlUserEnrolments.findByEnrolid", query = "SELECT m FROM MdlUserEnrolments m WHERE m.enrolid = :enrolid")
    , @NamedQuery(name = "MdlUserEnrolments.findByUserid", query = "SELECT m FROM MdlUserEnrolments m WHERE m.userid = :userid")
    , @NamedQuery(name = "MdlUserEnrolments.findByTimestart", query = "SELECT m FROM MdlUserEnrolments m WHERE m.timestart = :timestart")
    , @NamedQuery(name = "MdlUserEnrolments.findByTimeend", query = "SELECT m FROM MdlUserEnrolments m WHERE m.timeend = :timeend")
    , @NamedQuery(name = "MdlUserEnrolments.findByModifierid", query = "SELECT m FROM MdlUserEnrolments m WHERE m.modifierid = :modifierid")
    , @NamedQuery(name = "MdlUserEnrolments.findByTimecreated", query = "SELECT m FROM MdlUserEnrolments m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlUserEnrolments.findByTimemodified", query = "SELECT m FROM MdlUserEnrolments m WHERE m.timemodified = :timemodified")})
public class MdlUserEnrolments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private long status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enrolid")
    private long enrolid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private long userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestart")
    private long timestart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeend")
    private long timeend;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modifierid")
    private long modifierid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timecreated")
    private long timecreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timemodified")
    private long timemodified;

    public MdlUserEnrolments() {
    }

    public MdlUserEnrolments(Long id) {
        this.id = id;
    }

    public MdlUserEnrolments(Long id, long status, long enrolid, long userid, long timestart, long timeend, long modifierid, long timecreated, long timemodified) {
        this.id = id;
        this.status = status;
        this.enrolid = enrolid;
        this.userid = userid;
        this.timestart = timestart;
        this.timeend = timeend;
        this.modifierid = modifierid;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getEnrolid() {
        return enrolid;
    }

    public void setEnrolid(long enrolid) {
        this.enrolid = enrolid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }

    public long getTimeend() {
        return timeend;
    }

    public void setTimeend(long timeend) {
        this.timeend = timeend;
    }

    public long getModifierid() {
        return modifierid;
    }

    public void setModifierid(long modifierid) {
        this.modifierid = modifierid;
    }

    public long getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(long timecreated) {
        this.timecreated = timecreated;
    }

    public long getTimemodified() {
        return timemodified;
    }

    public void setTimemodified(long timemodified) {
        this.timemodified = timemodified;
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
        if (!(object instanceof MdlUserEnrolments)) {
            return false;
        }
        MdlUserEnrolments other = (MdlUserEnrolments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlUserEnrolments[ id=" + id + " ]";
    }
    
}
