package com.ssrv.fms.model.item;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.Materialindents;

/**
 * 
 * @author PAMS
 */
//@Entity
//@Table(name = "itemstocklevel")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Itemstocklevels.findAll", query = "SELECT i FROM Itemstocklevels i"),
		@NamedQuery(name = "Itemstocklevels.findById", query = "SELECT i FROM Itemstocklevels i WHERE i.id = :id"),
		@NamedQuery(name = "Itemstocklevels.findByQuantityInStock", query = "SELECT i FROM Itemstocklevels i WHERE i.quantityInStock = :quantityInStock"),
		@NamedQuery(name = "Itemstocklevels.findByStockTakingDate", query = "SELECT i FROM Itemstocklevels i WHERE i.stockTakingDate = :stockTakingDate") })
public class Itemstocklevels implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	
	@Column(name = "QUANTITYINSTOCK")
	private Float quantityInStock;
	
	@Column(name = "STOCKTAKINGDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stockTakingDate;
	
	@JoinColumn(name = "ItemId", referencedColumnName = "ITEMID")
	@ManyToOne
	private Items item;
	
	@JoinColumn(name = "IndentId", referencedColumnName = "ID")
	@ManyToOne
	private Materialindents materialIndent;

	public Itemstocklevels() {
	}

	public void setItem(Items item)
		{
			this.item = item;
		}

	public void setMaterialIndent(Materialindents materialIndent)
		{
			this.materialIndent = materialIndent;
		}

	public Itemstocklevels(Integer id) {
		this.id = id;
	}

	public Itemstocklevels(Items item, Materialindents materialIndent) {
		this.item = item;
		this.materialIndent = materialIndent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Float quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Date getStockTakingDate() {
		return stockTakingDate;
	}

	public void setStockTakingDate(Date stockTakingDate) {
		this.stockTakingDate = stockTakingDate;
	}

	public Items getItemId() {
		return item;
	}

	public void setItemId(Items itemId) {
		this.item = itemId;
	}

	public Materialindents getMaterialIndent() {
		return materialIndent;
	}

	public Items getItem() {
		return item;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Itemstocklevels)) {
			return false;
		}
		Itemstocklevels other = (Itemstocklevels) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Itemstocklevels[ id=" + id + " ]";
	}

}
