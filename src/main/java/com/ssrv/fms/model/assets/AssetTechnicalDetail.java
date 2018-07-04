package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_technical_details database table.
 * 
 */
@Entity
@Table(name="asset_technical_details")
@NamedQuery(name="AssetTechnicalDetail.findAll", query="SELECT a FROM AssetTechnicalDetail a")
public class AssetTechnicalDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String color;
	private String depth;
	private String height;
	private String weight;
	private String width;
	private AssetUse assetUse;

	public AssetTechnicalDetail() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getDepth() {
		return this.depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}


	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
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
		if (!(obj instanceof AssetTechnicalDetail)) {
			return false;
		}
		AssetTechnicalDetail other = (AssetTechnicalDetail) obj;
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