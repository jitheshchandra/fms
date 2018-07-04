package com.ssrv.fms.vo.employee;

import java.sql.Date;

import com.ssrv.fms.model.employee.Employees;

public class EmployeeForm 
{
	private Long id;	
	private String empCode;	
	private String firstName;	
	private String lastName;	
	private String middleName;
	private Long designationId;	
	private Long supervisorId;	
	private Long organizationId;
	private Long branchId;
	private Date DOB;	
	private String email;
	private String phone;	
	private String address1;	
	private String address2;	
	private String address3;	
	private String city;	
	private Long stateId;	
	private Long countryId;	
	private String attendanceStatus;
	private Float noOfHourWorked;
	
	public EmployeeForm()
	{
		
	}
	public EmployeeForm(Employees model)
	{
		this.id=model.getId();
		this.firstName=model.getFirstName();
		this.lastName=model.getLastName();
		this.address1=model.getAddress1();
		//this.address2=model.getAddress2();
		//this.address3=model.getAddress3();
		this.city=model.getCity();
		this.supervisorId=model.getSupervisorId().getId();
		this.designationId=model.getDesignationId().getId();
		this.countryId=model.getCountryId().getId();
		this.stateId=model.getStateId().getId();
		//this.DOB=(Date) model.getDob();
		this.organizationId=model.getOrganizationId().getId();
		this.branchId=model.getBranchId().getId();
		this.empCode=model.getEmpCode();
		this.email=model.getEmail();
		this.phone=model.getPhone();
	}
	public Float getNoOfHourWorked() {
		return noOfHourWorked;
	}

	public void setNoOfHourWorked(Float noOfHourWorked) {
		this.noOfHourWorked = noOfHourWorked;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}


	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
}
