package com.example.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.metier.entities.Client;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;

public interface ClientRepository extends JpaRepository<Client,String> {
	@Query("select c from Client c where c.cin like :x")
	 public List<Client>  ClientParMc(@Param("x")String mc);
	@Query("select cp from Client c join c.comptes cp where c.cin like :x")
	 public List<Compte> listComptesParClient(@Param("x")String cin);
	
	@Query("select op from Client c join c.comptes cp join cp.operationsEmis op where c.cin like :x")
	 public List<Operation> listOperationParClient(@Param("x")String cin);
	@Query("select op from Client c join c.comptes cp join cp.operationsReçus op where c.cin like :x")
	 public List<Operation> listOperationRParClient(@Param("x")String cin);
	
	@Query("select c from Client c where c.password like :x")
	 public Client  getbyPass(@Param("x")String mc);
	
    

	

}
