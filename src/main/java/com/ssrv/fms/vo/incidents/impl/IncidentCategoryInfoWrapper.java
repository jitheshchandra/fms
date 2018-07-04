package com.ssrv.fms.vo.incidents.impl;

import java.util.Collection;

import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;

public class IncidentCategoryInfoWrapper implements IncidentCategoryInfo {

	Long id;
	String name;
	Collection<Incidentcategory> incidentcategoryCollection;
	IncidentCategoryInfoWrapper parentCategoryId;

	Incidentcategory incidentCategory;

	IncidentCategoryInfo parentCategory;

	public IncidentCategoryInfoWrapper(Incidentcategory incidentCategory) {
		this.incidentCategory = incidentCategory;
		if (incidentCategory.getParentCategory() != null) {
			this.parentCategory = new IncidentCategoryInfoWrapper(
					incidentCategory.getParentCategory());
		}
	}

	@Override
	public Long getId() {
		return incidentCategory.getId();
	}

	@Override
	public String getName() {
		return incidentCategory.getName();
	}

	@Override
	public IncidentCategoryInfo getParentIncidentCategory() {
		return parentCategory;
	}

}
