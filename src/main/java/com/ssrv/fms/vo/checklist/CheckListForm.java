package com.ssrv.fms.vo.checklist;

import com.ssrv.fms.model.checklist.Checklists;

public class CheckListForm {
	private Long id;
	private String checkListName;
	private Long checkListTypeId;
	private Long managedEntityId;
	private Long mangedEntityGroupId;
	private Short defaultCheck;
	private String additionalInf;
	private int isDeleted;
	private long modifiedBy;
	private Boolean defaultChecklist;

	public CheckListForm() {
		// TODO Auto-generated constructor stub
	}

	public CheckListForm(Checklists checkList) {
		this.id = checkList.getId();
		this.checkListName = checkList.getName();
		//this.checkListTypeId = checkList.getCheckListTypeId().getId();
		/*if(checkList.getManagedEntityId()!=null)
	    this.managedEntityId=checkList.getManagedEntityId().getId();
		if(checkList.getManagedEntityGroupId()!=null)
	    this.mangedEntityGroupId=checkList.getManagedEntityGroupId().getId();*/
	//	this.defaultCheck = checkList.getDefaultCheck();
		///if (checkList.getDefaultCheck() == 0) 
		//{
		//	this.defaultChecklist = true;
		//} 
		//else {
		//	this.defaultChecklist = false;
		//}
	}

	public Boolean getDefaultChecklist() {
		return defaultChecklist;
	}

	public void setDefaultChecklist(Boolean defaultChecklist) {
		this.defaultChecklist = defaultChecklist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckListName() {
		return checkListName;
	}

	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}

	public Long getCheckListTypeId() {
		return checkListTypeId;
	}

	public void setCheckListTypeId(Long checkListTypeId) {
		this.checkListTypeId = checkListTypeId;
	}

	public String getAdditionalInf() {
		return additionalInf;
	}

	public void setAdditionalInf(String additionalInf) {
		this.additionalInf = additionalInf;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setDefaultCheck(Short defaultCheck) {
		this.defaultCheck = defaultCheck;
	}

	public Short getDefaultCheck() {
		return defaultCheck;
	}

	public Long getManagedEntityId() {
		return managedEntityId;
	}

	public void setManagedEntityId(Long managedEntityId) {
		this.managedEntityId = managedEntityId;
	}

	public Long getMangedEntityGroupId() {
		return mangedEntityGroupId;
	}

	public void setMangedEntityGroupId(Long mangedEntityGroupId) {
		this.mangedEntityGroupId = mangedEntityGroupId;
	}
}
