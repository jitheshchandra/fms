package com.ssrv.fms.model.schedule;

import java.io.Serializable;
import java.math.BigInteger;
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

import com.ssrv.fms.model.managedentity.ManagedEntityGroup;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "maintenanceschedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenaceSchedule.findAll", query = "SELECT m FROM MaintenaceSchedule m"),
    @NamedQuery(name = "MaintenaceSchedule.findById", query = "SELECT m FROM MaintenaceSchedule m WHERE m.id = :id"),
    @NamedQuery(name = "MaintenaceSchedule.findByStartTime", query = "SELECT m FROM MaintenaceSchedule m WHERE m.startTime = :startTime"),
    @NamedQuery(name = "MaintenaceSchedule.findByEndTime", query = "SELECT m FROM MaintenaceSchedule m WHERE m.endTime = :endTime"),
    @NamedQuery(name = "MaintenaceSchedule.findByRecurranceRule", query = "SELECT m FROM MaintenaceSchedule m WHERE m.recurranceRule = :recurranceRule"),
    @NamedQuery(name = "MaintenaceSchedule.findByCheckListId", query = "SELECT m FROM MaintenaceSchedule m WHERE m.checkListId = :checkListId")})
    /*@NamedQuery(name = "MaintenaceSchedule.findByOrganization", query = "SELECT m FROM MaintenaceSchedule m WHERE m.Organization = :Organization"),
    @NamedQuery(name = "MaintenaceSchedule.findByBranch", query = "SELECT m FROM MaintenaceSchedule m WHERE m.Branch = :Branch")})*/
public class MaintenaceSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "StartTime")
    private String startTime;
    
    @Column(name = "EndTime")
    private String endTime;
    
    @Column(name = "RecurranceRule")
    private String recurranceRule;
    
    @Column(name = "CheckListId")
    private BigInteger checkListId;
    
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "Title")
    private String Title;
    
   

	@Column(name = "OrgId")
    private String Organization;
    
    @Column(name = "BranchId")
    private String Branch;
    
    @Column(name = "EmpCode")
    private String empCode;
    
   
    public String getTitle() {
		return Title;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setTitle(String title) {
		Title = title;
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

	public MaintenaceSchedule() {
    }

    public MaintenaceSchedule(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRecurranceRule() {
        return recurranceRule;
    }

    public void setRecurranceRule(String recurranceRule) {
        this.recurranceRule = recurranceRule;
    }

    public BigInteger getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(BigInteger checkListId) {
        this.checkListId = checkListId;
    }
    public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String Organization) {
		this.Organization= Organization;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String Branch) {
		this.Branch = Branch;
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
        if (!(object instanceof MaintenaceSchedule)) {
            return false;
        }
        MaintenaceSchedule other = (MaintenaceSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Maintenaceschedule[ id=" + id + " ]";
    }
    
}
