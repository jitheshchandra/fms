package com.ssrv.fms.model.employee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.ssrv.fms.model.shift.Shifts;

//@Entity
//@Table(name = "employeeovertime")
public class Employeeovertime implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name="NoOfHours")
	private Float noOfHours;
	
	@Column(name="OverTimeDate")
	private Date overTimeDate;
	
	@Column(name="ModifiedOn")
	private Date modifiedOn;
	
	@Column(name="ModifiedBy")
	private Long modifiedBy;

    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne
    private Shifts shiftId;
        
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne
    private Employees employeeId;
    
    
    public Date getModifiedOn()
		{
			return modifiedOn;
		}

	public void setModifiedOn(Date modifiedOn)
		{
			this.modifiedOn = modifiedOn;
		}
	public Long getModifiedBy()
		{
			return modifiedBy;
		}

	public void setModifiedBy(Long modifiedBy)
		{
			this.modifiedBy = modifiedBy;
		}

	public Employeeovertime()
    {
    	
    }
    
    public Employeeovertime(Long id)
    {
    	this.id=id;
    }

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Float getNoOfHours()
	{
		return noOfHours;
	}

	public void setNoOfHours(Float noOfHours)
	{
		this.noOfHours = noOfHours;
	}

	public Date getOverTimeDate()
	{
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate)
	{
		this.overTimeDate = overTimeDate;
	}

	public Shifts getShiftId()
	{
		return shiftId;
	}

	public void setShiftId(Shifts shiftId)
	{
		this.shiftId = shiftId;
	}

	public Employees getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(Employees employeeId)
	{
		this.employeeId = employeeId;
	}
    
	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Employeeattendence))
		{
			return false;
		}
		Employeeovertime other = (Employeeovertime) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "jpa.Employeeovertime[ id=" + id + " ]";
	}

}
