package com.ssrv.fms.vo.Asset;

import com.ssrv.fms.model.assets.AssetCondition;

public class AssetConditionForm {
	
	private String id;
	private String conditionName;
	private String description;
	
	public AssetConditionForm(){
		
	}
	
	public  AssetConditionForm(AssetCondition assetCondition) {
		this.id=assetCondition.getId()+"";
		this.conditionName=assetCondition.getName();
	}
		
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the conditionName
	 */
	public String getConditionName() {
		return conditionName;
	}
	/**
	 * @param conditionName the conditionName to set
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
}
