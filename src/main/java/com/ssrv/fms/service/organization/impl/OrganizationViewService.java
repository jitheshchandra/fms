package com.ssrv.fms.service.organization.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.service.organization.intf.IOrganizationViewService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.util.beans.BeanUtil;
import com.ssrv.fms.vo.branch.BranchView;
import com.ssrv.fms.vo.organization.OrganizationView;

@Service
@Lazy
public class OrganizationViewService implements IOrganizationViewService {

	@Autowired
	private OrganizationService organizationService;

	@Override
	@Transactional
	public List<OrganizationView> getAllOrganizations() throws FmsException {

		List<OrganizationView> orgViews = new ArrayList<OrganizationView>();
		try {
			List<Organization> orgs = organizationService.getAllOrganizations();

			for (Organization org : orgs) {
				OrganizationView orgView = new OrganizationView();
				BeanUtil.copyBeans(orgView, org);
				List<BranchView> branchViews = new ArrayList<BranchView>();
				Collection<Branch> branches = org.getBranches();
				if (branches != null && !branches.isEmpty()) {
					BeanUtil.copyBeans(branchViews, org.getBranches(),
							new BranchView());
					orgView.setBranches(branchViews);
				}
				orgViews.add(orgView);
			}
		} catch (IllegalAccessException e) {

			throw new FmsException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FmsException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new FmsException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new FmsException(e.getMessage());
		}
		return orgViews;
	}
}
