package com.ssrv.fms.model.user;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.organization.Organization;

@XmlRootElement(name = "UserBranchMapping")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "usersbranchmapping")
public class UserBranchMapping implements Serializable
{

	private static final long serialVersionUID = 1L;

	/*@XmlElement(name = "password")
	@Column(name = "Password")*/
	
	@XmlElement(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;
		
	@JoinColumn(name = "userId", referencedColumnName = "Id")
    @ManyToOne
	private User userId;

	@JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
    @ManyToOne
	private Organization organizationId;
	
	@JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
	private Branch branchId;
	
	@Column(name="DefaultBranch")
	private long defaultBranch;
	
	@Column(name="DefaultOrganization")
	private long defaultOrganization;

	public long getDefaultOrganization()
		{
			return defaultOrganization;
		}

	public void setDefaultOrganization(long defaultOrganization)
		{
			this.defaultOrganization = defaultOrganization;
		}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Organization getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Organization organizationId) {
		this.organizationId = organizationId;
	}

	public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public long getDefaultBranch() {
		return defaultBranch;
	}

	public void setDefaultBranch(long defaultBranch) {
		this.defaultBranch = defaultBranch;
	}
}
