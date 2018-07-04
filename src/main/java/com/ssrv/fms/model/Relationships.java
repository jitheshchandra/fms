package com.ssrv.fms.model;

import java.io.Serializable;
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

import com.ssrv.fms.model.employee.Employeesfamilydetails;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "relationships")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relationships.findAll", query = "SELECT r FROM Relationships r"),
    @NamedQuery(name = "Relationships.findById", query = "SELECT r FROM Relationships r WHERE r.id = :id"),
    @NamedQuery(name = "Relationships.findByName", query = "SELECT r FROM Relationships r WHERE r.name = :name"),
    @NamedQuery(name = "Relationships.findByIsDeleted", query = "SELECT r FROM Relationships r WHERE r.isDeleted = :isDeleted"),
    @NamedQuery(name = "Relationships.findByModifiedOn", query = "SELECT r FROM Relationships r WHERE r.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Relationships.findByModifiedBy", query = "SELECT r FROM Relationships r WHERE r.modifiedBy = :modifiedBy")})
public class Relationships implements Serializable {
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
    private Short isDeleted;
    
    
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    
    
    @OneToMany(mappedBy = "relationshipId")
    private Collection<Employeesfamilydetails> employeesfamilydetailsCollection;

    public Relationships() {
    }

    public Relationships(Long id) {
        this.id = id;
    }

    public Relationships(Long id, String name) {
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

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @XmlTransient
    public Collection<Employeesfamilydetails> getEmployeesfamilydetailsCollection() {
        return employeesfamilydetailsCollection;
    }

    public void setEmployeesfamilydetailsCollection(Collection<Employeesfamilydetails> employeesfamilydetailsCollection) {
        this.employeesfamilydetailsCollection = employeesfamilydetailsCollection;
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
        if (!(object instanceof Relationships)) {
            return false;
        }
        Relationships other = (Relationships) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Relationships[ id=" + id + " ]";
    }
    
}
