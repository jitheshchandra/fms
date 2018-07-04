package com.ssrv.fms.service.managedentity.intf;

import java.util.List;

import com.ssrv.fms.vo.managedentity.ManagedEntityMasterVO;

public interface IManagedEntityMasterService {
List<ManagedEntityMasterVO> getAllManagedEntityMaster();
Boolean saveManagedEntityMaster(List<ManagedEntityMasterVO> form);
Boolean deleteManagedEntityMaster(Long managedEntityMasterId);
}
