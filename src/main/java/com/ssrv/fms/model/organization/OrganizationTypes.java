package com.ssrv.fms.model.organization;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "OrganizationType")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OrganizationTypes.findAll", query = "SELECT o FROM OrganizationTypes o"),
		@NamedQuery(name = "OrganizationTypes.findById", query = "SELECT o FROM OrganizationTypes o WHERE o.id = :id"),
		@NamedQuery(name = "OrganizationTypes.findByName", query = "SELECT o FROM OrganizationTypes o WHERE o.name = :name"),
		@NamedQuery(name = "OrganizationTypes.findByCode", query = "SELECT o FROM OrganizationTypes o WHERE o.code = :code"),
		@NamedQuery(name = "OrganizationTypes.findByIsDeleted", query = "SELECT o FROM OrganizationTypes o WHERE o.isDeleted = :isDeleted"),
		@NamedQuery(name = "OrganizationTypes.findByModifiedBy", query = "SELECT o FROM OrganizationTypes o WHERE o.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "OrganizationTypes.findByModifiedOn", query = "SELECT o FROM OrganizationTypes o WHERE o.modifiedOn = :modifiedOn") })
public class OrganizationTypes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Code")
	private String code;
	@Column(name = "IsDeleted")
	private Short isDeleted;
	@Column(name = "ModifiedBy")
	private BigInteger modifiedBy;
	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationTypes")
	private Collection<Organization> organizationCollection;

	public OrganizationTypes() {
	}

	public OrganizationTypes(Long id) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@XmlTransient
	public Collection<Organization> getOrganizationCollection() {
		return organizationCollection;
	}

	public void setOrganizationCollection(
			Collection<Organization> organizationCollection) {
		this.organizationCollection = organizationCollection;
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
		if (!(object instanceof OrganizationTypes)) {
			return false;
		}
		OrganizationTypes other = (OrganizationTypes) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Organizationtypes[ id=" + id + " ]";
	}

}
