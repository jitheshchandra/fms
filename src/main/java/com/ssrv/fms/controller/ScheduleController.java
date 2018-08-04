package com.ssrv.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.ssrv.fms.dao.schedule.intf.ScheduleService;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.employee.intf.EmployeeService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.schedule.EditScheduleForm;
import com.ssrv.fms.vo.schedule.ManageScheduleVO;
import com.ssrv.fms.vo.schedule.ScheduleVO;

@Controller
public class ScheduleController {
	
	//@Autowired
    //private ScheduleService scheduleServiceImpl;
	
	@Autowired
	private OrganizationService organizationServiceImpl;
	
	@Autowired
	private BranchService branchServiceImpl;
	
	@Autowired
	private  EmployeeService employeeServiceImpl;
	
	
	@RequestMapping(value = "/Schedule", method = RequestMethod.GET)
	public String CreateSchedule(ModelMap model) {
		// pass the organizations form the controller .
		List<Organization> organizations = organizationServiceImpl.getAllOrganizations();
		model.addAttribute("organizations", organizations);
		return "Schedule";
	}

	
	@RequestMapping(value = "/SaveSchedules",  method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody String  saveScheduleDate(@RequestBody ScheduleVO scheduleData,HttpServletRequest request) {
		
		//save to database 
		
		try {
			
			Boolean isSaved = scheduleServiceImpl.SaveSchedule(scheduleData);
			if(isSaved)
			{
				return "Schedule Saved Successfully";
			}
			
				return "Schedule could not be saved";
			        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "Error Occured";
		}
		
						       
			    }
	
	@RequestMapping(value = "/SaveEditSchedule",  method = { RequestMethod.POST,RequestMethod.GET })
	public String saveUpdateSchedule(ModelMap model,EditScheduleForm scheduleData) {
		 scheduleServiceImpl.UpdateSchedule(scheduleData);
				return "ManageSchedules";			       
	}
	
	@RequestMapping(value= "/getAllSchedules", method= { RequestMethod.POST })
	public @ResponseBody List<ScheduleVO> getSchedules(){
		
		List<ScheduleVO>schedules= scheduleServiceImpl.GetAllSchedules();
		return schedules;
	}
	@RequestMapping(value = "/ManageSchedules", method = RequestMethod.GET)
	public String ManageSchedules() {


		return "ManageSchedules";
	}
	
	@RequestMapping(value= "/getAllSchedulesByDate", method= { RequestMethod.POST })
	public @ResponseBody List<ManageScheduleVO> getSchedules(@RequestParam String FromDate,@RequestParam String ToDate)
	{
			
		try {
			
			String[] from = FromDate.split("/");		 
			String[] to = ToDate.split("/");			
		    String from_Date =from[0]+"-"+from[1]+"-"+from[2];
			String to_Date = to[0]+"-"+to[1]+"-"+to[2];		
			List<ManageScheduleVO>schedules= scheduleServiceImpl.GetAllSchedulesByDate(from_Date, to_Date);
			return schedules;			

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	@RequestMapping(value= "/editSchedules", method= {RequestMethod.GET, RequestMethod.POST })
	public String  editSchedules(@RequestParam String sid,ModelMap model)
	{
			
		try {
			long scheduleId = Long.parseLong(sid);
			ManageScheduleVO schedules= scheduleServiceImpl.GetAllSchedulesByID(scheduleId);
			
			model.addAttribute("scheduleData",schedules);
			long orgid=Long.parseLong(schedules.getOrganization());
			String orgName;
			if(orgid==0)
			orgName ="--NA--";
			else
			{
			orgName = organizationServiceImpl.getOrganizationById(orgid).getName();
			}
			model.addAttribute("Org",orgName);
			long branchid=Long.parseLong(schedules.getBranch());
			String branchName;
			
			if(branchid==0)
			branchName ="--NA--";
			else
			{
			branchName = branchServiceImpl.getBranchById(branchid).getBranchName();
			}
			model.addAttribute("Branch",branchName);
			List<Employees> employees= employeeServiceImpl.getAllEmployeeByOrgIdAndBranchId( orgid, branchid);
			 model.addAttribute("emp",employees);
			 return "EditSchedules";			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}

	// Delete organization.
	@RequestMapping(value = "/delSchedule", method= {RequestMethod.GET, RequestMethod.POST })
	public String deleteSchedule(@RequestParam("id") String id)
		{
			try
				{
				scheduleServiceImpl.DeleteScheduleById(Integer.parseInt(id));
					return "redirect:ManageSchedules";
				}

			catch (Exception e)
				{
					System.out.println(e.getMessage());
					return "ErrorMsg";
				}
		}
	
	
	
	
}
