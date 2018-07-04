package com.ssrv.fms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//We are not using this any where . 
//@Entity
//@Table(name = "ROLE")
public class Role {
	
	@Id
	@Column(name = "ROLE_ID")
	private String roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	
// where or how is role mapped by user Nag
	
//	@OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy="role", targetEntity = User.class)
//	private User user;
	
	public static final String ROLE_DOCTOR = "doctor";
	public static final String ROLE_CLINIC = "clinic";
	public static final String ROLE_TRANSCRIBER = "transcriber";
	public static final String ROLE_ADMIN = "admin";
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
