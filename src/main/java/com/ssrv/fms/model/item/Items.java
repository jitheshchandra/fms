package com.ssrv.fms.model.item;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.Unitsofmeasurement;
import com.ssrv.fms.model.incident.Indentdetails;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
		@NamedQuery(name = "Items.findByItemId", query = "SELECT i FROM Items i WHERE i.id = :id"),
		@NamedQuery(name = "Items.findByItemName", query = "SELECT i FROM Items i WHERE i.name = :name"),
		@NamedQuery(name = "Items.findByItemType", query = "SELECT i FROM Items i WHERE i.type = :type"),
		@NamedQuery(name = "Items.findByIsDeleted", query = "SELECT i FROM Items i WHERE i.isDeleted = :isDeleted"),
		@NamedQuery(name = "Items.findByModifiedOn", query = "SELECT i FROM Items i WHERE i.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Items.findByModifiedBy", query = "SELECT i FROM Items i WHERE i.modifiedBy = :modifiedBy") })
public class Items implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemId")
	private Long id;
	
	@Column(name = "ItemName")
	private String name;

	@ManyToOne
	@JoinColumn(name = "ItemType", referencedColumnName = "id")
	private Itemtype type;

	@Column(name = "IsDeleted")
	private Short isDeleted;
	
	@Column(name = "ModifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	@Column(name = "ModifiedBy")
	private BigInteger modifiedBy;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "items")
	private Collection<Indentdetails> indentdetailsCollection;
	
	@OneToMany(mappedBy = "item")
	private Collection<Itemdeliverydetails> itemdeliverydetailsCollection;
	
/*	@OneToMany(mappedBy = "item")
	private Collection<Itemstocklevels> itemstocklevelsCollection;*/
	
	@OneToMany(mappedBy = "item")
	private Collection<Itemconsumption> itemconsumptionCollection; 
	
	@JoinColumn(name = "UnitOfMeasurement", referencedColumnName = "Id")
	@ManyToOne
	private Unitsofmeasurement unitOfMeasurement;

	public Items() {
	}

	public Items(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName()
		{
			return name;
		}

	public void setName(String name)
		{
			this.name = name;
		}

	public Itemtype getType()
		{
			return type;
		}

	public void setType(Itemtype type)
		{
			this.type = type;
		}

	public void setItemType(Itemtype itemType) {
		this.type = itemType;
	}

	public Itemtype getItemType() {
		return type;
	}

	public Short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@XmlTransient
	public Collection<Indentdetails> getIndentdetailsCollection() {
		return indentdetailsCollection;
	}

	public void setIndentdetailsCollection(
			Collection<Indentdetails> indentdetailsCollection) {
		this.indentdetailsCollection = indentdetailsCollection;
	}

	@XmlTransient
	public Collection<Itemdeliverydetails> getItemdeliverydetailsCollection() {
		return itemdeliverydetailsCollection;
	}

	public void setItemdeliverydetailsCollection(
			Collection<Itemdeliverydetails> itemdeliverydetailsCollection) {
		this.itemdeliverydetailsCollection = itemdeliverydetailsCollection;
	}

/*	@XmlTransient
	public Collection<Itemstocklevels> getItemstocklevelsCollection() {
		return itemstocklevelsCollection;
	}

	public void setItemstocklevelsCollection(
			Collection<Itemstocklevels> itemstocklevelsCollection) {
		this.itemstocklevelsCollection = itemstocklevelsCollection;
	}*/

	@XmlTransient
	public Collection<Itemconsumption> getItemconsumptionCollection() {
		return itemconsumptionCollection;
	}

	public void setItemconsumptionCollection(
			Collection<Itemconsumption> itemconsumptionCollection) {
		this.itemconsumptionCollection = itemconsumptionCollection;
	}

	public Unitsofmeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(Unitsofmeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
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
		if (!(object instanceof Items)) {
			return false;
		}
		Items other = (Items) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Items[ itemId=" + id + " ]";
	}

}
