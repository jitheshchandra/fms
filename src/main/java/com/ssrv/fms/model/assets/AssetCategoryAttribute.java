package com.ssrv.fms.model.assets;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the asset_category_attribute database table.
 * 
 */
@Entity
@Table(name="asset_category_attribute")
@NamedQuery(name="AssetCategoryAttribute.findAll", query="SELECT a FROM AssetCategoryAttribute a")
public class AssetCategoryAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private BigInteger id;

	@Column(name="attribute_type")
	private BigInteger attributeType;

	@Column(name="category_id")
	private BigInteger categoryId;

	private String name;

	@Column(name="org_id")
	private BigInteger orgId;

	public AssetCategoryAttribute() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getAttributeType() {
		return this.attributeType;
	}

	public void setAttributeType(BigInteger attributeType) {
		this.attributeType = attributeType;
	}

	public BigInteger getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(BigInteger categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getOrgId() {
		return this.orgId;
	}

	public void setOrgId(BigInteger orgId) {
		this.orgId = orgId;
	}

}