package com.ssrv.fms.service.organization.intf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.Unitsofmeasurement;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.service.material.IItemService;
import com.ssrv.fms.service.material.IMaterialService;
import com.ssrv.fms.test.BaseFMSTester;
import com.ssrv.fms.vo.material.MaterialIndentInfo;
import com.ssrv.fms.vo.material.MaterialReceiptDetailInfo;
import com.ssrv.fms.vo.material.MaterialReceiptDetailInfoForm;
import com.ssrv.fms.vo.material.MaterialReceiptInfoForm;
import com.ssrv.fms.vo.material.item.ItemInfo;
import com.ssrv.fms.vo.material.item.ItemInfoForm;

public class MaterialIntegrationTest extends BaseFMSTester {
	@Autowired
	private IMaterialService materialService;

	@Autowired
	private IItemService itemService;

	@Autowired
	@Qualifier(value = "baseDao")
	private IBaseDAO dao;

	// TODO: in progress.
	@Test
	public void saveItemType() {
		Itemtype type = new Itemtype();
		type.setIsDeleted(0);
		type.setModifiedBy(new BigInteger("1"));
		type.setModifiedOn(new Date());
		type.setName("type-1");
		itemService.saveItemType(type);
	}

	@Test
	public void saveItem() {
		ItemInfoForm itemForm = new ItemInfoForm();
		Date now = new Date();
		itemForm.setItemCode("code1" + now.getTime());
		itemForm.setName("name" + now.getTime());
		Itemtype type = itemService.getItemType(1l);
		itemForm.setItemTypeId(type.getId());

		Unitsofmeasurement uom = dao.findById(Unitsofmeasurement.class, 1l);
		itemForm.setUomId(uom.getId());

		itemService.saveItem(itemForm);
	}

	@Test
	public void saveMaterialReceipt() {

		MaterialReceiptInfoForm receiptForm = new MaterialReceiptInfoForm();
		receiptForm.setReceiptDate((java.sql.Date) new Date());
		receiptForm.setReceiptNumber("111111" + new Date().getTime());

		List<MaterialReceiptDetailInfo> indentDetails = new ArrayList<MaterialReceiptDetailInfo>();
		MaterialIndentInfo materialIndent = materialService
				.getMaterialIndentById(1l);
		ItemInfo item = itemService.getItem(1l);
		MaterialReceiptDetailInfoForm indentDetailsForm = new MaterialReceiptDetailInfoForm(item, materialIndent);
		indentDetails.add(indentDetailsForm);
		
		//receiptForm.setIndentDetail(indentDetails);
		receiptForm.setIndentDetails(indentDetails);

		materialService.saveMaterialReceipt(receiptForm);

	}
}
