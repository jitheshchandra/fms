package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the asset_attribute database table.
 * 
 */
@Entity
@Table(name="asset_attribute")
@NamedQuery(name="AssetAttribute.findAll", query="SELECT a FROM AssetAttribute a")
public class AssetAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="asset_id")
	private BigInteger assetId;

	@Column(name="boolean")
	private boolean boolean_;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private BigInteger integer;

	@Column(name="org_id")
	private BigInteger orgId;

	private String string;

	public AssetAttribute() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getAssetId() {
		return this.assetId;
	}

	public void setAssetId(BigInteger assetId) {
		this.assetId = assetId;
	}

	public Object getBoolean_() {
		return this.boolean_;
	}

	public void setBoolean_(boolean boolean_) {
		this.boolean_ = boolean_;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigInteger getInteger() {
		return this.integer;
	}

	public void setInteger(BigInteger integer) {
		this.integer = integer;
	}

	public BigInteger getOrgId() {
		return this.orgId;
	}

	public void setOrgId(BigInteger orgId) {
		this.orgId = orgId;
	}

	public String getString() {
		return this.string;
	}

	public void setString(String string) {
		this.string = string;
	}

}