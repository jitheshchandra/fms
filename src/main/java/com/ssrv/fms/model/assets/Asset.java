package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="asset_category")
	private BigInteger assetCategory;

	@Column(name="asset_condition")
	private BigInteger assetCondition;

	private String code;

	private String description;

	@Column(name="is_available")
	private byte isAvailable;

	@Column(name="is_deleted")
	private byte isDeleted;

	private String manufacturer;

	private String model;

	private String name;

	private String supplier;

	private String type;

	private BigInteger user;

	public Asset() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getAssetCategory() {
		return this.assetCategory;
	}

	public void setAssetCategory(BigInteger assetCategory) {
		this.assetCategory = assetCategory;
	}

	public BigInteger getAssetCondition() {
		return this.assetCondition;
	}

	public void setAssetCondition(BigInteger assetCondition) {
		this.assetCondition = assetCondition;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigInteger getUser() {
		return this.user;
	}

	public void setUser(BigInteger user) {
		this.user = user;
	}

}