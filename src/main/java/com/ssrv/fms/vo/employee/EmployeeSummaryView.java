package com.ssrv.fms.vo.employee;

public class EmployeeSummaryView
	{

		public long id;

		public String empCode;

		public String empFirstName;

		public String empDesignation;

		public String empSupervisor;

		public String empDOB;

		public long getId()
			{
				return id;
			}

		public void setId(long id)
			{
				this.id = id;
			}

		public String getEmpCode()
			{
				return empCode;
			}

		public void setEmpCode(String empCode)
			{
				this.empCode = empCode;
			}

		public String getEmpFirstName()
			{
				return empFirstName;
			}

		public void setEmpFirstName(String empFirstName)
			{
				this.empFirstName = empFirstName;
			}

		public String getEmpDesignation()
			{
				return empDesignation;
			}

		public void setEmpDesignation(String empDesignation)
			{
				this.empDesignation = empDesignation;
			}

		public String getEmpSupervisor()
			{
				return empSupervisor;
			}

		public void setEmpSupervisor(String empSupervisor)
			{
				this.empSupervisor = empSupervisor;
			}

		public String getEmpDOB()
			{
				return empDOB;
			}

		public void setEmpDOB(String empDOB)
			{
				this.empDOB = empDOB;
			}

	}
