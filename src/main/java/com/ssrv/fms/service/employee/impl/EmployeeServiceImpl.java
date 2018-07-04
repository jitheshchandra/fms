package com.ssrv.fms.service.employee.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.Groomings;
import com.ssrv.fms.model.Relationships;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.employee.Employeeattendence;
import com.ssrv.fms.model.employee.Employeeovertime;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.employee.Employeesfamilydetails;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.model.job.Jobmovements;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;
import com.ssrv.fms.service.employee.intf.EmployeeService;
import com.ssrv.fms.vo.Job.EmployeeJobAllocation;
import com.ssrv.fms.vo.Job.EmployeeJobAllocationForm;
import com.ssrv.fms.vo.Job.EmployeeJobMovement;
import com.ssrv.fms.vo.Job.EmployeeJobmovementForm;
import com.ssrv.fms.vo.employee.EmloyeeGroomingCheckList;
import com.ssrv.fms.vo.employee.EmployeeAttandenceForm;
import com.ssrv.fms.vo.employee.EmployeeAttendanceConfiguration;
import com.ssrv.fms.vo.employee.EmployeeFamilyDetailsForm;
import com.ssrv.fms.vo.employee.EmployeeForm;
import com.ssrv.fms.vo.employee.EmployeeGrooming;
import com.ssrv.fms.vo.employee.EmployeeJobAllocationView;
import com.ssrv.fms.vo.employee.EmployeeOverTime;
import com.ssrv.fms.vo.employee.EmployeeOverTimeList;
import com.ssrv.fms.vo.employee.EmployeeRelationSummaryView;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;

