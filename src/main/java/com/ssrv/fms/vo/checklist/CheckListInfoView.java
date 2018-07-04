package com.ssrv.fms.vo.checklist;

import java.util.List;

public class CheckListInfoView
	{
		private Long managedentitytypeId;
		private Long managedentitysubtypeId;
		private List<CheckListForm> checkListForms;

		public List<CheckListForm> getCheckListForms()
			{
				return checkListForms;
			}

		public void setCheckListForms(List<CheckListForm> checkListForms)
			{
				this.checkListForms = checkListForms;
			}

		public Long getManagedentitytypeId() {
			return managedentitytypeId;
		}

		public void setManagedentitytypeId(Long managedentitytypeId) {
			this.managedentitytypeId = managedentitytypeId;
		}

		public Long getManagedentitysubtypeId() {
			return managedentitysubtypeId;
		}

		public void setManagedentitysubtypeId(Long managedentitysubtypeId) {
			this.managedentitysubtypeId = managedentitysubtypeId;
		}

		
	}
