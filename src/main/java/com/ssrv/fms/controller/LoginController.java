package com.ssrv.fms.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssrv.fms.excetptions.FmsException;
import com.ssrv.fms.model.user.User;
import com.ssrv.fms.model.user.UserBranchMapping;
import com.ssrv.fms.service.admin.intf.LoginService;
import com.ssrv.fms.vo.UserForm;

//import com.ssrv.fms.model.Employee;

@Controller
public class LoginController
	{

		@Autowired
		private LoginService LoginServiceImpl;
		HttpSession session = null;
		// Spring Security see this :
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String login()
			{
				return "login";
			}
		@RequestMapping(value = "/ChangePassword", method = RequestMethod.GET)
		public String changePassword(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws FmsException
			{
				HttpSession session = request.getSession(true);
				if (session.getAttribute("userId") == null)
					{
						return "login";
					} else
					{
						try
							{
								long userId = (Long) session.getAttribute("userId");
								String oldPassword = LoginServiceImpl.getPassword(userId);
								/*MessageDigest md = MessageDigest.getInstance("MD5");
								md.update(oldPassword.getBytes());
								byte byteData[] = md.digest();
								// convert the byte to hex format method 1
								StringBuffer sb = new StringBuffer();
								for (int i = 0; i < byteData.length; i++)
									{
										sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
									}*/

								System.out.println("Digest(in hex format):: " + oldPassword);
								model.addAttribute("oldPwd",oldPassword);
								model.addAttribute("userId", userId);
							} catch (FmsException e)
							{
								System.out.println(e);
							} /*catch (NoSuchAlgorithmException e)
							{
								e.printStackTrace();
							}*/
					}
				return "changePassword";
			}

		@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
		public String updatePassword(ModelMap model, UserForm form) throws NoSuchAlgorithmException
			{
				long userId = form.getUserId();
				String newpassword = form.getNewpassword();
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
				LoginServiceImpl.setPassword(newPwd, userId);
				// myscript.RgisterStart
				return "redirect:/Home";
			}

		@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
		public String forgotPassword()
			{
				return "forgotPassword";
			}

		@RequestMapping(value = "/verifylogin", method = RequestMethod.POST)
		public String verifylogin(UserForm form, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
			{
				try
					{
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(form.getPassword().getBytes());
						byte byteData[] = md.digest();
						// convert the byte to hex format method 1
						StringBuffer sb = new StringBuffer();
						for (int i = 0; i < byteData.length; i++)
							{
								sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
							}
						System.out.println("Digest(in hex format):: " + sb.toString());	
						//sb.toString()
						boolean userAuthenticated = LoginServiceImpl.doLogin(form.getUserName(),sb.toString() , true);
						if (userAuthenticated)
							{
								HttpSession session = request.getSession(true);
								User loggedUser = LoginServiceImpl.getUserId(form.getUserName(), sb.toString());
								long userId = Integer.parseInt(loggedUser.getId());
								List<UserBranchMapping> loggedUserDetails = LoginServiceImpl.getUserBranchMappingByUserId(userId);
								/*Organization userOrganizartion = loggedUserDetails.getOrganizationId();
								Branch userBranch = loggedUserDetails.getBranchId();*/
								// Found out setting sessions for variables .
								session.setAttribute("userId", userId);
								session.setAttribute("userDetails", loggedUserDetails);
								return "Home";
							}
						else
							{
								bindingResult.addError(new ObjectError("Invalid", "Invalid credentials. Username or Password is incorrect."));
								model.addAttribute("user_error", " ** Invalid credentials. Username or Password is incorrect.");
								return "login";
							}

					} catch (Exception e)
					{
						e.printStackTrace();
						return "login";
					}
			}
		@RequestMapping(value = "/Home", method = RequestMethod.GET)
		public String home()
			{

				return "Home";

			}

	}
