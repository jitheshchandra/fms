package com.ssrv.fms.service.managedentity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssrv.fms.dao.impl.BaseDAO;
import com.ssrv.fms.model.managedentity.ManagedEntityMaster;
import com.ssrv.fms.service.managedentity.intf.IManagedEntityMasterService;
import com.ssrv.fms.vo.managedentity.ManagedEntityMasterVO;

@Lazy
@Service
@Transactional
public class ManagedEntityMasterImpl implements IManagedEntityMasterService {
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	BaseDAO baseDao;

	@Override
	public List<ManagedEntityMasterVO> getAllManagedEntityMaster() {
		try {
			//List<ManagedEntityMaster> modelList = baseDao.getAll(ManagedEntityMaster.class);
			@SuppressWarnings("unchecked")
			List<ManagedEntityMaster> modelList=entityManager.createQuery("SELECT m FROM ManagedEntityMaster m WHERE m.isDeleted=0").getResultList();
			List<ManagedEntityMasterVO> formList = new ArrayList<ManagedEntityMasterVO>();
			for (ManagedEntityMaster list : modelList) {
				formList.add(new ManagedEntityMasterVO(list));
			}
			return formList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean saveManagedEntityMaster(List<ManagedEntityMasterVO> formList) {
		try {
			for (ManagedEntityMasterVO form : formList) {
				if (form.getId() != null) {
					ManagedEntityMaster model = baseDao.findById(
							ManagedEntityMaster.class, form.getId());
					model.setName(form.getName());

				} else {
					ManagedEntityMaster model = new ManagedEntityMaster();
					model.setName(form.getName());
					model.setIsDeleted((short) 0);
					entityManager.persist(model);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteManagedEntityMaster(Long managedEntityMasterId) {
		try {
			baseDao.findById(ManagedEntityMaster.class, managedEntityMasterId)
					.setIsDeleted((short) 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
