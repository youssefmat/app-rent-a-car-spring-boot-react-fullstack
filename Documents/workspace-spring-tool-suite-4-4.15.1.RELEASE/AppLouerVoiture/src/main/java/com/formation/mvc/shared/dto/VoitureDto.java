package com.formation.mvc.shared.dto;

import java.io.Serializable;
import java.util.Date;


public class VoitureDto implements Serializable{

	
	private static final long serialVersionUID = -4253819334425639735L;
	
    private Long id;
	private String voitureId;
	private String voitureName;
	private String marque;
	private String modele;
	private Long anneeModel;
	private String ville;
	private Double prix;
	private UserDto user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVoitureId() {
		return voitureId;
	}
	public void setVoitureId(String voitureId) {
		this.voitureId = voitureId;
	}
	public String getVoitureName() {
		return voitureName;
	}
	public void setVoitureName(String voitureName) {
		this.voitureName = voitureName;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public Long getAnneeModel() {
		return anneeModel;
	}
	public void setAnneeModel(Long anneeModel) {
		this.anneeModel = anneeModel;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	

}
