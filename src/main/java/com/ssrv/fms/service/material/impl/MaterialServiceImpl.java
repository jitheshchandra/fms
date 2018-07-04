package com.ssrv.fms.service.material.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.dao.material.IItemtypeDAO;
import com.ssrv.fms.dao.material.IMaterialDAO;
import com.ssrv.fms.excetptions.IndentDetailNotFoundException;
import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.incident.Indentdetails;
import com.ssrv.fms.model.incident.IndentdetailsPK;
import com.ssrv.fms.model.item.Itemconsumption;
import com.ssrv.fms.model.item.Itemdelivery;
import com.ssrv.fms.model.item.Itemdeliverydetails;
import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemstocklevels;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.material.IItemService;
import com.ssrv.fms.service.material.IMaterialService;
import com.ssrv.fms.vo.indent.IndentDetailInfo;
import com.ssrv.fms.vo.indent.IndentDetailInfoForm;
import com.ssrv.fms.vo.material.MaterialConsumptionInfo;
import com.ssrv.fms.vo.material.MaterialConsumptionList;
import com.ssrv.fms.vo.material.MaterialIndentInfo;
import com.ssrv.fms.vo.material.MaterialIndentInfoView;
import com.ssrv.fms.vo.material.MaterialReceiptDetailInfo;
import com.ssrv.fms.vo.material.MaterialReceiptDetailInfoForm;
import com.ssrv.fms.vo.material.MaterialReceiptInfo;

