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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "subequipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subequipments.findAll", query = "SELECT s FROM Subequipments s"),
    @NamedQuery(name = "Subequipments.findById", query = "SELECT s FROM Subequipments s WHERE s.id = :id"),
    @NamedQuery(name = "Subequipments.findByName", query = "SELECT s FROM Subequipments s WHERE s.name = :name"),
    @NamedQuery(name = "Subequipments.findByEquipmentId", query = "SELECT s FROM Subequipments s WHERE s.equipmentId = :equipmentId"),
    @NamedQuery(name = "Subequipments.findByIsDeleted", query = "SELECT s FROM Subequipments s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "Subequipments.findByModifiedBy", query = "SELECT s FROM Subequipments s WHERE s.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Subequipments.findByModifiedDate", query = "SELECT s FROM Subequipments s WHERE s.modifiedDate = :modifiedDate")})
public class Subequipments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "EquipmentId")
    private BigInteger equipmentId;
    @Column(name = "IsDeleted")
    private Short isDeleted;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public Subequipments() {
    }

    public Subequipments(Long id) {
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

    public BigInteger getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(BigInteger equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subequipments)) {
            return false;
        }
        Subequipments other = (Subequipments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Subequipments[ id=" + id + " ]";
    }
    
}
