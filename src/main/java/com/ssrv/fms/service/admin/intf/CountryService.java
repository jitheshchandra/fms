package com.ssrv.fms.service.admin.intf;

import java.util.List;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.vo.country.CountryForm;

public interface CountryService 
{
	
	
	public List<Country> getAllCountries();

	public void SaveCountry(CountryForm form);

	public String deleteCountry(int id);

	public Country getCountryByID(long id);
	
	public void updateCountry(CountryForm cnt);

}
