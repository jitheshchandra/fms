package com.ssrv.fms.service.organization.intf;

import java.util.List;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.vo.organization.OrganizationView;

public interface IOrganizationViewService {

	public List<OrganizationView> getAllOrganizations() throws FmsException;
}
