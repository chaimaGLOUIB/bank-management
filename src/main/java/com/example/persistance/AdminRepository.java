package com.example.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.metier.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	@Query("select c from Admin c where c.password like :x")
	 public Admin  getbyPass(@Param("x")String mc);

}
