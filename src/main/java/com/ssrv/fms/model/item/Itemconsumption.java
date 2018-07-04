package com.ssrv.fms.model.item;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "itemconsumption")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Itemconsumption.findAll", query = "SELECT i FROM Itemconsumption i"),
		// @NamedQuery(name = "Itemconsumption.findByIndentId", query =
		// "SELECT i FROM Itemconsumption i WHERE i.indentId = :indentId"),
		@NamedQuery(name = "Itemconsumption.findByQuantity", query = "SELECT i FROM Itemconsumption i WHERE i.quantity = :quantity"),
		@NamedQuery(name = "Itemconsumption.findByConsumptionDate", query = "SELECT i FROM Itemconsumption i WHERE i.consumptionDate = :consumptionDate") })
public class Itemconsumption implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	
	@Column(name = "Quantity")
	private Float quantity;
	
	@Column(name = "ConsumptionDate")
	@Temporal(TemporalType.DATE)
	private Date consumptionDate;
	
	@JoinColumn(name = "ItemId", referencedColumnName = "ItemId")
	@ManyToOne
	private Items item;
	
//	@JoinColumn(name = "IndentId", referencedColumnName = "Id", updatable = false,insertable=false)
//	@OneToOne(optional = false)
//	private Materialindents materialindents;
	
	@JoinColumn(name = "IndentId", referencedColumnName = "Id")
	@ManyToOne
	private Materialindents materialindents;

	public Itemconsumption() {
	}

	public Itemconsumption(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Materialindents getMaterialindents() {
		return materialindents;
	}

	public void setMaterialindents(Materialindents materialindents) {
		this.materialindents = materialindents;
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
		Itemconsumption other = (Itemconsumption) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Itemconsumption[ id=" + id + " ]";
	}


}
