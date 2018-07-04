package com.ssrv.fms.vo.material;

import java.sql.Date;

import com.ssrv.fms.vo.material.item.ItemDeliveryInfo;
import com.ssrv.fms.vo.material.item.ItemDeliveryInfoForm;
import com.ssrv.fms.vo.material.item.ItemInfo;
import com.ssrv.fms.vo.material.item.ItemInfoForm;

public class MaterialReceiptDetailInfoForm implements MaterialReceiptDetailInfo
	{

		private Long id;
		private Float quantity;
		private Date deliveryDate;
		private ItemDeliveryInfoForm itemDelivery;
		private ItemInfoForm item;
		private MaterialIndentInfoForm materialIndent;

		private ItemDeliveryInfo itemDelivery1;
		private ItemInfo item1;
		private MaterialIndentInfo materialIndent1;

		public MaterialReceiptDetailInfoForm()
			{

			}

		public MaterialReceiptDetailInfoForm(ItemInfo item1, MaterialIndentInfo materialIndent1)
			{
				this.item1 = item;
				this.materialIndent1 = materialIndent;
			}

		public ItemDeliveryInfo getItemDelivery1()
			{
				return itemDelivery1;
			}

		public void setItemDelivery1(ItemDeliveryInfo itemDelivery1)
			{
				this.itemDelivery1 = itemDelivery1;
			}

		public ItemInfo getItem1()
			{
				return item1;
			}

		public void setItem1(ItemInfo item1)
			{
				this.item1 = item1;
			}

		public MaterialIndentInfo getMaterialIndent1()
			{
				return materialIndent1;
			}

		public void setMaterialIndent1(MaterialIndentInfo materialIndent1)
			{
				this.materialIndent1 = materialIndent1;
			}

		public MaterialReceiptDetailInfoForm(ItemInfoForm item, MaterialIndentInfoForm materialIndent)
			{
				this.item = item;
				this.materialIndent = materialIndent;
			}

		@Override
		public Long getId()
			{
				return id;
			}

		public void setId(Long id)
			{
				this.id = id;
			}

		@Override
		public Float getQuantity()
			{
				return quantity;
			}

		public void setQuantity(Float quantity)
			{
				this.quantity = quantity;
			}

		@Override
		public Date getDeliveryDate()
			{
				return deliveryDate;
			}

		public void setDeliveryDate(Date deliveryDate)
			{
				this.deliveryDate = deliveryDate;
			}

		@Override
		public ItemDeliveryInfo getItemDelivery()
			{
				return itemDelivery;
			}

		public void setItemDelivery(ItemDeliveryInfoForm itemDelivery)
			{
				this.itemDelivery = itemDelivery;
			}

		@Override
		public ItemInfo getItem()
			{
				return item;
			}

		public void setItem(ItemInfoForm item)
			{
				this.item = item;
			}

		@Override
		public MaterialIndentInfo getMaterialIndent()
			{
				return materialIndent;
			}

		public void setMaterialIndent(MaterialIndentInfoForm materialIndent)
			{
				this.materialIndent = materialIndent;
			}

		// Changes: Raghu
		private Long itemId;

		public Long getItemId()
			{
				return itemId;
			}

		public void setItemId(Long itemId)
			{
				this.itemId = itemId;
			}
	}