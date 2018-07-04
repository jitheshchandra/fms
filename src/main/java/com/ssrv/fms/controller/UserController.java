package com.ssrv.fms.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrv.fms.model.user.User;
import com.ssrv.fms.service.User.Intf.UserIntf;
import com.ssrv.fms.service.branch.intf.BranchService;
import com.ssrv.fms.service.organization.intf.OrganizationService;
import com.ssrv.fms.vo.BranchSummary;
import com.ssrv.fms.vo.organization.OrganizationSummary;
import com.ssrv.fms.vo.user.CreateUserForm;
import com.ssrv.fms.vo.user.UserBranchMappingVO;

@Controller
public class UserController
	{
		@Autowired
		UserIntf userImpl;
		
		@Autowired
		BranchService branchImpl;
		
		@Autowired
		OrganizationService organizationImpl;
		

		@RequestMapping(value = "/UserSummary")
		public String userSummary()
			{
				return "UserSummary";
			}
		
		@RequestMapping(value="/getAllUser",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> getAllUser(@RequestParam String orgId,@RequestParam String brchId)
		{
			Map<String, Object> resultList=new HashMap<String, Object>();
			List<User> users = userImpl.getAllUser(Long.parseLong(orgId),Long.parseLong(brchId ));
			List<CreateUserForm> form=new ArrayList<CreateUserForm>();
			for(User user:users)
				{
					CreateUserForm newForm=new CreateUserForm();
					newForm.setUserName(user.getUserName());
					newForm.setUserTypeId(user.getUserTypeId());
					newForm.setId(user.getId());
					form.add(newForm);
				}
			resultList.put("users", form);
			return resultList;	
		}
		
		@RequestMapping(value="/EditUserTest",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> getUserById(Model model, @RequestParam String userId)
		{
			Map<String, Object> resultList=new HashMap<String, Object>();	
			List<UserBranchMappingVO> userBranchMappingList = userImpl.getAllUserBarnchMappingByUserId(Long.parseLong(userId));	
			
			HashSet<Long> organizationSet=new  HashSet<Long>();
			HashSet<Long> branchSet=new HashSet<Long>();		
			List<BranchSummary> listOfBranch=new ArrayList<BranchSummary>();		
			for(UserBranchMappingVO addingOrgId: userBranchMappingList)
				{
					organizationSet.add(addingOrgId.getOrganizationId());
				}		
			for(UserBranchMappingVO addingBranchId:userBranchMappingList)
				{
					branchSet.add(addingBranchId.getBranchId());
				}		
			for(Long orgId:organizationSet)
				{
					List<BranchSummary> branches= branchImpl.getBranchesByOrgId(orgId);	
					for(BranchSummary branch:branches)
						{
							for(Long selectBranch:branchSet)
								{
									if(selectBranch==branch.getId())
										{
											branch.setSelected(true);
										}
								}
							for(UserBranchMappingVO defaultBarnch:userBranchMappingList)
								{
									if(branch.getId()==defaultBarnch.getDefaultBranch())
										{
											branch.setDefault(true);
										}
								}					
							listOfBranch.add(branch);
						}
				}
			
			List<OrganizationSummary> organizations = organizationImpl.getallOrganizationDetails();
			for(OrganizationSummary org:organizations)
				{
					for(Long selectedbranches:organizationSet)
						{
							if(selectedbranches==org.getOrgId())
								{
									org.setSelected(true);
								}
						}
					for(UserBranchMappingVO defaultOrganization:userBranchMappingList)
						{
							if(org.getOrgId()==defaultOrganization.getDefaultOraganization())
								{
									org.setDefault(true);
								}
						}
				}
			resultList.put("organizationList", organizations);
			resultList.put("ListOfBarnch", listOfBranch);		
			return resultList;
		}
		
		@RequestMapping(value="/EditUser",method=RequestMethod.GET)
		public String getUserByIdForFirstPage(Model model, @RequestParam String userId)
		{
			User user = userImpl.getUserById(userId);
			model.addAttribute("user", user);
			return "EditUser";
		}
		
		@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
		public @ResponseBody boolean deleteUser(@RequestParam String userId){
			boolean result = userImpl.deleteUser(userId);
			return result;
		}
		
		@RequestMapping(value = "/AddUser", method = RequestMethod.GET)
		public String addUser()
			{
				return "AddUser";
			}

		@RequestMapping(value = "/restTest/saveUserType", method = RequestMethod.POST)
		public String addUser(CreateUserForm form, ModelMap model) throws NoSuchAlgorithmException
			{			
				if(form.getId()==null)
					{
				String newpassword = form.getPassword();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(newpassword.getBytes());
				byte byteData[] = md.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++)

					{
						sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
					}
				System.out.println("Digest(in hex format):: " + sb.toString());
				String newPwd = sb.toString();
				form.setPassword(newPwd);
				
					}
				userImpl.saveUser(form);
				return "redirect:/UserSummary";
			}
		
		//Get all user to check availability
		@RequestMapping(value="/getAllUsersToCheckAvailability",method={RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody List<CreateUserForm> getAllUserToCheckAvailabilty()
		{
			List<User> userList = userImpl.getAllUsers();
			List<CreateUserForm> checkUserList=new ArrayList<CreateUserForm>();
			for(User list:userList)
			{
				CreateUserForm form=new CreateUserForm();
				form.setUserName(list.getUserName());
				checkUserList.add(form);
			}
			return checkUserList;
		}
	}
