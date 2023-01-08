package com.formation.mvc.requests;

public class UserRequest {
	
	private String fristName;
    private String listeName;
    private String email;
    private String password;

    
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
