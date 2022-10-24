package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.services.AdminMetier;
import com.example.metier.services.AgentMetier;
import com.example.metier.services.ClientMetier;
 

import java.util.List;

@SpringBootApplication

public class GestBanqueApplication {


	public static void main(String[] args) {
		
		
		
		ApplicationContext ctx=SpringApplication.run(GestBanqueApplication.class, args);
		
		
		
		

		
		
		}

}
