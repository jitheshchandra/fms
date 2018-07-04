package com.ssrv.fms.model.organization;

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
@Table(name = "organizationcontact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizationcontacts.findAll", query = "SELECT o FROM Organizationcontacts o"),
    @NamedQuery(name = "Organizationcontacts.findById", query = "SELECT o FROM Organizationcontacts o WHERE o.id = :id"),
    @NamedQuery(name = "Organizationcontacts.findByContactPerson", query = "SELECT o FROM Organizationcontacts o WHERE o.contactPerson = :contactPerson"),
    @NamedQuery(name = "Organizationcontacts.findByContactMailId", query = "SELECT o FROM Organizationcontacts o WHERE o.contactMailId = :contactMailId"),
    @NamedQuery(name = "Organizationcontacts.findByContactMobileNo", query = "SELECT o FROM Organizationcontacts o WHERE o.contactMobileNo = :contactMobileNo"),
    @NamedQuery(name = "Organizationcontacts.findByModifiedOn", query = "SELECT o FROM Organizationcontacts o WHERE o.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Organizationcontacts.findByModifiedBy", query = "SELECT o FROM Organizationcontacts o WHERE o.modifiedBy = :modifiedBy")})
public class Organizationcontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
   
    @Column(name = "ContactPerson")
    private String contactPerson;
   
    @Column(name = "ContactMailId")
    private String contactMailId;
   
    @Column(name = "ContactMobileNo")
    private String contactMobileNo;
    
    @Column(name="ContactType")
    private String contactPersonDesignation;
   
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
   
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy; 
    
    @Column(name="IsDeleted")
    private short isDeleted;
   
    @JoinColumn(name = "OrganizationID", referencedColumnName = "Id")
    @ManyToOne
    private Organization organizationID;

    public Organizationcontacts() {
    }

    public Organizationcontacts(Long id) {
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

    public String getContactMailId() {
        return contactMailId;
    }

    public void setContactMailId(String contactMailId) {
        this.contactMailId = contactMailId;
    }

    public String getContactMobileNo() {
        return contactMobileNo;
    }

    public void setContactMobileNo(String contactMobileNo) {
        this.contactMobileNo = contactMobileNo;
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
 
    public short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Organization getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Organization organizationID) {
        this.organizationID = organizationID;
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
        if (!(object instanceof Organizationcontacts)) {
            return false;
        }
        Organizationcontacts other = (Organizationcontacts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Organizationcontacts[ id=" + id + " ]";
    }
    
}
