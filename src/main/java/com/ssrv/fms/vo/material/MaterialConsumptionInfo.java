package com.ssrv.fms.vo.material;

import java.util.Date;
import java.util.List;

import com.ssrv.fms.vo.material.item.ItemInfo;

public interface MaterialConsumptionInfo {
	Long getId();

	Float getQuantity();

	Date getConsumptionDate();

	ItemInfo getItem();

	MaterialIndentInfo getMaterialIndent();
	
	public List<MaterialConsumptionList> getMaterialConsumptionList();
	
	public long getMaterialIndentId();
}
