package com.ssrv.fms.vo.UserBranchMappingForm;

import com.ssrv.fms.model.user.UserBranchMapping;

public class UserBranchMappingForm {
	private Long userBranchMappingId;

	private long userId;

	private long organizationId;

	private long branchId;

	private Long defaultBranchId;

	private Long defaultOrganization;

	public UserBranchMappingForm() {

	}

	public UserBranchMappingForm(UserBranchMapping model) {
		this.userId = Long.parseLong(model.getUserId().getId());
		this.organizationId = model.getOrganizationId().getId();
		this.branchId = model.getBranchId().getId();
		this.defaultBranchId = model.getDefaultBranch();
		this.defaultOrganization = model.getDefaultOrganization();
	}

	public Long getDefaultOrganization() {
		return defaultOrganization;
	}

	public void setDefaultOrganization(Long defaultOrganization) {
		this.defaultOrganization = defaultOrganization;
	}

	public Long getUserBranchMappingId() {
		return userBranchMappingId;
	}

	public void setUserBranchMappingId(Long userBranchMappingId) {
		this.userBranchMappingId = userBranchMappingId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public Long getDefaultBranchId() {
		return defaultBranchId;
	}

	public void setDefaultBranchId(long l) {
		this.defaultBranchId = l;
	}

}
