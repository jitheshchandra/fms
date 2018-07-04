package com.ssrv.fms.model.incident;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssrv.fms.model.branch.Branch;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "ESCALATIONMATRIX")
@XmlRootElement
public class EscalationMatrix implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "BranchId", referencedColumnName = "Id")
	private Branch branch;

	@ManyToOne
	@JoinColumn(name = "IncidentTypeId", referencedColumnName = "Id")
	private Incidentcategory category;

	@ManyToOne
	@JoinColumn(name = "EscalationLevelId", referencedColumnName = "Id")
	private Escalationlevel level;

	@Column(name = "LevelSequence")
	private int order;

	@Column(name = "ContactPersonName")
	private String contactName;

	@Column(name = "ContactPersonEmail")
	private String contactEmail;

	@Column(name = "ContactPersonPhone")
	private String contactPhone;

	// @Column(name = "trigger")
	// private String trigger;

	@Column(name = "Action")
	private String action;

	@Column(name = "MODIFIEDON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	@Column(name = "MODIFIEDBY")
	private BigInteger modifiedBy;

	@OneToMany(mappedBy = "escalationMatrix")
	private Collection<Incidents> incidentsCollection;

	public EscalationMatrix() {
	}

	public EscalationMatrix(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Incidentcategory getCategory() {
		return category;
	}

	public void setCategory(Incidentcategory category) {
		this.category = category;
	}

	public Escalationlevel getLevel() {
		return level;
	}

	public void setLevel(Escalationlevel level) {
		this.level = level;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public Collection<Incidents> getIncidentsCollection() {
		return incidentsCollection;
	}

	public void setIncidentsCollection(Collection<Incidents> incidentsCollection) {
		this.incidentsCollection = incidentsCollection;
	}

}
