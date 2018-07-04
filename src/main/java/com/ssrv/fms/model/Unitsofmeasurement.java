package com.ssrv.fms.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.item.Items;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "unitsofmeasurement")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Unitsofmeasurement.findAll", query = "SELECT u FROM Unitsofmeasurement u"),
		@NamedQuery(name = "Unitsofmeasurement.findById", query = "SELECT u FROM Unitsofmeasurement u WHERE u.id = :id"),
		@NamedQuery(name = "Unitsofmeasurement.findByName", query = "SELECT u FROM Unitsofmeasurement u WHERE u.name = :name") })
public class Unitsofmeasurement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Name")
	private String name;
	@OneToMany(mappedBy = "unitOfMeasurement")
	private Collection<Items> itemsCollection;

	public Unitsofmeasurement() {
	}

	public Unitsofmeasurement(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Collection<Items> getItemsCollection() {
		return itemsCollection;
	}

	public void setItemsCollection(Collection<Items> itemsCollection) {
		this.itemsCollection = itemsCollection;
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
		if (!(object instanceof Unitsofmeasurement)) {
			return false;
		}
		Unitsofmeasurement other = (Unitsofmeasurement) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Unitsofmeasurement[ id=" + id + " ]";
	}

}
