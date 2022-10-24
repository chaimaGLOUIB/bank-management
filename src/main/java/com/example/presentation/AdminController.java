package com.example.presentation;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;
import com.example.metier.entities.Operation;
import com.example.metier.services.AdminMetier;
import com.example.persistance.AgenceRepository;
import com.example.persistance.AgentRepository;
import com.example.persistance.OperationRepository;
@Controller
@EnableGlobalMethodSecurity (prePostEnabled = true)


@PreAuthorize ("hasRole ('ROLE_admin')") 
@RequestMapping("/Admin")


public class AdminController {
	@Autowired
	private AdminMetier adminMetier;
	@Autowired
	private AgenceRepository agenceRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private OperationRepository operationRepository;
	 
	 
    

	@RequestMapping("/DerniersClients")
	public String dernierClient(Model model) {
	  Page<Client> derClient=adminMetier.derniersClients(0,3);
	  int nbrRetrait=operationRepository.nbrRetrait();
	  int nbrDepot=operationRepository.nbrDepot();
	  int nbrVirementt=operationRepository.nbrVirement();
	    model.addAttribute("retrais", nbrRetrait);
	    model.addAttribute("depot", nbrDepot);
	    model.addAttribute("virement", nbrVirementt);
	    model.addAttribute("DerniersClients", derClient);
	    
	    return "Admin/DerniersClients";
		
	}
//	@RequestMapping(value="/ListAjoutAgence",method=RequestMethod.GET)
//	
//	public String ListAjoutAgence(Model model,Model model1) {
//		
//      
//	  List<Agence> agences=adminMetier.listAgences();
//	    model.addAttribute("Agences",agences);
//	    model1.addAttribute("Agence",new Agence());
//
//	    return "ListAjoutAgence";
//		
//	}
	@RequestMapping(value="/saveAgence",method=RequestMethod.POST)
	public String save(@Valid Agence a,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
		{return "redirect:/Admin/ListAjoutAgence";}
		 adminMetier.addAgence(a);
		 return "redirect:/Admin/ListAjoutAgence";
	}
	
	
	@RequestMapping(value="ListAjoutAgence/UpdateAgence",method=RequestMethod.POST)
	public String update(@Valid Agence a,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
		{return "redirect:/Admin/ListAjoutAgence";}
		agenceRepository.save(a);
		 return "redirect:/Admin/ListAjoutAgence";
	}
	
	
	@RequestMapping(value="/ListAjoutAgence",method=RequestMethod.GET)
	
	public String ListAjoutAgence(Model model,@RequestParam(name="motCle",defaultValue="") String mc) {
		
      
	  List<Agence> agences=agenceRepository.findAllByNom("%"+mc+"%"); 
	    model.addAttribute("Agences",agences);
	    model.addAttribute("Agence",new Agence());

   
	    return "Admin/ListAjoutAgence";
		
	}
	
@RequestMapping(value="/ListAjoutAgence/supprimer",method=RequestMethod.GET)
	
	public String supprimer(@RequestParam(name="code") String code) { 
		
      agenceRepository.deleteById(code);
	  
	    
		 return "redirect:/Admin/ListAjoutAgence";


		
	}

@RequestMapping(value="/ListAjoutAgence/editer",method=RequestMethod.GET)

public String edier(@RequestParam(name="code") String code,Model model) {
	
Agence A=agenceRepository.getOne(code);
  model.addAttribute("agence", A);
    
	 return "Admin/editAgence";


	
}
@RequestMapping(value="/SaveAgent",method=RequestMethod.POST)
public String saveAgent(@Valid Agent a,@RequestParam(name ="password") String password) {
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	a.setPassword(passwordEncoder.encode(password));

	 adminMetier.addAgent(a);
	 return "redirect:/Admin/Agent";
}


@RequestMapping(value="Agent/UpdateAgent",method=RequestMethod.POST)
public String updateAgent(@Valid Agent a,BindingResult bindingResult) {
	if(bindingResult.hasErrors())
	{return "redirect:/Admin/Agent";}
	agentRepository.save(a);
	 return "redirect:/Admin/Agent";
}


@RequestMapping(value="/Agent",method=RequestMethod.GET)

public String Agent(Model model,@RequestParam(name="motCle",defaultValue="") String mc) {
	
  
  List<Agent> agents=adminMetier.listAgentParAgences("%"+mc+"%"); 
    model.addAttribute("Agents",agents);
    model.addAttribute("Agent",new Agent());
    List<Agence> agences=agenceRepository.findAll(); 
    model.addAttribute("Agences",agences);

    return "Admin/Agent";
	
}

@RequestMapping(value="Agent/supprimerAgent",method=RequestMethod.GET)

public String supprimerAgent(@RequestParam(name="code") String code) {
	
  agentRepository.deleteById(code);
  
    
	 return "redirect:/Admin/Agent";


	
}

@RequestMapping(value="Agent/editerAgent",method=RequestMethod.GET)

public String editerAgent(@RequestParam(name="code") String code,Model model) {

Agent A=agentRepository.getOne(code);
model.addAttribute("agent", A);
List<Agence> agences=agenceRepository.findAll(); 
model.addAttribute("Agences",agences);


 return "Admin/editAgent";



}
@RequestMapping(value="listDesOperations",method=RequestMethod.POST)
public String Operation(Model model,@RequestParam(name="nom") String nom,@RequestParam(name="genre") String genre,@RequestParam(name="dateDebut") Date dateDebut,@RequestParam(name="dateFin") Date dateFin)

{	
	List<Agence> agences=agenceRepository.findAll(); 
	model.addAttribute("Agences",agences);
	if("Retrais".equals(genre))
	{
	List<Operation> operationsEmis=agenceRepository.listOperationsParAgenceParDate(nom, dateDebut, dateFin);
	model.addAttribute("operationsEmis",operationsEmis);
//	for(Operation a:operationsEmis)
//	{
//		System.out.println(a.getMontant());
//	}
//	System.out.println(genre);
	}
	if("Depots".equals(genre)){
		List<Operation> operationsReçus=agenceRepository.listOperationsRParAgenceParDate(nom, dateDebut, dateFin);
		model.addAttribute("operationsRecus",operationsReçus);
	}
	
	return "Admin/transaction"; 
}

@RequestMapping("transaction")
public String transaction(Model model)
{
	List<Agence> agences=agenceRepository.findAll(); 
	model.addAttribute("Agences",agences);

	
	return "Admin/transaction"; 
}


	
	
	
	
	
	
	
	

}
