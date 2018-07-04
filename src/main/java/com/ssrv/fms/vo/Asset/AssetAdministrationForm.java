package com.ssrv.fms.vo.Asset;

import java.util.Date;

public class AssetAdministrationForm {
	private String id;
	private String assetId;
	private String assetCode;
	private String purchasePrice;
	private String purchaceRequest;
	private String invoiceNo;
	private String currentValue;
	private Date disposalDate;
	private Integer yearOfLife;
	private Date amortaizationStart;
	private Date amortaizationEnd;
	private String annualAmortaization;
	private String fullyDepricated;	
		
	public 	String getId() {
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
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getPurchaceRequest() {
		return purchaceRequest;
	}
	public void setPurchaceRequest(String purchaceRequest) {
		this.purchaceRequest = purchaceRequest;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	public Date getDisposalDate() {
		return disposalDate;
	}
	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}
	public Integer getYearOfLife() {
		return yearOfLife;
	}
	public void setYearOfLife(Integer yearOfLife) {
		this.yearOfLife = yearOfLife;
	}
	public Date getAmortaizationStart() {
		return amortaizationStart;
	}
	public void setAmortaizationStart(Date amortaizationStart) {
		this.amortaizationStart = amortaizationStart;
	}
	public Date getAmortaizationEnd() {
		return amortaizationEnd;
	}
	public void setAmortaizationEnd(Date amortaizationEnd) {
		this.amortaizationEnd = amortaizationEnd;
	}
	public String getAnnualAmortaization() {
		return annualAmortaization;
	}
	public void setAnnualAmortaization(String annualAmortaization) {
		this.annualAmortaization = annualAmortaization;
	}
	public String getFullyDepricated() {
		return fullyDepricated;
	}
	public void setFullyDepricated(String fullyDepricated) {
		this.fullyDepricated = fullyDepricated;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
}
