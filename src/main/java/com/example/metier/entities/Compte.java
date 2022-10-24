package com.example.metier.entities;

import java.io.Serializable;

/***********************************************************************
 * Module:  Compte.java
 * Author:  soufiane
 * Purpose: Defines the Class Compte
 ***********************************************************************/

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;





@Entity

public class Compte implements Serializable {
 
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
   private String codeCompte;
	private String type;
	private double taux;
	private double decouvert;
	private double soldMax;
	
	
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public double getSoldMax() {
		return soldMax;
	}
	public void setSoldMax(double soldMax) {
		this.soldMax = soldMax;
	}
	public Compte(String codeCompte, String type, double taux, Date dateCreation, double solde, boolean active) {
		super();
		this.codeCompte = codeCompte;
		this.type = type;
		this.taux = taux;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.active = active;
	}
	public Compte(String codeCompte, String type, double decouvert, double soldMax, Date dateCreation, double solde,
			boolean active) {
		super();
		this.codeCompte = codeCompte;
		this.type = type;
		this.decouvert = decouvert;
		this.soldMax = soldMax;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.active = active;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Date de création
	 */
   private Date dateCreation;
   /**
    * Le solde
    */ 
   private double solde;
   /**
    * Si le comptes est activé 
    */
   private boolean active;
   /**
    * La liste des clients de compte
    */
   @ManyToMany()
   

   	 @JoinTable(name="mesComptes",joinColumns=@JoinColumn(name="codeCompte"),inverseJoinColumns=@JoinColumn(name="codeClient"))
   private List<Client> clients;
   	 /**
   	  * Agent qui créé ce compte
   	  */
   	 @ManyToOne()
   	 @JoinColumn(name="codeAgent")
   public Agent agent;
   	 /**
   	  * La liste des operations effectué sur ce compte dont il est l'emmetteur
   	  */
   	 @OneToMany(mappedBy="compteEmetteur",fetch = FetchType.EAGER)
   	@Fetch(value = FetchMode.SUBSELECT)
   private List<Operation> operationsEmis;
   	 /**
   	  * La liste des opérations effectué su ce compte dont il est le recepteur
   	  */
   	 @OneToMany(mappedBy="compteRecepteur",fetch = FetchType.EAGER)
   	@Fetch(value = FetchMode.SUBSELECT)
   private List<Operation> operationsReçus;
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Compte(String code) {
		this.codeCompte = code;
	}
	public Compte( Date dateCreation, double solde,List<Client> clients,boolean active) {
		super();
		
		this.dateCreation = dateCreation;
		this.solde = solde;
		
		this.clients = clients;
		this.active = active;
	}
	
	public String getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public List<Operation> getOperationsEmis() {
		return operationsEmis;
	}
	public void setOperationsEmis(List<Operation> operationsEmis) {
		this.operationsEmis = operationsEmis;
	}
	public List<Operation> getOperationsReçus() {
		return operationsReçus;
	}
	public void setOperationsReçus(List<Operation> operationsReçus) {
		this.operationsReçus = operationsReçus;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	   
   
   
   

}