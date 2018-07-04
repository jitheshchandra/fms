package com.ssrv.fms.vo.material;

import java.sql.Date;
import java.util.List;

import com.ssrv.fms.vo.material.item.ItemInfo;

public class MaterialConsumptionInfoForm implements MaterialConsumptionInfo {
	private ItemInfo item;
	private MaterialIndentInfo materialIndent;
	private Date consumptionDate;
	private Long id;
	private Float quantity;
	
	MaterialConsumptionInfoForm()
	{
		
	}

	public MaterialConsumptionInfoForm(ItemInfo item,
			MaterialIndentInfo materialIndent) {
	}

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
	public Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	@Override
	public ItemInfo getItem() {
		return item;
	}

	@Override
	public MaterialIndentInfo getMaterialIndent() {
		return materialIndent;
	}	
	
	//Changes:Raghu
	private List<MaterialConsumptionList> materialConsumptionList;
	private long materialIndentId;

	public List<MaterialConsumptionList> getMaterialConsumptionList()
		{
			return materialConsumptionList;
		}

	public void setMaterialConsumptionList(List<MaterialConsumptionList> materialConsumptionList)
		{
			this.materialConsumptionList = materialConsumptionList;
		}

	public long getMaterialIndentId()
		{
			return materialIndentId;
		}

	public void setMaterialIndentId(long materialIndentId)
		{
			this.materialIndentId = materialIndentId;
		}
	
	
}
