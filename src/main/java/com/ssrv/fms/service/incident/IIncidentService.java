package com.ssrv.fms.service.incident;

import java.util.List;

import com.ssrv.fms.model.incident.IncidentStatus;
import com.ssrv.fms.vo.incidents.EscalationLevelInfo;
import com.ssrv.fms.vo.incidents.EscalationMatrixInfo;
import com.ssrv.fms.vo.incidents.IncidentCategoryInfo;
import com.ssrv.fms.vo.incidents.IncidentInfo;
import com.ssrv.fms.vo.incidents.IncidentInfoForm;
import com.ssrv.fms.vo.incidents.UpdateIncidentStatusForms;
import com.ssrv.fms.vo.incidents.impl.EscalationMatrixInfoForm;

public interface IIncidentService {

	List<EscalationLevelInfo> getEscalationLevelList();

	List<IncidentCategoryInfo> getIncidentCategoryList();

	IncidentCategoryInfo getIncidentCategoryById(Long id);

	void saveIncidentCategory(IncidentCategoryInfo categoryInfo);

	void deleteIncidentCategoryById(Long id);

	List<EscalationMatrixInfo> getEscalationMatrix(Long branchId, Long typeId);

	void saveEscalationLevel(String name);

	Boolean deleteEscalationLevel(long id);

	void saveEscalationMatrix(EscalationMatrixInfoForm escalationMatrixForm);

	Boolean deleteEscalationMatrix(long id);

	void saveIncident(IncidentInfoForm incidentInfo);

	List<IncidentCategoryInfo> getIncidentCategoriesHavingEscalationMatrix(
			long branchId);

	List<IncidentInfo> getIncidentsByStatus(IncidentStatus status);

	void updateIncidentStatus(
			UpdateIncidentStatusForms updateIncidentStatusForms);

}
