package com.ssrv.fms.dao.organization;

import java.util.List;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.branch.Branchcontact;

public interface IBranchDAO extends IBaseDAO {
	List<Branch> getBranchByOrgId(Long id);
	List<Branchcontact> getBranchContactByBranch(Long bid);
}
