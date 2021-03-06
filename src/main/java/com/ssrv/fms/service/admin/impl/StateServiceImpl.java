package com.ssrv.fms.service.admin.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.service.admin.intf.StateService;
import com.ssrv.fms.vo.state.StateForm;

@Service
@Lazy
public class StateServiceImpl implements StateService
	{

		@PersistenceContext
		private EntityManager entityManager;

		// Get All State
		@SuppressWarnings("unchecked")
		@Transactional
		public List<States> getallStateNames()
			{
				List<States> states = entityManager.createQuery("SELECT S FROM States S WHERE S.isDeleted=0").getResultList();
				//query.setParameter(1, 0);
				//List<States> states = query.getResultList();
				// List<States> states = (List<States>)
				// entityManager.createQuery(
				// "SELECT S FROM States S WHERE S.isDeleted=? AND S.countryId=?").getResultList();
				System.out.println("Insert to state querry get exicute");
				System.out.println("States Are:" + states);
				return states;
			}

		// Save State
		@Transactional
		public void SaveState(StateForm form)
			{

				States state = new States();

				// Country cntrId = entityManager.find(Country.class,
				// form.getCntrId());
				Country cntrId = entityManager.find(Country.class, form.getCntrId());

				state.setCountryId(cntrId);

				state.setName(form.getName());

				state.setIsDeleted(0);

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				Date date = new Date();
				System.out.println(dateFormat.format(date));
				state.setModifiedOn(date);

				entityManager.persist(state);

			}

		// To delete particular row
		@Transactional
		public void deleteState(int id)
			{

				long sts_id = (long) id;

				States state = entityManager.find(States.class, sts_id);

				state.setIsDeleted(1);

			}

		// to Edit State
		public States getStateById(Long id)
			{

				States state = entityManager.find(States.class, id);
				
//				StateForm stateForm= new StateForm();
//				state.setId(stateForm.getId());
//				Country cId = entityManager.find(Country.class, stateForm.cntrId);
//				state.setCountryId(cId);
//				state.setName(stateForm.getName());
				
				return state;
			}

		// to udate State

		@Transactional
		public void updateState(StateForm form)
			{

				States state = entityManager.find(States.class, form.getId());
				state.setName(form.getName());
				/*
				 * Country country = entityManager.find(Country.class,
				 * form.getCntr_id());
				 * state.setCountryId(country);
				 */

			}

		public List<States> getAllStateByCountryId(long id)
			{
				try
					{
						@SuppressWarnings("unchecked")
						List<States> states = entityManager.createQuery("SELECT S FROM States S WHERE S.isDeleted=0 AND S.countryId=" + id).getResultList();
						
						return states;
					}
				catch (Exception ex)
					{
						return null;
					}

			}

		public long getCountryByStateId(long stateId)
			{
			
				Query query = entityManager.createQuery("SELECT S.countryId FROM States S WHERE S.id =" + stateId);
				//System.out.print("country Id is"+cntrId);
				long cntrId = (Long) query.getSingleResult();
				
			
				return cntrId;
				
			}

		public StateForm getStateFomByStateId(long id)
			{
				States state = entityManager.find(States.class, id);
				StateForm stateForm= new StateForm();
				
				
				stateForm.setId(state.getId());
     			//stateForm.getId(state.setId(id));
				//Country cId = entityManager.find(Country.class, stateForm.cntrId);
				Country cId = entityManager.find(Country.class, state.getCountryId());
			//	stateForm.setCntrId(cId);
				
	    		state.setName(stateForm.getName());
				return stateForm;
			}

		@Override
		public List<States> getAllStates() {
			@SuppressWarnings("unchecked")
			List<States> state = entityManager.createQuery("SELECT S FROM States S WHERE S.isDeleted=0").getResultList();
			return state;
		}

	}
