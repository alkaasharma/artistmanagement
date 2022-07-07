package com.lambton.org.artistmanagement.bean;

import java.util.Date;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String address;
	private Date dob;
	private Date registeredOn;
	private String phoneNumber;
	private String captcha;
	private Role role;
	private Department department;
	
	public User()
	{
		
	}
	
	
	public User(int userId, String firstName, String lastName, String password, String email, String address, Date dob,
			Date registeredOn, String phoneNumber,Role role, Department department) {
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
		this.role = role;
		this.department = department;
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
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", address=" + address + ", dob=" + dob + ", registeredOn="
				+ registeredOn + ", phoneNumber=" + phoneNumber + ", captcha=" + captcha + ", role=" + role
				+ ", department=" + department + "]";
	}
	
	
	
	
	
}
