package com.ssrv.fms.vo.incidents.impl;

import java.util.Date;

import com.ssrv.fms.vo.incidents.IncidentResolveInfo;

public class IncidentResolveInfoImpl implements IncidentResolveInfo {

	private Long id;
	private String status;
	private Date resolvedOn;
	private String resolvedRemarks;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		if (this.status.equals("C")) {
			this.resolvedOn = new Date();
		}
	}

	@Override
	public Date getResolvedOn() {
		return resolvedOn;
	}

	@Override
	public String getResolvedRemarks() {
		return resolvedRemarks;
	}

	public void setResolvedRemarks(String resolvedRemarks) {
		this.resolvedRemarks = resolvedRemarks;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
