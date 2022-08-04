package com.lambton.org.artistmanagement.bean;

public class AccessRequest {

	private int requestId;
	private int userId;
	private String userRole;
	private String requestStatus;


	
	
	public AccessRequest(int userId, String userRole, String requestStatus) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.requestStatus = requestStatus;
	}

	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserName(String userRole) {
		this.userRole = userRole;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}


}
