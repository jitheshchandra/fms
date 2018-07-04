package com.ssrv.fms.vo.indent;

import com.ssrv.fms.model.incident.Indentdetails;

public class IndentDetailInfoWrapper implements IndentDetailInfo
	{
		private final Indentdetails indentDetail;
		public IndentDetailInfoWrapper(Indentdetails indentDetail)
			{
				this.indentDetail = indentDetail;
			}
		@Override
		public Long getMaterialIndentId()
			{
				return indentDetail.getId().getIndentId();
			}
		@Override
		public Long getItemId()
			{
				return indentDetail.getId().getItemId();
			}
		@Override
		public Float getOrderQuantity()
			{
				return indentDetail.getQuantity();
			}
		@Override
		public Float getReOrderlevel()
			{
				return indentDetail.getReorderLevel();
			}
	}
