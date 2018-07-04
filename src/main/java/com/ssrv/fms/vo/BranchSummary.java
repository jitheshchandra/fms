package com.ssrv.fms.vo;

import com.ssrv.fms.model.branch.Branch;

public class BranchSummary {

	private Long id;
	private String branchName;
	private String organization;	
	private Long organizationId;
	private String address;
	private String contactPerson;
	private String contactNumber;	
	private boolean isSelected;	
	private boolean isDefault;	
	private Long mappingId;
	public BranchSummary() {

	}
	public BranchSummary(Branch branch) {
		this.id = branch.getId();
		this.branchName = branch.getBranchName();
		this.organization = branch.getOrganization().getName();
		this.organizationId=branch.getOrganization().getId();
	}
	public Long getMappingId()
		{
			return mappingId;
		}
	public void setMappingId(Long mappingId)
		{
			this.mappingId = mappingId;
		}
	public boolean isDefault()
		{
			return isDefault;
		}
	public void setDefault(boolean isDefault)
		{
			this.isDefault = isDefault;
		}
	public boolean isSelected()
		{
			return isSelected;
		}

	public void setSelected(boolean isSelected)
		{
			this.isSelected = isSelected;
		}

	public Long getOrganizationId()
		{
			return organizationId;
		}

	public void setOrganizationId(Long organizationId)
		{
			this.organizationId = organizationId;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
