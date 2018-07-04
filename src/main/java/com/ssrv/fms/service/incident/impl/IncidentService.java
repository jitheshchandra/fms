package com.ssrv.fms.service.incident.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.dao.incident.IIncidentDAO;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.incident.EscalationMatrix;
import com.ssrv.fms.model.incident.Escalationlevel;
import com.ssrv.fms.model.incident.IncidentStatus;
import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.model.incident.Incidents;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.service.incident.IIncidentService;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;
import com.ssrv.fms.vo.incidents.IncidentInfo;
import com.ssrv.fms.vo.incidents.IncidentInfoForm;
import com.ssrv.fms.vo.incidents.IncidentResolveInfo;
import com.ssrv.fms.vo.incidents.UpdateIncidentStatusForms;
import com.ssrv.fms.vo.incidents.impl.EscalationLevelInfoWrapper;
import com.ssrv.fms.vo.incidents.impl.EscalationMatrixInfoForm;
import com.ssrv.fms.vo.incidents.impl.EscalationMatrixInfoWrapper;
import com.ssrv.fms.vo.incidents.impl.EscalationMatrixLevelInfoForm;
import com.ssrv.fms.vo.incidents.impl.IncidentCategoryInfoWrapper;
import com.ssrv.fms.vo.incidents.impl.IncidentInfoWrapper;

@Component
public class IncidentService implements IIncidentService {

	@Autowired
	private IIncidentDAO incidentDao;

	@Autowired
	@Qualifier(value = "baseDao")
	private IBaseDAO baseDao;

	@Override
	@Transactional(readOnly = true)
	public List<IncidentCategoryInfo> getIncidentCategoryList() {
		List<Incidentcategory> categories = incidentDao
				.getAll(Incidentcategory.class);
		List<IncidentCategoryInfo> categoryInfoList = new ArrayList<IncidentCategoryInfo>();
		for (Incidentcategory category : categories) {
			IncidentCategoryInfoWrapper categoryInfo = new IncidentCategoryInfoWrapper(
					category);
			categoryInfoList.add(categoryInfo);
		}
		return categoryInfoList;
	}

	@Override
	@Transactional(readOnly = true)
	public IncidentCategoryInfo getIncidentCategoryById(Long id) {
		Validate.notNull(id, "id cannot be null");
		Incidentcategory category = incidentDao.findById(
				Incidentcategory.class, id);
		if (category == null) {
			return null;
		}
		IncidentCategoryInfoWrapper categoryInfo = new IncidentCategoryInfoWrapper(
				category);
		return categoryInfo;
	}

	@Override
	@Transactional
	public void saveIncidentCategory(IncidentCategoryInfo categoryInfo) {
		Incidentcategory category = null;
		if (categoryInfo.getId() != null) {
			category = incidentDao.findById(Incidentcategory.class,
					categoryInfo.getId());
		} else {
			category = new Incidentcategory();
		}
		toIncidentCategory(categoryInfo, category);
		incidentDao.save(category);
	}

	private void toIncidentCategory(IncidentCategoryInfo categoryInfo,
			Incidentcategory category) {
		category.setName(categoryInfo.getName());
		if (categoryInfo.getParentIncidentCategory() != null) {
			Incidentcategory parentCategory = incidentDao.findById(
					Incidentcategory.class, categoryInfo
							.getParentIncidentCategory().getId());
			category.setParentCategory(parentCategory);
		}
		/**
		 * TODO: if children are changed then, this would need to be updated.
		 * else, can be ignored until then.
		 */
		// if(CollectionUtils.isNotEmpty(categoryInfo.get))
	}

	@Override
	@Transactional
	public void deleteIncidentCategoryById(Long id) {
		Incidentcategory incident = incidentDao.findById(
				Incidentcategory.class, id);
		incidentDao.delete(incident);
	}

	@Override
	@Transactional
	public List<EscalationMatrixInfo> getEscalationMatrix(Long branchId,
			Long typeId) {
		List<EscalationMatrix> escalationLevels = incidentDao
				.getEscalationMatrixByBranchAndType(branchId, typeId);
		List<EscalationMatrixInfo> escalationMatrixInfoList = new ArrayList<EscalationMatrixInfo>();
		for (EscalationMatrix matrix : escalationLevels) {
			escalationMatrixInfoList
					.add(new EscalationMatrixInfoWrapper(matrix));
		}
		return escalationMatrixInfoList;
	}

	@Override
	@Transactional
	public void saveEscalationMatrix(
			EscalationMatrixInfoForm escalationMatrixForm) {
		List<EscalationMatrix> escalationLevels = incidentDao
				.getEscalationMatrixByBranchAndType(
						escalationMatrixForm.getBranchId(),
						escalationMatrixForm.getCategoryId());
		Branch branch = baseDao.findById(Branch.class,
				escalationMatrixForm.getBranchId());
		Incidentcategory category = baseDao.findById(Incidentcategory.class,
				escalationMatrixForm.getCategoryId());
		int order = 0;
		for (EscalationMatrixLevelInfoForm matrixLevel : escalationMatrixForm
				.getMatrixLevel()) {
			order++;
			EscalationMatrix escalationMatrix = null;
			if (matrixLevel.getId() != null) {
				for (EscalationMatrix escalationLevel : escalationLevels) {
					if (escalationLevel.getId().equals(matrixLevel.getId())) {
						escalationMatrix = escalationLevel;
						break;
					}
				}
			} else if (escalationMatrix == null) {
				escalationMatrix = new EscalationMatrix();
				escalationLevels.add(escalationMatrix);
			}
			escalationMatrix.setBranch(branch);
			escalationMatrix.setCategory(category);
			escalationMatrix.setContactEmail(matrixLevel.getContactEmail());
			escalationMatrix.setContactPhone(matrixLevel.getContactPhone());
			escalationMatrix.setContactName(matrixLevel.getContactName());
			Escalationlevel escalationLevel = baseDao.findById(
					Escalationlevel.class, matrixLevel.getLevelId());
			escalationMatrix.setLevel(escalationLevel);
			escalationMatrix.setOrder(order);
			incidentDao.save(escalationMatrix);
		}
	}

