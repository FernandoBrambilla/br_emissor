package com.fernando.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_venda")
public class ItensVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Product produto;
	
	private Integer quantidade;

	@Column(name = "total_iten")
	private Double totalIten;

	public Long getId() {
		return id;
	}

	public Product getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getTotalIten() {
		return totalIten;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(Product produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setTotalIten(Double totalIten) {
		this.totalIten = totalIten;
	}

	
}
