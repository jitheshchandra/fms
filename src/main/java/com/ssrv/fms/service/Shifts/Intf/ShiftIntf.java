package com.ssrv.fms.service.Shifts.Intf;

import java.util.List;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.shift.Shifts;
import com.ssrv.fms.vo.Shifts.ShiftForm;
import com.ssrv.fms.vo.Shifts.ShiftSummaryForm;


public interface ShiftIntf
	{
		//public List<Shifts> getAllShift();

		public void SaveShift(ShiftForm form);
		
		public List<Branch> getBranchByOraganizationId(long orgId);
		
		public List<Shifts> getAllShiftsByBranchId(long brId,long crId);
		
		public Shifts getShiftsById(long shiftsId);
		
		public void updateShift(ShiftForm shift);
		
	

//		public String deleteUsertype(int id);
//
//		public Usertype getUsertypeByID(long id);
//		
//		public void updateUsertype(UsertypeForm cnt);

	}
