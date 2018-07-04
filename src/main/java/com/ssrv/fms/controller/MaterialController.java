package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.excetptions.IndentDetailNotFoundException;
import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.Unitsofmeasurement;
import com.ssrv.fms.model.incident.Indentdetails;
import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemstocklevels;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityGroupService;
import com.ssrv.fms.service.material.IItemService;
import com.ssrv.fms.service.material.IMaterialService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.Designation.DesignationForm;
import com.ssrv.fms.vo.indent.IndentDetailInfoForm;
import com.ssrv.fms.vo.indent.IndentDetailInfoView;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryView;
import com.ssrv.fms.vo.material.MaterialConsumptionInfoForm;
import com.ssrv.fms.vo.material.MaterialIndentInfoForm;
import com.ssrv.fms.vo.material.MaterialReceiptInfoForm;
import com.ssrv.fms.vo.material.item.ItemInfoForm;
import com.ssrv.fms.vo.material.item.ItemTypeForm;

@Controller
public class MaterialController
	{

		@Autowired
		private IItemService itemService;

		@Autowired
		private OrganizationService organizationService;

		@Autowired
		private BranchService branchService;

		@Autowired
		private IManagedEntityGroupService managedEntityGroupService;

		@Autowired
		private IMaterialService materialService;

		@Autowired
		@Qualifier(value = "baseDao")
		private IBaseDAO baseDao;

		@Autowired
		private CheckListIntf checkListIntf;

		/*** Material Master ***/

		// MaterialMaster page
		@RequestMapping(value = "/MaterialMaster", method = RequestMethod.GET)
		public String materialMaster(Model materialMasterModel)
			{
				List<Itemtype> itemTypes = itemService.getAllItemType();
				materialMasterModel.addAttribute("itemTypes", itemTypes);
				List<Unitsofmeasurement> uoms = baseDao.getAll(Unitsofmeasurement.class);
				materialMasterModel.addAttribute("uoms", uoms);
				return "MaterialMaster";
			}

		// Saving MaterialMaster or Items
		@RequestMapping(value = "/AddMaterial", method = RequestMethod.POST)
		public String addMaterial(ItemInfoForm itemInfo, BindingResult result)
			{
				if (result.hasErrors())
					{
						return "MaterialMaster";
					}
				itemService.saveItem(itemInfo);
				return "redirect:/MaterialMaster";
			}

		/*** Material Consumption ***/

		// Material Consumption page
		@RequestMapping(value = "/MaterialConsumption", method = RequestMethod.GET)
		public String materialConsumption(Model materialConsumptionModel)
			{
				List<Organization> orgs = organizationService.getAllOrganizations();
				materialConsumptionModel.addAttribute("organizations", orgs);
				return "MaterialConsumption";
			}

		// Getting Item Stock based on Organization, Branch & Managed Entity
		// Id`s
		@RequestMapping(value = "/getItemStockLevel", method = RequestMethod.POST)
		public @ResponseBody List<IndentDetailInfoView> getItemsFromItemStockLevel(Model model, @RequestParam String organizationId,
				@RequestParam String branchId, @RequestParam String manageEntityId)
			{
				List<Itemstocklevels> indentList = materialService.getItemStockLevel(Long.parseLong(organizationId), Long.parseLong(branchId),
						Long.parseLong(manageEntityId));
				List<IndentDetailInfoView> indentLists = new ArrayList<IndentDetailInfoView>();
				if(indentList!=null){
				for (Itemstocklevels indent : indentList)
					{
						IndentDetailInfoView i = new IndentDetailInfoView();
						String itemName = indent.getItem().getName();
						Long itemId = indent.getItem().getId();
						Long indentId = indent.getMaterialIndent().getId();
						i.setItemId(itemId);
						i.setId(indentId);
						i.setName(itemName);
						i.setOrderQuantity(indent.getQuantityInStock());
						indentLists.add(i);
					}
				}
				return indentLists;
			}

		// Save Material Consumption
		@RequestMapping(value = "/saveMaterialConsumption", method = RequestMethod.POST)
		public String saveMaterialConsumption(MaterialConsumptionInfoForm materialConsumptionInfo, BindingResult result)
			{
				/*if (result.hasErrors())
					{
						return "MaterialConsumption";
					}*/
				materialService.saveMaterialConsumption(materialConsumptionInfo);
				return "redirect:/MaterialConsumption";
			}

		/*** Material Receipt ***/

		// Saving material receipt
		@RequestMapping(value = "/saveMaterialReceipt", method = RequestMethod.POST)
		public String saveMaterialReceipt(MaterialReceiptInfoForm materialReceipt, BindingResult result)
			{
				materialService.saveMaterialReceipt(materialReceipt);
				return "redirect:MaterialReceipt";
			}

		// Material receipt page
		@RequestMapping(value = "/MaterialReceipt")
		public String materialReceipt()
			{
				return "MaterialReceipt";
			}

		/*** Item Type ***/

		Date currentDate = new Date();

		// Getting ItemTypeSummary
		@RequestMapping(value = "/ItemTypeSummary", method = RequestMethod.GET)
		public String getAllItemType(Model itemTypeModel)
			{
				List<Itemtype> itemType = itemService.getAllItemType();
				itemTypeModel.addAttribute("itemType", itemType);
				return "ItemTypeSummary";
			}

		// Adding Item Type to database
		@RequestMapping(value = "/addItemType", method = RequestMethod.POST)
		public String saveItemType(Model itemTypeModel, ItemTypeForm itemTypeForm)
			{
				Itemtype itemTypeDao = new Itemtype();
				itemTypeDao.setIsDeleted(0);
				itemTypeDao.setName(itemTypeForm.getItemName());
				itemTypeDao.setModifiedOn(currentDate);
				itemService.saveItemType(itemTypeDao);
				return "redirect:/ItemTypeSummary";
			}
		// Open Editing ItemType page
		@RequestMapping(value = "/editItemType", method = RequestMethod.GET)
		public String editItemType(@RequestParam("id") long id, Model model)
			{
				Itemtype itemTypeName = itemService.getItemType(id);
				model.addAttribute("itemType", itemTypeName);
				return "EditItemType";
			}

		/// Update Country Details .
		@RequestMapping(value = "/updateitemType", method = RequestMethod.POST)
		public String updateItemType(ItemTypeForm form, ModelMap model)
		{
			try
			{
				itemService.updateitemType(form);
				return "redirect:/ItemTypeSummary";
			} 
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				return "ErrorMsg";
			}
		}
		
		// After editing Item type save into database
		@RequestMapping(value = "/updateItemtype", method = RequestMethod.POST)
		public String updateItemType(@RequestParam("id") long id, ItemTypeForm itemTypeForm)
			{
				itemService.updateItemType(id, itemTypeForm);
				return "redirect:/ItemTypeSummary";
			}

		// Deleting Item type
		@RequestMapping(value = "/deleteItemType", method = RequestMethod.GET)
		public String deleteItemType(@RequestParam("id") long id)
			{
				itemService.deleteItemType(id);
				return "redirect:/ItemTypeSummary";
			}

		/*** MaterialList ***/

		// Getting Organization list for MaterialList
		@RequestMapping(value = "/MaterialList", method = RequestMethod.GET)
		public String materialList(Model materialListModel)
			{
				List<Items> items = itemService.getAllItem();
				materialListModel.addAttribute("item", items);
				return "MaterialList";
			}

		// Getting Managed Entity based on branch
		@RequestMapping(value = "/getManagedEntitiesForBranch", method = RequestMethod.POST)
		public @ResponseBody List<ManagedEntityGroupSummaryView> getManagedEntitiesForBranch(@RequestParam String branchId)
			{
				List<ManagedEntityGroupSummaryView> managedEntityList = managedEntityGroupService.getManagedEntityGroupByBranch(Long
						.parseLong(branchId));
				return managedEntityList;
			}

		@RequestMapping(value = "/getListOfIndentDetails", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> getIndentDetails(@RequestParam String organizationId, @RequestParam String branchId,
				@RequestParam String manageEntityId)
			{
				Map<String, Object> returnMap = new HashMap<String, Object>();

				List<Items> itemList = itemService.getAllItem();
				List<ItemInfoForm> itemForm = new ArrayList<ItemInfoForm>();
				for (Items item : itemList)
					{
						ItemInfoForm items = new ItemInfoForm();
						items.setId(item.getId());
						items.setName(item.getName());
						itemForm.add(items);
					}
				returnMap.put("items", itemForm);

				List<Indentdetails> indentList = materialService.getIndentDetail(Long.parseLong(organizationId), Long.parseLong(branchId),
						Long.parseLong(manageEntityId));
				Materialindents materialIndent = materialService.getMaterialIndentId(Long.parseLong(organizationId), Long.parseLong(branchId),
						Long.parseLong(manageEntityId));
				List<IndentDetailInfoView> indentLists = new ArrayList<IndentDetailInfoView>();
				for (Indentdetails indent : indentList)
					{
						IndentDetailInfoView i = new IndentDetailInfoView();
						String itemName = indent.getItems().getName();
						Long itemId = indent.getItems().getId();
						i.setItemId(itemId);
						i.setId(materialIndent.getId());
						i.setName(itemName);
						i.setOrderQuantity(indent.getQuantity());
						i.setReOrderlevel(indent.getReorderLevel());
						indentLists.add(i);
					}
				returnMap.put("indentLists", indentLists);
				return returnMap;
			}
		
		@RequestMapping(value="/getListOfIndentDetail",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> getIndentListForMaterialReciept(@RequestParam String organizationId,@RequestParam String branchId,@RequestParam String manageEntityId)
		{
			Map<String, Object> resultMap=new HashMap<String, Object>();
			List<IndentDetailInfoView> indentdetailInfoList=new ArrayList<IndentDetailInfoView>();
			List<Indentdetails> indentList = materialService.getIndentDetail(Long.parseLong(organizationId),Long.parseLong(branchId),Long.parseLong(manageEntityId));
			for(Indentdetails list:indentList)
				{
					indentdetailInfoList.add(new IndentDetailInfoView(list));
				}
			resultMap.put("indentLists", indentdetailInfoList);
			return resultMap;		
		}

		// Deleting Indent Detail
		@RequestMapping(value = "/deleteIndentDetailList", method = RequestMethod.POST)
		public @ResponseBody boolean deleteIndentDetailList(@RequestParam String itemId, @RequestParam String materialIndentId)
				throws NumberFormatException, IndentDetailNotFoundException
			{
				Boolean results = materialService.deleteMaterialIndent(Long.parseLong(materialIndentId), Long.parseLong(itemId));
				return results;
			}

		// Saving Indent List
		@RequestMapping(value = "/finalTryToSaveMaterialList", method = RequestMethod.POST)
		public String finalTryToSaveMaterialList(MaterialIndentInfoForm form)
			{
				Materialindents materialIndent = materialService.getMaterialIndentId(form.getOrganizationId(), form.getBranchId(),
						form.getManagedEntityId());
				List<IndentDetailInfoForm> indentIds = form.getIndentDetails();
				for (IndentDetailInfoForm indentId : indentIds)
					{
						indentId.setMaterialIndentId(materialIndent.getId());
					}
				materialService.saveMaterialIndent(form);
				return "MaterialList";
			}
	}
