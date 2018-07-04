package com.ssrv.fms.model.managedentity;

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

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "managedentitymaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagedEntityMaster.findAll", query = "SELECT m FROM ManagedEntityMaster m"),
    @NamedQuery(name = "ManagedentManagedEntityMasteritymaster.findById", query = "SELECT m FROM ManagedEntityMaster m WHERE m.id = :id"),
    @NamedQuery(name = "ManagedEntityMaster.findByName", query = "SELECT m FROM ManagedEntityMaster m WHERE m.name = :name"),
    @NamedQuery(name = "ManagedEntityMaster.findByIsDeleted", query = "SELECT m FROM ManagedEntityMaster m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "ManagedEntityMaster.findByModifiedBy", query = "SELECT m FROM ManagedEntityMaster m WHERE m.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "ManagedEntityMaster.findByModifiedDate", query = "SELECT m FROM ManagedEntityMaster m WHERE m.modifiedDate = :modifiedDate")})
public class ManagedEntityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "IsDeleted")
    private Short isDeleted;
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
   
    public ManagedEntityMaster() {
    }

    public ManagedEntityMaster(Long id) {
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
        if (!(object instanceof ManagedEntityMaster)) {
            return false;
        }
        ManagedEntityMaster other = (ManagedEntityMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.ManagedEntityMaster[ id=" + id + " ]";
    }
    
}
