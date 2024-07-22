package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.ProductUnity;


public interface ProductUnityRepository extends JpaRepository<ProductUnity, Integer> {
	
	 @Query("SELECT u from ProductUnity u WHERE u.descricao like ?1%")
	  List<ProductUnity> findByName(@Param("desc")String desc);
	}
