package com.ssrv.fms.vo.incidents;

import java.util.Date;

public interface IncidentResolveInfo {
	Long getId();

	String getStatus();

	Date getResolvedOn();

	String getResolvedRemarks();

}
