
package com.fernando.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

/**
 *
 * @author Fernando
 */
@Entity
public class Markup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private BigDecimal markup;
    
    private boolean utilizar;

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
