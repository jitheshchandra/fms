package com.ssrv.fms.model.checklist;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "checklisttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checklisttype.findAll", query = "SELECT c FROM Checklisttype c"),
    @NamedQuery(name = "Checklisttype.findById", query = "SELECT c FROM Checklisttype c WHERE c.id = :id"),
    @NamedQuery(name = "Checklisttype.findByName", query = "SELECT c FROM Checklisttype c WHERE c.name = :name"),
    @NamedQuery(name = "Checklisttype.findByIsDeleted", query = "SELECT c FROM Checklisttype c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "Checklisttype.findByModifiedOn", query = "SELECT c FROM Checklisttype c WHERE c.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Checklisttype.findByModifiedBy", query = "SELECT c FROM Checklisttype c WHERE c.modifiedBy = :modifiedBy")})
public class Checklisttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    private BigInteger modifiedBy;
   
    public Checklisttype() {
    }

    public Checklisttype(Long id) {
        this.id = id;
    }

    public Checklisttype(Long id, String name) {
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

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
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
        if (!(object instanceof Checklisttype)) {
            return false;
        }
        Checklisttype other = (Checklisttype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Checklisttype[ id=" + id + " ]";
    }
    
}
