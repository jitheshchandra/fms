package com.ssrv.fms.service.branch.intf;

import java.util.List;

import com.ssrv.fms.model.branch.Branch;

public interface IBranchService {

	List<Branch> getBranchesByOrgId(Long id);

	// public Branch getDefaultBranch(Long orgId) throws FmsException;
}
