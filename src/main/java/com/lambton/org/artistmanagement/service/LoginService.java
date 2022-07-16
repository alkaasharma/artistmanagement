package com.lambton.org.artistmanagement.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lambton.org.artistmanagement.bean.Department;
import com.lambton.org.artistmanagement.bean.Login;
import com.lambton.org.artistmanagement.bean.Role;
import com.lambton.org.artistmanagement.bean.User;
import com.lambton.org.artistmanagement.dao.LoginDao;
import com.lambton.org.artistmanagement.dto.UserDto;

public class LoginService {


	public HashMap<String,Object> checkStatusAndCredentials(Login login)//only credentials implement status as well??
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
				System.out.println("user in login "+user);
				System.out.println(user);
				data.put("user",user);	
			}
			else 
			{
				data.put("message","have already logged in");
			}


		}
		else
		{
			data.put("message","wrong credentials");
		}
		return data;

	}


	public Map<String,Object> processDepartmentList()
	{
		Map<String,Object> dataMap= new LinkedHashMap<String, Object>();
		LoginDao loginDao= new LoginDao();
		List<Department> departmentList= loginDao.getDepartmentList();
		if(departmentList!=null)//if(null!=departmentList)
		{
			dataMap.put("departmentList", departmentList);

		}
		else
		{
			dataMap.put("message","server error");
		}
		return dataMap;
	}


	public Map<String,Object> processRoleList()
	{
		Map<String,Object> dataMap= new LinkedHashMap<String, Object>();
		LoginDao loginDao= new LoginDao();
		List<Role> roleList= loginDao.getRoleList();
		if(roleList!=null)
		{
			dataMap.put("roleList", roleList);

		}
		else
		{
			dataMap.put("message","server error");
		}
		return dataMap;
	}



	public Map<String,Object> processUpdateInformation(User editedInfo)
	{
		Map<String,Object> dataMap= new LinkedHashMap<String, Object>();
		UserDto dto=new UserDto(editedInfo.getUserId(),editedInfo.getFirstName(),editedInfo.getLastName(),editedInfo.getPassword(),editedInfo.getEmail(),
				editedInfo.getAddress(),new Date(editedInfo.getDob().getTime()),null,
				editedInfo.getPhoneNumber(),editedInfo.getRole().getRoleId(),editedInfo.getDepartment().getDepartmentId());
		System.out.println(dto);
		LoginDao loginDao= new LoginDao();
		if(loginDao.updateUserInformation(dto))
		{   Role role=loginDao.getRoleById(dto.getRoleId());
		Department department=loginDao.getdepartmentById(dto.getDepartmentId());
		User editedUser= new User(dto.getUserId(),dto.getFirstName(),dto.getLastName(),dto.getPassword(),dto.getEmail(),
				dto.getAddress(),new Date(dto.getDob().getTime()),null,
				dto.getPhoneNumber(),role,department);
		dataMap.put("editedUser", editedUser);
		}
		else 
		{
			dataMap.put("message","something went wrong please try again");
		}


		return dataMap;
	}

	public Map<String,Object> processViewUsers(HttpServletRequest request)
	{   

		Map<String,Object> dataMap= new LinkedHashMap<String, Object>();
		LoginDao dao =new LoginDao();
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(
					request.getParameter("page"));
		List<UserDto> userDtoList=dao.getAllUsersByPage((page - 1) * recordsPerPage,recordsPerPage);
		int noOfRecords = dao.getNoOfRecords();
		int noOfPages = (int)Math.ceil(noOfRecords * 1.0/ recordsPerPage);
		List<User> userList=null;
		if(userDtoList!=null)
		{   userList= new ArrayList<User>();
		for(UserDto dto:userDtoList)
		{   Role role=dao.getRoleById(dto.getRoleId());
		Department department=dao.getdepartmentById(dto.getDepartmentId());
		User user= new User(dto.getUserId(),dto.getFirstName(),dto.getLastName(),dto.getPassword(),dto.getEmail(),
				dto.getAddress(),new Date(dto.getDob().getTime()),null,
				dto.getPhoneNumber(),role,department);
		userList.add(user);
		}
		dataMap.put("userList", userList);
		dataMap.put("noOfPages", noOfPages);
		dataMap.put("currentPage", page);
		}
		else 
		{
			dataMap.put("message","something went wrong please try again");
		}


		return dataMap;
	}


}
