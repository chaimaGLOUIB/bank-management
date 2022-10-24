package com.example.metier.services;

import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;
import com.example.metier.entities.Operation;

public interface AdminMetier {
 public List<Agent> listAgent();
 public List<Agent> listAgentParAgences(String mc);
 public Agent addAgent(Agent A);
 public void deleteAgent(Agent A);
 public boolean UpdateAgent(Agent A);
 public List<Operation> listOperationsParAgenceParDate(String mc,Date debut, Date fin);
 public List<Agence> listAgences ();
 public Agence addAgence(Agence A);
 public void UpdateAgence(String nom,String ville, String adresse,String id);
 public void removeAgence(Agence A);
 public Page<Client> derniersClients(int page, int size);
 public double nbrRetrait();
 public double nbrDepots();
 public double nbrVirement();

 
 
 

 
 

 
}
