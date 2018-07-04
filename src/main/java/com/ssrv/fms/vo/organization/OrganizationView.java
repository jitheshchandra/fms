package com.ssrv.fms.vo.organization;

import java.util.ArrayList;
import java.util.List;

import com.ssrv.fms.vo.branch.BranchView;

public class OrganizationView {

	private Long id;
	private String name;
	private List<BranchView> branches = new ArrayList<BranchView>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BranchView> getBranches() {
		return branches;
	}
	public void setBranches(List<BranchView> branches) {
		this.branches = branches;
	}
	
}
