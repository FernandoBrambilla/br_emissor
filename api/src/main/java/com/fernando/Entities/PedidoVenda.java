package com.fernando.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_venda")
public class PedidoVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_venda")
	private Venda venda;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Product produto;

	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public Venda getVenda() {
		return venda;
	}

	public Product getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setProduto(Product produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
