/**
 * Do not remove below comment, required for merge and lost changes to recover. 
 * 
 */
//http://122.166.211.51:99/Repository/FMS/Commit/308f6b2cf7edfdba53913c51a3b6ffc0a9f87f62

package com.ssrv.fms.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;
import com.ssrv.fms.service.Relationship.intf.RelationshipServiesIntf;
import com.ssrv.fms.service.Shifts.Intf.ShiftIntf;
import com.ssrv.fms.service.User.Intf.UserIntf;
import com.ssrv.fms.service.admin.intf.CountryService;
import com.ssrv.fms.service.admin.intf.StateService;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.designation.intf.DesignationService;
import com.ssrv.fms.service.employee.intf.EmployeeService;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.Designation.DesignationForm;
import com.ssrv.fms.vo.Job.EmployeeJobAllocationForm;
import com.ssrv.fms.vo.Job.EmployeeJobmovementForm;
import com.ssrv.fms.vo.Shifts.ShiftForm;
import com.ssrv.fms.vo.checklist.CheckListForm;
import com.ssrv.fms.vo.employee.EmployeeAttandenceForm;
import com.ssrv.fms.vo.employee.EmployeeFamilyDetailsForm;
import com.ssrv.fms.vo.employee.EmployeeForm;
import com.ssrv.fms.vo.employee.EmployeeGrooming;
import com.ssrv.fms.vo.employee.EmployeeJobAllocationView;
import com.ssrv.fms.vo.employee.EmployeeOverTime;
import com.ssrv.fms.vo.employee.EmployeeRelationSummaryView;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;
import com.ssrv.fms.vo.user.UserBranchMappingVO;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeServiceImpl;

	@Autowired
	private CheckListIntf checkListImpl;

	@Autowired
	private OrganizationService organizationServiceImpl;

	@Autowired
	private DesignationService designationServiceImpl;

	@Autowired
	private StateService stateServiceImpl;

	@Autowired
	private CountryService countryServiceImpl;

	@Autowired
	private RelationshipServiesIntf relationServies;

	@Autowired
	private BranchService branchServieceImpl;

	@Autowired
	private ShiftIntf shiftImpl;

	@Autowired
	private UserIntf userImpl;

	//todo:this needs to be checked .
	@RequestMapping(value = "/EmployeeSummary", method ={ RequestMethod.GET,RequestMethod.POST})
	public String employeeSummary(Model model) {
		List<EmployeeSummaryView> employees = employeeServiceImpl
				.getallEmployeesSummary();

		model.addAttribute("Emp", employees);

		return "EmployeeSummary";
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public String editEmployee(@RequestParam("eid") Long eid, ModelMap model) {

		List<Country> countries = countryServiceImpl.getAllCountries();
		List<States> states = stateServiceImpl.getallStateNames();

		List<Designations> designations = designationServiceImpl
				.getAllDesignations();

		List<Organization> organizations = organizationServiceImpl
				.getAllOrganizations();
	
	  List<Branch> branchs=branchServieceImpl.getAllBranches();

		List<Employees> supervisor = employeeServiceImpl.getallEmployees();

		Employees employee = employeeServiceImpl.getEmployeebyId(eid);

		model.addAttribute("emp", employee);
		model.addAttribute("countries", countries);
		model.addAttribute("states", states);
		model.addAttribute("branch", branchs);
		model.addAttribute("designations", designations);
		model.addAttribute("organizations", organizations);
		model.addAttribute("supervisor", supervisor);

		return "EditEmployee";
	}

	// Redirect to add new Employee *****.
	
	@RequestMapping(value = "/addEmployee", method ={ RequestMethod.GET,RequestMethod.POST})
	public String addEmployee(@RequestParam("selectdOrgn") String org,@RequestParam("selectedbran") String bran,Model model) {
		try{
			long organization=Integer.parseInt(org);
			long branch=Integer.parseInt(bran);
			Organization org_name =organizationServiceImpl.getOrganizationById(organization);
			Branch brch_name= branchServieceImpl.getBranchById(branch);
			model.addAttribute("SelectedOrganization", org_name);
			model.addAttribute("SelectedBranch", brch_name);			
			List<Employees> supervisor = employeeServiceImpl.getallEmployees();
			model.addAttribute("supervisor", supervisor);
			List<Designations> designations = designationServiceImpl
					.getAllDesignations();
			model.addAttribute("designations", designations);
			return "EmployeeDetails";
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}
	
	
	
	
   // save employee
	@RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST )
	public String addNewEmployee(EmployeeForm form, ModelMap model) {

		employeeServiceImpl.saveEmployee(form);

		return "redirect:/EmployeeSummary";

	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public String updateEmployee(EmployeeForm form) {
		employeeServiceImpl.updateEmployee(form);
		return "redirect:/EmployeeSummary";
	}

	/*** Employee Relation ***/

	// Employee Family Details page
	@RequestMapping(value = "/EmployeeFamilyDetails", method = RequestMethod.GET)
	public String getEmpFamilyDetails(ModelMap model,
			@RequestParam("eid") long eid) {
		List<EmployeeRelationSummaryView> empRelationship = employeeServiceImpl
				.getAllEmployeeRelationByEmpId(eid);
		Employees employee = employeeServiceImpl.getEmployeebyId(eid);
		model.addAttribute("employee", employee);
		model.addAttribute("EmpRelDetail", empRelationship);
		return "EmployeeFamilyDetails";
	}

	// Adding EmployeeRelation Details
	@RequestMapping(value = "/AddEmployeeRelationship", method = RequestMethod.GET)
	public String addRelation(ModelMap model, @RequestParam("empId") Long empId) {
		// relationServies.getRelationByID(id)
		List<Relationships> relation = relationServies.getAllRelation();
		model.addAttribute("relationshipId", relation);
		Employees employee = employeeServiceImpl.getEmployeebyId(empId);
		model.addAttribute("employee", employee);
		return "AddEmployeeRelationship";
	}

	// Saving EmployeeRelation Details
	@RequestMapping(value = "/saveEmployeeRelation", method = RequestMethod.POST)
	public String saveRelation(ModelMap model, EmployeeFamilyDetailsForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		employeeServiceImpl.saveEmployeeRelation(form);
		String empId = req.getParameter("employeeId");
		long id = Integer.parseInt(empId);
		return "redirect:/EmployeeFamilyDetails?eid=" + id;
	}

	// Editing EmployeeRelation details

	@RequestMapping(value = "/editRelation", method = RequestMethod.GET)
	public String editRelation(ModelMap model, @RequestParam("relId") long id) {
		List<Relationships> relation = relationServies.getAllRelation();
		model.addAttribute("relationshipId", relation);

		Employeesfamilydetails empRel = employeeServiceImpl
				.getEmpRelationById(id);
		model.addAttribute("empRel", empRel);
		model.addAttribute("fRelId", empRel);
		return "EditEmployeeRelation";
	}

	// Updating EmployeeRelation Details
	@RequestMapping(value = "/updateEmployeeRelationship", method = RequestMethod.POST)
	public String updateRelation(ModelMap model,
			EmployeeFamilyDetailsForm form, HttpServletRequest req,
			HttpServletResponse resp) {
		employeeServiceImpl.updateEmployeeRelation(form);

		String empId = req.getParameter("empId");
		long id = Integer.parseInt(empId);

		return "redirect:/EmployeeFamilyDetails?eid=" + id;
	}

	// Deleting Employee Relation
	@RequestMapping(value = "/deleteRelation", method = RequestMethod.GET)
	public String deleteRelation(ModelMap model, @RequestParam("id") long id,
			@RequestParam("eid") long eid) {
		employeeServiceImpl.deleteEmployeeRelation(id);
		return "redirect:/EmployeeFamilyDetails?eid=" + eid;
	}

	/*** Temporary Employee ***/

	// Get temporary employee page
	@RequestMapping(value = "/AddTemporaryEmployee", method = RequestMethod.GET)
	public String addEmergencyEmployee(ModelMap model,
			@RequestParam("organizationId") long organizationId,
			@RequestParam("branchId") long branchId,
			@RequestParam("shiftId") long shiftId) {
		List<Country> countries = countryServiceImpl.getAllCountries();
		List<States> state = stateServiceImpl.getAllStates();
		List<Employees> supervisor = employeeServiceImpl.getallEmployees();
		List<Designations> designations = designationServiceImpl
				.getAllDesignations();

		model.addAttribute("countries", countries);
		model.addAttribute("designations", designations);
		model.addAttribute("states", state);
		model.addAttribute("supervisor", supervisor);

		model.addAttribute("organizationId", organizationId);
		model.addAttribute("branchId", branchId);
		model.addAttribute("shiftId", shiftId);
		return "AddTemporaryEmployee";
	}

	// Save temporary employee
	@RequestMapping(value = "/SaveTemporaryEmployee")
	public String saveEmergencyEmployee(ModelMap model,
			EmployeeForm employeeForm,
			EmployeeJobAllocationView jobAllocationForm) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		employeeServiceImpl.saveEmergencyEmployee(employeeForm,
				jobAllocationForm);
		resultMap.put("organizationId", jobAllocationForm.getOrganisationId());
		resultMap.put("branchId", jobAllocationForm.getBranchId());
		resultMap.put("shiftId", jobAllocationForm.getShiftId());
		return "redirect:/EmployeeAttendance";
	}

	/*** Job Movement ***/

	// JobMovement page
	@RequestMapping(value = "/EmployeeJobMovement", method = RequestMethod.GET)
	public String employeeJobMovement(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		// Checking for session
		HttpSession session = request.getSession(true);
		if (session.getAttribute("userId") == null
				|| session.getAttribute("userId").equals("")) {
			// If session get fail return to login page
			return "login";
		} else {
			// If session get true return Employee job movement page
			return "EmployeeJobMovement";
		}
	}

	// Saving Job movement
	@RequestMapping(value = "/saveEmployeeJobMovement", method = RequestMethod.POST)
	public String saveEmployeeJobMovement(EmployeeJobmovementForm from) {
		employeeServiceImpl.saveEmployeeJobMovement(from);
		return "redirect:/EmployeeJobMovement";
	}

	// Getting Job movement List
	@RequestMapping(value = "/gettingJobMovementList", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Map<String, Object> gettingEmployeeJobMovement(
			@RequestParam String organizationId, @RequestParam String branchId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ShiftForm> shiftList = new ArrayList<ShiftForm>();
		List<EmployeeForm> employeeList = new ArrayList<EmployeeForm>();
		// List<EmployeeJobAllocationView> jobAllocationList=new
		// ArrayList<EmployeeJobAllocationView>();
		List<DesignationForm> designationList = new ArrayList<DesignationForm>();

		// Get shift by organization and branch
		List<Shifts> shifts = shiftImpl.getAllShiftsByBranchId(
				Long.parseLong(branchId), Long.parseLong(organizationId));
		for (Shifts shift : shifts) {
			shiftList.add(new ShiftForm(shift));
		}

		// Get all designation
		List<Designations> designations = designationServiceImpl
				.getAllDesignations();
		for (Designations designation : designations) {
			designationList.add(new DesignationForm(designation));
		}

		// Get all employees who belongs to organization of long-in user
		Set<Long> orgIdSet = new HashSet<Long>();
		HttpSession session = request.getSession(true);
		Long userId = (Long) session.getAttribute("userId");
		List<UserBranchMappingVO> userBranchMapping = userImpl
				.getAllUserBarnchMappingByUserId(userId);
		for (UserBranchMappingVO set : userBranchMapping) {
			orgIdSet.add(set.getOrganizationId());
		}

		for (Long orgIds : orgIdSet) {
			List<Employees> employees = employeeServiceImpl
					.getAllEmployeeByOrgId(orgIds);
			for (Employees list : employees) {
				if (list != null) {
					employeeList.add(new EmployeeForm(list));
				}
			}
		}

		resultMap.put("shifts", shiftList);
		resultMap.put("designations", designationList);
		resultMap.put("employees", employeeList);
		return resultMap;
	}

	/*** Employee Attendance ***/

	// Getting Employee attendance page
	@RequestMapping(value = "/EmployeeAttendance", method = RequestMethod.GET)
	public String employeeAttendance(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("userId") == null
				|| session.getAttribute("userId").equals("")) {
			// If session get fail return to login page
			return "login";
		} else {
			// If session get true return Employee job movement page
			return "EmployeeAttendance";
		}
	}

	// Saving Employee Attendance
	@RequestMapping(value = "/saveAttendance", method ={ RequestMethod.POST, RequestMethod.GET})
	public String saveEmployeeAttendance(ModelMap model,
			EmployeeAttandenceForm attendenceForm) {
		System.out.println(attendenceForm.getAttendanceDate());
		employeeServiceImpl.saveEmployeeAttendance(attendenceForm);
		return "redirect:/EmployeeAttendance";
	}

	// Getting employee attendance based on date
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/gettingAttendanceDate",method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	Map<String, Object> gettingAttendance(
			@RequestBody EmployeeAttandenceForm formData) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		List<EmployeeForm> employeeFormList = new ArrayList<EmployeeForm>();
		List<Employeeattendence> attendanceList = employeeServiceImpl
				.gettingEmployeeAttendanceOnDate(formData);
		if (!attendanceList.isEmpty()) {
			for (Employeeattendence list : attendanceList) {
				EmployeeForm form = new EmployeeForm();
				form.setId(list.getEmployeeId().getId());
				form.setFirstName(list.getEmployeeId().getFirstName());
				form.setLastName(list.getEmployeeId().getLastName());
				form.setAttendanceStatus(list.getStatus());
				employeeFormList.add(form);
			}
		}

		else {
			List<Joballocations> employeeList = employeeServiceImpl
					.getEmployeeByOrgAndShift(formData.getOrganizationId(),
							formData.getBranchId(), formData.getShiftId(),
							formData.getAttendanceDate());
			for (Joballocations emp : employeeList) {
				EmployeeForm form = new EmployeeForm();
				form.setId(emp.getEmployeeId().getId());
				form.setFirstName(emp.getEmployeeId().getFirstName());
				form.setLastName(emp.getEmployeeId().getLastName());
				form.setAttendanceStatus("P");
				employeeFormList.add(form);
			}
		}
		mapResult.put("employees", employeeFormList);
		return mapResult;
	}

	/*** Employee Over time ***/

	// Employee overtime page
	@RequestMapping(value = "/EmployeeOverTime")
	public String employeeGroomingPage() {
		return "EmployeeOverTime";
	}

	// Employee overtime saving
	@RequestMapping(value = "/addEmployeeOverTime", method = RequestMethod.POST)
	public String addEmployeeOverTime(EmployeeOverTime form) {
		employeeServiceImpl.saveEmployeeOverTime(form);
		return "redirect:/EmployeeOverTime";
	}

	// Get list of job-allocation employee by overtime-date
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/getEmployeeOverTimeByDate")
	public @ResponseBody
	Map<String, Object> gettingAttendance(@RequestBody EmployeeOverTime formData) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		List<EmployeeForm> employeeFormList = new ArrayList<EmployeeForm>();
		List<Employeeovertime> overTimeList = employeeServiceImpl
				.getEmployeeOverTimeListByDate(formData);
		if (overTimeList.size() != 0) {
			for (Employeeovertime list : overTimeList) {
				EmployeeForm form = new EmployeeForm();
				form.setId(list.getEmployeeId().getId());
				form.setFirstName(list.getEmployeeId().getFirstName());
				form.setLastName(list.getEmployeeId().getLastName());
				form.setNoOfHourWorked(list.getNoOfHours());
				;
				form.setEmpCode(list.getEmployeeId().getEmpCode());
				employeeFormList.add(form);
			}
		} else {
			List<Joballocations> employeeList = employeeServiceImpl
					.getEmployeeByOrgAndShift(formData.getOrganizationId(),
							formData.getBranchId(), formData.getShiftId(),
							formData.getOverTimeDate());
			for (Joballocations emp : employeeList) {
				EmployeeForm form = new EmployeeForm();
				form.setId(emp.getEmployeeId().getId());
				form.setFirstName(emp.getEmployeeId().getFirstName());
				form.setLastName(emp.getEmployeeId().getLastName());
				form.setEmpCode(emp.getEmployeeId().getEmpCode());
				employeeFormList.add(form);
			}
		}
		mapResult.put("employees", employeeFormList);
		return mapResult;
	}

	/*** Employee grooming ***/

	// Getting employee grooming page
	@RequestMapping(value = "/EmployeeGrooming")
	public String employeeGrooming() {
		return "EmployeeGrooming";
	}

	// Get Checklist based on organization and branch
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/getCheckListForGrooming")
	public @ResponseBody
	Map<String, Object> getCheckList(@RequestBody EmployeeGrooming formData) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CheckListForm> checkListForm = new ArrayList<CheckListForm>();
		List<Checklists> checkList = checkListImpl
				.getChecklistByOrgIdAndBrchId(formData);

		List<Groomings> groomingList = employeeServiceImpl
				.getGroomingByGroomingDate(formData);
		if (!groomingList.isEmpty()) {
			for (Checklists list1 : checkList) {
				CheckListForm test = new CheckListForm(list1);
				for (Groomings list2 : groomingList) {
					if (list2 != null) {
						if (list2.getCheckListId().getId() == list1.getId()) {
							test.setDefaultCheck((short) 0);
						} else {
							test.setDefaultCheck((short) 1);
						}
					}
				}
				checkListForm.add(test);
			}
			/*
			 * for(Groomings list:groomingList) { Long groomingId=
			 * list.getCheckListId().getId(); for(Checklists
			 * checkLists:checkList) { Long checkListId = checkLists.getId();
			 * if(groomingId==checkListId) {
			 * checkLists.setDefaultCheck((short)0); } else {
			 * checkLists.setDefaultCheck((short)1); } checkListForm.add(new
			 * CheckListForm(checkLists)); } }
			 */
		} else {
			for (Checklists list : checkList) {
				checkListForm.add(new CheckListForm(list));
			}
			resultMap.put("checkList", checkListForm);
		}
		return resultMap;
	}

	// Save Employee grooming
	@RequestMapping(value = "/saveEmployeeGrooming", method = RequestMethod.POST)
	public String saveEmployeeGrooming(EmployeeGrooming form,
			@RequestParam("organizationId") Long orgId,
			@RequestParam("branchId") Long brchId,
			@RequestParam("shiftId") Long shiftId) {
		employeeServiceImpl.saveEmployeeGrooming(form);
		return "redirect:EmployeeGrooming";
	}

	// Get employee for grooming
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/getEmployeeBygroomingDay")
	public @ResponseBody
	Map<String, Object> getEmployeeByGroomingDay(
			@RequestBody EmployeeGrooming formData) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EmployeeForm> resultList = new ArrayList<EmployeeForm>();
		List<Joballocations> employeeList = employeeServiceImpl
				.getEmployeeByOrgAndShift(formData.getOrganizationId(),
						formData.getBranchId(), formData.getShiftId(),
						formData.getGroomingDay());
		for (Joballocations emp : employeeList) {
			EmployeeForm form = new EmployeeForm();
			form.setId(emp.getEmployeeId().getId());
			form.setFirstName(emp.getEmployeeId().getFirstName());
			form.setLastName(emp.getEmployeeId().getLastName());
			form.setEmpCode(emp.getEmployeeId().getEmpCode());
			resultList.add(form);
		}
		resultMap.put("employees", resultList);
		return resultMap;
	}

	/*** Here employee-grooming over ***/
	
	/*** Employee Job-Allocation ***/
	@RequestMapping(value = "/EmployeeJobAllocation", method = RequestMethod.GET)
	public String employeeJobAllocation(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		// here get all the employees by organization id .
		if (session.getAttribute("userId") == null
				|| session.getAttribute("userId").equals("")) {
			return "login";
		}

		return "EmployeeJobAllocation";
	}

	//Employee attendance notification
	@RequestMapping(value = "/AttendanceNotification", method = RequestMethod.GET)
	public String employeeJobAllocation() 
	{
		return "UnderConstruction";
	}
	
    //Get supervisor for job allocation
	@RequestMapping(value = "/getEmployeesByOrg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<EmployeeSummaryView> getSupervisors(@RequestParam String orgId,
			@RequestParam String branchId) {
		List<EmployeeSummaryView> supervisors = employeeServiceImpl
				.getEmployeesByOrganization(Long.parseLong(orgId));
		return supervisors;
	}
    // Get all employee by org ******
	@RequestMapping(value = "/getEmployeesByOrganizationAndBranch", method = RequestMethod.POST)
	public  @ResponseBody List<EmployeeSummaryView> getEmployeesByOrganizationAnBranch(@RequestParam String orgId, @RequestParam String branchId,Model model)
		{
		List<EmployeeSummaryView> employeesumryView;
		try {
			employeesumryView =  employeeServiceImpl.getEmployeesByOrganization(Long.parseLong(orgId));
			return employeesumryView;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
			//Boolean deleted = checkListIntf.deleteCheckList(Long.parseLong(checkListId));
		//	resultSet.put("yesDeleted", deleted);
			
		}
	
	//Getting employee based on shift getEmployeeByOrgAndShift
			@RequestMapping(value="/getEmployeeByOrgAndShift", method ={ RequestMethod.GET,RequestMethod.POST })
			public @ResponseBody Map<String, Object> getShift(@RequestParam String orgId,@RequestParam String shiftId,@RequestParam String branchId,@RequestParam String attendanceDate)
			{
				try {
				Map<String, Object> resultMap=new HashMap<String, Object>();
				
				long org= Integer.parseInt(orgId);
				long branch= Integer.parseInt(branchId);
				long shift= Integer.parseInt(shiftId);
						
				DateFormat df = new SimpleDateFormat("dd MM yyyy");
			   Date result = (Date)df.parse(attendanceDate);
				//result = df.parse(attendanceDate);
			    
			  
			   // DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
			   // Date date = (Date)formatter.parse(dateStr);
			   // System.out.println(date);  
					
				 
				List<Employees>  employees = employeeServiceImpl.getEmployeeByOrgIdBranchIdAndShiftId(org,branch,shift,result);	
				
				List<EmployeeAttandenceForm> employeeAttandenceForms=new ArrayList<EmployeeAttandenceForm>();
				for(Employees  empv:employees)
					{
					employeeAttandenceForms.add(new EmployeeAttandenceForm(empv));
					}
				resultMap.put("emps", employeeAttandenceForms);
				return resultMap;
				
				} 
				catch(Exception e)
				{
					e.printStackTrace();
				
				}
				return null;
			}

	// Fetch all the unallocated Employees and assign them to a organization .
	@RequestMapping(value = "/getAllUnAllocatedEmployees", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<EmployeeSummaryView> getUnAllocatedEmployees() {
		List<EmployeeSummaryView> supervisors = employeeServiceImpl
				.getUnAllocatedEmployees();
		return supervisors;
	}
	
	//Save employee job-allocation
	@RequestMapping(value="/saveEmployeeJobAllocation",method=RequestMethod.POST)
	public String saveEmployeeJobAllocation(EmployeeJobAllocationForm form)
	{
		employeeServiceImpl.saveEmployeeJobAllocation(form);
		return "redirect:EmployeeJobAllocation";
	}
	
	
	// Delete selected Employee
	@RequestMapping(value = "/delEmployee", method = RequestMethod.GET)
	public String deleteOrg(@RequestParam("id") String id)
		{
			try
				{
					employeeServiceImpl.deleteEmployee(Integer.parseInt(id));
					return "redirect:EmployeeSummary";
				}

			catch (Exception e)
				{
					System.out.println(e.getMessage());
					return "ErrorMsg";
				}
		}

	
}
