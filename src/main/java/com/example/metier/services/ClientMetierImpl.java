package com.example.metier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;
import com.example.persistance.ClientRepository;
import com.example.persistance.CompteRepository;
@Service
public class ClientMetierImpl implements ClientMetier {
	@Autowired
private ClientRepository clientRepository ;
	@Autowired
	private CompteRepository compteRepository ;

	@Override
	public List<Compte> listComptesParClient(String cin) {
		
		return  clientRepository.listComptesParClient(cin);
		
	}
	

	@Override
	public List<Operation> listOperationsParClient(String cin) {
		return clientRepository.listOperationParClient(cin);
	}
	

	@Override
	public List<Operation> listOperationParCompte(String code) {
		
		return compteRepository.listOperationsParCompte(code);
	} 
	
	
}
