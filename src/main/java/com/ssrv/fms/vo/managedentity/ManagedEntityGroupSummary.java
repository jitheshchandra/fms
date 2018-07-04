package com.ssrv.fms.vo.managedentity;

import java.util.List;

import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.vo.checklist.CheckListForm;

public class ManagedEntityGroupSummary {

	private Long id;
	private String name;
	private String description;
	private List<CheckListForm> listOfCheckList;

	public ManagedEntityGroupSummary() {
	}

	public ManagedEntityGroupSummary(ManagedEntityGroup managedEntityGroup) {
		this.id = managedEntityGroup.getId();
		this.name = managedEntityGroup.getName();
		this.description = managedEntityGroup.getDescription();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CheckListForm> getListOfCheckList() {
		return listOfCheckList;
	}

	public void setListOfCheckList(List<CheckListForm> listOfCheckList) {
		this.listOfCheckList = listOfCheckList;
	}
}
