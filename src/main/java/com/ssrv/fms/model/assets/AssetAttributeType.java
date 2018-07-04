package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_attribute_type database table.
 * 
 */
@Entity
@Table(name="asset_attribute_type")
@NamedQuery(name="AssetAttributeType.findAll", query="SELECT a FROM AssetAttributeType a")
public class AssetAttributeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	public AssetAttributeType() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}