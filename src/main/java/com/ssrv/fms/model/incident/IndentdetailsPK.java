package com.ssrv.fms.model.incident;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author PAMS
 */
@Embeddable
public class IndentdetailsPK implements Serializable {
	@Basic(optional = false)
	@Column(name = "IndentId")
	private Long indentId;
	@Basic(optional = false)
	@Column(name = "ItemId")
	private Long itemId;

	public IndentdetailsPK() {
	}

	public IndentdetailsPK(Long indentId, Long itemId) {
		this.indentId = indentId;
		this.itemId = itemId;
	}

	public Long getIndentId() {
		return indentId;
	}

	public void setIndentId(Long indentId) {
		this.indentId = indentId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += indentId;
		hash += itemId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof IndentdetailsPK)) {
			return false;
		}
		IndentdetailsPK other = (IndentdetailsPK) object;
		if (this.indentId != other.indentId) {
			return false;
		}
		if (this.itemId != other.itemId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jpa.IndentdetailsPK[ indentId=" + indentId + ", itemId="
				+ itemId + " ]";
	}

}
