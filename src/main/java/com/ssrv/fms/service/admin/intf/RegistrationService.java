package com.ssrv.fms.service.admin.intf;

import com.ssrv.fms.model.user.User;

public interface RegistrationService {
	
	/**
	 * All the methods are parameterized with User local variable to 
	 * manage thread safety. Since the service class is instantiated as 
	 * singleton (not prototype), it should not have a member variable
	 * to ensure thread safety
	 * Strategy pattern has been used to delegate the calls to related concrete classes.
	 */
	
	
	public void processRegistration(User user);
	
	public void save(User user);
	
	public void sendEmailNotification(User user);
	
	public void logToHistory(User user);

}
