package com.ssrv.fms.model.employee;

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

import com.ssrv.fms.model.checklist.Checklists;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "employeedocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeedocuments.findAll", query = "SELECT e FROM Employeedocuments e"),
    @NamedQuery(name = "Employeedocuments.findById", query = "SELECT e FROM Employeedocuments e WHERE e.id = :id"),
  //  @NamedQuery(name = "Employeedocuments.findByReceiveStatus", query = "SELECT e FROM Employeedocuments e WHERE e.receiveStatus = :receiveStatus"),
    @NamedQuery(name = "Employeedocuments.findByReceivedOn", query = "SELECT e FROM Employeedocuments e WHERE e.receivedOn = :receivedOn"),
    @NamedQuery(name = "Employeedocuments.findByModifiedOn", query = "SELECT e FROM Employeedocuments e WHERE e.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Employeedocuments.findByModifiedBy", query = "SELECT e FROM Employeedocuments e WHERE e.modifiedBy = :modifiedBy")})
public class Employeedocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
 /*   @Column(name = "ReceiveStatus")
    private Short receiveStatus;*/
    @Column(name = "ReceivedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedOn;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @JoinColumn(name = "CheckListId", referencedColumnName = "Id")
    @ManyToOne
    private Checklists checkListId;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Employees employeeId;

    public Employeedocuments() {
    }

    public Employeedocuments(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  public Short getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Short receiveStatus) {
        this.receiveStatus = receiveStatus;
    }*/

    public Date getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
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

    public Checklists getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(Checklists checkListId) {
        this.checkListId = checkListId;
    }

    public Employees getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employees employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Employeedocuments)) {
            return false;
        }
        Employeedocuments other = (Employeedocuments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Employeedocuments[ id=" + id + " ]";
    }
    
}
