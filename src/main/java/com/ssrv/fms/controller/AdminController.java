package com.ssrv.fms.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssrv.fms.constants.AdminResource;
import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.User.Intf.UserIntf;
import com.ssrv.fms.service.admin.intf.ChangeBranchIntf;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.employee.intf.EmployeeService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;

@Controller
@Path(AdminResource.ROOT_PATH)
public class AdminController
	{
		@GET
		@Produces(MediaType.TEXT_HTML)
		public ModelAndView sayIndex(ModelAndView model)
			{
				return new ModelAndView("login");
			}

		@Autowired
		ChangeBranchIntf changeBranchImpl;
		
		@Autowired
		OrganizationService organizationImpl;
		
		@Autowired
		BranchService branchServiceImpl;
		
		@Autowired
		EmployeeService employeeImpl;
		
		@Autowired
		UserIntf userImpl;
		
		
		//Get organization and branch by login user id
		@RequestMapping(value = "/ChangeBranch", method = RequestMethod.GET)
		public String changeBranch(Model model,HttpServletRequest request, HttpServletResponse response)throws FmsException, NoSuchAlgorithmException
			{

				HttpSession session = request.getSession(true);
				if (session.getAttribute("userId") == null)
	  				{
						return "login";
					}
				else
					{
						long userId = (Long) session.getAttribute("userId");
						
						UserBranchMappingForm userBranchMapping = userImpl.getuserByUserId(userId);
						Organization oraganization = organizationImpl.getOrganizationById(userBranchMapping.getDefaultOrganization());
						model.addAttribute("userId", userId);
						model.addAttribute("defaultOrganization",oraganization);	
						model.addAttribute("defaultBranchId",userBranchMapping.getDefaultBranchId());
						 
						/****Below code is need don`t delete****/
						/*//Getting user details of from user change Branch table 
						UserBranchMapping changeUserBranch = changeBranchImpl.getUserBranchMapping(userId);
					    long userChangeBranchId = changeUserBranch.getId();
					    model.addAttribute("changeBarnchId", userChangeBranchId);
					    
						//Getting Organization Name to which  user belongs....
						Organization organizationId = changeUserBranch.getOrganizationId();
						Long getIdOfOrg = organizationId.getId();
					    //Organization oraganization = organizationImpl.getOrganizationById(getIdOfOrg);
					    //model.addAttribute("oraganization",oraganization);
					    
					    //Getting list of branch which are there under Organization
					    List<Branch> orgBranches = branchServiceImpl.getBranchByOrgId(getIdOfOrg);
					    model.addAttribute("branch", orgBranches);
					    
					    //Getting Branch to which branch user belongs
					    Branch branch = changeUserBranch.getBranchId();
					    Long branchId = branch.getId();
					    Branch defaultBranch = branchServiceImpl.getBranchById(branchId);
					    model.addAttribute("defaultBranch", defaultBranch);*/
					}
				
				return "ChangeBranch";
			}
	
		
		///Updating Branches Of User
		@RequestMapping (value = "/updateUserBranch", method = RequestMethod.POST)
		public String updateBranch(Model model,UserBranchMappingForm form)
			{
				changeBranchImpl.updateUserBranchMapping(form);
				return "Home";
			}
		
	}
