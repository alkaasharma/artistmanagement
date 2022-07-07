package com.lambton.org.artistmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.lambton.org.artistmanagement.bean.Department;
import com.lambton.org.artistmanagement.bean.Login;
import com.lambton.org.artistmanagement.bean.Role;
import com.lambton.org.artistmanagement.dto.UserDto;
import com.lambton.org.artistmanagement.util.DatabaseConnection;

public class LoginDao {

	public UserDto getLoginStatusAndCredentials(Login login) 
	{
		UserDto userDto =null;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = DatabaseConnection.getConnectionObject();
			statement=connection.prepareStatement("select * from users where email=? and password =?");
			statement.setString(1,login.getEmail());
			statement.setString(2,login.getPassword());
			ResultSet result=statement.executeQuery();	
			while(result.next())
			{
				userDto=new UserDto(result.getInt("user_id"),result.getString("first_name"),result.getString("last_name"),result.getString("password"),result.getString("email"),result.getString("address"),
						result.getDate("dob"),result.getDate("registered_on"),result.getString("phone_number"),result.getInt("role_id"), result.getInt("department_id"));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return userDto;		

	}


	public boolean saveLoginInformation(Login login) 
	{
		Connection con=null;
		PreparedStatement stmt=null;
		boolean result=false;
		try {
			con = DatabaseConnection.getConnectionObject();
			stmt=
					con.prepareStatement("insert into login (email,password,logged_in_at,logged_out_at,login_status)"
							+ "values(?,?,?,?,?)");  
			stmt.setString(1,login.getEmail());  
			stmt.setString(2,login.getPassword()); 
			stmt.setDate(3,new Date(System.currentTimeMillis()));  
			stmt.setDate(4,null); 
			stmt.setBoolean(5,true);  

			if(stmt.executeUpdate()>0) {result= true;}

		}catch (Exception e) 
		{

			e.printStackTrace();
		}
		return result;


	}
	public	Role getRoleById(int roleId)
	{
		Role role =null;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = DatabaseConnection.getConnectionObject();
			statement=connection.prepareStatement("select * from roles where role_id=?");
			statement.setInt(1,roleId);
			ResultSet result=statement.executeQuery();	
			while(result.next())
			{
				role=new Role(result.getInt("role_id"),result.getString("role_name"));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return role;		

	}


	public Department getdepartmentById(int departmentId)
	{
		Department department =null;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = DatabaseConnection.getConnectionObject();
			statement=connection.prepareStatement("select * from department where department_id=?");
			statement.setInt(1,departmentId);
			ResultSet result=statement.executeQuery();	
			while(result.next())
			{
				department=new Department(result.getInt("department_id"),result.getString("department_name"));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return department;
	}

}
