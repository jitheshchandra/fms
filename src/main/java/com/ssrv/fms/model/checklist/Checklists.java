package com.ssrv.fms.model.checklist;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.Groomings;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.employee.Employeedocuments;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.managedentity.ManagedEntityInstance;
import com.ssrv.fms.model.managedentity.ManagedEntitySubType;
import com.ssrv.fms.model.managedentity.ManagedEntityType;
import com.ssrv.fms.model.managedentity.Managedentitychecklistmap;
import com.ssrv.fms.model.organization.Organization;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "checklist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checklists.findAll", query = "SELECT c FROM Checklists c"),
    @NamedQuery(name = "Checklists.findById", query = "SELECT c FROM Checklists c WHERE c.id = :id"),
    @NamedQuery(name = "Checklists.findByName", query = "SELECT c FROM Checklists c WHERE c.name = :name"),
    @NamedQuery(name = "Checklists.findByIsDeleted", query = "SELECT c FROM Checklists c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "Checklists.findByModifiedBy", query = "SELECT c FROM Checklists c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Checklists.findByModifiedDate", query = "SELECT c FROM Checklists c WHERE c.modifiedDate = :modifiedDate")})
public class Checklists implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "Description")
    private String description;
    
    @JoinColumn(name = "ManagedEntityType",referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityType managedEntityType;
   
    @JoinColumn(name = "ManagedEntitySubType",referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntitySubType managedEntitySubType;
    
    @Column(name = "Key")
    private String key;
    
    @Column(name = "DefaultValue")
    private Boolean defaultValue;
   
    @Column(name = "IsDeleted")
    private Short isDeleted;
   
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
   
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
   
  
   public Checklists(){
	   
   }
    public Checklists(Long id) {
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


    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public ManagedEntityType getManagedEntityType() {
		return managedEntityType;
	}


	public void setManagedEntityType(ManagedEntityType managedEntityType) {
		this.managedEntityType = managedEntityType;
	}


	public ManagedEntitySubType getManagedEntitySubType() {
		return managedEntitySubType;
	}

	public void setManagedEntitySubType(ManagedEntitySubType managedEntitySubType) {
		this.managedEntitySubType = managedEntitySubType;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

   

    public Date getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Date modifiedBy) {
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
        if (!(object instanceof Checklists)) {
            return false;
        }
        Checklists other = (Checklists) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Checklists[ id=" + id + " ]";
    }
    
}
