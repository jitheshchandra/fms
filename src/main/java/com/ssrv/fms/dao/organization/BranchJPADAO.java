package com.ssrv.fms.dao.organization;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssrv.fms.dao.impl.BaseDAO;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.branch.Branchcontact;

@Component
public class BranchJPADAO extends BaseDAO implements IBranchDAO {

	@Override
	public List<Branch> getBranchByOrgId(Long id) {
		return createCriteria(Branch.class).innerJoin("organization")
				.andEquals("organization.id", id).andEquals("isDeleted", 0).getResultList();
	}

	@Override
	public List<Branchcontact> getBranchContactByBranch(Long bid) {
		return createCriteria(Branchcontact.class).andEquals("branchId", bid).andEquals("isDeleted", 0)
				.getResultList();
	}
}
