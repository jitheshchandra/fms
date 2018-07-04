package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.service.admin.intf.CountryService;
import com.ssrv.fms.vo.country.CountryForm;

@Controller
public class CountryController {
	@Autowired
	CountryService countryServiceImpl;

	// Country Summary
	@RequestMapping(value = "/CountrySummary", method = RequestMethod.GET)
	public String countrySummary(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		try {
			if (session.getAttribute("userId") == null
					|| session.getAttribute("userId").equals("")) {
				return "login";
			}
			List<Country> countries = countryServiceImpl.getAllCountries();
			model.addAttribute("CountrySummaryDetail", countries);
			return "CountrySummary";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// To Save Country To DataBase
	@RequestMapping(value = "/addCountry", method = RequestMethod.POST)
	public String saveCountry(CountryForm form, ModelMap model) {
		try {
			countryServiceImpl.SaveCountry(form);
			return "redirect:/CountrySummary";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// To delete country
	@RequestMapping(value = "/deleteCountry", method = RequestMethod.GET)
	public String deleteCountry(@RequestParam("id") String countreId) {
		try {
			int countryId = Integer.parseInt(countreId);
			countryServiceImpl.deleteCountry(countryId);
			return "redirect:CountrySummary";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// Edit country
	@RequestMapping(value = "/editCounrty", method = RequestMethod.GET)
	public String editCountry(@RequestParam("id") long countryId, ModelMap model) {
		try {
			Country countryName = countryServiceImpl.getCountryByID(countryId);
			model.addAttribute("country", countryName);
			return "EditCountry";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}

	// Update Country Details .
	@RequestMapping(value = "/updateCountry", method = RequestMethod.POST)
	public String updateCountry(CountryForm form, ModelMap model) {
		try {
			countryServiceImpl.updateCountry(form);
			return "redirect:/CountrySummary";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// Ajax test
	@RequestMapping("/ajaxTest")
	public ModelAndView ajaxTest() {
		return new ModelAndView("ajax", "message",
				"Crunchify Spring MVC with Ajax and JQuery Demo..");
	}

	// Get all country
	@RequestMapping(value = "/getCountry", method = { RequestMethod.POST,
			RequestMethod.GET })                   
	public @ResponseBody List<CountryForm> getCountry() {
		List<CountryForm> countries = new ArrayList<CountryForm>();
		List<Country> country = countryServiceImpl.getAllCountries();
		for (Country list : country) {
			countries.add(new CountryForm(list));
		}
		return countries;
	}
}
