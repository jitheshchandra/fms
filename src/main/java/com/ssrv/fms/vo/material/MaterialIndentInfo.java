package com.ssrv.fms.vo.material;

import java.util.List;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.vo.indent.IndentDetailInfoForm;

public interface MaterialIndentInfo {
	Long getId();

	Branch getBranch();
	
	Organization getOrganization();
	
	ManagedEntityGroup getManageEntity();

	String getName();

	List<IndentDetailInfoForm> getIndentDetails();

}
