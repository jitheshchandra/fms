package com.ssrv.fms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.assets.Asset;
import com.ssrv.fms.model.assets.AssetCategory;
import com.ssrv.fms.model.assets.AssetCondition;
import com.ssrv.fms.service.asset.intf.AssetIntf;
import com.ssrv.fms.vo.Asset.AssetAdministrationForm;
import com.ssrv.fms.vo.Asset.AssetAttributeTypeView;
import com.ssrv.fms.vo.Asset.AssetCategoryAttributesView;
import com.ssrv.fms.vo.Asset.AssetCategoryForm;
import com.ssrv.fms.vo.Asset.AssetCatrgoryAttributeForm;
import com.ssrv.fms.vo.Asset.AssetConditionForm;
import com.ssrv.fms.vo.Asset.AssetForm;
import com.ssrv.fms.vo.Asset.AssetInstallationForm;
import com.ssrv.fms.vo.Asset.AssetMaintainanceForm;
import com.ssrv.fms.vo.Asset.AssetNotesForm;
import com.ssrv.fms.vo.Asset.AssetPositionForm;
import com.ssrv.fms.vo.Asset.AssetRelationForm;
import com.ssrv.fms.vo.Asset.AssetRelationTypeForm;
import com.ssrv.fms.vo.Asset.AssetTechnicalDetailsForm;
import com.ssrv.fms.vo.Asset.AssetUseForm;
import com.ssrv.fms.vo.Asset.AssetWarrantyForm;

@Controller
public class AssetManagementController {

