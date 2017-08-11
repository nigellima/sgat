/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nigel
 */
@Entity
@Table(name = "mdl_grade_grades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlGradeGrades.findAll", query = "SELECT m FROM MdlGradeGrades m")
    , @NamedQuery(name = "MdlGradeGrades.findById", query = "SELECT m FROM MdlGradeGrades m WHERE m.id = :id")
    , @NamedQuery(name = "MdlGradeGrades.findByItemid", query = "SELECT m FROM MdlGradeGrades m WHERE m.itemid = :itemid")
    , @NamedQuery(name = "MdlGradeGrades.findByUserid", query = "SELECT m FROM MdlGradeGrades m WHERE m.userid = :userid")
    , @NamedQuery(name = "MdlGradeGrades.findByRawgrade", query = "SELECT m FROM MdlGradeGrades m WHERE m.rawgrade = :rawgrade")
    , @NamedQuery(name = "MdlGradeGrades.findByRawgrademax", query = "SELECT m FROM MdlGradeGrades m WHERE m.rawgrademax = :rawgrademax")
    , @NamedQuery(name = "MdlGradeGrades.findByRawgrademin", query = "SELECT m FROM MdlGradeGrades m WHERE m.rawgrademin = :rawgrademin")
    , @NamedQuery(name = "MdlGradeGrades.findByRawscaleid", query = "SELECT m FROM MdlGradeGrades m WHERE m.rawscaleid = :rawscaleid")
    , @NamedQuery(name = "MdlGradeGrades.findByUsermodified", query = "SELECT m FROM MdlGradeGrades m WHERE m.usermodified = :usermodified")
    , @NamedQuery(name = "MdlGradeGrades.findByFinalgrade", query = "SELECT m FROM MdlGradeGrades m WHERE m.finalgrade = :finalgrade")
    , @NamedQuery(name = "MdlGradeGrades.findByHidden", query = "SELECT m FROM MdlGradeGrades m WHERE m.hidden = :hidden")
    , @NamedQuery(name = "MdlGradeGrades.findByLocked", query = "SELECT m FROM MdlGradeGrades m WHERE m.locked = :locked")
    , @NamedQuery(name = "MdlGradeGrades.findByLocktime", query = "SELECT m FROM MdlGradeGrades m WHERE m.locktime = :locktime")
    , @NamedQuery(name = "MdlGradeGrades.findByExported", query = "SELECT m FROM MdlGradeGrades m WHERE m.exported = :exported")
    , @NamedQuery(name = "MdlGradeGrades.findByOverridden", query = "SELECT m FROM MdlGradeGrades m WHERE m.overridden = :overridden")
    , @NamedQuery(name = "MdlGradeGrades.findByExcluded", query = "SELECT m FROM MdlGradeGrades m WHERE m.excluded = :excluded")
    , @NamedQuery(name = "MdlGradeGrades.findByFeedbackformat", query = "SELECT m FROM MdlGradeGrades m WHERE m.feedbackformat = :feedbackformat")
    , @NamedQuery(name = "MdlGradeGrades.findByInformationformat", query = "SELECT m FROM MdlGradeGrades m WHERE m.informationformat = :informationformat")
    , @NamedQuery(name = "MdlGradeGrades.findByTimecreated", query = "SELECT m FROM MdlGradeGrades m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlGradeGrades.findByTimemodified", query = "SELECT m FROM MdlGradeGrades m WHERE m.timemodified = :timemodified")
    , @NamedQuery(name = "MdlGradeGrades.findByAggregationstatus", query = "SELECT m FROM MdlGradeGrades m WHERE m.aggregationstatus = :aggregationstatus")
    , @NamedQuery(name = "MdlGradeGrades.findByAggregationweight", query = "SELECT m FROM MdlGradeGrades m WHERE m.aggregationweight = :aggregationweight")})
