package com.ssrv.fms.service.UserType.Intf;

import java.util.List;

import com.ssrv.fms.model.UserType.Usertype;
import com.ssrv.fms.vo.Usertype.UsertypeForm;

public interface UsertypeIntf {
	
	public List<Usertype> getAllUsertype();

	public void SaveUsertype(UsertypeForm form);

	public String deleteUsertype(int id);

	public Usertype getUsertypeByID(long id);
	
	public void updateUsertype(UsertypeForm cnt);
	

}
