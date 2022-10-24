package com.example.metier.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;


@Entity
public class Agence {
	@Id
	
	private String codeAgence;
	
	private String nom;
	
	private String ville;
	
	private String adresse;
	
	private String Contact;
	
	private String Email;
	
	@OneToMany(mappedBy="agence")
	private java.util.List<Client> clients;
	@OneToMany(mappedBy="agence")	
	private java.util.List<Agent> agents;
	
	public String getCodeAgence() {
		return codeAgence;
	}
	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}
	
	
	
	public Agence() {
		super();
	}
	public Agence(String codeAgence, String nom, String ville, String adresse, String contact, String email) {
		super();
		this.codeAgence = codeAgence;
		this.nom = nom;
		this.ville = ville;
		this.adresse = adresse;
		this.Contact = contact;
		this.Email = email;
	}
	public String getContact() {
		return Contact;
	}
	public void setContact(String contact) {
		this.Contact = contact;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	
	
	
}
