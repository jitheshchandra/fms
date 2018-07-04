package com.ssrv.fms.service.managedentity.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.managedentity.ManagedEntityInstance;
import com.ssrv.fms.model.managedentity.ManagedEntityMaster;
import com.ssrv.fms.model.managedentity.ManagedEntitySubType;
import com.ssrv.fms.model.managedentity.ManagedEntityType;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityService;
import com.ssrv.fms.vo.checklist.CheckListDetailsForm;
import com.ssrv.fms.vo.checklist.CheckListSummaryForm;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;
import com.ssrv.fms.vo.managedentity.ManagedEntityInstanceView;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeVO;
import com.ssrv.fms.vo.managedentity.ManagedEntityTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityVo;
import com.sun.media.sound.FFT;

@Service
@Lazy
@Transactional
public class ManagedEntityServiceImpl implements IManagedEntityService {

	@PersistenceContext
	private EntityManager entityManager; 

	public List<ManagedEntityInstanceView> getManagedEntityInstanceViewsByOrgBranch(Long branchId) throws FmsException {
		// TODO Auto-generated method stub
		// List<ManagedEntityInstance> managedEntitInstances =
		// getManagedEntityInstancesByOrgBranch(branchId);
		List<ManagedEntityInstanceView> mInstViews = new ArrayList<ManagedEntityInstanceView>();

		/*
		 * for(ManagedEntityInstance mInst : managedEntitInstances){
		 * ManagedEntityInstanceView mInstView = new
		 * ManagedEntityInstanceView();
		 * 
		 * try{ BeanUtil.copyBeans(mInstView, mInst); }
		 * catch(IllegalAccessException e){ throw new FmsException(); }
		 * catch(InvocationTargetException e){ throw new FmsException(); }
		 * catch(Exception e){ throw new FmsException(); }
		 * 
		 * 
		 * mInstViews.add(mInstView); }
		 */

		return mInstViews;
	}

	
	/*
	 * @Override public List<ManagedEntityInstance>
	 * getManagedEntityInstancesByOrgBranch(Long branchId) throws FmsException{
	 * // TODO Auto-generated method stub Branch branch =
	 * entityManager.find(Branch.class, branchId); return
	 * branch.getManagedEntityInstances(); }
	 */

	@Override
	public List<ManagedEntityInstanceView> getManagedEntityInstancesByOrgBranch(Long branchId) {

		List<ManagedEntityInstanceView> managedEntityInstanceViews = new ArrayList<ManagedEntityInstanceView>();

		if (branchId == null) {
			return managedEntityInstanceViews;
		}
		List<ManagedEntityInstance> ManagedEntityInstances = (List<ManagedEntityInstance>) entityManager.createQuery("SELECT EI FROM ManagedEntityInstance EI where EI.branchId=" + branchId + "AND isDeleted=0 ").getResultList();

		for (ManagedEntityInstance managedEntityInstance : ManagedEntityInstances) {
			ManagedEntityInstanceView managedEntityInstanceView = new ManagedEntityInstanceView();
			managedEntityInstanceView.setMainEntityName(managedEntityInstance.getName());
			if(managedEntityInstance.getManagedEntityGroup()==0)
			{
				managedEntityInstanceView.setMasterName("--NA--");
			}
			else
			{
				ManagedEntityInstance mi = entityManager.find(ManagedEntityInstance.class, managedEntityInstance.getManagedEntityGroup());
				managedEntityInstanceView.setMasterName(mi.getName());
			}
		
			
			managedEntityInstanceView.setCapacity(managedEntityInstance.getCapacity());
			managedEntityInstanceView.setSerialNumber(managedEntityInstance.getSerialNumber());
			managedEntityInstanceView.setIsMainEntity(managedEntityInstance.isMainEntity());
			managedEntityInstanceView.setName(managedEntityInstance.getName());
			
		  if(managedEntityInstance.isMainEntity()&& managedEntityInstance.getMainManagedEntityId()!=null)
			{
				managedEntityInstanceView.setMainEntityName("--NA--");
			}
			else if(!managedEntityInstance.isMainEntity()&& managedEntityInstance.getMainManagedEntityId()!=null)
			{
				
				ManagedEntityInstance mainManagedEntityInstance =entityManager.find(ManagedEntityInstance.class,Long.parseLong(managedEntityInstance.getMainManagedEntityId().toString()));
		    	managedEntityInstanceView.setMainEntityName(mainManagedEntityInstance.getName());
				
			}
			else 
			{
				managedEntityInstanceView.setMainEntityName("--NA--");
			}
			managedEntityInstanceView.setId(managedEntityInstance.getId());
            
			managedEntityInstanceViews.add(managedEntityInstanceView);
		}
		return managedEntityInstanceViews;
	}

