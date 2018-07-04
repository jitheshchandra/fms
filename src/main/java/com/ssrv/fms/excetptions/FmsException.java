package com.ssrv.fms.excetptions;

public class FmsException extends Exception{
	private String message;
	
	public FmsException() {
		// TODO Auto-generated constructor stub
	}
	
	public FmsException(String message) {
		this.message  = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
