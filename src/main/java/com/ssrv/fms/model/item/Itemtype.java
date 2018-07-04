package com.ssrv.fms.model.item;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author PAMS
 */
@Entity
@Table(name = "itemtypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemtype.findAll", query = "SELECT t FROM Itemtype t")})
public class Itemtype implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "isDeleted")
	private Integer isDeleted;

	@Column(name = "modifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Column(name = "modifiedBy")
	private BigInteger modifiedBy;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
		
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
