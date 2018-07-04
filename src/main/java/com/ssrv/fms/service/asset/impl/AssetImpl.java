package com.ssrv.fms.service.asset.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.assets.Asset;
import com.ssrv.fms.model.assets.AssetAdministration;
import com.ssrv.fms.model.assets.AssetAttachment;
import com.ssrv.fms.model.assets.AssetAttributeType;
import com.ssrv.fms.model.assets.AssetCategory;
import com.ssrv.fms.model.assets.AssetCategoryAttribute;
import com.ssrv.fms.model.assets.AssetCondition;
import com.ssrv.fms.model.assets.AssetInstallation;
import com.ssrv.fms.model.assets.AssetMaintainance;
import com.ssrv.fms.model.assets.AssetNote;
import com.ssrv.fms.model.assets.AssetPosition;
import com.ssrv.fms.model.assets.AssetRelation;
import com.ssrv.fms.model.assets.AssetRelationType;
import com.ssrv.fms.model.assets.AssetTechnicalDetail;
import com.ssrv.fms.model.assets.AssetUse;
import com.ssrv.fms.model.assets.AssetWarranty;
import com.ssrv.fms.service.asset.intf.AssetIntf;
import com.ssrv.fms.vo.Asset.AssetAdministrationForm;
import com.ssrv.fms.vo.Asset.AssetAttributeTypeView;
import com.ssrv.fms.vo.Asset.AssetAttributes;
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

@Lazy
@Service
@Transactional
public class AssetImpl implements AssetIntf {

	@PersistenceContext
	private EntityManager entityManager;
	
	/* Asset summary */
	@Override
	public List<AssetForm> getAssets() {
		@SuppressWarnings("unchecked")
		List<Asset> assetList=entityManager.createQuery("SELECT s  FROM  Asset s").getResultList();
		
		List<AssetForm> assetFormList=new ArrayList<AssetForm>();
		
		for(Asset asset:assetList){
			AssetForm form=new AssetForm();
		//	form.setId(asset.getId());
			form.setName(asset.getName());
		//	form.setAssetCategory(asset.getAssetCategoryBean().getId());
			//form.setAssetCondition(asset.getAssetConditionBean().getId());
			form.setAvailable(asset.getIsAvailable());
			form.setCode(asset.getCode());
			form.setDescription(asset.getDescription());
			form.setManufacturer(asset.getManufacturer());
			form.setModel(asset.getModel());
			form.setSupplier(asset.getSupplier());
			form.setType(asset.getType());
			form.setUser(asset.getUser().toString());
			assetFormList.add(form);
		}
		return assetFormList;
	}
	
	@Override
	public List<AssetUseForm> getAssetUse() {
		@SuppressWarnings("unchecked")
		List<AssetUse> assetUser=entityManager.createQuery("SELECT s  FROM  AssetUse s WHERE s.deleted=0").getResultList();
		List<AssetUseForm> assetUserFormList=new ArrayList<>();
		for(AssetUse user:assetUser){
			AssetUseForm form=new AssetUseForm();
			//form.setId(user.getId());
			form.setDescription(user.getDescription());
			form.setName(user.getName());
			//form.setAssetCode(entityManager.find(Asset.class, user.getAsset1().getId()).getCode());
			//form.setParentCode(entityManager.find(Asset.class, user.getAsset2()).getCode());
			assetUserFormList.add(form);
		}
		return assetUserFormList;
	}


	@Override
	@Transactional
	public boolean saveAsset(AssetForm form) {
		// TODO Auto-generated method stub
		Asset asset;
		if(form.getId()!=null && !form.getId().isEmpty() && form.getId()!=""){
			asset=entityManager.find(Asset.class, form.getId());
		}else{
			asset=new Asset();
		}
		
		asset.setName(form.getName());
		//if(!form.getAssetCategory().isEmpty() && form.getAssetCategory()!=null)
		//	asset.setAssetCategoryBean(entityManager.find(AssetCategory.class, form.getAssetCategory()));
		//if(!form.getAssetCondition().isEmpty() && form.getAssetCondition()!=null)
		//	asset.setAssetConditionBean(entityManager.find(AssetCondition.class, form.getAssetCondition()));
		asset.setCode(form.getCode());
		asset.setDescription(form.getDescription());
		asset.setType(form.getType());
		asset.setIsAvailable(form.getIsAvailable());
		asset.setManufacturer(form.getManufacturer());
		asset.setSupplier(form.getSupplier());
		asset.setModel(form.getModel());
		if(form.getId()!=null && !form.getId().isEmpty() && form.getId()!="")
			entityManager.merge(asset);
		else
			entityManager.persist(asset);		
		if(saveAssetUse(asset,form.getAssetUseId()))
			return true;
		else 
			return false;
	}

