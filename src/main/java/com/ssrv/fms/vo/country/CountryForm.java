package com.ssrv.fms.vo.country;

import java.sql.Date;

import com.ssrv.fms.model.contact.Country;

public class CountryForm {
	
	public Long id;

	String name;
	
	Short isDeleted;
	
	Date modifiedOn;
	
	String modifiedBy;
	
	public CountryForm()
	{
		
	}
	//save changes
	public CountryForm(Country modelData)
	{
		this.id=modelData.getId();
		this.name=modelData.getName();	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