@Service
@Lazy
@Component
public class MaterialServiceImpl implements IMaterialService
	{
		@PersistenceContext
		private EntityManager entityManager;

		@Autowired
		@Qualifier(value = "baseDao")
		private IBaseDAO baseDao;

		@Autowired
		private IMaterialDAO materialDao;

		@Autowired
		private IItemtypeDAO itemDao;

		@Autowired
		private IItemService itemService;

		/*** MaterialIndent Section ***/

		// Saving Material Indent
		@Override
		@Transactional
		public void saveMaterialIndent(MaterialIndentInfo materialIndent)
			{
				Materialindents indent = toMaterialIndent(materialIndent);
				baseDao.save(indent);
			}

		private Materialindents toMaterialIndent(MaterialIndentInfo materialIndentInfo)
			{
				Materialindents materialIndents = null;
				Long materialIndentId = materialIndentInfo.getIndentDetails().get(0).getMaterialIndentId();
				if (materialIndentId != null)
					{
						materialIndents = baseDao.findById(Materialindents.class, materialIndentId);
					} else
					{
						materialIndents = new Materialindents();
					}
				// materialIndents.setName(materialIndentInfo.getName());
				// materialIndents.setBranch(materialIndentInfo.getBranch());
				// materialIndents.setOrganization(materialIndentInfo.getOrganization());
				List<Indentdetails> indentDetails = new ArrayList<Indentdetails>();
				for (IndentDetailInfo indentDetailInfo : materialIndentInfo.getIndentDetails())
					{
						indentDetails.add(toIndentsDetails(indentDetailInfo));
					}
				materialIndents.setIndentdetails(indentDetails);
				return materialIndents;
			}

		private Indentdetails toIndentsDetails(IndentDetailInfo indentDetailInfo)
			{
				Validate.notNull(indentDetailInfo.getMaterialIndentId(), "materialIndentId cannot be null");
				Validate.notNull(indentDetailInfo.getItemId(), "itemId cannot be null");
				IndentdetailsPK primaryKey = new IndentdetailsPK(indentDetailInfo.getMaterialIndentId(), indentDetailInfo.getItemId());
				Indentdetails indentDetail = baseDao.findById(Indentdetails.class, primaryKey);
				if (indentDetail == null)
					{
						indentDetail = new Indentdetails(primaryKey);
					}
				indentDetail.setReorderLevel(indentDetailInfo.getReOrderlevel());
				indentDetail.setQuantity(indentDetailInfo.getOrderQuantity());
				return indentDetail;
			}

		// Deleting Indent Detail Row
		@Override
		@Transactional
		public Boolean deleteMaterialIndent(Long materialIndentId, Long itemId) throws IndentDetailNotFoundException
			{
				Validate.notNull(materialIndentId, "materialIndentId cannot be null");
				Validate.notNull(itemId, "itemId cannot be null");
				IndentdetailsPK primaryKey = new IndentdetailsPK(materialIndentId, itemId);
				Indentdetails indentDetail = baseDao.findById(Indentdetails.class, primaryKey);
				if (indentDetail == null)
					{
						throw new IndentDetailNotFoundException("Indent Detail with id materialIndentId:" + materialIndentId + ", itemId:" + itemId
								+ " not found");

					}

				@SuppressWarnings("unchecked")
				List<Itemstocklevels> itemStockDetails = entityManager.createQuery(
						"SELECT i FROM Itemstocklevels i WHERE i.materialIndent=" + materialIndentId + "AND i.item=" + itemId).getResultList();
				if (!itemStockDetails.isEmpty())
					{
						Itemstocklevels itemStockDetail = (Itemstocklevels) entityManager.createQuery(
								"SELECT i FROM Itemstocklevels i WHERE i.materialIndent=" + materialIndentId + "AND i.item=" + itemId)
								.getSingleResult();
						baseDao.delete(itemStockDetail);
					}
				baseDao.delete(indentDetail);
				return true;
			}

		// Getting material receipt
		@Override
		public List<MaterialReceiptInfo> getMaterialReceipt(Long branchId)
			{
				// TODO Auto-generated method stub
				return null;
			}

		// Saving material receipt
		@Override
		@Transactional
		public void saveMaterialReceipt(MaterialReceiptInfo materialReceipt)
			{
				Itemdelivery itemDelivery = null;
				if (materialReceipt.getId() != null)
					{
						itemDelivery = materialDao.findById(Itemdelivery.class, materialReceipt.getId());
					}
				if (itemDelivery == null)
					{
						itemDelivery = new Itemdelivery();
					}
				toItemDelivery(materialReceipt, itemDelivery);
				for (MaterialReceiptDetailInfoForm detail : materialReceipt.getIndentDetail())
					{
						Validate.notNull(detail.getItemId(), "MaterialReceiptInfo[details].item cannot be null");
						Validate.notNull(materialReceipt.getMaterialIndentId(), "MaterialReceiptInfo[details].materialIndent cannot be null");
						Items item = entityManager.find(Items.class, detail.getItemId());
						Materialindents indent = entityManager.find(Materialindents.class, materialReceipt.getMaterialIndentId());
						updateStockLevel(item, indent, detail.getQuantity(), Boolean.TRUE);
					}
				materialDao.save(itemDelivery);

				for (MaterialReceiptDetailInfoForm detail : materialReceipt.getIndentDetail())
					{
						Items item = entityManager.find(Items.class, detail.getItemId());
						Materialindents indent = entityManager.find(Materialindents.class, materialReceipt.getMaterialIndentId());
						saveItemDeliveryIndetail(itemDelivery, indent, item, detail.getQuantity(), Boolean.TRUE);
					}

			}

		private void saveItemDeliveryIndetail(Itemdelivery itemDelivery, Materialindents indentDetail, Items item, Float quantity, Boolean delivery)
			{
				Itemdeliverydetails itemDeliveryDetail = new Itemdeliverydetails();
				itemDeliveryDetail.setDeliveryDate(new Date());
				itemDeliveryDetail.setMaterialIndent(indentDetail);
				itemDeliveryDetail.setItem(item);
				itemDeliveryDetail.setDelivery(itemDelivery);
				itemDeliveryDetail.setQuantity(quantity);
				materialDao.save(itemDeliveryDetail);
			}

		private void toItemDelivery(MaterialReceiptInfo materialReceipt, Itemdelivery itemdelivery)
			{
				itemdelivery.setOrderDate(materialReceipt.getReceiptDate());
				itemdelivery.setOrderNo(materialReceipt.getReceiptNumber());
				itemdelivery.setModifiedOn(new Date());
				List<MaterialReceiptDetailInfoForm> materialReceiptDetails = materialReceipt.getIndentDetail();
				for (MaterialReceiptDetailInfo materialReceiptDetail : materialReceiptDetails)
					{
						itemdelivery.getItemDeliveryDetails();
						materialReceiptDetail.getItemDelivery();
					}
			}

		// Updating MaterialStokLevel table
		@Transactional
		private void updateStockLevel(Items item, Materialindents materialIndent, Float quantity, Boolean delivery)
			{
				List itemStock = entityManager.createQuery(
						"SELECT i FROM Itemstocklevels i WHERE i.item=" + item.getId() + " AND i.materialIndent=" + materialIndent.getId())
						.getResultList();
				if (itemStock.isEmpty())
					{
						Itemstocklevels gettingNewStock = new Itemstocklevels();
						gettingNewStock.setItemId(item);
						gettingNewStock.setMaterialIndent(materialIndent);
						gettingNewStock.setQuantityInStock(quantity);
						gettingNewStock.setStockTakingDate(new Date());
						itemDao.save(gettingNewStock);
					} else
					{
						Itemstocklevels gettingNewStock = (Itemstocklevels) entityManager.createQuery(
								"SELECT i FROM Itemstocklevels i WHERE i.item=" + item.getId() + " AND i.materialIndent=" + materialIndent.getId())
								.getSingleResult();
						gettingNewStock.setQuantityInStock((Float) (gettingNewStock.getQuantityInStock() + quantity));
						gettingNewStock.setStockTakingDate(new Date());
						itemDao.save(gettingNewStock);
					}
			}

		// Saving Material Consumption
		@Override
		@Transactional
		public void saveMaterialConsumption(MaterialConsumptionInfo materialConsumption)
			{
				List<MaterialConsumptionList> consumptionList = materialConsumption.getMaterialConsumptionList();
				for (MaterialConsumptionList consumed : consumptionList)
					{
					if(consumed.getConsumedQuantity()!=0){
						Itemconsumption itemConsumption = new Itemconsumption();
						itemConsumption.setConsumptionDate(materialConsumption.getConsumptionDate());
						Items item = itemDao.findById(Items.class, consumed.getItemId());
						itemConsumption.setItem(item);
						Materialindents indentId = itemDao.findById(Materialindents.class, materialConsumption.getMaterialIndentId());
						itemConsumption.setMaterialindents(indentId);
						itemConsumption.setQuantity(consumed.getConsumedQuantity());
						itemDao.save(itemConsumption);
						updateStockLevelByConsumption(item, indentId, consumed.getConsumedQuantity(), Boolean.FALSE);
					}
					}
			}

		// Updating stock level in ItemStock table
		@Transactional
		private void updateStockLevelByConsumption(Items item, Materialindents materialIndent, Float quantity, Boolean delivery)
			{
				List itemStock = entityManager.createQuery(
						"SELECT i FROM Itemstocklevels i WHERE i.item=" + item.getId() + " AND i.materialIndent=" + materialIndent.getId())
						.getResultList();
				if (itemStock.isEmpty())
					{
						Itemstocklevels gettingNewStock = new Itemstocklevels();
						gettingNewStock.setItemId(item);
						gettingNewStock.setMaterialIndent(materialIndent);
						gettingNewStock.setQuantityInStock(quantity);
						gettingNewStock.setStockTakingDate(new Date());
						itemDao.save(gettingNewStock);
					} else
					{
						Itemstocklevels gettingNewStock = (Itemstocklevels) entityManager.createQuery(
								"SELECT i FROM Itemstocklevels i WHERE i.item=" + item.getId() + " AND i.materialIndent=" + materialIndent.getId())
								.getSingleResult();
						gettingNewStock.setQuantityInStock((Float) (gettingNewStock.getQuantityInStock() - quantity));
						gettingNewStock.setStockTakingDate(new Date());
						itemDao.save(gettingNewStock);
					}
			}

		// Getting MaterialIndent based on Id
		@Override
		public MaterialIndentInfo getMaterialIndentById(Long id)
			{
				Materialindents materialIndent = baseDao.findById(Materialindents.class, id);
				if (materialIndent == null)
					{
						return null;
					}
				MaterialIndentInfo materialIndentInfo = new MaterialIndentInfoView(materialIndent);
				return materialIndentInfo;
			}

		// Getting Indent Detail by using Organization,Branch & ManagedEntity
		@Override
		@Transactional
		public List<Indentdetails> getIndentDetail(Long organizationId, Long branchId, Long mangedEntityId)
			{
				List materialIndent = entityManager.createQuery(
						"SELECT m FROM Materialindents m WHERE m.organization=" + organizationId + "AND m.branch=" + branchId
								+ "AND m.managedEntity=" + mangedEntityId).getResultList();
				if (materialIndent.isEmpty())
					{
						Materialindents model = new Materialindents();
						model.setOrganization(entityManager.find(Organization.class, organizationId));
						model.setBranch(entityManager.find(Branch.class, branchId));
					//	model.setManagedEntity(entityManager.find(ManagedEntityGroup.class, mangedEntityId));
						model.setModifiedOn(new Date());
						entityManager.persist(model);
					}
				Materialindents getMaterialIndentId = (Materialindents) entityManager.createQuery(
						"SELECT m FROM Materialindents m WHERE m.organization=" + organizationId + "AND m.branch=" + branchId
								+ "AND m.managedEntity=" + mangedEntityId).getSingleResult();
				@SuppressWarnings("unchecked")
				List<Indentdetails> indentDetail = entityManager.createQuery(
						"SELECT i FROM Indentdetails i WHERE i.id.indentId=" + getMaterialIndentId.getId()).getResultList();
				return indentDetail;
			}

		@Override
		public Materialindents getMaterialIndentId(Long organizationId, Long branchId, Long mangedEntityId)
			{
				Materialindents getMaterialIndentId = (Materialindents) entityManager.createQuery(
						"SELECT m FROM Materialindents m WHERE m.organization=" + organizationId + "AND m.branch=" + branchId
								+ "AND m.managedEntity=" + mangedEntityId).getSingleResult();
				return getMaterialIndentId;
			}

		// Getting Stock Level by using Organization,Branch & ManagedEntity
		public List<Itemstocklevels> getItemStockLevel(Long organizationId, Long branchId, Long mangedEntityId)
			{

				List materialIndent = entityManager.createQuery(
						"SELECT m FROM Materialindents m WHERE m.organization=" + organizationId + "AND m.branch=" + branchId
								+ "AND m.managedEntity=" + mangedEntityId).getResultList();
				if (materialIndent.isEmpty())
					{
						Materialindents model = new Materialindents();
						model.setOrganization(entityManager.find(Organization.class, organizationId));
						model.setBranch(entityManager.find(Branch.class, branchId));
					//	model.setManagedEntity(entityManager.find(ManagedEntityGroup.class, mangedEntityId));
						model.setModifiedOn(new Date());
						entityManager.persist(model);
					}

				Materialindents getMaterialIndentId = (Materialindents) entityManager.createQuery(
						"SELECT m FROM Materialindents m WHERE m.organization=" + organizationId + "AND m.branch=" + branchId
								+ "AND m.managedEntity=" + mangedEntityId).getSingleResult();
				@SuppressWarnings("unchecked")
				List<Itemstocklevels> itemStockDetail = entityManager.createQuery(
						"SELECT i FROM Itemstocklevels i WHERE i.materialIndent=" + getMaterialIndentId.getId()).getResultList();
				return itemStockDetail;
			}

		// As of now below code is not using
		@Override
		@Transactional
		public String saveMaterialList(MaterialIndentInfo form)
			{
				List<IndentDetailInfoForm> indentDetailForm = form.getIndentDetails();
				for (IndentDetailInfoForm indentForm : indentDetailForm)
					{
						Indentdetails i = new Indentdetails();
						IndentdetailsPK primaryKey = new IndentdetailsPK(indentForm.getItemId(), indentForm.getMaterialIndentId());
						i.setId(primaryKey);
						// i.setMaterialindents(baseDao.findById(Materialindents.class,indentForm.getItemId()));
						// i.setItems(baseDao.findById(Items.class,
						// indentForm.getItemId()));
						i.setQuantity(indentForm.getOrderQuantity());
						i.setReorderLevel(indentForm.getReOrderlevel());
						entityManager.persist(i);
					}
				return null;
			}
	}
