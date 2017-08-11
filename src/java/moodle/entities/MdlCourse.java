/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodle.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "mdl_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlCourse.findAll", query = "SELECT m FROM MdlCourse m")
    , @NamedQuery(name = "MdlCourse.findById", query = "SELECT m FROM MdlCourse m WHERE m.id = :id")
    , @NamedQuery(name = "MdlCourse.findByCategory", query = "SELECT m FROM MdlCourse m WHERE m.category = :category")
    , @NamedQuery(name = "MdlCourse.findBySortorder", query = "SELECT m FROM MdlCourse m WHERE m.sortorder = :sortorder")
    , @NamedQuery(name = "MdlCourse.findByFullname", query = "SELECT m FROM MdlCourse m WHERE m.fullname = :fullname")
    , @NamedQuery(name = "MdlCourse.findByShortname", query = "SELECT m FROM MdlCourse m WHERE m.shortname = :shortname")
    , @NamedQuery(name = "MdlCourse.findByIdnumber", query = "SELECT m FROM MdlCourse m WHERE m.idnumber = :idnumber")
    , @NamedQuery(name = "MdlCourse.findBySummaryformat", query = "SELECT m FROM MdlCourse m WHERE m.summaryformat = :summaryformat")
    , @NamedQuery(name = "MdlCourse.findByFormat", query = "SELECT m FROM MdlCourse m WHERE m.format = :format")
    , @NamedQuery(name = "MdlCourse.findByShowgrades", query = "SELECT m FROM MdlCourse m WHERE m.showgrades = :showgrades")
    , @NamedQuery(name = "MdlCourse.findByNewsitems", query = "SELECT m FROM MdlCourse m WHERE m.newsitems = :newsitems")
    , @NamedQuery(name = "MdlCourse.findByStartdate", query = "SELECT m FROM MdlCourse m WHERE m.startdate = :startdate")
    , @NamedQuery(name = "MdlCourse.findByMarker", query = "SELECT m FROM MdlCourse m WHERE m.marker = :marker")
    , @NamedQuery(name = "MdlCourse.findByMaxbytes", query = "SELECT m FROM MdlCourse m WHERE m.maxbytes = :maxbytes")
    , @NamedQuery(name = "MdlCourse.findByLegacyfiles", query = "SELECT m FROM MdlCourse m WHERE m.legacyfiles = :legacyfiles")
    , @NamedQuery(name = "MdlCourse.findByShowreports", query = "SELECT m FROM MdlCourse m WHERE m.showreports = :showreports")
    , @NamedQuery(name = "MdlCourse.findByVisible", query = "SELECT m FROM MdlCourse m WHERE m.visible = :visible")
    , @NamedQuery(name = "MdlCourse.findByVisibleold", query = "SELECT m FROM MdlCourse m WHERE m.visibleold = :visibleold")
    , @NamedQuery(name = "MdlCourse.findByGroupmode", query = "SELECT m FROM MdlCourse m WHERE m.groupmode = :groupmode")
    , @NamedQuery(name = "MdlCourse.findByGroupmodeforce", query = "SELECT m FROM MdlCourse m WHERE m.groupmodeforce = :groupmodeforce")
    , @NamedQuery(name = "MdlCourse.findByDefaultgroupingid", query = "SELECT m FROM MdlCourse m WHERE m.defaultgroupingid = :defaultgroupingid")
    , @NamedQuery(name = "MdlCourse.findByLang", query = "SELECT m FROM MdlCourse m WHERE m.lang = :lang")
    , @NamedQuery(name = "MdlCourse.findByCalendartype", query = "SELECT m FROM MdlCourse m WHERE m.calendartype = :calendartype")
    , @NamedQuery(name = "MdlCourse.findByTheme", query = "SELECT m FROM MdlCourse m WHERE m.theme = :theme")
    , @NamedQuery(name = "MdlCourse.findByTimecreated", query = "SELECT m FROM MdlCourse m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlCourse.findByTimemodified", query = "SELECT m FROM MdlCourse m WHERE m.timemodified = :timemodified")
    , @NamedQuery(name = "MdlCourse.findByRequested", query = "SELECT m FROM MdlCourse m WHERE m.requested = :requested")
    , @NamedQuery(name = "MdlCourse.findByEnablecompletion", query = "SELECT m FROM MdlCourse m WHERE m.enablecompletion = :enablecompletion")
    , @NamedQuery(name = "MdlCourse.findByCompletionnotify", query = "SELECT m FROM MdlCourse m WHERE m.completionnotify = :completionnotify")
    , @NamedQuery(name = "MdlCourse.findByCacherev", query = "SELECT m FROM MdlCourse m WHERE m.cacherev = :cacherev")})
