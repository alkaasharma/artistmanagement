package com.lambton.org.artistmanagement.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lambton.org.artistmanagement.bean.Login;
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
		User user=(User) session.getAttribute("loggedInUser");

		return new ModelAndView("profile","user",user);
	}


	@RequestMapping(value="editArtistProfile", method = RequestMethod.POST)
	ModelAndView editProfile(User user )
	{
		
		return null;

	}




}
