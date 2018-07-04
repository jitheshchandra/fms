package com.ssrv.fms.model.managedentity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.shift.Shifts;

/**
 *
 * @author PAMS
 */
//@Entity
//@Table(name = "managedentitygroupemployeemap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Managedentitygroupemployeemap.findAll", query = "SELECT m FROM Managedentitygroupemployeemap m"),
    @NamedQuery(name = "Managedentitygroupemployeemap.findById", query = "SELECT m FROM Managedentitygroupemployeemap m WHERE m.id = :id"),
    @NamedQuery(name = "Managedentitygroupemployeemap.findBySupervisorId", query = "SELECT m FROM Managedentitygroupemployeemap m WHERE m.supervisorId = :supervisorId"),
    @NamedQuery(name = "Managedentitygroupemployeemap.findByExecutiveId", query = "SELECT m FROM Managedentitygroupemployeemap m WHERE m.executiveId = :executiveId")})
public class Managedentitygroupemployeemap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "SupervisorId")
    private BigInteger supervisorId;
    @Column(name = "ExecutiveId")
    private BigInteger executiveId;
    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne
    private Shifts shiftId;
    @JoinColumn(name = "FacilityGroupId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityGroup facilityGroupId;

    public Managedentitygroupemployeemap() {
    }

    public Managedentitygroupemployeemap(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(BigInteger supervisorId) {
        this.supervisorId = supervisorId;
    }

    public BigInteger getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(BigInteger executiveId) {
        this.executiveId = executiveId;
    }

    public Shifts getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shifts shiftId) {
        this.shiftId = shiftId;
    }

    public ManagedEntityGroup getFacilityGroupId() {
        return facilityGroupId;
    }

    public void setFacilityGroupId(ManagedEntityGroup facilityGroupId) {
        this.facilityGroupId = facilityGroupId;
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
        if (!(object instanceof Managedentitygroupemployeemap)) {
            return false;
        }
        Managedentitygroupemployeemap other = (Managedentitygroupemployeemap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Managedentitygroupemployeemap[ id=" + id + " ]";
    }
    
}
