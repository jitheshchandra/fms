package com.ssrv.fms.model.user;



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
import javax.xml.bind.annotation.XmlTransient;

import com.ssrv.fms.model.Acl;

/**
 *
 * @author PAMS
 */
@Entity
@Table(name = "usertype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertypes.findAll", query = "SELECT u FROM Usertypes u"),
    @NamedQuery(name = "Usertypes.findById", query = "SELECT u FROM Usertypes u WHERE u.id = :id"),
    @NamedQuery(name = "Usertypes.findByName", query = "SELECT u FROM Usertypes u WHERE u.name = :name"),
    @NamedQuery(name = "Usertypes.findByIsDeleted", query = "SELECT u FROM Usertypes u WHERE u.isDeleted = :isDeleted")})
public class Usertypes implements Serializable {
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
    @OneToMany(mappedBy = "userTypeId")
    private Collection<Acl> aclCollection;

    public Usertypes() {
    }

    public Usertypes(Long id) {
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

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    public Collection<Acl> getAclCollection() {
        return aclCollection;
    }

    public void setAclCollection(Collection<Acl> aclCollection) {
        this.aclCollection = aclCollection;
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
        if (!(object instanceof Usertypes)) {
            return false;
        }
        Usertypes other = (Usertypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Usertypes[ id=" + id + " ]";
    }
    
}

