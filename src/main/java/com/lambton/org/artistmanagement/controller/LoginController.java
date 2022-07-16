package com.lambton.org.artistmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lambton.org.artistmanagement.bean.Department;
import com.lambton.org.artistmanagement.bean.Login;
import com.lambton.org.artistmanagement.bean.Role;
import com.lambton.org.artistmanagement.bean.User;
import com.lambton.org.artistmanagement.service.LoginService;

@Controller
public class LoginController {




	@RequestMapping(value="loginForm" ,method=RequestMethod.GET)
	String openLoginForm(Model model)
	{
		model.addAttribute("login", new Login());
		return "loginForm";
	}


	@RequestMapping(value="loggedIn" ,method=RequestMethod.POST)
	String login(HttpSession session ,@ModelAttribute("login")Login login,Model model)
	{
		LoginService service = new LoginService();
		HashMap<String, Object> data=service.checkStatusAndCredentials(login);
		User user=(User) data.get("user");
		if(null!=user)
		{   

			String role=user.getRole().getRoleName();
			session.setAttribute("loggedInUser", user);
			model.addAttribute("user",user);
			if(role.equals("ADMINISTRATOR"))
			{
				return "welcome_admin";
			}
			else if(role.equals("MANAGER"))
			{
				return "welcome_manager";
			}
			else 
			{
				return "welcome_artist";
			}
		}
		else

		{
			login.setEmail(null);
			login.setPassword(null);
			model.addAttribute("error",data.get("message"));
			return "loginForm";
		}



	}



	@RequestMapping(value="viewArtistProfile", method = RequestMethod.GET)
	ModelAndView viewProfile(HttpSession session)
	{
		User user=(User) session.getAttribute("loggedInUser");//shoud i store only userId?
		System.out.println("from session " +user );
		LoginService loginService=new LoginService();
		Map<String,Object> departmentDataMap= loginService.processDepartmentList();
		Map<String,Object> roleDataMap= loginService.processRoleList();
		List<Department> departmentList=(List<Department>) departmentDataMap.get("departmentList");
		List<Role>roleList=(List<Role>) roleDataMap.get("roleList");
		ModelAndView mv= new ModelAndView();
		if(departmentList!=null && roleList!=null)
		{
			mv.addObject("user",user);//??how i am getting user object without mv.
			mv.addObject("roleList",roleList);
			mv.addObject("departmentList",departmentList);
			mv.setViewName("profile");
		}
		else 
		{
			mv.addObject("error",roleDataMap.get("message"));//??????
			mv.setViewName("error");    	
		}

		return mv;
	}


	@RequestMapping(value="editArtistProfile", method = RequestMethod.POST)
	ModelAndView editProfile(@ModelAttribute("user")User editedInfo,HttpSession session )
	{//if changed email????
		System.out.println(editedInfo);
		ModelAndView mv= new ModelAndView();
		LoginService loginService=new LoginService();
		Map<String,Object> dataMap=loginService.processUpdateInformation(editedInfo);
		User editedUser=(User)dataMap.get("editedUser");
		System.out.println(editedUser);
		if(editedUser!=null)//information updated
		{
			User user=(User) session.getAttribute("loggedInUser");
			session.setAttribute("loggedInUser",editedUser);
			//if password changed
			if(editedUser.getPassword().equals(user.getPassword()))
			{
				//no need to logout continue with process
				Map<String,Object> departmentDataMap= loginService.processDepartmentList();
				Map<String,Object> roleDataMap= loginService.processRoleList();
				mv.addObject("user", editedUser);
				mv.addObject("roleList",(List<Role>)roleDataMap.get("roleList"));
				mv.addObject("departmentList",(List<Department>)departmentDataMap.get("departmentList"));
				mv.addObject("message","profile updated");
				mv.setViewName("profile");

			}
			else 
			{   // need to logout and then logged in
				mv.addObject("login", new Login());
				mv.setViewName("loginForm");

			}

		}
		else
		{
			mv.addObject("error",dataMap.get("message"));//??????
			mv.setViewName("error");
		}

		return mv;

	}



	@RequestMapping(value="viewUsers", method = RequestMethod.GET)
	ModelAndView viewUsers(HttpServletRequest request)
	{   
		ModelAndView mv= new ModelAndView();
		LoginService loginService=new LoginService();
		Map<String,Object> departmentDataMap= loginService.processDepartmentList();
		Map<String,Object> dataMap=loginService.processViewUsers(request);
		List<User> userList=(List<User>) dataMap.get("userList");



		if(userList!=null)
		{		mv.addObject("userList", userList);
		mv.addObject("noOfPages", (Integer)dataMap.get("noOfPages"));
		mv.addObject("currentPage",(Integer)dataMap.get("page"));
		mv.addObject("departmentList",(List<Department>)departmentDataMap.get("departmentList"));
		mv.setViewName("viewUsers");

		}
		else
		{
			mv.addObject("error",dataMap.get("message"));
			mv.setViewName("error");
		}

		return mv;

	}

}
