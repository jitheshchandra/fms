package com.ssrv.fms.vo.incidents.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.service.incident.IIncidentService;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;

public class IncidentCategoryInfoForm implements IncidentCategoryInfo {

	Long id;
	String name;

	Incidentcategory incidentCategory;

	IncidentCategoryInfo parentCategory;

	private String parentCategoryId;

	@Autowired
	private IIncidentService incidentService;

	public IncidentCategoryInfoForm() {
	}

	public IncidentCategoryInfoForm(String name, IncidentCategoryInfo parent) {
		this.name = name;
		this.parentCategory = parent;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IncidentCategoryInfo getParentIncidentCategory() {
		return parentCategory;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public void setParentCategory(IncidentCategoryInfo incidentCategory) {
		this.parentCategory = incidentCategory;

	}

}
