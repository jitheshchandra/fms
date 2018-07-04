package com.ssrv.fms.model.managedentity;

import java.io.Serializable;
import java.util.Collection;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.branch.Branch;

@Entity
@Table(name = "ManagedEntityType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagedEntityType.findAll", query = "SELECT M FROM ManagedEntityType M"),
    @NamedQuery(name = "ManagedEntityType.findById", query = "SELECT M FROM ManagedEntityType M WHERE M.id = :id"),
    @NamedQuery(name = "ManagedEntityType.findByIsDeleted", query = "SELECT m FROM ManagedEntityType m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "ManagedEntityType.findByName", query = "SELECT M FROM ManagedEntityType M WHERE M.name = :name")})
public class ManagedEntityType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Name")
    private String name;

    @Column(name = "IsDeleted")
    private Short isDeleted;
    
    

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

	public Short isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
