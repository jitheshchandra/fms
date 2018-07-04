package com.ssrv.fms.model.job;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
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

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.shift.Shifts;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "jobmovement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobmovements.findAll", query = "SELECT j FROM Jobmovements j"),
    @NamedQuery(name = "Jobmovements.findById", query = "SELECT j FROM Jobmovements j WHERE j.id = :id"),
    @NamedQuery(name = "Jobmovements.findByRemarks", query = "SELECT j FROM Jobmovements j WHERE j.remarks = :remarks"),
    @NamedQuery(name = "Jobmovements.findByModifiedOn", query = "SELECT j FROM Jobmovements j WHERE j.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Jobmovements.findByModifiedBy", query = "SELECT j FROM Jobmovements j WHERE j.modifiedBy = :modifiedBy")})
public class Jobmovements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    @JoinColumn(name = "ToDesignationId", referencedColumnName = "Id")
    @ManyToOne
    private Designations toDesignationId;
    @JoinColumn(name = "FromDesignationId", referencedColumnName = "Id")
    @ManyToOne
    private Designations fromDesignationId;
    @JoinColumn(name = "ToShiftId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Shifts toShiftId;
    @JoinColumn(name = "FromShiftId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Shifts fromShiftId;
    @JoinColumn(name = "ToOrganizationID", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Organization toOrganizationID;
    @JoinColumn(name = "FromOrganizationId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Organization fromOrganizationId;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Employees employeeId;

    public Jobmovements() {
    }

    public Jobmovements(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Designations getToDesignationId() {
        return toDesignationId;
    }

    public void setToDesignationId(Designations toDesignationId) {
        this.toDesignationId = toDesignationId;
    }

    public Designations getFromDesignationId() {
        return fromDesignationId;
    }

    public void setFromDesignationId(Designations fromDesignationId) {
        this.fromDesignationId = fromDesignationId;
    }

    public Shifts getToShiftId() {
        return toShiftId;
    }

    public void setToShiftId(Shifts toShiftId) {
        this.toShiftId = toShiftId;
    }

    public Shifts getFromShiftId() {
        return fromShiftId;
    }

    public void setFromShiftId(Shifts fromShiftId) {
        this.fromShiftId = fromShiftId;
    }

    public Organization getToOrganizationID() {
        return toOrganizationID;
    }

    public void setToOrganizationID(Organization toOrganizationID) {
        this.toOrganizationID = toOrganizationID;
    }

    public Organization getFromOrganizationId() {
        return fromOrganizationId;
    }

    public void setFromOrganizationId(Organization fromOrganizationId) {
        this.fromOrganizationId = fromOrganizationId;
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
        if (!(object instanceof Jobmovements)) {
            return false;
        }
        Jobmovements other = (Jobmovements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Jobmovements[ id=" + id + " ]";
    }
    
}
