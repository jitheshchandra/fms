package com.ssrv.fms.vo.incidents.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;
import com.ssrv.fms.vo.incidents.IncidentInfoForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;

public class IncidentInfoFormImpl implements IncidentInfoForm {
	private Long id;
	private Date incidentDate;
	private String incidentTime;

	private Long managedEntityId;
	private ManagedEntityGroupSummary managedEntitySummary;

	private String remarks;
	private String status;
	private Date resolvedOn;
	private String resolvedRemarks;

	private Long escalationMatrixId;
	private EscalationMatrixInfo escalationMatrix;

	private Long incidentTypeId;
	private IncidentCategoryInfo incidentType;

	@Override
	public Date getIncidentDate() {
		return incidentDate;
	}

	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public void setIncidentDate(String incidentDate) {
		if (incidentDate != null) {
			try {
				this.incidentDate = format.parse(incidentDate);
			} catch (ParseException e) {
				// TODO: handle exception appropriately..
				e.printStackTrace();
				incidentDate = null;
			}
		} else {
			this.incidentDate = null;
		}
	}

	@Override
	public String getIncidentTime() {
		return incidentTime;
	}

	public void setIncidentTime(String incidentTime) {
		this.incidentTime = incidentTime;
	}

	@Override
	public ManagedEntityGroupSummary getManagedEntityGroup() {
		return managedEntitySummary;
	}

	public void setManagedEntityGroup(
			ManagedEntityGroupSummary managedEntitySummary) {
		this.managedEntitySummary = managedEntitySummary;
	}

	@Override
	public Long getManagedEntityId() {
		return managedEntityId;
	}

	public void setManagedEntityId(Long managedEntityId) {
		this.managedEntityId = managedEntityId;
	}

	@Override
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Date getResolvedOn() {
		return resolvedOn;
	}

	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}

	@Override
	public String getResolvedRemarks() {
		return resolvedRemarks;
	}

	public void setResolvedRemarks(String resolvedRemarks) {
		this.resolvedRemarks = resolvedRemarks;
	}

	@Override
	public EscalationMatrixInfo getEscalationMatrix() {
		return escalationMatrix;
	}

	@Override
	public IncidentCategoryInfo getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentCategoryInfo incidentType) {
		this.incidentType = incidentType;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long getEscalationMatrixId() {
		return escalationMatrixId;
	}

	public void setEscalationMatrixId(Long escalationMatrixId) {
		this.escalationMatrixId = escalationMatrixId;
	}

	public void setEscalationMatrix(EscalationMatrixInfo escalationMatrix) {
		this.escalationMatrix = escalationMatrix;
	}

	@Override
	public Long getIncidentTypeId() {
		return incidentTypeId;
	}

	public void setIncidentTypeId(Long incidentTypeId) {
		this.incidentTypeId = incidentTypeId;
	}
}
