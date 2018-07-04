package com.ssrv.fms.dao.incident.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssrv.fms.dao.impl.BaseDAO;
import com.ssrv.fms.dao.incident.IIncidentDAO;
import com.ssrv.fms.model.incident.EscalationMatrix;
import com.ssrv.fms.model.incident.Escalationlevel;
import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.model.incident.Incidents;

@Component
public class IncidentJPADAO extends BaseDAO implements IIncidentDAO {

	@Override
	public List<Incidentcategory> getIncidentCategoryByParentId(Long id) {
		return createCriteria(Incidentcategory.class).innerJoin("incident")
				.andEquals("incident.parentCategory", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EscalationMatrix> getEscalationMatrixByBranchAndType(
			Long branchId, Long typeId) {
		return getEntityManager()
				.createQuery(
						"select em from EscalationMatrix as em inner join  em.branch as branch inner join em.category as category where branch.id="
								+ branchId
								+ " and category.id="
								+ typeId
								+ " order by em.order").getResultList();
	}

	@Override
	public Escalationlevel getEscalationByName(String name) {
		List<Escalationlevel> escList = createCriteria(Escalationlevel.class)
				.andEquals("name", name).getResultList();
		if (escList.size() == 0) {
			return null;
		}
		return escList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Incidentcategory> getIncidentCategoriesHavingEscalationMatrix(
			long branchId) {
		return getEntityManager()
				.createQuery(
						"select cat from EscalationMatrix as em inner join em.category as cat inner join em.branch as br where br.id="
								+ branchId + " order by em.order")
				.getResultList();
	}

	@Override
	public List<Incidents> getIncidentByStatus(String status) {
		return createCriteria(Incidents.class).andEquals("status", status)
				.getResultList();
	}
}
