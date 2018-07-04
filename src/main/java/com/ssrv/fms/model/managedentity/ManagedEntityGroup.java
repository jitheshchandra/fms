package com.ssrv.fms.model.managedentity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.organization.Organization;


/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "ManagedEntityGroup")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "ManagedEntityGroup.findAll", query = "SELECT m FROM ManagedEntityGroup m"),
		@NamedQuery(name = "ManagedEntityGroup.findById", query = "SELECT m FROM ManagedEntityGroup m WHERE m.id = :id"),
		@NamedQuery(name = "ManagedEntityGroup.findByName", query = "SELECT m FROM ManagedEntityGroup m WHERE m.name = :name"),
		// @NamedQuery(name = "ManagedEntityGroup.findByBranch", query =
		// "SELECT m FROM ManagedEntityGroup m WHERE m.branch.id = :branchId"),
		@NamedQuery(name = "ManagedEntityGroup.findByIsDeleted", query = "SELECT m FROM ManagedEntityGroup m WHERE m.isDeleted = :isDeleted"),
		@NamedQuery(name = "ManagedEntityGroup.findByModifiedBy", query = "SELECT m FROM ManagedEntityGroup m WHERE m.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "ManagedEntityGroup.findByModifiedDate", query = "SELECT m FROM ManagedEntityGroup m WHERE m.modifiedDate = :modifiedDate") })
public class ManagedEntityGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Name")
	private String name;

	@JoinColumn(name = "ORGANIZATIONID", referencedColumnName = "ID")
	@ManyToOne
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;
	
	@Column(name="Description")
	private String description;

	@Column(name = "IsDeleted")
	private short isDeleted;
	
	@Column(name = "ModifiedBy")
	private BigInteger modifiedBy;
	
	@Column(name = "ModifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	/*@OneToMany(mappedBy = "facilityGroupId")
	private Collection<Managedentitygroupemployeemap> managedentitygroupemployeemapCollection;
	*/
	/*@OneToMany(mappedBy = "facilityGroupId")
	private Collection<MaintenaceSchedule> maintenacescheduleCollection;
	*/
	//@OneToMany
	//private Collection<ManagedEntityInstance> managedEntityInstances;
	
/*	@OneToMany(mappedBy="managedEntityGroupId")
	private Collection<Checklists> checklistId;
*/
	public ManagedEntityGroup() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

/*	@XmlTransient
	public Collection<Managedentitygroupemployeemap> getManagedentitygroupemployeemapCollection() {
		return managedentitygroupemployeemapCollection;
	}

	public void setManagedentitygroupemployeemapCollection(
			Collection<Managedentitygroupemployeemap> managedentitygroupemployeemapCollection) {
		this.managedentitygroupemployeemapCollection = managedentitygroupemployeemapCollection;
	}*/

	/*@XmlTransient
	public Collection<MaintenaceSchedule> getMaintenacescheduleCollection() {
		return maintenacescheduleCollection;
	}

	public void setMaintenaceScheduleCollection(
			Collection<MaintenaceSchedule> maintenaceScheduleCollection) {
		this.maintenacescheduleCollection = maintenacescheduleCollection;
	}
*/
/*	@XmlTransient
	public Collection<ManagedEntityInstance> getManagedEntityInstances() {
		return managedEntityInstances;
	}

	public void setManagedEntityInstances(
			Collection<ManagedEntityInstance> managedEntityInstances) {
		this.managedEntityInstances = managedEntityInstances;
	}
*/
	

/*	public Collection<Checklists> getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(Collection<Checklists> checklistId) {
		this.checklistId = checklistId;
	}*/
}
