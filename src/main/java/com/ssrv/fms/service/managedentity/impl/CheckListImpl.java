package com.ssrv.fms.service.managedentity.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.CheckListDetails;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.checklist.Checklisttype;
import com.ssrv.fms.model.managedentity.ManagedEntityInstance;
import com.ssrv.fms.model.managedentity.Managedentitychecklistmap;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;
import com.ssrv.fms.vo.checklist.CheckListDetailInfoView;
import com.ssrv.fms.vo.checklist.CheckListDetailsForm;
import com.ssrv.fms.vo.checklist.CheckListForm;
import com.ssrv.fms.vo.checklist.CheckListInfoView;
import com.ssrv.fms.vo.employee.EmployeeGrooming;

@Service
@Lazy
@Transactional
public class CheckListImpl implements CheckListIntf
	{
		@PersistenceContext
		private EntityManager entityManager;
	
    @Override
	@Transactional
	public List<Checklists> getCheckList(Long orgId, Long brchId)
		{			
			@SuppressWarnings("unchecked")
			List<Checklists> checkList = entityManager.createQuery("SELECT c FROM Checklists c WHERE c.isDeleted=0 AND c.branchId="+brchId+"AND c.organizationId="+orgId).getResultList();
			return checkList;
		}

	@Override
	public List<Checklisttype> getAllCheckListType()
		{
			@SuppressWarnings("unchecked")
			List<Checklisttype> checkListType = entityManager.createQuery("SELECT c FROM Checklisttype c WHERE c.isDeleted=0").getResultList();
			return checkListType;
		}

	@Override
	public Boolean deleteCheckList(Long id)
		{
			Checklists checkList = entityManager.find(Checklists.class,id);
			checkList.setIsDeleted((short)1);
			return true;
		}

	@Override
	public Checklists getCheckListById(Long id)
		{
			Checklists checkList = entityManager.find(Checklists.class,id);
			return checkList;
		}

	@Override
	public List<CheckListDetails> getCheckListDetailByCheckListId(Long id)
		{
			@SuppressWarnings("unchecked")
			List<CheckListDetails> checkListDetails = entityManager.createQuery("SELECT c FROM CheckListDetails c WHERE c.isDeleted=0 AND c.checkList="+id).getResultList();
			return checkListDetails;
		}

	@Override
	public void saveCheckListDetail(CheckListDetailInfoView form)
		{
			List<CheckListDetailsForm> checkListDetails = form.getCheckListdetail();
			for(CheckListDetailsForm checkListDetail:checkListDetails)
				{
					CheckListDetails model=new CheckListDetails();
				//	model.setCheckList(entityManager.find(Checklists.class, form.getCheckListId()));
				//	model.setIsDeleted((short)0);
				//	model.setName(checkListDetail.getName());
				//	model.setGroupName(checkListDetail.getGroupName());
				//	model.setMaxLimit(checkListDetail.getMaxLimits());
				//	model.setMinLimit(checkListDetail.getMinLimits());
					model.setModifiedOn(new Date());
				//	model.setQuantity(checkListDetail.getQuantity());
				//	model.setSeqNo(checkListDetail.getSeqNo());
				//	model.setUomId(checkListDetail.getUOMId());
					entityManager.persist(model);
				}
		}

	@Override
	public void deleteCheckListDetail(Long id)
		{
			CheckListDetails checkListDetail = entityManager.find(CheckListDetails.class, id);
			checkListDetail.setIsDeleted((short)1);
		}

	@Override
	public void saveCheckList(CheckListInfoView form)
		{
			Checklists checkList=null;
			List<CheckListForm> checkLists = form.getCheckListForms();
			for(CheckListForm list:checkLists)
				{
					if(list.getId()!=null)
						{
							checkList=entityManager.find(Checklists.class, list.getId());
							//checkList.setBranchId(entityManager.find(Branch.class,form.getBranchId()));
							//checkList.setOrganizationId(entityManager.find(Organization.class, form.getOraganizationId()));
							//checkList.setCheckListTypeId(entityManager.find(Checklisttype.class, list.getCheckListTypeId()));
						//	checkList.setDefaultCheck(list.getDefaultCheck());
							checkList.setName(list.getCheckListName());
							checkList.setModifiedDate(new Date());
						}
					else{
						checkList=new Checklists();
					//	checkList.setBranchId(entityManager.find(Branch.class,form.getBranchId()));
						//checkList.setOrganizationId(entityManager.find(Organization.class, form.getOraganizationId()));
					//	checkList.setCheckListTypeId(entityManager.find(Checklisttype.class, list.getCheckListTypeId()));
					//	checkList.setDefaultCheck((short)list.getDefaultCheck());
						checkList.setName(list.getCheckListName());
						checkList.setModifiedDate(new Date());
						checkList.setIsDeleted((short)0);
						entityManager.persist(checkList);
					}
				}	
		}
	
	@Override
	public List<Checklists> getChecklistByOrgIdAndBrchId(EmployeeGrooming formData)
		{
			@SuppressWarnings("unchecked")
			List<Checklists> checkList = entityManager.createQuery("SELECT c FROM Checklists c WHERE c.organizationId="+formData.getOrganizationId()+"AND c.branchId="+formData.getBranchId()+"AND c.checkListTypeId=1 AND c.isDeleted=0").getResultList();
			return checkList;
		}

	//Update Managed Entity CheckList by Rest-full-Api
	@Override
	public boolean saveManagedEntityCheckListByRestFullApi(Long managedEntityInstanceId, Long checkListId)
	{
		if(managedEntityInstanceId!=0||managedEntityInstanceId!=null||checkListId!=null||checkListId!=0)
		{
			Managedentitychecklistmap model=new Managedentitychecklistmap();
			model.setCheckListId(entityManager.find(Checklists.class, checkListId));
			model.setFacilityId(entityManager.find(ManagedEntityInstance.class,managedEntityInstanceId));
		}
		else{
			
		}
		return false;
	}
		

	}
