package com.example.presentation;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.metier.entities.Admin;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;
import com.example.persistance.AdminRepository;
import com.example.persistance.AgentRepository;
import com.example.persistance.ClientRepository;


/**
 * @author : Glouib chaima
 * 
 */
@Controller

public class LoginContrller {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AgentRepository agentRepository;
	@Autowired
	ClientRepository clientRepository;
    @GetMapping("/login")
    public String getLogin(){
        return "ind";
    }
    @GetMapping("/changepassword")
    public String password(){
        return "changepassword";
    }
    @RequestMapping(value="/changepassword/save",method=RequestMethod.POST)
    public String savePass(HttpServletRequest request,@RequestParam(name="ancien") String ancien,@RequestParam(name="new") String n)
    {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String cin = ((UserDetails)principal).getUsername();
	
    	if (request.isUserInRole("ROLE_admin")) {
         Admin a= adminRepository.getOne(cin);
         a.setPassword(passwordEncoder.encode(n));
         adminRepository.save(a);
         return "Admin/home";
        }
        if (request.isUserInRole("ROLE_client")) {
        	 Client a= clientRepository.getOne(cin);
             a.setPassword(passwordEncoder.encode(n));
             clientRepository.save(a);
             return "Client/homeClient";
        }
        if (request.isUserInRole("ROLE_agent")) {
        	
  		Agent a=agentRepository.getOne(cin);
        	 
             System.out.println(a.getcin());

             a.setPassword(passwordEncoder.encode(n));
             agentRepository.save(a);
             return "Agent/homeAgent";
       }
        return "";
    }
    
    @GetMapping("")

    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_admin")) {
            return "Admin/home";
        }
        if (request.isUserInRole("ROLE_client")) {
        	 return "Client/homeClient";
        }
        if (request.isUserInRole("ROLE_agent")) {
       	 return "Agent/homeAgent";
       }
        return "";
    }
}
