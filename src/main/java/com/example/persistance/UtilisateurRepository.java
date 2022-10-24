package com.example.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.metier.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,String>{

}

