package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.service.designation.intf.DesignationService;
import com.ssrv.fms.vo.Designation.DesignationForm;

@Controller
public class DesignationController {

	@Autowired
	private DesignationService designationServiceImpl;
	HttpSession session = null;	
	
	///Summary Of Designation
	@RequestMapping(value = "/DesignationSummary", method = RequestMethod.GET)
	public String designationSummary(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(true);
	try {

			if (session.getAttribute("userId") == null
					|| session.getAttribute("userId").equals("")) 
			{

				return "login";
			}
			List<Designations> designation = designationServiceImpl.getAllDesignations();
			model.addAttribute("designation", designation);
			System.out.println("Designation retrieved");
			return "DesignationSummary";
		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}
	
	
	/// To Save Designation into DataBase
	@RequestMapping(value = "/addDesignation", method = RequestMethod.POST)
	public String saveDesignation(DesignationForm form, ModelMap model) 
	{
		designationServiceImpl.SaveDesignation(form);
		return "redirect:/DesignationSummary";
	}
	
	
	/// To delete Destination
	@RequestMapping(value = "/deleteDestination", method = RequestMethod.GET)
	public String deleteDesignation(@RequestParam("id") String id)
	{
		try
		{
			int ds_id = Integer.parseInt(id);
			designationServiceImpl.deleteDesignation(ds_id);
			return "redirect:/DesignationSummary";
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}
	

	/// edit Designation
	@RequestMapping(value = "/editDesignation", method = RequestMethod.GET)
	public String editDesignation(@RequestParam("id") long id, ModelMap model) {
		try
		{
		   Designations designationType = designationServiceImpl.getDesignationById(id);
		   model.addAttribute("designation", designationType);
		   return "EditDesignation";
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}
	

	/// Update Country Details .
	@RequestMapping(value = "/updateDesignation", method = RequestMethod.POST)
	public String updateDesignation(DesignationForm form, ModelMap model)
	{
		try
		{
			designationServiceImpl.updateDesignation(form);
			return "redirect:/DesignationSummary";
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}
	
	@RequestMapping(value="/getDesignations",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<DesignationForm> getDesignation()
	{
		List<DesignationForm> designations=new ArrayList<DesignationForm>();
		List<Designations> designation = designationServiceImpl.getAllDesignations();
		for(Designations list:designation)
		{
			designations.add(new DesignationForm(list));
		}
		return designations;
	}

}
