package com.ssrv.fms.service.UserType.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.UserType.Usertype;
import com.ssrv.fms.service.UserType.Intf.UsertypeIntf;
import com.ssrv.fms.vo.Usertype.UsertypeForm;

@Service
@Lazy
public class UsertypeImpl implements UsertypeIntf {

	@PersistenceContext
	private EntityManager entityManager;

	
	//Get all user

	public List<Usertype> getAllUsertype() {
		@SuppressWarnings("unchecked")
		List<Usertype> usertype = (List<Usertype>) entityManager.createQuery(
				"SELECT U FROM Usertype U WHERE isDeleted=0").getResultList();
		System.out.println("UserType:" + usertype);
		return usertype;
	}
	
	
	//Save All user
@Transactional
	public void SaveUsertype(UsertypeForm form) {

		Usertype usertype = new Usertype();
		usertype.setName(form.getName());
		usertype.setIsDeleted(0);

		entityManager.persist(usertype);

	}
	
	
	//Delete user by id
@Transactional
	public String deleteUsertype(int id) {

		long user_id = (long) id;
		Usertype user = entityManager.find(Usertype.class, user_id);

		user.setIsDeleted(1);

		entityManager.merge(user);

		return "entity update successfully";

	}

	
	//Edit User name
	
	public Usertype getUsertypeByID(long id) {

		Usertype user = entityManager.find(Usertype.class, id);
		return user;
	}
	
	
	//Update User form
    @Transactional
	public void updateUsertype(UsertypeForm form) {
		Usertype user = entityManager.find(Usertype.class, form.getId());
		user.setName(form.getName());

	}


}
