package com.ssrv.fms.dao.incident;

import java.util.List;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.incident.EscalationMatrix;
import com.ssrv.fms.model.incident.Escalationlevel;
import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.model.incident.Incidents;

public interface IIncidentDAO extends IBaseDAO {
	List<Incidentcategory> getIncidentCategoryByParentId(Long id);

	List<EscalationMatrix> getEscalationMatrixByBranchAndType(Long branchId,
			Long typeId);

	Escalationlevel getEscalationByName(String name);

	List<Incidentcategory> getIncidentCategoriesHavingEscalationMatrix(
			long branchId);

	List<Incidents> getIncidentByStatus(String status);
}
