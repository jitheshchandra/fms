package com.ssrv.fms.vo.state;

import java.sql.Date;

import com.ssrv.fms.model.contact.States;
// done.....jit
public class StateForm
	{
		public long id;

		public long cntrId;

		public String name;
		
		public Short isDeleted;
		
		public Date modifiedOn;
		
		public String modifiedBy;
		
		public StateForm() {
			// TODO Auto-generated constructor stub
		}
		
		public StateForm(States modelData)
		{
			this.id=modelData.getId();
			this.cntrId=modelData.getCountryId().getId();
			this.name=modelData.getName();
		}

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

		public Short getIsDeleted()
			{
				return isDeleted;
			}

		public void setIsDeleted(Short isDeleted)
			{
				this.isDeleted = isDeleted;
			}

		public Date getModifiedOn()
			{
				return modifiedOn;
			}

		public void setModifiedOn(Date modifiedOn)
			{
				this.modifiedOn = modifiedOn;
			}

		public String getModifiedBy()
			{
				return modifiedBy;
			}

		public void setModifiedBy(String modifiedBy)
			{
				this.modifiedBy = modifiedBy;
			}

		public long getCntrId()
			{
				return cntrId;
			}

		public void setCntrId(long cntrId)
			{
				this.cntrId = cntrId;
			}

		
	}
