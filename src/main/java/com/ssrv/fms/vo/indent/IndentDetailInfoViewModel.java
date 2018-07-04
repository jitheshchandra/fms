package com.ssrv.fms.vo.indent;

import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.item.Items;

public class IndentDetailInfoViewModel
	{
		Materialindents materialIndents;
		Items items;
		float quantity;
		float orderdLevel;
		
		public float getQuantity()
			{
				return quantity;
			}
		public void setQuantity(float quantity)
			{
				this.quantity = quantity;
			}
		public float getOrderdLevel()
			{
				return orderdLevel;
			}
		public void setOrderdLevel(float orderdLevel)
			{
				this.orderdLevel = orderdLevel;
			}
		public Materialindents getMaterialIndents()
			{
				return materialIndents;
			}
		public void setMaterialIndents(Materialindents materialIndents)
			{
				this.materialIndents = materialIndents;
			}
		public Items getItems()
			{
				return items;
			}
		public void setItems(Items items)
			{
				this.items = items;
			}
		
	}
