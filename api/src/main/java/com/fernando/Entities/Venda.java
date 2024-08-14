package com.fernando.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda extends RepresentationModel<Venda> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_venda")
	private LocalDateTime dataVenda;

	private Integer quantidade;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pedido_venda", joinColumns = @JoinColumn(name = "id_venda"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private List<Product> produto = new ArrayList<>();

	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	private Double desconto;

	@Column(name = "valor_pago")
	private Double valorPago;

	@ManyToOne()
	@JoinColumn(name = "meio_pagamento_id")
	private MeioPagamento meioPgto;

	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	private User usuario;

	private Boolean status;

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public List<Product> getProduto() {
		return produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public MeioPagamento getMeioPgto() {
		return meioPgto;
	}

	public User getUsuario() {
		return usuario;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setProduto(List<Product> produto) {
		this.produto = produto;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public void setMeioPgto(MeioPagamento meioPgto) {
		this.meioPgto = meioPgto;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
