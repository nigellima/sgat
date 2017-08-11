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
@Table(name = "mdl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlUser.findAll", query = "SELECT m FROM MdlUser m")
    , @NamedQuery(name = "MdlUser.findById", query = "SELECT m FROM MdlUser m WHERE m.id = :id")
    , @NamedQuery(name = "MdlUser.findByAuth", query = "SELECT m FROM MdlUser m WHERE m.auth = :auth")
    , @NamedQuery(name = "MdlUser.findByConfirmed", query = "SELECT m FROM MdlUser m WHERE m.confirmed = :confirmed")
    , @NamedQuery(name = "MdlUser.findByPolicyagreed", query = "SELECT m FROM MdlUser m WHERE m.policyagreed = :policyagreed")
    , @NamedQuery(name = "MdlUser.findByDeleted", query = "SELECT m FROM MdlUser m WHERE m.deleted = :deleted")
    , @NamedQuery(name = "MdlUser.findBySuspended", query = "SELECT m FROM MdlUser m WHERE m.suspended = :suspended")
    , @NamedQuery(name = "MdlUser.findByMnethostid", query = "SELECT m FROM MdlUser m WHERE m.mnethostid = :mnethostid")
    , @NamedQuery(name = "MdlUser.findByUsername", query = "SELECT m FROM MdlUser m WHERE m.username = :username")
    , @NamedQuery(name = "MdlUser.findByPassword", query = "SELECT m FROM MdlUser m WHERE m.password = :password")
    , @NamedQuery(name = "MdlUser.findByIdnumber", query = "SELECT m FROM MdlUser m WHERE m.idnumber = :idnumber")
    , @NamedQuery(name = "MdlUser.findByFirstname", query = "SELECT m FROM MdlUser m WHERE m.firstname = :firstname")
    , @NamedQuery(name = "MdlUser.findByLastname", query = "SELECT m FROM MdlUser m WHERE m.lastname = :lastname")
    , @NamedQuery(name = "MdlUser.findByEmail", query = "SELECT m FROM MdlUser m WHERE m.email = :email")
    , @NamedQuery(name = "MdlUser.findByEmailstop", query = "SELECT m FROM MdlUser m WHERE m.emailstop = :emailstop")
    , @NamedQuery(name = "MdlUser.findByIcq", query = "SELECT m FROM MdlUser m WHERE m.icq = :icq")
    , @NamedQuery(name = "MdlUser.findBySkype", query = "SELECT m FROM MdlUser m WHERE m.skype = :skype")
    , @NamedQuery(name = "MdlUser.findByYahoo", query = "SELECT m FROM MdlUser m WHERE m.yahoo = :yahoo")
    , @NamedQuery(name = "MdlUser.findByAim", query = "SELECT m FROM MdlUser m WHERE m.aim = :aim")
    , @NamedQuery(name = "MdlUser.findByMsn", query = "SELECT m FROM MdlUser m WHERE m.msn = :msn")
    , @NamedQuery(name = "MdlUser.findByPhone1", query = "SELECT m FROM MdlUser m WHERE m.phone1 = :phone1")
    , @NamedQuery(name = "MdlUser.findByPhone2", query = "SELECT m FROM MdlUser m WHERE m.phone2 = :phone2")
    , @NamedQuery(name = "MdlUser.findByInstitution", query = "SELECT m FROM MdlUser m WHERE m.institution = :institution")
    , @NamedQuery(name = "MdlUser.findByDepartment", query = "SELECT m FROM MdlUser m WHERE m.department = :department")
    , @NamedQuery(name = "MdlUser.findByAddress", query = "SELECT m FROM MdlUser m WHERE m.address = :address")
    , @NamedQuery(name = "MdlUser.findByCity", query = "SELECT m FROM MdlUser m WHERE m.city = :city")
    , @NamedQuery(name = "MdlUser.findByCountry", query = "SELECT m FROM MdlUser m WHERE m.country = :country")
    , @NamedQuery(name = "MdlUser.findByLang", query = "SELECT m FROM MdlUser m WHERE m.lang = :lang")
    , @NamedQuery(name = "MdlUser.findByCalendartype", query = "SELECT m FROM MdlUser m WHERE m.calendartype = :calendartype")
    , @NamedQuery(name = "MdlUser.findByTheme", query = "SELECT m FROM MdlUser m WHERE m.theme = :theme")
    , @NamedQuery(name = "MdlUser.findByTimezone", query = "SELECT m FROM MdlUser m WHERE m.timezone = :timezone")
    , @NamedQuery(name = "MdlUser.findByFirstaccess", query = "SELECT m FROM MdlUser m WHERE m.firstaccess = :firstaccess")
    , @NamedQuery(name = "MdlUser.findByLastaccess", query = "SELECT m FROM MdlUser m WHERE m.lastaccess = :lastaccess")
    , @NamedQuery(name = "MdlUser.findByLastlogin", query = "SELECT m FROM MdlUser m WHERE m.lastlogin = :lastlogin")
    , @NamedQuery(name = "MdlUser.findByCurrentlogin", query = "SELECT m FROM MdlUser m WHERE m.currentlogin = :currentlogin")
    , @NamedQuery(name = "MdlUser.findByLastip", query = "SELECT m FROM MdlUser m WHERE m.lastip = :lastip")
    , @NamedQuery(name = "MdlUser.findBySecret", query = "SELECT m FROM MdlUser m WHERE m.secret = :secret")
    , @NamedQuery(name = "MdlUser.findByPicture", query = "SELECT m FROM MdlUser m WHERE m.picture = :picture")
    , @NamedQuery(name = "MdlUser.findByUrl", query = "SELECT m FROM MdlUser m WHERE m.url = :url")
    , @NamedQuery(name = "MdlUser.findByDescriptionformat", query = "SELECT m FROM MdlUser m WHERE m.descriptionformat = :descriptionformat")
    , @NamedQuery(name = "MdlUser.findByMailformat", query = "SELECT m FROM MdlUser m WHERE m.mailformat = :mailformat")
    , @NamedQuery(name = "MdlUser.findByMaildigest", query = "SELECT m FROM MdlUser m WHERE m.maildigest = :maildigest")
    , @NamedQuery(name = "MdlUser.findByMaildisplay", query = "SELECT m FROM MdlUser m WHERE m.maildisplay = :maildisplay")
    , @NamedQuery(name = "MdlUser.findByAutosubscribe", query = "SELECT m FROM MdlUser m WHERE m.autosubscribe = :autosubscribe")
    , @NamedQuery(name = "MdlUser.findByTrackforums", query = "SELECT m FROM MdlUser m WHERE m.trackforums = :trackforums")
    , @NamedQuery(name = "MdlUser.findByTimecreated", query = "SELECT m FROM MdlUser m WHERE m.timecreated = :timecreated")
    , @NamedQuery(name = "MdlUser.findByTimemodified", query = "SELECT m FROM MdlUser m WHERE m.timemodified = :timemodified")
    , @NamedQuery(name = "MdlUser.findByTrustbitmask", query = "SELECT m FROM MdlUser m WHERE m.trustbitmask = :trustbitmask")
    , @NamedQuery(name = "MdlUser.findByImagealt", query = "SELECT m FROM MdlUser m WHERE m.imagealt = :imagealt")
    , @NamedQuery(name = "MdlUser.findByLastnamephonetic", query = "SELECT m FROM MdlUser m WHERE m.lastnamephonetic = :lastnamephonetic")
    , @NamedQuery(name = "MdlUser.findByFirstnamephonetic", query = "SELECT m FROM MdlUser m WHERE m.firstnamephonetic = :firstnamephonetic")
    , @NamedQuery(name = "MdlUser.findByMiddlename", query = "SELECT m FROM MdlUser m WHERE m.middlename = :middlename")
    , @NamedQuery(name = "MdlUser.findByAlternatename", query = "SELECT m FROM MdlUser m WHERE m.alternatename = :alternatename")})
