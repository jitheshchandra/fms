package com.ssrv.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.organization.Organizationcontacts;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.organization.AddOrganizationForm;
import com.ssrv.fms.vo.organization.OrganizationContactForm;
import com.ssrv.fms.vo.organization.OrganizationSummary;

@Controller
public class OrganizationController
	{

		@Autowired
		private OrganizationService organizationServiceImpl;
		HttpSession session = null;

		// Read and Return the Organization from the Database
		@RequestMapping(value = "/orgSummary", method = RequestMethod.GET)
		public String orgSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			{
				// true - create new session if required
				HttpSession session = request.getSession(true);
				try
					{
						if (session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
							{
								return "login";
							}
						List<OrganizationSummary> orgs = organizationServiceImpl.getallOrganizationDetials();
						model.addAttribute("orgSummary_detail", orgs);
						return "orgSummary";
					} catch (Exception ex)
					{
						System.out.println(ex.getMessage());
						return "ErrorMsg";
					}
			}

		// Provision to edit the Organization Details .When user selects a
		// organization.
		@RequestMapping(value = "/orgdetails", method = RequestMethod.GET)
		public String orgdetails(@RequestParam String id, ModelMap model)
			{
				Organization org = organizationServiceImpl.findOrganizationByID(Integer.parseInt(id));
				List<Organizationcontacts> orgContact = organizationServiceImpl.getAllOrganizationContactsByOrgID(Integer.parseInt(id));
			    model.addAttribute("Org", org);
				model.addAttribute("orgContact", orgContact);
				return "orgdetails";
			}

		// AddOrganization .Redirect to View .
		@RequestMapping(value = "/addOrganization", method = RequestMethod.GET)
		public String addOrgization()
			{
				return "AddOrganization";
			}

		// store the new organization into the DataBase .
		@RequestMapping(value = "/addNewOrg", method = RequestMethod.POST)
		public String addOrg(AddOrganizationForm org, ModelMap model)
			{
				try
					{	
						organizationServiceImpl.saveOrganization(org);
					    model.addAttribute("Org_Saved","Organization Saved");
						return "redirect:/orgSummary";
					}

				catch (Exception e)
					{
						System.out.println(e.getMessage());
						model.addAttribute("Org_Saved","Could not save Organization");
						return "ErrorMsg";
					}

			}

		// Update Organization Details .
		@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
		public String updateOrg(AddOrganizationForm org, BindingResult bindingResult, ModelMap model)
			{
				try
					{
						organizationServiceImpl.updateOrganization(org);
					    model.addAttribute("Org_Saved",org.getName()+"Organization Saved Successfully");
						return "redirect:/orgSummary";
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						model.addAttribute("Org_Saved", "Could not save Organization");
						return "redirect:/orgdetials?id="+org.getId();
					}
			}

		// Delete organization.
		@RequestMapping(value = "/delOrg", method = RequestMethod.GET)
		public String deleteOrg(@RequestParam("id") String id)
			{
				try
					{
						organizationServiceImpl.deleteOrganization(Integer.parseInt(id));
						return "redirect:orgSummary";
					}

				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		// Add new organization contact
		@RequestMapping(value = "/addOrgContact", method = RequestMethod.GET)
		public String Contact(@RequestParam("Oid") Long Oid, ModelMap model)
			{
				try
					{
						model.addAttribute("org_id", Oid);
						return "addOrgContact";
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}

			}

		@RequestMapping(value = "/addNewOrgContact", method = RequestMethod.POST)
		public String addNewOrgContact(OrganizationContactForm orgCon, ModelMap model)
			{
				try
					{
						// implement the add organization methods here .
						OrganizationContactForm orgCForm = orgCon;
						organizationServiceImpl.addNewOrganizationContact(orgCForm);
						return "redirect:/orgdetails?id=" + orgCForm.getOrgId();
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		//Edit organization contact 
		@RequestMapping(value = "/editOrgContact", method = RequestMethod.GET)
		public String editOrgContact(@RequestParam("id") long id, ModelMap model)
			{
				try
					{
					Organizationcontacts orgContact = organizationServiceImpl.getAllOrganizationContactsByID(id);
						model.addAttribute("EditOrgContact", orgContact);
						return "editOrgContact";
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		//Update organization contact
		@RequestMapping(value = "/UpdateOrgContact", method = RequestMethod.POST)
		public String UpdateOrgContact(OrganizationContactForm form, BindingResult bindingResult, ModelMap model)
			{
				try
					{
						organizationServiceImpl.updateOrganizationContact(form);
						return "redirect:/orgSummary";
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}
		
		//Delete organization contact
		@RequestMapping(value="/deleteOrganizationContact",method=RequestMethod.GET)
		public String deleteOrganizationContact(@RequestParam String orgContactId,@RequestParam String orgId)
		{
			organizationServiceImpl.deleteOrganizationContact(Long.parseLong(orgContactId));
			return "redirect:/orgdetails?id="+orgId;
		}
		
		//Get all organization summary
		@RequestMapping(value = "/getOrganizations", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody List<OrganizationSummary> getOrganization()
			{
				List<OrganizationSummary> organizations = organizationServiceImpl.getallOrganizationDetails();
				return organizations;
			}

	}
