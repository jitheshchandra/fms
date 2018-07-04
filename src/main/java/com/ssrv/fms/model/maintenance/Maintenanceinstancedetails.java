package com.ssrv.fms.model.maintenance;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.ssrv.fms.model.Actiontakendetails;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "maintenanceinstancedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenanceinstancedetails.findAll", query = "SELECT m FROM Maintenanceinstancedetails m"),
    @NamedQuery(name = "Maintenanceinstancedetails.findById", query = "SELECT m FROM Maintenanceinstancedetails m WHERE m.id = :id"),
    @NamedQuery(name = "Maintenanceinstancedetails.findByMaintenanceDate", query = "SELECT m FROM Maintenanceinstancedetails m WHERE m.maintenanceDate = :maintenanceDate"),
    @NamedQuery(name = "Maintenanceinstancedetails.findBySupervisedDate", query = "SELECT m FROM Maintenanceinstancedetails m WHERE m.supervisedDate = :supervisedDate")})
public class Maintenanceinstancedetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "MaintenanceDate")
    @Temporal(TemporalType.DATE)
    private Date maintenanceDate;
    @Column(name = "SupervisedDate")
    @Temporal(TemporalType.DATE)
    private Date supervisedDate;
    @OneToMany(mappedBy = "maintenanceInstanceDtlsId")
    private Collection<Maintenanceresult> maintenanceresultCollection;
    @OneToMany(mappedBy = "maintenanceInstanceDetailsId")
    private Collection<Actiontakendetails> actiontakendetailsCollection;
    @JoinColumn(name = "MaintenanceInstanceId", referencedColumnName = "Id")
    @ManyToOne
    private Maintenanceinstance maintenanceInstanceId;

    public Maintenanceinstancedetails() {
    }

    public Maintenanceinstancedetails(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public Date getSupervisedDate() {
        return supervisedDate;
    }

    public void setSupervisedDate(Date supervisedDate) {
        this.supervisedDate = supervisedDate;
    }

    @XmlTransient
    public Collection<Maintenanceresult> getMaintenanceresultCollection() {
        return maintenanceresultCollection;
    }

    public void setMaintenanceresultCollection(Collection<Maintenanceresult> maintenanceresultCollection) {
        this.maintenanceresultCollection = maintenanceresultCollection;
    }

    @XmlTransient
    public Collection<Actiontakendetails> getActiontakendetailsCollection() {
        return actiontakendetailsCollection;
    }

    public void setActiontakendetailsCollection(Collection<Actiontakendetails> actiontakendetailsCollection) {
        this.actiontakendetailsCollection = actiontakendetailsCollection;
    }

    public Maintenanceinstance getMaintenanceInstanceId() {
        return maintenanceInstanceId;
    }

    public void setMaintenanceInstanceId(Maintenanceinstance maintenanceInstanceId) {
        this.maintenanceInstanceId = maintenanceInstanceId;
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
        if (!(object instanceof Maintenanceinstancedetails)) {
            return false;
        }
        Maintenanceinstancedetails other = (Maintenanceinstancedetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Maintenanceinstancedetails[ id=" + id + " ]";
    }
    
}
