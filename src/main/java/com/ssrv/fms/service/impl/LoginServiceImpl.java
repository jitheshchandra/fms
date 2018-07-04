package com.ssrv.fms.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.user.User;
import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.service.admin.intf.LoginService;

@Service
public class LoginServiceImpl implements LoginService
	{

		@PersistenceContext
		private EntityManager entityManager;

		public boolean doLogin(String username, String password,
				boolean rememberMe) throws Exception
			{

				@SuppressWarnings("unchecked")
				List<User> userAuth = (List<User>) entityManager.createQuery("SELECT U.userName,U.password FROM User U WHERE userName='"+ username + "' AND password='" + password+ "'").getResultList();
				int isUserPresent = userAuth.size();
				if (isUserPresent >= 1)
					return true;
				else
					return false;

			}

		public User getUserId(String userName, String password)
			{

				User user = (User) entityManager.createQuery(
						"SELECT U FROM User U WHERE userName='" + userName
								+ "' AND password='" + password + "'")
						.getSingleResult();
				return user;

			}

		public List<UserBranchMapping> getUserBranchMappingByUserId(long userid)
			{

				@SuppressWarnings("unchecked")
				List<UserBranchMapping> user = entityManager.createQuery("SELECT U FROM UserBranchMapping U WHERE u.userId="+ userid ).getResultList();
				return user;

			}


		@Transactional
		public String getPassword(long userId) throws FmsException
			{
			
				User user = entityManager.find(User.class, userId+"");
						
				String oldPassword = user.getPassword();
				
				if (oldPassword.length() >= 1)
					return oldPassword;
				else
					throw new FmsException();

								
			}
		@Transactional
		public void setPassword(String newPassword,long userId)
		{
			
			User user = entityManager.find(User.class, userId+"");
			user.setPassword(newPassword);
				entityManager.merge(user);	
		
		}
		
		
		
		
		
	}
