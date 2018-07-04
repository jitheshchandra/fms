package com.ssrv.fms.vo.material.item;

import java.util.List;

public class IndentDetailForm
	{	
		private Long organizationId;
		private Long branche;
		private Long manageEntitye;	
		private List<IndentDetailsView> indentDetails;
		public Long getOrganizationId()
			{
				return organizationId;
			}
		public void setOrganizationId(Long organizationId)
			{
				this.organizationId = organizationId;
			}
		public Long getBranche()
			{
				return branche;
			}
		public void setBranche(Long branche)
			{
				this.branche = branche;
			}
		public Long getManageEntitye()
			{
				return manageEntitye;
			}
		public void setManageEntitye(Long manageEntitye)
			{
				this.manageEntitye = manageEntitye;
			}
		public List<IndentDetailsView> getIndentDetails()
			{
				return indentDetails;
			}
		public void setIndentDetails(List<IndentDetailsView> indentDetails)
			{
				this.indentDetails = indentDetails;
			}
	}
