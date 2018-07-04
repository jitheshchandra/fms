package com.ssrv.fms.model.job;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "joballocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Joballocations.findAll", query = "SELECT j FROM Joballocations j"),
    @NamedQuery(name = "Joballocations.findById", query = "SELECT j FROM Joballocations j WHERE j.id = :id"),
    @NamedQuery(name = "Joballocations.findBySupervisorId", query = "SELECT j FROM Joballocations j WHERE j.supervisorId = :supervisorId"),
    @NamedQuery(name = "Joballocations.findByStartDate", query = "SELECT j FROM Joballocations j WHERE j.startDate = :startDate"),
    @NamedQuery(name = "Joballocations.findByEndDate", query = "SELECT j FROM Joballocations j WHERE j.endDate = :endDate"),
    @NamedQuery(name = "Joballocations.findByModifiedOn", query = "SELECT j FROM Joballocations j WHERE j.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Joballocations.findByModifiedBy", query = "SELECT j FROM Joballocations j WHERE j.modifiedBy = :modifiedBy")})
public class Joballocations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "SupervisorId")
    private String supervisorId;
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    @JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
    @ManyToOne
    private Organization organizationId;
    
    //Changes:Adding Branch Id And Setter and getter
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;
     
    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne
    private Shifts shiftId;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne
    private Employees employeeId;
    
    public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public Joballocations() {
    }

    public Joballocations(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }

    public Shifts getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shifts shiftId) {
        this.shiftId = shiftId;
    }

    public Employees getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employees employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Joballocations)) {
            return false;
        }
        Joballocations other = (Joballocations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Joballocations[ id=" + id + " ]";
    }
    
}
