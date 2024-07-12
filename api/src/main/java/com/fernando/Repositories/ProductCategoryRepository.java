package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	
	 @Query("SELECT u from ProductCategory u WHERE u.descricao like ?1%")
	  List<ProductCategory> findByName(@Param("desc")String desc);
	}
