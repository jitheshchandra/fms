package com.ssrv.fms.service.organization.intf;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.test.BaseFMSTester;
import com.ssrv.fms.vo.organization.AddOrganizationForm;

public class OrganizationIntegrationTest extends BaseFMSTester {
	@Autowired
	OrganizationService organizationService;

	@Test
	public void addOrganization() {

		AddOrganizationForm org = new AddOrganizationForm();
		org.setAddress1("aaa1");
		org.setAddress2("aaa1");
		org.setAddress3("aaa1");
		org.setContactMailId("aaa1@hasd");

		org.setName("org-name");
		org.setOrganizationTypesId(1l);
		org.setRegNumber("reg-num");
		org.setRegdate("22-7-2014");
		org.setOrgState("KA");
		org.setPinCode("560022");
		org.setContactMobileNo("123123123");
		org.setContactPerson("contact-person");
		org.setContactPersonDesignation("SSE");

		org.setAddress1("aaa1");
		org.setAddress1("aaa1");
		org.setAddress1("aaa1");

		organizationService.saveOrganization(org);

	}

	@Autowired
	@Qualifier(value = "baseDao")
	private IBaseDAO itemTypeDao;

	@Test
	public void test() {
		System.out.println(itemTypeDao.getAll(Itemtype.class));
	}
}
