package com.ssrv.fms.vo.Designation;

import java.util.Date;

import com.ssrv.fms.model.Designations;

public class DesignationForm {
	private long id;
	String name;
	Short isDeleted;
	Date modifiedOn;
	String modifiedBy;
	public DesignationForm()
	{
		
	}
	public DesignationForm(Designations designation)
	{
		this.id=designation.getId();
		this.name=designation.getName();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	

}
