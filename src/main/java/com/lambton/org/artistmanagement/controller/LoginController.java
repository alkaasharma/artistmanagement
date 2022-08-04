package com.lambton.org.artistmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
			model.addAttribute("user",user);//do i need it can't i use session to getuser information 
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
		LoginService loginService=new LoginService();
		Map<String,Object> departmentDataMap= loginService.processDepartmentList();
		Map<String,Object> roleDataMap= loginService.processRoleList();
		List<Department> departmentList=(List<Department>) departmentDataMap.get("departmentList");
		List<Role>roleList=(List<Role>) roleDataMap.get("roleList");
		ModelAndView mv= new ModelAndView();
		if(departmentList!=null && roleList!=null)
		{
			mv.addObject("user",user);
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
	{
		ModelAndView mv= new ModelAndView();
		LoginService loginService=new LoginService();
		Map<String,Object> dataMap=loginService.processUpdateInformation(editedInfo);
		User editedUser=(User)dataMap.get("editedUser");
		if(editedUser!=null)//information updated
		{
			User user=(User) session.getAttribute("loggedInUser");

			if(user.getUserId()==editedUser.getUserId())
			{session.setAttribute("loggedInUser",editedUser);
			if(editedUser.getPassword().equals(user.getPassword()))
			{
				//no need to logout continue with process if password same
				Map<String,Object> departmentDataMap= loginService.processDepartmentList();
				Map<String,Object> roleDataMap= loginService.processRoleList();
				mv.addObject("user", editedUser);
				mv.addObject("roleList",(List<Role>)roleDataMap.get("roleList"));
				mv.addObject("departmentList",(List<Department>)departmentDataMap.get("departmentList"));
				mv.addObject("message","profile updated");
				mv.setViewName("profile");

			}
			else 
			{   //if password changed need to logout and then logged in again
				mv.addObject("login", new Login());
				mv.setViewName("loginForm");

			}
			}

			else 
			{
				mv.setViewName("redirect:/viewUsers");	
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
		{mv.addObject("userList", userList);
		mv.addObject("selectedDepartment",request.getParameter("departmentId"));
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



	@RequestMapping(value="updateUser/{userId}")
	String updateUser(@PathVariable int userId,Model model)
	{
		LoginService loginService=new LoginService();
		Map<String,Object> dataMap=	loginService.processUserById(userId);
		User user=(User)dataMap.get("user");
		if(null!=user)
		{   Map<String,Object> departmentDataMap= loginService.processDepartmentList();
		model.addAttribute("departmentList",(List<Department>)departmentDataMap.get("departmentList"));
		model.addAttribute("user", user);
		return "profile";
		}
		else 
		{
			model.addAttribute("error", dataMap.get("message"));
			return "redirect:/viewUsers";
		}

	}



	@RequestMapping(value="deleteUser/{userId}")
	String deleteUser(@PathVariable int userId,Model model)
	{
		LoginService loginService=new LoginService();
		boolean result=loginService.processUserDeletionById(userId);

		if(!result)
		{   
			model.addAttribute("error","something went wrong please try again");
			return "viewUsers";
		}
		else 
		{
			return "redirect:/viewUsers"; /* need of "/" beforeviewusers */
		}

	}
	
	

	@RequestMapping(value="accessRequest/{id}")
	@ResponseBody//??
	String accessRequest(@PathVariable int id)
	{
		LoginService service=new LoginService();
		if(service.processAccessRequest(id))
		{
		   	return "request is pending";
		}
		else
		{
			return "error";	
		}
		
	}


	

}
