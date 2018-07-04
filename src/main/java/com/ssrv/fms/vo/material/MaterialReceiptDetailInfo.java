package com.ssrv.fms.vo.material;

import java.util.Date;

import com.ssrv.fms.vo.material.item.ItemDeliveryInfo;
import com.ssrv.fms.vo.material.item.ItemInfo;

public interface MaterialReceiptDetailInfo {

	Long getId();

	Float getQuantity();

	Date getDeliveryDate();

	ItemDeliveryInfo getItemDelivery();

	ItemInfo getItem();

	MaterialIndentInfo getMaterialIndent();
}
