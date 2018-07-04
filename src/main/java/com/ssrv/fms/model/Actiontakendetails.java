package com.ssrv.fms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.maintenance.Maintenanceinstancedetails;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "actiontakendetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actiontakendetails.findAll", query = "SELECT a FROM Actiontakendetails a"),
    @NamedQuery(name = "Actiontakendetails.findById", query = "SELECT a FROM Actiontakendetails a WHERE a.id = :id"),
    @NamedQuery(name = "Actiontakendetails.findByAbnormalityIdentifiedDate", query = "SELECT a FROM Actiontakendetails a WHERE a.abnormalityIdentifiedDate = :abnormalityIdentifiedDate"),
    @NamedQuery(name = "Actiontakendetails.findByAbnormality", query = "SELECT a FROM Actiontakendetails a WHERE a.abnormality = :abnormality"),
    @NamedQuery(name = "Actiontakendetails.findByActionTaken", query = "SELECT a FROM Actiontakendetails a WHERE a.actionTaken = :actionTaken"),
    @NamedQuery(name = "Actiontakendetails.findByRemarks", query = "SELECT a FROM Actiontakendetails a WHERE a.remarks = :remarks"),
    @NamedQuery(name = "Actiontakendetails.findByModifiedBy", query = "SELECT a FROM Actiontakendetails a WHERE a.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Actiontakendetails.findByModifiedDate", query = "SELECT a FROM Actiontakendetails a WHERE a.modifiedDate = :modifiedDate")})
public class Actiontakendetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "AbnormalityIdentifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abnormalityIdentifiedDate;
    @Column(name = "Abnormality")
    private String abnormality;
    @Column(name = "ActionTaken")
    private String actionTaken;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "MaintenanceInstanceDetailsId", referencedColumnName = "Id")
    @ManyToOne
    private Maintenanceinstancedetails maintenanceInstanceDetailsId;

    public Actiontakendetails() {
    }

    public Actiontakendetails(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAbnormalityIdentifiedDate() {
        return abnormalityIdentifiedDate;
    }

    public void setAbnormalityIdentifiedDate(Date abnormalityIdentifiedDate) {
        this.abnormalityIdentifiedDate = abnormalityIdentifiedDate;
    }

    public String getAbnormality() {
        return abnormality;
    }

    public void setAbnormality(String abnormality) {
        this.abnormality = abnormality;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Maintenanceinstancedetails getMaintenanceInstanceDetailsId() {
        return maintenanceInstanceDetailsId;
    }

    public void setMaintenanceInstanceDetailsId(Maintenanceinstancedetails maintenanceInstanceDetailsId) {
        this.maintenanceInstanceDetailsId = maintenanceInstanceDetailsId;
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
        if (!(object instanceof Actiontakendetails)) {
            return false;
        }
        Actiontakendetails other = (Actiontakendetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Actiontakendetails[ id=" + id + " ]";
    }
    
}
