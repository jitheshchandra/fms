package com.ssrv.fms.vo.employee;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ssrv.fms.model.employee.Employeeattendence;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.model.managedentity.Managedentitygroupdetails;

public class EmployeeAttandenceForm {
	private long organizationId;
	private long branchId;
	private long shiftId;
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private Date attendanceDate;
	private List<EmployeeAttendanceConfiguration> employees;
	
	public EmployeeAttandenceForm()
	{}
	

	public EmployeeAttandenceForm(Employees empv)
	{
		
		this.branchId=empv.getBranchId().getId();
		this.organizationId=empv.getOrganizationId().getId();
		
		
	}
	


	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public List<EmployeeAttendanceConfiguration> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeAttendanceConfiguration> employees) {
		this.employees = employees;
	}

	public Date getAttendanceDate()
		{
			return attendanceDate;
		}

	public void setAttendanceDate(Date attendanceDate)
		{
			this.attendanceDate = attendanceDate;
		}

	
	
	

//	public void setAttendanceDate(String attendanceDate) {
//		if (attendanceDate != null) {
//			try {
//				this.attendanceDate = (Date) format.parse(attendanceDate);
//			} catch (ParseException e) {
//				// TODO: handle exception appropriately..
//				e.printStackTrace();
//			}
//		} else {
//			this.attendanceDate = null;
//		}
//	}

}
