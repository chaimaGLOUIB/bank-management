package com.example.persistance;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.metier.entities.Agence;
import com.example.metier.entities.Agent;
import com.example.metier.entities.Operation;

public interface AgenceRepository extends JpaRepository<Agence,String>  {
	@Query("select g from Agence A join A.agents g where A.nom like :x")
	public List<Agent> listAgentParAgence(@Param("x")String nom);
	@Query("select Op from Agence A join A.clients cl join cl.comptes co join co.operationsEmis Op where A.nom like :x and Op.dateOperation between :y and :z")
	public List<Operation> listOperationsParAgenceParDate(@Param("x")String nom,@Param("y")Date y,@Param("z")Date z);
	@Query("select Op from Agence A join A.clients cl join cl.comptes co join co.operationsReçus Op where A.nom like :x and Op.dateOperation between :y and :z")
	public List<Operation> listOperationsRParAgenceParDate(@Param("x")String nom,@Param("y")Date y,@Param("z")Date z);
	@Modifying
    @Query("update Agence  SET nom = :nom ,ville = :ville ,adresse = :adresse WHERE codeAgence = :id")
    public void updateAgence(@Param("nom") String nom,@Param("ville") String ville,@Param("adresse") String adresse, @Param("id") String id);
	@Query("select A from Agence A where A.nom like :x")
	public List<Agence> findAllByNom(@Param("x")String nom);
}
