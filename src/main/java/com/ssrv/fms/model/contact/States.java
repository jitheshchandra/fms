package com.ssrv.fms.model.contact;

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
import com.ssrv.fms.model.employee.Employees;
import com.ssrv.fms.model.organization.Organization;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "States.findAll", query = "SELECT s FROM States s"),
    @NamedQuery(name = "States.findById", query = "SELECT s FROM States s WHERE s.id = :id"),
    @NamedQuery(name = "States.findByName", query = "SELECT s FROM States s WHERE s.name = :name"),
    @NamedQuery(name = "States.findByIsDeleted", query = "SELECT s FROM States s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "States.findByModifiedOn", query = "SELECT s FROM States s WHERE s.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "States.findByModifiedBy", query = "SELECT s FROM States s WHERE s.modifiedBy = :modifiedBy")})
public class States implements Serializable {
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
    private int isDeleted;
    
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    @Column(name = "ModifiedBy")
    private BigInteger modifiedBy;
    
    @JoinColumn(name = "CountryId", referencedColumnName = "Id")
    @ManyToOne
    private Country countryId;
    
    @OneToMany(mappedBy = "stateId")
    private Collection<Employees> employeesCollection;
    
    @OneToMany(mappedBy="stateId")
    private Collection<Branch> branch;
    
    @OneToMany(mappedBy="stateId")
    private Collection<Organization> orgainzation;

    public States() {
    }

    public States(Long id) {
        this.id = id;
    }

    public States(Long id, String name) {
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int i) {
        this.isDeleted = i;
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

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
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
	
	public Collection<Organization> getOrgainzation() {
		return orgainzation;
	}

	public void setOrgainzation(Collection<Organization> orgainzation) {
		this.orgainzation = orgainzation;
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
        if (!(object instanceof States)) {
            return false;
        }
        States other = (States) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.States[ id=" + id + " ]";
    }
    
}
