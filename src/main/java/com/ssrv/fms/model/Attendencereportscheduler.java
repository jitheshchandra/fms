package com.ssrv.fms.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "attendencereportscheduler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendencereportscheduler.findAll", query = "SELECT a FROM Attendencereportscheduler a"),
    @NamedQuery(name = "Attendencereportscheduler.findById", query = "SELECT a FROM Attendencereportscheduler a WHERE a.id = :id"),
    @NamedQuery(name = "Attendencereportscheduler.findByOrganizationId", query = "SELECT a FROM Attendencereportscheduler a WHERE a.organizationId = :organizationId"),
    @NamedQuery(name = "Attendencereportscheduler.findByBranchId", query = "SELECT a FROM Attendencereportscheduler a WHERE a.branchId = :branchId"),
    @NamedQuery(name = "Attendencereportscheduler.findByAttendenceSchedule", query = "SELECT a FROM Attendencereportscheduler a WHERE a.attendenceSchedule = :attendenceSchedule"),
    @NamedQuery(name = "Attendencereportscheduler.findByAttendencePercentage", query = "SELECT a FROM Attendencereportscheduler a WHERE a.attendencePercentage = :attendencePercentage")})
public class Attendencereportscheduler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "OrganizationId")
    private BigInteger organizationId;
    @Column(name = "BranchId")
    private BigInteger branchId;
    @Column(name = "AttendenceSchedule")
    private Integer attendenceSchedule;
    @Column(name = "AttendencePercentage")
    private Integer attendencePercentage;

    public Attendencereportscheduler() {
    }

    public Attendencereportscheduler(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigInteger organizationId) {
        this.organizationId = organizationId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public Integer getAttendenceSchedule() {
        return attendenceSchedule;
    }

    public void setAttendenceSchedule(Integer attendenceSchedule) {
        this.attendenceSchedule = attendenceSchedule;
    }

    public Integer getAttendencePercentage() {
        return attendencePercentage;
    }

    public void setAttendencePercentage(Integer attendencePercentage) {
        this.attendencePercentage = attendencePercentage;
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
        if (!(object instanceof Attendencereportscheduler)) {
            return false;
        }
        Attendencereportscheduler other = (Attendencereportscheduler) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Attendencereportscheduler[ id=" + id + " ]";
    }
    
}