	/*** Managed Entity ***/

	// Save Managed-Entity
	@Override
	public boolean saveManagedEntity(ManagedEntityVo form) {
		try {
			ManagedEntityInstance entityInstance = new ManagedEntityInstance();
			entityInstance.setBranchId(entityManager.find(Branch.class, form.getBranch()));
			entityInstance.setName(form.getName());
			entityInstance.setCapacity(form.getCapacity());
			if(form.isMainEntity())
			{
			entityInstance.setMainEntity(true);
			entityInstance.setManagedEntityGroup(0);
			entityInstance.setMainManagedEntityId(BigInteger.valueOf(0));
			
			}
			else
			{
				entityInstance.setMainEntity(false);
				entityInstance.setManagedEntityGroup(form.getMasterEntity());
				entityInstance.setMainManagedEntityId(BigInteger.valueOf(form.getMasterEntity()));
			
			}
			
			entityInstance.setSerialNumber(form.getSlrNo());
	
			entityInstance.setMainEntity(form.isMainEntity());
			entityInstance.setIsDeleted((short)0);
			entityManager.persist(entityInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//// Save Managed-Entity-Sub Type**********
	@Override
	public boolean saveManagedEntitySubType(ManagedEntitySubTypeForm form) {
		try{
			ManagedEntitySubType managedEntitySubType=new ManagedEntitySubType();
			managedEntitySubType.setName(form.getName());
			long entityTypeID = Integer.parseInt(form.getManagedEntityTypeId());
			ManagedEntityType managedEntity = entityManager.find(ManagedEntityType.class, (long)entityTypeID);
			managedEntitySubType.setManagedEntityTypeId(managedEntity);
			managedEntitySubType.setDeleted((short) 0);
			entityManager.persist(managedEntitySubType);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	
	

	// Get Managed-Entity list
	// ms vasantha here you have to reaqd all the managed entities which are
	// main entities and show themn in the dropdown ultimately .
	@Override
	public List<ManagedEntityVo> getManagedEntityList() {
		@SuppressWarnings("unchecked")
		List<ManagedEntityInstance> managedEntityList = entityManager
				.createQuery("SELECT m FROM ManagedEntityInstance m WHERE m.isDeleted=0 AND m.isMainEntity=1")
				.getResultList();
		List<ManagedEntityVo> mainList = new ArrayList<ManagedEntityVo>();
		/*
		 * for(ManagedEntityInstance list:managedEntityList) { mainList.add(new
		 * ManagedEntityVo(list)); }
		 */
		return mainList;
	}
//***********************************************************************************
	@Override
	public String delmangdEntity(int id) {
		ManagedEntityInstance delMangEnty = entityManager.find(ManagedEntityInstance.class, (long)id);
		
		if(delMangEnty!=null){
			delMangEnty.setIsDeleted((short) 1);
			 entityManager.merge(delMangEnty);
			 return " Deleted Successfully";
			 }
		     
		  else {
			return "Faild to Delete";
		}}

	
	@Override
	public List<ManagedEntityType> getAllManagedEntityTypes() {
		
		List<ManagedEntityType> itemTypeList = entityManager.createQuery("SELECT M FROM ManagedEntityType M where M.isDeleted=0").getResultList();
		return itemTypeList;
	}
	
	@Override
	@Transactional
	public void saveManagedEntityType(ManagedEntityType mEntityType) {
		// TODO Auto-generated method stub
		ManagedEntityType managedEntityType = new ManagedEntityType();
		managedEntityType.setName(mEntityType.getName());
		managedEntityType.setDeleted((short) 0);
		entityManager.persist(managedEntityType);
	}


	@Override
	@Transactional
	public void deleteManagedEntityType(long id) {
		ManagedEntityType entityType =entityManager.find(ManagedEntityType.class, id);
		
		entityType.setDeleted((short) 1);
		
		entityManager.merge(entityType);
		
	}

	@Override
	public ManagedEntityType getAllManagedEntityType(long id) {
		@SuppressWarnings("unchecked")
		ManagedEntityType itemTypeList = (ManagedEntityType) entityManager.createQuery("SELECT M FROM ManagedEntityType M where M.isDeleted=0 AND M.id="+id).getSingleResult();
		return itemTypeList;
	}

	
	
	@Override
	public ManagedEntityType getManagedEntityTypeById(long id) {
		ManagedEntityType managedEntityType=entityManager.find(ManagedEntityType.class, id);
		return managedEntityType;
	}
	@Override
	public ManagedEntitySubType getAllManagedEntitySubTypesbyId(long mid) {
		ManagedEntitySubType itemType = entityManager.find(ManagedEntitySubType.class, mid);
		return itemType;
	}

	@Override
	public List<ManagedEntitySubTypeVO> getAllManagedEntitySubTypesbyTypeId(long id) {
		@SuppressWarnings("unchecked")
		
		List<ManagedEntitySubTypeVO> entitySubTypes = new ArrayList<ManagedEntitySubTypeVO>();
		List<ManagedEntitySubType> itemType = (List<ManagedEntitySubType>) entityManager.createQuery("SELECT M FROM ManagedEntitySubType M where M.isDeleted=0 AND M.managedEntityTypeId="+id).getResultList();
		
		
		for (ManagedEntitySubType item : itemType) {
			ManagedEntitySubTypeVO entitySubType = new ManagedEntitySubTypeVO();
			entitySubType.setId(item.getId());
			entitySubType.setName(item.getName());	
			long managedEntityTypeID = item.getManagedEntityTypeId().getId();
			ManagedEntityType entityType =entityManager.find(ManagedEntityType.class, managedEntityTypeID);
			entitySubType.setId(item.getId());
			entitySubType.setManagedEntityTypeName(item.getName());	
			entitySubTypes.add(entitySubType);
		}
		return entitySubTypes;
	}

	@Override
	@Transactional
	public void deleteManagedEntitySubTypesbySubTypeId(long id) {
   ManagedEntitySubType delMangEnty = entityManager.find(ManagedEntitySubType.class, (long)id);
		
		if(delMangEnty!=null){
			delMangEnty.setDeleted((short) 1);
			 entityManager.merge(delMangEnty);
			
		}
	}

	@Override
	public void saveDefaultChecklists(CheckListDetailsForm form) {
		
		System.out.println("save the default check lists ");
		
		
	}

	@Override//*********************need to check
	public String savechecklistsummarry(CheckListSummaryForm form) {
		
		Checklists checklists=new Checklists();
		checklists.setName(form.getName());
		checklists.setDescription(form.getDescription());
		Long m_tid=Long.parseLong(form.getManagedEntityTypeId());
		checklists.setManagedEntityType(entityManager.find(ManagedEntityType.class, m_tid));
		Long m_sid=Long.parseLong(form.getManagedEntitySubTypeId());
		checklists.setManagedEntitySubType(entityManager.find(ManagedEntitySubType.class,m_sid));
		checklists.setIsDeleted((short) 0);
		checklists.setModifiedBy(new Date());
		checklists.setKey(form.getKey());
		 Boolean b_defoult = Boolean.parseBoolean(form.getDescription());
		checklists.setDefaultValue(b_defoult);
		checklists.setModifiedDate(new Date());
		
		
		entityManager.persist(checklists);
		
 		return null;
	}

	@Override
	public ManagedEntitySubType getManagedEntitySubType(Long mid) {
		ManagedEntitySubType managedEntitySubType=entityManager.find(ManagedEntitySubType.class, mid);
		return managedEntitySubType;
	}
	
	
	@Override
	@Transactional
	public void updateManagedEntityType(ManagedEntityTypeForm form) {
		ManagedEntityType managedEntityType=entityManager.find(ManagedEntityType.class, form.getId());
		managedEntityType.setName(form.getName());
		managedEntityType.setId(form.getId());
	}

	
       //******************************************
	@Override
	@Transactional
	public void updateManagedEntitySubType(ManagedEntitySubTypeForm form) {
		ManagedEntitySubType managedEntitySubType=entityManager.find(ManagedEntitySubType.class, form.getId());
		managedEntitySubType.setName(form.getName());
		Long m_id=Long.parseLong(form.getManagedEntityTypeId());
		ManagedEntityType managedEntityType=entityManager.find(ManagedEntityType.class, m_id);
		managedEntitySubType.setManagedEntityTypeId(managedEntityType);
	}

	
	

	
	
}
		


