package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.constants.ManagedEntityResource;
import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.Unitsofmeasurement;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.CheckListDetails;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.checklist.Checklisttype;
import com.ssrv.fms.model.managedentity.ManagedEntitySubType;
import com.ssrv.fms.model.managedentity.ManagedEntityType;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityGroupService;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityMasterService;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.branch.BranchForm;
import com.ssrv.fms.vo.checklist.CheckListDetailInfoView;
import com.ssrv.fms.vo.checklist.CheckListDetailsForm;
import com.ssrv.fms.vo.checklist.CheckListForm;
import com.ssrv.fms.vo.checklist.CheckListInfoView;
import com.ssrv.fms.vo.checklist.CheckListSummaryForm;
import com.ssrv.fms.vo.checklist.CheckListTypeForm;
import com.ssrv.fms.vo.employee.EmployeeForm;
import com.ssrv.fms.vo.employee.EmployeeSummaryView;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummary;
import com.ssrv.fms.vo.managedentity.ManagedEntityGroupSummaryForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityInstanceView;
import com.ssrv.fms.vo.managedentity.ManagedEntityMasterVO;
import com.ssrv.fms.vo.managedentity.ManagedEntityMasterView;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntitySubTypeVO;
import com.ssrv.fms.vo.managedentity.ManagedEntityTypeForm;
import com.ssrv.fms.vo.managedentity.ManagedEntityVo;

@Controller
public class ManagedEntityController {
	@Autowired
	private IManagedEntityGroupService managedEntityGroupService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private IManagedEntityService managedEntityService;

	@Autowired
	private OrganizationService organizationServiceImpl;

	@Autowired
	private BranchService branchServiveImpl;

	@Autowired
	private CheckListIntf checkListIntf;

	@Autowired
	private IManagedEntityMasterService managedEntityMasterImpl;
	HttpSession session = null;

	@Autowired
	@Qualifier(value = "baseDao")
	private IBaseDAO baseDao;

	private String dd2;

	/*** Managed Entity ***/

	// Get Managed entity page
	@RequestMapping(value = "/ManagedEntityPage")
	public String getManagedEntityPage() {
		return "ManagedEntity";
	}

	/*
	 * @RequestMapping(value="/ManagedEntityPageRedirect") public String
	 * getManagedEntityPage(@RequestParam String orgId, @RequestParam String
	 * brchId) { return "ManagedEntity"; }
	 */

	// Get managed entity list
	@RequestMapping(value = "/getManagedEntityList", method = RequestMethod.POST)
	public @ResponseBody
	List<ManagedEntityVo> getManagedEntityList() {
		return managedEntityService.getManagedEntityList();
	}

	/*** Managed Entity Master ***/

