package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_use database table.
 * 
 */
@Entity
@Table(name="asset_use")
@NamedQuery(name="AssetUse.findAll", query="SELECT a FROM AssetUse a")
public class AssetUse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="asset_id")
	private java.math.BigInteger assetId;

	private String description;

	@Column(name="is_deleted")
	private byte isDeleted;

	private String name;

	@Column(name="parent_id")
	private java.math.BigInteger parentId;

	public AssetUse() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public java.math.BigInteger getAssetId() {
		return this.assetId;
	}

	public void setAssetId(java.math.BigInteger assetId) {
		this.assetId = assetId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.math.BigInteger getParentId() {
		return this.parentId;
	}

	public void setParentId(java.math.BigInteger parentId) {
		this.parentId = parentId;
	}

}