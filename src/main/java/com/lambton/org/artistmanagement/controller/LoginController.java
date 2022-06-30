package com.lambton.org.artistmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.lambton.org.artistmanagement.bean.Department;
import com.lambton.org.artistmanagement.bean.Role;
import com.lambton.org.artistmanagement.bean.User;

@Controller
public class LoginController {


	List<Department> getDepartmentList()
	{
		return new ArrayList<Department>();
	}
	
	
	List<Role> getRoleList()
	{
		return new ArrayList<Role>();
	}
	
	
	void checkLoginStatus()
	{
		
	}
	
	
	
	User getUserById()
	{
		return new User();
	}
	
	
	List<User> getUsersList()
	{
		return new ArrayList<User>();
	}
	
	
	
}
