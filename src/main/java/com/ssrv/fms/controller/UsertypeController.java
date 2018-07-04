package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.UserType.Usertype;
import com.ssrv.fms.service.UserType.Intf.UsertypeIntf;
import com.ssrv.fms.vo.Usertype.UsertypeForm;

@Controller
public class UsertypeController {
	
	@Autowired
	UsertypeIntf usertypeIntf;
	
	///User type Summary
	@RequestMapping(value="/UsertypeSummary",method=RequestMethod.GET)
	public String usertypeSummary(ModelMap model)
	{
		List<Usertype> user = usertypeIntf.getAllUsertype();	
		model.addAttribute("user", user);	
		return "UsertypeSummary";		
	}
	
	///Save UserType
	@RequestMapping(value="/saveUsertype",method=RequestMethod.POST)
	public String saveUsertype(UsertypeForm form,ModelMap model)
	{
		usertypeIntf.SaveUsertype(form);	
		return "redirect:/UsertypeSummary";		
	}
	
	///Delete UserType
	@RequestMapping(value="/DeleteUsertype",method=RequestMethod.GET)
	public String deleteUsertype(@RequestParam("id")String id)
	{
		int user_id = Integer.parseInt(id);		
		usertypeIntf.deleteUsertype(user_id);	
		return "redirect:/UsertypeSummary";
		
	}
	
	///Edit UserType
	@RequestMapping(value="/EditUsertype",method=RequestMethod.GET)
	public String editeUsertype(@RequestParam("id") long id,ModelMap model)
	{
		Usertype user = usertypeIntf.getUsertypeByID(id);	
		model.addAttribute("user_type", user);		
		return "EditUsertype";
	}
	
	///Update UserType
	@RequestMapping(value="/UpdateUsertype",method=RequestMethod.POST)
	public String updateUsertype(UsertypeForm form,ModelMap model)
	{
		usertypeIntf.updateUsertype(form);	
		return "redirect:/UsertypeSummary";
	}
	
	//Get all user types
	@RequestMapping(value = "/getAllUserTypes", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<UsertypeForm> getOrganization()
		{
			List<Usertype> userTypeList = usertypeIntf.getAllUsertype();
			List<UsertypeForm> resultList=new ArrayList<UsertypeForm>();
			for(Usertype userType:userTypeList)
				{
					UsertypeForm form=new UsertypeForm();
					form.setId(userType.getId());
					form.setName(userType.getName());
					resultList.add(form);
				}
				return resultList;
		}

}
