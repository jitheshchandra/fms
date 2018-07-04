package com.ssrv.fms.service.asset.intf;

import java.util.List;

import com.ssrv.fms.vo.Asset.AssetCategoryAttributesView;
import com.ssrv.fms.model.assets.Asset;
import com.ssrv.fms.model.assets.AssetCategory;
import com.ssrv.fms.model.assets.AssetCondition;
import com.ssrv.fms.vo.Asset.AssetAdministrationForm;
import com.ssrv.fms.vo.Asset.AssetAttributeTypeView;
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


public interface AssetIntf {
	/* Asset summary */
	public List<AssetForm> getAssets();
	public boolean saveAsset(AssetForm form);
	public AssetForm editAsset(String assetId);
	public boolean updateAsset(AssetForm form);
	public boolean deleteAsset(String assetId);
	public List<AssetUseForm>  getAssetUse();	
	public Asset getManagementSummarById(String id);//*****
	public void updateAssetManagementSummary(AssetForm form);//*****
	
	/*Asset category*/
	public List<AssetCategoryForm> getAssetCategory();
	public void SaveAssetManagementCategory(AssetCategoryForm form);//*****
	public AssetCategory getAssetManagementCategoryId(Long id);//*******
	/*Asset condition*/
	public List<AssetConditionForm> getAssetCondition();
	public void SaveAssetManagementCondition(AssetConditionForm form);//*****
	public AssetCondition getAssetManagementConditionId(Long id);//*******
	
	/*Asset Relationship Type*/
	public List<AssetRelationTypeForm> getAssetRealtionshipType();
	public void SaveAssetRelationType(AssetRelationTypeForm form);
	
	/*Asset position*/
	public boolean updateAssetPossion(List<AssetPositionForm> result);
	public List<AssetPositionForm> getAssetPossiton();
	
	/*Asset Warranty*/
	public boolean updateAssetWarranty(List<AssetWarrantyForm> result);
	public List<AssetWarrantyForm> getAssetWarranty();
	
	/*Asset Technical Details*/
	public boolean updateTechnicalDetails(List<AssetTechnicalDetailsForm> result);
	public List<AssetTechnicalDetailsForm> getAssetTechnicalDetailsForm();
	
	/*Asset Notes*/
	public boolean updateAssetNotes(List<AssetNotesForm> result);
	public List<AssetNotesForm> getAssetNotes();
	
	/*Asset Maintainance*/	
	public boolean updateAssetMaintainance(List<AssetMaintainanceForm> result);
	public List<AssetMaintainanceForm> getAssetMaintainance();
	
	/*Asset Administration*/
	public boolean updateAssetAdministartion(List<AssetAdministrationForm> result);
	public List<AssetAdministrationForm> getAssetAdministration();
	
	/*Asset Installation*/
	public boolean updateAssetInstalation(List<AssetInstallationForm> result);
	public List<AssetInstallationForm> getAssetInstallation();
	
	/*Asset Relationship*/
	public boolean updateAssetRelationship(List<AssetRelationForm> result);
	public List<AssetRelationForm> getAssetRelations();
	public void SaveAssetRelation(AssetRelationForm form);
	
	/*Asset Attributes Dynamism*/
	public List<AssetCategoryAttributesView> getAssetCategoryAttributes(long cid);
	public List<AssetAttributeTypeView> getAssetAttributeTypes();
	public boolean saveAssetCategoryAttributes(AssetCatrgoryAttributeForm assetCategoryAttributes);
	public boolean deleteAssetcategoryAttribute(long parseLong);
	
	
	
	
}

