package com.formation.mvc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="voitures")
public class VoitureEntity implements Serializable {
	
	private static final long serialVersionUID = -2314524547419760156L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,length = 35)
	private String voitureId;
	
	@Column(nullable = false,length = 40)
	private String voitureName;
	
	@Column(nullable = false,length = 30)
	private String marque;
	
	@Column(nullable = false,length = 20)
	private String modele;
	
	@Column(nullable = false)
	private Long anneeModel;
	
	@Column(nullable = true)
	private String voiturePhoto;
	
	@Column(nullable = false,length = 100)
	private String ville;
	
	@Column(nullable = false)
	private Double prix;
	
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;
	
	
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
	public String getVoiturePhoto() {
		return voiturePhoto;
	}
	public void setVoiturePhoto(String voiturePhoto) {
		this.voiturePhoto = voiturePhoto;
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
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
	
	
	

}
