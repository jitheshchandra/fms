package com.ssrv.fms.service.admin.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.service.admin.intf.ChangeBranchIntf;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;

@Service
@Lazy
public class ChangeBranchImpl implements ChangeBranchIntf {

	@PersistenceContext
	private EntityManager entityManager;

	public UserBranchMapping getUserBranchMapping(long userId) {
		UserBranchMapping changeBranch = entityManager.find(
				UserBranchMapping.class, userId);
		return changeBranch;
	}

	@Transactional
	public void updateUserBranchMapping(UserBranchMappingForm form) {

		@SuppressWarnings("unchecked")
		List<UserBranchMapping> mappingList = entityManager.createQuery(
				"SELECT b FROM UserBranchMapping b WHERE b.userId="
						+ form.getUserId()).getResultList();
		for (UserBranchMapping list : mappingList) {
			list.setDefaultBranch(form.getDefaultBranchId());
			entityManager.merge(list);
		}
		/*
		 * entityManager.createQuery("") Branch branchId =
		 * entityManager.find(Branch.class, form.getBranchId());
		 * branchMapping.setBranchId(branchId);
		 * entityManager.merge(branchMapping);
		 */
	}

}
