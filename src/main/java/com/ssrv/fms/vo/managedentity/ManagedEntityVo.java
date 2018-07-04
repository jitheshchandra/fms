package com.ssrv.fms.vo.managedentity;


import com.ssrv.fms.model.managedentity.ManagedEntityInstance;


public class ManagedEntityVo {
	
	private String name;
	private long branch;
	private long organization;
	private String capacity;
	private String slrNo;
	private boolean isMainEntity;
	private long masterEntity;
	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSlrNo() {
		return slrNo;
	}

	public void setSlrNo(String slrNo) {
		this.slrNo = slrNo;
	}

	public boolean isMainEntity() {
		return isMainEntity;
	}

	public void setMainEntity(boolean isMainEntity) {
		this.isMainEntity = isMainEntity;
	}

	public long getMasterEntity() {
		return masterEntity;
	}

	public void setMasterEntity(long masterEntity) {
		this.masterEntity = masterEntity;
	}
	
}
