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
@Entity
@Table(name = "itemdeliverydetail")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Itemdeliverydetails.findAll", query = "SELECT i FROM Itemdeliverydetails i"),
		@NamedQuery(name = "Itemdeliverydetails.findById", query = "SELECT i FROM Itemdeliverydetails i WHERE i.id = :id"),
		@NamedQuery(name = "Itemdeliverydetails.findByQuantity", query = "SELECT i FROM Itemdeliverydetails i WHERE i.quantity = :quantity"),
		@NamedQuery(name = "Itemdeliverydetails.findByDeliveryDate", query = "SELECT i FROM Itemdeliverydetails i WHERE i.deliveryDate = :deliveryDate") })
public class Itemdeliverydetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce fieldvalidation
	@Column(name = "QUANTITY")
	private Float quantity;
	@Column(name = "DELIVERYDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;
	@JoinColumn(name = "DELIVERYID", referencedColumnName = "ID")
	@ManyToOne
	private Itemdelivery delivery;
	@JoinColumn(name = "ITEMID", referencedColumnName = "ItemId")
	@ManyToOne
	private Items item;
	@JoinColumn(name = "INDENTID", referencedColumnName = "ID")
	@ManyToOne
	private Materialindents materialIndent;

	public Itemdeliverydetails() {
	}

	public Itemdeliverydetails(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Itemdelivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Itemdelivery delivery) {
		this.delivery = delivery;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Materialindents getMaterialIndent() {
		return materialIndent;
	}

	public void setMaterialIndent(Materialindents materialIndent) {
		this.materialIndent = materialIndent;
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
		if (!(object instanceof Itemdeliverydetails)) {
			return false;
		}
		Itemdeliverydetails other = (Itemdeliverydetails) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Itemdeliverydetails[ id=" + id + " ]";
	}

}
