package com.ssrv.fms.vo.employee;

import java.sql.Date;
import java.util.List;

public class EmployeeGrooming {
	private Long id;
	private Long organizationId;
	private Long branchId;
	private Long shiftId;
	private Date groomingDay;
	private Long employeeId;
	private List<EmloyeeGroomingCheckList> checkList;
	private Long modifiedBy;
	private Date ModifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGroomingDay() {
		return groomingDay;
	}

	public void setGroomingDay(Date groomingDay) {
		this.groomingDay = groomingDay;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmloyeeGroomingCheckList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<EmloyeeGroomingCheckList> checkList) {
		this.checkList = checkList;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return ModifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		ModifiedOn = modifiedOn;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

}
