package com.ssrv.fms.vo.material.item;

import com.ssrv.fms.model.item.Items;

public class ItemInfoView implements ItemInfo {
	private final Items item;

	public ItemInfoView(Items item) {
		this.item = item;
	}

	@Override
	public String getName() {
		return item.getName();
	}

	@Override
	public String getItemCode() {
		// TODO: what item code corresponds to ? ?
		return "";
	}

	@Override
	public Long getId() {
		return item.getId();
	}

	@Override
	public Long getItemTypeId() {
		return item.getItemType().getId();
	}

	@Override
	public Long getUomId() {
		return item.getUnitOfMeasurement().getId();
	}
}
