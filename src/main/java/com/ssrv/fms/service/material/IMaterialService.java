package com.ssrv.fms.service.material;

import java.util.List;

import com.ssrv.fms.excetptions.IndentDetailNotFoundException;
import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.incident.Indentdetails;
import com.ssrv.fms.model.item.Itemstocklevels;
import com.ssrv.fms.vo.material.MaterialConsumptionInfo;
import com.ssrv.fms.vo.material.MaterialIndentInfo;
import com.ssrv.fms.vo.material.MaterialReceiptInfo;

public interface IMaterialService
	{

		void saveMaterialIndent(MaterialIndentInfo materialIndent);

		Boolean deleteMaterialIndent(Long materialIndentId, Long itemId) throws IndentDetailNotFoundException;

		MaterialIndentInfo getMaterialIndentById(Long id);
		
		//List<IndentDetailInfoView> getIndentDetails(Long organizationId,Long branchId,Long mangedEntityId);
		
		List<Indentdetails> getIndentDetail(Long organizationId,Long branchId,Long mangedEntityId);
		
		Materialindents getMaterialIndentId(Long organizationId,Long branchId,Long mangedEntityId);

		List<MaterialReceiptInfo> getMaterialReceipt(Long branchId);

		void saveMaterialReceipt(MaterialReceiptInfo materialReceipt);

		void saveMaterialConsumption(MaterialConsumptionInfo materialConsumption);
		
		//MaterialList
		
		String saveMaterialList(MaterialIndentInfo form);
		
		public List<Itemstocklevels> getItemStockLevel(Long organizationId, Long branchId, Long mangedEntityId);
	}
