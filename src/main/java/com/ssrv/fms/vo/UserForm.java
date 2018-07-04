package com.ssrv.fms.vo;

public class UserForm {
	
	private long userId;
	private String userName;
	private String password;	
	private String newpassword;
	
	public String getNewpassword()
		{
			return newpassword;
		}
	public void setNewpassword(String newpassword)
		{
			this.newpassword = newpassword;
		}
	public long getUserId()
		{
			return userId;
		}
	public void setUserId(long userId)
		{
			this.userId = userId;
		}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
