package com.ssrv.fms.model.assets;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the asset_warranty database table.
 * 
 */
@Entity
@Table(name="asset_warranty")
@NamedQuery(name="AssetWarranty.findAll", query="SELECT a FROM AssetWarranty a")
public class AssetWarranty implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String warrantyCode;
	private Date warrantyEnd;
	private Date warrantyStart;
	private String warrantyType;
	private AssetUse assetUse;

	public AssetWarranty() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Column(name="warranty_code")
	public String getWarrantyCode() {
		return this.warrantyCode;
	}

	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="warranty_end")
	public Date getWarrantyEnd() {
		return this.warrantyEnd;
	}

	public void setWarrantyEnd(Date warrantyEnd) {
		this.warrantyEnd = warrantyEnd;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="warranty_start")
	public Date getWarrantyStart() {
		return this.warrantyStart;
	}

	public void setWarrantyStart(Date warrantyStart) {
		this.warrantyStart = warrantyStart;
	}


	@Column(name="warranty_type")
	public String getWarrantyType() {
		return this.warrantyType;
	}

	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}


	//bi-directional many-to-one association to AssetUse
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="asset_id")
	public AssetUse getAssetUse() {
		return this.assetUse;
	}

	public void setAssetUse(AssetUse assetUse) {
		this.assetUse = assetUse;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AssetWarranty)) {
			return false;
		}
		AssetWarranty other = (AssetWarranty) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}