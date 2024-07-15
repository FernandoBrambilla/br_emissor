
package com.fernando.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity 
@Table(name = "product_category")
public class ProductCategory implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public ProductCategory(String descricao) {
		this.descricao = descricao;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer id;
    
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
