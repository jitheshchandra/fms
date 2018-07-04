package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.service.admin.intf.CountryService;
import com.ssrv.fms.service.admin.intf.StateService;
import com.ssrv.fms.vo.state.StateForm;

@Controller
public class StateController
	{

		@Autowired
		private StateService stateServiceImpl;
		HttpSession session = null;

		@Autowired
		private CountryService countryServiceImpl;

		///Loading Country List
		@RequestMapping(value = "/StateList", method = RequestMethod.GET)
		public String stateList(ModelMap model, HttpServletRequest request, HttpServletResponse response)

			{
				HttpSession session = request.getSession(true);
				try
					{

						if (session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
							{

								return "login";
							}

						List<Country> country = countryServiceImpl.getAllCountries();
						model.addAttribute("CountrySummaryDetail", country);
                		return "StateList";

					}
				catch (Exception ex)
					{
						return "ErrorMsg";
					}
			}

		
		///Getting State List
		@RequestMapping(value = "/test", method = RequestMethod.GET)
		public String stateSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response)

			{
				HttpSession session = request.getSession(true);
				try
					{

						if (session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))
							{

								return "login";
							}
                		List<Country> country = countryServiceImpl.getAllCountries();
						model.addAttribute("CountrySummaryDetail", country);
						String countryId = request.getParameter("cntr");
						int countryIds = Integer.parseInt(countryId);
						List<States> state = stateServiceImpl.getAllStateByCountryId(countryIds);
						model.addAttribute("stateId", state);
						return "StateList";

					}
				catch (Exception ex)
					{
						System.out.println(ex.getMessage());
						return "ErrorMsg";
					}
			}

		
		/// To Save Data To Database
		@RequestMapping(value = "/saveState", method = RequestMethod.POST)
		public String saveState(StateForm form, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			{

				try
					{
						stateServiceImpl.SaveState(form);
						long setCountryId = form.getCntrId();
						System.out.println("State saved");
						request.setAttribute("cntr", setCountryId);
						String countrieId = request.getParameter("cntrId");
						int countryId = Integer.parseInt(countrieId);
						return "redirect:/test?cntr=" + countryId;
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		
		/// to delete state
		@RequestMapping(value = "/delState", method = RequestMethod.GET)
		public String deleteState(@RequestParam("id") String id, @RequestParam("countryId") String cntrId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			{
				try
					{
						int stateId = Integer.parseInt(id);
						stateServiceImpl.deleteState(stateId);
						request.setAttribute("cntr", cntrId);
		             	return "redirect:/test?cntr=" + cntrId;
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}
			}

		
		/// Edit State
		@RequestMapping(value = "/editState", method = RequestMethod.GET)
		public String editState(@RequestParam("id") long stateId, Model model)
			{
				try
					{
						States state = stateServiceImpl.getStateById(stateId);
						model.addAttribute("state", state);
						return "EditState";
					}
				catch (Exception Ex)
					{
						System.out.println(Ex.getMessage());
						return "ErrorMsg";
					}
			}

		
		/// Update State
		@RequestMapping(value = "/updateState", method = RequestMethod.POST)
		public String updateState( @RequestParam("id") long id,StateForm form, Model model, HttpServletRequest request, HttpServletResponse response)
			{
				try
					{
						stateServiceImpl.updateState(form);
						String countryIdString = request.getParameter("cId");					
				     	long countryId=Integer.parseInt(countryIdString);
						model.addAttribute("cntr", countryId);
						return "redirect:/test?cntr=" + countryId;
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}

			}
		

		///To Add State
		@RequestMapping(value = "/AddState", method = RequestMethod.GET)
		public String addState(StateForm form, Model model, @RequestParam("id1") long countryId)
			{
				Country country = countryServiceImpl.getCountryByID(countryId);
				List<Country> countrie = countryServiceImpl.getAllCountries();
				model.addAttribute("cnt", countrie);
				model.addAttribute("countee", country);
				return "AddState";
			}
		
		@RequestMapping(value="/getStates",method={RequestMethod.GET,RequestMethod.POST})
		public @ResponseBody List<StateForm> getStates(@RequestParam String cntrId)
		{
			List<States> state = stateServiceImpl.getAllStateByCountryId(Long.parseLong(cntrId));
			List<StateForm> stateList=new ArrayList<StateForm>();
			for(States list:state)
			{
				if(list!=null)
				{
					stateList.add(new StateForm(list));
				}
			}
			return stateList;
		}

	}