	// Get managed entity page
	@RequestMapping(value = "/ManagedEntityMaster")
	public String getManagedEntityMasterPage(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getSession() != null) {
			return "ManagedEntityMaster";
		}
		return "login";
	}

	// Get managed entity summary
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/getAllManagedEntityMaster")
	public @ResponseBody
	List<ManagedEntityMasterVO> getManagedEntityMasterSummary() {
		return managedEntityMasterImpl.getAllManagedEntityMaster();
	}

	// Save managed entity
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/saveManagedEntityMasters")
	public String saveManagedEntityMasters(@RequestBody ManagedEntityMasterView formList) {
		Boolean result = managedEntityMasterImpl.saveManagedEntityMaster(formList.getMasterList());
		if (result == true)
			return "redirect:/getAllManagedEntityMaster";
		return "Erorr while saving";
	}

	// Delete managed entity
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/deleteManagedEntityMaster")
	public @ResponseBody
	Boolean deleteManagedEntityMaster(@RequestParam Long managedEntityMasterId) {
		return managedEntityMasterImpl.deleteManagedEntityMaster(managedEntityMasterId);
	}

	/*** Managed Entity Group ***/

	// Managed Entity Group
	@RequestMapping(value = "/ManagedEntityGroupPage", method = RequestMethod.GET)
	public String managedEntityPage(ModelMap model, HttpServletRequest request, HttpServletResponse response, ManagedEntityGroupSummaryForm groupSummaryForm) {
		HttpSession session = request.getSession(true);
		try {
			if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")) {
				return "login";
			}
			List<ManagedEntityGroupSummary> v1 = managedEntityGroupService.getManagedEntityGroupByOrgAndBranch(groupSummaryForm);
			model.addAttribute("v2_detail", v1);
			return "ManagedEntityGroups";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// store new managed entity .
	@RequestMapping(value = "/saveManagedEntity", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveManagedEntity(ManagedEntityVo form, ModelMap model) {
		try {
			managedEntityService.saveManagedEntity(form);
			model.addAttribute("mgd_Saved", "Entity Saved");
			return "redirect:/ManagedEntityPage";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mgd_Saved", "Could not save entity");
			return "ErrorMsg";
		}

	}

	// store the new ManagedEntitySubType into the DataBase ******.
	@RequestMapping(value = "/saveManagedEntitySubType", method = RequestMethod.POST)
	public String saveManagedEntitySubType(ManagedEntitySubTypeForm form, ModelMap model) {
		try {
			managedEntityService.saveManagedEntitySubType(form);
			model.addAttribute("ManagedEntitySubType_Saved", "ManagedEntitySubType Saved");
			return "redirect:/ManagedEntitySubType";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}

	// store the new mgroup into the DataBase .
	@RequestMapping(value = "/saveManagedEntityGroup", method = RequestMethod.POST)
	public String saveManagedGroup(ManagedEntityGroupSummaryForm form, ModelMap model) {
		try {

			managedEntityGroupService.saveManagedGroup(form);
			model.addAttribute("managedEntityGroupSummaryForm_Saved", " managedEntityGroupSummaryForm Saved");
			return "redirect:/ManagedEntityGroupPage";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("managedEntityGroupSummaryForm_Saved", "Could not save managedEntityGroupSummaryForm ");
			return "ErrorMsg";
		}

	}

	@RequestMapping(value = "/addNewManagedEntityGroup", method = RequestMethod.GET)
	public String addNewManagedGroup(ManagedEntityGroupSummaryForm form, @RequestParam("selectdOrgn") String org, @RequestParam("selectedbran") String bran, ModelMap model) {

		long organization = Integer.parseInt(org);
		long branch = Integer.parseInt(bran);
		Organization org_name = organizationServiceImpl.getOrganizationById(organization);
		Branch brch_name = branchServiveImpl.getBranchById(branch);
		model.addAttribute("SelectedOrganization", org_name);
		model.addAttribute("SelectedBranch", brch_name);
		return "AddNewManagedEntityGroup";

	}

	// ******************************************************************
	// Delete ManagadGroup.
	@RequestMapping(value = "/delManagdGroup", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteMangd(@RequestParam("id") String id) {
		try {
			managedEntityGroupService.deleteManagadGroup(Integer.parseInt(id));
			return "redirect:";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// ******************************************************************
	// Delete ManagadEntity.
	@RequestMapping(value = "/delManagdEntity", method = { RequestMethod.POST, RequestMethod.GET })
	public String delmangdEntitysummary(@RequestParam("id") String id) {
		try {
			managedEntityService.delmangdEntity(Integer.parseInt(id));
			return "redirect:";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// Get data for managed entity group page
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "ManagedEntity")
	public @ResponseBody
	Map<String, Object> getManagedEnityGroupSummary(@RequestBody ManagedEntityGroupSummaryForm groupSummaryForm, Model model) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			ManagedEntityGroupSummary mgdEntGrpSmryView = (ManagedEntityGroupSummary) managedEntityGroupService.getManagedEntityGroupByOrgAndBranch(groupSummaryForm);
			// List<ManagedEntityGroupSummary> summaries =
			// mgdEntGrpSmryView.getManagedEntityGroupSummaries();
			model.addAttribute(ManagedEntityResource.MANAGED_ENTITY_GROUPSUMMARY_VIEW_ATTR, mgdEntGrpSmryView);
			resultMap.put("managedEntitySummary", mgdEntGrpSmryView);
			return resultMap;
		} catch (Exception e) {
			// to handle exception mandatorily eg show an error page
			System.out.println(e);
		}
		// return "ManagedEntityGroups";
		return null;
	}

	// Managed Entity Instance Page
	@RequestMapping(value = "/ManagedEntityInstance")
	public String mangedEntityInstancePage() {
		return "ManagedEntityInstance";
	}

	@RequestMapping(value = "/ManagedEntityGroup", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<Organization> dd1 = organizationServiceImpl.getAllOrganizations();
		model.addAttribute("dd1options", dd1);
		return "ManagedEntityGroups";
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ManagedEntityResource.MANAGED_ENTITY_GROUP_SUMMARY_MOBILE)
	public String mGetManagedEnityGroupSummary(ManagedEntityGroupSummaryForm groupSummaryForm) {
		return null;
	}

	@RequestMapping(value = ManagedEntityResource.SHOW_MANAGED_ENTITY_GROUP_CREATION, method = RequestMethod.POST)
	public String showManagedEntityGroupCreation(BranchForm branchForm, Model model) {
		try {
			Long branchId = branchForm.getId();
			if (branchId != null && branchId != 0) {
				List<ManagedEntityInstanceView> managedEntityInstanceViews = managedEntityService.getManagedEntityInstanceViewsByOrgBranch(branchForm.getId());
				model.addAttribute(ManagedEntityResource.MANAGED_ENTITY_INSTANCES_VIEW_ATTR, managedEntityInstanceViews);
				model.addAttribute("branchId", branchForm.getId());
			} else {
				throw new FmsException("branchId is mandatory");
			}
		} catch (FmsException e) {
			System.out.println(e);
		}

		return null;
	}

	/**** Check List Details ****/

	// Check List Details Summary
	@RequestMapping(value = "/CheckListsDetails", method = RequestMethod.GET)
	public String checkListDetails(ModelMap model, @RequestParam String checkListId) {
		Checklists checkList = checkListIntf.getCheckListById(Long.parseLong(checkListId));
		List<CheckListDetails> checkListDetail = checkListIntf.getCheckListDetailByCheckListId(Long.parseLong(checkListId));
		List<Unitsofmeasurement> uom = baseDao.getAll(Unitsofmeasurement.class);
		model.addAttribute("uom", uom);
		model.addAttribute("checkListDetails", checkListDetail);
		model.addAttribute("checkList", checkList);
		return "CheckListsDetails";
	}

	// Adding check list detail
	@RequestMapping(value = "/addCheckListDetail", method = RequestMethod.POST)
	public String saveCheckListDetail(CheckListDetailInfoView form) {
		checkListIntf.saveCheckListDetail(form);
		return "redirect:/CheckListsDetails?checkListId=" + form.getCheckListId();

	}

	// Deleting CheckList Detail
	@RequestMapping(value = "/deleteCheckListDetail", method = RequestMethod.GET)
	public String deleteCheckListDetail(@RequestParam String id, @RequestParam String checkListId) {
		checkListIntf.deleteCheckListDetail(Long.parseLong(id));
		return "redirect:/CheckListsDetails?checkListId=" + checkListId;
	}

	/*** Chick List ***/

	// CheckList Page
	@RequestMapping("/CheckLists")
	public String checkListPage() {
		return "CheckLists";
	}

	// Get checkList Detail
	@RequestMapping(value = "/getCheckList", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Map<String, Object> getCheckList(@RequestParam String orgId, @RequestParam String brchId) {
		Map<String, Object> resultSet = new HashMap<String, Object>();

		List<Checklists> cleckListModel = checkListIntf.getCheckList(Long.parseLong(orgId), Long.parseLong(brchId));
		List<CheckListForm> formList = new ArrayList<CheckListForm>();

		resultSet.put("checkList", formList);
		for (Checklists checkList : cleckListModel) {
			CheckListForm form = new CheckListForm();
			form.setId(checkList.getId());
			form.setCheckListName(checkList.getName());
			// form.setCheckListTypeId(checkList.getCheckListTypeId().getId());
			// form.setDefaultCheck(checkList.getDefaultCheck());
			formList.add(form);
		}

		List<Checklisttype> checkListTypeList = checkListIntf.getAllCheckListType();

		List<CheckListTypeForm> checkListType = new ArrayList<CheckListTypeForm>();
		resultSet.put("checkListType", checkListType);
		for (Checklisttype type : checkListTypeList) {
			CheckListTypeForm form = new CheckListTypeForm();
			form.setId(type.getId());
			form.setName(type.getName());
			checkListType.add(form);
		}
		return resultSet;
	}

	@RequestMapping(value = "/deleteCheckList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteCheckList(@RequestParam String checkListId) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		Boolean deleted = checkListIntf.deleteCheckList(Long.parseLong(checkListId));
		resultSet.put("yesDeleted", deleted);
		return resultSet;
	}

	/*
	 * @RequestMapping(value = "/saveManagedEntity", method =
	 * RequestMethod.POST) public String
	 * saveManagedEntityInstance(ManagedEntityVo managedEnity) { Map<String,
	 * Object> resultSet = new HashMap<String, Object>(); // Boolean deleted =
	 * checkListIntf.deleteCheckList(Long.parseLong(checkListId)); //
	 * resultSet.put("yesDeleted", deleted); return "ManagedEntityInstance"; }
	 */
	@RequestMapping(value = "/getManagedEntitiesByOrganizationAndBranch", method = RequestMethod.POST)
	public @ResponseBody
	List<ManagedEntityGroupSummary> getManagedEntitiesByOrganizationAnBranch(@RequestParam String orgId, @RequestParam String branchId, Model model) {

		ManagedEntityGroupSummaryForm form = new ManagedEntityGroupSummaryForm();
		form.setBranchId(Integer.parseInt(branchId));
		form.setOrganizationId(Integer.parseInt(orgId));
		List<ManagedEntityGroupSummary> mgdEntGrpSmryView = (List<ManagedEntityGroupSummary>) managedEntityGroupService.getManagedEntityGroupByOrgAndBranch(form);

		// Boolean deleted =
		// checkListIntf.deleteCheckList(Long.parseLong(checkListId));
		// resultSet.put("yesDeleted", deleted);
		return mgdEntGrpSmryView;
	}

	// AddNewManaged entity instance Redirect to View
	// *********************************************.
	@RequestMapping(value = "/addNewManaged", method = { RequestMethod.GET, RequestMethod.POST })
	public String addNewManaged(@RequestParam("selectdOrgn") String org, @RequestParam("selectedbran") String bran, Model model) {
		try {
			long organization = Integer.parseInt(org);
			long branch = Integer.parseInt(bran);
			Organization org_name = organizationServiceImpl.getOrganizationById(organization);
			Branch brch_name = branchServiveImpl.getBranchById(branch);
			model.addAttribute("SelectedOrganization", org_name);
			model.addAttribute("SelectedBranch", brch_name);
			return "ManagedEntityInstance";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getManagedEntitiyInstancesByOrganizationAndBranch", method = RequestMethod.POST)
	public @ResponseBody
	List<ManagedEntityInstanceView> getManagedEntityInstancesByOrganizationAnBranch(@RequestParam String orgId, @RequestParam String branchId, Model model) {

		List<ManagedEntityInstanceView> mgdEntGrpSmryView;
		try {
			mgdEntGrpSmryView = (List<ManagedEntityInstanceView>) managedEntityService.getManagedEntityInstancesByOrgBranch(Long.parseLong(branchId));
			return mgdEntGrpSmryView;
		} catch (FmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	
	}



	// Redirect to managed Entity type page
	@RequestMapping(value = "/ManagedEntityType")
	public String getManagedEntityTypePage(Model managedEntityTypeModel) {
		List<ManagedEntityType> entityType = managedEntityService.getAllManagedEntityTypes();
		managedEntityTypeModel.addAttribute("entityType", entityType);
		return "ManagedEntityType";
	}

	// Redirect to managed entity subtype page .
	@RequestMapping(value = "/ManagedEntitySubType")
	public String getManagedEntitySubTypePage() {
		return "ManagedEntitySubTypeSummary";
	}

	// Redirect to add managed entity subtype page .
	@RequestMapping(value = "/addManagedEntitySubType")
	public String addManagedEntitySubTypePage() {
		return "AddManagedEntitySubType";
	}

	@RequestMapping(value = "/addManagedEntityType", method = { RequestMethod.GET, RequestMethod.POST })
	public String addManagedEntityType(Model managedEntityTypeModel, ManagedEntityTypeForm form) {
		ManagedEntityType mEntityType = new ManagedEntityType();

		mEntityType.setName(form.getName());
		managedEntityService.saveManagedEntityType(mEntityType);
		return "redirect:/ManagedEntityType";
	}

	// editEntityType
	@RequestMapping(value = "/editEntityType", method = { RequestMethod.GET, RequestMethod.POST })
	public String editManagedEntityType(@RequestParam("id") long id, Model model) {
		ManagedEntityType mEntityType = new ManagedEntityType();

		ManagedEntityType entityType = managedEntityService.getAllManagedEntityType(id);
		model.addAttribute("entityType", entityType);
		return "EditManagedEntityType";

	}

	@RequestMapping(value = "/deleteManagedEntityType", method = { RequestMethod.GET, RequestMethod.POST })
	public String delManagedEntityType(@RequestParam("id") long id) {

		managedEntityService.deleteManagedEntityType(id);
		return "redirect:/ManagedEntityType";
	}

	// Get all organization summary
	@RequestMapping(value = "/getManagedEntityTypes", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<ManagedEntityType> getOrganization() {
		List<ManagedEntityType> entities = managedEntityService.getAllManagedEntityTypes();
		return entities;
	}

	// Get all organization summary
	@RequestMapping(value = "/managedEntitySubtypeDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	ManagedEntitySubType getManagedEntitySubTypeDetails(@RequestParam("id") long id) {
		ManagedEntitySubType entities = managedEntityService.getAllManagedEntitySubTypesbyId(id);
		return entities;
	}

	// Get all organization summary
	@RequestMapping(value = "/managedEntitySubtypeByType", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<ManagedEntitySubTypeVO> getManagedEntitySubTypeByType(@RequestParam("id") long id) {
		List<ManagedEntitySubTypeVO> entities = managedEntityService.getAllManagedEntitySubTypesbyTypeId(id);
		return entities;
	}

	// delete managed Entity SubType
	@RequestMapping(value = "/delmanagedEntitySubtypeByType", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	void deleteManagedEntitySubTypeByType(@RequestParam("id") long id) {
		managedEntityService.deleteManagedEntitySubTypesbySubTypeId(id);

	}

	// To save
	@RequestMapping(value = "/saveDefaultChecklists", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveDefaultChecklists(ModelMap model, CheckListDetailsForm form) {

		return "CheckListsDetails";
	}

	// To save checklistsummarry********************need to check
	@RequestMapping(value = "/savechecklistsummarry", method = { RequestMethod.GET, RequestMethod.POST })
	public String savechecklistsummarry(ModelMap model, CheckListSummaryForm form) {
		System.out.println("save checklistsummarry first step");
		managedEntityService.savechecklistsummarry(form);
		return "redirect:/CheckListSummary";
	}

	// Redirect to add managed entity subtype page .
	@RequestMapping(value = "/ChecklistSummary")
	public String ChecklistSummary() {
		return "ChecklistSummary";
	}

	// Redirect to add managed entity subtype page .
	@RequestMapping(value = "/addCheckList")
	public String addCheckList() {
		return "AddChecklist";
	}

	// / Edit ManagedEntityType**working
	@RequestMapping(value = "/editManagedEntityType", method = RequestMethod.GET)
	public String editManagedEntityType(@RequestParam("id") long id, ModelMap model) {
		try {
			ManagedEntityType managedEntityType = managedEntityService.getManagedEntityTypeById(id);
			model.addAttribute("ManagedEntityType", managedEntityType);
			return "EditManagedEntityType";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}
	}

	// Update ManagedEntityType ***
	@RequestMapping(value = "/updateManagedEntityType", method = RequestMethod.POST)
	public String updateManagedEntityType(ManagedEntityTypeForm form) {
		managedEntityService.updateManagedEntityType(form);
		return "redirect:/ManagedEntityType";
	}

	// Edit ManagedEntitySubType**working
	@RequestMapping(value = "/editManagedEntitySubType", method = { RequestMethod.GET, RequestMethod.POST })
	public String editManagedEntitySubType(@RequestParam("mid") Long mid, Model model) {
		ManagedEntitySubType managedEntitySubType = managedEntityService.getAllManagedEntitySubTypesbyId(mid);
		List<ManagedEntityType> managedEntityTypes = managedEntityService.getAllManagedEntityTypes();
		model.addAttribute("subtypes", managedEntitySubType);
		model.addAttribute("mtupe", managedEntityTypes);
		return "EditManagedEntitySubType";
	}

	// update ManagedEntitySubType ***working
	@RequestMapping(value = "/updateManagedEntitySubType", method = RequestMethod.POST)
	public String updateManagedEntitySubType(ManagedEntitySubTypeForm form) {
		managedEntityService.updateManagedEntitySubType(form);
		return "redirect:/ManagedEntitySubType";
	}
	
	
	//getting all the managed entity groups by orgid and branch id as you dont want other companys ids to be shown .
	@RequestMapping(value = "/getAllManagendEntityGroupsByOrgIdAndBranchId", method = RequestMethod.POST)
	public @ResponseBody List<ManagedEntityGroupSummary> getAllManagendEntityGroupsByOrgIdAndBranchId() {
		
		
		//managedEntityService.updateManagedEntitySubType(form);
		
		return null;
	//	return "redirect:/ManagedEntitySubType";
	}
	
}
