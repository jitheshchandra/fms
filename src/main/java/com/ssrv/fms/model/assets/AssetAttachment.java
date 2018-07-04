package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the asset_attachment database table.
 * 
 */
@Entity
@Table(name="asset_attachment")
@NamedQuery(name="AssetAttachment.findAll", query="SELECT a FROM AssetAttachment a")
public class AssetAttachment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String attachment;
	private BigInteger userId;
	private AssetUse assetUse;
	private List<AssetNote> assetNotes;

	public AssetAttachment() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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


	//bi-directional many-to-one association to AssetNote
	@OneToMany(mappedBy="assetAttachment")
	public List<AssetNote> getAssetNotes() {
		return this.assetNotes;
	}

	public void setAssetNotes(List<AssetNote> assetNotes) {
		this.assetNotes = assetNotes;
	}

	public AssetNote addAssetNote(AssetNote assetNote) {
		getAssetNotes().add(assetNote);
		assetNote.setAssetAttachment(this);

		return assetNote;
	}

	public AssetNote removeAssetNote(AssetNote assetNote) {
		getAssetNotes().remove(assetNote);
		assetNote.setAssetAttachment(null);

		return assetNote;
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
		if (!(obj instanceof AssetAttachment)) {
			return false;
		}
		AssetAttachment other = (AssetAttachment) obj;
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