package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.MeioPagamento;

public interface MeioPagamentoRepository extends JpaRepository<MeioPagamento, Long> {

	@Query("SELECT u from MeioPagamento u WHERE u.meio like %?1%")
	List<MeioPagamento> findByName(@Param("desc") String descricao);
}