@Service
@Lazy
public class EmployeeServiceImpl implements EmployeeService
	{
		@PersistenceContext
		private EntityManager entityManager;

		/*** Employee Section ***/

		//Get employee with employee-summary-vo form
		public List<EmployeeSummaryView> getallEmployeesSummary()
			{
				List<EmployeeSummaryView> empSummary = new ArrayList<EmployeeSummaryView>();
				@SuppressWarnings("unchecked")
				List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E where E.isDeleted=0").getResultList();
				for (Employees emp : employees)
					{
						EmployeeSummaryView empView = new EmployeeSummaryView();
						empView.setId(emp.getId());
						empView.setEmpCode(emp.getEmpCode());
						empView.setEmpFirstName(emp.getFirstName());
						empView.setEmpDOB("121312");
						Designations designation = emp.getDesignationId();
						empView.setEmpDesignation(designation.getName());
						Employees supervisor = emp.getSupervisorId();
						empView.setEmpSupervisor(supervisor.getFirstName());
						empSummary.add(empView);
					}
				return empSummary;
			}

		//Save Employee
		@Transactional
		public Employees saveEmployee(EmployeeForm form)
			{
				Employees employee = new Employees();
				employee.setAddress1(form.getAddress1());
				employee.setAddress2(form.getAddress2());
				employee.setAddress3(form.getAddress3());
				employee.setCity(form.getCity());
				Country country = entityManager.find(Country.class, form.getCountryId());
	      		employee.setCountryId(country);
	      		States state = entityManager.find(States.class, form.getStateId());
	      		employee.setStateId(state);
				Designations designation = entityManager.find(Designations.class, form.getDesignationId());
				employee.setDesignationId(designation);
				employee.setDob(form.getDOB());
     			employee.setEmail(form.getEmail());
				employee.setEmpCode(form.getEmpCode());
				employee.setFirstName(form.getFirstName());
				employee.setLastName(form.getLastName());
				employee.setMiddleName(form.getMiddleName());
				employee.setIsDeleted(0);
				employee.setMobile(form.getPhone());
				
				Organization organization = entityManager.find(Organization.class, form.getOrganizationId());
				employee.setOrganizationId(organization);
				Branch branch=entityManager.find(Branch.class, form.getBranchId());
				employee.setBranchId(branch);
				Employees supervisor = entityManager.find(Employees.class, form.getSupervisorId());
				employee.setSupervisorId(supervisor);
				entityManager.persist(employee);
				return employee;
			}

		//Get all employee with employee model
		public List<Employees> getallEmployees()
			{
				@SuppressWarnings("unchecked")
				List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E where E.isDeleted=0").getResultList();
				return employees;
			}

		//Get employee by employee-id
		public Employees getEmployeebyId(Long eid)
			{
				Employees employee = entityManager.find(Employees.class, eid);
				return employee;
			}

		//Update employee
		@Override
		@Transactional
		public void updateEmployee(EmployeeForm form)
			{
				Employees employee = entityManager.find(Employees.class, form.getId());
				employee.setAddress1(form.getAddress1());
				employee.setAddress2(form.getAddress2());
				employee.setAddress3(form.getAddress3());
				employee.setCity(form.getCity());
				States state = entityManager.find(States.class, form.getStateId());
			    employee.setStateId(state);
				Country country = entityManager.find(Country.class, form.getCountryId());
			    employee.setCountryId(country);
				Designations designation = entityManager.find(Designations.class, form.getDesignationId());
				employee.setDesignationId(designation);
				employee.setDob(form.getDOB());
				employee.setEmail(form.getEmail());
				employee.setEmpCode(form.getEmpCode());
				employee.setFirstName(form.getFirstName());
				employee.setLastName(form.getLastName());
				employee.setMiddleName(form.getMiddleName());
				employee.setMobile(form.getPhone());
				Organization organization = entityManager.find(Organization.class, form.getOrganizationId());
				employee.setOrganizationId(organization);
				Employees supervisor = entityManager.find(Employees.class, form.getSupervisorId());
				employee.setSupervisorId(supervisor);
			}

		/*** Employee Job Allocation Section ***/

		//Get all employee by organization **********
		@Override
		public List<EmployeeSummaryView> getEmployeesByOrganization(long orgId)
			{
			List<EmployeeSummaryView> empSummary = new ArrayList<EmployeeSummaryView>();
			/*if(orgId==null){
				return empSummary;
			}*/
				List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E Where E.organizationId=" + orgId+" and E.isDeleted=0").getResultList();
				for (Employees emp : employees)
				{
					EmployeeSummaryView empView = new EmployeeSummaryView();
					empView.setId(emp.getId());
					empView.setEmpCode(emp.getEmpCode());
					empView.setEmpFirstName(emp.getFirstName());
					empView.setEmpDOB("121312");
					Designations designation = emp.getDesignationId();
					empView.setEmpDesignation(designation.getName());
					Employees supervisor = emp.getSupervisorId();
					empView.setEmpSupervisor(supervisor.getFirstName());
					empSummary.add(empView);
				}
			return empSummary;
		
			}

		/*** Employee job-allocation***/

		//Get job-allocation by organization
		public List<EmployeeJobAllocationView> getEmployeesByOrganization1(long orgId)
			{
				List<EmployeeJobAllocationView> empJobAllocation = new ArrayList<EmployeeJobAllocationView>();
				@SuppressWarnings("unchecked")
				List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E Where E.organizationId=" + orgId+" and E.isDeleted=0").getResultList();
				for (Employees emp : employees)
					{
						EmployeeJobAllocationView empView = new EmployeeJobAllocationView();
						empView.setEmployeeCode(emp.getEmpCode());
						empView.setEmployeeName(emp.getFirstName());
						empView.setOrganizationId(emp.getOrganizationId().getName());
						empJobAllocation.add(empView);
					}
			return empJobAllocation;
			}

		//Save Job-Allocation
		@Override
		@Transactional
		public void saveEmployeeJobAllocation(EmployeeJobAllocationForm form) {
			List<EmployeeJobAllocation> listOfEmployee = form.getJobAlloc();
			for(EmployeeJobAllocation list:listOfEmployee)
			{
				if(list!=null)
				{
					if(list.getOrganizationId()!=null&&list.getBranchId()!=null&&list.getShiftId()!=null)
					{
						Joballocations model=new Joballocations();
						model.setEmployeeId(entityManager.find(Employees.class, list.getEmployeeId()));
						model.setBranchId(entityManager.find(Branch.class, list.getBranchId()));
						model.setOrganizationId(entityManager.find(Organization.class, list.getOrganizationId()));
						model.setSupervisorId(list.getSupervisorId().toString());
						model.setShiftId(entityManager.find(Shifts.class, list.getShiftId()));
						model.setStartDate(list.getStartDate());
						model.setModifiedOn(new Date());
						entityManager.persist(model);
					}
				}
			}
		}
		
		/*** Temporary employee ***/
		
		//Save temporary employee
		@Override
		@Transactional
		public void saveEmergencyEmployee(EmployeeForm emplDetailsForm, EmployeeJobAllocationView jobAllocationForm)
			{
			    //Save employee
			    Employees addEmployee = saveEmployee(emplDetailsForm);
			    //Save job-allocation
			    saveTemporaryJobAllocation(jobAllocationForm,addEmployee);
			}
		
		//Save temporary employee job-allocation
		private void saveTemporaryJobAllocation(EmployeeJobAllocationView jobAllocationForm,Employees employee)
		{
			if(employee!=null)
			{
			Joballocations jobAllocation = new Joballocations();
			jobAllocation.setEmployeeId(employee);
			Branch branchId = entityManager.find(Branch.class, jobAllocationForm.getBranchId());
			jobAllocation.setBranchId(branchId);
			Organization organisationId = entityManager.find(Organization.class, jobAllocationForm.getOrganisationId());
			jobAllocation.setOrganizationId(organisationId);
			Shifts shiftId = entityManager.find(Shifts.class, jobAllocationForm.getShiftId());
			jobAllocation.setShiftId(shiftId);
			//need to implement 
			//jobAllocation.setStartDate(jobAllocationForm.getStartDate());
			jobAllocation.setStartDate(new Date());
			jobAllocation.setModifiedBy(jobAllocationForm.getModifiedOn());
			jobAllocation.setModifiedOn(new Date());
			jobAllocationForm.setSupervisor(employee.getSupervisorId().getFirstName());
			entityManager.persist(jobAllocation);
			}
		}
			
	    /*** Employee Job movement ***/
		
		//Saving employee job-movement
		//Don`t delete comment lines needed for next step
		@Transactional
		@Override
		public void saveEmployeeJobMovement(EmployeeJobmovementForm form)
			{
				List<EmployeeJobMovement> employeeJobMovementList = form.getEmployees();
				for(EmployeeJobMovement employeeJobMovement:employeeJobMovementList)
					{  
						try{
						//Updating Employee table	
						Employees employeeId = entityManager.find(Employees.class, employeeJobMovement.getEmployeeId());
						Organization fromOrganizationId = employeeId.getOrganizationId();
						Organization toOrganization=entityManager.find(Organization.class, form.getOrganizationId());
						Designations toDesignationId = entityManager.find(Designations.class, employeeJobMovement.getToDesignation());
						Designations fromDesignationId = entityManager.find(Designations.class, employeeJobMovement.getFromDesignation());
						//Shifts fromShiftId = entityManager.find(Shifts.class, employeeJobMovement.getFromShiftId());
						Shifts toShiftId = entityManager.find(Shifts.class, employeeJobMovement.getToShiftId());
						
						//Update Employee table
						employeeId.setDesignationId(toDesignationId);
						employeeId.setOrganizationId(toOrganization);
							
						//Update Job allocation table
						if(employeeJobMovement.getToShiftId()!=employeeJobMovement.getFromShiftId())
						{
						updateJobAllocattionByJobMovement(employeeJobMovement,form);
						}
						
						//Updating Job Movement table
						@SuppressWarnings("unchecked")
						List<Jobmovements> ifIsPresent = entityManager.createQuery("SELECT m FROM Jobmovements m WHERE m.employeeId="+employeeJobMovement.getEmployeeId()+"AND m.modifiedOn=:date")
						.setParameter("date",new Date(),TemporalType.DATE)
						.getResultList();	
						if(!ifIsPresent.isEmpty())
						{	
						Jobmovements jobMovement = (Jobmovements) entityManager.createQuery("SELECT m FROM Jobmovements m WHERE m.employeeId="+employeeJobMovement.getEmployeeId()).getSingleResult();
						jobMovement.setEmployeeId(employeeId);
						jobMovement.setFromOrganizationId(fromOrganizationId);		
						jobMovement.setToOrganizationID(toOrganization);	
						//Need to get from-shift
						jobMovement.setFromShiftId(toShiftId);		
						jobMovement.setToShiftId(toShiftId);
			            jobMovement.setFromDesignationId(fromDesignationId);    
			            jobMovement.setToDesignationId(toDesignationId);
			            jobMovement.setRemarks(employeeJobMovement.getRemarks());
			            jobMovement.setModifiedOn(new Date());       
							}
						else
							{
								Jobmovements jobMovement=new Jobmovements();		
								jobMovement.setEmployeeId(employeeId);
								jobMovement.setFromOrganizationId(fromOrganizationId);		
								jobMovement.setToOrganizationID(toOrganization);
								//Need to get from-shift
								jobMovement.setFromShiftId(toShiftId);	
								jobMovement.setToShiftId(toShiftId);
					            jobMovement.setFromDesignationId(fromDesignationId);					      
					            jobMovement.setToDesignationId(toDesignationId);
					            jobMovement.setRemarks(employeeJobMovement.getRemarks());
					            jobMovement.setModifiedOn(new Date());
					            entityManager.persist(jobMovement);   
							}
					}			
				catch(Exception e)
					{
						e.printStackTrace();
					}
					}	
			}
		
		//Update job-allocation by job-movement
		@Transactional
		private void updateJobAllocattionByJobMovement(EmployeeJobMovement employeeJobMovement,EmployeeJobmovementForm jobMovemnt)
		{
			try{
				//Need to get from-shift 
				  //Update Job-allocation
			      @SuppressWarnings("unchecked")
				List<Joballocations> getAllocationLists = entityManager.createQuery("SELECT j FROM Joballocations j WHERE j.organizationId="+jobMovemnt.getOrganizationId()+"AND j.shiftId="+employeeJobMovement.getToShiftId()+"AND j.branchId="+jobMovemnt.getBranchId()+"AND j.employeeId="+employeeJobMovement.getEmployeeId()+"AND j.endDate IS NULL").getResultList();
			      if(!getAllocationLists.isEmpty())
			      {
			    	  Joballocations jobAllocation = (Joballocations) entityManager.createQuery("SELECT j FROM Joballocations j WHERE j.organizationId="+jobMovemnt.getOrganizationId()+"AND j.shiftId="+employeeJobMovement.getToShiftId()+"AND j.branchId="+jobMovemnt.getBranchId()+"AND j.employeeId="+employeeJobMovement.getEmployeeId()+"AND j.endDate IS NULL").getSingleResult();
			    	  jobAllocation.setEndDate(new Date());
			    	  jobAllocation.setModifiedOn(new Date());
			    	  entityManager.merge(jobAllocation);
			      }		      
			      //Creating new tuple 
			      else{
			      Joballocations newJobAllocation=new Joballocations();
			      newJobAllocation.setEmployeeId(entityManager.find(Employees.class, employeeJobMovement.getEmployeeId()));
			      newJobAllocation.setBranchId(entityManager.find(Branch.class,jobMovemnt.getBranchId()));
			      newJobAllocation.setOrganizationId(entityManager.find(Organization.class,jobMovemnt.getOrganizationId()));
			      newJobAllocation.setShiftId(entityManager.find(Shifts.class , employeeJobMovement.getToShiftId()));
			      newJobAllocation.setStartDate(new Date());
			      newJobAllocation.setModifiedOn(new Date());
			      entityManager.persist(newJobAllocation);
			      }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		//Get employee from job-allocation by organization and branch
		@Override
		public List<Joballocations> getEmplForJobMovementByOrgAndBrch(long organozationId, long branchId)
			{
				@SuppressWarnings("unchecked")
				List<Joballocations> jobAllocation = entityManager.createQuery("SELECT j FROM Joballocations j WHERE j.organizationId="+organozationId+"AND j.branchId="+branchId+"AND j.endDate IS NULL").getResultList();
				return jobAllocation;
			}

		//Getting employeeList based on attendance date
		@Override
		public List<Employeeattendence> gettingEmployeeAttendanceOnDate(EmployeeAttandenceForm formData) {
			@SuppressWarnings("unchecked")
			List<Employeeattendence> employeeList=entityManager.createQuery("SELECT e FROM Employeeattendence e WHERE e.shiftId="+formData.getShiftId()+"AND e.attendenceDate =:a")
					.setParameter("a",formData.getAttendanceDate(), TemporalType.TIMESTAMP)
					.getResultList();
			return employeeList;
		}
	
		//Get employee from job-allocation by organization and shift 
		@Override
		public List<Joballocations> getEmployeeByOrgAndShift(long orgId,long brchId,long shiftId,Date date)
			{
				String query="SELECT j FROM Joballocations j WHERE j.organizationId=" + orgId +"AND j.branchId="+ brchId +" AND j.shiftId=" + shiftId +" AND j.startDate <= :attendanceDate AND (:attendanceDate <= j.endDate OR j.endDate IS NULL)";
				@SuppressWarnings("unchecked")
				List<Joballocations> employeeList = entityManager.createQuery(query).setParameter("attendanceDate",date, TemporalType.TIMESTAMP).getResultList();
				return employeeList;
			}
		
		/**** Employee Attendance Section ****/

		//Get employee by organization and shift for employee attendance this is different table 
		@Override
		public List<Employees> getEmployeeByOrgIdBranchIdAndShiftId(long orgId, long branchId,long shiftId,Date attendanceDate)
			{
				@SuppressWarnings("unchecked")
				List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E where E.organizationId="+orgId+" AND E.branchId="+branchId+" AND E.isDeleted=0").getResultList();
				
				List<Long> emp_ids= new ArrayList<Long>();
				for(Employees temp_emp:employees)
				{
					emp_ids.add(temp_emp.getId());
				}				
				String empIds = emp_ids.toString();
				String emps = empIds.substring(1, empIds.length()-1);
				
				List<Employeeattendence> emplAttendance = (List<Employeeattendence>) entityManager.createQuery("SELECT E from Employeeattendence E where E.shiftId="+shiftId+"and E.AttendenceDate="+attendanceDate+" AND E.employeeId in("+emps+")" ).getResultList();
				
				return employees;
			}

		//Save employee attendance 
		@Override
		@Transactional
		public void saveEmployeeAttendance(EmployeeAttandenceForm attandenceForm)
			{
				List<EmployeeAttendanceConfiguration> employeeList = attandenceForm.getEmployees();
				for (EmployeeAttendanceConfiguration empLis : employeeList)
					{							
						@SuppressWarnings("unchecked")
						List<Employeeattendence> employee=entityManager.createQuery("SELECT e FROM Employeeattendence e WHERE e.employeeId="+empLis.getEmployeeId()+"AND e.attendenceDate =:a").setParameter("a", attandenceForm.getAttendanceDate(), TemporalType.DATE).getResultList();
						if(employee.isEmpty())
							{
								Employeeattendence model = new Employeeattendence();
								Employees employeeId = entityManager.find(Employees.class, empLis.getEmployeeId());
								model.setEmployeeId(employeeId);
								model.setAttendenceDate(attandenceForm.getAttendanceDate());
								model.setStatus(empLis.getStatus());
								Shifts shift = entityManager.find(Shifts.class, attandenceForm.getShiftId());
								model.setShiftId(shift);
								entityManager.persist(model);								
							}
						else
							{
								Employeeattendence eemployee=(Employeeattendence) entityManager.createQuery("SELECT e FROM Employeeattendence e WHERE e.employeeId="+empLis.getEmployeeId()+"AND e.attendenceDate =:a")
										.setParameter("a", attandenceForm.getAttendanceDate(), TemporalType.DATE)
										.getSingleResult();
								eemployee.setStatus(empLis.getStatus());
								eemployee.setAttendenceDate(attandenceForm.getAttendanceDate());				
					    	}
					}
			}

		
		/*** Employee grooming ***/
		
		//Get employee grooming by grooming-date
		public List<Groomings> getGroomingByGroomingDate(EmployeeGrooming form){
			@SuppressWarnings("unchecked")
			List<Groomings> groomings = entityManager.createQuery("SELECT g FROM Groomings g WHERE  g.employees="+form.getEmployeeId()+"AND g.groomingDay=:a")
					.setParameter("a",form.getGroomingDay(), TemporalType.TIMESTAMP)
					.getResultList();
			return groomings;
		}
		
		//Save employee grooming
		@Override
		@Transactional
		public void saveEmployeeGrooming(EmployeeGrooming form)
			{	
				@SuppressWarnings("unchecked")
				List<Groomings> groomings = entityManager.createQuery("SELECT g FROM Groomings g WHERE  g.employees="+form.getEmployeeId()+"AND g.groomingDay=:a")
						.setParameter("a",form.getGroomingDay(), TemporalType.DATE)
						.getResultList();
				if(groomings.isEmpty())
					{
						List<EmloyeeGroomingCheckList> checkList = form.getCheckList();
						for(EmloyeeGroomingCheckList grooming:checkList)
							{
							if(grooming.getCheckListId()!=null)
							  {
								Groomings model=new Groomings();
								Checklists checkListId = entityManager.find(Checklists.class, grooming.getCheckListId());
								model.setCheckListId(checkListId);
								model.setCheckListResult("yes");
								model.setEmployeeId(form.getEmployeeId());
								model.setGroomingDay(form.getGroomingDay());
								Date d=new Date();
								model.setModifiedOn(d);
								entityManager.persist(model);
							  }
							}
					}
				else
					{
						Groomings result = (Groomings) entityManager.createQuery("SELECT g FROM Groomings g WHERE g.employeeId="+form.getEmployeeId()+"AND g.groomingDay=:a")
								.setParameter("a",form.getGroomingDay(), TemporalType.DATE)
								.getSingleResult();
						List<EmloyeeGroomingCheckList> checkList = form.getCheckList();
						for(EmloyeeGroomingCheckList grooming:checkList)
							{	
							if(grooming.getCheckListId()!=null)
							  {
								Checklists checkListId = entityManager.find(Checklists.class, grooming.getCheckListId());
								result.setCheckListId(checkListId);
								result.setCheckListResult("yes");
								result.setEmployeeId(form.getEmployeeId());
								result.setGroomingDay(form.getGroomingDay());
								Date d=new Date();
								result.setModifiedOn(d);
							  }
							}			
					}
			}
		
		//Save grooming by restApi
		@Override
		@Transactional
		public boolean saveEmployeeGroomingByRestApi(Long employeeId, Long checkListId) {
			if(employeeId!=null||employeeId!=0)
			{
			Groomings model=new Groomings();
			model.setCheckListId(entityManager.find(Checklists.class, checkListId));
			model.setEmployeeId(employeeId);
			model.setGroomingDay(new Date());
			model.setCheckListResult("Yes");
			model.setModifiedOn(new Date());
			entityManager.persist(model);
			return true;
			}
			else {
				return false;
			}
		}

		/***** Employee relation *****/

		//Get all employee relation by employee id
		public List<EmployeeRelationSummaryView> getAllEmployeeRelationByEmpId(long id)
			{
				List<EmployeeRelationSummaryView> empRelation = new ArrayList<EmployeeRelationSummaryView>();
				@SuppressWarnings("unchecked")
				List<Employeesfamilydetails> employeeRelation = entityManager.createQuery(
						"SELECT e FROM Employeesfamilydetails e WHERE e.isDeleted=0 AND e.employeeId=" + id).getResultList();
				for (Employeesfamilydetails empRel : employeeRelation)
					{
						EmployeeRelationSummaryView form = new EmployeeRelationSummaryView();
						form.setName(empRel.getName());
						Relationships relation = empRel.getRelationshipId();
						form.setRelationShipType(relation.getName());
						form.setId(empRel.getId());
						Employees emp = empRel.getEmployeeId();
						form.setEmployeeId(emp.getId());
						form.setEmployeeName(emp.getFirstName());
						empRelation.add(form);
					}
				return empRelation;
			}	

		//Save employee relation details
		@Transactional
		public void saveEmployeeRelation(EmployeeFamilyDetailsForm form)
			{
				Employeesfamilydetails empRelDetails = new Employeesfamilydetails();
				Employees empId = entityManager.find(Employees.class, form.getEmployeeId());
				empRelDetails.setEmployeeId(empId);
				Relationships relationShipId = entityManager.find(Relationships.class, form.getRelationshipId());
				empRelDetails.setRelationshipId(relationShipId);
				empRelDetails.setName(form.getName());
				empRelDetails.setIsDeleted((short) 0);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				empRelDetails.setModifiedOn(date);
				empRelDetails.setModifiedBy(form.getModifiedBy());
				entityManager.persist(empRelDetails);
			}

		//Get employee relation detail by id
		@Transactional
		public Employeesfamilydetails getEmpRelationById(long id)
			{
				Employeesfamilydetails empRel = entityManager.find(Employeesfamilydetails.class, id);
				return empRel;
			}

		//Update employee relation
		@Transactional
		public void updateEmployeeRelation(EmployeeFamilyDetailsForm form)
			{
				Employeesfamilydetails employee = entityManager.find(Employeesfamilydetails.class, form.getId());
				employee.setName(form.getName());
				Relationships relId = entityManager.find(Relationships.class, form.getRelationshipId());
				employee.setRelationshipId(relId);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				employee.setModifiedOn(date);
				employee.setModifiedBy(form.getModifiedBy());
			}
		
		//Delete employee relation
		@Transactional
		public String deleteEmployeeRelation(long id)
			{
				Employeesfamilydetails delRel = entityManager.find(Employeesfamilydetails.class, id);
				delRel.setIsDeleted((short) 1);

				return "Relation Deleted Seccesfuly";
			}
		
		/*** Employee overtime ***/
		
		//Save employee over-time
		@Override
		@Transactional
		public void saveEmployeeOverTime(EmployeeOverTime overTimeForm)
		{
			List<EmployeeOverTimeList> overTimeList = overTimeForm.getEmployees();
			for(EmployeeOverTimeList employeeList:overTimeList)
			{ 
				if(employeeList.getNoOfHours()!=null)
				{
					@SuppressWarnings("unchecked")
					List<Employeeattendence> employee=entityManager.createQuery("SELECT e FROM Employeeattendence e WHERE e.employeeId="+employeeList.getEmployeeId()+"AND e.attendenceDate =:a")
							.setParameter("a", overTimeForm.getOverTimeDate(), TemporalType.DATE)
							.getResultList();
					if(employee.isEmpty())
						{
						System.out.println("First save Attendance");
						}
					else
						{
							Employeeattendence eemployee=(Employeeattendence) entityManager.createQuery("SELECT e FROM Employeeattendence e WHERE e.employeeId="+employeeList.getEmployeeId()+"AND e.attendenceDate =:a")
									.setParameter("a",overTimeForm.getOverTimeDate(), TemporalType.DATE)
									.getSingleResult();
							eemployee.setAttendenceDate(overTimeForm.getOverTimeDate());
							eemployee.setHoursWorked(employeeList.getNoOfHours());
							
							Employeeovertime overTime=null;
							
							@SuppressWarnings("unchecked")
							List<Employeeovertime> overTimeLists=entityManager.createQuery("SELECT e FROM Employeeovertime e WHERE e.employeeId="+employeeList.getEmployeeId()+"AND e.overTimeDate =:a")
									.setParameter("a", overTimeForm.getOverTimeDate(), TemporalType.DATE)
									.getResultList();
							if(!overTimeLists.isEmpty())
							{
							overTime=(Employeeovertime) entityManager.createQuery("SELECT e FROM Employeeovertime e WHERE e.employeeId="+employeeList.getEmployeeId()+"AND e.overTimeDate =:a")
									.setParameter("a",overTimeForm.getOverTimeDate(), TemporalType.DATE)
									.getSingleResult();
							overTime.setNoOfHours(employeeList.getNoOfHours());
							overTime.setModifiedOn(new Date());
							}							
							else{
								overTime=new Employeeovertime();
								overTime.setNoOfHours(employeeList.getNoOfHours());
								overTime.setOverTimeDate(overTimeForm.getOverTimeDate());
								overTime.setModifiedOn(new Date());
								overTime.setEmployeeId(entityManager.find(Employees.class,employeeList.getEmployeeId()));
								overTime.setShiftId(entityManager.find(Shifts.class,overTimeForm.getShiftId()));
								entityManager.persist(overTime);	
							}							
						}
				}
				else
				{
					System.out.println("No hours is 0");
				}
			}
		}
		
		//Employee over-time by shift and date 
		@SuppressWarnings("unchecked")
		@Override
		public List<Employeeovertime> getEmployeeOverTimeListByDate(EmployeeOverTime overTimeForm) {
			List<Employeeovertime> employeeList=entityManager.createQuery("SELECT e FROM Employeeovertime e WHERE e.shiftId="+overTimeForm.getShiftId()+"AND e.overTimeDate =:a")
			.setParameter("a",overTimeForm.getOverTimeDate(), TemporalType.DATE)
			.getResultList();
			return employeeList;
		}
		

		//Get all the unallocated employees and save them in job allocation .
		@Override
		public List<EmployeeSummaryView> getUnAllocatedEmployees() {
			
			List<EmployeeSummaryView> empSummary = new ArrayList<EmployeeSummaryView>();
			@SuppressWarnings("unchecked")
			List<Employees> employees = (List<Employees>) entityManager.createQuery("SELECT E from Employees E where E.id not in (Select employeeId from Joballocations)")
					.getResultList();
			for (Employees emp : employees)
			{
				EmployeeSummaryView empView = new EmployeeSummaryView();
				empView.setId(emp.getId());
				empView.setEmpCode(emp.getEmpCode());
				empView.setEmpFirstName(emp.getFirstName());
				empView.setEmpDOB("121312");
				Designations designation = emp.getDesignationId();
				empView.setEmpDesignation(designation.getName());
				Employees supervisor = emp.getSupervisorId();
				empView.setEmpSupervisor(supervisor.getFirstName());
				empSummary.add(empView);
			}
		return empSummary;
		}

		//Get employee by organization 
		public List<Employees> getAllEmployeeByOrgId(Long orgId)
		{
			@SuppressWarnings("unchecked")
			List<Employees> employees = entityManager.createQuery("SELECT E from Employees E WHERE E.organizationId="+orgId).getResultList();
			return employees;
		}

		//Get employee by organization and branch 
		@Override
		public List<Employees> getAllEmployeeByOrgIdAndBranchId(long orgId, long branchId)
		{
			@SuppressWarnings("unchecked")
			List<Employees> employees = entityManager.createQuery("SELECT E FROM Employees E WHERE E.organizationId="+orgId+"AND E.branchId="+branchId+"").getResultList();
		    return employees;                                         
		}


		//Get job-allocation by organization and branch 
		@Override
		public List<Joballocations> getAllEmployeeByOrgsIdAndBranchId(long organozationId, long branchId) {
			// TODO Auto-generated method stub
			return null;
		}

		
		//delete selected employee
		@Override
		@Transactional
		public String deleteEmployee(long id) {
			Employees delEmp = entityManager.find(Employees.class, id);
			delEmp.setIsDeleted((int)1);
			entityManager.merge(delEmp);
			return "Employee Deleted Successfully";
		}
		
		
		
	}
