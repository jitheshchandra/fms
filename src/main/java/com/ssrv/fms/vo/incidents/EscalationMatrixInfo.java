package com.ssrv.fms.vo.incidents;

import com.ssrv.fms.vo.BranchSummary;

public interface EscalationMatrixInfo {

	Long getId();

	String getName();

	BranchSummary getBranch();

	int getOrder();

	String getContactName();

	String getContactEmail();

	String getContactPhone();

	String getAction();

	IncidentCategoryInfo getCategory();

	EscalationLevelInfo getLevel();

	// Collection<Incidents> getIncidentsCollection();
}
