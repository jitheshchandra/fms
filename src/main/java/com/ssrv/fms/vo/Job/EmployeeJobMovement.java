package com.ssrv.fms.vo.Job;

import java.util.Date;

public class EmployeeJobMovement {
	private long id;
	private long employeeId;
	private long fromOrganizationId;
	private long toOrganizationId;
	private long fromShiftId;
	private long toShiftId;
	private long fromDesignation;
	private long toDesignation;
	private String remarks;
	private Date modifiedBy;
	private Date modifiedOn;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromOrganizationId() {
		return fromOrganizationId;
	}

	public void setFromOrganizationId(long fromOrganizationId) {
		this.fromOrganizationId = fromOrganizationId;
	}

	public long getToOrganizationId() {
		return toOrganizationId;
	}

	public void setToOrganizationId(long toOrganizationId) {
		this.toOrganizationId = toOrganizationId;
	}

	public long getFromShiftId() {
		return fromShiftId;
	}

	public void setFromShiftId(long fromShiftId) {
		this.fromShiftId = fromShiftId;
	}

	public long getToShiftId() {
		return toShiftId;
	}

	public void setToShiftId(long toShiftId) {
		this.toShiftId = toShiftId;
	}

	public long getFromDesignation() {
		return fromDesignation;
	}

	public void setFromDesignation(long fromDesignation) {
		this.fromDesignation = fromDesignation;
	}

	public long getToDesignation() {
		return toDesignation;
	}

	public void setToDesignation(long toDesignation) {
		this.toDesignation = toDesignation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
}
