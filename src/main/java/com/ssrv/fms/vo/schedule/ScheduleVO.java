package com.ssrv.fms.vo.schedule;

import java.util.Date;

/**
 * @author JITHESH
 *
 */
public class ScheduleVO {

	public String title;
	public Date startDate;
	public Date enddate;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
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


