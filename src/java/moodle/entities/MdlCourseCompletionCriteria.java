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
@Table(name = "mdl_course_completion_criteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdlCourseCompletionCriteria.findAll", query = "SELECT m FROM MdlCourseCompletionCriteria m")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findById", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.id = :id")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByCourse", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.course = :course")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByCriteriatype", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.criteriatype = :criteriatype")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByModule", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.module = :module")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByModuleinstance", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.moduleinstance = :moduleinstance")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByCourseinstance", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.courseinstance = :courseinstance")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByEnrolperiod", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.enrolperiod = :enrolperiod")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByTimeend", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.timeend = :timeend")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByGradepass", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.gradepass = :gradepass")
    , @NamedQuery(name = "MdlCourseCompletionCriteria.findByRole", query = "SELECT m FROM MdlCourseCompletionCriteria m WHERE m.role = :role")})
public class MdlCourseCompletionCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "course")
    private long course;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criteriatype")
    private long criteriatype;
    @Size(max = 100)
    @Column(name = "module")
    private String module;
    @Column(name = "moduleinstance")
    private BigInteger moduleinstance;
    @Column(name = "courseinstance")
    private BigInteger courseinstance;
    @Column(name = "enrolperiod")
    private BigInteger enrolperiod;
    @Column(name = "timeend")
    private BigInteger timeend;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gradepass")
    private BigDecimal gradepass;
    @Column(name = "role")
    private BigInteger role;

    public MdlCourseCompletionCriteria() {
    }

    public MdlCourseCompletionCriteria(Long id) {
        this.id = id;
    }

    public MdlCourseCompletionCriteria(Long id, long course, long criteriatype) {
        this.id = id;
        this.course = course;
        this.criteriatype = criteriatype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public long getCriteriatype() {
        return criteriatype;
    }

    public void setCriteriatype(long criteriatype) {
        this.criteriatype = criteriatype;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public BigInteger getModuleinstance() {
        return moduleinstance;
    }

    public void setModuleinstance(BigInteger moduleinstance) {
        this.moduleinstance = moduleinstance;
    }

    public BigInteger getCourseinstance() {
        return courseinstance;
    }

    public void setCourseinstance(BigInteger courseinstance) {
        this.courseinstance = courseinstance;
    }

    public BigInteger getEnrolperiod() {
        return enrolperiod;
    }

    public void setEnrolperiod(BigInteger enrolperiod) {
        this.enrolperiod = enrolperiod;
    }

    public BigInteger getTimeend() {
        return timeend;
    }

    public void setTimeend(BigInteger timeend) {
        this.timeend = timeend;
    }

    public BigDecimal getGradepass() {
        return gradepass;
    }

    public void setGradepass(BigDecimal gradepass) {
        this.gradepass = gradepass;
    }

    public BigInteger getRole() {
        return role;
    }

    public void setRole(BigInteger role) {
        this.role = role;
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
        if (!(object instanceof MdlCourseCompletionCriteria)) {
            return false;
        }
        MdlCourseCompletionCriteria other = (MdlCourseCompletionCriteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "moodle.entities.MdlCourseCompletionCriteria[ id=" + id + " ]";
    }
    
}
