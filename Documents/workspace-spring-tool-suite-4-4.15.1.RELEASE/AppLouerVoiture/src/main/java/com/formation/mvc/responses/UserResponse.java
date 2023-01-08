package com.formation.mvc.responses;

public class UserResponse {
	
	private String userId;
	 private String fristName;
	 private String listeName;
    private String email;
    
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public String getListeName() {
		return listeName;
	}
	public void setListeName(String listeName) {
		this.listeName = listeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