public class MdlCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "category")
    private long category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sortorder")
    private long sortorder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "shortname")
    private String shortname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idnumber")
    private String idnumber;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "summary")
    private String summary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "summaryformat")
    private short summaryformat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "format")
    private String format;
    @Basic(optional = false)
    @NotNull
    @Column(name = "showgrades")
    private short showgrades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "newsitems")
    private int newsitems;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    private long startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marker")
    private long marker;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maxbytes")
    private long maxbytes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "legacyfiles")
    private short legacyfiles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "showreports")
    private short showreports;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    private boolean visible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visibleold")
    private boolean visibleold;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupmode")
    private short groupmode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupmodeforce")
    private short groupmodeforce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "defaultgroupingid")
    private long defaultgroupingid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "lang")
    private String lang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "calendartype")
    private String calendartype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "theme")
    private String theme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timecreated")
    private long timecreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timemodified")
    private long timemodified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requested")
    private boolean requested;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enablecompletion")
    private boolean enablecompletion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "completionnotify")
    private boolean completionnotify;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cacherev")
    private long cacherev;
    public MdlCourse() {
    }

    public MdlCourse(Long id) {
        this.id = id;
    }

    public MdlCourse(Long id, long category, long sortorder, String fullname, String shortname, String idnumber, short summaryformat, String format, short showgrades, int newsitems, long startdate, long marker, long maxbytes, short legacyfiles, short showreports, boolean visible, boolean visibleold, short groupmode, short groupmodeforce, long defaultgroupingid, String lang, String calendartype, String theme, long timecreated, long timemodified, boolean requested, boolean enablecompletion, boolean completionnotify, long cacherev) {
        this.id = id;
        this.category = category;
        this.sortorder = sortorder;
        this.fullname = fullname;
        this.shortname = shortname;
        this.idnumber = idnumber;
        this.summaryformat = summaryformat;
        this.format = format;
        this.showgrades = showgrades;
        this.newsitems = newsitems;
        this.startdate = startdate;
        this.marker = marker;
        this.maxbytes = maxbytes;
        this.legacyfiles = legacyfiles;
        this.showreports = showreports;
        this.visible = visible;
        this.visibleold = visibleold;
        this.groupmode = groupmode;
        this.groupmodeforce = groupmodeforce;
        this.defaultgroupingid = defaultgroupingid;
        this.lang = lang;
        this.calendartype = calendartype;
        this.theme = theme;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
        this.requested = requested;
        this.enablecompletion = enablecompletion;
        this.completionnotify = completionnotify;
        this.cacherev = cacherev;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getSortorder() {
        return sortorder;
    }

    public void setSortorder(long sortorder) {
        this.sortorder = sortorder;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public short getSummaryformat() {
        return summaryformat;
    }

    public void setSummaryformat(short summaryformat) {
        this.summaryformat = summaryformat;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public short getShowgrades() {
        return showgrades;
    }

    public void setShowgrades(short showgrades) {
        this.showgrades = showgrades;
    }

    public int getNewsitems() {
        return newsitems;
    }

    public void setNewsitems(int newsitems) {
        this.newsitems = newsitems;
    }

    public long getStartdate() {
        return startdate;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public long getMarker() {
        return marker;
    }

    public void setMarker(long marker) {
        this.marker = marker;
    }

    public long getMaxbytes() {
        return maxbytes;
    }

    public void setMaxbytes(long maxbytes) {
        this.maxbytes = maxbytes;
    }

    public short getLegacyfiles() {
        return legacyfiles;
    }

    public void setLegacyfiles(short legacyfiles) {
        this.legacyfiles = legacyfiles;
    }

    public short getShowreports() {
        return showreports;
    }

    public void setShowreports(short showreports) {
        this.showreports = showreports;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisibleold() {
        return visibleold;
    }

    public void setVisibleold(boolean visibleold) {
        this.visibleold = visibleold;
    }

    public short getGroupmode() {
        return groupmode;
    }

    public void setGroupmode(short groupmode) {
        this.groupmode = groupmode;
    }

    public short getGroupmodeforce() {
        return groupmodeforce;
    }

    public void setGroupmodeforce(short groupmodeforce) {
        this.groupmodeforce = groupmodeforce;
    }

    public long getDefaultgroupingid() {
        return defaultgroupingid;
    }

    public void setDefaultgroupingid(long defaultgroupingid) {
        this.defaultgroupingid = defaultgroupingid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCalendartype() {
        return calendartype;
    }

    public void setCalendartype(String calendartype) {
        this.calendartype = calendartype;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public boolean getRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public boolean getEnablecompletion() {
        return enablecompletion;
    }

    public void setEnablecompletion(boolean enablecompletion) {
        this.enablecompletion = enablecompletion;
    }

    public boolean getCompletionnotify() {
        return completionnotify;
    }

    public void setCompletionnotify(boolean completionnotify) {
        this.completionnotify = completionnotify;
    }

    public long getCacherev() {
        return cacherev;
    }

    public void setCacherev(long cacherev) {
        this.cacherev = cacherev;
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
        if (!(object instanceof MdlCourse)) {
            return false;
        }
        MdlCourse other = (MdlCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlCourse[ id=" + id + " ]";
    }
    
}
