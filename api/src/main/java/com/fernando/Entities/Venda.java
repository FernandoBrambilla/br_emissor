package com.fernando.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
@JsonPropertyOrder(value = { "id", "dataVenda", "cliente", "desconto", "valorPago", "pedidoVenda", "meioPgto",
		"usuario", "status" })
public class Venda extends RepresentationModel<Venda> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_venda")
	private LocalDateTime dataVenda;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "produtos_pedido", joinColumns = @JoinColumn(name = "id_vendas"), inverseJoinColumns = @JoinColumn(name = "id_itens_venda"))
	private List<ItensVenda> itensVendas = new ArrayList<>();

	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private Double desconto;

	@Column(name = "valor_pago")
	private Double valorPago;

	@ManyToOne()
	@JoinColumn(name = "meio_pagamento_id")
	private MeioPagamento meioPgto;

	private Double troco;
	
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

	public Cliente getCliente() {
		return cliente;
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

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	
	public List<ItensVenda> getItensVendas() {
		return itensVendas;
	}


	public void setItensVendas(List<ItensVenda> itensVendas) {
		this.itensVendas = itensVendas;
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
