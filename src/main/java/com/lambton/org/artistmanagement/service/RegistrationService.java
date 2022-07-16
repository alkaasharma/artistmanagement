package com.lambton.org.artistmanagement.service;

import java.sql.Date;

import com.lambton.org.artistmanagement.bean.User;
import com.lambton.org.artistmanagement.dao.RegistrationDao;
import com.lambton.org.artistmanagement.dto.UserDto;

public class RegistrationService 
{


	public boolean  registrationProcess(User userBean)
	{   
		UserDto user=new UserDto(userBean.getUserId(),userBean.getFirstName(),userBean.getLastName(),
				userBean.getPassword(),userBean.getEmail(),userBean.getAddress(),new Date(userBean.getDob().getTime()),
				new Date(System.currentTimeMillis()), userBean.getPhoneNumber(),1,1);//1 for normal users  
		System.out.println(user);
		RegistrationDao dao =new RegistrationDao();
		boolean registeredOrNot=false;

		try {
			if(dao.saveUserInformation(user)) 
			{registeredOrNot=true;}


		} catch (Exception e) {

			e.printStackTrace();
		}
		return registeredOrNot;
	}


}
