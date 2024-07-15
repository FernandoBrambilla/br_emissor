
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
public class UnidadeProduto extends RepresentationModel<UnidadeProduto> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public UnidadeProduto(String descricao) {
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
