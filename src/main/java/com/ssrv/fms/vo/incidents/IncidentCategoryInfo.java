package com.ssrv.fms.vo.incidents;

public interface IncidentCategoryInfo {

	Long getId();

	String getName();

	IncidentCategoryInfo getParentIncidentCategory();
}
