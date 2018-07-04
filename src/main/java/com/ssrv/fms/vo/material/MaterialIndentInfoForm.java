package com.ssrv.fms.vo.material;

import java.util.List;

import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.vo.indent.IndentDetailInfoForm;

public class MaterialIndentInfoForm implements MaterialIndentInfo
	{
		private Long id;
		private Branch branch;
		private Organization organization;
		private ManagedEntityGroup manageEntity;
		private String name;
		private List<IndentDetailInfoForm> indentDetails;
		
		private Long organizationId;
		private Long branchId;
		private Long managedEntityId;
		
		public Long getOrganizationId()
			{
				return organizationId;
			}
		public void setOrganizationId(Long organizationId)
			{
				this.organizationId = organizationId;
			}
		public Long getBranchId()
			{
				return branchId;
			}
		public void setBranchId(Long branchId)
			{
				this.branchId = branchId;
			}
		public Long getManagedEntityId()
			{
				return managedEntityId;
			}
		public void setManagedEntityId(Long managedEntityId)
			{
				this.managedEntityId = managedEntityId;
			}
		public MaterialIndentInfoForm()
			{
			}
		@Override
		public Long getId()
			{
				return id;
			}
		public void setId(Long id)
			{
				this.id = id;
			}
		public void setBranch(Branch branch)
			{
				this.branch = branch;
			}
		@Override
		public Branch getBranch()
			{
				return branch;
			}
		public void setName(String name)
			{
				this.name = name;
			}
		@Override
		public String getName()
			{
				return name;
			}
		@Override
		public Organization getOrganization()
			{
				return organization;
			}
		@Override
		public List<IndentDetailInfoForm> getIndentDetails()
			{
				return indentDetails;
			}
		public void setIndentDetails(List<IndentDetailInfoForm> indentDetails)
			{
				this.indentDetails = indentDetails;
			}
		@Override
		public ManagedEntityGroup getManageEntity()
			{		
				return manageEntity;
			}
		public void setOrganization(Organization organization)
			{
				this.organization = organization;
			}
		public void setManageEntity(ManagedEntityGroup manageEntity)
			{
				this.manageEntity = manageEntity;
			}
	}
