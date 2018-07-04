package com.ssrv.fms.service.branch.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.organization.IBranchDAO;
import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.branch.Branchcontact;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.branch.AddBranchForm;
import com.ssrv.fms.vo.branch.BranchContactForm;
import com.ssrv.fms.vo.branch.BranchContactInfo;
import com.ssrv.fms.vo.branch.BranchContactInfoWrapper;
import com.ssrv.fms.vo.branch.BranchForm;

@Service
@Lazy
public class BranchServiceImpl implements BranchService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	IBranchDAO branchDao;

	// /Getting All Branches
	@Override
	public List<BranchSummary> getAllBranches(Long orgId) {
		
		List<Branch> branchs = entityManager.createQuery("SELECT b FROM Branch b where b.isDeleted=0 AND b.organization="+ orgId).getResultList();
		List<BranchSummary> branchSummaries = new ArrayList<BranchSummary>();
		for (Branch branch : branchs) {
			@SuppressWarnings("unchecked")
			List<Branchcontact> branchContact = entityManager.createQuery("SELECT b FROM Branchcontact b WHERE branchId="+ branch.getId()).getResultList();
			BranchSummary branchSummary = new BranchSummary();
			branchSummary.setBranchName(branch.getBranchName());
			branchSummary.setAddress(branch.getAddress1());
			branchSummary.setId(branch.getId());
			Organization org = entityManager.find(Organization.class, branch.getOrganization().getId());
			branchSummary.setOrganization(org.getName());
			branchSummary.setOrganizationId(org.getId());

			if (branchContact.size() > 0) {
				branchSummary.setContactPerson(branchContact.get(0).getContactPerson());
				branchSummary.setContactNumber(branchContact.get(0).getContactPersonMobile());

			} else {
				branchSummary.setContactPerson("-- NA --");
				branchSummary.setContactNumber("-- NA --");
			}
			branchSummaries.add(branchSummary);
		}
		return branchSummaries;
	}

	//Saving branches
	@Override
	@Transactional
	public void saveBranch(AddBranchForm form) {
		try{
		Branch branch = new Branch();
		branch.setAddress1(form.getAddress1());
		branch.setAddress2(form.getAddress2());
		branch.setAddress3(form.getAddress3());
		branch.setBranchName(form.getBranchName());
		Organization org =(entityManager.find(Organization.class,  getOrganizationIdByName(form.getOrganizationName())));
		branch.setCountry(entityManager.find(Country.class,  Long.parseLong(form.getBranchCountry())));
		branch.setStateId(entityManager.find(States.class, Long.parseLong(form.getBranchState())));
		branch.setOrganization(org);
		branch.setPinCode(form.getPinCode());
		branch.setIsDeleted((short) 0);
		entityManager.persist(branch);
		Branchcontact branchContact = new Branchcontact();
		branchContact.setBranchId(branch);
		branchContact.setContactPerson(form.getContactPerson());
		branchContact.setContactPersonMailId(form.getContactMailId());
		branchContact.setContactPersonMobile(form.getContactMobileNo());
		entityManager.persist(branchContact);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public long getOrganizationIdByName(String orgName)
	{
		long org = (long) entityManager.createQuery("SELECT O.id FROM Organization O where O.isDeleted=0 AND O.name='"+ orgName+"'").getSingleResult();
		return org;
	}
	
	//Getting branch based on id
	@Override
	public Branch getBranchById(Long id) {
    	Branch branch = entityManager.find(Branch.class, id);
		return branch;
	}

	//Updating branches based on id
	@Override
	@Transactional
	public void updateBranch(BranchForm form) {
		Branch branch = entityManager.find(Branch.class, form.getId());
		branch.setBranchName(form.getBranchName());
		branch.setAddress1(form.getAddress1());
		branch.setAddress2(form.getAddress2());
		branch.setAddress3(form.getAddress3());
		Organization org = entityManager.find(Organization.class,form.getOrganizationId());
		branch.setCountry(entityManager.find(Country.class, form.getBranchCountry()));
		branch.setStateId(entityManager.find(States.class, form.getBranchState()));
		branch.setOrganization(org);
		branch.setPinCode(form.getPinCode());
		entityManager.merge(branch);
	}

	// /Deleting branches based on id
	@Override
	@Transactional
	public void deleteBranch(int branchID) {
		
		Branch branch = entityManager.find(Branch.class, (long) branchID);
		if(branch != null ){
			
		branch.setIsDeleted((short) 1);
		entityManager.merge(branch);
		
			}
			
		}
	
	
	
	
	
	
	
	// /Saving branch contacts
	@Override
	@Transactional
	public void saveBranchContact(BranchContactForm form) {
		Branchcontact branchContact = new Branchcontact();
		Branch branch = entityManager.find(Branch.class, form.getBranchID());
		branchContact.setBranchId(branch);
		branchContact.setContactPerson(form.getContactPerson());
		branchContact.setContactPersonMailId(form.getContactMailId());
		branchContact.setContactPersonMobile(form.getContactMobileNo());
		branchContact.setContactPersonDesignation(form.getContactPersonDesignation());
		branchContact.setModifiedOn(new Date());
		branchContact.setIsDeleted((short)0);
		entityManager.persist(branchContact);
	}

	// /Getting Branches contact by using id
	@Override
	@Transactional
	public List<BranchContactInfo> getBranchContactByBranchID(Long bid) {
		List<Branchcontact> branchContacts = branchDao.getBranchContactByBranch(bid);
		List<BranchContactInfo> branchContactInfoList = new ArrayList<BranchContactInfo>();
		for (Branchcontact contactInfo : branchContacts) {
			branchContactInfoList.add(new BranchContactInfoWrapper(contactInfo));
		}
		return branchContactInfoList;
	}
	
	// /Getting branch contacts based on branch id
	@Override
	public Branchcontact getBranchContactById(Long id) {
		Branchcontact branchcontact = entityManager.find(Branchcontact.class,id);
		return branchcontact;
	}

	// /Updating branch contacts
	@Override
	@Transactional
	public void updateBranchContact(BranchContactForm form) {
		Branchcontact branchContact = entityManager.find(Branchcontact.class,form.getContactId());
		branchContact.setContactPerson(form.getContactPerson());
		branchContact.setContactPersonMailId(form.getContactMailId());
		branchContact.setContactPersonMobile(form.getContactMobileNo());
		branchContact.setContactPersonDesignation(form.getContactPersonDesignation());
	}

	// /Getting Branches Based on Organization Id
	@Override
	@Transactional
	public List<BranchSummary> getBranchesByOrgId(Long orgId) {
		List<Branch> branches = branchDao.getBranchByOrgId(orgId);
		List<BranchSummary> branchSummaryList = new ArrayList<BranchSummary>();
		for (Branch branch : branches) {
			branchSummaryList.add(new BranchSummary(branch));
		}
		return branchSummaryList;
	}

	//to getDefault Organization
	@Override
	public Branch getDefaultBranch(Long orgId) throws FmsException {
		// TODO Auto-generated method stub
		return null;
	}

	//Getting branches based on organization id
	@Override
	public List<Branch> getBranchByOrgId(long ogrId) {
		@SuppressWarnings("unchecked")
		List<Branch> branch = entityManager.createQuery(
				"SELECT b FROM Branch b where b.isDeleted=0 AND b.organization="
						+ ogrId).getResultList();
		return branch;
	}

	//Delete branch contact 
	@Override
	@Transactional
	public void deleteBranchContact(Long branchContactId) {
		Branchcontact branchContact = branchDao.findById(Branchcontact.class, branchContactId);
		branchContact.setIsDeleted((short)1);
		entityManager.merge(branchContact);
	}

	@Override
	public List<Branch> getAllBranches() {
		List<Branch> branch=entityManager.createQuery("SELECT b FROM Branch b where b.isDeleted=0 ").getResultList();
		return branch;
	}
}
