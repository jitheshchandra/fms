package com.ssrv.fms.model.maintenance;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.managedentity.ManagedEntityInstance;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "maintenanceinstance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenanceinstance.findAll", query = "SELECT m FROM Maintenanceinstance m"),
    @NamedQuery(name = "Maintenanceinstance.findById", query = "SELECT m FROM Maintenanceinstance m WHERE m.id = :id"),
    @NamedQuery(name = "Maintenanceinstance.findByPlannedStartsTime", query = "SELECT m FROM Maintenanceinstance m WHERE m.plannedStartsTime = :plannedStartsTime"),
    @NamedQuery(name = "Maintenanceinstance.findByActualStartTime", query = "SELECT m FROM Maintenanceinstance m WHERE m.actualStartTime = :actualStartTime"),
    @NamedQuery(name = "Maintenanceinstance.findByCheckListItemId", query = "SELECT m FROM Maintenanceinstance m WHERE m.checkListItemId = :checkListItemId"),
    @NamedQuery(name = "Maintenanceinstance.findByRemarks", query = "SELECT m FROM Maintenanceinstance m WHERE m.remarks = :remarks")})
public class Maintenanceinstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "PlannedStartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedStartsTime;
    
    @Column(name = "ActualStartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualStartTime;
    
    @Column(name = "CheckListId")
    private BigInteger checkListItemId;
    @Column(name = "Remarks")
    private String remarks;
    
    @OneToMany(mappedBy = "maintenanceInstanceId")
    private Collection<Maintenanceinstancedetails> maintenanceinstancedetailsCollection;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityInstance facilityId;

    public Maintenanceinstance() {
    }

    public Maintenanceinstance(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlannedStartsTime() {
        return plannedStartsTime;
    }

    public void setPlannedStartsTime(Date plannedStartsTime) {
        this.plannedStartsTime = plannedStartsTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public BigInteger getCheckListItemId() {
        return checkListItemId;
    }

    public void setCheckListItemId(BigInteger checkListItemId) {
        this.checkListItemId = checkListItemId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlTransient
    public Collection<Maintenanceinstancedetails> getMaintenanceinstancedetailsCollection() {
        return maintenanceinstancedetailsCollection;
    }

    public void setMaintenanceinstancedetailsCollection(Collection<Maintenanceinstancedetails> maintenanceinstancedetailsCollection) {
        this.maintenanceinstancedetailsCollection = maintenanceinstancedetailsCollection;
    }

    public ManagedEntityInstance getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(ManagedEntityInstance facilityId) {
        this.facilityId = facilityId;
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
        if (!(object instanceof Maintenanceinstance)) {
            return false;
        }
        Maintenanceinstance other = (Maintenanceinstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Maintenanceinstance[ id=" + id + " ]";
    }
    
}