	@Transactional
	private boolean saveAssetUse(Asset asset,String assetUseId){
		AssetUse use;
		if(assetUseId!=null && !assetUseId.isEmpty())
			use=entityManager.find(AssetUse.class, assetUseId);
		else
			use=new AssetUse();
		use.setName(asset.getName());
		use.setDescription(asset.getDescription());
		//if(asset.getId()!=null && !asset.getId().toString().isEmpty())
		////	use.setAsset1(entityManager.find(Asset.class, asset.getId()));		
		//if(use.getId()!=null && !use.getId().equals("")){
		//	entityManager.merge(use);			
	/*//	}else{
			entityManager.persist(use);
			saveAssetPosition(use);
			saveAssetWarranty(use);
			saveAssetTechnicalDetails(use);
			saveAssetNotes(use);
		    saveAssetMaintainance(use);
			saveAssetAttachments(use);
			saveAssetAdministration(use);
			saveAssetInstallation(use);
			saveAssetRelation(use);
		}*/
		return true;
	}
	
	@Transactional
	private void saveAssetRelation(AssetUse use) {
		// TODO Auto-generated method stub
		AssetRelation relation=new AssetRelation();
		relation.setAssetUse(use);
		entityManager.persist(relation);
	}

	@Transactional
	private void saveAssetInstallation(AssetUse use) {
		// TODO Auto-generated method stub
		AssetInstallation installation=new AssetInstallation();
		installation.setAssetUse(use);
		entityManager.persist(installation);		
	}

	@Transactional
	private void saveAssetAdministration(AssetUse use) {
		// TODO Auto-generated method stub
		AssetAdministration administration=new AssetAdministration();
		administration.setAssetUse(use);
		entityManager.persist(administration);		
	}

	@Transactional
	private void saveAssetAttachments(AssetUse use) {
		// TODO Auto-generated method stub
		AssetAttachment attachment=new AssetAttachment();
		attachment.setAssetUse(use);
		entityManager.persist(attachment);
	}

	@Transactional
	private void saveAssetMaintainance(AssetUse use) {
		// TODO Auto-generated method stub
		AssetMaintainance maintainance=new AssetMaintainance();
		maintainance.setAssetUse(use);
		entityManager.persist(maintainance);
	}

	@Transactional
	private void saveAssetNotes(AssetUse use) {
		// TODO Auto-generated method stub
		AssetNote note=new AssetNote();
		note.setAssetUse(use);
		entityManager.persist(note);
	}

	@Transactional
	private void saveAssetTechnicalDetails(AssetUse use) {
		// TODO Auto-generated method stub
		AssetTechnicalDetail technicalDetails=new AssetTechnicalDetail();
		technicalDetails.setAssetUse(use);
		entityManager.persist(technicalDetails);
	}

	@Transactional
	private void saveAssetWarranty(AssetUse use) {
		// TODO Auto-generated method stub
		AssetWarranty warranty=new AssetWarranty();
		warranty.setAssetUse(use);
		entityManager.persist(warranty);
	}

	@Transactional
	private void saveAssetPosition(AssetUse use) {
		// TODO Auto-generated method stub
		AssetPosition position=new AssetPosition();
		position.setAssetUse(use);
		entityManager.persist(position);
	}

