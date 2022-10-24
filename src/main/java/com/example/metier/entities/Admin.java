package com.example.metier.entities;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import antlr.collections.List;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Utilisateur {
	
	


	


	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String cin, String telephone, String adresse, String password, String nom, String prenom, String email,
			String sexe, Date date) {
		super(cin, telephone, adresse, password, nom, prenom, email, sexe, date);
		// TODO Auto-generated constructor stub
	}

	public Admin(String cin) {
		super(cin);
		// TODO Auto-generated constructor stub
	}
	

	
	
    
}
