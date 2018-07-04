package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the asset_notes database table.
 * 
 */
@Entity
@Table(name="asset_notes")
@NamedQuery(name="AssetNote.findAll", query="SELECT a FROM AssetNote a")
public class AssetNote implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String notes;
	private BigInteger userId;
	private AssetUse assetUse;
	private AssetAttachment assetAttachment;

	public AssetNote() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	@Column(name="user_id")
	public BigInteger getUserId() {
		return this.userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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


	//bi-directional many-to-one association to AssetAttachment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="attachment_id")
	public AssetAttachment getAssetAttachment() {
		return this.assetAttachment;
	}

	public void setAssetAttachment(AssetAttachment assetAttachment) {
		this.assetAttachment = assetAttachment;
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
		if (!(obj instanceof AssetNote)) {
			return false;
		}
		AssetNote other = (AssetNote) obj;
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