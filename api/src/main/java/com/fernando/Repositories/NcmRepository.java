package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.NCM;


public interface NcmRepository extends JpaRepository<NCM, Long> {
	
	 @Query("SELECT u from NCM u WHERE u.descricao like ?1%")
	  List<NCM> findByName(@Param("desc")String desc);
	}
