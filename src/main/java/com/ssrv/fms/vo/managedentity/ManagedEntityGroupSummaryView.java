package com.ssrv.fms.vo.managedentity;

import java.util.ArrayList;
import java.util.List;

import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.vo.organization.OrganizationView;

public class ManagedEntityGroupSummaryView
	{

		List<ManagedEntityGroupSummary> managedEntityGroupSummaries = new ArrayList<ManagedEntityGroupSummary>();
		List<OrganizationView> organizations = new ArrayList<OrganizationView>();
		private String name;
		private Long id;

		public ManagedEntityGroupSummaryView(ManagedEntityGroup meg)
			{
				this.name = meg.getName();
				this.id = meg.getId();
			}

		public ManagedEntityGroupSummaryView()
			{
			}

		public String getName()
			{
				return name;
			}

		public Long getId()
			{
				return id;
			}

		public List<ManagedEntityGroupSummary> getManagedEntityGroupSummaries()
			{
				return managedEntityGroupSummaries;
			}

		public void setManagedEntityGroupSummaries(List<ManagedEntityGroupSummary> managedEntityGroupSummaries)
			{
				this.managedEntityGroupSummaries = managedEntityGroupSummaries;
			}

		public List<OrganizationView> getOrganizations()
			{
				return organizations;
			}

		public void setOrganizations(List<OrganizationView> organizations)
			{
				this.organizations = organizations;
			}

	}
