package com.ssrv.fms.vo.employee;

import java.math.BigInteger;
import java.util.Date;

public class EmployeeFamilyDetailsForm
	{
		private long id;
		
		String name;
		
		long relationshipId;
		
		long employeeId;
		
		int isDeleted;
		
		BigInteger modifiedBy;
		
		Date modifiedOn;

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

		public long getRelationshipId()
			{
				return relationshipId;
			}

		public void setRelationshipId(long relationshipId)
			{
				this.relationshipId = relationshipId;
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

		public BigInteger getModifiedBy()
			{
				return modifiedBy;
			}

		public void setModifiedBy(BigInteger modifiedBy)
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
