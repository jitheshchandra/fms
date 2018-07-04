package com.ssrv.fms.service.designation.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.service.designation.intf.DesignationService;
import com.ssrv.fms.vo.Designation.DesignationForm;

@Service
@Lazy
public class DesignationServiceImpl implements DesignationService {

	@PersistenceContext
	private EntityManager entityManager;

	// to Get all desination
	public List<Designations> getAllDesignations() {

		@SuppressWarnings("unchecked")
		List<Designations> designations = (List<Designations>) entityManager
				.createQuery("SELECT D FROM Designations D Where ISDELETED=0").getResultList();

		return designations;
	}

	// Save Designation
	@Transactional
	public void SaveDesignation(DesignationForm form) {

		Designations designation = new Designations();

		designation.setName(form.getName());

		designation.setIsDeleted(0);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date(0);
	    System.out.println(dateFormat.format(date));
			
		designation.setModifiedOn(date);

		entityManager.persist(designation);
	}

	// delete Designation
	@Transactional
	public void deleteDesignation(int id) {

		long ds_id = (long) id;

		Designations ds = entityManager.find(Designations.class, ds_id);

		if (ds != null) {

			ds.setIsDeleted(1);

			entityManager.merge(ds);
			System.out.println("Designation delete");
		} else
			System.out.println("Designation con`t delete");

	}

	
	//edit Designation
	public Designations getDesignationById(Long id) {
		Designations ds=entityManager.find(Designations.class, id);
		return ds;
	}
	
	//update Designation 
	@Transactional
    public void updateDesignation(DesignationForm form) {
		
    	Designations ds=entityManager.find(Designations.class, form.getId());
    	
    	ds.setName(form.getName());
		
	}
	
	
}
