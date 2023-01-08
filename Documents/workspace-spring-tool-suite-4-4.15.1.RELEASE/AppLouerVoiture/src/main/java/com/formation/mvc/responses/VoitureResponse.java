package com.formation.mvc.responses;


public class VoitureResponse {
	
	private String voitureId;
	private String voitureName;
	private String marque;
	private String modele;
	private Long anneeModel;
	private String ville;
	private Double prix;
	
	
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
	

}
