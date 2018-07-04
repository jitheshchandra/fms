package com.ssrv.fms.vo.incidents.impl;

import java.util.Date;

import com.ssrv.fms.model.incident.Incidents;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;
import com.ssrv.fms.vo.incidents.IncidentInfo;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;

public class IncidentInfoWrapper implements IncidentInfo {
	private final Incidents incidents;
	private final EscalationMatrixInfo matrixEscalationInfo;
	private final IncidentCategoryInfo incidentCategoryInfo;

	public IncidentInfoWrapper(Incidents incidents) {
		this.incidents = incidents;
		matrixEscalationInfo = new EscalationMatrixInfoWrapper(
				incidents.getEscalationMatrix());
		incidentCategoryInfo = new IncidentCategoryInfoWrapper(
				incidents.getIncidentType());
		incidents.getManagedEntity();
	}

	@Override
	public Long getId() {
		return incidents.getId();
	}

	@Override
	public Date getIncidentDate() {
		return incidents.getIncidentDate();
	}

	@Override
	public String getIncidentTime() {
		return incidents.getIncidentTime();
	}

	@Override
	public ManagedEntityGroupSummary getManagedEntityGroup() {
		return new ManagedEntityGroupSummary(incidents.getManagedEntity());
	}

	@Override
	public String getRemarks() {
		return incidents.getRemarks();
	}

	@Override
	public String getStatus() {
		return incidents.getStatus();
	}

	@Override
	public Date getResolvedOn() {
		return incidents.getResolvedOn();
	}

	@Override
	public String getResolvedRemarks() {
		return incidents.getResolvedRemarks();
	}

	@Override
	public EscalationMatrixInfo getEscalationMatrix() {
		return matrixEscalationInfo;
	}

	@Override
	public IncidentCategoryInfo getIncidentType() {
		return incidentCategoryInfo;
	}

}
