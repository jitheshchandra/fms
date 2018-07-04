package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset_category database table.
 * 
 */
@Entity
@Table(name="asset_category")
@NamedQuery(name="AssetCategory.findAll", query="SELECT a FROM AssetCategory a")
public class AssetCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="category_name")
	private String categoryName;

	private String description;

	public AssetCategory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}