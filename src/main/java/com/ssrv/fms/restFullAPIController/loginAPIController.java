package com.ssrv.fms.restFullAPIController;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssrv.fms.model.user.User;
import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.service.admin.intf.LoginService;
import com.ssrv.fms.vo.UserBranchMappingForm.UserBranchMappingForm;

@RestController
public class loginAPIController {
	@Autowired
	private LoginService LoginServiceImpl;
	HttpSession session = null;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/restLogin")
    public @ResponseBody Map<String, Object> verifylogin(@RequestParam String userName,@RequestParam String password,ModelMap model, HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		try
			{
			    Map<String, Object> resultMap=new HashMap<String, Object>();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte byteData[] = md.digest();
				
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++)
					{
						sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
					}
				System.out.println("Digest(in hex format):: " + sb.toString());	
				boolean userAuthenticated = LoginServiceImpl.doLogin(userName,sb.toString() , true);
				if (userAuthenticated)
					{
					    List<UserBranchMappingForm> formList=new ArrayList<UserBranchMappingForm>();
					
						HttpSession session = request.getSession(true);
						User loggedUser = LoginServiceImpl.getUserId(userName, sb.toString());
						long userId = Integer.parseInt(loggedUser.getId());
						List<UserBranchMapping> loggedUserDetails = LoginServiceImpl.getUserBranchMappingByUserId(userId);
						session.setAttribute("userId", userId);
						session.setAttribute("userDetails", loggedUserDetails);
						for(UserBranchMapping list:loggedUserDetails)
						{
							formList.add(new UserBranchMappingForm(list));
						}
						resultMap.put("user", formList);
						return resultMap;
					}
				else
					{
						String errorMsg="Invalid credentials, Username or Password is incorrect.";
						model.addAttribute("user_error", " ** Invalid credentials. Username or Password is incorrect.");
						resultMap.put("Error", errorMsg);
						return resultMap;
					}

			} catch (Exception e)
			{
				e.printStackTrace();
				return model;
			}	
	} 
}