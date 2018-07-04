package com.ssrv.fms.service.managedentity.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityGroupService;
import com.ssrv.fms.service.organization.intf.IOrganizationViewService;
import com.ssrv.fms.util.beans.BeanUtil;
import com.ssrv.fms.vo.checklist.CheckListForm;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryView;

@Service
@Lazy
@Transactional
public class ManagedEntityGroupServiceImpl implements IManagedEntityGroupService {
	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	@Transactional(readOnly = true)
	public List<ManagedEntityGroupSummaryView> getManagedEntityGroupByBranch(Long branchId) {
		List<ManagedEntityGroup> managedEntityGroupList = factory.createEntityManager()
				.createQuery(
						"SELECT m FROM ManagedEntityGroup m inner join m.branch branch WHERE branch.id = :branchId")
				.setParameter("branchId", branchId).getResultList();

		List<ManagedEntityGroupSummaryView> megvList = new ArrayList<ManagedEntityGroupSummaryView>();
		for (ManagedEntityGroup meg : managedEntityGroupList) {
			megvList.add(new ManagedEntityGroupSummaryView(meg));
		}
		return megvList;
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BranchService branchService;

	@Autowired
	private IOrganizationViewService organizationViewService;

	@Transactional
	public ManagedEntityGroupSummaryView getManagedEntityGroupSummary(ManagedEntityGroupSummaryForm groupSummaryForm)
			throws FmsException {
		ManagedEntityGroupSummaryView megSummView = new ManagedEntityGroupSummaryView();

		Long branchId = groupSummaryForm.getBranchId();
		Branch branch = null;
		if (branchId == null || branchId == 0) {
			branch = branchService.getDefaultBranch(groupSummaryForm.getOrganizationId());
		}

		branch = entityManager.find(Branch.class, branchId);
		Collection<ManagedEntityGroup> managedEntityGroups = branch.getManagedEntityGroups();
		List<ManagedEntityGroupSummary> managedEntityGroupSummaries = new ArrayList<ManagedEntityGroupSummary>();
		for (ManagedEntityGroup meGroup : managedEntityGroups) {
			// Collection<Checklists> checkList = meGroup.getChecklistId();
			@SuppressWarnings("unchecked")
			Collection<Checklists> checkList = entityManager.createQuery(
					"SELECT c FROM Checklists c WHERE c.isDeleted=0 AND c.managedEntityGroupId=" + meGroup.getId())
					.getResultList();
			ManagedEntityGroupSummary megSummary = new ManagedEntityGroupSummary();
			List<CheckListForm> checkListForms = new ArrayList<CheckListForm>();

			for (Checklists list : checkList) {
				checkListForms.add(new CheckListForm(list));
			}
			megSummary.setListOfCheckList(checkListForms);

			try {
				System.out.println("grp name : " + meGroup.getName());
				BeanUtil.copyBeans(megSummary, meGroup);
			} catch (IllegalAccessException e) {
				// log it
				throw new FmsException(e.getMessage());
			} catch (InvocationTargetException e) {
				// log it
				throw new FmsException(e.getMessage());
			}
			managedEntityGroupSummaries.add(megSummary);
		}
		megSummView.setManagedEntityGroupSummaries(managedEntityGroupSummaries);
		megSummView.setOrganizations(organizationViewService.getAllOrganizations());
		return megSummView;
	}

	// 19/8/2015
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagedEntityGroupSummary> getManagedEntityGroupByOrgAndBranch(
			ManagedEntityGroupSummaryForm groupSummaryForm) {

		List<ManagedEntityGroupSummary> managedEntityGroupSummaries = new ArrayList<ManagedEntityGroupSummary>();

		if (groupSummaryForm == null) {
			return managedEntityGroupSummaries;
		}

		List<ManagedEntityGroup> ManagedEntityGroups = (List<ManagedEntityGroup>) entityManager
				.createQuery("SELECT E From ManagedEntityGroup E where E.branch=" + groupSummaryForm.getBranchId()
						+ " AND E.organization=" + groupSummaryForm.getOrganizationId() + " AND E.isDeleted=0")
				.getResultList();

		for (ManagedEntityGroup managedEntityGroups : ManagedEntityGroups) {
			ManagedEntityGroupSummary managedEntityGroupSummary = new ManagedEntityGroupSummary();
			managedEntityGroupSummary.setName(managedEntityGroups.getName());
			managedEntityGroupSummary.setDescription(managedEntityGroups.getDescription());
			managedEntityGroupSummary.setId(managedEntityGroups.getId());

			managedEntityGroupSummaries.add(managedEntityGroupSummary);
		}
		return managedEntityGroupSummaries;
	}

	@Override
	public boolean saveManagedGroup(ManagedEntityGroupSummaryForm form) {
		try {

			
			//String[] a=form.getName().split(",");
			//String[] b=form.getDescription().split(",");

			
			ManagedEntityGroup entityGroup = new ManagedEntityGroup();
			entityGroup.setName(form.getName());
			entityGroup.setDescription(form.getDescription());
			//entityGroup.setId(form.getId());
		//	for()
			entityGroup.setModifiedDate(new Date());
			entityGroup.setIsDeleted((short) 0);
			entityGroup.setBranch(entityManager.find(Branch.class, form.getBranchId()));
			entityGroup.setOrganization(entityManager.find(Organization.class, form.getOrganizationId()));
			entityManager.persist(entityGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String deleteManagadGroup(int id) {

		
		ManagedEntityGroup delMangGrp = entityManager.find(ManagedEntityGroup.class, (long)id);

		if (delMangGrp != null) {
			delMangGrp.setIsDeleted((short) 1);
			entityManager.merge(delMangGrp);
			return " Deleted Successfully";
		}

		else {
			return "Faild to Delete";
		}

	}
}