	@Override
	public AssetForm editAsset(String assetUseId) {
		AssetUse assetUse=entityManager.find(AssetUse.class, assetUseId);
	//	Asset asset = entityManager.find(Asset.class, assetUse.getAsset1().getId());
			AssetForm form=new AssetForm();
			/*//form.setId(asset.getId());
			form.setName(asset.getName());
			//if(asset.getAssetCategoryBean().getId()!=null && !asset.getAssetCategoryBean().getId().isEmpty())
			//	form.setAssetCategory(asset.getAssetCategoryBean().getId());
			//if(asset.getAssetConditionBean().getId()!=null && !asset.getAssetConditionBean().getId().isEmpty())
			//	form.setAssetCondition(asset.getAssetConditionBean().getId());
			form.setAvailable(asset.getIsAvailable());
			form.setCode(asset.getCode());
			form.setDescription(asset.getDescription());
			form.setManufacturer(asset.getManufacturer());
			form.setModel(asset.getModel());
			form.setSupplier(asset.getSupplier());
			form.setType(asset.getType());
			if(asset.getUser()!=null && !asset.getUser().equals(""))
				form.setUser(asset.getUser().toString());*/
		return form;
	}

	@Override
	public boolean updateAsset(AssetForm form) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	@Transactional
	public boolean deleteAsset(String assetId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetUse> assetUse=entityManager.createQuery("SELECT a FROM AssetUse a WHERE a.id="+assetId).getResultList();
		AssetUse asset=assetUse.get(0);
	//	asset.setDeleted(Byte.parseByte("1"));
		entityManager.merge(asset);
		
	//	asset.getAsset1().setIsDeleted(Byte.parseByte("1"));
	//	entityManager.merge(asset.getAsset1());		
		return true;
	}

