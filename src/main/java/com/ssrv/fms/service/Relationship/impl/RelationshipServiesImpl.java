package com.ssrv.fms.service.Relationship.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.Relationships;
import com.ssrv.fms.service.Relationship.intf.RelationshipServiesIntf;
import com.ssrv.fms.vo.Relationship.RelationshipForm;

@Service
@Lazy
public class RelationshipServiesImpl implements RelationshipServiesIntf {

	@PersistenceContext
	private EntityManager entityManager;

	
	//to get all relationship
	
	@SuppressWarnings("unchecked")
	public List<Relationships> getAllRelation() {
		
//		
//		List<Country> countries = (List<Country>) entityManager.createQuery(
//				"SELECT C FROM Country C WHERE isDeleted=0").getResultList();
//		System.out.println("country:" + countries);
//		return countries;
		
		
		List<Relationships> relation = (List<Relationships>) entityManager
				.createQuery("SELECT r FROM Relationships r WHERE isDeleted=0")
				.getResultList();
		System.out.println("relation:" + relation);
		return relation;
	}

	//To save relationship
	@Transactional
	public void SaveRelation(RelationshipForm form) {
		Relationships relation = new Relationships();
		relation.setName(form.getName());
		relation.setIsDeleted((short) 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

		relation.setModifiedOn(date);

		entityManager.persist(relation);

	}

	
	//To delete relationship
	@Transactional
	public String deleteRelation(int id) {
		long rl_id = (long) id;

		Relationships rel = (Relationships) entityManager.find(
				Relationships.class, rl_id);

		if (rel != null) {

			rel.setIsDeleted((short) 1);

			entityManager.merge(rel);

			return "entity updated successfully";

		} else

			return "Entity does not exist";
	}

	
	//Get Relation by using id
	
	public Relationships getRelationByID(long id) {
		Relationships relation = entityManager.find(Relationships.class, id);
		return relation;
	}

	
	// update relation
	@Transactional
	public void updateRelation(RelationshipForm form) {
		Relationships relation = entityManager.find(Relationships.class,
				form.getId());

		relation.setName(form.getName());

	}

}
