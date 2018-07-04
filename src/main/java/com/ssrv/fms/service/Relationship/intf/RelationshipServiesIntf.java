package com.ssrv.fms.service.Relationship.intf;

import com.ssrv.fms.model.Relationships;
import com.ssrv.fms.vo.Relationship.RelationshipForm;

import java.util.List;

public interface RelationshipServiesIntf {
	
		public List<Relationships> getAllRelation();

		public void SaveRelation(RelationshipForm form);

		public String deleteRelation(int id);

		public Relationships getRelationByID(long id);
		
		public void updateRelation(RelationshipForm cnt);

	}

	


