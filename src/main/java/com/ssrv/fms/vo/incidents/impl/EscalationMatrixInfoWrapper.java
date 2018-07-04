package com.ssrv.fms.vo.incidents.impl;

import com.ssrv.fms.model.incident.EscalationMatrix;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;

public class EscalationMatrixInfoWrapper implements EscalationMatrixInfo {

	private final EscalationMatrix escalationMatrix;

	public EscalationMatrixInfoWrapper(EscalationMatrix escalationMatrix) {
		this.escalationMatrix = escalationMatrix;
		this.escalationMatrix.getBranch();
		this.escalationMatrix.getCategory();
	}

	@Override
	public Long getId() {
		return escalationMatrix.getId();
	}

	@Override
	public String getName() {
		return escalationMatrix.getName();
	}

	@Override
	public BranchSummary getBranch() {
		return new BranchSummary(escalationMatrix.getBranch());
	}

	@Override
	public int getOrder() {
		return escalationMatrix.getOrder();
	}

	@Override
	public String getContactName() {
		return escalationMatrix.getContactName();
	}

	@Override
	public String getContactEmail() {
		return escalationMatrix.getContactEmail();
	}

	@Override
	public String getContactPhone() {
		return escalationMatrix.getContactPhone();
	}

	@Override
	public String getAction() {
		return escalationMatrix.getAction();
	}

	@Override
	public IncidentCategoryInfo getCategory() {
		return new IncidentCategoryInfoWrapper(escalationMatrix.getCategory());
	}

	@Override
	public EscalationLevelInfo getLevel() {
		return new EscalationLevelInfoWrapper(escalationMatrix.getLevel());
	}
}
