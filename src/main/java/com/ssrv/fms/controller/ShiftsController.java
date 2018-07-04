package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;
import com.ssrv.fms.service.Shifts.Intf.ShiftIntf;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.Shifts.ShiftForm;
import com.ssrv.fms.vo.Shifts.UserValidation;

@Controller
public class ShiftsController
	{	
	
		@Autowired
		private ShiftIntf shiftIntf;

		@Autowired
		private OrganizationService organizationIntf;

		@Autowired
		private BranchService branchService;
	
		///To Get Organization List
		@RequestMapping(value = "/ShiftSummary", method = RequestMethod.GET)
		public String getOrganizationList(ModelMap model, HttpServletRequest req, HttpServletResponse resp, @ModelAttribute("form") ShiftForm form, BindingResult result)
			{
				UserValidation validation = new UserValidation();

				validation.validate(form, result);

				HttpSession session = req.getSession(true);
				try
					{
					 System.out.println(session.getAttribute("userId"));
						if (session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
							{

								return "login";
							}

						List<Organization> organization = organizationIntf.getAllOrganizations();
						model.addAttribute("OrganizationName", organization);
						return "ShiftSummary";
					}
				catch (Exception ex)
					{
						System.out.println(ex.getMessage());
						return "ErrorMsg";
					}
			}
		
		///Get List Of Shift Based On Organization & Branch
		@SuppressWarnings("unused")
		@RequestMapping(value = "/selectBranch", method = RequestMethod.GET)
		public String getShifts(ModelMap model, HttpServletRequest req, HttpServletResponse resp)
			{
				UserValidation validation = new UserValidation();
				HttpSession session = req.getSession(true);
				try
					{
						if (session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
							{
								return "login";
							}
						
						List<Organization> organization = organizationIntf.getAllOrganizations();
						model.addAttribute("OrganizationName", organization);
						
						String organisationId=req.getParameter("organizationId");
						
						if(organisationId!="")
						{
						    long organizationId = Integer.parseInt(organisationId);
						    List<BranchSummary> branch = branchService.getBranchesByOrgId(organizationId);
						    model.addAttribute("branch", branch);
						    String branchId = req.getParameter("branchId");
						    if (branchId.length() != 0 && branchId != null)
							  {
								   int brId = Integer.parseInt(branchId);
								   if (brId > 0)
									  {
										  List<Shifts> shifts = shiftIntf.getAllShiftsByBranchId(brId, organizationId);
										  model.addAttribute("shifts", shifts);
										  return "ShiftSummary";
									  }
							  }
						  return "ShiftSummary";
						}
					  return "ShiftSummary";

					}
				catch (Exception ex)
					{
						System.out.println(ex.getMessage());
						return "ErrorMsg";
					}
			}

		
		
		/// Edit Shifts
		@RequestMapping(value = "/editShift", method = RequestMethod.GET)
		public String editShift(@RequestParam("id") long shiftId, ModelMap model)
			{
				Shifts shift = shiftIntf.getShiftsById(shiftId);
				model.addAttribute("shift", shift);
				return "EditShift";
			}


		/// Update Shifts
		@RequestMapping(value = "/updateShift", method = RequestMethod.POST)
		public String UpdateShifts(ShiftForm form, ModelMap model, HttpServletRequest req, HttpServletResponse resp)
			{
				try
					{
						shiftIntf.updateShift(form);
						Shifts shift = shiftIntf.getShiftsById(form.getId());
						req.setAttribute("organizationId", shift.getOrganizationId().getId());
						req.setAttribute("branchId", shift.getBranchId().getId());
						return "redirect:/selectBranch?organizationId=" + shift.getOrganizationId().getId() + "&branchId=" + shift.getBranchId().getId();
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		
		///Add Shift
		@RequestMapping(value = "/addShifts", method = RequestMethod.GET)
		public String addShifts(ShiftForm form, ModelMap model,HttpServletRequest req, HttpServletResponse resp)
			{ 
				String name = req.getParameter("name");
				String startingTime = req.getParameter("startTime");
				String endingTime = req.getParameter("endTime");

				String organizatonId = req.getParameter("organizationId");
				long organizationsId = Integer.parseInt(organizatonId);
				req.setAttribute("organizationId", organizationsId);

				String branchId = req.getParameter("branchId");
				long branchesId = Integer.parseInt(branchId);	
				req.setAttribute("branchId", branchesId);

				if (name != "" && name!=null&& startingTime != "" &&startingTime!=null&& endingTime != "" &&endingTime!=null&& organizationsId!=0 && organizatonId!=null&&branchId!=null && branchesId!=0 )
					{
					    shiftIntf.SaveShift(form);
						return "redirect:/selectBranch?organizationId=" + organizationsId + "&branchId=" + branchesId;
					}
				else
					{			
						return "redirect:/selectBranch?organizationId=" + organizationsId + "&branchId=" + branchesId;			
					}


			}
					//Getting List of Shift
		@RequestMapping(value="/getShiftByOrgIdAndBrchId",method =RequestMethod.POST)
		public @ResponseBody Map<String, Object> getShift(@RequestParam String orgId,@RequestParam String branchId)
		{
			Map<String, Object> resultMap=new HashMap<String, Object>();
			List<Shifts> shifts = shiftIntf.getAllShiftsByBranchId(Long.parseLong(branchId),Long.parseLong(orgId));	
			List<ShiftForm> shiftList=new ArrayList<ShiftForm>();
			for(Shifts  shift:shifts)
				{
				shiftList.add(new ShiftForm(shift));
				}
			resultMap.put("shifts", shiftList);
			return resultMap;
		}
		}
