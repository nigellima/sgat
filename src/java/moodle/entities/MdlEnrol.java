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
@Table(name = "mdl_enrol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlEnrol.findAll", query = "SELECT m FROM MdlEnrol m")
    , @NamedQuery(name = "MdlEnrol.findById", query = "SELECT m FROM MdlEnrol m WHERE m.id = :id")
    , @NamedQuery(name = "MdlEnrol.findByEnrol", query = "SELECT m FROM MdlEnrol m WHERE m.enrol = :enrol")
    , @NamedQuery(name = "MdlEnrol.findByStatus", query = "SELECT m FROM MdlEnrol m WHERE m.status = :status")
    , @NamedQuery(name = "MdlEnrol.findByCourseid", query = "SELECT m FROM MdlEnrol m WHERE m.courseid = :courseid")
    , @NamedQuery(name = "MdlEnrol.findBySortorder", query = "SELECT m FROM MdlEnrol m WHERE m.sortorder = :sortorder")
    , @NamedQuery(name = "MdlEnrol.findByName", query = "SELECT m FROM MdlEnrol m WHERE m.name = :name")
    , @NamedQuery(name = "MdlEnrol.findByEnrolperiod", query = "SELECT m FROM MdlEnrol m WHERE m.enrolperiod = :enrolperiod")
    , @NamedQuery(name = "MdlEnrol.findByEnrolstartdate", query = "SELECT m FROM MdlEnrol m WHERE m.enrolstartdate = :enrolstartdate")
    , @NamedQuery(name = "MdlEnrol.findByEnrolenddate", query = "SELECT m FROM MdlEnrol m WHERE m.enrolenddate = :enrolenddate")
    , @NamedQuery(name = "MdlEnrol.findByExpirynotify", query = "SELECT m FROM MdlEnrol m WHERE m.expirynotify = :expirynotify")
    , @NamedQuery(name = "MdlEnrol.findByExpirythreshold", query = "SELECT m FROM MdlEnrol m WHERE m.expirythreshold = :expirythreshold")
    , @NamedQuery(name = "MdlEnrol.findByNotifyall", query = "SELECT m FROM MdlEnrol m WHERE m.notifyall = :notifyall")
    , @NamedQuery(name = "MdlEnrol.findByPassword", query = "SELECT m FROM MdlEnrol m WHERE m.password = :password")
    , @NamedQuery(name = "MdlEnrol.findByCost", query = "SELECT m FROM MdlEnrol m WHERE m.cost = :cost")
    , @NamedQuery(name = "MdlEnrol.findByCurrency", query = "SELECT m FROM MdlEnrol m WHERE m.currency = :currency")
    , @NamedQuery(name = "MdlEnrol.findByRoleid", query = "SELECT m FROM MdlEnrol m WHERE m.roleid = :roleid")
    , @NamedQuery(name = "MdlEnrol.findByCustomint1", query = "SELECT m FROM MdlEnrol m WHERE m.customint1 = :customint1")
    , @NamedQuery(name = "MdlEnrol.findByCustomint2", query = "SELECT m FROM MdlEnrol m WHERE m.customint2 = :customint2")
    , @NamedQuery(name = "MdlEnrol.findByCustomint3", query = "SELECT m FROM MdlEnrol m WHERE m.customint3 = :customint3")
    , @NamedQuery(name = "MdlEnrol.findByCustomint4", query = "SELECT m FROM MdlEnrol m WHERE m.customint4 = :customint4")
    , @NamedQuery(name = "MdlEnrol.findByCustomint5", query = "SELECT m FROM MdlEnrol m WHERE m.customint5 = :customint5")
    , @NamedQuery(name = "MdlEnrol.findByCustomint6", query = "SELECT m FROM MdlEnrol m WHERE m.customint6 = :customint6")
    , @NamedQuery(name = "MdlEnrol.findByCustomint7", query = "SELECT m FROM MdlEnrol m WHERE m.customint7 = :customint7")
    , @NamedQuery(name = "MdlEnrol.findByCustomint8", query = "SELECT m FROM MdlEnrol m WHERE m.customint8 = :customint8")
    , @NamedQuery(name = "MdlEnrol.findByCustomchar1", query = "SELECT m FROM MdlEnrol m WHERE m.customchar1 = :customchar1")
    , @NamedQuery(name = "MdlEnrol.findByCustomchar2", query = "SELECT m FROM MdlEnrol m WHERE m.customchar2 = :customchar2")
    , @NamedQuery(name = "MdlEnrol.findByCustomchar3", query = "SELECT m FROM MdlEnrol m WHERE m.customchar3 = :customchar3")
    , @NamedQuery(name = "MdlEnrol.findByCustomdec1", query = "SELECT m FROM MdlEnrol m WHERE m.customdec1 = :customdec1")
    , @NamedQuery(name = "MdlEnrol.findByCustomdec2", query = "SELECT m FROM MdlEnrol m WHERE m.customdec2 = :customdec2")
    , @NamedQuery(name = "MdlEnrol.findByTimecreated", query = "SELECT m FROM MdlEnrol m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlEnrol.findByTimemodified", query = "SELECT m FROM MdlEnrol m WHERE m.timemodified = :timemodified")})
