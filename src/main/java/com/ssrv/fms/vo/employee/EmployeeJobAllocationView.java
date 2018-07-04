package com.ssrv.fms.vo.employee;

import java.sql.Date;

import com.ssrv.fms.model.job.Joballocations;

public class EmployeeJobAllocationView 
{
	private Long id;
	private String employeeCode;
	private String employeeName;
	private Long employeeId;
	private String employeeLastName;
	private String organizationId;
	private String branch;
	private String supervisor;
	private long suprvisorId;
	private String shift;
	private Date startDate;
	private Date endDate;	
	private long modifiedBy;	
	private Date modifiedOn;	
	private long branchId;
	private long organisationId;
	private long shiftId;
	private long designationId;
	private String mobileNo;
	

	public EmployeeJobAllocationView()
	{
		
	}
	
	public EmployeeJobAllocationView(Joballocations joballocation)
	{
		this.id=joballocation.getId();
		this.employeeCode=joballocation.getEmployeeId().getEmpCode();
		this.employeeName=joballocation.getEmployeeId().getFirstName();
		this.employeeLastName=joballocation.getEmployeeId().getLastName();
		this.branchId=joballocation.getBranchId().getId();
		this.branch=joballocation.getBranchId().getBranchName();
		this.organisationId=joballocation.getOrganizationId().getId();
		this.shift=joballocation.getShiftId().getShiftName();
		this.shiftId=joballocation.getShiftId().getId();
		this.employeeId=joballocation.getEmployeeId().getId();
		this.designationId=joballocation.getEmployeeId().getDesignationId().getId();
		this.mobileNo=joballocation.getEmployeeId().getMobile();
		//this.startDate=(Date) joballocation.getStartDate();
		//this.endDate=(Date) joballocation.getEndDate();
		//this.supervisor=joballocation.getSupervisorId();
		//this.suprvisorId=Long.parseLong(joballocation.getSupervisorId());
	}
		
	
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(long organisationId) {
		this.organisationId = organisationId;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public long getSuprvisorId() {
		return suprvisorId;
	}

	public void setSuprvisorId(long suprvisorId) {
		this.suprvisorId = suprvisorId;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
