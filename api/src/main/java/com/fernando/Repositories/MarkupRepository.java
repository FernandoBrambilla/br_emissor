package com.fernando.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.Entities.Markup;

/**
 *
 * @author Fernando
 */
public interface MarkupRepository extends JpaRepository<Markup, Integer> {
    
}
