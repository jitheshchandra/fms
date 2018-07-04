package com.ssrv.fms.vo.branch;

public class BranchSummary {
	
	private Long bId;
	
	private String branchName;
	
	private String organization;
	
	private String address ;
	
	private String contactPerson;
	
	private String contactNumber;

	
	public long getbId() {
		return bId;
	}

	public void setbId(Long long1) {
		this.bId = long1;
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
