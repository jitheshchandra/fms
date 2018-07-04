package com.ssrv.fms.service.incident.intf;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.incident.IIncidentDAO;
import com.ssrv.fms.model.incident.Incidentcategory;
import com.ssrv.fms.service.incident.IIncidentService;
import com.ssrv.fms.test.BaseFMSTester;

public class IncidentIntegrationTest extends BaseFMSTester {
	@Autowired
	private IIncidentService incidentService;

	@Autowired
	private IIncidentDAO incidentDAO;

	@Test
	@Transactional
	public void saveIncidentCategory() {
		Incidentcategory cat = new Incidentcategory();
		cat.setName("Cat 1");
		incidentDAO.save(cat);
	}

}
