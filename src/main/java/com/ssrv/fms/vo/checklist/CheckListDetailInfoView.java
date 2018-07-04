package com.ssrv.fms.vo.checklist;

import java.util.List;

public class CheckListDetailInfoView
	{
		private Long checkListId;
		private List<CheckListDetailsForm> checkListdetail;
		public Long getCheckListId()
			{
				return checkListId;
			}
		public void setCheckListId(Long checkListId)
			{
				this.checkListId = checkListId;
			}
		public List<CheckListDetailsForm> getCheckListdetail()
			{
				return checkListdetail;
			}
		public void setCheckListdetail(List<CheckListDetailsForm> checkListdetail)
			{
				this.checkListdetail = checkListdetail;
			}
	}
