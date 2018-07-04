package com.ssrv.fms.vo.incidents.impl;

import org.apache.commons.lang3.Validate;

import com.ssrv.fms.model.incident.Escalationlevel;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;

public class EscalationLevelInfoWrapper implements EscalationLevelInfo {

	private final Escalationlevel escalationLevel;

	public EscalationLevelInfoWrapper(Escalationlevel escalationLevel) {
		Validate.notNull(escalationLevel, "escalation level cannot be null");
		this.escalationLevel = escalationLevel;
	}

	@Override
	public Long getId() {
		return escalationLevel.getId();
	}

	@Override
	public String getName() {
		return escalationLevel.getName();
	}

}
