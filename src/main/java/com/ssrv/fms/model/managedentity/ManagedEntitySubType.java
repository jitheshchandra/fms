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

@Entity
@Table(name = "ManagedEntitySubType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagedEntitySubType.findAll", query = "SELECT c FROM Checklists c"),
    @NamedQuery(name = "ManagedEntitySubType.findById", query = "SELECT c FROM Checklists c WHERE c.id = :id"),
    @NamedQuery(name = "ManagedEntitySubType.findByName", query = "SELECT c FROM Checklists c WHERE c.name = :name")})
public class ManagedEntitySubType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Name")
    private String name;

    @JoinColumn(name = "ManagedEntityTypeId", referencedColumnName = "Id")
    @ManyToOne
    private ManagedEntityType managedEntityTypeId;
    
    @Column(name = "IsDeleted")
    private short isDeleted;
    
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

	public ManagedEntityType getManagedEntityTypeId() {
		return managedEntityTypeId;
	}

	public void setManagedEntityTypeId(ManagedEntityType managedEntityTypeId) {
		this.managedEntityTypeId = managedEntityTypeId;
	}

	public short isDeleted() {
		return isDeleted;
	}

	public void setDeleted(short s) {
		this.isDeleted = s;
	}
    
	
    
}