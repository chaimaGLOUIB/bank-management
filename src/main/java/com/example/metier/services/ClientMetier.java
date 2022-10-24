package com.example.metier.services;
import java.util.List;

import com.example.metier.entities.*;



public interface ClientMetier {
	public List<Compte> listComptesParClient(String cin);

	public List<Operation> listOperationsParClient(String cin);
	public List<Operation> listOperationParCompte(String code);
	}
