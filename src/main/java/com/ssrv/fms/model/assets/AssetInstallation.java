package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the asset_installation database table.
 * 
 */
@Entity
@Table(name="asset_installation")
@NamedQuery(name="AssetInstallation.findAll", query="SELECT a FROM AssetInstallation a")
public class AssetInstallation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date acceptanceDate;
	private Date installationDate;
	private String installer;
	private AssetUse assetUse;

	public AssetInstallation() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="acceptance_date")
	public Date getAcceptanceDate() {
		return this.acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="installation_date")
	public Date getInstallationDate() {
		return this.installationDate;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}


	public String getInstaller() {
		return this.installer;
	}

	public void setInstaller(String installer) {
		this.installer = installer;
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
		if (!(obj instanceof AssetInstallation)) {
			return false;
		}
		AssetInstallation other = (AssetInstallation) obj;
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