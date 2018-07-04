package com.ssrv.fms.model.shift;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.employee.Employeeattendence;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.model.job.Jobmovements;
import com.ssrv.fms.model.managedentity.Managedentitygroupemployeemap;
import com.ssrv.fms.model.organization.Organization;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shifts.findAll", query = "SELECT s FROM Shifts s"),
    @NamedQuery(name = "Shifts.findById", query = "SELECT s FROM Shifts s WHERE s.id = :id"),
    @NamedQuery(name = "Shifts.findByShiftName", query = "SELECT s FROM Shifts s WHERE s.shiftName = :shiftName"),
    @NamedQuery(name = "Shifts.findByIsDeleted", query = "SELECT s FROM Shifts s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "Shifts.findByModifiedOn", query = "SELECT s FROM Shifts s WHERE s.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Shifts.findByModifiedBy", query = "SELECT s FROM Shifts s WHERE s.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Shifts.findByStartTime", query = "SELECT s FROM Shifts s WHERE s.startTime = :startTime"),
    @NamedQuery(name = "Shifts.findByEndTime", query = "SELECT s FROM Shifts s WHERE s.endTime = :endTime")})
public class Shifts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "ShiftName")
    private String shiftName; 
    
    @Column(name = "IsDeleted")
    private int isDeleted;
    
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    
    @Column(name = "StartTime")
    private String startTime;
    
    @Column(name = "EndTime")
    private String endTime;
    
    @OneToMany(mappedBy = "shiftId")
    private Collection<Joballocations> joballocationsCollection;
    
/*    @OneToMany(mappedBy = "shiftId")
    private Collection<Managedentitygroupemployeemap> managedentitygroupemployeemapCollection;*/
    
    @OneToMany(mappedBy = "shiftId")
    private Collection<Employeeattendence> employeeattendenceCollection;
    
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;
    
    @JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
    @ManyToOne
    private Organization organizationId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toShiftId")
    private Collection<Jobmovements> jobmovementsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromShiftId")
    private Collection<Jobmovements> jobmovementsCollection1;

    public Shifts() {
    }

    public Shifts(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public Collection<Joballocations> getJoballocationsCollection() {
        return joballocationsCollection;
    }

    public void setJoballocationsCollection(Collection<Joballocations> joballocationsCollection) {
        this.joballocationsCollection = joballocationsCollection;
    }

    /*@XmlTransient
    public Collection<Managedentitygroupemployeemap> getManagedentitygroupemployeemapCollection() {
        return managedentitygroupemployeemapCollection;
    }

    public void setManagedentitygroupemployeemapCollection(Collection<Managedentitygroupemployeemap> managedentitygroupemployeemapCollection) {
        this.managedentitygroupemployeemapCollection = managedentitygroupemployeemapCollection;
    }*/

    @XmlTransient
    public Collection<Employeeattendence> getEmployeeattendenceCollection() {
        return employeeattendenceCollection;
    }

    public void setEmployeeattendenceCollection(Collection<Employeeattendence> employeeattendenceCollection) {
        this.employeeattendenceCollection = employeeattendenceCollection;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }

    @XmlTransient
    public Collection<Jobmovements> getJobmovementsCollection() {
        return jobmovementsCollection;
    }

    public void setJobmovementsCollection(Collection<Jobmovements> jobmovementsCollection) {
        this.jobmovementsCollection = jobmovementsCollection;
    }

    @XmlTransient
    public Collection<Jobmovements> getJobmovementsCollection1() {
        return jobmovementsCollection1;
    }

    public void setJobmovementsCollection1(Collection<Jobmovements> jobmovementsCollection1) {
        this.jobmovementsCollection1 = jobmovementsCollection1;
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
        if (!(object instanceof Shifts)) {
            return false;
        }
        Shifts other = (Shifts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Shifts[ id=" + id + " ]";
    }
    
}