public class MdlEnrol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "enrol")
    private String enrol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private long status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseid")
    private long courseid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sortorder")
    private long sortorder;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "enrolperiod")
    private BigInteger enrolperiod;
    @Column(name = "enrolstartdate")
    private BigInteger enrolstartdate;
    @Column(name = "enrolenddate")
    private BigInteger enrolenddate;
    @Column(name = "expirynotify")
    private Boolean expirynotify;
    @Column(name = "expirythreshold")
    private BigInteger expirythreshold;
    @Column(name = "notifyall")
    private Boolean notifyall;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @Size(max = 20)
    @Column(name = "cost")
    private String cost;
    @Size(max = 3)
    @Column(name = "currency")
    private String currency;
    @Column(name = "roleid")
    private BigInteger roleid;
    @Column(name = "customint1")
    private BigInteger customint1;
    @Column(name = "customint2")
    private BigInteger customint2;
    @Column(name = "customint3")
    private BigInteger customint3;
    @Column(name = "customint4")
    private BigInteger customint4;
    @Column(name = "customint5")
    private BigInteger customint5;
    @Column(name = "customint6")
    private BigInteger customint6;
    @Column(name = "customint7")
    private BigInteger customint7;
    @Column(name = "customint8")
    private BigInteger customint8;
    @Size(max = 255)
    @Column(name = "customchar1")
    private String customchar1;
    @Size(max = 255)
    @Column(name = "customchar2")
    private String customchar2;
    @Size(max = 1333)
    @Column(name = "customchar3")
    private String customchar3;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "customdec1")
    private BigDecimal customdec1;
    @Column(name = "customdec2")
    private BigDecimal customdec2;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "customtext1")
    private String customtext1;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "customtext2")
    private String customtext2;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "customtext3")
    private String customtext3;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "customtext4")
    private String customtext4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timecreated")
    private long timecreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timemodified")
    private long timemodified;

    public MdlEnrol() {
    }

    public MdlEnrol(Long id) {
        this.id = id;
    }

    public MdlEnrol(Long id, String enrol, long status, long courseid, long sortorder, long timecreated, long timemodified) {
        this.id = id;
        this.enrol = enrol;
        this.status = status;
        this.courseid = courseid;
        this.sortorder = sortorder;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnrol() {
        return enrol;
    }

    public void setEnrol(String enrol) {
        this.enrol = enrol;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public long getSortorder() {
        return sortorder;
    }

    public void setSortorder(long sortorder) {
        this.sortorder = sortorder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getEnrolperiod() {
        return enrolperiod;
    }

    public void setEnrolperiod(BigInteger enrolperiod) {
        this.enrolperiod = enrolperiod;
    }

    public BigInteger getEnrolstartdate() {
        return enrolstartdate;
    }

    public void setEnrolstartdate(BigInteger enrolstartdate) {
        this.enrolstartdate = enrolstartdate;
    }

    public BigInteger getEnrolenddate() {
        return enrolenddate;
    }

    public void setEnrolenddate(BigInteger enrolenddate) {
        this.enrolenddate = enrolenddate;
    }

    public Boolean getExpirynotify() {
        return expirynotify;
    }

    public void setExpirynotify(Boolean expirynotify) {
        this.expirynotify = expirynotify;
    }

    public BigInteger getExpirythreshold() {
        return expirythreshold;
    }

    public void setExpirythreshold(BigInteger expirythreshold) {
        this.expirythreshold = expirythreshold;
    }

    public Boolean getNotifyall() {
        return notifyall;
    }

    public void setNotifyall(Boolean notifyall) {
        this.notifyall = notifyall;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigInteger getRoleid() {
        return roleid;
    }

    public void setRoleid(BigInteger roleid) {
        this.roleid = roleid;
    }

    public BigInteger getCustomint1() {
        return customint1;
    }

    public void setCustomint1(BigInteger customint1) {
        this.customint1 = customint1;
    }

    public BigInteger getCustomint2() {
        return customint2;
    }

    public void setCustomint2(BigInteger customint2) {
        this.customint2 = customint2;
    }

    public BigInteger getCustomint3() {
        return customint3;
    }

    public void setCustomint3(BigInteger customint3) {
        this.customint3 = customint3;
    }

    public BigInteger getCustomint4() {
        return customint4;
    }

    public void setCustomint4(BigInteger customint4) {
        this.customint4 = customint4;
    }

    public BigInteger getCustomint5() {
        return customint5;
    }

    public void setCustomint5(BigInteger customint5) {
        this.customint5 = customint5;
    }

    public BigInteger getCustomint6() {
        return customint6;
    }

    public void setCustomint6(BigInteger customint6) {
        this.customint6 = customint6;
    }

    public BigInteger getCustomint7() {
        return customint7;
    }

    public void setCustomint7(BigInteger customint7) {
        this.customint7 = customint7;
    }

    public BigInteger getCustomint8() {
        return customint8;
    }

    public void setCustomint8(BigInteger customint8) {
        this.customint8 = customint8;
    }

    public String getCustomchar1() {
        return customchar1;
    }

    public void setCustomchar1(String customchar1) {
        this.customchar1 = customchar1;
    }

    public String getCustomchar2() {
        return customchar2;
    }

    public void setCustomchar2(String customchar2) {
        this.customchar2 = customchar2;
    }

    public String getCustomchar3() {
        return customchar3;
    }

    public void setCustomchar3(String customchar3) {
        this.customchar3 = customchar3;
    }

    public BigDecimal getCustomdec1() {
        return customdec1;
    }

    public void setCustomdec1(BigDecimal customdec1) {
        this.customdec1 = customdec1;
    }

    public BigDecimal getCustomdec2() {
        return customdec2;
    }

    public void setCustomdec2(BigDecimal customdec2) {
        this.customdec2 = customdec2;
    }

    public String getCustomtext1() {
        return customtext1;
    }

    public void setCustomtext1(String customtext1) {
        this.customtext1 = customtext1;
    }

    public String getCustomtext2() {
        return customtext2;
    }

    public void setCustomtext2(String customtext2) {
        this.customtext2 = customtext2;
    }

    public String getCustomtext3() {
        return customtext3;
    }

    public void setCustomtext3(String customtext3) {
        this.customtext3 = customtext3;
    }

    public String getCustomtext4() {
        return customtext4;
    }

    public void setCustomtext4(String customtext4) {
        this.customtext4 = customtext4;
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
        if (!(object instanceof MdlEnrol)) {
            return false;
        }
        MdlEnrol other = (MdlEnrol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlEnrol[ id=" + id + " ]";
    }
    
}
