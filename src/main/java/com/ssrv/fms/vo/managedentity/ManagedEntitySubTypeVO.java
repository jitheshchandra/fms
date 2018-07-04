package com.ssrv.fms.vo.managedentity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssrv.fms.model.managedentity.ManagedEntityType;

public class ManagedEntitySubTypeVO {
	
	
	   private Long id;
	    
	  
	    private String name;

	  
	    private String managedEntityTypeName;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getManagedEntityTypeName() {
			return managedEntityTypeName;
		}


		public void setManagedEntityTypeName(String managedEntityTypeName) {
			this.managedEntityTypeName = managedEntityTypeName;
		}
	    
	  
	  
}
