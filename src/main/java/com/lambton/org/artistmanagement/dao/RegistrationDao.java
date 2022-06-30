package com.lambton.org.artistmanagement.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.lambton.org.artistmanagement.dto.UserDto;

public class RegistrationDao 
{

	Connection getConnectionObject() throws IOException, ClassNotFoundException, SQLException
	{
		FileInputStream fis=new FileInputStream("E:/workspace/lambton_wil_project/artistmanagement/src/main/resources/connection.properties"); 
		Properties p=new Properties (); 
		p.load (fis); 
		String dname= (String) p.get ("Dname"); 
		String url= (String) p.get ("URL"); 
		String username= (String) p.get ("Uname"); 
		String password= (String) p.get ("password"); 
		Class.forName(dname); 
		return DriverManager.getConnection(url, username, password); 
	}


	public boolean saveUserInformation(UserDto user) throws Exception
	{
		
		Connection con=getConnectionObject();
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
