package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the asset_relation database table.
 * 
 */
@Entity
@Table(name="asset_relation")
@NamedQuery(name="AssetRelation.findAll", query="SELECT a FROM AssetRelation a")
public class AssetRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "asset_id", referencedColumnName = "id")
    private AssetUse assetUse;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "relation_type", referencedColumnName = "id")
    private AssetRelationType assetRelationType;
	
	@Column(name = "entity_type")
	private String entityType;
	
	@Column(name = "entity")
	private String entity;
	
	public AssetRelation() {
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssetUse getAssetUse() {
		return assetUse;
	}

	public void setAssetUse(AssetUse assetUse) {
		this.assetUse = assetUse;
	}

	public AssetRelationType getAssetRelationType() {
		return assetRelationType;
	}

	public void setAssetRelationType(AssetRelationType assetRelationType) {
		this.assetRelationType = assetRelationType;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}


	


	/*//bi-directional many-to-one association to AssetRelationType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="relation_type")
	public AssetRelationType getAssetRelationType() {
		return this.assetRelationType;
	}

	public void setAssetRelationType(AssetRelationType assetRelationType) {
		this.assetRelationType = assetRelationType;
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


	 (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	 (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AssetRelation)) {
			return false;
		}
		AssetRelation other = (AssetRelation) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
*/
}