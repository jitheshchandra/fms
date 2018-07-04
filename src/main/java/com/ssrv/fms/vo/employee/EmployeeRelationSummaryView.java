package com.ssrv.fms.vo.employee;

public class EmployeeRelationSummaryView
	{
		public long id;
		public String name;
		public String relationShipType;
		public String employeeName;
		public long employeeId;
		public String getEmployeeName()
			{
				return employeeName;
			}
		public void setEmployeeName(String employeeName)
			{
				this.employeeName = employeeName;
			}
		public long getEmployeeId()
			{
				return employeeId;
			}
		public void setEmployeeId(long employeeId)
			{
				this.employeeId = employeeId;
			}
		public String getName()
			{
				return name;
			}
		public void setName(String name)
			{
				this.name = name;
			}
		public String getRelationShipType()
			{
				return relationShipType;
			}
		public void setRelationShipType(String relationShipType)
			{
				this.relationShipType = relationShipType;
			}
		public long getId()
			{
				return id;
			}
		public void setId(long id)
			{
				this.id = id;
			}
		
	}
