package com.ssrv.fms.vo.managedentity;

import com.ssrv.fms.model.managedentity.ManagedEntityMaster;

public class ManagedEntityMasterVO{
private Long id;
private String name;
private Short isDeleted;
public ManagedEntityMasterVO() {
	// TODO Auto-generated constructor stub
}
public ManagedEntityMasterVO(ManagedEntityMaster model)
{
	this.id=model.getId();
	this.name=model.getName();
	this.isDeleted=model.getIsDeleted();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Short getIsDeleted() {
	return isDeleted;
}
public void setIsDeleted(Short isDeleted) {
	this.isDeleted = isDeleted;
}

}
