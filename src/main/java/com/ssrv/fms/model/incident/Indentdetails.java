package com.ssrv.fms.model.incident;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.item.Items;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "indentdetail")
@XmlRootElement
public class Indentdetails implements Serializable
	{
		private static final long serialVersionUID = 1L;
		@EmbeddedId
		protected IndentdetailsPK id;

		// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
		// consider using these annotations to enforce field validation

		@JoinColumn(name = "INDENTID", referencedColumnName = "ID", insertable = false, updatable = false)
		@ManyToOne(optional = false)
		private Materialindents materialindents;

		@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", insertable = false, updatable = false)
		@ManyToOne(optional = false)
		private Items items;

		@Column(name = "REORDERLEVEL")
		private Float reorderLevel;

		@Column(name = "Quantity")
		private Float quantity;

		public Float getQuantity()
			{
				return quantity;
			}

		public void setQuantity(Float quantity)
			{
				this.quantity = quantity;
			}

		public Indentdetails()
			{
			}

		public Indentdetails(IndentdetailsPK indentdetailsPK)
			{
				this.id = indentdetailsPK;
			}

		public Indentdetails(Long indentId, Long itemId)
			{
				this.id = new IndentdetailsPK(indentId, itemId);
			}

		public IndentdetailsPK getId()
			{
				return id;
			}

		public void setId(IndentdetailsPK id)
			{
				this.id = id;
			}

		public Float getReorderLevel()
			{
				return reorderLevel;
			}

		public void setReorderLevel(Float reorderLevel)
			{
				this.reorderLevel = reorderLevel;
			}

		public Items getItems()
			{
				return items;
			}

		public void setItems(Items items)
			{
				id.setItemId(materialindents.getId());
				this.items = items;
			}

		public Materialindents getMaterialindents()
			{
				return materialindents;
			}

		public void setMaterialindents(Materialindents materialindents)
			{
				id.setIndentId(materialindents.getId());
				this.materialindents = materialindents;
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
				if (!(object instanceof Indentdetails))
					{
						return false;
					}
				Indentdetails other = (Indentdetails) object;
				if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
					{
						return false;
					}
				return true;
			}

		@Override
		public String toString()
			{
				return "jpa.Indentdetails[ indentdetailsPK=" + id + " ]";
			}

	}
