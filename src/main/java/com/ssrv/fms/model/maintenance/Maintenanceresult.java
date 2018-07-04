package com.ssrv.fms.model.maintenance;

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

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "maintenanceresult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenanceresult.findAll", query = "SELECT m FROM Maintenanceresult m"),
    @NamedQuery(name = "Maintenanceresult.findById", query = "SELECT m FROM Maintenanceresult m WHERE m.id = :id"),
    @NamedQuery(name = "Maintenanceresult.findByCheckListItemId", query = "SELECT m FROM Maintenanceresult m WHERE m.checkListItemId = :checkListItemId"),
    @NamedQuery(name = "Maintenanceresult.findByResult", query = "SELECT m FROM Maintenanceresult m WHERE m.result = :result")})
public class Maintenanceresult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "CheckListItemId")
    private BigInteger checkListItemId;
    @Column(name = "Result")
    private String result;
    @JoinColumn(name = "MaintenanceInstanceDtlsId", referencedColumnName = "Id")
    @ManyToOne
    private Maintenanceinstancedetails maintenanceInstanceDtlsId;

    public Maintenanceresult() {
    }

    public Maintenanceresult(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getCheckListItemId() {
        return checkListItemId;
    }

    public void setCheckListItemId(BigInteger checkListItemId) {
        this.checkListItemId = checkListItemId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Maintenanceinstancedetails getMaintenanceInstanceDtlsId() {
        return maintenanceInstanceDtlsId;
    }

    public void setMaintenanceInstanceDtlsId(Maintenanceinstancedetails maintenanceInstanceDtlsId) {
        this.maintenanceInstanceDtlsId = maintenanceInstanceDtlsId;
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
        if (!(object instanceof Maintenanceresult)) {
            return false;
        }
        Maintenanceresult other = (Maintenanceresult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Maintenanceresult[ id=" + id + " ]";
    }
    
}