	/* Asset category */
	@Override
	public List<AssetCategoryForm> getAssetCategory() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetCategory> categoryList=entityManager.createQuery("SELECT a FROM AssetCategory a").getResultList();
		List<AssetCategoryForm> categoryFormList=new ArrayList<>();
		for(AssetCategory category:categoryList){
			AssetCategoryForm form=new AssetCategoryForm();
			form.setCategoryName(category.getCategoryName());
			form.setId(category.getId()+"");
			form.setDescription(category.getDescription());
			categoryFormList.add(form);
		}
		return categoryFormList;
	}

	/* Asset condition */
	@Override
	public List<AssetConditionForm> getAssetCondition() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetCondition> conditionList=entityManager.createQuery("SELECT a FROM AssetCondition a").getResultList();
		List<AssetConditionForm> conditionFormList=new ArrayList<AssetConditionForm>();
		for(AssetCondition condition:conditionList){
			AssetConditionForm form=new AssetConditionForm();			
			form.setId(condition.getId()+"");
			form.setConditionName(condition.getName());
			form.setDescription(condition.getDescription());
			conditionFormList.add(form);
		}
		return conditionFormList;
	}

	@Override
	@Transactional
	public boolean updateAssetPossion(List<AssetPositionForm> result) {
		// TODO Auto-generated method stub
		for(AssetPositionForm forms:result){
			AssetPosition model=entityManager.find(AssetPosition.class, forms.getId().toString());
			model.setBuilding(forms.getBuilding());
			model.setComplex(forms.getComplex());
			model.setFloor(forms.getFloor());
			model.setRoom(forms.getRoom());
			model.setUnit(forms.getUnit());
			model.setWareHouse(forms.getWarehouse());
			entityManager.merge(model);
		}
		return true;		
	}

	@Override
	@Transactional
	public boolean updateAssetWarranty(List<AssetWarrantyForm> result) {
		// TODO Auto-generated method stub
		for(AssetWarrantyForm forms:result){
			AssetWarranty model=entityManager.find(AssetWarranty.class, forms.getId());
			model.setWarrantyCode(forms.getWarrantyCode());
			model.setWarrantyEnd(forms.getWarrantyEnd());
			model.setWarrantyStart(forms.getWarrantyStart());
			model.setWarrantyType(forms.getWarrantyType());
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateTechnicalDetails(List<AssetTechnicalDetailsForm> result) {
		// TODO Auto-generated method stub
		for(AssetTechnicalDetailsForm form:result){
			AssetTechnicalDetail model=entityManager.find(AssetTechnicalDetail.class, form.getId());
			model.setColor(form.getColor());
			model.setDepth(form.getDepth());
			model.setHeight(form.getHeight());
			model.setWeight(form.getWeight());
			model.setWidth(form.getWidth());
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateAssetNotes(List<AssetNotesForm> result) {
		// TODO Auto-generated method stub
		for(AssetNotesForm form:result){
			AssetNote model=entityManager.find(AssetNote.class, form.getId());
			model.setNotes(form.getNotes());
			model.setUserId(form.getUserId());
			model.setAssetAttachment(entityManager.find(AssetAttachment.class,form.getAttachmentId()));
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateAssetMaintainance(List<AssetMaintainanceForm> result) {
		// TODO Auto-generated method stub
		for(AssetMaintainanceForm form : result){
			AssetMaintainance model=entityManager.find(AssetMaintainance.class, form.getId());
			model.setArea(form.getArea());
			model.setCategory(form.getCategory());
			model.setCondition(form.getCondtion());
			model.setCriticality(form.getCriticality());
			model.setGroup(form.getGroup());
			model.setLastCheckDate(form.getLastCheckDate());
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateAssetAdministartion(List<AssetAdministrationForm> result) {
		// TODO Auto-generated method stub
		for(AssetAdministrationForm form : result){
			AssetAdministration model=entityManager.find(AssetAdministration.class, form.getId());
			model.setAmortizationEnd(form.getAmortaizationEnd());
			model.setAmortizationStart(form.getAmortaizationStart());
			model.setAnnulAmortization(form.getAnnualAmortaization());
			model.setCurrentValue(form.getCurrentValue());
			model.setDisposalDate(form.getDisposalDate());
			model.setFullyDepricated(form.getFullyDepricated());
			model.setInvoiceNumber(form.getInvoiceNo());
			model.setPurchasePrice(form.getPurchasePrice());
			model.setPurchaseRequest(form.getPurchaceRequest());
			model.setYearOfLife(form.getYearOfLife());
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateAssetInstalation(List<AssetInstallationForm> result) {
		// TODO Auto-generated method stub
		for(AssetInstallationForm form : result){
			AssetInstallation model=entityManager.find(AssetInstallation.class, form.getId());
			model.setAcceptanceDate(form.getAcceptanceDate());
			model.setInstallationDate(form.getInsatllationDate());
			model.setInstaller(form.getInstaller());
			entityManager.merge(model);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateAssetRelationship(List<AssetRelationForm> result) {
		// TODO Auto-generated method stub
		for(AssetRelationForm form:result){
			AssetRelation model=entityManager.find(AssetRelation.class,form.getId());
			model.setAssetRelationType(entityManager.find(AssetRelationType.class,form.getRelationType()));
			model.setEntity(form.getEntity());
			model.setEntityType(form.getEntityType());
			entityManager.merge(model);			
		}
		return true;
	}

	@Override
	public List<AssetPositionForm> getAssetPossiton() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetPosition> models=entityManager.createQuery("SELECT a FROM AssetPosition a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetPositionForm> forms=new ArrayList<AssetPositionForm>();
		for(AssetPosition model:models){
			AssetPositionForm form=new AssetPositionForm();
			form.setId(model.getId());
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
			form.setAssetId(assetUse.getName());
		//	form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setBuilding(model.getBuilding());
			form.setComplex(model.getComplex());
			form.setFloor(model.getFloor());
			form.setRoom(model.getRoom());
			form.setUnit(model.getUnit());
			form.setWarehouse(model.getWareHouse());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetWarrantyForm> getAssetWarranty() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetWarranty> models=entityManager.createQuery("SELECT a FROM AssetWarranty a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetWarrantyForm> forms=new ArrayList<AssetWarrantyForm>();
		for(AssetWarranty model:models){
			AssetWarrantyForm form=new AssetWarrantyForm();
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
		//	form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setAssetId(assetUse.getName());
			form.setId(model.getId());
			form.setWarrantyCode(model.getWarrantyCode());
			form.setWarrantyEnd(model.getWarrantyEnd());
			form.setWarrantyStart(model.getWarrantyStart());
			form.setWarrantyType(model.getWarrantyType());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetTechnicalDetailsForm> getAssetTechnicalDetailsForm() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetTechnicalDetail> models=entityManager.createQuery("SELECT a FROM AssetTechnicalDetail a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetTechnicalDetailsForm> forms=new ArrayList<AssetTechnicalDetailsForm>();
		for(AssetTechnicalDetail model:models){
			AssetTechnicalDetailsForm form=new AssetTechnicalDetailsForm();
			form.setId(model.getId());
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
		//	form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setAssetId(assetUse.getName());
			form.setColor(model.getColor());
			form.setDepth(model.getDepth());
			form.setHeight(model.getDepth());
			form.setWeight(model.getWeight());
			form.setWidth(model.getWidth());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetNotesForm> getAssetNotes() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetNote> models=entityManager.createQuery("SELECT a FROM AssetNote a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetNotesForm> forms=new ArrayList<AssetNotesForm>();
		for(AssetNote model:models){
			AssetNotesForm form=new AssetNotesForm();
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
//			form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setAssetId(assetUse.getName());
			form.setId(form.getId());
			form.setNotes(model.getNotes());
			form.setUserId(model.getUserId());
			//form.setAttachmentId(model.getAssetAttachment().getAttachment());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetMaintainanceForm> getAssetMaintainance() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetMaintainance> models=entityManager.createQuery("SELECT a FROM AssetMaintainance a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetMaintainanceForm> forms=new ArrayList<AssetMaintainanceForm>();
		for(AssetMaintainance model:models){
			AssetMaintainanceForm form=new AssetMaintainanceForm();
			form.setId(model.getId());
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
			form.setAssetId(assetUse.getName());
			form.setArea(model.getArea());
			form.setCategory(model.getCategory());
			form.setCondtion(model.getCondition());
			form.setCriticality(model.getCriticality());
			form.setLastCheckDate(model.getLastCheckDate());
			form.setGroup(model.getGroup());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetAdministrationForm> getAssetAdministration() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")		
		List<AssetAdministration> models=entityManager.createQuery("SELECT a FROM AssetAdministration a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetAdministrationForm> forms=new ArrayList<AssetAdministrationForm>();
		for(AssetAdministration model:models){
			AssetAdministrationForm form=new AssetAdministrationForm();
			form.setId(model.getId());		
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
			form.setAssetId(assetUse.getName());
			//form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setAmortaizationStart(model.getAmortizationStart());
			form.setAmortaizationEnd(model.getAmortizationEnd());
			form.setAnnualAmortaization(model.getAnnulAmortization());
			form.setCurrentValue(model.getCurrentValue());
			form.setDisposalDate(model.getDisposalDate());
			form.setFullyDepricated(model.getFullyDepricated());
			form.setInvoiceNo(model.getInvoiceNumber());
			form.setPurchaceRequest(model.getPurchaseRequest());
			form.setPurchasePrice(model.getPurchasePrice());
			form.setYearOfLife(model.getYearOfLife());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetInstallationForm> getAssetInstallation() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetInstallation> models=entityManager.createQuery("SELECT a FROM AssetInstallation a WHERE EXISTS (SELECT 'id' FROM AssetUse p WHERE p.id = a.assetUse AND p.deleted = 0)").getResultList();
		List<AssetInstallationForm> forms=new ArrayList<AssetInstallationForm>();
		for(AssetInstallation model:models){
			AssetInstallationForm form=new AssetInstallationForm();
			form.setId(model.getId());
			form.setAcceptanceDate(model.getAcceptanceDate());
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
			form.setAssetId(assetUse.getName());
			//form.setAssetCode(entityManager.find(Asset.class, assetUse.getAsset1().getId()).getCode());
			form.setInsatllationDate(model.getInstallationDate());
			form.setInstaller(model.getInstaller());
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<AssetRelationForm> getAssetRelations() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<AssetRelation> models=entityManager.createQuery("SELECT a FROM AssetRelation ").getResultList();
		List<AssetRelationForm> forms=new ArrayList<AssetRelationForm>();
		for(AssetRelation model:models){
			AssetRelationForm form=new AssetRelationForm();
			form.setId(model.getId());
			AssetUse assetUse = entityManager.find(AssetUse.class,model.getAssetUse().getId());
		//	form.setAssetId(assetUse.getName());
			form.setEntity(model.getEntity());
			form.setEntityType(model.getEntityType());
		//	form.setRelationType(model.getAssetRelationType().getName());
			forms.add(form);
		}
		return forms;
	}

	
	
	//save Relation
	@Override
	@Transactional
	public void SaveAssetRelationType(AssetRelationTypeForm form) {
		AssetRelationType assetRelationType=new AssetRelationType();
		assetRelationType.setName(form.getName());
		assetRelationType.setDescription(form.getDescription());
		entityManager.persist(assetRelationType);
		
	}
	
	@Override
	public List<AssetRelationTypeForm> getAssetRealtionshipType() {
		
		List<AssetRelationType> models=entityManager.createQuery("SELECT a FROM AssetRelationType a").getResultList();
		List<AssetRelationTypeForm> forms=new ArrayList<AssetRelationTypeForm>();
		for(AssetRelationType model:models){
			AssetRelationTypeForm form=new AssetRelationTypeForm();
			form.setId(model.getId());
			form.setName(model.getName());
			form.setDescription(model.getDescription());
			forms.add(form);
		}
		return forms;
	}
	//Save AssetManagementCondition*****
	@Transactional
	public void SaveAssetManagementCondition(AssetConditionForm form) {
		
				AssetCondition assetCondition = new AssetCondition();
				assetCondition.setName(form.getConditionName());

				entityManager.persist(assetCondition);

			}

	@Override//****
	public AssetCondition getAssetManagementConditionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	//Save AssetManagementCategory*****
	@Transactional
	public void SaveAssetManagementCategory(AssetCategoryForm form) {
		AssetCategory assetCategory=new AssetCategory();
		assetCategory.setCategoryName(form.getCategoryName());
		assetCategory.setDescription(form.getDescription());
		entityManager.persist(assetCategory);
		
	}

	//edit ManagementSummar*****
	@Override
	public Asset getManagementSummarById(String id) {
	
		Asset asset=entityManager.find(Asset.class, id);
		return asset;
	}

	@Override
	public AssetCategory getAssetManagementCategoryId(Long id) {
		AssetCategory assetCategory=entityManager.find(AssetCategory.class,id);
		return assetCategory;
	}

	@Override//****need to check
	@Transactional
	public void updateAssetManagementSummary(AssetForm form) {
		Asset asset=entityManager.find(Asset.class,form.getId());
		asset.setName(form.getName());
		asset.setDescription(form.getDescription());
		asset.setCode(form.getCode());
		asset.setType(form.getType());
		asset.setSupplier(form.getSupplier());
		asset.setUser(BigInteger.valueOf(Long.parseLong(form.getUser())));
		asset.setManufacturer(form.getManufacturer());
		asset.setModel(form.getModel());
		asset.setIsAvailable(form.isAvailable());
		//asset.setAssetCategoryBean(form.getAssetCategory());
		//asset.setAssetConditionBean(form.getAssetCondition());
	//	asset.setAssetCategoryBean(entityManager.find(AssetCategory.class, Long.parseLong(form.getAssetCategory())));
		//asset.setAssetConditionBean(entityManager.find(AssetCondition.class, Long.parseLong(form.getAssetCondition())));
		
	}

	
	
	// need to work for both Relation and Asset Id
	@Override
	@Transactional
	public void SaveAssetRelation(AssetRelationForm form) {
		AssetRelation assetRelation=new AssetRelation();
	  //  Asset asset=entityManager.find(Asset.class, form.getAssetId());
	//  assetRelation.setAssetRelationType(form.getRelationType());
	//  assetRelation.setAssetUse(form.getAssetId());
		//assetRelation.setAssetid(form.getAssetId());
		//assetRelation.setRelationtype(form.getRelationType());
		assetRelation.setEntityType(form.getEntityType());
		assetRelation.setEntity(form.getEntity());
		entityManager.persist(assetRelation);
		
		
	}

	@Override
	public List<AssetCategoryAttributesView> getAssetCategoryAttributes(long cid) {
		// TODO Auto-generated method stub
		
		List<AssetCategoryAttribute> assetCategoryAttributes = (List<AssetCategoryAttribute>) entityManager.createQuery("SELECT AT FROM AssetCategoryAttribute AT WHERE AT.categoryId='"+cid+"'").getResultList();
		
		List<AssetCategoryAttributesView> at = new ArrayList<AssetCategoryAttributesView>();
		
		for(AssetCategoryAttribute a:assetCategoryAttributes)
		{
			AssetCategoryAttributesView act = new AssetCategoryAttributesView();
			act.setAttributeType(entityManager.find(AssetAttributeType.class,a.getAttributeType().longValue()).getName());
			act.setName(a.getName());
			act.setId(Long.parseLong(a.getId().toString()));
			
			at.add(act);
		}
		
		return at;
	}

	@Override
	public List<AssetAttributeTypeView> getAssetAttributeTypes() {

		List<AssetAttributeType> assetAttributeTypes = (List<AssetAttributeType>) entityManager.createQuery("SELECT AT FROM AssetAttributeType AT").getResultList();
		
		List<AssetAttributeTypeView> at = new ArrayList<AssetAttributeTypeView>();
		
		for(AssetAttributeType a:assetAttributeTypes)
		{
			AssetAttributeTypeView act = new AssetAttributeTypeView();			
			act.setName(a.getName());
			act.setId(a.getId());
			
			at.add(act);
		}
		
		return at;
	}

	@Override
	@Transactional
	public boolean saveAssetCategoryAttributes(AssetCatrgoryAttributeForm assetCategoryAttributes) {
	
		AssetCategory category =entityManager.find(AssetCategory.class, assetCategoryAttributes.getCategory());
		
		
		AssetAttributes[] assetAttributes = assetCategoryAttributes.getAssetAttributes();
		
		for (AssetAttributes assetcatAttribute :assetAttributes ) {
			
			String at=assetcatAttribute.getId();
			
			if(assetcatAttribute.getId()!=null && !at.isEmpty())
			{
				updateAssetCategoryAttributes(assetcatAttribute,category) ;
			}
			
			else
			{
			AssetCategoryAttribute assetcategoryAttribute = new AssetCategoryAttribute();
			
			long atType ;
			if(assetcatAttribute.getAttributeType().equalsIgnoreCase("string"))
			 atType =2;
			 else if(assetcatAttribute.getAttributeType().equalsIgnoreCase("integer"))
				 atType =1;
			 else if(assetcatAttribute.getAttributeType().equalsIgnoreCase("date"))
				 atType =3;
			 else 
				 atType =4;
			
			
			
			assetcategoryAttribute.setAttributeType(BigInteger.valueOf(atType));
			
			assetcategoryAttribute.setCategoryId(BigInteger.valueOf((category.getId())));
						
			assetcategoryAttribute.setName(assetcatAttribute.getName());
			
			entityManager.persist(assetcategoryAttribute);
			}
			//return true;
		}
		
		return false;
	
		
	}

	@Transactional
	private void updateAssetCategoryAttributes(AssetAttributes assetcatAttribute,AssetCategory category) {
		// TODO Auto-generated method stub
		
		AssetCategoryAttribute assetcategoryAttribute = entityManager.find(AssetCategoryAttribute.class, BigInteger.valueOf(Long.parseLong(assetcatAttribute.getId())));
		
		

		long atType ;
		if(assetcatAttribute.getAttributeType().equalsIgnoreCase("string"))
		 atType =2;
		 else if(assetcatAttribute.getAttributeType().equalsIgnoreCase("integer"))
			 atType =1;
		 else if(assetcatAttribute.getAttributeType().equalsIgnoreCase("date"))
			 atType =3;
		 else 
			 atType =4;
		
		
		
		assetcategoryAttribute.setAttributeType(BigInteger.valueOf(atType));
		
		assetcategoryAttribute.setCategoryId(BigInteger.valueOf(category.getId()));
					
		assetcategoryAttribute.setName(assetcatAttribute.getName());
		
		entityManager.merge(assetcategoryAttribute);
		
		
	}

	@Override
	@Transactional
	public boolean deleteAssetcategoryAttribute(long id) {
		try{
		AssetCategoryAttribute assetcategoryAttribute = entityManager.find(AssetCategoryAttribute.class, BigInteger.valueOf(id));
		entityManager.remove(assetcategoryAttribute);
		
		return true;
		
		}catch(Exception e)		
		{
			System.out.println(e.getMessage());
			return false;
			
		}
	}

	
}	
	

