package com.ssrv.fms.vo.checklist;

import java.sql.Date;


public class CheckListSummaryForm
	{
		private long id;
		private String name;
		private String description;
		private String managedEntityTypeId;
		private String managedEntitySubTypeId;
		private String key;
		
	
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

		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getManagedEntityTypeId() {
			return managedEntityTypeId;
		}

		public void setManagedEntityTypeId(String managedEntityTypeId) {
			this.managedEntityTypeId = managedEntityTypeId;
		}

		public String getManagedEntitySubTypeId() {
			return managedEntitySubTypeId;
		}

		public void setManagedEntitySubTypeId(String managedEntitySubTypeId) {
			this.managedEntitySubTypeId = managedEntitySubTypeId;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		
	}