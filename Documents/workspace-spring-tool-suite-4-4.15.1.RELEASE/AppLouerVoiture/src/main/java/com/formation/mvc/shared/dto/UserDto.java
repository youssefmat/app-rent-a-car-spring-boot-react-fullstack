package com.formation.mvc.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	
	private static final long serialVersionUID = 5854291369074287770L;
	
	private long   id;
	private String userId;
	private String fristName;
    private String listeName;
    private String email;
    private String password;
    private String encrypePasseword;
    private String emailVerificationToken;
    private Boolean emainVereficationStatus = false;
    private List<VoitureDto> voitures;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncrypePasseword() {
		return encrypePasseword;
	}
	public void setEncrypePasseword(String encrypePasseword) {
		this.encrypePasseword = encrypePasseword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmainVereficationStatus() {
		return emainVereficationStatus;
	}
	public void setEmainVereficationStatus(Boolean emainVereficationStatus) {
		this.emainVereficationStatus = emainVereficationStatus;
	}
	public List<VoitureDto> getVoitures() {
		return voitures;
	}
	public void setVoitures(List<VoitureDto> voitures) {
		this.voitures = voitures;
	}

}
