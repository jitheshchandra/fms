package com.ssrv.fms.vo.user;

import java.util.List;

public class CreateUserForm
	{
		private String Id;
		private String userName;
		private String password;
		private List<String> includedOrganizations;
		private List<String> includeBranch;
		private String isDefaultOrganization;
	    private String isDefaultBranch;
		private Long userTypeId;
		private String userType;

		public String getId()
			{
				return Id;
			}

		public void setId(String id)
			{
				Id = id;
			}

		public String getUserType()
			{
				return userType;
			}

		public void setUserType(String userType)
			{
				this.userType = userType;
			}

		public String getUserName()
			{
				return userName;
			}

		public void setUserName(String userName)
			{
				this.userName = userName;
			}

		public String getPassword()
			{
				return password;
			}

		public void setPassword(String password)
			{
				this.password = password;
			}

		public List<String> getIncludedOrganizations()
			{
				return includedOrganizations;
			}

		public void setIncludedOrganizations(List<String> includedOrganizations)
			{
				this.includedOrganizations = includedOrganizations;
			}
		public String getIsDefaultOrganization()
			{
				return isDefaultOrganization;
			}

		public void setIsDefaultOrganization(String isDefaultOrganization)
			{
				this.isDefaultOrganization = isDefaultOrganization;
			}

		public String getIsDefaultBranch()
			{
				return isDefaultBranch;
			}

		public void setIsDefaultBranch(String isDefaultBranch)
			{
				this.isDefaultBranch = isDefaultBranch;
			}

		public Long getUserTypeId()
			{
				return userTypeId;
			}

		public void setUserTypeId(Long userTypeId)
			{
				this.userTypeId = userTypeId;
			}

		public List<String> getIncludeBranch()
			{
				return includeBranch;
			}

		public void setIncludeBranch(List<String> includeBranch)
			{
				this.includeBranch = includeBranch;
			}
		
		// ask shesh on how to add or save the organisations .
	}
