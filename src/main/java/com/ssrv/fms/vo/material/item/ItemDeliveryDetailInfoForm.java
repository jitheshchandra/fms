package com.ssrv.fms.vo.material.item;

import java.util.Date;

import com.ssrv.fms.vo.material.MaterialIndentInfo;

public class ItemDeliveryDetailInfoForm implements ItemDeliveryDetailInfo {

	private Long id;
	private Float quantity;
	private Date deliveryDate;
	private ItemDeliveryInfo delivery;
	private ItemInfo item;
	private MaterialIndentInfo materialIndent;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Override
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public ItemDeliveryInfo getDelivery() {
		return delivery;
	}

	public void setDelivery(ItemDeliveryInfo delivery) {
		this.delivery = delivery;
	}

	@Override
	public ItemInfo getItem() {
		return item;
	}

	public void setItem(ItemInfo item) {
		this.item = item;
	}

	@Override
	public MaterialIndentInfo getMaterialIndent() {
		return materialIndent;
	}

	public void setMaterialIndent(MaterialIndentInfo materialIndent) {
		this.materialIndent = materialIndent;
	}

}