package com.ssrv.fms.vo.Asset;

import java.util.Date;

public class AssetInstallationForm {
	private String id;
	private String assetId;
	private Date insatllationDate;
	private Date acceptanceDate;
	private String installer;
	private String assetCode;
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
	public Date getInsatllationDate() {
		return insatllationDate;
	}
	public void setInsatllationDate(Date insatllationDate) {
		this.insatllationDate = insatllationDate;
	}
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	public String getInstaller() {
		return installer;
	}
	public void setInstaller(String installer) {
		this.installer = installer;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
}
