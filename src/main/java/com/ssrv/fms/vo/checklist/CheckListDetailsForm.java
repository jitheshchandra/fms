package com.ssrv.fms.vo.checklist;

import java.sql.Date;


public class CheckListDetailsForm
	{
		private long id;
		private String name;
		private Long managedEntityTypeId;
		private Long managedEntitySubTypeId;
		private Boolean checked;
		private Boolean unchecked;
	
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getManagedEntityTypeId() {
			return managedEntityTypeId;
		}

		public void setManagedEntityTypeId(Long managedEntityTypeId) {
			this.managedEntityTypeId = managedEntityTypeId;
		}

		public Long getManagedEntitySubTypeId() {
			return managedEntitySubTypeId;
		}

		public void setManagedEntitySubTypeId(Long managedEntitySubTypeId) {
			this.managedEntitySubTypeId = managedEntitySubTypeId;
		}

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

		public Boolean getUnchecked() {
			return unchecked;
		}

		public void setUnchecked(Boolean unchecked) {
			this.unchecked = unchecked;
		}

		
		
		
	}
