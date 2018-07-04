package com.ssrv.fms.service.organization.intf;

import java.util.List;

import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.organization.OrganizationTypes;
import com.ssrv.fms.model.organization.Organizationcontacts;
import com.ssrv.fms.vo.organization.AddOrganizationForm;
import com.ssrv.fms.vo.organization.OrganizationContactForm;
import com.ssrv.fms.vo.organization.OrganizationSummary;

public interface OrganizationService {
	
	public List<Organization> getAllOrganizations();
	
	public void saveOrganization(AddOrganizationForm org);
	
	public Organization findOrganizationByID(int i);

	public List<OrganizationTypes> getAllOrganizationTypes();

	public OrganizationTypes findOrganizationTypesByID(long id);

	public List<Organizationcontacts> getAllOrganizationContacts();

	public List<Organizationcontacts> getAllOrganizationContactsByOrgID(long id);

	public Organizationcontacts getAllOrganizationContactsByID(long id);

	public List<Organization> getallOrgnizationNames();

	public List<OrganizationSummary> getallOrganizationDetials();

	public String deleteOrganization(int id);

	public void updateOrganization(AddOrganizationForm org);

	public void updateOrganizationContact(OrganizationContactForm orgContactForm);

	public void addNewOrganizationContact(OrganizationContactForm orgContactForm);

	public List<Organization> getAllServiceConsumerOrganizations();
	
	public Organization getOrganizationById(long organizaionId);

	public List<OrganizationSummary> getallOrganizationDetails();
	
	public void deleteOrganizationContact(Long organizationContactId);
}
