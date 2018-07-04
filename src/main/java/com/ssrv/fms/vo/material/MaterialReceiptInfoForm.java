package com.ssrv.fms.vo.material;

import java.sql.Date;
import java.util.List;

import com.ssrv.fms.model.branch.Branch;

public class MaterialReceiptInfoForm implements MaterialReceiptInfo
	{

		private Long id;
		private String receiptNumber;
		private Branch branch;
		private Date receiptDate;
		private List<MaterialReceiptDetailInfoForm> indentDetail;
		private List<MaterialReceiptDetailInfo> indentDetails;
		
		
//
//		public void setIndentDetails(List<MaterialReceiptDetailInfo> indentDetails)
//			{
//				this.indentDetails = indentDetails;
//			}

		
		@Override
		public Long getId()
			{
				return id;
			}

		public void setId(Long id)
			{
				this.id = id;
			}

		@Override
		public String getReceiptNumber()
			{
				return receiptNumber;
			}

		public void setReceiptNumber(String receiptNumber)
			{
				this.receiptNumber = receiptNumber;
			}

		@Override
		public Branch getBranch()
			{
				return branch;
			}

		public void setBranch(Branch branch)
			{
				this.branch = branch;
			}

		@Override
		public Date getReceiptDate()
			{
				return receiptDate;
			}

		public void setReceiptDate(Date receiptDate)
			{
				this.receiptDate = receiptDate;
			}

		@Override
		public List<MaterialReceiptDetailInfo> getIndentDetails()
			{
				// return indentDetail;
				return indentDetails;
			}

		public void setIndentDetails(List<MaterialReceiptDetailInfo> indentDetails)
			{
				this.indentDetails = indentDetails;
			}

		public List<MaterialReceiptDetailInfoForm> getIndentDetail()
			{
				return indentDetail;
			}

		public void setIndentDetail(List<MaterialReceiptDetailInfoForm> indentDetail)
			{
				this.indentDetail = indentDetail;
			}

		// Changes: Raghu
		private Long materialIndentId;

		public Long getMaterialIndentId()
			{
				return materialIndentId;
			}

		public void setMaterialIndentId(Long materialIndentId)
			{
				this.materialIndentId = materialIndentId;
			}

	}
