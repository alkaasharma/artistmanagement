package com.lambton.org.artistmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambton.org.artistmanagement.bean.UserBean;
import com.lambton.org.artistmanagement.dao.loginDAOclass;



@Controller
public class LoginController {
	
	@Autowired
	loginDAOclass loginDao;
	
	

	@RequestMapping("login")    
	public String getLogin(Model m){ 
		m.addAttribute("login", new UserBean());
		return "loginForm";
	}    
	
	@RequestMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@RequestMapping("/welcome")
	public String getWelcome() {
		return "welcome";
	}
	
	@RequestMapping(value = "login/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") UserBean login, Model m)
	{
		UserBean user = loginDao.get(login.getEmail(), login.getPassword());
		
		if(user != null) {
			m.addAttribute("username", user.getEmail());
			return "redirect:/welcome";
		}
		
		else {
			m.addAttribute("error", "Invalid username or password");
			return "loginForm";
		}
	}
	
	

}
