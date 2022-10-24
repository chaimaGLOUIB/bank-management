package com.example.metier.services;

import java.util.List;
import java.util.Date;


import org.springframework.data.domain.Page;

import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;
 
public interface AgentMetier {
   public boolean removeClient(String cin);
   public Client addClient(Client c);
   public Client updateClient(Client c);
   public List<Client> clientParCin(String cin);
   public List<Compte> listComptesparClient(String cin);

   
   public List<Compte> CompteParCode(String code);
   public List<Compte> listCompte();
   public List<Operation> listOperationParClient(String nom);
   public void verser(String codeCompte, double montant) ;
   public void retirer(String codeCompte, double montant);
	public void virement(String codeCompteemetteur, String codeCompterecepteur, double montant);
	public boolean removeOperation(long code);
	public List <Client> listClients();
	public List<Operation> listOperations();
  

}
