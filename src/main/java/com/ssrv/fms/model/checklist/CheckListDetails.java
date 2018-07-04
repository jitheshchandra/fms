package com.ssrv.fms.model.checklist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssrv.fms.model.managedentity.ManagedEntityInstance;
import com.ssrv.fms.model.managedentity.ManagedEntityType;

@Entity
@Table(name = "checklistdetail")
public class CheckListDetails implements Serializable
	{
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Basic(optional = false)
		@Column(name = "Id")
		private Long id;

		

	    @Column(name = "ManagedEntityType")
		private long managedEntityType;
		
		@Column(name = "ManagedEntitySubType")
		private long managedEntitySubType;
		
		@Column(name = "ChecklistName")
		private String checklistName;
		
		@Column(name = "Value")
		private boolean value;
		
		
		@Column(name = "IsDeleted")
		private Short isDeleted;

		@Column(name = "ModifiedOn")
		private Date modifiedOn;

		@Column(name = "ModifiedBy")
		private Long modifiedBy;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public long getManagedEntityType() {
			return managedEntityType;
		}

		public void setManagedEntityType(long managedEntityType) {
			this.managedEntityType = managedEntityType;
		}

		public long getManagedEntitySubType() {
			return managedEntitySubType;
		}

		public void setManagedEntitySubType(long managedEntitySubType) {
			this.managedEntitySubType = managedEntitySubType;
		}

		public String getChecklistName() {
			return checklistName;
		}

		public void setChecklistName(String checklistName) {
			this.checklistName = checklistName;
		}

		public boolean isValue() {
			return value;
		}

		public void setValue(boolean value) {
			this.value = value;
		}

		public Short getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Short isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Date getModifiedOn() {
			return modifiedOn;
		}

		public void setModifiedOn(Date modifiedOn) {
			this.modifiedOn = modifiedOn;
		}

		public Long getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(Long modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		



	}
