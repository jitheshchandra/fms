package com.ssrv.fms.vo.employee;

public class EmployeeAttendanceConfiguration {
	private long attendenceId;

	private long employeeId;

	private String employeeName;

	private String status;

	public long getAttendenceId() {
		return attendenceId;
	}

	public void setAttendenceId(long attendenceId) {
		this.attendenceId = attendenceId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
