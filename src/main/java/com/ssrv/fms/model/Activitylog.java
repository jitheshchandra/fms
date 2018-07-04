package com.ssrv.fms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "activitylog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activitylog.findAll", query = "SELECT a FROM Activitylog a"),
    @NamedQuery(name = "Activitylog.findById", query = "SELECT a FROM Activitylog a WHERE a.id = :id"),
    @NamedQuery(name = "Activitylog.findByActivityDate", query = "SELECT a FROM Activitylog a WHERE a.activityDate = :activityDate"),
    @NamedQuery(name = "Activitylog.findByActivityTime", query = "SELECT a FROM Activitylog a WHERE a.activityTime = :activityTime"),
    @NamedQuery(name = "Activitylog.findByEquipmentId", query = "SELECT a FROM Activitylog a WHERE a.equipmentId = :equipmentId"),
    @NamedQuery(name = "Activitylog.findByMaintenanceCompleted", query = "SELECT a FROM Activitylog a WHERE a.maintenanceCompleted = :maintenanceCompleted"),
    @NamedQuery(name = "Activitylog.findByTechnicianId", query = "SELECT a FROM Activitylog a WHERE a.technicianId = :technicianId"),
    @NamedQuery(name = "Activitylog.findByEscalated", query = "SELECT a FROM Activitylog a WHERE a.escalated = :escalated"),
    @NamedQuery(name = "Activitylog.findByEscalatedTo", query = "SELECT a FROM Activitylog a WHERE a.escalatedTo = :escalatedTo")})
public class Activitylog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "ActivityDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityDate;
    @Column(name = "ActivityTime")
    private String activityTime;
    @Column(name = "EquipmentId")
    private BigInteger equipmentId;
    @Column(name = "MaintenanceCompleted")
    private Short maintenanceCompleted;
    @Column(name = "TechnicianId")
    private BigInteger technicianId;
    @Column(name = "Escalated")
    private Short escalated;
    @Column(name = "EscalatedTo")
    private BigInteger escalatedTo;

    public Activitylog() {
    }

    public Activitylog(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public BigInteger getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(BigInteger equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Short getMaintenanceCompleted() {
        return maintenanceCompleted;
    }

    public void setMaintenanceCompleted(Short maintenanceCompleted) {
        this.maintenanceCompleted = maintenanceCompleted;
    }

    public BigInteger getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(BigInteger technicianId) {
        this.technicianId = technicianId;
    }

    public Short getEscalated() {
        return escalated;
    }

    public void setEscalated(Short escalated) {
        this.escalated = escalated;
    }

    public BigInteger getEscalatedTo() {
        return escalatedTo;
    }

    public void setEscalatedTo(BigInteger escalatedTo) {
        this.escalatedTo = escalatedTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activitylog)) {
            return false;
        }
        Activitylog other = (Activitylog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Activitylog[ id=" + id + " ]";
    }
    
}
