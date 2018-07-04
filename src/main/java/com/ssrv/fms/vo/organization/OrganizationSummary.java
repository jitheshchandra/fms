package com.ssrv.fms.vo.organization;

public class OrganizationSummary {
	
	
	private long orgId;
	
	private String orgName;
		
	private String orgAddress;

    private String orgContactPerson;
	
	private String orgContactNumber;

	private String orgType;
	
	private boolean isSelected;
	
	private boolean isDefault;

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

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getOrgContactPerson() {
		return orgContactPerson;
	}

	public void setOrgContactPerson(String orgContactPerson) {
		this.orgContactPerson = orgContactPerson;
	}

	public String getOrgContactNumber() {
		return orgContactNumber;
	}

	public void setOrgContactNumber(String orgContactNumber) {
		this.orgContactNumber = orgContactNumber;
	}

		
	
	
	 

}
