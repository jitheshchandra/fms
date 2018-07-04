package com.ssrv.fms.model.employee;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.Relationships;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "employeesfamilydetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeesfamilydetails.findAll", query = "SELECT e FROM Employeesfamilydetails e"),
    @NamedQuery(name = "Employeesfamilydetails.findById", query = "SELECT e FROM Employeesfamilydetails e WHERE e.id = :id"),
    @NamedQuery(name = "Employeesfamilydetails.findByName", query = "SELECT e FROM Employeesfamilydetails e WHERE e.name = :name"),
    @NamedQuery(name = "Employeesfamilydetails.findByIsDeleted", query = "SELECT e FROM Employeesfamilydetails e WHERE e.isDeleted = :isDeleted"),
    @NamedQuery(name = "Employeesfamilydetails.findByModifiedOn", query = "SELECT e FROM Employeesfamilydetails e WHERE e.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Employeesfamilydetails.findByModifiedBy", query = "SELECT e FROM Employeesfamilydetails e WHERE e.modifiedBy = :modifiedBy")})
public class Employeesfamilydetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "IsDeleted")
    private Short isDeleted;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @JoinColumn(name = "RelationshipId", referencedColumnName = "Id")
    @ManyToOne
    private Relationships relationshipId;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne
    private Employees employeeId;

    public Employeesfamilydetails() {
    }

    public Employeesfamilydetails(Long id) {
        this.id = id;
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

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
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

    public Relationships getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Relationships relationshipId) {
        this.relationshipId = relationshipId;
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
        if (!(object instanceof Employeesfamilydetails)) {
            return false;
        }
        Employeesfamilydetails other = (Employeesfamilydetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Employeesfamilydetails[ id=" + id + " ]";
    }
    
}
