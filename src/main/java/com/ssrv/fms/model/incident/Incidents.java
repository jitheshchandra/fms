package com.ssrv.fms.model.incident;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.managedentity.ManagedEntityGroup;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "incident")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Incidents.findAll", query = "SELECT i FROM Incidents i"),
		@NamedQuery(name = "Incidents.findById", query = "SELECT i FROM Incidents i WHERE i.id = :id"),
		@NamedQuery(name = "Incidents.findByIncidentDate", query = "SELECT i FROM Incidents i WHERE i.incidentDate = :incidentDate"),
		@NamedQuery(name = "Incidents.findByIncidentTime", query = "SELECT i FROM Incidents i WHERE i.incidentTime = :incidentTime"),
		@NamedQuery(name = "Incidents.findByFacilityId", query = "SELECT i FROM Incidents i WHERE i.managedEntity = :facilityId"),
		@NamedQuery(name = "Incidents.findByRemarks", query = "SELECT i FROM Incidents i WHERE i.remarks = :remarks"),
		@NamedQuery(name = "Incidents.findByStatus", query = "SELECT i FROM Incidents i WHERE i.status = :status"),
		@NamedQuery(name = "Incidents.findByResolvedOn", query = "SELECT i FROM Incidents i WHERE i.resolvedOn = :resolvedOn"),
		@NamedQuery(name = "Incidents.findByResolvedRemarks", query = "SELECT i FROM Incidents i WHERE i.resolvedRemarks = :resolvedRemarks"),
		@NamedQuery(name = "Incidents.findByModifiedOn", query = "SELECT i FROM Incidents i WHERE i.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Incidents.findByModifiedBy", query = "SELECT i FROM Incidents i WHERE i.modifiedBy = :modifiedBy") })
public class Incidents implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "INCIDENTDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date incidentDate;
	@Column(name = "INCIDENTTIME")
	private String incidentTime;

	@JoinColumn(name = "FACILITYID", referencedColumnName = "Id")
	@ManyToOne
	private ManagedEntityGroup managedEntity;

	@Column(name = "REMARKS")
	private String remarks;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "RESOLVEDON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolvedOn;
	@Column(name = "RESOLVEDREMARKS")
	private String resolvedRemarks;
	@Column(name = "MODIFIEDON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	@Column(name = "MODIFIEDBY")
	private BigInteger modifiedBy;
	@JoinColumn(name = "EscalationLevelId", referencedColumnName = "Id")
	@ManyToOne
	private EscalationMatrix escalationMatrix;
	
	@JoinColumn(name = "INCIDENTTYPEID", referencedColumnName = "Id")
	@ManyToOne
	private Incidentcategory incidentType;

	public Incidents() {
	}

	public Incidents(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getIncidentTime() {
		return incidentTime;
	}

	public void setIncidentTime(String incidentTime) {
		this.incidentTime = incidentTime;
	}

	public ManagedEntityGroup getManagedEntity() {
		return managedEntity;
	}

	public void setManagedEntity(ManagedEntityGroup managedEntity) {
		this.managedEntity = managedEntity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getResolvedOn() {
		return resolvedOn;
	}

	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}

	public String getResolvedRemarks() {
		return resolvedRemarks;
	}

	public void setResolvedRemarks(String resolvedRemarks) {
		this.resolvedRemarks = resolvedRemarks;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public EscalationMatrix getEscalationMatrix() {
		return escalationMatrix;
	}

	public void setEscalationMatrix(EscalationMatrix escalationMatrix) {
		this.escalationMatrix = escalationMatrix;
	}

	public Incidentcategory getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(Incidentcategory incidentType) {
		this.incidentType = incidentType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Incidents)) {
			return false;
		}
		Incidents other = (Incidents) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Incidents[ id=" + id + " ]";
	}

}
