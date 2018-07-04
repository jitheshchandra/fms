package com.ssrv.fms.service.admin.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.service.admin.intf.CountryService;
import com.ssrv.fms.vo.country.CountryForm;

@Service
@Lazy
public class CountryServiceImpl implements CountryService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() {
		List<Country> countries = (List<Country>) entityManager.createQuery(
				"SELECT C FROM Country C WHERE isDeleted=0").getResultList();
		System.out.println("country:" + countries);
		return countries;
	}

	@Transactional
	public void SaveCountry(CountryForm form) {

		Country country = new Country();
		country.setName(form.getName());
		country.setIsDeleted(0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
	 System.out.println(dateFormat.format(date));
		
		country.setModifiedBy(date);
		
		entityManager.persist(country);

	}

	@Transactional
	public String deleteCountry(int id) {

		long cnt_id = (long) id;

		Country cnt = entityManager.find(Country.class, cnt_id);

		if (cnt != null) {

			cnt.setIsDeleted(1);

			entityManager.merge(cnt);

			return "entity updated successfully";

		} else

			return "Entity does not exist";

	}

	// To edit Country,Get country by id
	public Country getCountryByID(long id) {

		Country country = entityManager.find(Country.class, id);
		return country;

	}

	// update country
	@Transactional
	public void updateCountry(CountryForm form) {
		Country cntr = entityManager.find(Country.class, form.getId());

		cntr.setName(form.getName());
	}

}
