package com.ssrv.fms.restFullAPIController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssrv.fms.service.employee.intf.EmployeeService;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;

@RestController
public class updateCheckListAPIController {
	
	@Autowired
	EmployeeService emplImpl;
	
	@Autowired
	CheckListIntf checkListImpl;
	
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(value="/updateCheckList")
public @ResponseBody Map<String, Object> updateCheckList(@RequestParam String managedEntityId,@RequestParam String employeeId,@RequestParam String checkListId,HttpServletRequest request, HttpServletResponse response)
{

	Map<String, Object> resultMap=new HashMap<String, Object>();
	HttpSession session = request.getSession(true);
	try{
		if(session.getAttribute("userId")==null||session.getAttribute("userId").equals(""))
		{
			String erorr="Please login first";
			resultMap.put("Erorr", erorr);
		}
		else
		{
			
		if(managedEntityId!=""|| managedEntityId==null || managedEntityId.equals(0))
		{
			//Please save checklist of managed entity i`m bit confuse with database please do implementation
			//Below code to save checklist for managed entity is not suer is it right or not 
			//Check it one and update to Manju sir
			//checkListImpl.saveManagedEntityCheckListByRestFullApi(managedEntityId, checkListId)
			String constraction="Sorry this API is still under constraction";
			resultMap.put("Under Constraction", constraction);
		}
		else
		{
			boolean result = emplImpl.saveEmployeeGroomingByRestApi(Long.parseLong(employeeId), Long.parseLong(checkListId));
			if(result==true)
			{
				String aa="Employee Grooming CheckList Save Successfully";
				resultMap.put("Result", aa);
			}
			else
			{
				String aa="Employee grooming checklist not saved,Please check inputs";
				resultMap.put("Result", aa);
			}
		}
	    return resultMap;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return resultMap;
	}
	return resultMap;	
}
}
