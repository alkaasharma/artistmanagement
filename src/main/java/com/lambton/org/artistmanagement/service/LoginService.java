package com.lambton.org.artistmanagement.service;

import java.util.HashMap;

import com.lambton.org.artistmanagement.bean.Department;
import com.lambton.org.artistmanagement.bean.Login;
import com.lambton.org.artistmanagement.bean.Role;
import com.lambton.org.artistmanagement.bean.User;
import com.lambton.org.artistmanagement.dao.LoginDao;
import com.lambton.org.artistmanagement.dto.UserDto;

public class LoginService {


	public HashMap<String,Object> checkStatusAndCredentials(Login login)//only credentials
	{   
		HashMap<String,Object> data=new HashMap<String, Object>();
		User user=null;
		LoginDao loginDao=new LoginDao();
		UserDto userDto=loginDao.getLoginStatusAndCredentials(login);
		if (null!=userDto)
		{
			if(loginDao.saveLoginInformation(login))
			{
				Role role=loginDao.getRoleById(userDto.getRoleId());
				Department department=loginDao.getdepartmentById(userDto.getDepartmentId());
				user =new User(userDto.getUserId(),userDto.getFirstName(),userDto.getLastName(),userDto.getPassword(),userDto.getEmail(),
						userDto.getAddress(),userDto.getDob(),userDto.getRegisteredOn(),userDto.getPhoneNumber(), role, department);
				data.put("user",user);	
			}
			else 
			{
				System.out.println("hfjdhfhdj");
				data.put("message","have already logged in");
			}


		}
		else
		{
			data.put("message","wrong credentials");
		}
		return data;

	}
}
