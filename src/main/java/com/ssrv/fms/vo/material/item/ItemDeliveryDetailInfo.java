package com.ssrv.fms.vo.material.item;

import java.util.Date;

import com.ssrv.fms.vo.material.MaterialIndentInfo;

public interface ItemDeliveryDetailInfo {

	Long getId();

	Float getQuantity();

	Date getDeliveryDate();

	ItemDeliveryInfo getDelivery();

	ItemInfo getItem();

	MaterialIndentInfo getMaterialIndent();

}