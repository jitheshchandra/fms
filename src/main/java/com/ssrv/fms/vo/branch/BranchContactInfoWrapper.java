package com.ssrv.fms.vo.branch;

import com.ssrv.fms.model.branch.Branchcontact;

public class BranchContactInfoWrapper implements BranchContactInfo {

	private String contactPerson;

	private String contactMailId;

	private String contactMobileNo;
	
	private String contactPersonDesignation;

	private long contactId;

	private long branchID;

	public BranchContactInfoWrapper(Branchcontact contact) {
		this.contactId = contact.getId();
		this.contactMailId = contact.getContactPersonMailId();
		this.contactMobileNo = contact.getContactPersonMobile();
		this.contactPerson = contact.getContactPerson();
		this.contactPersonDesignation=contact.getContactPersonDesignation();
		this.branchID = contact.getBranchId().getId();
	}

	@Override
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Override
	public String getContactMailId() {
		return contactMailId;
	}

	public void setContactMailId(String contactMailId) {
		this.contactMailId = contactMailId;
	}

	@Override
	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	@Override
	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	@Override
	public long getBranchID() {
		return branchID;
	}

	public void setBranchID(long branchID) {
		this.branchID = branchID;
	}

	@Override
	public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}

	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
	}
}
