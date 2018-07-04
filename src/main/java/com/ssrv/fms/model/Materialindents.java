package com.ssrv.fms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.incident.Indentdetails;
import com.ssrv.fms.model.item.Itemconsumption;
import com.ssrv.fms.model.item.Itemdeliverydetails;
import com.ssrv.fms.model.item.Itemstocklevels;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "materialindent")
@XmlRootElement
public class Materialindents implements Serializable
	{
		private static final long serialVersionUID = 1L;
		@Id
		@Basic(optional = false)
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Id")
		private Long id;

		// @ManyToOne
		// @JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
		// private Organization organization;

		@ManyToOne
		@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
		private Branch branch;

		@ManyToOne
		@JoinColumn(name = "OrganizationId", referencedColumnName = "ID")
		private Organization organization;
		
		/*@ManyToOne
		@JoinColumn(name = "ManagedEntityId", referencedColumnName = "ID")
		private ManagedEntityGroup managedEntity;*/

		@Column(name = "Name")
		private String name;
		
		@Column(name = "ModifiedOn")
		@Temporal(TemporalType.DATE)
		private Date modifiedOn;
		
		@Column(name = "ModifiedBy")
		private BigInteger modifiedBy;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "materialindents")
		private List<Indentdetails> indentdetails;
		
		@OneToMany(mappedBy = "materialIndent")
		private Collection<Itemdeliverydetails> itemdeliverydetailsCollection;
		
		/*@OneToMany(mappedBy = "materialIndent")
		private Collection<Itemstocklevels> itemstocklevelsCollection;*/
		
//		@OneToOne(cascade = CascadeType.ALL, mappedBy = "materialindents")
//		//@OneToMany(mappedBy = "materialindents")
//		private Itemconsumption itemconsumption;
		
		@OneToMany(mappedBy="materialindents")
		private Collection<Itemconsumption> itemconsumption;
		
		
		/*public ManagedEntityGroup getManagedEntity()
			{
				return managedEntity;
			}

		public void setManagedEntity(ManagedEntityGroup managedEntity)
			{
				this.managedEntity = managedEntity;
			}*/

		public Organization getOrganization()
			{
				return organization;
			}

		public void setOrganization(Organization organization)
			{
				this.organization = organization;
			}

		public Materialindents()
			{
			}

		public Materialindents(Long id)
			{
				this.id = id;
			}

		public Long getId()
			{
				return id;
			}

		public void setId(Long id)
			{
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

		public Date getModifiedOn()
			{
				return modifiedOn;
			}

		public void setModifiedOn(Date modifiedOn)
			{
				this.modifiedOn = modifiedOn;
			}

		public BigInteger getModifiedBy()
			{
				return modifiedBy;
			}

		public void setModifiedBy(BigInteger modifiedBy)
			{
				this.modifiedBy = modifiedBy;
			}

		public List<Indentdetails> getIndentdetails()
			{
				return indentdetails;
			}

		public void setIndentdetails(List<Indentdetails> indentdetails)
			{
				this.indentdetails = indentdetails;
			}

		@XmlTransient
		public Collection<Itemdeliverydetails> getItemdeliverydetailsCollection()
			{
				return itemdeliverydetailsCollection;
			}

		public void setItemdeliverydetailsCollection(Collection<Itemdeliverydetails> itemdeliverydetailsCollection)
			{
				this.itemdeliverydetailsCollection = itemdeliverydetailsCollection;
			}

		/*@XmlTransient
		public Collection<Itemstocklevels> getItemstocklevelsCollection()
			{
				return itemstocklevelsCollection;
			}

		public void setItemstocklevelsCollection(Collection<Itemstocklevels> itemstocklevelsCollection)
			{
				this.itemstocklevelsCollection = itemstocklevelsCollection;
			}*/

//		public Itemconsumption getItemconsumption()
//			{
//				return itemconsumption;
//			}
//
//		public void setItemconsumption(Itemconsumption itemconsumption)
//			{
//				this.itemconsumption = itemconsumption;
//			}
		
		
		public Collection<Itemconsumption> getItemconsumption()
			{
				return itemconsumption;
			}

		public void setItemconsumption(Collection<Itemconsumption> itemconsumption)
			{
				this.itemconsumption = itemconsumption;
			}
		
		public void setBranch(Branch branch)
			{
				this.branch = branch;
			}

		public Branch getBranch()
			{
				return branch;
			}

		@Override
		public int hashCode()
			{
				int hash = 0;
				hash += (id != null ? id.hashCode() : 0);
				return hash;
			}

		@Override
		public boolean equals(Object object)
			{
				// TODO: Warning - this method won't work in the case the id
				// fields are
				// not set
				if (!(object instanceof Materialindents))
					{
						return false;
					}
				Materialindents other = (Materialindents) object;
				if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
					{
						return false;
					}
				return true;
			}

		@Override
		public String toString()
			{
				return "jpa.Materialindents[ id=" + id + " ]";
			}

		public Collection<Itemstocklevels> getItemstocklevelsCollection() {
			// TODO Auto-generated method stub
			return null;
		}

	}