public class MdlUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "auth")
    private String auth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmed")
    private boolean confirmed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "policyagreed")
    private boolean policyagreed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted")
    private boolean deleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suspended")
    private boolean suspended;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mnethostid")
    private long mnethostid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "idnumber")
    private String idnumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "emailstop")
    private boolean emailstop;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "icq")
    private String icq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "skype")
    private String skype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "yahoo")
    private String yahoo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "aim")
    private String aim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "msn")
    private String msn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone1")
    private String phone1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone2")
    private String phone2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "institution")
    private String institution;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country")
    private String country;
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
    @Size(min = 1, max = 100)
    @Column(name = "timezone")
    private String timezone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firstaccess")
    private long firstaccess;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastaccess")
    private long lastaccess;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastlogin")
    private long lastlogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "currentlogin")
    private long currentlogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastip")
    private String lastip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "secret")
    private String secret;
    @Basic(optional = false)
    @NotNull
    @Column(name = "picture")
    private long picture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descriptionformat")
    private short descriptionformat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mailformat")
    private boolean mailformat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maildigest")
    private boolean maildigest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maildisplay")
    private short maildisplay;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autosubscribe")
    private boolean autosubscribe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trackforums")
    private boolean trackforums;
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
    @Column(name = "trustbitmask")
    private long trustbitmask;
    @Size(max = 255)
    @Column(name = "imagealt")
    private String imagealt;
    @Size(max = 255)
    @Column(name = "lastnamephonetic")
    private String lastnamephonetic;
    @Size(max = 255)
    @Column(name = "firstnamephonetic")
    private String firstnamephonetic;
    @Size(max = 255)
    @Column(name = "middlename")
    private String middlename;
    @Size(max = 255)
    @Column(name = "alternatename")
    private String alternatename;

    public MdlUser() {
    }

    public MdlUser(Long id) {
        this.id = id;
    }

    public MdlUser(Long id, String auth, boolean confirmed, boolean policyagreed, boolean deleted, boolean suspended, long mnethostid, String username, String password, String idnumber, String firstname, String lastname, String email, boolean emailstop, String icq, String skype, String yahoo, String aim, String msn, String phone1, String phone2, String institution, String department, String address, String city, String country, String lang, String calendartype, String theme, String timezone, long firstaccess, long lastaccess, long lastlogin, long currentlogin, String lastip, String secret, long picture, String url, short descriptionformat, boolean mailformat, boolean maildigest, short maildisplay, boolean autosubscribe, boolean trackforums, long timecreated, long timemodified, long trustbitmask) {
        this.id = id;
        this.auth = auth;
        this.confirmed = confirmed;
        this.policyagreed = policyagreed;
        this.deleted = deleted;
        this.suspended = suspended;
        this.mnethostid = mnethostid;
        this.username = username;
        this.password = password;
        this.idnumber = idnumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.emailstop = emailstop;
        this.icq = icq;
        this.skype = skype;
        this.yahoo = yahoo;
        this.aim = aim;
        this.msn = msn;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.institution = institution;
        this.department = department;
        this.address = address;
        this.city = city;
        this.country = country;
        this.lang = lang;
        this.calendartype = calendartype;
        this.theme = theme;
        this.timezone = timezone;
        this.firstaccess = firstaccess;
        this.lastaccess = lastaccess;
        this.lastlogin = lastlogin;
        this.currentlogin = currentlogin;
        this.lastip = lastip;
        this.secret = secret;
        this.picture = picture;
        this.url = url;
        this.descriptionformat = descriptionformat;
        this.mailformat = mailformat;
        this.maildigest = maildigest;
        this.maildisplay = maildisplay;
        this.autosubscribe = autosubscribe;
        this.trackforums = trackforums;
        this.timecreated = timecreated;
        this.timemodified = timemodified;
        this.trustbitmask = trustbitmask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean getPolicyagreed() {
        return policyagreed;
    }

    public void setPolicyagreed(boolean policyagreed) {
        this.policyagreed = policyagreed;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public long getMnethostid() {
        return mnethostid;
    }

    public void setMnethostid(long mnethostid) {
        this.mnethostid = mnethostid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEmailstop() {
        return emailstop;
    }

    public void setEmailstop(boolean emailstop) {
        this.emailstop = emailstop;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public long getFirstaccess() {
        return firstaccess;
    }

    public void setFirstaccess(long firstaccess) {
        this.firstaccess = firstaccess;
    }

    public long getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(long lastaccess) {
        this.lastaccess = lastaccess;
    }

    public long getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(long lastlogin) {
        this.lastlogin = lastlogin;
    }

    public long getCurrentlogin() {
        return currentlogin;
    }

    public void setCurrentlogin(long currentlogin) {
        this.currentlogin = currentlogin;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getPicture() {
        return picture;
    }

    public void setPicture(long picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getDescriptionformat() {
        return descriptionformat;
    }

    public void setDescriptionformat(short descriptionformat) {
        this.descriptionformat = descriptionformat;
    }

    public boolean getMailformat() {
        return mailformat;
    }

    public void setMailformat(boolean mailformat) {
        this.mailformat = mailformat;
    }

    public boolean getMaildigest() {
        return maildigest;
    }

    public void setMaildigest(boolean maildigest) {
        this.maildigest = maildigest;
    }

    public short getMaildisplay() {
        return maildisplay;
    }

    public void setMaildisplay(short maildisplay) {
        this.maildisplay = maildisplay;
    }

    public boolean getAutosubscribe() {
        return autosubscribe;
    }

    public void setAutosubscribe(boolean autosubscribe) {
        this.autosubscribe = autosubscribe;
    }

    public boolean getTrackforums() {
        return trackforums;
    }

    public void setTrackforums(boolean trackforums) {
        this.trackforums = trackforums;
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

    public long getTrustbitmask() {
        return trustbitmask;
    }

    public void setTrustbitmask(long trustbitmask) {
        this.trustbitmask = trustbitmask;
    }

    public String getImagealt() {
        return imagealt;
    }

    public void setImagealt(String imagealt) {
        this.imagealt = imagealt;
    }

    public String getLastnamephonetic() {
        return lastnamephonetic;
    }

    public void setLastnamephonetic(String lastnamephonetic) {
        this.lastnamephonetic = lastnamephonetic;
    }

    public String getFirstnamephonetic() {
        return firstnamephonetic;
    }

    public void setFirstnamephonetic(String firstnamephonetic) {
        this.firstnamephonetic = firstnamephonetic;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getAlternatename() {
        return alternatename;
    }

    public void setAlternatename(String alternatename) {
        this.alternatename = alternatename;
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
        if (!(object instanceof MdlUser)) {
            return false;
        }
        MdlUser other = (MdlUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlUser[ id=" + id + " ]";
    }
    
}
