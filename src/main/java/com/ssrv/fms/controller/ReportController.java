package com.ssrv.fms.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssrv.fms.service.reports.JasperReports;



@Controller
public class ReportController {
	
	JasperReports reportClass = new JasperReports();
	
	
		/*@RequestMapping(value = "/employeeAttendanceReport", method = RequestMethod.GET)
		public String generateEmployeeAttendanceReport()
			{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ReportingMonth", "11");
			params.put("ReportYear", "2014");
			params.put("Organization", "1");
			params.put("Branch", "0");
		
			reportClass.addParams(params,"EmployeeAttendence");
				return "UnderConstruction";
			}*/
		@RequestMapping(value = "/employeeGroomingReport", method = RequestMethod.GET)
		public String generateEmployeeGroomingReport()
			{
			
			reportClass.createReport();
				return "UnderConstruction";
			}
		@RequestMapping(value = "/checklistReport", method = RequestMethod.GET)
		public String generateCheckListReport()
			{
			
			reportClass.createReport();
				return "UnderConstruction";
			}
		
		@RequestMapping(value = "/materialIndentReport", method = RequestMethod.GET)
		public String generateMaterialIndentReport()
			{
			
			reportClass.createReport();
				return "UnderConstruction";
			}
		
		@RequestMapping(value = "/siteSnapShotReport", method = RequestMethod.GET)
		public String generatesiteSnapShotReport()
			{					
				return "UnderConstruction";
			}
		
		@RequestMapping(value = "/snagRegisterReport", method = RequestMethod.GET)
		public String snagRegisterReport()
		{						
				return "UnderConstruction";
			}
		
		//report for Employee grooming .
	/*	@RequestMapping(value = "/employeeAttendanceReport", method = RequestMethod.GET)
		public String generateEmployeeAttendanceReport()
			{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ReportingMonth", "11");
			params.put("ReportYear", "2014");
			params.put("Organization", "1");
			params.put("Branch", "8");
		
			reportClass.addParams(params,"EmployeeGrooming");
				return "UnderConstruction";
			}*/
		
		//report for MaterialIndent
		/*@RequestMapping(value = "/employeeAttendanceReport", method = RequestMethod.GET)
		public String generateEmployeeAttendanceReport()
			{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ReportingMonth", "11");
			params.put("ReportYear", "2014");
			params.put("Organization", "1");
			params.put("Branch", "8");
		
			reportClass.addParams(params,"MaterialIndent");
				return "UnderConstruction";
			}*/
		
		//report for  MaterialDailyConsumption
		/*@RequestMapping(value = "/employeeAttendanceReport", method = RequestMethod.GET)
		public String generateEmployeeAttendanceReport()
			{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ReportingMonth", "11");
			params.put("ReportYear", "2014");
			params.put("Organization", "1");
			params.put("Branch", "0");
		
			reportClass.addParams(params,"MaterialDailyConsumption");
				return "UnderConstruction";
			}*/
		
		@RequestMapping(value = "/employeeAttendanceReport", method = RequestMethod.GET)
		public String generateEmployeeAttendanceReport()
			{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ReportingMonth", "11");
			params.put("ReportYear", "2014");
			params.put("Organization", "1324134");
			params.put("Branch", "5");
		
			reportClass.addParams(params,"EmployeeAttendence");
				return "UnderConstruction";
			}

}


