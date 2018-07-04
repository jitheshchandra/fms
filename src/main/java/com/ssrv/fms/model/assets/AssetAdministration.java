package com.ssrv.fms.model.assets;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the asset_administration database table.
 * 
 */
@Entity
@Table(name="asset_administration")
@NamedQuery(name="AssetAdministration.findAll", query="SELECT a FROM AssetAdministration a")
public class AssetAdministration implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date amortizationEnd;
	private Date amortizationStart;
	private String annulAmortization;
	private String currentValue;
	private Date disposalDate;
	private String fullyDepricated;
	private String invoiceNumber;
	private String purchasePrice;
	private String purchaseRequest;
	private int yearOfLife;
	private AssetUse assetUse;

	public AssetAdministration() {
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
	@Column(name="amortization_end")
	public Date getAmortizationEnd() {
		return this.amortizationEnd;
	}

	public void setAmortizationEnd(Date amortizationEnd) {
		this.amortizationEnd = amortizationEnd;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="amortization_start")
	public Date getAmortizationStart() {
		return this.amortizationStart;
	}

	public void setAmortizationStart(Date amortizationStart) {
		this.amortizationStart = amortizationStart;
	}


	@Column(name="annul_amortization")
	public String getAnnulAmortization() {
		return this.annulAmortization;
	}

	public void setAnnulAmortization(String annulAmortization) {
		this.annulAmortization = annulAmortization;
	}


	@Column(name="current_value")
	public String getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="disposal_date")
	public Date getDisposalDate() {
		return this.disposalDate;
	}

	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}


	@Column(name="fully_depricated")
	public String getFullyDepricated() {
		return this.fullyDepricated;
	}

	public void setFullyDepricated(String fullyDepricated) {
		this.fullyDepricated = fullyDepricated;
	}


	@Column(name="invoice_number")
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	@Column(name="purchase_price")
	public String getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	@Column(name="purchase_request")
	public String getPurchaseRequest() {
		return this.purchaseRequest;
	}

	public void setPurchaseRequest(String purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}


	@Column(name="year_of_life")
	public int getYearOfLife() {
		return this.yearOfLife;
	}

	public void setYearOfLife(int yearOfLife) {
		this.yearOfLife = yearOfLife;
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
		if (!(obj instanceof AssetAdministration)) {
			return false;
		}
		AssetAdministration other = (AssetAdministration) obj;
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