package com.ssrv.fms.vo.managedentity;

public class ManagedEntityInstanceView
{

 private Long id;
 private String name;
 private String masterName;
 private Boolean isMainEntity;
 private String mainEntityName;
 private String capacity;
 private String serialNumber;
 //private String instanceName;
 
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
public String getMasterName() {
	return masterName;
}
public void setMasterName(String masterName) {
	this.masterName = masterName;
}
public Boolean getIsMainEntity() {
	return isMainEntity;
}
public void setIsMainEntity(Boolean isMainEntity) {
	this.isMainEntity = isMainEntity;
}
public String getMainEntityName() {
	return mainEntityName;
}
public void setMainEntityName(String mainEntityName) {
	this.mainEntityName = mainEntityName;
}
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public String getSerialNumber() {
	return serialNumber;
}
public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}

}

 
 

