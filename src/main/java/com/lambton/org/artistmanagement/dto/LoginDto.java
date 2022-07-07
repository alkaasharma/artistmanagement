package com.lambton.org.artistmanagement.dto;

import java.sql.Date;

public class LoginDto {
	private String email;
	private String password;
	private Date loggedInAt;
	private Date loggedOutAt;
	private boolean loginStatus;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLoggedInAt() {
		return loggedInAt;
	}
	public void setLoggedInAt(Date loggedInAt) {
		this.loggedInAt = loggedInAt;
	}
	public Date getLoggedOutAt() {
		return loggedOutAt;
	}
	public void setLoggedOutAt(Date loggedOutAt) {
		this.loggedOutAt = loggedOutAt;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	
}
