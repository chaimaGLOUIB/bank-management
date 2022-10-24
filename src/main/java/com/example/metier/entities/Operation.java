package com.example.metier.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Operation {
	@Id
    @GeneratedValue
	private long id;
    private double montant;
    private Date dateOperation;
    private String type;
    
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="codeCompteEmetteur")
    private Compte compteEmetteur;
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="codeCompteRecepteur")
    private Compte compteRecepteur;
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Compte getCompteEmetteur() {
		return compteEmetteur;
	}
	public void setCompteEmetteur(Compte compteEmetteur) {
		this.compteEmetteur = compteEmetteur;
	}
	public Compte getCompteRecepteur() {
		return compteRecepteur;
	}
	public void setCompteRecepteur(Compte compteRecepteur) {
		this.compteRecepteur = compteRecepteur;
	}
	public Operation(double montant, Date dateOperation, String type) {
		super();
		this.montant = montant;
		this.dateOperation = dateOperation;
		this.type = type;
	}
    
    
}
