package com.ssrv.fms.model.shift;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "shiftstemplate")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Shiftstemplate.findAll", query = "SELECT s FROM Shiftstemplate s"),
		@NamedQuery(name = "Shiftstemplate.findById", query = "SELECT s FROM Shiftstemplate s WHERE s.id = :id"),
		@NamedQuery(name = "Shiftstemplate.findByShiftName", query = "SELECT s FROM Shiftstemplate s WHERE s.shiftName = :shiftName"),
		@NamedQuery(name = "Shiftstemplate.findByStartTime", query = "SELECT s FROM Shiftstemplate s WHERE s.startTime = :startTime"),
		@NamedQuery(name = "Shiftstemplate.findByEndTime", query = "SELECT s FROM Shiftstemplate s WHERE s.endTime = :endTime"),
		@NamedQuery(name = "Shiftstemplate.findByIsDeleted", query = "SELECT s FROM Shiftstemplate s WHERE s.isDeleted = :isDeleted"),
		@NamedQuery(name = "Shiftstemplate.findByModifiedOn", query = "SELECT s FROM Shiftstemplate s WHERE s.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Shiftstemplate.findByModifiedBy", query = "SELECT s FROM Shiftstemplate s WHERE s.modifiedBy = :modifiedBy") })
public class Shiftstemplate implements Serializable
	{
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Basic(optional = false)
		@Column(name = "Id")
		private Long id;

		@Column(name = "ShiftName")
		private String shiftName;
		
		@Column(name = "StartTime")
		@Temporal(TemporalType.TIMESTAMP)
		private Date startTime;
		
		@Column(name = "EndTime")
		@Temporal(TemporalType.TIMESTAMP)
		private Date endTime;
		
		@Column(name = "IsDeleted")
		private int isDeleted;
		
		@Column(name = "ModifiedOn")
		@Temporal(TemporalType.TIMESTAMP)
		private Date modifiedOn;
		
		@Column(name = "ModifiedBy")
		private String modifiedBy;

		public Shiftstemplate()
			{
			}

		public Shiftstemplate(Long id)
			{
				this.id = id;
			}

		public Long getId()
			{
				return id;
			}

		public void setId(Long id)
			{
				this.id = id;
			}

		public String getShiftName()
			{
				return shiftName;
			}

		public void setShiftName(String shiftName)
			{
				this.shiftName = shiftName;
			}

		public Date getStartTime()
			{
				return startTime;
			}

		public void setStartTime(Date startTime)
			{
				this.startTime = startTime;
			}

		public Date getEndTime()
			{
				return endTime;
			}

		public void setEndTime(Date endTime)
			{
				this.endTime = endTime;
			}

		public int getIsDeleted()
			{
				return isDeleted;
			}

		public void setIsDeleted(int i)
			{
				this.isDeleted = i;
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

		public void setModifiedBy(String string)
			{
				this.modifiedBy = string;
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
				// TODO: Warning - this method won't work in the case the id
				// fields are not set
				if (!(object instanceof Shiftstemplate))
					{
						return false;
					}
				Shiftstemplate other = (Shiftstemplate) object;
				if ((this.id == null && other.id != null)
						|| (this.id != null && !this.id.equals(other.id)))
					{
						return false;
					}
				return true;
			}

		@Override
		public String toString()
			{
				return "jpa.Shiftstemplate[ id=" + id + " ]";
			}

	}
