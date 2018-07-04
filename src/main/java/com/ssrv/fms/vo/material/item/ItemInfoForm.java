package com.ssrv.fms.vo.material.item;

public class ItemInfoForm implements ItemInfo {

	private Long id;
	private String itemCode;
	private Long typeId;
	private Long uomId;
	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Override
	public String getItemCode() {
		return itemCode;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long getItemTypeId() {
		return typeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.typeId = itemTypeId;
	}

	@Override
	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public Long getTypeId()
		{
			return typeId;
		}

	public void setTypeId(Long typeId)
		{
			this.typeId = typeId;
		}

	public void setId(Long id)
		{
			this.id = id;
		}

	
}
