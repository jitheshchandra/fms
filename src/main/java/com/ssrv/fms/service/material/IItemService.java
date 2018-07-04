package com.ssrv.fms.service.material;

import java.util.List;

import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.vo.material.item.ItemInfo;
import com.ssrv.fms.vo.material.item.ItemInfoForm;
import com.ssrv.fms.vo.material.item.ItemTypeForm;

public interface IItemService
	{
		Long saveItem(ItemInfo itemInfo);
		
		List<Items> getAllItem();

		void saveItemType(Itemtype itemType);

		ItemInfo getItem(Long id);

		Itemtype getItemType(Long id);

		public List<Itemtype> getAllItemType();
		
		public void updateItemType(Long id,ItemTypeForm itemTypeForm);
		
		public void deleteItemType(Long id);
		
		public void saveItems(ItemInfoForm form);

		void updateitemType(ItemTypeForm form);//****

	}
