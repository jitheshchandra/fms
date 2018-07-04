package com.ssrv.fms.vo.material.item;

import java.util.Date;

public class ItemTypeForm
	{
		private Long id;
		private String itemName;
		private Integer isDeleted;
		private Date modifiedOn;
		private Long modifiedBy;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getItemName()
			{
				return itemName;
			}
		public void setItemName(String itemName)
			{
				this.itemName = itemName;
			}
		public Integer getIsDeleted()
			{
				return isDeleted;
			}
		public void setIsDeleted(Integer isDeleted)
			{
				this.isDeleted = isDeleted;
			}
		public Date getModifiedOn()
			{
				return modifiedOn;
			}
		public void setModifiedOn(Date modifiedOn)
			{
				this.modifiedOn = modifiedOn;
			}
		public Long getModifiedBy()
			{
				return modifiedBy;
			}
		public void setModifiedBy(Long modifiedBy)
			{
				this.modifiedBy = modifiedBy;
			}
	}
