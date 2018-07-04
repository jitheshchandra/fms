package com.ssrv.fms.vo.incidents.impl;

import java.util.List;

import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;

public class EscalationMatrixInfoForm {

	private BranchSummary branch;
	
	private IncidentCategoryInfo category;
	
	private Long categoryId;

	private List<EscalationMatrixLevelInfoForm> matrixLevel;

	public IncidentCategoryInfo getCategory() {
		return category;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategory(IncidentCategoryInfo category) {
		this.category = category;
	}

	public BranchSummary getBranch() {
		return branch;
	}

	private Long branchId;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setBranch(BranchSummary branch) {
		this.branch = branch;
	}

	public void setMatrixLevel(List<EscalationMatrixLevelInfoForm> matrixLevel) {
		this.matrixLevel = matrixLevel;
	}

	public List<EscalationMatrixLevelInfoForm> getMatrixLevel() {
		return matrixLevel;
	}

}
