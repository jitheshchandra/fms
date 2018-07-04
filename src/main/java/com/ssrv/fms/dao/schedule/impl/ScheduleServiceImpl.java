package com.ssrv.fms.dao.schedule.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.exolab.castor.types.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.schedule.intf.ScheduleService;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.maintenance.Maintenanceinstance;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.schedule.MaintenaceSchedule;
import com.ssrv.fms.vo.schedule.EditScheduleForm;
import com.ssrv.fms.vo.schedule.ManageScheduleVO;
import com.ssrv.fms.vo.schedule.ScheduleVO;


@Service
public class ScheduleServiceImpl implements ScheduleService {

	@PersistenceContext
	private EntityManager entityManager;
	
	

	
	 
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public boolean SaveSchedule(ScheduleVO form) {
		
		
		try {
		
		MaintenaceSchedule scheduleInstance = new MaintenaceSchedule();
		
		scheduleInstance.setTitle(form.getTitle());
		scheduleInstance.setStartTime(form.getStartTime().toString());
		scheduleInstance.setEndTime(form.getEndTime().toString());
		scheduleInstance.setStartDate(form.getStartDate());
		scheduleInstance.setEndDate(form.getEnddate());
	    scheduleInstance.setOrganization(form.getOrganization());
		scheduleInstance.setBranch(form.getBranch());
		scheduleInstance.setEmpCode(form.getEmpCode());
		entityManager.persist(scheduleInstance);
		
		return true;
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<ScheduleVO> GetAllSchedules() {
		// TODO Auto-generated method stub
List<ScheduleVO> schedules = new ArrayList<ScheduleVO>(); 
		
		List<MaintenaceSchedule> s = (List<MaintenaceSchedule>) entityManager.createQuery("SELECT S FROM MaintenaceSchedule S ").getResultList();
		
		for(MaintenaceSchedule ms: s)
		{			
			ScheduleVO schedule = new ScheduleVO();
						
			schedule.setStartDate((ms.getStartDate()));
			schedule.setEnddate((ms.getEndDate()));
			schedule.setTitle((ms.getTitle()));
			schedule.setStartTime(ms.getStartTime());
			schedule.setEndTime(ms.getEndTime());
			schedule.setOrganization((ms.getOrganization()));
			schedule.setBranch((ms.getBranch()));
			
			schedules.add(schedule);
		}
		
		
		
		return schedules;
		
	}

	@Override
	public List<ManageScheduleVO> GetAllSchedulesByDate(String fromDate,String toDate) {
		// TODO Auto-generated method stub
List<ManageScheduleVO> schedules = new ArrayList<ManageScheduleVO>(); 
		
		List<MaintenaceSchedule> s = (List<MaintenaceSchedule>) entityManager.createQuery("SELECT S FROM MaintenaceSchedule S  where StartDate>='"+fromDate+"' AND EndDate<='"+toDate+"'").getResultList();
		
		for(MaintenaceSchedule ms: s)
		{			
			ManageScheduleVO schedule = new ManageScheduleVO();
			schedule.setId(ms.getId());			
			schedule.setStartDate((ms.getStartDate()));
			schedule.setEnddate((ms.getEndDate()));
			schedule.setTitle((ms.getTitle()));
			schedule.setStartTime(ms.getStartTime());
			schedule.setEndTime(ms.getEndTime());
			schedule.setOrganization((ms.getOrganization()));
			schedule.setBranch((ms.getBranch()));
			
			if(Long.parseLong(ms.getEmpCode())==0)
			{
				schedule.setEmployeeCode("--NA--");
				schedule.setEmployeeName("--NA--");
			}
			else
			{
			schedule.setEmployeeCode(ms.getEmpCode());
			long empid = Long.parseLong(ms.getEmpCode());
			
			Employees employee =  entityManager.find(Employees.class,empid);
			
			schedule.setEmployeeName(employee.getFirstName()+" "+employee.getLastName());
			
			}
			
			
		
			schedules.add(schedule);
		}
		
		
		
		return schedules;
		
	}


	@Override
	public ManageScheduleVO GetAllSchedulesByID(long scheduleId) {
ManageScheduleVO schedules = new ManageScheduleVO(); 
		
		MaintenaceSchedule s = (MaintenaceSchedule) entityManager.createQuery("SELECT S FROM MaintenaceSchedule S  where S.id="+scheduleId).getSingleResult();
							
			ManageScheduleVO schedule = new ManageScheduleVO();
			schedule.setId(s.getId());			
			schedule.setStartDate((s.getStartDate()));
			schedule.setEnddate((s.getEndDate()));
			schedule.setTitle((s.getTitle()));
			schedule.setStartTime(s.getStartTime());
			schedule.setEndTime(s.getEndTime());
			schedule.setOrganization((s.getOrganization()));
			schedule.setBranch((s.getBranch()));
			
			if(Long.parseLong(s.getEmpCode())==0)
			{
				schedule.setEmployeeCode("--NA--");
				schedule.setEmployeeName("--NA--");
			}
			else
			{
			schedule.setEmployeeCode(s.getEmpCode());
			long empid = Long.parseLong(s.getEmpCode());			
			Employees employee =  entityManager.find(Employees.class,empid);			
			schedule.setEmployeeName(employee.getFirstName()+" "+employee.getLastName());						
	}
			return schedule;
}
	
	@Override
	@Transactional
	
	public String DeleteScheduleById(int id)
		{
			
		MaintenaceSchedule schedule = entityManager.find(MaintenaceSchedule.class, (long)id);
			if (schedule != null)
				{					
					entityManager.remove(schedule);
					return "entity updated successfully";
					} 
			    else
				return "Entity does not exist";
		}

	@Transactional
	@Override
	public boolean UpdateSchedule(EditScheduleForm form) {
		try {
			
			MaintenaceSchedule scheduleInstance = entityManager.find(MaintenaceSchedule.class, form.getSid());
			
			scheduleInstance.setTitle(form.getTitle());
			scheduleInstance.setStartTime(form.getStartTime().toString());
			scheduleInstance.setEndTime(form.getEndTime().toString());
			//should date also be modified , must ask 
			//scheduleInstance.setStartDate(Date.parseDate(form.getStartDate()));
			//scheduleInstance.setEndDate(Date.parseDate(form.getEnddate()));
		    scheduleInstance.setOrganization(form.getOrganization());
			scheduleInstance.setBranch(form.getBranch());
			scheduleInstance.setEmpCode(form.getEmpCode());
			entityManager.merge(scheduleInstance);
			
			return true;
			}
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	
	}


	
	
	}
