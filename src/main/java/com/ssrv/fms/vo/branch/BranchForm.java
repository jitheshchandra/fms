package com.ssrv.fms.vo.branch;

public class BranchForm
{
	private long id;

	private String branchName;

	private long organizationId;

	private String address1;

	private String address2;

	private String address3;

	private String branchState;

	private String branchCountry;

	private String pinCode;

	public String getBranchName()
	{
		return branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(long organizationId)
	{
		this.organizationId = organizationId;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getAddress3()
	{
		return address3;
	}

	public void setAddress3(String address3)
	{
		this.address3 = address3;
	}

	public String getBranchState()
	{
		return branchState;
	}

	public void setBranchState(String branchState)
	{
		this.branchState = branchState;
	}

	public String getBranchCountry()
	{
		return branchCountry;
	}

	public void setBranchCountry(String branchCountry)
	{
		this.branchCountry = branchCountry;
	}

	public String getPinCode()
	{
		return pinCode;
	}

	public void setPinCode(String pinCode)
	{
		this.pinCode = pinCode;
	}

}
