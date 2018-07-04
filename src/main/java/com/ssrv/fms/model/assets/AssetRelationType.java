package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asset_relation_type database table.
 * 
 */
@Entity
@Table(name="asset_relation_type")
@NamedQuery(name="AssetRelationType.findAll", query="SELECT a FROM AssetRelationType a")
public class AssetRelationType implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private String name;
	/*private List<AssetRelation> assetRelations;*/

	public AssetRelationType() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to AssetRelation
	/*@OneToMany(mappedBy="assetRelationType")
	public List<AssetRelation> getAssetRelations() {
		return this.assetRelations;
	}

	public void setAssetRelations(List<AssetRelation> assetRelations) {
		this.assetRelations = assetRelations;
	}

	public AssetRelation addAssetRelation(AssetRelation assetRelation) {
		getAssetRelations().add(assetRelation);
		assetRelation.setAssetRelationType(this);

		return assetRelation;
	}

	public AssetRelation removeAssetRelation(AssetRelation assetRelation) {
		getAssetRelations().remove(assetRelation);
		assetRelation.setAssetRelationType(null);

		return assetRelation;
	}
*/

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
		if (!(obj instanceof AssetRelationType)) {
			return false;
		}
		AssetRelationType other = (AssetRelationType) obj;
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