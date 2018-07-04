package com.ssrv.fms.service.employee.intf;

import java.util.Date;
import java.util.List;

import com.ssrv.fms.model.Groomings;
import com.ssrv.fms.model.employee.Employeeattendence;
import com.ssrv.fms.model.employee.Employeeovertime;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.employee.Employeesfamilydetails;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.vo.Job.EmployeeJobAllocationForm;
import com.ssrv.fms.vo.Job.EmployeeJobmovementForm;
import com.ssrv.fms.vo.employee.EmployeeAttandenceForm;
import com.ssrv.fms.vo.employee.EmployeeFamilyDetailsForm;
import com.ssrv.fms.vo.employee.EmployeeForm;
import com.ssrv.fms.vo.employee.EmployeeGrooming;
import com.ssrv.fms.vo.employee.EmployeeJobAllocationView;
import com.ssrv.fms.vo.employee.EmployeeOverTime;
import com.ssrv.fms.vo.employee.EmployeeRelationSummaryView;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;

public interface EmployeeService {

	/*** Employee Section ***/

	public List<EmployeeSummaryView> getallEmployeesSummary();

	public Employees saveEmployee(EmployeeForm form);

	public String deleteEmployee(long id);

	public List<Employees> getallEmployees();

	public Employees getEmployeebyId(Long eid);

	public void updateEmployee(EmployeeForm form);

	public List<EmployeeSummaryView> getEmployeesByOrganization(long orgId);

	public List<Employees> getAllEmployeeByOrgId(Long orgIds);

	public List<Employees> getAllEmployeeByOrgIdAndBranchId(
			long organizationId, long branchId);

	/*** Employee Job-Allocation ***/

	// Changes
	public List<EmployeeJobAllocationView> getEmployeesByOrganization1(
			long orgId);

	public List<EmployeeSummaryView> getUnAllocatedEmployees();

	public List<Joballocations> getAllEmployeeByOrgsIdAndBranchId(
			long organozationId, long branchId);

	public void saveEmployeeJobAllocation(EmployeeJobAllocationForm form);

	/*** Employee Attendance Section ***/

	public void saveEmployeeAttendance(EmployeeAttandenceForm attandenceForm);

	public List<Employeeattendence> gettingEmployeeAttendanceOnDate(
			EmployeeAttandenceForm formData);

	public void saveEmergencyEmployee(EmployeeForm emplDetailsForm,
			EmployeeJobAllocationView jobAllocationForm);

	/*** Generic method ***/

	public List<Employees> getEmployeeByOrgIdBranchIdAndShiftId(long orgId,long branchId, long shiftId,Date attendanceDate); //for attendance only ****

	public List<Joballocations> getEmployeeByOrgAndShift(long orgId,long branchId, long shiftId, Date date);

	/*** Employee Job Movement ***/

	public void saveEmployeeJobMovement(EmployeeJobmovementForm form);

	public List<Joballocations> getEmplForJobMovementByOrgAndBrch(
			long organozationId, long branchId);

	/*** Employee Grooming ***/

	public List<Groomings> getGroomingByGroomingDate(EmployeeGrooming form);

	public void saveEmployeeGrooming(EmployeeGrooming form);

	public boolean saveEmployeeGroomingByRestApi(Long employeeId,
			Long checkListId);

	/*** Employee Relation Section ***/

	public List<EmployeeRelationSummaryView> getAllEmployeeRelationByEmpId(
			long id);

	public void saveEmployeeRelation(EmployeeFamilyDetailsForm form);

	public Employeesfamilydetails getEmpRelationById(long id);

	public void updateEmployeeRelation(EmployeeFamilyDetailsForm form);

	public String deleteEmployeeRelation(long id);

	/*** Employee Over Time ***/

	public void saveEmployeeOverTime(EmployeeOverTime overTimeForm);

	public List<Employeeovertime> getEmployeeOverTimeListByDate(
			EmployeeOverTime overtimeForm);

}
