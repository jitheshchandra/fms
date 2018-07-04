package com.ssrv.fms.vo.incidents.impl;

import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;

public class EscalationMatrixLevelInfoForm implements EscalationMatrixInfo {

	private Long id;

	private int order;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	private String action;
	private EscalationLevelInfo level;

	/**
	 * Not required.
	 */
	private String name;

	public EscalationMatrixLevelInfoForm() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Override
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Override
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public EscalationLevelInfo getLevel() {
		return level;
	}

	private Long levelId;

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public void setLevel(EscalationLevelInfo level) {
		this.level = level;
	}

	@Override
	public BranchSummary getBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncidentCategoryInfo getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

}
