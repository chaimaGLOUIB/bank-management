package com.example.presentation;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;
import com.example.metier.services.AdminMetier;
import com.example.metier.services.AgentMetier;
import com.example.persistance.AgenceRepository;
import com.example.persistance.AgentRepository;
import com.example.persistance.ClientRepository;
import com.example.persistance.CompteRepository;
import com.example.persistance.OperationRepository;
@Controller


@PreAuthorize ("hasRole ('ROLE_agent')") 
@RequestMapping("/Agent")


public class AgentController {
	@Autowired
	private AgentMetier agentMetier;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AgenceRepository agenceRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	 
//	@RequestMapping("/statistiqueParAgentParjour")
//	public String statistique(Model model) {
//	  
//	    return "statParAgent";
//		
//	}
    

	@RequestMapping(value="/clients",method=RequestMethod.GET)
	public String client (Model model,@RequestParam(name ="cin",defaultValue="") String cin) {
	  List<Client> clients =agentMetier.clientParCin("%"+cin+"%");
	  model.addAttribute("clients", clients);
	  model.addAttribute("client",new Client());
	  List<Agence> agences=agenceRepository.findAll(); 
	    model.addAttribute("Agences",agences);
	  
	    return "Agent/client";
		
	}
	@RequestMapping(value="/SaveClient",method=RequestMethod.POST)
	public String save(@Valid Client a ,@RequestParam(name ="password") String password ) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		a.setPassword(passwordEncoder.encode(password));
		 agentMetier.addClient(a);
	    return "redirect:/Agent/clients";
	}
	@RequestMapping(value="client/supprimerClient",method=RequestMethod.GET)

	public String supprimerClient(@RequestParam(name="code") String code) {
		
	  clientRepository.deleteById(code);
	  
	    
		 return "redirect:/Agent/clients";
}
	@RequestMapping(value="client/editerclient",method=RequestMethod.GET)

	public String editerClient(Model model,@RequestParam(name="code") String code) {
		Client client=clientRepository.getOne(code);
		List<Agence> agences=agenceRepository.findAll(); 
		model.addAttribute("Agences",agences);
	 model.addAttribute("client",client);
	    
		 return "Agent/editClient";
}
	@RequestMapping(value="client/UpdateClient",method=RequestMethod.POST)
	public String updateAgent(@Valid Client a) {
		
		clientRepository.save(a);
		 return "redirect:/Agent/clients";
	}
	
	@RequestMapping(value="comptes",method=RequestMethod.GET)
public String Comptes(Model model,@RequestParam(name="codeC",defaultValue="") String codeC) {
		List<Compte> comptes=agentMetier.CompteParCode("%"+codeC+"%");
		List<Client> clients= clientRepository.findAll();
		model.addAttribute("comptes", comptes);
		model.addAttribute("clients", clients);
		model.addAttribute("compte", new Compte());
		
		return "Agent/compte";
		
			}
	@RequestMapping(value="/SaveCompte",method=RequestMethod.POST)
	public String saveCompte(@Valid Compte a ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String cin = ((UserDetails)principal).getUsername();
		Agent Agen=agentRepository.getOne(cin);
		
		  a.setAgent(Agen);
		 compteRepository.save(a);
	    return "redirect:/Agent/comptes";
	}
	@RequestMapping(value="comptes/supprimerCompte",method=RequestMethod.GET)

	public String supprimerCompte(@RequestParam(name="code") String code) {
		
	  compteRepository.deleteById(code);
	  
	    
		 return "redirect:/Agent/comptes";
}
	@RequestMapping(value="comptes/editercompte",method=RequestMethod.GET)

	public String editerCompte(Model model,@RequestParam(name="code") String code) {
		Compte compte=compteRepository.getOne(code);
	 model.addAttribute("compte",compte);
	 List<Client> clients= clientRepository.findAll();
		model.addAttribute("clients", clients);
	    
		 return "Agent/ediitCompte";
}
	@RequestMapping(value="comptes/UpdateCompte",method=RequestMethod.POST)
	public String updateCompte(@Valid Compte a) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String cin = ((UserDetails)principal).getUsername();
		Agent Agen=agentRepository.getOne(cin);
		
		  a.setAgent(Agen);
		compteRepository.save(a);
		 return "redirect:/Agent/comptes";
	}
	
	
	
	@RequestMapping(value="operations")
	public String operation(Model model)
	{
		List<Operation> operations=agentMetier.listOperations();
		model.addAttribute("operations",operations);
		model.addAttribute("operation",new Operation());

		
		return "Agent/operations";
		
		
		
		
	}
	@RequestMapping(value="operations/retrait")
	public String retrait(@Valid Operation a,@RequestParam(name="compteEmetteur") String codeCompte,@RequestParam(name="montant") double montant)
	{
	agentMetier.retirer(codeCompte, montant);
	
	 return "redirect:/Agent/operations";
		
	}
	@RequestMapping(value="operations/versement")
	public String verser(@Valid Operation a,@RequestParam(name="compteRecepteur") String codeCompte,@RequestParam(name="montant") double montant)
	{
	agentMetier.verser(codeCompte, montant);
	
	 return "redirect:/Agent/operations";
		
	}
	@RequestMapping(value="operations/virement")
	public String virement(@Valid Operation a,@RequestParam(name="compteEmetteur") String codeCompteE,@RequestParam(name="compteRecepteur") String codeCompteR,@RequestParam(name="montant") double montant)
	{
	agentMetier.virement(codeCompteE, codeCompteR, montant);
	
	 return "redirect:/Agent/operations";
		
	}
	

	

}