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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.model.maintenance.Maintenanceinstance;

@Entity
@Table(name = "managedentityinstance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagedEntityInstance.findAll", query = "SELECT m FROM ManagedEntityInstance m"),
    @NamedQuery(name = "ManagedEntityInstance.findById", query = "SELECT m FROM ManagedEntityInstance m WHERE m.id = :id"),
    @NamedQuery(name = "ManagedEntityInstance.findByName", query = "SELECT m FROM ManagedEntityInstance m WHERE m.name = :name"),
    @NamedQuery(name = "ManagedEntityInstance.findByMainManagedEntityId", query = "SELECT m FROM ManagedEntityInstance m WHERE m.mainManagedEntityId = :mainManagedEntityId"),
    @NamedQuery(name = "ManagedEntityInstance.findBySerialNumber", query = "SELECT m FROM ManagedEntityInstance m WHERE m.serialNumber = :serialNumber"),
    @NamedQuery(name = "ManagedEntityInstance.findByCapacity", query = "SELECT m FROM ManagedEntityInstance m WHERE m.capacity = :capacity"),
   
    @NamedQuery(name = "ManagedEntityInstance.findByIsDeleted", query = "SELECT m FROM ManagedEntityInstance m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "ManagedEntityInstance.findByModifiedDate", query = "SELECT m FROM ManagedEntityInstance m WHERE m.modifiedDate = :modifiedDate")})
public class ManagedEntityInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "MainManagedEntityId")
    private BigInteger mainManagedEntityId;
    
    @Column(name = "SerialNumber")
    private String serialNumber;
    
    @Column(name = "Capacity")
    private String capacity;
    
    @Column(name = "IsMainEntity")
    private boolean isMainEntity;
    
    @Column(name = "IsDeleted")
    private Short isDeleted;
    
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
             
	//@OneToMany(mappedBy = "facilityId")
    //private Collection<Managedentitychecklistmap> managedentitychecklistmapCollection;
    
    //@ManyToMany
   // @JoinTable(name="ManagedEntityGroup_ManagedEntityInstance",
	//joinColumns=@JoinColumn(name="ManagedEntityInstanceId"),
	//inverseJoinColumns=@JoinColumn(name="ManagedEntityGroupId"))
    //private Collection<ManagedEntityGroup> managedEntityGroups;
    
    @OneToMany(mappedBy = "facilityId")
    private Collection<Maintenanceinstance> maintenanceinstanceCollection;
    
    @Column(name = "ManagedEntityGroupId")
    private long managedEntityGroup;
    
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;
    
 
    public long getManagedEntityGroup() {
		return managedEntityGroup;
	}

	public void setManagedEntityGroup(long managedEntityMaster) {
		this.managedEntityGroup = managedEntityMaster;
	}

	public ManagedEntityInstance() {
    }

    public ManagedEntityInstance(Long id) {
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

   public BigInteger getMainManagedEntityId() {
        return mainManagedEntityId;
    }

    public void setMainManagedEntityId(BigInteger mainManagedEntityId) {
        this.mainManagedEntityId = mainManagedEntityId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    
        public boolean isMainEntity() {
		return isMainEntity;
	}

	public void setMainEntity(boolean isMainEntity) {
		this.isMainEntity = isMainEntity;
	}

	public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    

/*	@XmlTransient
    public Collection<Managedentitychecklistmap> getManagedentitychecklistmapCollection() {
        return managedentitychecklistmapCollection;
    }

    public void setManagedentitychecklistmapCollection(Collection<Managedentitychecklistmap> managedentitychecklistmapCollection) {
        this.managedentitychecklistmapCollection = managedentitychecklistmapCollection;
    }

    public Collection<ManagedEntityGroup> getManagedEntityGroups() {
        return managedEntityGroups;
    }

    public void setManagedEntityGroups(Collection<ManagedEntityGroup> managedEntityGroups) {
        this.managedEntityGroups = managedEntityGroups;
    }*/

    public Collection<Maintenanceinstance> getMaintenanceinstanceCollection() {
        return maintenanceinstanceCollection;
    }

    public void setMaintenanceinstanceCollection(Collection<Maintenanceinstance> maintenanceinstanceCollection) {
        this.maintenanceinstanceCollection = maintenanceinstanceCollection;
    }



    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
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
        if (!(object instanceof ManagedEntityInstance)) {
            return false;
        }
        ManagedEntityInstance other = (ManagedEntityInstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.ManagedEntityInstance[ id=" + id + " ]";
    }
    
}
