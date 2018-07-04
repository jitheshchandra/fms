package com.ssrv.fms.vo.material;

public class MaterialConsumptionList
	{
		private Long itemId;
		private float consumedQuantity;
		public Long getItemId()
			{
				return itemId;
			}

		public void setItemId(Long itemId)
			{
				this.itemId = itemId;
			}

		public float getConsumedQuantity()
			{
				return consumedQuantity;
			}

		public void setConsumedQuantity(float consumedQuantity)
			{
				this.consumedQuantity = consumedQuantity;
			}

		
	}
