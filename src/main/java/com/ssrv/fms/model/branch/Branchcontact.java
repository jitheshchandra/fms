package com.ssrv.fms.model.branch;

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

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "BranchContact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branchcontacts.findAll", query = "SELECT b FROM Branchcontact b"),
    @NamedQuery(name = "Branchcontacts.findById", query = "SELECT b FROM Branchcontact b WHERE b.id = :id"),
    @NamedQuery(name = "Branchcontacts.findByContactPerson", query = "SELECT b FROM Branchcontact b WHERE b.contactPerson = :contactPerson"),
    @NamedQuery(name = "Branchcontacts.findByContactPersonMailId", query = "SELECT b FROM Branchcontact b WHERE b.contactPersonMailId = :contactPersonMailId"),
    @NamedQuery(name = "Branchcontacts.findByContactPersonMobile", query = "SELECT b FROM Branchcontact b WHERE b.contactPersonMobile = :contactPersonMobile"),
    @NamedQuery(name = "Branchcontacts.findByIsDeleted", query = "SELECT b FROM Branchcontact b WHERE b.isDeleted = :isDeleted"),
    @NamedQuery(name = "Branchcontacts.findByModifiedOn", query = "SELECT b FROM Branchcontact b WHERE b.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Branchcontacts.findByModifiedBy", query = "SELECT b FROM Branchcontact b WHERE b.modifiedBy = :modifiedBy")})
public class Branchcontact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
   
    @Column(name = "ContactPerson")
    private String contactPerson;
   
    @Column(name = "ContactPersonMailId")
    private String contactPersonMailId;
   
    @Column(name = "ContactPersonMobile")
    private String contactPersonMobile;
    
    @Column(name="ContactType")
    private String contactPersonDesignation;
   
    @Column(name = "IsDeleted")
    private Short isDeleted;
   
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
   
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;

    public Branchcontact() {
    }

    public Branchcontact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonMailId() {
        return contactPersonMailId;
    }

    public void setContactPersonMailId(String contactPersonMailId) {
        this.contactPersonMailId = contactPersonMailId;
    }

    public String getContactPersonMobile() {
        return contactPersonMobile;
    }

    public void setContactPersonMobile(String contactPersonMobile) {
        this.contactPersonMobile = contactPersonMobile;
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

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }
    
    public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}

	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
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
        if (!(object instanceof Branchcontact)) {
            return false;
        }
        Branchcontact other = (Branchcontact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Branchcontacts[ id=" + id + " ]";
    }
    
}
