package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the asset_maintainance database table.
 * 
 */
@Entity
@Table(name="asset_maintainance")
@NamedQuery(name="AssetMaintainance.findAll", query="SELECT a FROM AssetMaintainance a")
public class AssetMaintainance implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String area;
	private String category;
	private String condition;
	private String criticality;
	private String group;
	private Date lastCheckDate;
	private AssetUse assetUse;

	public AssetMaintainance() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getCriticality() {
		return this.criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}


	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="last_check_date")
	public Date getLastCheckDate() {
		return this.lastCheckDate;
	}

	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
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
		if (!(obj instanceof AssetMaintainance)) {
			return false;
		}
		AssetMaintainance other = (AssetMaintainance) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}