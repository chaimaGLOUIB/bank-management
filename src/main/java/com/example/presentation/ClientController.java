package com.example.presentation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;
import com.example.metier.services.AdminMetier;
import com.example.metier.services.ClientMetier;
import com.example.persistance.AgenceRepository;
import com.example.persistance.AgentRepository;
import com.example.persistance.ClientRepository;
import com.example.persistance.CompteRepository;
@Controller


@PreAuthorize ("hasRole ('ROLE_client')") 

@RequestMapping("/Client")
public class ClientController {
	

	

	
		@Autowired
		private ClientRepository clientRepository;
		@Autowired
		private ClientMetier clientMetier;
		 
		 
	    
		@RequestMapping("/Comptes")
		public String comptes(Model model) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  String cin = ((UserDetails)principal).getUsername();
			  List<Compte> comptesC=clientMetier.listComptesParClient(cin);

			    model.addAttribute("comptesC",comptesC);

			    return "Client/comptes";
			
			
			
		}
		@RequestMapping("/Operations")
		public String operation(Model model) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  String cin = ((UserDetails)principal).getUsername();
		  List<Operation> operations=clientMetier.listOperationsParClient(cin);
		  List<Operation> operationsR=clientRepository.listOperationRParClient(cin);

		  
		    model.addAttribute("operations", operations);

		    model.addAttribute("operationsR", operationsR);
		    return "Client/operationsClient";
			
		}
}
