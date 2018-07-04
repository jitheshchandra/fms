package com.ssrv.fms.vo.Asset;

import java.util.Date;

public class AssetWarrantyForm {
	private String id;
	private String assetId;
	private String assetCode;
	private String warrantyCode;
	private String warrantyType;
	private Date warrantyStart;
	private Date warrantyEnd;
			
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
	public String getWarrantyCode() {
		return warrantyCode;
	}
	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}
	public String getWarrantyType() {
		return warrantyType;
	}
	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}
	public Date getWarrantyStart() {
		return warrantyStart;
	}
	public void setWarrantyStart(Date warrantyStart) {
		this.warrantyStart = warrantyStart;
	}
	public Date getWarrantyEnd() {
		return warrantyEnd;
	}
	public void setWarrantyEnd(Date warrantyEnd) {
		this.warrantyEnd = warrantyEnd;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}		
}
