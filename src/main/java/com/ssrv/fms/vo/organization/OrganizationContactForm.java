package com.ssrv.fms.vo.organization;


public class OrganizationContactForm 

{
	private String contactPerson;	
	private String contactMailId;	
	private String contactMobileNo;	
	private String contactPersonDesignation;	
	private long contactId;
	private long orgId;
	
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public long getContactId() {
		return contactId;
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
	public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}
	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
	}
	
}
