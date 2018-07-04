package com.ssrv.fms.vo.schedule;

import java.util.Date;
import java.util.List;

import com.ssrv.fms.model.employee.Employees;

public class EditScheduleForm {
	public long sid;
	public String title;
	public String startDate;
	public String enddate;
	public String startTime;
	public String endTime;
	public String managedEntityGroupName;
	public String supervisor;
	public String organization;
	public String branch;
	public String empCode;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getManagedEntityGroupName() {
		return managedEntityGroupName;
	}
	public void setManagedEntityGroupName(String managedEntityGroupName) {
		this.managedEntityGroupName = managedEntityGroupName;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	

}
