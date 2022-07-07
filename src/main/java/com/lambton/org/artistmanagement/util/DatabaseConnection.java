package com.lambton.org.artistmanagement.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	public static Connection getConnectionObject() throws IOException, ClassNotFoundException, SQLException
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

}