	@Override
	@Transactional
	public List<EscalationLevelInfo> getEscalationLevelList() {
		List<Escalationlevel> escalationLevels = incidentDao
				.getAll(Escalationlevel.class);
		List<EscalationLevelInfo> escalationLevelInfos = new ArrayList<EscalationLevelInfo>();
		for (Escalationlevel escalation : escalationLevels) {
			escalationLevelInfos
					.add(new EscalationLevelInfoWrapper(escalation));
		}
		return escalationLevelInfos;
	}

	@Override
	@Transactional
	public void saveEscalationLevel(String name) {
		Validate.notNull(name, "name cannot be null");
		Escalationlevel escalation = incidentDao.getEscalationByName(name);
		if (escalation != null) {
			// throw exception
		}
		escalation = new Escalationlevel();
		escalation.setName(name);
		incidentDao.save(escalation);
	}

	@Override
	@Transactional
	public Boolean deleteEscalationLevel(long id) {
		/**
		 * TODO>> move all the escalation for this level,if found and then, set
		 * deleted to true.
		 */
		Escalationlevel escalation = incidentDao.findById(
				Escalationlevel.class, id);
		if (escalation != null) {
			incidentDao.delete(escalation);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean deleteEscalationMatrix(long id) {
		EscalationMatrix escalationMatrix = incidentDao.findById(
				EscalationMatrix.class, id);
		if (escalationMatrix != null) {
			incidentDao.delete(escalationMatrix);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void saveIncident(IncidentInfoForm incidentInfoForm) {
		Incidents incident = null;
		if (incidentInfoForm.getId() != null) {
			incident = incidentDao.findById(Incidents.class,
					incidentInfoForm.getId());
		}
		if (incident == null) {
			incident = new Incidents();
		}
		toIncident(incidentInfoForm, incident);
		incidentDao.save(incident);
	}

	private void toIncident(IncidentInfoForm incidentInfoForm,
			Incidents incident) {
		incident.setIncidentDate(incidentInfoForm.getIncidentDate());
		incident.setIncidentTime(incidentInfoForm.getIncidentTime());
		Incidentcategory incidentCategory = incidentDao.findById(
				Incidentcategory.class, incidentInfoForm.getIncidentTypeId());
		incident.setIncidentType(incidentCategory);
		ManagedEntityGroup managedEntity = incidentDao
				.findById(ManagedEntityGroup.class,
						incidentInfoForm.getManagedEntityId());
		incident.setManagedEntity(managedEntity);
		incident.setRemarks(incidentInfoForm.getRemarks());
		toIncidentStatus(incidentInfoForm, incident);

		EscalationMatrix escalationMatrix = null;
		if (incidentInfoForm.getEscalationMatrixId() == null) {
			escalationMatrix = getEntryEscalationLevel(incident
					.getManagedEntity().getBranch().getId(), incident
					.getIncidentType().getId());
		} else {
			escalationMatrix = incidentDao.findById(EscalationMatrix.class,
					incidentInfoForm.getEscalationMatrixId());

		}
		incident.setEscalationMatrix(escalationMatrix);
	}

	private EscalationMatrix getEntryEscalationLevel(Long branchId, Long typeId) {
		List<EscalationMatrix> escalationLevels = incidentDao
				.getEscalationMatrixByBranchAndType(branchId, typeId);
		if (CollectionUtils.isNotEmpty(escalationLevels)) {
			return escalationLevels.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public List<IncidentCategoryInfo> getIncidentCategoriesHavingEscalationMatrix(
			long branchId) {
		List<Incidentcategory> categories = incidentDao
				.getIncidentCategoriesHavingEscalationMatrix(branchId);
		List<IncidentCategoryInfo> categoryInfoList = new ArrayList<IncidentCategoryInfo>();
		for (Incidentcategory category : categories) {
			IncidentCategoryInfoWrapper categoryInfo = new IncidentCategoryInfoWrapper(
					category);
			categoryInfoList.add(categoryInfo);
		}
		return categoryInfoList;
	}

	@Override
	public List<IncidentInfo> getIncidentsByStatus(IncidentStatus status) {
		List<Incidents> incidents = incidentDao.getIncidentByStatus(status
				.name());
		List<IncidentInfo> incidentList = new ArrayList<IncidentInfo>();
		for (Incidents incident : incidents) {
			incidentList.add(new IncidentInfoWrapper(incident));
		}
		return incidentList;
	}

	@Override
	@Transactional
	public void updateIncidentStatus(
			UpdateIncidentStatusForms updateIncidentStatusForms) {
		for (IncidentResolveInfo incidentForm : updateIncidentStatusForms
				.getIncidents()) {
			Incidents incident = incidentDao.findById(Incidents.class,
					incidentForm.getId());
			toIncidentStatus(incidentForm, incident);
			incidentDao.save(incident);
		}

	}

	private void toIncidentStatus(IncidentResolveInfo incidentForm,
			Incidents incident) {
		incident.setStatus(incidentForm.getStatus());
		incident.setResolvedOn(incidentForm.getResolvedOn());
		incident.setResolvedRemarks(incidentForm.getResolvedRemarks());
	}
}
