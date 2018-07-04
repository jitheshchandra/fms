package com.ssrv.fms.vo.Asset;

public class AssetForm {
	/**
	 * @return the isAvailable
	 */
	public byte getIsAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	private String id;
	private String name;
	private String assetCategory;
	private String assetCondition;
	private String parentAsset;
	private String code;
	private String description;
	private String type;
	private byte isAvailable;
	private String manufacturer;
	private String user;
	private String supplier;
	private String model;
	private String assetUseId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getAssetCondition() {
		return assetCondition;
	}

	public void setAssetCondition(String assetCondition) {
		this.assetCondition = assetCondition;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte isAvailable() {
		return isAvailable;
	}

	public void setAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAssetUseId() {
		return assetUseId;
	}

	public void setAssetUseId(String assetUseId) {
		this.assetUseId = assetUseId;
	}

	public String getParentAsset() {
		return parentAsset;
	}

	public void setParentAsset(String parentAsset) {
		this.parentAsset = parentAsset;
	}

}
