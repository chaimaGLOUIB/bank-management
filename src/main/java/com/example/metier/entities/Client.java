package com.example.metier.entities;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@DiscriminatorValue("client")
public class Client  extends Utilisateur {
	@ManyToOne(optional=false)

	@JoinColumn(name="codeAgence")
	private Agence agence;
	
	
	
 @ManyToMany(fetch = FetchType.EAGER,mappedBy="clients")
   
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Compte> comptes;

	public Agence getAgence() {
		return agence;
	}


	public void setAgence(Agence agence) {
		this.agence = agence;
	}


	public List<Compte> getComptesE() {
		return comptes;
	}


	public void setComptesE(List<Compte> comptesE) {
		this.comptes = comptesE;
	}


	


	public Client(String cin) {
		super(cin);
		
		// TODO Auto-generated constructor stub
	}
	


	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Client(String cin, Agence agence, List<Compte> comptes) {
		super(cin);
		this.agence = agence;
		this.comptes = comptes;
	}


	public Client(String cin, String telephone, String adresse, String password, String nom, String prenom,
			String email, String sexe, Date date) {
		super(cin, telephone, adresse, password, nom, prenom, email, sexe, date);
		// TODO Auto-generated constructor stub
	}


	

	



	
	
	
	
}