package com.ssrv.fms.service.admin.intf;

import java.util.List;

import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.vo.state.StateForm;

public interface StateService {

	public List<States> getallStateNames();

	public void SaveState(StateForm form);
	
	public void deleteState(int id);
	
	public States getStateById(Long id);
	
	public void updateState(StateForm branch);
	
	public List<States> getAllStateByCountryId(long id);
	
	public long getCountryByStateId(long stateId);
	
	public StateForm getStateFomByStateId(long id);
	
	public List<States> getAllStates();

}
