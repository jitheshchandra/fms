package com.ssrv.fms.model.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ssrv.fms.model.user.User;

//@Entity
//@Table(name = "CONTACT")
public class Contact {
	
	@Id
	@Column(name = "CONTACT_ID")
	private String contactId;
	
	@Column(name = "ADDRESS_LINE_1")
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE_2")
	private String addressLine2;
	
	@Column(name = "ZIP_CODE")
	private String zipCode;
	
	@Column(name = "DISTRICT")
	private String district;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "PRIMARY_EMAIL_ID")
	private String primaryEmail;
	
	@Column(name = "SECONDARY_EMAIL_ID")
	private String secondaryEmail;
	
	@Column(name = "PRIMARY_CONTACT_NO")
	private String primaryContactNumber;
	
	@Column(name = "SECONDARY_CONTACT_NO")
	private String secondaryContactNumber;
	
	@Column(name = "EMERGENCY_CONTACT_NO")
	private String emergencyContactNumber;
	
	//@OneToOne(optional = false, mappedBy = "contact", targetEntity = User.class)
	//private User user;
	
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}
	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}
	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}
	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
