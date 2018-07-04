package com.ssrv.fms.model.employee;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.Designations;
import com.ssrv.fms.model.Groomings;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.contact.Country;
import com.ssrv.fms.model.contact.States;
import com.ssrv.fms.model.job.Joballocations;
import com.ssrv.fms.model.job.Jobmovements;
import com.ssrv.fms.model.organization.Organization;

/**
 *
 * @author Jithesh Chandra
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByEmpCode", query = "SELECT e FROM Employees e WHERE e.empCode = :empCode"),
    @NamedQuery(name = "Employees.findByFirstName", query = "SELECT e FROM Employees e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employees.findByMiddleName", query = "SELECT e FROM Employees e WHERE e.middleName = :middleName"),
    @NamedQuery(name = "Employees.findByLastName", query = "SELECT e FROM Employees e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employees.findByDob", query = "SELECT e FROM Employees e WHERE e.dob = :dob"),
    @NamedQuery(name = "Employees.findByAddress1", query = "SELECT e FROM Employees e WHERE e.address1 = :address1"),
    @NamedQuery(name = "Employees.findByAddress2", query = "SELECT e FROM Employees e WHERE e.address2 = :address2"),
    @NamedQuery(name = "Employees.findByAddress3", query = "SELECT e FROM Employees e WHERE e.address3 = :address3"),
    @NamedQuery(name = "Employees.findByCity", query = "SELECT e FROM Employees e WHERE e.city = :city"),
    @NamedQuery(name = "Employees.findByPhone", query = "SELECT e FROM Employees e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employees.findByMobile", query = "SELECT e FROM Employees e WHERE e.mobile = :mobile"),
    @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employees e WHERE e.email = :email"),
    @NamedQuery(name = "Employees.findByLocked", query = "SELECT e FROM Employees e WHERE e.locked = :locked"),
    @NamedQuery(name = "Employees.findByIsDeleted", query = "SELECT e FROM Employees e WHERE e.isDeleted = :isDeleted"),
    @NamedQuery(name = "Employees.findByModifiedOn", query = "SELECT e FROM Employees e WHERE e.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Employees.findByModifiedBy", query = "SELECT e FROM Employees e WHERE e.modifiedBy = :modifiedBy")})
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EmpCode")
    private String empCode;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "DOB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;
    @Column(name = "Address1")
    private String address1;
    @Column(name = "Address2")
    private String address2;
    @Column(name = "Address3")
    private String address3;
    @Column(name = "City")
    private String city;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Mobile")
    private String mobile;
    @Column(name = "Email")
    private String email;
    @Column(name = "Locked")
    private Short locked;
    @Column(name = "IsDeleted")
    private int isDeleted;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ModifiedBy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employees")
    private Groomings groomings;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Employeesfamilydetails> employeesfamilydetailsCollection;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Joballocations> joballocationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<Employeedocuments> employeedocumentsCollection;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Employeeattendence> employeeattendenceCollection;
    
  
    

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<Jobmovements> jobmovementsCollection;
	
    @JoinColumn(name = "OrganizationId", referencedColumnName = "Id")
    @ManyToOne
    private Organization organizationId;
    
    @JoinColumn(name = "BranchId", referencedColumnName = "Id")
    @ManyToOne
    private Branch branchId;
    
    
    
    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    @ManyToOne
    private States stateId;
    @JoinColumn(name = "CountryId", referencedColumnName = "Id")
    @ManyToOne
    private Country countryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisorId")
    private Collection<Employees> employeesCollection;
    @JoinColumn(name = "SupervisorId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Employees supervisorId;
    @JoinColumn(name = "DesignationId", referencedColumnName = "Id")
    @ManyToOne
    private Designations designationId;
    
    public Employees() {
    }

    public Employees(Long id) {
        this.id = id;
    }

    public Employees(Long id, String empCode, String firstName) {
        this.id = id;
        this.empCode = empCode;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getLocked() {
        return locked;
    }

    public void setLocked(Short locked) {
        this.locked = locked;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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

    public Groomings getGroomings() {
        return groomings;
    }

    public void setGroomings(Groomings groomings) {
        this.groomings = groomings;
    }
    
 
    @XmlTransient
    public Collection<Employeesfamilydetails> getEmployeesfamilydetailsCollection() {
        return employeesfamilydetailsCollection;
    }

    public void setEmployeesfamilydetailsCollection(Collection<Employeesfamilydetails> employeesfamilydetailsCollection) {
        this.employeesfamilydetailsCollection = employeesfamilydetailsCollection;
    }

    @XmlTransient
    public Collection<Joballocations> getJoballocationsCollection() {
        return joballocationsCollection;
    }

    public void setJoballocationsCollection(Collection<Joballocations> joballocationsCollection) {
        this.joballocationsCollection = joballocationsCollection;
    }

    @XmlTransient
    public Collection<Employeedocuments> getEmployeedocumentsCollection() {
        return employeedocumentsCollection;
    }

    public void setEmployeedocumentsCollection(Collection<Employeedocuments> employeedocumentsCollection) {
        this.employeedocumentsCollection = employeedocumentsCollection;
    }

    @XmlTransient
    public Collection<Employeeattendence> getEmployeeattendenceCollection() {
        return employeeattendenceCollection;
    }

    public void setEmployeeattendenceCollection(Collection<Employeeattendence> employeeattendenceCollection) {
        this.employeeattendenceCollection = employeeattendenceCollection;
    }

    @XmlTransient
    public Collection<Jobmovements> getJobmovementsCollection() {
        return jobmovementsCollection;
    }

    public void setJobmovementsCollection(Collection<Jobmovements> jobmovementsCollection) {
        this.jobmovementsCollection = jobmovementsCollection;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }
  
    
    
    public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public States getStateId() {
        return stateId;
    }

    public void setStateId(States stateId) {
        this.stateId = stateId;
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

    public Employees getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Employees supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Designations getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Designations designationId) {
        this.designationId = designationId;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Employees[ id=" + id + " ]";
    }
    
}
