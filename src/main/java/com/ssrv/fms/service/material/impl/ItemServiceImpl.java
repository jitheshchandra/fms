package com.ssrv.fms.service.material.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.Unitsofmeasurement;
import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.service.material.IItemService;
import com.ssrv.fms.vo.material.item.ItemInfo;
import com.ssrv.fms.vo.material.item.ItemInfoForm;
import com.ssrv.fms.vo.material.item.ItemInfoView;
import com.ssrv.fms.vo.material.item.ItemTypeForm;

@Service
@Lazy
@Component
public class ItemServiceImpl implements IItemService {

	private final Short NOT_DELETED = Short.valueOf("0");
	private final Short DELETED = Short.valueOf("1");

	@Autowired
	IBaseDAO baseDao;

	@Override
	@Transactional
	public Long saveItem(ItemInfo itemInfo) {
		Items item = toItems(itemInfo);
		baseDao.save(item);
		return item.getId();
	}

	private Items toItems(ItemInfo itemInfo) {
		Items item = new Items();

		item.setName(itemInfo.getName());

		Itemtype itemType = baseDao.findById(Itemtype.class,
				itemInfo.getItemTypeId());
		item.setItemType(itemType);

		Unitsofmeasurement uom = baseDao.findById(Unitsofmeasurement.class,
				itemInfo.getUomId());
		item.setUnitOfMeasurement(uom);

		// item.setModifiedBy(BigInteger)
		item.setModifiedOn(new Date());
		item.setIsDeleted(NOT_DELETED);

		return item;
	}

	@Override
	@Transactional(readOnly = true)
	public ItemInfo getItem(Long id) {
		Validate.notNull(id, "id cannot be null");
		Items item = baseDao.findById(Items.class, id);
		if (item != null) {
			return new ItemInfoView(item);
		}
		return null;
	}
	
	
	//Changes made by Raghu

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveItemType(Itemtype itemType) {
		baseDao.save(itemType);
	}

	@Override//***
	public Itemtype getItemType(Long id) {
		Itemtype itemtype=entityManager.find(Itemtype.class, id);
		return itemtype;
	}
	@Override
	@Transactional
	public void updateitemType(ItemTypeForm form) {
	//	Itemtype itemtype=entityManager.find(Itemtype.class, form.getId());
	//	itemtype.setName(form.getItemName());
	}
	@Override
	public List<Itemtype> getAllItemType()
		{
			@SuppressWarnings("unchecked")
			List<Itemtype> itemTypeList = entityManager.createQuery("SELECT i FROM Itemtype i WHERE i.isDeleted=0").getResultList();
			return itemTypeList;
		}
	@Override
	@Transactional
	public void updateItemType(Long id, ItemTypeForm itemTypeForm)
		{
			Itemtype itemType = entityManager.find(Itemtype.class,id);
			itemType.setName(itemTypeForm.getItemName());
		}
	@Override
	@Transactional
	public void deleteItemType(Long id)
		{
			Itemtype itemType = entityManager.find(Itemtype.class,id);
			itemType.setIsDeleted(1);
		}
	@Override
	@Transactional
	public void saveItems(ItemInfoForm form)
		{
			Items model=new Items();
			model.setName(form.getName());
			Unitsofmeasurement unitOfMesurment = entityManager.find(Unitsofmeasurement.class,form.getUomId());
			model.setUnitOfMeasurement(unitOfMesurment);
			Itemtype itemTypes = entityManager.find(Itemtype.class,form.getItemTypeId());
			model.setItemType(itemTypes);
			entityManager.persist(model);
		}
	@Override
	@Transactional
	public List<Items> getAllItem()
		{
			@SuppressWarnings("unchecked")
			List<Items> item = entityManager.createQuery("SELECT i FROM Items i WHERE i.isDeleted=0").getResultList();
			return item;
		}
}
