package com.ssrv.fms.vo.Job;

import java.util.List;

public class EmployeeJobmovementForm {
	private Long organizationId;
	private Long branchId;
	private List<EmployeeJobMovement> employees;

	public List<EmployeeJobMovement> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeJobMovement> employees) {
		this.employees = employees;
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
}
