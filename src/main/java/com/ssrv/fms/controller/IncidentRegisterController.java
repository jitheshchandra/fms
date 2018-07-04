package com.ssrv.fms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.incident.IncidentStatus;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.incident.IIncidentService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;
import com.ssrv.fms.vo.incidents.IncidentInfo;
import com.ssrv.fms.vo.incidents.UpdateIncidentStatusForms;
import com.ssrv.fms.vo.incidents.impl.EscalationMatrixInfoForm;
import com.ssrv.fms.vo.incidents.impl.IncidentCategoryInfoForm;
import com.ssrv.fms.vo.incidents.impl.IncidentInfoFormImpl;

@Controller
public class IncidentRegisterController {

	@Autowired
	IIncidentService incidentService;

	@Autowired
	private BranchService branchServive;

	@Autowired
	private OrganizationService organizationIntf;

	@Autowired
	private BranchService branchService;

	@RequestMapping(value = "/EscalationLevel", method = RequestMethod.GET)
	public String escalationLevel(Model incidentRegisterModel) {
		List<EscalationLevelInfo> escalationLevels = incidentService
				.getEscalationLevelList();
		incidentRegisterModel
				.addAttribute("escalationLevels", escalationLevels);
		return "EscalationLevel";
	}

	@RequestMapping(value = "/saveEscalationLevel", method = RequestMethod.GET)
	public String saveEscalationLevel(String name) {
		incidentService.saveEscalationLevel(name);
		return "redirect:EscalationLevel";
	}

	@RequestMapping(value = "/deleteEscalationLevel", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteEscalationLevel(@RequestParam String escalationId) {
		Boolean result = incidentService.deleteEscalationLevel(Long
				.parseLong(escalationId));
		Map<String, Object> returnResult = new HashMap<String, Object>();
		returnResult.put("result", result);
		return returnResult;
	}

	@RequestMapping(value = "/deleteEscalationMatrix", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteEscalationMatrix(
			@RequestParam String escalationMatrixId) {
		Boolean result = incidentService.deleteEscalationMatrix(Long
				.parseLong(escalationMatrixId));
		Map<String, Object> returnResult = new HashMap<String, Object>();
		returnResult.put("result", result);
		return returnResult;
	}

	@RequestMapping(value = "/IncidentCategoryEntity", method = RequestMethod.GET)
	public String addIncidentCategory(Model incidentRegisterModel) {
		List<IncidentCategoryInfo> categoryList = incidentService
				.getIncidentCategoryList();
		incidentRegisterModel.addAttribute("categories", categoryList);
		return "IncidentCategoryEntity";
	}

	@RequestMapping(value = "/saveIncidentCategoryEntity", method = RequestMethod.GET)
	public String saveIncidentCategoryEntity(
			IncidentCategoryInfoForm incidentCategoryInfoForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return "IncidentCategoryEntity";
		}

		if (!StringUtils
				.isEmpty(incidentCategoryInfoForm.getParentCategoryId())) {
			incidentCategoryInfoForm.setParentCategory(incidentService
					.getIncidentCategoryById(Long
							.parseLong(incidentCategoryInfoForm
									.getParentCategoryId())));
		}
		/**
		 * throw exception if not successful.
		 */
		incidentService.saveIncidentCategory(incidentCategoryInfoForm);
		return "redirect:IncidentCategoryEntity";
	}

	@RequestMapping(value = "/EscalationMatrixEntity", method = RequestMethod.GET)
	public String EscalationMatrixEntity(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<IncidentCategoryInfo> categoryList = incidentService
				.getIncidentCategoryList();
		model.addAttribute("categories", categoryList);

		return "EscalationMatrixEntity";
	}

	@RequestMapping(value = "/getIncidentCategories", method = RequestMethod.POST)
	public @ResponseBody
	List<IncidentCategoryInfo> getIncidentCategories() {
		List<IncidentCategoryInfo> categoryList = incidentService
				.getIncidentCategoryList();
		return categoryList;
	}

	@RequestMapping(value = "/getIncidentCategoriesHavingEscalationMatrix", method = RequestMethod.POST)
	public @ResponseBody
	List<IncidentCategoryInfo> getIncidentCategoriesHavingEscalationMatrix(
			@RequestParam String branchId) {
		List<IncidentCategoryInfo> categoryList = incidentService
				.getIncidentCategoriesHavingEscalationMatrix(Long
						.parseLong(branchId));
		return categoryList;
	}

	@RequestMapping(value = "/getEscalationMatrix", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getEscalationMatrix(@RequestParam String branchId,
			@RequestParam String categoryTypeId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<EscalationMatrixInfo> escList = incidentService
				.getEscalationMatrix(Long.parseLong(branchId),
						Long.parseLong(categoryTypeId));
		returnMap.put("escList", escList);

		List<EscalationLevelInfo> escalationLevels = incidentService
				.getEscalationLevelList();
		returnMap.put("escalationLevels", escalationLevels);

		return returnMap;
	}

	@RequestMapping(value = "/saveEscalationMatrix", method = RequestMethod.POST)
	public String saveEscalationMatrix(
			EscalationMatrixInfoForm escalationMatrixInfoForm, ModelMap model) {
		incidentService.saveEscalationMatrix(escalationMatrixInfoForm);
		return "redirect:/EscalationMatrixEntity";
	}

	@RequestMapping(value = "/IncidentRegisterEntity", method = RequestMethod.GET)
	public String addIncidentRegister(Model incidentRegisterModel) {
		return "IncidentRegisterEntity";
	}

	@RequestMapping(value = "/saveIncident", method = RequestMethod.POST)
	public String saveIncident(IncidentInfoFormImpl incidentInfoForm,
			ModelMap model) {
		incidentService.saveIncident(incidentInfoForm);
		return "redirect:/IncidentRegisterEntity";
	}

	@RequestMapping(value = "/IncidentRegisterUpdateEntity", method = RequestMethod.GET)
	public String addIncidentRegisterUpdate(Model incidentRegisterModel) {
		return "IncidentRegisterUpdateEntity";
	}

	@RequestMapping(value = "/getOpenIncidents", method = RequestMethod.POST)
	public @ResponseBody
	List<IncidentInfo> getOpenIncidents() {
		List<IncidentInfo> incidentList = incidentService
				.getIncidentsByStatus(IncidentStatus.O);
		return incidentList;
	}

	@RequestMapping(value = "/updateIncidents", method = RequestMethod.POST)
	public String updateIncidentForm(
			UpdateIncidentStatusForms updateIncidentStatusForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return "IncidentRegisterUpdateEntity";
		}
		incidentService.updateIncidentStatus(updateIncidentStatusForm);
		return "redirect:/IncidentRegisterUpdateEntity";
	}
}
