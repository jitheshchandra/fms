package com.ssrv.fms.model.managedentity;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@Table(name = "managedentitygroupdetails")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Managedentitygroupdetails.findAll", query = "SELECT m FROM Managedentitygroupdetails m"),
@NamedQuery(name = "Managedentitygroupdetails.findById", query = "SELECT m FROM Managedentitygroupdetails m WHERE m.id = :id")})
public class Managedentitygroupdetails implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @JoinColumn(name = "FacilityId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityGroup managedEntityGroupId;
    @JoinColumn(name = "FacilityGroupId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityInstance managedEntityInstanceId;

    public Managedentitygroupdetails() {
    }

    public Managedentitygroupdetails(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ManagedEntityGroup getmanagedEntityGroupId() {
        return managedEntityGroupId;
    }

    public void setmanagedEntityGroupId(ManagedEntityGroup managedEntityGroupId) {
        this.managedEntityGroupId = managedEntityGroupId;
    }

    public ManagedEntityInstance getmanagedEntityInstanceId() {
        return managedEntityInstanceId;
    }

    public void setmanagedEntityInstanceId(ManagedEntityInstance managedEntityInstanceId) {
        this.managedEntityInstanceId = managedEntityInstanceId;
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
        if (!(object instanceof Managedentitygroupdetails)) {
            return false;
        }
        Managedentitygroupdetails other = (Managedentitygroupdetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Managedentitygroupdetails[ id=" + id + " ]";
    }
    
}
