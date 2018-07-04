package com.ssrv.fms.model.branch;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.Acl;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.managedentity.ManagedEntityInstance;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "Branch")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b"),
		@NamedQuery(name = "Branch.findById", query = "SELECT b FROM Branch b WHERE b.id = :id"),
		@NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = :branchName"),
		@NamedQuery(name = "Branch.findByAddress1", query = "SELECT b FROM Branch b WHERE b.address1 = :address1"),
		@NamedQuery(name = "Branch.findByAddress2", query = "SELECT b FROM Branch b WHERE b.address2 = :address2"),
		@NamedQuery(name = "Branch.findByAddress3", query = "SELECT b FROM Branch b WHERE b.address3 = :address3"),
		@NamedQuery(name = "Branch.findByPinCode", query = "SELECT b FROM Branch b WHERE b.pinCode = :pinCode"),
		@NamedQuery(name = "Branch.findByIsDeleted", query = "SELECT b FROM Branch b WHERE b.isDeleted = :isDeleted"),
		@NamedQuery(name = "Branch.findByModifiedBy", query = "SELECT b FROM Branch b WHERE b.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "Branch.findByModifiedDate", query = "SELECT b FROM Branch b WHERE b.modifiedDate = :modifiedDate") })
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;

	@Column(name = "BranchName")
	private String branchName;

	@Column(name = "Address1")
	private String address1;
	
	@Column(name = "Address2")
	private String address2;
	
	@Column(name = "Address3")
	private String address3;
	
	@Column(name = "PinCode")
	private String pinCode;
	
	@Column(name = "IsDeleted")
	private Short isDeleted;
	
	@Column(name = "ModifiedBy")
	private BigInteger modifiedBy;
	
	@Column(name = "ModifiedDate")
	private BigInteger modifiedDate;
	
	@OneToMany(mappedBy = "branchId")
	private Collection<Branchcontact> branchcontactsCollection;
	
	@ManyToOne
	@JoinColumn(name="CountyId",referencedColumnName="Id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="StateId",referencedColumnName="Id")
	private States stateId;

	@ManyToOne
	@JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
	private Organization organization;

	/*@ManyToOne
	@JoinColumn(name = "BranchId", referencedColumnName = "Id")
	private Branch branch;*/
	
	@OneToMany(mappedBy = "branchId")
	private Collection<Shifts> shiftsCollection;
	
	@OneToMany(mappedBy = "branchId")
	private Collection<Acl> aclCollection;
	
/*	@OneToMany(mappedBy = "branchId")
	private Collection<Checklists> checklistsCollection;*/
	
	@OneToMany(mappedBy = "branchId")
	private List<ManagedEntityInstance> managedEntityInstances;

	@OneToMany(mappedBy = "branch")
	private Collection<ManagedEntityGroup> managedEntityGroups;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public BigInteger getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(BigInteger modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@XmlTransient
	public Collection<Branchcontact> getBranchcontactsCollection() {
		return branchcontactsCollection;
	}

	public void setBranchcontactsCollection(
			Collection<Branchcontact> branchcontactsCollection) {
		this.branchcontactsCollection = branchcontactsCollection;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@XmlTransient
	public Collection<Shifts> getShiftsCollection() {
		return shiftsCollection;
	}

	public void setShiftsCollection(Collection<Shifts> shiftsCollection) {
		this.shiftsCollection = shiftsCollection;
	}

	@XmlTransient
	public Collection<Acl> getAclCollection() {
		return aclCollection;
	}

	public void setAclCollection(Collection<Acl> aclCollection) {
		this.aclCollection = aclCollection;
	}

/*	@XmlTransient
	public Collection<Checklists> getChecklistsCollection() {
		return checklistsCollection;
	}

	public void setChecklistsCollection(
			Collection<Checklists> checklistsCollection) {
		this.checklistsCollection = checklistsCollection;
	}*/

	@XmlTransient
	public List<ManagedEntityInstance> getManagedEntityInstances() {
		return managedEntityInstances;
	}

	public void setManagedEntityInstances(
			List<ManagedEntityInstance> managedEntityInstances) {
		this.managedEntityInstances = managedEntityInstances;
	}

	public Collection<ManagedEntityGroup> getManagedEntityGroups() {
		return managedEntityGroups;
	}

	public void setManagedEntityGroups(
			Collection<ManagedEntityGroup> managedEntityGroups) {
		this.managedEntityGroups = managedEntityGroups;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public States getStateId() {
		return stateId;
	}

	public void setStateId(States stateId) {
		this.stateId = stateId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Branch)) {
			return false;
		}
		Branch other = (Branch) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Branches[ id=" + id + " ]";
	}

}
