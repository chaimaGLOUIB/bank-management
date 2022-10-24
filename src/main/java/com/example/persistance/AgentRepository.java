package com.example.persistance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.metier.entities.Agent;
import com.example.metier.entities.Client;

public interface AgentRepository extends JpaRepository<Agent,String> {
	@Query("select c from Agent c where c.password like :x")
	 public Agent getbyPass(@Param("x")String mc);
}
