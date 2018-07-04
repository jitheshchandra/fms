package com.ssrv.fms.service.admin.intf;

import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;

public interface ChangeBranchIntf
	{

		public UserBranchMapping getUserBranchMapping(long userId);

		public void updateUserBranchMapping(UserBranchMappingForm from);

	}
