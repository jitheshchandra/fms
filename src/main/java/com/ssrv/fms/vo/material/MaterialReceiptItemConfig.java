package com.ssrv.fms.vo.material;

import java.sql.Date;
import java.util.List;

public class MaterialReceiptItemConfig
	{
		private long materialIndentId;
		private Date receiptDate;
		private String receiptNumber;
		//private List<MaterialReceiptItemList> indentDetails;
		public long getMaterialIndentId()
			{
				return materialIndentId;
			}
		public void setMaterialIndentId(long materialIndentId)
			{
				this.materialIndentId = materialIndentId;
			}
		public Date getReceiptDate()
			{
				return receiptDate;
			}
		public void setReceiptDate(Date receiptDate)
			{
				this.receiptDate = receiptDate;
			}
		public String getReceiptNumber()
			{
				return receiptNumber;
			}
		public void setReceiptNumber(String receiptNumber)
			{
				this.receiptNumber = receiptNumber;
			}
//		public List<MaterialReceiptItemList> getIndentDetails()
//			{
//				return indentDetails;
//			}
//		public void setIndentDetails(List<MaterialReceiptItemList> indentDetails)
//			{
//				this.indentDetails = indentDetails;
//			}
	}
