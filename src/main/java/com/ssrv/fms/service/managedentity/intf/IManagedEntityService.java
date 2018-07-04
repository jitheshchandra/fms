package com.ssrv.fms.service.managedentity.intf;

import java.util.List;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.managedentity.ManagedEntitySubType;
import com.ssrv.fms.model.managedentity.ManagedEntityType;
import com.ssrv.fms.vo.checklist.CheckListDetailsForm;
import com.ssrv.fms.vo.checklist.CheckListSummaryForm;
import com.ssrv.fms.vo.employee.EmployeeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityInstanceView;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeVO;
import com.ssrv.fms.vo.managedentity.ManagedEntityTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityVo;

public interface IManagedEntityService {

	public List<ManagedEntityInstanceView> getManagedEntityInstancesByOrgBranch(
			Long branchId) throws FmsException;

	public List<ManagedEntityInstanceView> getManagedEntityInstanceViewsByOrgBranch(
			Long branchId) throws FmsException;
	
	public boolean saveManagedEntity(ManagedEntityVo form);
	
	public void updateManagedEntityType(ManagedEntityTypeForm form);//*****
	
	public void updateManagedEntitySubType(ManagedEntitySubTypeForm form);
	
	
	public List<ManagedEntityVo> getManagedEntityList();
	
	public String delmangdEntity (int id);

	//save managed entity type .
	public void saveManagedEntityType(ManagedEntityType mEntityType);

	public List<ManagedEntityType> getAllManagedEntityTypes();

	public void deleteManagedEntityType(long id);

	public ManagedEntityType getAllManagedEntityType(long id);

	public ManagedEntitySubType getAllManagedEntitySubTypesbyId(long id);
	

	// Get All managed Entity Sub types by 
	public List<ManagedEntitySubTypeVO> getAllManagedEntitySubTypesbyTypeId(long id);

	public void deleteManagedEntitySubTypesbySubTypeId(long id);

	boolean saveManagedEntitySubType(ManagedEntitySubTypeForm form);//**********
	
	public void saveDefaultChecklists(CheckListDetailsForm form);
	public ManagedEntitySubType getManagedEntitySubType(Long mid);//***
	
	public String savechecklistsummarry(CheckListSummaryForm form);//************

	public ManagedEntityType getManagedEntityTypeById(long id);//*****

	
}