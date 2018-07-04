package com.ssrv.fms.vo.schedule;

import java.util.Date;
import java.util.List;

import com.ssrv.fms.model.employee.Employees;

public class EditSchedulesVO {
	
	public String Title;
	public Date StartDate;
	public Date Enddate;
	public String StartTime;
	public String EndTime;
	public String ManagedEntityGroupName;
	public String Supervisor;
	public String Organization;
	public String Branch;
	public List<Employees> employees;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEnddate() {
		return Enddate;
	}
	public void setEnddate(Date enddate) {
		Enddate = enddate;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getManagedEntityGroupName() {
		return ManagedEntityGroupName;
	}
	public void setManagedEntityGroupName(String managedEntityGroupName) {
		ManagedEntityGroupName = managedEntityGroupName;
	}
	public String getSupervisor() {
		return Supervisor;
	}
	public void setSupervisor(String supervisor) {
		Supervisor = supervisor;
	}
	public String getOrganization() {
		return Organization;
	}
	public void setOrganization(String organization) {
		Organization = organization;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public List<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
	
	

}
