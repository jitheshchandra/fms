package com.ssrv.fms.vo.indent;


public class IndentDetailInfoForm implements IndentDetailInfo
	{
		private Long materialIndentId;
		private Long itemId;
		private Float reOrderlevel;
		private Float orderQuantity;
		public IndentDetailInfoForm()
			{
			}
		public void setReOrderlevel(Float reOrderlevel)
			{
				this.reOrderlevel = reOrderlevel;
			}
		@Override
		public Long getMaterialIndentId()
			{
				return materialIndentId;
			}
		public void setMaterialIndentId(Long materialIndentId)
			{
				this.materialIndentId = materialIndentId;
			}
		@Override
		public Long getItemId()
			{
				return itemId;
			}
		public void setItemId(Long itemId)
			{
				this.itemId = itemId;
			}
		@Override
		public Float getOrderQuantity()
			{
				return orderQuantity;
			}
		@Override
		public Float getReOrderlevel()
			{
				return reOrderlevel;
			}
		public void setOrderQuantity(Float orderQuantity)
			{
				this.orderQuantity = orderQuantity;
			}
	}
