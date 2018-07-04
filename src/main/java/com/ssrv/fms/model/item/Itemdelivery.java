package com.ssrv.fms.model.item;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "itemdelivery")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Itemdelivery.findAll", query = "SELECT i FROM Itemdelivery i"),
		@NamedQuery(name = "Itemdelivery.findById", query = "SELECT i FROM Itemdelivery i WHERE i.id = :id"),
		@NamedQuery(name = "Itemdelivery.findByOrderNo", query = "SELECT i FROM Itemdelivery i WHERE i.orderNo = :orderNo"),
		@NamedQuery(name = "Itemdelivery.findByOrderDate", query = "SELECT i FROM Itemdelivery i WHERE i.orderDate = :orderDate"),
		@NamedQuery(name = "Itemdelivery.findByModifiedOn", query = "SELECT i FROM Itemdelivery i WHERE i.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Itemdelivery.findByModifiedBY", query = "SELECT i FROM Itemdelivery i WHERE i.modifiedBY = :modifiedBY") })
public class Itemdelivery implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
	@Column(name = "OrderNo")
	private String orderNo;
	@Column(name = "OrderDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	@Column(name = "ModifiedBY")
	private BigInteger modifiedBY;
	@OneToMany(mappedBy = "delivery")
	private Collection<Itemdeliverydetails> itemDeliveryDetails;

	public Itemdelivery() {
	}

	public Itemdelivery(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public BigInteger getModifiedBY() {
		return modifiedBY;
	}

	public void setModifiedBY(BigInteger modifiedBY) {
		this.modifiedBY = modifiedBY;
	}

	public Collection<Itemdeliverydetails> getItemDeliveryDetails() {
		return itemDeliveryDetails;
	}

	public void setItemDeliveryDetails(
			Collection<Itemdeliverydetails> itemDeliveryDetails) {
		this.itemDeliveryDetails = itemDeliveryDetails;
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
		if (!(object instanceof Itemdelivery)) {
			return false;
		}
		Itemdelivery other = (Itemdelivery) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Itemdelivery[ id=" + id + " ]";
	}

}
