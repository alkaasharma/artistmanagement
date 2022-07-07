package com.lambton.org.artistmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lambton.org.artistmanagement.dto.UserDto;
import com.lambton.org.artistmanagement.util.DatabaseConnection;

public class RegistrationDao 
{




	public boolean saveUserInformation(UserDto user) throws Exception
	{

		Connection con=DatabaseConnection.getConnectionObject();
		PreparedStatement stmt=
				con.prepareStatement("insert into users (first_name,last_name,password,email,dob,address,"
						+ "phone_number,registered_on,role_id,department_id) "
						+ "values(?,?,?,?,?,?,?,?,?,?)");  
		stmt.setString(1,user.getFirstName());  
		stmt.setString(2,user.getLastName()); 
		stmt.setString(3,user.getPassword());  
		stmt.setString(4,user.getEmail()); 
		stmt.setDate(5,user.getDob());  
		stmt.setString(6,user.getAddress()); 
		stmt.setString(7,user.getPhoneNumber());  
		stmt.setDate(8,user.getRegisteredOn());  
		stmt.setInt(9,user.getRoleId());
		stmt.setInt(10,user.getDepartmentId()); 

		if(!(stmt.executeUpdate()>0)) {return false;}
		else return true;


	}

}
