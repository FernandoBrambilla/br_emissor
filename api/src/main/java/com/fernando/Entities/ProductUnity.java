
package com.fernando.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import org.springframework.hateoas.RepresentationModel;

@Entity 
@Table(name = "unidade_produto")
public class ProductUnity extends RepresentationModel<ProductUnity> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public ProductUnity(String descricao) {
		this.descricao = descricao;
	}
    
	public ProductUnity(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public ProductUnity() {
		super();
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
