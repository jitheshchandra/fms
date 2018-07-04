package com.ssrv.fms.service.organization.intf;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.item.Itemtype;
import com.ssrv.fms.model.user.User;
import com.ssrv.fms.test.BaseFMSTester;

public class UserServiceIntegrationTest extends BaseFMSTester {
	@Autowired
	IBaseDAO baseDao;

	@Test
	@Transactional
	public void addUser() {

		Employees employee = new Employees();
		employee.setAddress1("1");
		employee.setAddress2("2");
		employee.setAddress3("3");
		employee.setCity("Bangalore");
		// employee.setCountryId("IN");
		// employee.setDesignationId("SSE");
		employee.setDob(new Date());
		employee.setEmail("h@h.h");
		employee.setEmpCode("123");
		employee.setFirstName("Hari");
		employee.setLastName("Haran");
		// employee.setIsDeleted(1);

		User user = new User();
	//	user.setEmployeeId(employee);
		user.setPassword("hari");
		//user.setUserId("hari");
		user.setUserName("hari");
		// user.set
		baseDao.save(user);

	}

	@Autowired
	@Qualifier(value = "baseDao")
	private IBaseDAO itemTypeDao;

	@Test
	public void test() {
		System.out.println(itemTypeDao.getAll(Itemtype.class));
	}
}
