package com.ssrv.fms.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.employee.Employees;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "grooming")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groomings.findAll", query = "SELECT g FROM Groomings g"),
    @NamedQuery(name = "Groomings.findById", query = "SELECT g FROM Groomings g WHERE g.id = :id"),
    @NamedQuery(name = "Groomings.findByEmployeeId", query = "SELECT g FROM Groomings g WHERE g.employeeId = :employeeId"),
    @NamedQuery(name = "Groomings.findByGroomingDay", query = "SELECT g FROM Groomings g WHERE g.groomingDay = :groomingDay"),
    @NamedQuery(name = "Groomings.findByCheckListResult", query = "SELECT g FROM Groomings g WHERE g.checkListResult = :checkListResult"),
    @NamedQuery(name = "Groomings.findByModifiedOn", query = "SELECT g FROM Groomings g WHERE g.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Groomings.findByModifiedBy", query = "SELECT g FROM Groomings g WHERE g.modifiedBy = :modifiedBy")})
public class Groomings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
   
    @Basic(optional = false)
    @Column(name = "EmployeeId")
    private long employeeId;

    @Column(name = "GroomingDay")
    @Temporal(TemporalType.TIMESTAMP)
    private Date groomingDay;
    
    @Basic(optional = false)
    @Column(name = "CheckListResult")
    private String checkListResult;
   
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    
    @JoinColumn(name = "CheckListId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Checklists checkListId;
     
    @JoinColumn(name = "EmployeeId", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Employees employees;

    public Groomings() {
    }

    public Groomings(Long id) {
        this.id = id;
    }

    public Groomings(Long id, long employeeId, String checkListResult) {
        this.id = id;
        this.employeeId = employeeId;
        this.checkListResult = checkListResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getGroomingDay() {
        return groomingDay;
    }

    public void setGroomingDay(Date groomingDay) {
        this.groomingDay = groomingDay;
    }

    public String getCheckListResult() {
        return checkListResult;
    }

    public void setCheckListResult(String checkListResult) {
        this.checkListResult = checkListResult;
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

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
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
        if (!(object instanceof Groomings)) {
            return false;
        }
        Groomings other = (Groomings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Groomings[ id=" + id + " ]";
    }
    
}
