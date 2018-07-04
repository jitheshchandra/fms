package com.ssrv.fms.vo.incidents;

import java.util.List;

import com.ssrv.fms.vo.incidents.impl.IncidentResolveInfoImpl;

public class UpdateIncidentStatusForms {
	private List<IncidentResolveInfoImpl> incidents;

	public void setIncidents(List<IncidentResolveInfoImpl> incidents) {
		this.incidents = incidents;
	}

	public List<IncidentResolveInfoImpl> getIncidents() {
		return incidents;
	}

}
