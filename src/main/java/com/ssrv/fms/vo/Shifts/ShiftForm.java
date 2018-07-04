package com.ssrv.fms.vo.Shifts;

import java.util.List;

import com.ssrv.fms.model.shift.Shifts;

public class ShiftForm
	{
		String name;
		String startTime;
		String endTime;
		private long id;
		long organizationId;
		long branchId;
		List<ShiftSummaryForm> shiftSummary;

		public ShiftForm()
		{
			
		}
		public ShiftForm(Shifts shift)
		{
			this.id=shift.getId();
			this.name=shift.getShiftName();
			this.startTime=shift.getStartTime();
			this.endTime=shift.getEndTime();
		}
		
		public long getId()
			{
				return id;
			}

		public void setId(long id)
			{
				this.id = id;
			}

		public long getOrganizationId()
			{
				return organizationId;
			}

		public void setOrganizationId(long organizationId)
			{
				this.organizationId = organizationId;
			}

		public long getBranchId()
			{
				return branchId;
			}

		public void setBranchId(long branchId)
			{
				this.branchId = branchId;
			}

		public List<ShiftSummaryForm> getShiftSummary()
			{
				return shiftSummary;
			}

		public void setShiftSummary(List<ShiftSummaryForm> shiftSummary)
			{
				this.shiftSummary = shiftSummary;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String getStartTime()
			{
				return startTime;
			}

		public void setStartTime(String startTime)
			{
				this.startTime = startTime;
			}

		public String getEndTime()
			{
				return endTime;
			}

		public void setEndTime(String endTime)
			{
				this.endTime = endTime;
			}
		
			

	}
