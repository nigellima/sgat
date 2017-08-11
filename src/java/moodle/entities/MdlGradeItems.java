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
@Table(name = "mdl_grade_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlGradeItems.findAll", query = "SELECT m FROM MdlGradeItems m")
    , @NamedQuery(name = "MdlGradeItems.findById", query = "SELECT m FROM MdlGradeItems m WHERE m.id = :id")
    , @NamedQuery(name = "MdlGradeItems.findByCourseid", query = "SELECT m FROM MdlGradeItems m WHERE m.courseid = :courseid")
    , @NamedQuery(name = "MdlGradeItems.findByCategoryid", query = "SELECT m FROM MdlGradeItems m WHERE m.categoryid = :categoryid")
    , @NamedQuery(name = "MdlGradeItems.findByItemname", query = "SELECT m FROM MdlGradeItems m WHERE m.itemname = :itemname")
    , @NamedQuery(name = "MdlGradeItems.findByItemtype", query = "SELECT m FROM MdlGradeItems m WHERE m.itemtype = :itemtype")
    , @NamedQuery(name = "MdlGradeItems.findByItemmodule", query = "SELECT m FROM MdlGradeItems m WHERE m.itemmodule = :itemmodule")
    , @NamedQuery(name = "MdlGradeItems.findByIteminstance", query = "SELECT m FROM MdlGradeItems m WHERE m.iteminstance = :iteminstance")
    , @NamedQuery(name = "MdlGradeItems.findByItemnumber", query = "SELECT m FROM MdlGradeItems m WHERE m.itemnumber = :itemnumber")
    , @NamedQuery(name = "MdlGradeItems.findByIdnumber", query = "SELECT m FROM MdlGradeItems m WHERE m.idnumber = :idnumber")
    , @NamedQuery(name = "MdlGradeItems.findByGradetype", query = "SELECT m FROM MdlGradeItems m WHERE m.gradetype = :gradetype")
    , @NamedQuery(name = "MdlGradeItems.findByGrademax", query = "SELECT m FROM MdlGradeItems m WHERE m.grademax = :grademax")
    , @NamedQuery(name = "MdlGradeItems.findByGrademin", query = "SELECT m FROM MdlGradeItems m WHERE m.grademin = :grademin")
    , @NamedQuery(name = "MdlGradeItems.findByScaleid", query = "SELECT m FROM MdlGradeItems m WHERE m.scaleid = :scaleid")
    , @NamedQuery(name = "MdlGradeItems.findByOutcomeid", query = "SELECT m FROM MdlGradeItems m WHERE m.outcomeid = :outcomeid")
    , @NamedQuery(name = "MdlGradeItems.findByGradepass", query = "SELECT m FROM MdlGradeItems m WHERE m.gradepass = :gradepass")
    , @NamedQuery(name = "MdlGradeItems.findByMultfactor", query = "SELECT m FROM MdlGradeItems m WHERE m.multfactor = :multfactor")
    , @NamedQuery(name = "MdlGradeItems.findByPlusfactor", query = "SELECT m FROM MdlGradeItems m WHERE m.plusfactor = :plusfactor")
    , @NamedQuery(name = "MdlGradeItems.findByAggregationcoef", query = "SELECT m FROM MdlGradeItems m WHERE m.aggregationcoef = :aggregationcoef")
    , @NamedQuery(name = "MdlGradeItems.findByAggregationcoef2", query = "SELECT m FROM MdlGradeItems m WHERE m.aggregationcoef2 = :aggregationcoef2")
    , @NamedQuery(name = "MdlGradeItems.findBySortorder", query = "SELECT m FROM MdlGradeItems m WHERE m.sortorder = :sortorder")
    , @NamedQuery(name = "MdlGradeItems.findByDisplay", query = "SELECT m FROM MdlGradeItems m WHERE m.display = :display")
    , @NamedQuery(name = "MdlGradeItems.findByDecimals", query = "SELECT m FROM MdlGradeItems m WHERE m.decimals = :decimals")
    , @NamedQuery(name = "MdlGradeItems.findByHidden", query = "SELECT m FROM MdlGradeItems m WHERE m.hidden = :hidden")
    , @NamedQuery(name = "MdlGradeItems.findByLocked", query = "SELECT m FROM MdlGradeItems m WHERE m.locked = :locked")
    , @NamedQuery(name = "MdlGradeItems.findByLocktime", query = "SELECT m FROM MdlGradeItems m WHERE m.locktime = :locktime")
    , @NamedQuery(name = "MdlGradeItems.findByNeedsupdate", query = "SELECT m FROM MdlGradeItems m WHERE m.needsupdate = :needsupdate")
    , @NamedQuery(name = "MdlGradeItems.findByWeightoverride", query = "SELECT m FROM MdlGradeItems m WHERE m.weightoverride = :weightoverride")
    , @NamedQuery(name = "MdlGradeItems.findByTimecreated", query = "SELECT m FROM MdlGradeItems m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlGradeItems.findByTimemodified", query = "SELECT m FROM MdlGradeItems m WHERE m.timemodified = :timemodified")})
