package com.ssrv.fms.service.organization.intf;

import java.util.List;





import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.organization.Organizationcontacts;
import com.ssrv.fms.model.organization.OrganizationTypes;

public interface IOrganizationService 
{
	public List<Organization> getAllOrganizations();
	
	public void saveOrganization(Organization org);
	
	public Organization findOrganizationByID(String id);
	
	public List<OrganizationTypes> getAllOrganizationTypes();
	
	public List<OrganizationTypes> getAllOrganizationTypesByID(String id);
	
	public List<Organizationcontacts> getAllOrganizationContacts();
	
	public List<Organizationcontacts> getAllOrganizationContactsByID();
}
