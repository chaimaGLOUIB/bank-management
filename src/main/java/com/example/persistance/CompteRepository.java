package com.example.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.metier.entities.Compte;
import com.example.metier.entities.Operation;

public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query("select op from Compte c join c.operationsEmis op where c.codeCompte like :code ")
	public List<Operation> listOperationsParCompte(@Param("code")String code) ;
	@Query("select c from Compte c where c.codeCompte like :code ")
	public List<Compte> CompteParCode(@Param("code")String code);

}
