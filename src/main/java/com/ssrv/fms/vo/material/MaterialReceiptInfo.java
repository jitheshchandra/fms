package com.ssrv.fms.vo.material;

import java.sql.Date;
import java.util.List;

import com.ssrv.fms.model.branch.Branch;

public interface MaterialReceiptInfo
	{
		Long getId();

		String getReceiptNumber();

		Branch getBranch();

		Date getReceiptDate();

		List<MaterialReceiptDetailInfo> getIndentDetails();
		
		List<MaterialReceiptDetailInfoForm> getIndentDetail();
		
		public Long getMaterialIndentId();
	}
