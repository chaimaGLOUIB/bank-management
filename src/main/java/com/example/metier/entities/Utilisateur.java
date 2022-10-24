package com.example.metier.entities;

import java.sql.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role")

public class Utilisateur {
	@Id 
	protected String cin;
	
	public Utilisateur(String cin) {
		super();
		this.cin = cin;
	}
	private String telephone;
	private String adresse;
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private String sexe;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getcin() {
		return cin;
	}
	public void setcin(String cin) {
		this.cin = cin;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Utilisateur(String cin, String telephone, String adresse, String password, String nom, String prenom,
			String email, String sexe, Date date) {
		super();
		this.cin = cin;
		this.telephone = telephone;
		this.adresse = adresse;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.sexe = sexe;
		this.date = date;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
