package com.ssrv.fms.vo.material.item;

import java.util.Date;
import java.util.List;

public class ItemDeliveryInfoForm implements ItemDeliveryInfo {

	private Long id;
	private String orderNo;
	private Date orderDate;

	private List<ItemDeliveryDetailInfoForm> itemDeliveryDetails;

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String getOrderNo() {
		return orderNo;
	}

	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<ItemDeliveryDetailInfoForm> getItemDeliveryDetails() {
		return itemDeliveryDetails;
	}

	public void setItemDeliveryDetails(
			List<ItemDeliveryDetailInfoForm> itemDeliveryDetails) {
		this.itemDeliveryDetails = itemDeliveryDetails;
	}
}
