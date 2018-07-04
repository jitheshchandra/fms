package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_position database table.
 * 
 */
@Entity
@Table(name="asset_position")
@NamedQuery(name="AssetPosition.findAll", query="SELECT a FROM AssetPosition a")
public class AssetPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String building;
	private String complex;
	private String floor;
	private String room;
	private String unit;
	private String wareHouse;
	private AssetUse assetUse;

	public AssetPosition() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}


	public String getComplex() {
		return this.complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}


	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}


	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}


	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	@Column(name="ware_house")
	public String getWareHouse() {
		return this.wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
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
		if (!(obj instanceof AssetPosition)) {
			return false;
		}
		AssetPosition other = (AssetPosition) obj;
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