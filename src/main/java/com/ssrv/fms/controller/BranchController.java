package com.ssrv.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.branch.Branchcontact;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.admin.intf.StateService;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.branch.AddBranchForm;
import com.ssrv.fms.vo.branch.BranchContactForm;
import com.ssrv.fms.vo.branch.BranchContactInfo;
import com.ssrv.fms.vo.branch.BranchForm;


@Controller
public class BranchController {

	@Autowired
	private BranchService branchService;

	@Autowired
	private OrganizationService organizationServiceImpl;

	@Autowired
	private StateService stateServiceImpl;
	
	//Check for user session
	public boolean checkForSession(HttpServletRequest req,HttpServletResponse resp)
	{
		HttpSession session = req.getSession(true);
		if(session.getAttribute("userId")!=null&&session.getAttribute("userId")!="")
		{
			return true;
		}
		return false;
	}

	//Get all branches
	@RequestMapping(value = "/branchsummary", method = RequestMethod.GET)
	public String branchsummary(Model model,HttpServletRequest req,HttpServletResponse resp) {
	try {
		    boolean session = checkForSession(req, resp);
		    if(session!=true)
		    {
		    	return "login";  	
		    }
			return "branchsummary";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}
	
	//Get branches by organization
	@RequestMapping(value="/getBranchByOrganization",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<BranchSummary> getAllBranchesByOrganization(@RequestParam Long orgId)
	{
		return branchService.getAllBranches(orgId);
	}

	
	@RequestMapping(value = "/addBranch",method={RequestMethod.POST,RequestMethod.GET})
	public String addBranch(@RequestParam("selectedOrg") String org,Model model) {
		try{
		long organization = Integer.parseInt(org);
		Organization org_name=	organizationServiceImpl.getOrganizationById(organization);
		model.addAttribute("SelectedOrganization", org_name);
		
		}
		catch(Exception ex){
			System.out.println("ex");
			
		}
	
		return "BranchDetails";
	}

	//Add new branch
	@RequestMapping(value = "/addNewBranch",method={RequestMethod.POST,RequestMethod.GET})
	public String addNewBranch(AddBranchForm form, Model model) {
		try {
			branchService.saveBranch(form);
			return "redirect:/branchsummary";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	//Edit branch
	@RequestMapping(value = "/editBranch", method = RequestMethod.GET)
	public String updateBranch(@RequestParam("bid") Long bid, Model model) {
		try {
			Branch branch = branchService.getBranchById(bid);
			model.addAttribute("branch", branch);
			List<BranchContactInfo> branchContacts = branchService.getBranchContactByBranchID(bid);
			model.addAttribute("branchContacts", branchContacts);
			model.addAttribute("branch_id", branch.getId());
			List<Organization> org = organizationServiceImpl.getAllOrganizations();
			model.addAttribute("org", org);
			return "EditBranch";
		} catch (Exception Ex) {
			System.out.println(Ex.getMessage());
			return "ErrorMsg";
		}
	}

	//Update branch
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public String updateBranch(BranchForm form, Model model) {
		branchService.updateBranch(form);
		return "redirect:/branchsummary";
	}

	/*//Delete branch
	@RequestMapping(value = "/delBranch",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<BranchSummary> deleteOrg(@RequestParam("id") String id,@RequestParam("orgId") Long orgId) 
	{
		int branch_id = Integer.parseInt(id);
		branchService.deleteBranch(branch_id);
		return branchService.getAllBranches(orgId);
	}
	*/
	
	
	//******************************************************************
	// Delete Branch
	@RequestMapping(value = "/delBranch", method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteBranch(@RequestParam("id") String branchID)
		{
			try
				{
				int branch_id = Integer.parseInt(branchID);
				branchService.deleteBranch(branch_id);
					return "redirect:/branchsummary";
				}

			catch (Exception e)
				{
					System.out.println(e.getMessage());
					return "ErrorMsg";
				}
		}

	
	
	
	
	
	//Add branch contact
	@RequestMapping(value = "/addBranchContact", method = RequestMethod.GET)
	public String addBranchContact(@RequestParam("bid") Long bid, ModelMap model) {
		model.addAttribute("branch_id", bid);
		return "AddBranchContact";
	}

	// Add new Branch Contact .
	@RequestMapping(value = "/addNewBranchContact", method = RequestMethod.POST)
	public String addNewBranchContact(BranchContactForm form, Model model) {
		try {
			branchService.saveBranchContact(form);
			return "redirect:/editBranch?bid=" + form.getBranchID();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Edit the Branch Contact .
	@RequestMapping(value = "/editBranchContact", method = RequestMethod.GET)
	public String editBranchContact(@RequestParam("bcid") Long bcid,
			ModelMap model) {
		Branchcontact branchcontact = branchService.getBranchContactById(bcid);
		model.addAttribute("branchContact", branchcontact);
		model.addAttribute("branch_id", branchcontact.getBranchId().getId());
		return "EditBranchContact";
	}

	//Update branch contact
	@RequestMapping(value = "/updateBranchContact", method = RequestMethod.POST)
	public String updateBranchContact(BranchContactForm form, ModelMap model,HttpServletRequest req,HttpServletResponse resp) {
		long branchId = Long.parseLong(req.getParameter("branchId"));
		branchService.updateBranchContact(form);
		return "redirect:/editBranch?bid="+branchId;
	}
	
	//Delete branch contact
	@RequestMapping(value="/deleteBranchContact",method=RequestMethod.GET)
	public String deleteBranchContact(@RequestParam("branchContactId") Long branchContactId,@RequestParam("branchId")Long branchId)
	{	
		branchService.deleteBranchContact(branchContactId);
		return "redirect:/editBranch?bid="+branchId;
	}

	// Getting Branches by organization
	@RequestMapping(value = "/getBranchByOrgId", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<BranchSummary> getBranchByOrgId(@RequestParam String orgId) {
		List<BranchSummary> branches = branchService.getBranchesByOrgId(Long.parseLong(orgId));
		return branches;
	}

}
