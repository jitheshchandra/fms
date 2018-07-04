package com.ssrv.fms.vo.user;

import com.ssrv.fms.model.user.UserBranchMapping;

public class UserBranchMappingVO
	{
		private Long id;
		private Long organizationId;
		private Long branchId;
		private Long defaultOraganization;
		private Long defaultBranch;
		
		public UserBranchMappingVO(UserBranchMapping mapping)
		{
			this.organizationId=mapping.getOrganizationId().getId();
			this.branchId=mapping.getBranchId().getId();
			this.defaultBranch=mapping.getDefaultBranch();
			this.defaultOraganization=mapping.getDefaultOrganization();
			this.id=mapping.getId();
		}
		
		public Long getId()
			{
				return id;
			}



		public void setId(Long id)
			{
				this.id = id;
			}



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

		public Long getDefaultOraganization()
			{
				return defaultOraganization;
			}

		public void setDefaultOraganization(Long defaultOraganization)
			{
				this.defaultOraganization = defaultOraganization;
			}

		public Long getDefaultBranch()
			{
				return defaultBranch;
			}

		public void setDefaultBranch(Long defaultBranch)
			{
				this.defaultBranch = defaultBranch;
			}
		
			
	}
