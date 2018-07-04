package com.ssrv.fms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.model.user.Usertypes;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "acl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acl.findAll", query = "SELECT a FROM Acl a"),
    @NamedQuery(name = "Acl.findById", query = "SELECT a FROM Acl a WHERE a.id = :id"),
    @NamedQuery(name = "Acl.findByFunction", query = "SELECT a FROM Acl a WHERE a.function = :function"),
    @NamedQuery(name = "Acl.findByAccessability", query = "SELECT a FROM Acl a WHERE a.accessability = :accessability"),
    @NamedQuery(name = "Acl.findByModifiedon", query = "SELECT a FROM Acl a WHERE a.modifiedon = :modifiedon"),
    @NamedQuery(name = "Acl.findByModifiedBy", query = "SELECT a FROM Acl a WHERE a.modifiedBy = :modifiedBy")})
public class Acl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "FunctionId")
    private String function;
    @Column(name = "Accessability")
    private Short accessability;
    @Column(name = "Modifiedon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedon;
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;
    @JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
    @ManyToOne
    private Organization organizationId;
    @JoinColumn(name = "UserTypeId", referencedColumnName = "Id")
    @ManyToOne
    private Usertypes userTypeId;

    public Acl() {
    }

    public Acl(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Short getAccessability() {
        return accessability;
    }

    public void setAccessability(Short accessability) {
        this.accessability = accessability;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }

    public Usertypes getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Usertypes userTypeId) {
        this.userTypeId = userTypeId;
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
        if (!(object instanceof Acl)) {
            return false;
        }
        Acl other = (Acl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Acl[ id=" + id + " ]";
    }
    
}