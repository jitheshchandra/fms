package com.ssrv.fms.service.managedentity.intf;

import java.util.List;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryView;
import com.ssrv.fms.vo.managedentity.ManagedEntityVo;

public interface IManagedEntityGroupService {

	ManagedEntityGroupSummaryView getManagedEntityGroupSummary(
			ManagedEntityGroupSummaryForm groupSummaryForm) throws FmsException;

	List<ManagedEntityGroupSummaryView> getManagedEntityGroupByBranch(
			Long branchId);
	List<ManagedEntityGroupSummary> getManagedEntityGroupByOrgAndBranch(ManagedEntityGroupSummaryForm groupSummaryForm);
	//***********
	public boolean saveManagedGroup(ManagedEntityGroupSummaryForm form);
	public String deleteManagadGroup(int id);

}
