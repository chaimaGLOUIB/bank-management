package com.example.metier.services;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;
import com.example.metier.entities.Operation;
import com.example.persistance.AgenceRepository;
import com.example.persistance.AgentRepository;
import com.example.persistance.ClientRepository;
import com.example.persistance.OperationRepository;
@ Service
public class AdminMetierImpl implements AdminMetier {
@Autowired
AgentRepository agentRepository;
@Autowired
AgenceRepository agenceRepository;
@Autowired
ClientRepository clientRepository;
@Autowired
OperationRepository operationRepository;
	@Override
	public List<Agent> listAgent() {
		return agentRepository.findAll();
		
	}

	@Override
	public List<Agent> listAgentParAgences(String nom) {
		
		return agenceRepository.listAgentParAgence(nom);
	}

	@Override
	public Agent addAgent(Agent A) {
		
		return agentRepository.save(A);
	}

	@Override
	public void deleteAgent(Agent A) {
		
		agentRepository.delete(A);;
	}

	@Override
	public boolean UpdateAgent(Agent A) {
		agentRepository.save(A); 
		return true;

	}

	@Override
	public List<Operation> listOperationsParAgenceParDate(String nom,Date d1, Date d2) {
	
		return agenceRepository.listOperationsParAgenceParDate(nom, d1, d2);
		
	}



	@Override
	public Agence addAgence(Agence A) {
		return agenceRepository.save(A);
	}

	

	@Override
	public void removeAgence(Agence A) {
		 agenceRepository.delete(A);
	}

	@Override
	public Page<Client> derniersClients(int page, int size) {
		return clientRepository.findAll(PageRequest.of(page, size, new Sort(Direction.DESC,"date")));

	}

	
	

	@Override
	public List<Agence> listAgences() {
		return agenceRepository.findAll();
	}

	@Override
	public void UpdateAgence(String nom, String ville, String adresse, String id) {
		agenceRepository.updateAgence(nom, ville, adresse, id);
		
	}

	@Override
	public double nbrRetrait() {
return(operationRepository.nbrRetrait());
	}

	@Override
	public double nbrDepots() {
		// TODO Auto-generated method stub
		return(operationRepository.nbrDepot());
	}

	@Override
	public double nbrVirement() {
		// TODO Auto-generated method stub
		return(operationRepository.nbrVirement());
	}

}
