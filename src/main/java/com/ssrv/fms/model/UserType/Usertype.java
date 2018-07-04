package com.ssrv.fms.model.UserType;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "usertype")
@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "usertype.findAll", query = "SELECT  FROM usertype c"),
//		@NamedQuery(name = "usertype.findById", query = "SELECT c FROM usertype c WHERE c.id = :id"),
//		@NamedQuery(name = "usertype.findByName", query = "SELECT c FROM usertype c WHERE c.name = :name"),
//	@NamedQuery(name = "usertype.findByIsDelete", query = "SELECT c FROM usertype c WHERE c.isDeleted = :isDeleted"), })
public class Usertype implements Serializable {
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

	public Usertype() {
	}

	public Usertype(Long id) {
		this.id = id;
	}

	public Usertype(Long id, String name) {
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

	public void setIsDeleted(int isDelete) {
		this.isDeleted = isDelete;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usertype)) {
			return false;
		}
		Usertype other = (Usertype) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.Usertype[ id=" + id + " ]";
	}

}
