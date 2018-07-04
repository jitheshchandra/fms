package com.ssrv.fms.service.organization.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.organization.OrganizationTypes;
import com.ssrv.fms.model.organization.Organizationcontacts;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.organization.AddOrganizationForm;
import com.ssrv.fms.vo.organization.OrganizationContactForm;
import com.ssrv.fms.vo.organization.OrganizationSummary;

@Service
@Lazy
public class OrganizationServiceImpl implements OrganizationService
	{

		@PersistenceContext
		private EntityManager entityManager;

		@Override
		@SuppressWarnings("unchecked")
		public List<Organization> getAllOrganizations()
			{
				List<Organization> orgs = entityManager.createQuery("SELECT O FROM Organization O WHERE O.isDeleted=0").getResultList();
				return orgs;
			}

		@Override
		@Transactional
		public void saveOrganization(AddOrganizationForm form)
			{
				Organization org = new Organization();
				org.setName(form.getName());
				org.setAddress1(form.getAddress1());
				org.setAddress2(form.getAddress2());
				org.setAddress3(form.getAddress3());
				org.setCountryId(entityManager.find(Country.class, Long.parseLong(form.getOrgCountry())));
				org.setStateId(entityManager.find(States.class, Long.parseLong(form.getOrgState())));
				org.setIsDeleted((short) 0);
				OrganizationTypes orgType = entityManager.find(OrganizationTypes.class, form.getOrganizationTypesId());
				org.setOrganizationTypes(orgType);
				org.setPinCode(form.getPinCode());
				entityManager.persist(org);
				
				System.out.println("org id"+org.getId());
				Organizationcontacts orgContact = new Organizationcontacts();
				orgContact.setOrganizationID(org);
				orgContact.setContactPerson(form.getContactPerson());
				orgContact.setContactMobileNo(form.getContactMobileNo());
				orgContact.setContactMailId(form.getContactMailId());
				orgContact.setContactPersonDesignation(form.getContactPersonDesignation());
				entityManager.persist(orgContact);
			}

		@Override
		public Organization findOrganizationByID(int id)
			{
				Organization org = entityManager.find(Organization.class, new Long(id));
				return org;
			}

		@Override
		public List<OrganizationTypes> getAllOrganizationTypes()
			{
				@SuppressWarnings("unchecked")
				List<OrganizationTypes> organizationTypeList = entityManager.createQuery("SELECT O FROM OrganizationTypes O WHERE O.isDeleted=0").getResultList();
				return organizationTypeList;
			}

		@Override
		public OrganizationTypes findOrganizationTypesByID(long id)
			{
				OrganizationTypes orgtype = entityManager.find(OrganizationTypes.class, new Long(id));
				return orgtype;
			}

		@Override
		public List<Organizationcontacts> getAllOrganizationContacts()
			{
				return null;
			}

		@Override
		public List<Organizationcontacts> getAllOrganizationContactsByOrgID(long id)
			{
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				List<Organizationcontacts> OrgContact = entityManager.createQuery(
						"SELECT C from Organization O,Organizationcontacts C where C.isDeleted=0 AND C.organizationID=O.id AND O.id=" + id).getResultList();
				return OrgContact;
			}

		@Override
		public Organizationcontacts getAllOrganizationContactsByID(long id)
			{
				@SuppressWarnings("unchecked")
				List<Organizationcontacts> Org_Contact = entityManager.createQuery("SELECT C from Organizationcontacts C WHERE C.isDeleted=0 AND C.id=" + id)
						.getResultList();
				if (Org_Contact.size() > 0)
					return Org_Contact.get(0);
				else
					return null;
			}

		@Override
		public List<Organization> getallOrgnizationNames()
			{
				@SuppressWarnings("unchecked")
				List<Organization> orgs = entityManager.createQuery("SELECT O.name FROM Organization O WHERE O.isDeleted=0").getResultList();
				return orgs;
			}

		@Override
		public List<OrganizationSummary> getallOrganizationDetials()
			{
				@SuppressWarnings("unchecked")
				List<Organization> orgs = entityManager.createQuery("SELECT O FROM Organization O WHERE O.isDeleted=0").getResultList();
				List<OrganizationSummary> orgSummary = new ArrayList<OrganizationSummary>();
				for (Organization org : orgs)
					{
					    OrganizationSummary oSummary = new OrganizationSummary();
					    Organizationcontacts contect=null;
						@SuppressWarnings("unchecked")
						List<Organizationcontacts> orgContact = entityManager.createQuery("SELECT O FROM Organizationcontacts O WHERE O.isDeleted=0 AND organizationID=" + org.getId()).getResultList();
						if(!orgContact.isEmpty())
						{
						 contect = orgContact.get(0);
						}
						oSummary.setOrgId(org.getId());
						oSummary.setOrgName(org.getName());
						oSummary.setOrgType(org.getOrganizationTypes().getName());
						oSummary.setOrgAddress(org.getAddress1());
						if(contect!=null)
						{
						oSummary.setOrgContactPerson(contect.getContactPerson());
						oSummary.setOrgContactNumber(contect.getContactMobileNo());
						}
						else
						{
							oSummary.setOrgContactPerson("--NA--");
							oSummary.setOrgContactNumber("--NA--");
						}
						orgSummary.add(oSummary);
					}
				return orgSummary;
			}

		@Override
		@Transactional
		public String deleteOrganization(int id)
			{
				
				Organization org = entityManager.find(Organization.class, (long)id);
				if (org != null)
					{
						org.setIsDeleted((short) 1);
						entityManager.merge(org);
						return "entity updated successfully";
						// Note manage.merge method is not called. But Product
						// will be
						// synchronised with the DB
					} else
					return "Entity does not exist";
			}

		@Override
		@Transactional
		public void updateOrganization(AddOrganizationForm u_org)
			{
				Organization org = entityManager.find(Organization.class,u_org.getId());
				org.setAddress1(u_org.getAddress1());
				org.setAddress2(u_org.getAddress2());
				org.setAddress3(u_org.getAddress3());
				org.setName(u_org.getName());
				org.setCountryId(entityManager.find(Country.class,Long.parseLong(u_org.getOrgCountry())));
				org.setStateId(entityManager.find(States.class,Long.parseLong(u_org.getOrgState())));
				org.setModifiedOn(new Date());
			}

		@Override
		@Transactional
		public void updateOrganizationContact(OrganizationContactForm orgContactForm)
			{
				Organizationcontacts orgc = entityManager.find(Organizationcontacts.class, orgContactForm.getContactId());
				if (orgc != null)
					{
						orgc.setContactMailId(orgContactForm.getContactMailId());
						orgc.setContactMobileNo(orgContactForm.getContactMobileNo());
						orgc.setContactPerson(orgContactForm.getContactPerson());
						orgc.setContactPersonDesignation(orgContactForm.getContactPersonDesignation());
						
					}
				entityManager.merge(orgc);
			}

		@Override
		@Transactional
		public void addNewOrganizationContact(OrganizationContactForm orgContactForm)
			{
				Organizationcontacts orgContact = new Organizationcontacts();
				orgContact.setContactMailId(orgContactForm.getContactMailId());
				orgContact.setContactMobileNo(orgContactForm.getContactMobileNo());
				orgContact.setContactPerson(orgContactForm.getContactPerson());
				Organization org = entityManager.find(Organization.class, orgContactForm.getOrgId());
				orgContact.setOrganizationID(org);
				orgContact.setContactPersonDesignation(orgContactForm.getContactPersonDesignation());
            	entityManager.persist(orgContact);
			}

		@Override
		public List<Organization> getAllServiceConsumerOrganizations()
			{
				@SuppressWarnings("unchecked")
				List<Organization> orgs = entityManager.createQuery("SELECT O FROM Organization O WHERE O.isDeleted=0 AND O.organizationTypesId=2").getResultList();
				return orgs;
			}

		public void saveOrganization(Organization org)
			{
			}

		public Organization findOrganizationByID(String id)
			{
				return null;
			}

		public List<OrganizationTypes> getAllOrganizationTypesByID(String id)
			{
				return null;
			}

		@Transactional
		public Organization getOrganizationById(long organizaionId)
			{
				Organization organization = entityManager.find(Organization.class, organizaionId);
				return organization;
			}

		//Get all organization
		@SuppressWarnings("unchecked")
		@Override
		public List<OrganizationSummary> getallOrganizationDetails()
			{
				List<Organization> orgs = entityManager.createQuery("SELECT O FROM Organization O WHERE O.isDeleted=0").getResultList();
				List<OrganizationSummary> orgSummary = new ArrayList<OrganizationSummary>();
				for (Organization org : orgs)
					{
					    Organizationcontacts contects =null;
						List<Organizationcontacts> orgContact = entityManager.createQuery("SELECT O FROM Organizationcontacts O WHERE O.isDeleted=0 AND organizationID=" + org.getId()).getResultList();
						if(!orgContact.isEmpty())
						{
					    contects = orgContact.get(0);
						}
						OrganizationSummary oSummary = new OrganizationSummary();
						oSummary.setOrgId(org.getId());
						oSummary.setOrgName(org.getName());
						oSummary.setOrgType(org.getOrganizationTypes().getName());
						oSummary.setOrgAddress(org.getAddress1());
						if(contects!=null)
						{
						oSummary.setOrgContactPerson(contects.getContactPerson());
						oSummary.setOrgContactNumber(contects.getContactMobileNo());
						}
						orgSummary.add(oSummary);
					}
				return orgSummary;
			}

		//Delete organization contact
		@Override
		@Transactional
		public void deleteOrganizationContact(Long organizationContactId) {
			Organizationcontacts orgContact = entityManager.find(Organizationcontacts.class, organizationContactId);
			orgContact.setIsDeleted((short)1);
		}

	}
