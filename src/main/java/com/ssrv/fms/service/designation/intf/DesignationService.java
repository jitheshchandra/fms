package com.ssrv.fms.service.designation.intf;

import java.util.List;

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.vo.Designation.DesignationForm;

public interface DesignationService 
{
	
	public List<Designations> getAllDesignations();

	public void SaveDesignation(DesignationForm form);
	
	public void deleteDesignation(int id);
	
	public Designations getDesignationById(Long id);
	
	public void updateDesignation(DesignationForm form);

}
