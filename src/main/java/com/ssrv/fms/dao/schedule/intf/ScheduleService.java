package com.ssrv.fms.dao.schedule.intf;

import java.util.List;

import com.ssrv.fms.vo.schedule.EditScheduleForm;
import com.ssrv.fms.vo.schedule.ManageScheduleVO;
import com.ssrv.fms.vo.schedule.ScheduleVO;


public interface ScheduleService {
	
	public boolean SaveSchedule(ScheduleVO form);
	
	public List<ScheduleVO> GetAllSchedules();

	public List<ManageScheduleVO> GetAllSchedulesByDate(String fromDate,String toDate);

	public ManageScheduleVO GetAllSchedulesByID(long scheduleId);
	
	public String DeleteScheduleById(int id);

	public boolean UpdateSchedule(EditScheduleForm form);
	

	
	
}
