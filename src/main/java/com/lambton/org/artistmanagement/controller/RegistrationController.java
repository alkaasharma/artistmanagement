package com.lambton.org.artistmanagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lambton.org.artistmanagement.bean.User;
import com.lambton.org.artistmanagement.service.RegistrationService;
import com.lambton.org.artistmanagement.util.CaptchaUtil;

@Controller
public class RegistrationController {

	@RequestMapping(value="/registration" , method = RequestMethod.GET)
	public String showRegistrationForm(Model model)
	{   model.addAttribute("user", new User());
	return "signUp";
	}



	@RequestMapping(value="/saveInfo", method = RequestMethod.POST)
	public ModelAndView saveUserInformation(@ModelAttribute("user") User user,HttpSession session)
	{
		RegistrationService service =new RegistrationService();
		boolean  registeredOrNot=service.registrationProcess(user);
		Boolean captchaResult=CaptchaUtil.matchCaptchaCode((String)session.getAttribute("CAPTCHA"),user.getCaptcha());
		if(!captchaResult || !registeredOrNot ) 
		{   user.setCaptcha(null);
		return new ModelAndView("signUp","user",user);
		}

		else {
			ModelAndView modelView= new ModelAndView();
			modelView.addObject("user",user);
			modelView.addObject("message","registration has been completed");
			modelView.setViewName("success");
			return modelView;
		}
	}


}
