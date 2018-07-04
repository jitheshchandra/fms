package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_condition database table.
 * 
 */
@Entity
@Table(name="asset_condition")
@NamedQuery(name="AssetCondition.findAll", query="SELECT a FROM AssetCondition a")
public class AssetCondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String description;

	private String name;

	public AssetCondition() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

}