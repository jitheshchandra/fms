package com.ssrv.fms.vo.incidents;

import java.util.Date;

import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;

public interface IncidentInfo extends IncidentResolveInfo {

	Date getIncidentDate();

	String getIncidentTime();

	ManagedEntityGroupSummary getManagedEntityGroup();

	String getRemarks();

	EscalationMatrixInfo getEscalationMatrix();

	IncidentCategoryInfo getIncidentType();

}
