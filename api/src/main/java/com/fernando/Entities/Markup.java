
package com.fernando.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Fernando
 */
@Entity
public class Markup {
    
   	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	
	@Column(nullable = false, columnDefinition="Decimal(5,2) default '0.00'")
    
    private BigDecimal markup = BigDecimal.ZERO;
    
	@Column(nullable = false)
    private boolean utilizar = false;

	public Integer getId() {
		return id;
	}

	public BigDecimal getMarkup() {
		return markup;
	}

	public boolean isUtilizar() {
		return utilizar;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}

	public void setUtilizar(boolean utilizar) {
		this.utilizar = utilizar;
	}
}
