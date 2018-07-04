package com.ssrv.fms.vo.incidents;

public interface IncidentInfoForm extends IncidentInfo {

	Long getManagedEntityId();

	Long getEscalationMatrixId();

	Long getIncidentTypeId();

}
