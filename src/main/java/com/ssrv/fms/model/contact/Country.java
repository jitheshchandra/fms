package com.ssrv.fms.model.contact;

import java.io.Serializable;
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

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.organization.Organization;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name"),
    @NamedQuery(name = "Country.findByIsDelete", query = "SELECT c FROM Country c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "Country.findByModifiedOn", query = "SELECT c FROM Country c WHERE c.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Country.findByModifiedBy", query = "SELECT c FROM Country c WHERE c.modifiedBy = :modifiedBy")})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    
    @Column(name = "IsDeleted")
    private Integer isDeleted;
    
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    
    @OneToMany(mappedBy = "countryId")
    private Collection<States> statesCollection;
  
    @OneToMany(mappedBy = "countryId")
    private Collection<Employees> employeesCollection;
    
    @OneToMany(mappedBy="country")
    private Collection<Branch> branch;
    
    @OneToMany(mappedBy="countryId")
    private Collection<Organization> organization;

    public Country() {
    }

    public Country(Long id) {
        this.id = id;
    }

    public Country(Long id, String name) {
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDelete) {
        this.isDeleted = isDelete;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @XmlTransient
    public Collection<States> getStatesCollection() {
        return statesCollection;
    }

    public void setStatesCollection(Collection<States> statesCollection) {
        this.statesCollection = statesCollection;
    }

    @XmlTransient
    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
    }
    
    public Collection<Branch> getBranch() {
		return branch;
	}

	public void setBranch(Collection<Branch> branch) {
		this.branch = branch;
	}
	
	public Collection<Organization> getOrganization() {
		return organization;
	}

	public void setOrganization(Collection<Organization> organization) {
		this.organization = organization;
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Country[ id=" + id + " ]";
    }
    
}
