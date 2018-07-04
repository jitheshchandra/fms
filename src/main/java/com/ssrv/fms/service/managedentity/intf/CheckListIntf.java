package com.ssrv.fms.service.managedentity.intf;

import java.util.List;

import com.ssrv.fms.model.checklist.CheckListDetails;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.checklist.Checklisttype;
import com.ssrv.fms.vo.checklist.CheckListDetailInfoView;
import com.ssrv.fms.vo.checklist.CheckListInfoView;
import com.ssrv.fms.vo.employee.EmployeeGrooming;

public interface CheckListIntf
	{
		List<Checklists> getCheckList(Long orgId, Long brchId);

		List<Checklisttype> getAllCheckListType();

		Boolean deleteCheckList(Long id);

		Checklists getCheckListById(Long id);

		List<CheckListDetails> getCheckListDetailByCheckListId(Long id);

		void saveCheckListDetail(CheckListDetailInfoView form);

		void deleteCheckListDetail(Long id);

		void saveCheckList(CheckListInfoView form);
		
		public List<Checklists> getChecklistByOrgIdAndBrchId(EmployeeGrooming formData);
		
		public boolean saveManagedEntityCheckListByRestFullApi(Long managedEntityId,Long checkListId);
	}
