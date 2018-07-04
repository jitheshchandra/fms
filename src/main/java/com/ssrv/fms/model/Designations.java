package com.ssrv.fms.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.job.Jobmovements;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "designation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Designations.findAll", query = "SELECT d FROM Designations d"),
    @NamedQuery(name = "Designations.findById", query = "SELECT d FROM Designations d WHERE d.id = :id"),
    @NamedQuery(name = "Designations.findByName", query = "SELECT d FROM Designations d WHERE d.name = :name"),
    @NamedQuery(name = "Designations.findByIsDeleted", query = "SELECT d FROM Designations d WHERE d.isDeleted = :isDeleted"),
    @NamedQuery(name = "Designations.findByModifiedOn", query = "SELECT d FROM Designations d WHERE d.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Designations.findByModifiedBy", query = "SELECT d FROM Designations d WHERE d.modifiedBy = :modifiedBy")})
public class Designations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "IsDeleted")
    private int isDeleted;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @OneToMany(mappedBy = "toDesignationId")
    private Collection<Jobmovements> jobmovementsCollection;
    @OneToMany(mappedBy = "fromDesignationId")
    private Collection<Jobmovements> jobmovementsCollection1;
    @OneToMany(mappedBy = "designationId")
    private Collection<Employees> employeesCollection;

    public Designations() {
    }

    public Designations(Long id) {
        this.id = id;
    }

    public Designations(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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

    @XmlTransient
    public Collection<Jobmovements> getJobmovementsCollection() {
        return jobmovementsCollection;
    }

    public void setJobmovementsCollection(Collection<Jobmovements> jobmovementsCollection) {
        this.jobmovementsCollection = jobmovementsCollection;
    }

    @XmlTransient
    public Collection<Jobmovements> getJobmovementsCollection1() {
        return jobmovementsCollection1;
    }

    public void setJobmovementsCollection1(Collection<Jobmovements> jobmovementsCollection1) {
        this.jobmovementsCollection1 = jobmovementsCollection1;
    }

    @XmlTransient
    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
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
        if (!(object instanceof Designations)) {
            return false;
        }
        Designations other = (Designations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Designations[ id=" + id + " ]";
    }
    
}
