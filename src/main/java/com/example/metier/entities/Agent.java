package com.example.metier.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@DiscriminatorValue("agent")
public class Agent extends Utilisateur {
	@ManyToOne(optional=false)
@JoinColumn(name="codeAgence")
private Agence agence;

public Agence getAgence() {
	return agence;
}

public void setAgence(Agence agence) {
	this.agence = agence;
}

public Agent() {
	super();
	// TODO Auto-generated constructor stub
}




public Agent(String cin, String telephone, String adresse, String password, String nom, String prenom, String email,
		String sexe, Date date,Agence agence) {
	super(cin, telephone, adresse, password, nom, prenom, email, sexe, date);
	this.agence=agence;
	// TODO Auto-generated constructor stub
}

public Agent(String cin) {
	super(cin);
	// TODO Auto-generated constructor stub
}



}