	public boolean checkForSession(HttpServletRequest req,
			HttpServletResponse resp) {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("userId") != null
				&& session.getAttribute("userId") != "") {
			return true;
		}
		return false;
	}

	/* Asset summary */

	@Autowired
	private AssetIntf assetImpl;

	// Asset summary page
	@RequestMapping(value = "/AssetManagementSummary", method = RequestMethod.GET)
	public String assetManagementSummary(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementSummary";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Get asset use
	@RequestMapping(value = "/getAssetSummary", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetUseForm> getAssetSummary() {

		return assetImpl.getAssetUse();
	}

	// Get asset
	@RequestMapping(value = "/getAllAsset", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetForm> getAsset() {
		return assetImpl.getAssets();
	}

	// Save asset
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/saveAsset")
	public @ResponseBody
	List<AssetUseForm> saveAsset(@RequestBody AssetForm form) {
		assetImpl.saveAsset(form);
		return assetImpl.getAssetUse();
	}

	// Delete asset
	@RequestMapping(value = "/deleteAseet", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetUseForm> deleteAsset(@RequestParam String assetId) {
		assetImpl.deleteAsset(assetId);
		return assetImpl.getAssetUse();
	}

	// Edit asset
	@RequestMapping(value = "/editAsset", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	AssetForm editAsset(@RequestParam String assetUseId) {
		AssetForm forr = assetImpl.editAsset(assetUseId);
		return forr;
	}

	// Asset position
	@RequestMapping(value = "/AssetManagementPosition", method = RequestMethod.GET)
	public String assetPossition(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementPosition";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetPosition", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetPositionForm> getAssetPossition() {
		return assetImpl.getAssetPossiton();
	}

	@RequestMapping(value = "/updateAssetPosition", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetPositionForm> updateAssetPossition(
			@RequestBody List<AssetPositionForm> formList) {
		assetImpl.updateAssetPossion(formList);
		return assetImpl.getAssetPossiton();
	}

	// Asset warranty
	@RequestMapping(value = "/AssetManagementWarranty", method = RequestMethod.GET)
	public String assetWarranty(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementWarranty";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetWarranty", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetWarrantyForm> getAssetWarranty() {
		return assetImpl.getAssetWarranty();
	}

	@RequestMapping(value = "/updateAssetTechnicalWarranty", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetWarrantyForm> updateAssetWarranty(
			@RequestBody List<AssetWarrantyForm> lists) {
		assetImpl.updateAssetWarranty(lists);
		return assetImpl.getAssetWarranty();
	}

	// Asset relation
	@RequestMapping(value = "/AssetManagementRelation", method = RequestMethod.GET)
	public String assetRelation(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementRelation";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Relation
	@RequestMapping(value = "/RelationForm", method = RequestMethod.GET)
	public String relation(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "RelationForm";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Add Relation
	@RequestMapping(value = "/AddRelation", method = RequestMethod.GET)
	public String relationa(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AddRelation";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Get All relation***
	@RequestMapping(value = "/getAssetRelation", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetRelationTypeForm> getAssetRelation() {
		return assetImpl.getAssetRealtionshipType();
	}

	// /Save RelationType*****
	@RequestMapping(value = "/saveRelation", method = RequestMethod.POST)
	public String saveRelationtype(AssetRelationTypeForm form, ModelMap model) {
		assetImpl.SaveAssetRelationType(form);
		return "redirect:/RelationForm";
	}

	// /Save Relation*****
	@RequestMapping(value = "/saveRelations", method = RequestMethod.POST)
	public String saveRelationt(AssetRelationForm form, ModelMap model) {
		assetImpl.SaveAssetRelation(form);
		return "redirect:/RelationForm";
	}

	// Asset technical detail
	@RequestMapping(value = "/AssetManagementTechnicalDetails", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String assetTechnicalDetail(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementTechnicalDetails";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetTechnicalDetail", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetTechnicalDetailsForm> getAssetTechnicalDetail() {
		return assetImpl.getAssetTechnicalDetailsForm();
	}

	@RequestMapping(value = "/updateAssetTechnicalDetail", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetTechnicalDetailsForm> updateAssetTechnicalDetail(
			@RequestBody List<AssetTechnicalDetailsForm> lists) {
		assetImpl.updateTechnicalDetails(lists);
		return assetImpl.getAssetTechnicalDetailsForm();
	}

	// Asset installation
	@RequestMapping(value = "/AssetManagementInstallation", method = RequestMethod.GET)
	public String assetInstallation(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementInstallation";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetInstallation", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetInstallationForm> getAssetInstallation() {
		return assetImpl.getAssetInstallation();
	}

	@RequestMapping(value = "/updateAssetInstallation", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetInstallationForm> updateAssetInstallation(
			@RequestBody List<AssetInstallationForm> formList) {
		assetImpl.updateAssetInstalation(formList);
		return assetImpl.getAssetInstallation();
	}

	// Asset administration
	@RequestMapping(value = "/AssetManagementAdministration", method = RequestMethod.GET)
	public String assetAdministration(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementAdministration";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetAdministration", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetAdministrationForm> getAssetAdministration() {
		return assetImpl.getAssetAdministration();
	}

	@RequestMapping(value = "/updateAssetAdministration", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetAdministrationForm> updateAssetAdministration(
			@RequestBody AssetAdministrationForm form) {
		List<AssetAdministrationForm> listOfAdmist = new ArrayList<AssetAdministrationForm>();
		listOfAdmist.add(form);
		assetImpl.updateAssetAdministartion(listOfAdmist);
		return assetImpl.getAssetAdministration();
	}

	// Asset maintainance
	@RequestMapping(value = "/AssetManagementMaintainance", method = RequestMethod.GET)
	public String assetMaintainence(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementMaintainance";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// / edit ManagementSummary*****need to check category and condition id
	@RequestMapping(value = "/editAssetManagementSummary", method = RequestMethod.GET)
	public String editAssetManagementSummary(@RequestParam("id") String id,
			ModelMap model) {

		Asset asset = assetImpl.getManagementSummarById(id);
		model.addAttribute("managementSummary", asset);
		return "EditAssetManagementSummary";
	}

	// update ManagedEntitySubType ***need to work
	@RequestMapping(value = "/updateAssetManagementSummary", method = RequestMethod.POST)
	public String updateAssetManagementSummary(AssetForm form) {
		assetImpl.updateAssetManagementSummary(form);
		return "redirect:/AssetManagementSummary";
	}

	// Asset category*****
	@RequestMapping(value = "/AssetManagementCategory", method = RequestMethod.GET)
	public String assetcategory(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			
			List<AssetCategoryForm> assetCategories =  assetImpl.getAssetCategory();
			model.addAttribute("assetManagementCategory",assetCategories);
			return "AssetManagementCategory";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// Asset condition*****
	@RequestMapping(value = "/AssetManagementCondition", method = RequestMethod.GET)
	public String assetcondition(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementCondition";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	// /Save AssetManagementCondition*****
	@RequestMapping(value = "/saveAssetManagementCondition", method = RequestMethod.POST)
	public String saveUsertype(AssetConditionForm form, ModelMap model) {
		assetImpl.SaveAssetManagementCondition(form);
		return "redirect:/AssetManagementCondition";
	}

	// / edit AssetManagementCondition*****
	@RequestMapping(value = "/editAssetManagementCondition", method = RequestMethod.GET)
	public String editAssetManagementCondition(@RequestParam("id") long id,
			ModelMap model) {
		try {
			AssetCondition assetCondition = assetImpl
					.getAssetManagementConditionId(id);
			model.addAttribute("assetManagementCondition", assetCondition);
			return "EditDesignation";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}

	// / edit AssetManagementCondition*****
	@RequestMapping(value = "/editAssetManagementCategory", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String editAssetManagementCategory(@RequestParam("id") String id,
			ModelMap model) {
		try {
			Long m_id = Long.parseLong("id");
			AssetCategory assetCategory = assetImpl
					.getAssetManagementCategoryId(m_id);
			model.addAttribute("assetManagementCategory", assetCategory);
			return "EditDesignation";
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return "ErrorMsg";
		}

	}

	// Save AssetManagementCategory
	@RequestMapping(value = "/saveAssetManagementCategory", method = RequestMethod.POST)
	public String saveAssetManagementCategory(AssetCategoryForm form,
			ModelMap model) {
		assetImpl.SaveAssetManagementCategory(form);
		return "redirect:/AssetManagementCategory";
	}

	@RequestMapping(value = "/getAssetMaintainance", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AssetMaintainanceForm> getAssetMaintainance() {
		return assetImpl.getAssetMaintainance();
	}

	// Asset notes
	@RequestMapping(value = "/AssetManagementNotes", method = RequestMethod.GET)
	public String assetNote(Model model, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			boolean session = checkForSession(req, resp);
			if (session != true) {
				return "login";
			}
			return "AssetManagementNotes";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}
	}

	@RequestMapping(value = "/getAssetNotes", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetNotesForm> getAssetNotes() {
		return assetImpl.getAssetNotes();
	}

	@RequestMapping(value = "/updateAssetNotes", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetNotesForm> updateAssetNotes(
			@RequestBody List<AssetNotesForm> lists) {
		assetImpl.updateAssetNotes(lists);
		return assetImpl.getAssetNotes();
	}

	/* Asset condition */
	@RequestMapping(value = "/getAssetCondition", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetConditionForm> getAssetConditions() {
		return assetImpl.getAssetCondition();
	}

	/* Asset category */
	@RequestMapping(value = "/getAssetCategory", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AssetCategoryForm> getAssetCategory() {
		return assetImpl.getAssetCategory();
	}
	
	

	
	// / Asset Attribute *****
	//do we really need multiple types 
		@RequestMapping(value = "/AssetAttributeType", method = RequestMethod.GET)
		public String addAssetAttributeType(ModelMap model) {
			try {
				
				return "AssetAttributeType";
			}

			catch (Exception e) {
				System.out.println(e.getMessage());
				return "ErrorMsg";
			}

		}

		// / edit AssetManagementCondition*****
				@RequestMapping(value = "/AssetManagementCategoryAttribute", method = RequestMethod.GET)
				public String addAssetCategoryAttribute(ModelMap model) {
					try {
						
						return "AssetManagementCategoryAttribute";
					}

					catch (Exception e) {
						System.out.println(e.getMessage());
						return "ErrorMsg";
					}

				}
				
				//
				@RequestMapping(value = "/getAssetCategoryAttributes", method = { RequestMethod.GET,
						RequestMethod.POST })
				public @ResponseBody List<AssetCategoryAttributesView> getAssetCategoryAttributes(@RequestParam("catId") String cid) {
					
					
					long catId = Long.parseLong(cid);
					
					return assetImpl.getAssetCategoryAttributes(catId);
				}
		
				
				@RequestMapping(value="/SaveAssetCategoryAttribute", method = RequestMethod.POST)
				  public  void post(@RequestParam("dataDetail") String cid , HttpServletRequest req,HttpServletResponse resp) {    
				 try{
					 String jsonData =(String) JSON.parse(cid.trim());
					 System.out.println("JSON Data : "+jsonData );
					 
				      ObjectMapper mapper = new ObjectMapper();
				      AssetCatrgoryAttributeForm assetCategoryAttributes = mapper.readValue(jsonData, AssetCatrgoryAttributeForm.class);
				      
				      assetImpl.saveAssetCategoryAttributes(assetCategoryAttributes);
				 }catch(Exception ex){
					 
					 System.out.println(ex);
				 } 
				  }
	
				//getAssetAttributeTypes
				@RequestMapping(value = "/getAssetAttributeTypes", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AssetAttributeTypeView> getAssetCategoryAttributeTypes() {
					
									
					return assetImpl.getAssetAttributeTypes();
				}
				
				
				@RequestMapping(value = "/deleteAssetCategoryAttribute", method = {RequestMethod.GET,RequestMethod.POST})
				public @ResponseBody List<AssetCategoryAttributesView> deleteAssetCategoryAttribute(@RequestParam("id") String id ,@RequestParam("cid") String cid, HttpServletRequest req,HttpServletResponse resp) {
								
											
					
					
								 if(assetImpl.deleteAssetcategoryAttribute(Long.parseLong(id)))
								 {
									 
									 
									 long catId = Long.parseLong(cid);
										
										return assetImpl.getAssetCategoryAttributes(catId);
								 }
								 else
								 {
									 return null;
								 }
							}
				
				
}
