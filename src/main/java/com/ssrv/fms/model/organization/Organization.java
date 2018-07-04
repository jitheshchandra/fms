package com.ssrv.fms.model.organization;

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

import com.ssrv.fms.model.Acl;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.model.job.Jobmovements;
import com.ssrv.fms.model.shift.Shifts;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "Organization")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
		@NamedQuery(name = "Organization.findById", query = "SELECT o FROM Organization o WHERE o.id = :id"),
		@NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
		@NamedQuery(name = "Organization.findByAddress1", query = "SELECT o FROM Organization o WHERE o.address1 = :address1"),
		@NamedQuery(name = "Organization.findByAddress2", query = "SELECT o FROM Organization o WHERE o.address2 = :address2"),
		@NamedQuery(name = "Organization.findByAddress3", query = "SELECT o FROM Organization o WHERE o.address3 = :address3"),
		@NamedQuery(name = "Organization.findByPinCode", query = "SELECT o FROM Organization o WHERE o.pinCode = :pinCode"),
		// @NamedQuery(name = "Organization.findByOrganizationTypeId", query =
		// "SELECT o FROM Organization o WHERE o.organizationTypeId = :organizationTypeId"),
		@NamedQuery(name = "Organization.findByIsDeleted", query = "SELECT o FROM Organization o WHERE o.isDeleted = :isDeleted"),
		@NamedQuery(name = "Organization.findByModifiedBy", query = "SELECT o FROM Organization o WHERE o.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "Organization.findByModifiedOn", query = "SELECT o FROM Organization o WHERE o.modifiedOn = :modifiedOn") })
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "Name")
	private String name;

	@Basic(optional = false)
	@Column(name = "Address1")
	private String address1;
	
	@Column(name = "Address2")
	private String address2;
	
	@Column(name = "Address3")
	private String address3;
	
	@Column(name = "PinCode")
	private String pinCode;
	
	@ManyToOne
	@JoinColumn(name="CountryId",referencedColumnName="Id")
	private Country countryId;
	
	@ManyToOne
	@JoinColumn(name="StateId",referencedColumnName="Id")
	private States stateId;
	
	@Column(name = "IsDeleted")
	private int isDeleted;
	
	@Column(name = "ModifiedBy")
	private BigInteger modifiedBy;
	
	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	@OneToMany(mappedBy = "organizationId")
	private Collection<Joballocations> joballocationsCollection;

	@JoinColumn(name = "OrganizationTypeId", referencedColumnName = "Id")
	@ManyToOne(optional = false)
	
	
	private OrganizationTypes organizationTypes;

	public void setOrganizationTypes(OrganizationTypes organizationTypes) {
		this.organizationTypes = organizationTypes;
	}

	public OrganizationTypes getOrganizationTypes() {
		return organizationTypes;
	}

	@OneToMany(mappedBy = "organizationID")
	private Collection<Organizationcontacts> organizationcontactsCollection;
	
	@OneToMany(mappedBy = "organization")
	private Collection<Branch> branches;
	
	@OneToMany(mappedBy = "organizationId")
	private Collection<Shifts> shiftsCollection;
	
	@OneToMany(mappedBy = "organizationId")
	private Collection<Acl> aclCollection;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "toOrganizationID")
	private Collection<Jobmovements> jobmovementsCollection;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fromOrganizationId")
	private Collection<Jobmovements> jobmovementsCollection1;
	/*
	@OneToMany(mappedBy = "organizationId")
	private Collection<Checklists> checklistsCollection;
	*/
	@OneToMany(mappedBy = "organizationId")
	private Collection<Employees> employeesCollection;

	public Organization() {
	}

	public Organization(Long id) {
		this.id = id;
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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	public Country getCountryId() {
		return countryId;
	}

	public void setCountryId(Country countryId) {
		this.countryId = countryId;
	}

	public States getStateId() {
		return stateId;
	}

	public void setStateId(States stateId) {
		this.stateId = stateId;
	}

	@XmlTransient
	public Collection<Joballocations> getJoballocationsCollection() {
		return joballocationsCollection;
	}

	public void setJoballocationsCollection(
			Collection<Joballocations> joballocationsCollection) {
		this.joballocationsCollection = joballocationsCollection;
	}

	@XmlTransient
	public Collection<Organizationcontacts> getOrganizationcontactsCollection() {
		return organizationcontactsCollection;
	}

	public void setOrganizationcontactsCollection(
			Collection<Organizationcontacts> organizationcontactsCollection) {
		this.organizationcontactsCollection = organizationcontactsCollection;
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

	@XmlTransient
	public Collection<Jobmovements> getJobmovementsCollection() {
		return jobmovementsCollection;
	}

	public void setJobmovementsCollection(
			Collection<Jobmovements> jobmovementsCollection) {
		this.jobmovementsCollection = jobmovementsCollection;
	}

	@XmlTransient
	public Collection<Jobmovements> getJobmovementsCollection1() {
		return jobmovementsCollection1;
	}

	public void setJobmovementsCollection1(
			Collection<Jobmovements> jobmovementsCollection1) {
		this.jobmovementsCollection1 = jobmovementsCollection1;
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
	public Collection<Employees> getEmployeesCollection() {
		return employeesCollection;
	}

	public void setEmployeesCollection(Collection<Employees> employeesCollection) {
		this.employeesCollection = employeesCollection;
	}

	public void setBranches(Collection<Branch> branches) {
		this.branches = branches;
	}

	public Collection<Branch> getBranches() {
		return branches;
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
		if (!(object instanceof Organization)) {
			return false;
		}
		Organization other = (Organization) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Organization[ id=" + id + " ]";
	}

}
