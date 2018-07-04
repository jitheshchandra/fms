package com.ssrv.fms.service.User.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.UserType.Usertype;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.user.User;
import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.service.User.Intf.UserIntf;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;
import com.ssrv.fms.vo.user.CreateUserForm;
import com.ssrv.fms.vo.user.UserBranchMappingVO;

@Service
@Lazy
@Transactional
public class UserImpl implements UserIntf
	{
		@PersistenceContext
		private EntityManager entityManager;
		
		@Autowired
		@Qualifier(value = "baseDao")
		private IBaseDAO baseDao;

		@Override
		public List<User> getAllUser(Long orgId,Long brchId)
			{
				List<User> userList=new ArrayList<User>();
				@SuppressWarnings({ "unchecked" })
				List<UserBranchMapping> userIdFromBranchMaping = entityManager.createQuery("SELECT b FROM UserBranchMapping b WHERE b.organizationId="+orgId+"AND b.branchId="+brchId).getResultList();
				for(UserBranchMapping userId:userIdFromBranchMaping)
					{			
						User user = entityManager.find(User.class,userId.getUserId());
						userList.add(user);
					}
				return userList;
			}

		@Override
		public boolean deleteUser(String userId)
			{
				try{
				User user = entityManager.find(User.class, userId);
				baseDao.delete(user);		
				@SuppressWarnings("unchecked")
				List<Long> usersIds = entityManager.createQuery("SELECT b.Id FROM UserBranchMapping b WHERE b.userId="+userId).getResultList();
				for(Long id:usersIds)
					{
				      entityManager.createQuery("DELETE FROM UserBranchMapping u WHERE u.Id="+id).executeUpdate();
					}
				}
				catch(Exception e)
					{
						e.printStackTrace();
					}
				return true;
			}

		@Override
		public User getUserById(String userId)
			{
				User user=(User) entityManager.createQuery("SELECT u FROM User u WHERE u.isLocked=0 AND u.userId="+userId).getSingleResult();
				return user;
			}

		@Override
		public void saveUser(CreateUserForm form)
			{
			 User userModel=null;		
		     if(form.getId()==null)
					{		
			        try{
				          userModel=new User();
				          userModel.setUserName(form.getUserName());
				          userModel.setPassword(form.getPassword());
				          
				          long utypeid = entityManager.find(Usertype.class, form.getUserTypeId()).getId();
				          userModel.setUserTypeId((utypeid));
				          userModel.setModifiedOn(new Date());
				          userModel.setLocked((byte) 0);
				          entityManager.persist(userModel);
				
				          List<String> branchs=form.getIncludeBranch();
						  for(String branchId:branchs)
						  {	  
							if(branchId!=null)
								{
								Branch branch = entityManager.find(Branch.class,Long.parseLong(branchId));
								UserBranchMapping userBranchMaping=new UserBranchMapping();
								userBranchMaping.setUserId(userModel);
								userBranchMaping.setOrganizationId(entityManager.find(Organization.class, branch.getOrganization().getId()));
								userBranchMaping.setBranchId(branch);
								userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
								userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
								userBranchMaping.setDefaultOrganization(Long.parseLong(form.getIsDefaultOrganization()));
								entityManager.persist(userBranchMaping);
							    }
						  }		  
				       }
				catch(Exception e)
					{
						e.printStackTrace();
					}
					}
				else
					{
					try{
						userModel=entityManager.find(User.class,form.getId());
						userModel.setUserName(form.getUserName());
						userModel.setUserTypeId(entityManager.find(Usertype.class, form.getUserTypeId()).getId());
						userModel.setModifiedOn(new Date());
						userModel.setLocked((byte)0);
						entityManager.persist(userModel);
						deleteUserBranchMappingByUserId(Long.parseLong(form.getId()));
						List<String> branchs=form.getIncludeBranch();
						UserBranchMapping userBranchMaping=null;
					    for(String branchId:branchs)
					    {			
					        if(branchId!=null)
						         { 
										Branch branch = entityManager.find(Branch.class,Long.parseLong(branchId));
									    @SuppressWarnings("unchecked")
									    List<UserBranchMapping> getBranch=entityManager.createQuery("SELECT b FROM UserBranchMapping b WHERE b.organizationId="+branch.getOrganization().getId()+"AND b.branchId="+branch.getId()+"AND b.userId="+form.getId()).getResultList();
										if(getBranch.isEmpty())
											{
									           userBranchMaping=new UserBranchMapping();
										       userBranchMaping.setUserId(userModel);
										       userBranchMaping.setOrganizationId(entityManager.find(Organization.class, branch.getOrganization().getId()));
										       userBranchMaping.setBranchId(branch);
										       userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
										       userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
										       userBranchMaping.setDefaultOrganization(Long.parseLong(form.getIsDefaultOrganization()));
										       entityManager.persist(userBranchMaping);
											}
										 else{
											   userBranchMaping=(UserBranchMapping) entityManager.createQuery("SELECT b FROM UserBranchMapping b WHERE b.organizationId="+branch.getOrganization().getId()+"AND b.branchId="+branch.getId()+"AND b.userId="+form.getId()).getSingleResult();
											   userBranchMaping.setUserId(userModel);
											   userBranchMaping.setOrganizationId(entityManager.find(Organization.class, branch.getOrganization().getId()));
											   userBranchMaping.setBranchId(branch);
											   userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
											   userBranchMaping.setDefaultBranch(Long.parseLong(form.getIsDefaultBranch()));
											   userBranchMaping.setDefaultOrganization(Long.parseLong(form.getIsDefaultOrganization()));
										    }
									}
					    }
					}
						catch(Exception e)
							{
								e.printStackTrace();
							}
					}
			}

		private void deleteUserBranchMappingByUserId(Long userId)
		{
			entityManager.createQuery("DELETE FROM UserBranchMapping b WHERE b.userId="+userId).executeUpdate();
		}
		
		@Override
		public List<UserBranchMappingVO> getAllUserBarnchMappingByUserId(Long userId)
			{
				@SuppressWarnings("unchecked")
				List<UserBranchMapping> mappingList = entityManager.createQuery("SELECT b FROM UserBranchMapping b WHERE b.userId="+userId).getResultList();
				List<UserBranchMappingVO> mappingVo=new ArrayList<UserBranchMappingVO>();
				for(UserBranchMapping list:mappingList)
					{
						mappingVo.add(new UserBranchMappingVO(list));
					}
				return mappingVo;
			}

		@Override
		public UserBranchMappingForm getuserByUserId(Long userId) {
			
			@SuppressWarnings("unchecked")
			List<UserBranchMapping> mappingList =entityManager.createQuery("SELECT b FROM UserBranchMapping b WHERE b.userId="+userId).getResultList();
			UserBranchMappingForm form=new  UserBranchMappingForm();
			for(UserBranchMapping list:mappingList)
			{
				form.setUserBranchMappingId(list.getId());
				form.setDefaultOrganization(list.getDefaultOrganization());
				form.setDefaultBranchId(list.getDefaultBranch());
			}
		
			return form;
		}

		@Override
		public List<User> getAllUsers() {
			@SuppressWarnings("unchecked")
			List<User> userList=entityManager.createQuery("SELECT U FROM User U").getResultList();
			return userList;
		}
	}
