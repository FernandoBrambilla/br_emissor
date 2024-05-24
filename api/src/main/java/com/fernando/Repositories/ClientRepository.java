package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.Clients;


public interface ClientRepository extends JpaRepository<Clients, Long> {
	
	
	 @Query("SELECT u from Clients u WHERE u.name like ?1%")
	  List<Clients> findByName(@Param("name")String name);
	}
