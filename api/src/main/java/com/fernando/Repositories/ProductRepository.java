package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 @Query("SELECT u from Product u WHERE u.descricao like %?1%")
	  List<Product> findByName(@Param("desc")String descricao);
	 
	 @Query("SELECT u from Product u WHERE u.ean_gtin like %?1%")
	  List<Product> findByCod(@Param("ean_gtin")String ean_gtin);
	}
