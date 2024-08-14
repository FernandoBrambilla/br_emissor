package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.Cliente;


public interface ClientRepository extends JpaRepository<Cliente, Long> {
	
	 @Query("SELECT u from Cliente u WHERE u.name like ?1%")
	  List<Cliente> findByName(@Param("name")String name);
	}
