package com.ssrv.fms.model.employee;

import java.io.Serializable;
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

import com.ssrv.fms.model.shift.Shifts;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "employeeattendence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeeattendence.findAll", query = "SELECT e FROM Employeeattendence e"),
    @NamedQuery(name = "Employeeattendence.findById", query = "SELECT e FROM Employeeattendence e WHERE e.id = :id"),
    @NamedQuery(name = "Employeeattendence.findByAttendenceDate", query = "SELECT e FROM Employeeattendence e WHERE e.attendenceDate = :attendenceDate"),
    @NamedQuery(name = "Employeeattendence.findByStatus", query = "SELECT e FROM Employeeattendence e WHERE e.status = :status"),
    @NamedQuery(name = "Employeeattendence.findByHoursWorked", query = "SELECT e FROM Employeeattendence e WHERE e.hoursWorked = :hoursWorked")})
public class Employeeattendence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "AttendenceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date attendenceDate;
    
    @Column(name = "Status")  
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Column(name = "HoursWorked")
    private Float hoursWorked;
    
    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne
    private Shifts shiftId;
    
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne
    private Employees employeeId;

    public Employeeattendence() {
    }

    public Employeeattendence(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAttendenceDate() {
    	System.out.println(attendenceDate);
        return attendenceDate;
    }

    public void setAttendenceDate(Date attendenceDate) {
    	System.out.println(attendenceDate);
        this.attendenceDate = attendenceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Float float1) {
        this.hoursWorked = float1;
    }

    public Shifts getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shifts shiftId) {
        this.shiftId = shiftId;
    }

    public Employees getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employees employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Employeeattendence)) {
            return false;
        }
        Employeeattendence other = (Employeeattendence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Employeeattendence[ id=" + id + " ]";
    }
    
}
