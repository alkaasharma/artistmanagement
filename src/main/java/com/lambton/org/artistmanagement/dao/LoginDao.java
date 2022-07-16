package com.lambton.org.artistmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	public UserDto getUserById(int userId) 
	{
		UserDto userDto =null;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = DatabaseConnection.getConnectionObject();
			statement=connection.prepareStatement("select * from users where user_id=?");
			statement.setInt(1,userId);
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



	public List<Department>getDepartmentList()
	{
		Connection connection=null;
		Department department =null;
		List<Department> departmentList=new ArrayList<Department>();
		try 
		{
			connection = DatabaseConnection.getConnectionObject();
			PreparedStatement statement=connection.prepareStatement("select * from department");
			ResultSet resultSet= statement.executeQuery();
			//			can i use if cond to check resuset to create list ovject
			while(resultSet.next())
			{
				department=new Department(resultSet.getInt("department_id"),resultSet.getString("department_name"));
				departmentList.add(department);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 

		return departmentList;

	}

	public List<Role>getRoleList()
	{
		Connection connection=null;
		Role role =null;
		List<Role> roleList=new ArrayList<Role>();
		try 
		{
			connection = DatabaseConnection.getConnectionObject();
			PreparedStatement statement=connection.prepareStatement("select * from roles");
			ResultSet resultSet= statement.executeQuery();
			//			can i use if cond to check resuset to create list ovject
			while(resultSet.next())
			{
				role=new Role(resultSet.getInt("role_id"),resultSet.getString("role_name"));
				roleList.add(role);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 

		return roleList;

	}


	public boolean updateUserInformation(UserDto user) 
	{//put if cond. on the basis of role 

		Connection con=null;
		PreparedStatement stmt=null;
		boolean result=false;
		try 
		{  
			con = DatabaseConnection.getConnectionObject();
			stmt=con.prepareStatement("update users set first_name=?,last_name=?,password=?,email=?,dob=?,address=?,"
					+ "phone_number=?,department_id=? where user_id=?"); 
			stmt.setString(1,user.getFirstName());  
			stmt.setString(2,user.getLastName()); 
			stmt.setString(3,user.getPassword());  
			stmt.setString(4,user.getEmail()); 
			stmt.setDate(5,user.getDob());  
			stmt.setString(6,user.getAddress()); 
			stmt.setString(7,user.getPhoneNumber());  
			stmt.setInt(8,user.getDepartmentId()); 
			stmt.setInt(9,user.getUserId()); 
			if(stmt.executeUpdate()>0)
			{result=true;}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;

	}


	private int noOfRecords;
	public List<UserDto>getAllUsersByPage(int offset, int noOfRecords)
	{
		 String query = "select SQL_CALC_FOUND_ROWS * from users limit " + offset + ", " + noOfRecords;
		Connection connection=null;
		UserDto userDto =null;
		List<UserDto> UserDtoList=new ArrayList<UserDto>();
		try 
		{
			connection = DatabaseConnection.getConnectionObject();
			PreparedStatement statement=connection.prepareStatement("select SQL_CALC_FOUND_ROWS * from users limit " + offset + ", " + noOfRecords);
			System.out.println(query);
			ResultSet resultSet= statement.executeQuery();
			while(resultSet.next())
			{
				userDto=new UserDto(resultSet.getInt("user_id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
						resultSet.getString("password"),resultSet.getString("email"),resultSet.getString("address"),
						resultSet.getDate("dob"),resultSet.getDate("registered_on"),resultSet.getString("phone_number"),
						resultSet.getInt("role_id"),resultSet.getInt("department_id"));
				UserDtoList.add(userDto);
			}
			
			resultSet.close();
			resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
  
            if (resultSet.next())
               this.noOfRecords = resultSet.getInt(1);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 

		return UserDtoList;

	}

	public int getNoOfRecords() { return noOfRecords; }
}
