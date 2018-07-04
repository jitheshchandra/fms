package com.ssrv.fms.restFullAPIController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssrv.fms.model.checklist.Checklists;
import com.ssrv.fms.service.User.Intf.UserIntf;
import com.ssrv.fms.service.managedentity.intf.CheckListIntf;
import com.ssrv.fms.vo.checklist.CheckListForm;
import com.ssrv.fms.vo.user.UserBranchMappingVO;

    @RestController
    public class getCheckListAPIController 
    { 	
      @Autowired
      CheckListIntf checkListImpl;
    
      @Autowired
      UserIntf userImpl;
	
      @POST
      @Consumes(MediaType.APPLICATION_JSON)
      @Produces(MediaType.APPLICATION_JSON)
      @RequestMapping(value="/restGetCheckList")
	  public Map<String, Object> restGetCheckList(@RequestParam Long userId,HttpServletRequest request, HttpServletResponse response)
	  {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		try
			{
				if (session.getAttribute("userId") == null
						|| session.getAttribute("userId").equals(""))
					{
					String error="Please login first";
					resultMap.put("Error", error);
					return resultMap;
					}
				List<UserBranchMappingVO> userBranchMaping = userImpl.getAllUserBarnchMappingByUserId(userId);
				if(!userBranchMaping.isEmpty())
				{
				  UserBranchMappingVO userBranchMappingObject = userBranchMaping.get(0);		
			      List<Checklists> checkList = checkListImpl.getCheckList(userBranchMappingObject.getDefaultOraganization(), userBranchMappingObject.getDefaultBranch());
				  List<CheckListForm> formList=new ArrayList<CheckListForm>();
				  for(Checklists list:checkList)
				  {
					 formList.add(new CheckListForm(list));
				  }
				  if(!formList.isEmpty())
				  {
				  resultMap.put("checkList", formList);
				  }
				  else
				  {
					  String NoData="Sorry no check list for this id";
					  resultMap.put("No-Checklist", NoData);
				  }
				  return resultMap;
				}
				else
				{
					String error="Sorry no user by this Id";
					resultMap.put("Erorr", error);
				}
			}
		catch (Exception ex)
			{
				System.out.println(ex.getMessage());
				return resultMap;
			}
		return resultMap;
	   }
    }
