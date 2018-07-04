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

import com.ssrv.fms.model.checklist.Checklists;

/**
 *
 * @author PAMS
 */
//@Entity
//@Table(name = "managedentitychecklistmap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Managedentitychecklistmap.findAll", query = "SELECT m FROM Managedentitychecklistmap m"),
    @NamedQuery(name = "Managedentitychecklistmap.findById", query = "SELECT m FROM Managedentitychecklistmap m WHERE m.id = :id")})
public class Managedentitychecklistmap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @JoinColumn(name = "FacilityId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityInstance facilityId;
    @JoinColumn(name = "CheckListId", referencedColumnName = "Id")
    @ManyToOne
    private Checklists checkListId;

    public Managedentitychecklistmap() {
    }

    public Managedentitychecklistmap(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ManagedEntityInstance getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(ManagedEntityInstance facilityId) {
        this.facilityId = facilityId;
    }

    public Checklists getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(Checklists checkListId) {
        this.checkListId = checkListId;
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
        if (!(object instanceof Managedentitychecklistmap)) {
            return false;
        }
        Managedentitychecklistmap other = (Managedentitychecklistmap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Managedentitychecklistmap[ id=" + id + " ]";
    }
    
}

