package com.ssrv.fms.model.incident;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "INCIDENTCATEGORY")
@XmlRootElement
public class Incidentcategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "MODIFIEDON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	@Column(name = "MODIFIEDBY")
	private BigInteger modifiedBy;
	@OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
	private Collection<Incidentcategory> incidentChildren;
	@JoinColumn(name = "PARENTCATEGORYID", referencedColumnName = "ID")
	@ManyToOne
	private Incidentcategory parentCategory;

	public Incidentcategory() {
	}

	public Incidentcategory(Long id) {
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

	public Collection<Incidentcategory> getIncidentChildren() {
		return incidentChildren;
	}

	public void setIncidentChildren(
			Collection<Incidentcategory> incidentChildren) {
		this.incidentChildren = incidentChildren;
	}

	public Incidentcategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Incidentcategory parentCategoryId) {
		this.parentCategory = parentCategoryId;
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
		if (!(object instanceof Incidentcategory)) {
			return false;
		}
		Incidentcategory other = (Incidentcategory) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Incidentcategory[ id=" + id + " ]";
	}

}
