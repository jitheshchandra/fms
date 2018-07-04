package com.ssrv.fms.vo.branch;

public class BranchContactForm
{
	
	public String contactPerson;
	
	public String contactMailId;
	
	public String contactMobileNo;
	
	public String contactPersonDesignation;
	
	public long contactId;

	public long BranchID;
	
	public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}

	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactMailId() {
		return contactMailId;
	}

	public void setContactMailId(String contactMailId) {
		this.contactMailId = contactMailId;
	}

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public long getBranchID() {
		return BranchID;
	}

	public void setBranchID(long branchID) {
		BranchID = branchID;
	}
}
