package com.example.metier.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;
import com.example.persistance.ClientRepository;
import com.example.persistance.CompteRepository;
import com.example.persistance.OperationRepository;
@Service
public class AgentMetierImpl implements AgentMetier {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	
	

	@Override
	public List<Client>  clientParCin(String cin) {
		// TODO Auto-generated method stub
		return clientRepository.ClientParMc(cin);
	}

	@Override
	public List<Compte> listCompte() {
		
		return compteRepository.findAll(Sort.by(Sort.Direction.DESC,"dateInscrit"));
	}
	

	@Override
	public List<Compte> CompteParCode(String code) {
		return compteRepository.CompteParCode(code);
	}

	@Override
	public List<Operation> listOperations() {
		return operationRepository.findAll(Sort.by(Sort.Direction.DESC,"dateOperation"));
	}

	@Override
	public List<Operation> listOperationParClient(String cin) {
	return clientRepository.listOperationParClient(cin);
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte cp= compteRepository.getOne(codeCompte);
		cp.setSolde(cp.getSolde()+montant);
		 compteRepository.save(cp);
		Operation op=new Operation(montant,new Date(),"versement");
		op.setCompteRecepteur(cp);
		operationRepository.save(op);
		
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp= compteRepository.getOne(codeCompte);
		if (cp.getSolde()<montant)
			throw new RuntimeException("sold insuffisant");
		cp.setSolde(cp.getSolde()-montant);
		 compteRepository.save(cp);
		Operation op=new Operation(montant,new Date(),"retirement");
		op.setCompteEmetteur(cp);
		operationRepository.save(op);
		
	}

	@Override
	public void virement(String codeCompteemetteur, String codeCompterecepteur, double montant) {
		retirer( codeCompteemetteur, montant);
		verser(codeCompterecepteur,montant);
		
		
		
	}

	

	@Override
	public boolean removeOperation(long code) {
		operationRepository.deleteById(code);
		return true;
	}

	@Override
	public boolean removeClient(String cin) {
	clientRepository.deleteById(cin);
	return true;
	}

	@Override
	public Client addClient(Client c) {
		return clientRepository.save(c);
	}

	@Override
	public Client updateClient(Client c) {
	return clientRepository.saveAndFlush(c);
	}

	@Override
	public List <Client> listClients()
	 {
			return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "dateInscrit"));
			}

	

	@Override
	public List<Compte> listComptesparClient(String nom) {
	    return clientRepository.listComptesParClient(nom);

	}

	

	

	


	

}