public class MdlGradeItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "courseid")
    private BigInteger courseid;
    @Column(name = "categoryid")
    private BigInteger categoryid;
    @Size(max = 255)
    @Column(name = "itemname")
    private String itemname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "itemtype")
    private String itemtype;
    @Size(max = 30)
    @Column(name = "itemmodule")
    private String itemmodule;
    @Column(name = "iteminstance")
    private BigInteger iteminstance;
    @Column(name = "itemnumber")
    private BigInteger itemnumber;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "iteminfo")
    private String iteminfo;
    @Size(max = 255)
    @Column(name = "idnumber")
    private String idnumber;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "calculation")
    private String calculation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gradetype")
    private short gradetype;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "grademax")
    private BigDecimal grademax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grademin")
    private BigDecimal grademin;
    @Column(name = "scaleid")
    private BigInteger scaleid;
    @Column(name = "outcomeid")
    private BigInteger outcomeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gradepass")
    private BigDecimal gradepass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "multfactor")
    private BigDecimal multfactor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plusfactor")
    private BigDecimal plusfactor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aggregationcoef")
    private BigDecimal aggregationcoef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aggregationcoef2")
    private BigDecimal aggregationcoef2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sortorder")
    private long sortorder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "display")
    private long display;
    @Column(name = "decimals")
    private Boolean decimals;
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
    @Column(name = "needsupdate")
    private long needsupdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weightoverride")
    private boolean weightoverride;
    @Column(name = "timecreated")
    private BigInteger timecreated;
    @Column(name = "timemodified")
    private BigInteger timemodified;

    public MdlGradeItems() {
    }

    public MdlGradeItems(Long id) {
        this.id = id;
    }

    public MdlGradeItems(Long id, String itemtype, short gradetype, BigDecimal grademax, BigDecimal grademin, BigDecimal gradepass, BigDecimal multfactor, BigDecimal plusfactor, BigDecimal aggregationcoef, BigDecimal aggregationcoef2, long sortorder, long display, long hidden, long locked, long locktime, long needsupdate, boolean weightoverride) {
        this.id = id;
        this.itemtype = itemtype;
        this.gradetype = gradetype;
        this.grademax = grademax;
        this.grademin = grademin;
        this.gradepass = gradepass;
        this.multfactor = multfactor;
        this.plusfactor = plusfactor;
        this.aggregationcoef = aggregationcoef;
        this.aggregationcoef2 = aggregationcoef2;
        this.sortorder = sortorder;
        this.display = display;
        this.hidden = hidden;
        this.locked = locked;
        this.locktime = locktime;
        this.needsupdate = needsupdate;
        this.weightoverride = weightoverride;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getCourseid() {
        return courseid;
    }

    public void setCourseid(BigInteger courseid) {
        this.courseid = courseid;
    }

    public BigInteger getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(BigInteger categoryid) {
        this.categoryid = categoryid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemmodule() {
        return itemmodule;
    }

    public void setItemmodule(String itemmodule) {
        this.itemmodule = itemmodule;
    }

    public BigInteger getIteminstance() {
        return iteminstance;
    }

    public void setIteminstance(BigInteger iteminstance) {
        this.iteminstance = iteminstance;
    }

    public BigInteger getItemnumber() {
        return itemnumber;
    }

    public void setItemnumber(BigInteger itemnumber) {
        this.itemnumber = itemnumber;
    }

    public String getIteminfo() {
        return iteminfo;
    }

    public void setIteminfo(String iteminfo) {
        this.iteminfo = iteminfo;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public short getGradetype() {
        return gradetype;
    }

    public void setGradetype(short gradetype) {
        this.gradetype = gradetype;
    }

    public BigDecimal getGrademax() {
        return grademax;
    }

    public void setGrademax(BigDecimal grademax) {
        this.grademax = grademax;
    }

    public BigDecimal getGrademin() {
        return grademin;
    }

    public void setGrademin(BigDecimal grademin) {
        this.grademin = grademin;
    }

    public BigInteger getScaleid() {
        return scaleid;
    }

    public void setScaleid(BigInteger scaleid) {
        this.scaleid = scaleid;
    }

    public BigInteger getOutcomeid() {
        return outcomeid;
    }

    public void setOutcomeid(BigInteger outcomeid) {
        this.outcomeid = outcomeid;
    }

    public BigDecimal getGradepass() {
        return gradepass;
    }

    public void setGradepass(BigDecimal gradepass) {
        this.gradepass = gradepass;
    }

    public BigDecimal getMultfactor() {
        return multfactor;
    }

    public void setMultfactor(BigDecimal multfactor) {
        this.multfactor = multfactor;
    }

    public BigDecimal getPlusfactor() {
        return plusfactor;
    }

    public void setPlusfactor(BigDecimal plusfactor) {
        this.plusfactor = plusfactor;
    }

    public BigDecimal getAggregationcoef() {
        return aggregationcoef;
    }

    public void setAggregationcoef(BigDecimal aggregationcoef) {
        this.aggregationcoef = aggregationcoef;
    }

    public BigDecimal getAggregationcoef2() {
        return aggregationcoef2;
    }

    public void setAggregationcoef2(BigDecimal aggregationcoef2) {
        this.aggregationcoef2 = aggregationcoef2;
    }

    public long getSortorder() {
        return sortorder;
    }

    public void setSortorder(long sortorder) {
        this.sortorder = sortorder;
    }

    public long getDisplay() {
        return display;
    }

    public void setDisplay(long display) {
        this.display = display;
    }

    public Boolean getDecimals() {
        return decimals;
    }

    public void setDecimals(Boolean decimals) {
        this.decimals = decimals;
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

    public long getNeedsupdate() {
        return needsupdate;
    }

    public void setNeedsupdate(long needsupdate) {
        this.needsupdate = needsupdate;
    }

    public boolean getWeightoverride() {
        return weightoverride;
    }

    public void setWeightoverride(boolean weightoverride) {
        this.weightoverride = weightoverride;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdlGradeItems)) {
            return false;
        }
        MdlGradeItems other = (MdlGradeItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlGradeItems[ id=" + id + " ]";
    }
    
}
