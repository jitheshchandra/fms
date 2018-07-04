package com.ssrv.fms.vo.employee;

import java.sql.Date;
import java.util.List;

public class EmployeeOverTime {
	private Long id;

	private Date modifiedOn;

	private Long ModifiedBy;

	private Long shiftId;

	private Long organizationId;
	
	private Long branchId;

	private Date overTimeDate;

	private List<EmployeeOverTimeList> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}

	public List<EmployeeOverTimeList> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeOverTimeList> employees) {
		this.employees = employees;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
