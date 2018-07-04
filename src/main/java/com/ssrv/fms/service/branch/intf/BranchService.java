package com.ssrv.fms.service.branch.intf;

import java.util.List;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.branch.Branchcontact;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.branch.AddBranchForm;
import com.ssrv.fms.vo.branch.BranchContactForm;
import com.ssrv.fms.vo.branch.BranchContactInfo;
import com.ssrv.fms.vo.branch.BranchForm;

public interface BranchService {

	public List<BranchSummary> getAllBranches(Long orgId);

	public List<BranchSummary> getBranchesByOrgId(Long orgId);

	public void saveBranch(AddBranchForm branch);

	public Branch getBranchById(Long id);

	public List<Branch> getAllBranches();
	public void updateBranch(BranchForm branch);

	public void deleteBranch(int branchID);

	public List<BranchContactInfo> getBranchContactByBranchID(Long bid);
	
	public void saveBranchContact(BranchContactForm branchcontact);

	public Branchcontact getBranchContactById(Long id);

	public void updateBranchContact(BranchContactForm form);

	public Branch getDefaultBranch(Long orgId) throws FmsException;

	public List<Branch> getBranchByOrgId(long ogrId);
	
	public void deleteBranchContact(Long branchContactId);

}