public class MdlGradeGrades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private long itemid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private long userid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rawgrade")
    private BigDecimal rawgrade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rawgrademax")
    private BigDecimal rawgrademax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rawgrademin")
    private BigDecimal rawgrademin;
    @Column(name = "rawscaleid")
    private BigInteger rawscaleid;
    @Column(name = "usermodified")
    private BigInteger usermodified;
    @Column(name = "finalgrade")
    private BigDecimal finalgrade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hidden")
    private long hidden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private long locked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locktime")
    private long locktime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exported")
    private long exported;
    @Basic(optional = false)
    @NotNull
    @Column(name = "overridden")
    private long overridden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "excluded")
    private long excluded;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "feedback")
    private String feedback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedbackformat")
    private long feedbackformat;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "information")
    private String information;
    @Basic(optional = false)
    @NotNull
    @Column(name = "informationformat")
    private long informationformat;
    @Column(name = "timecreated")
    private BigInteger timecreated;
    @Column(name = "timemodified")
    private BigInteger timemodified;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "aggregationstatus")
    private String aggregationstatus;
    @Column(name = "aggregationweight")
    private BigDecimal aggregationweight;

    public MdlGradeGrades() {
    }

    public MdlGradeGrades(Long id) {
        this.id = id;
    }

    public MdlGradeGrades(Long id, long itemid, long userid, BigDecimal rawgrademax, BigDecimal rawgrademin, long hidden, long locked, long locktime, long exported, long overridden, long excluded, long feedbackformat, long informationformat, String aggregationstatus) {
        this.id = id;
        this.itemid = itemid;
        this.userid = userid;
        this.rawgrademax = rawgrademax;
        this.rawgrademin = rawgrademin;
        this.hidden = hidden;
        this.locked = locked;
        this.locktime = locktime;
        this.exported = exported;
        this.overridden = overridden;
        this.excluded = excluded;
        this.feedbackformat = feedbackformat;
        this.informationformat = informationformat;
        this.aggregationstatus = aggregationstatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public BigDecimal getRawgrade() {
        return rawgrade;
    }

    public void setRawgrade(BigDecimal rawgrade) {
        this.rawgrade = rawgrade;
    }

    public BigDecimal getRawgrademax() {
        return rawgrademax;
    }

    public void setRawgrademax(BigDecimal rawgrademax) {
        this.rawgrademax = rawgrademax;
    }

    public BigDecimal getRawgrademin() {
        return rawgrademin;
    }

    public void setRawgrademin(BigDecimal rawgrademin) {
        this.rawgrademin = rawgrademin;
    }

    public BigInteger getRawscaleid() {
        return rawscaleid;
    }

    public void setRawscaleid(BigInteger rawscaleid) {
        this.rawscaleid = rawscaleid;
    }

    public BigInteger getUsermodified() {
        return usermodified;
    }

    public void setUsermodified(BigInteger usermodified) {
        this.usermodified = usermodified;
    }

    public BigDecimal getFinalgrade() {
        return finalgrade;
    }

    public void setFinalgrade(BigDecimal finalgrade) {
        this.finalgrade = finalgrade;
    }

    public long getHidden() {
        return hidden;
    }

    public void setHidden(long hidden) {
        this.hidden = hidden;
    }

    public long getLocked() {
        return locked;
    }

    public void setLocked(long locked) {
        this.locked = locked;
    }

    public long getLocktime() {
        return locktime;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public long getExported() {
        return exported;
    }

    public void setExported(long exported) {
        this.exported = exported;
    }

    public long getOverridden() {
        return overridden;
    }

    public void setOverridden(long overridden) {
        this.overridden = overridden;
    }

    public long getExcluded() {
        return excluded;
    }

    public void setExcluded(long excluded) {
        this.excluded = excluded;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public long getFeedbackformat() {
        return feedbackformat;
    }

    public void setFeedbackformat(long feedbackformat) {
        this.feedbackformat = feedbackformat;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public long getInformationformat() {
        return informationformat;
    }

    public void setInformationformat(long informationformat) {
        this.informationformat = informationformat;
    }

    public BigInteger getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(BigInteger timecreated) {
        this.timecreated = timecreated;
    }

    public BigInteger getTimemodified() {
        return timemodified;
    }

    public void setTimemodified(BigInteger timemodified) {
        this.timemodified = timemodified;
    }

    public String getAggregationstatus() {
        return aggregationstatus;
    }

    public void setAggregationstatus(String aggregationstatus) {
        this.aggregationstatus = aggregationstatus;
    }

    public BigDecimal getAggregationweight() {
        return aggregationweight;
    }

    public void setAggregationweight(BigDecimal aggregationweight) {
        this.aggregationweight = aggregationweight;
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
        if (!(object instanceof MdlGradeGrades)) {
            return false;
        }
        MdlGradeGrades other = (MdlGradeGrades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlGradeGrades[ id=" + id + " ]";
    }
    
}
