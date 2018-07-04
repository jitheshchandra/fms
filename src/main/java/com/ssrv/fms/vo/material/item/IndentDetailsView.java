package com.ssrv.fms.vo.material.item;

public class IndentDetailsView
	{
		private Long itemId;
		private Float orderQuantity;
		private Float reOrderlevel;
		public Long getItemId()
			{
				return itemId;
			}
		public void setItemId(Long itemId)
			{
				this.itemId = itemId;
			}
		public Float getOrderQuantity()
			{
				return orderQuantity;
			}
		public void setOrderQuantity(Float orderQuantity)
			{
				this.orderQuantity = orderQuantity;
			}
		public Float getReOrderlevel()
			{
				return reOrderlevel;
			}
		public void setReOrderlevel(Float reOrderlevel)
			{
				this.reOrderlevel = reOrderlevel;
			}
	}
