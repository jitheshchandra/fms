package com.ssrv.fms.service.User.Intf;

import java.util.List;

import com.ssrv.fms.model.user.User;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;
import com.ssrv.fms.vo.user.CreateUserForm;
import com.ssrv.fms.vo.user.UserBranchMappingVO;

public interface UserIntf
	{
		List<User> getAllUser(Long orgId,Long brchId);
		
		boolean deleteUser(String userId);
		
		User getUserById(String userId);
		
		void saveUser(CreateUserForm form); 
		
		List<UserBranchMappingVO> getAllUserBarnchMappingByUserId(Long userId);
		
		UserBranchMappingForm getuserByUserId(Long userId);
		
		List<User> getAllUsers();
	}
