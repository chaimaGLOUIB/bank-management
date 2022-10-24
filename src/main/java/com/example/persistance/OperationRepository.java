package com.example.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.metier.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation,Long> {
	
@Query("Select count(e) from Operation e where e.type ='retirement'")
public int nbrRetrait();

@Query("Select count(e) from Operation e where e.type  ='versement'")
public int nbrDepot();

@Query("Select count(e) from Operation e where e.type = 'versement'")
public int nbrVirement();
}