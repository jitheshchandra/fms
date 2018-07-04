package com.ssrv.fms.vo.employee;

import java.util.Date;

public class EmployeeRelationSummary
	{
		private long id;
		
		public String name;
		
		public long relationshipName;
		
		public long  employeeId;
		
		public int isDeleted;
		
		public long modifiedBy;
		
		public Date modifiedOn;

		public long getId()
			{
				return id;
			}

		public void setId(long id)
			{
				this.id = id;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public long getRelationshipName()
			{
				return relationshipName;
			}

		public void setRelationshipName(long relationshipName)
			{
				this.relationshipName = relationshipName;
			}

		public long getEmployeeId()
			{
				return employeeId;
			}

		public void setEmployeeId(long employeeId)
			{
				this.employeeId = employeeId;
			}

		public int getIsDeleted()
			{
				return isDeleted;
			}

		public void setIsDeleted(int isDeleted)
			{
				this.isDeleted = isDeleted;
			}

		public long getModifiedBy()
			{
				return modifiedBy;
			}

		public void setModifiedBy(long modifiedBy)
			{
				this.modifiedBy = modifiedBy;
			}

		public Date getModifiedOn()
			{
				return modifiedOn;
			}

		public void setModifiedOn(Date modifiedOn)
			{
				this.modifiedOn = modifiedOn;
			}
		
	}
