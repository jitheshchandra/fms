package com.ssrv.fms.vo.Asset;

import java.util.Date;

public class AssetMaintainanceForm {
	private String id;
	private String assetId;
	private String area;
	private String category;
	private String group;
	private String condtion;
	private String criticality;
	private Date lastCheckDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCondtion() {
		return condtion;
	}
	public void setCondtion(String condtion) {
		this.condtion = condtion;
	}
	public String getCriticality() {
		return criticality;
	}
	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}
	public Date getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	
}
