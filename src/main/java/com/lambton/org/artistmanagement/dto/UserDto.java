package com.lambton.org.artistmanagement.dto;

import java.sql.Date;

public class UserDto {

	private int userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String address;
	private Date dob;
	private Date registeredOn;
	private String phoneNumber;
	private int roleId;
	private int departmentId;
	
	
	
	public UserDto(int userId, String firstName, String lastName, String password, String email, String address,
			Date dob, Date registeredOn, String phoneNumber, int roleId, int departmentId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.registeredOn = registeredOn;
		this.phoneNumber = phoneNumber;
		this.roleId = roleId;
		this.departmentId = departmentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getRegisteredOn() {
		return registeredOn;
	}
	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", address=" + address + ", dob=" + dob + ", registeredOn="
				+ registeredOn + ", phoneNumber=" + phoneNumber + ", roleId=" + roleId + ", departmentId="
				+ departmentId + "]";
	}
	
	
}
