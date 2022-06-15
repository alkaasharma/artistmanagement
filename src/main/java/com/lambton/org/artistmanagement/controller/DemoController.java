package com.lambton.org.artistmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lambton.org.artistmanagement.bean.UserBean;

@Controller
public class DemoController {

	@RequestMapping("/login")    
	public String showform(Model m){    
		m.addAttribute("user", new UserBean());  
		return "loginForm";   
	}    

}