package com.ssrv.fms.service.admin.intf;

import java.util.List;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.user.User;
import com.ssrv.fms.model.user.UserBranchMapping;

/**
 *
 * @author josebarrueta
 * @since 1/18/2013
 *
 */
public interface LoginService {

    /**
     * log in action to authenticate a user.
     *
     * @param username
     * @param password
     */
   boolean doLogin(String username, String password, boolean rememberMe) throws Exception;

	User getUserId(String userName, String password);

	List<UserBranchMapping> getUserBranchMappingByUserId(long userid);
	
	
	public String getPassword(long userId) throws FmsException;
	
	public void setPassword(String newPassword,long userId);
	
	

}
