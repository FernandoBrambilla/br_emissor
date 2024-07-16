
package com.fernando.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends RepresentationModel<Product> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private String codigo;

	@Column(precision = 13 , scale = 2)
	private BigDecimal valorVenda;

	@Column(precision = 13 , scale = 2)
	private BigDecimal custo;

	private Integer estoque;

	@Column(name = "use_markup")
	private boolean utilizarMarkup = false;

	@OneToOne(cascade = CascadeType.ALL)
	private UnidadeProduto unidadeProduto = new UnidadeProduto("");
	
	@OneToOne(cascade = CascadeType.ALL)
	private Markup markup;

	@OneToOne(cascade = CascadeType.ALL)
	private ProductCategory categoria;

	private String fornecedor;

	private String tributacao;

	private Integer ncm;

	private String cest;

	private LocalDateTime dataInclusao;

	private String EAN_GTIN;
	
	private boolean status;

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public boolean isUtilizarMarkup() {
		return utilizarMarkup;
	}

	public UnidadeProduto getUnidadeProduto() {
		return unidadeProduto;
	}

	public Markup getMarkup() {
		return markup;
	}

	public ProductCategory getCategoria() {
		return categoria;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getTributacao() {
		return tributacao;
	}

	public Integer getNcm() {
		return ncm;
	}

	public String getCest() {
		return cest;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public String getEAN_GTIN() {
		return EAN_GTIN;
	}

	public boolean isStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setUtilizarMarkup(boolean utilizarMarkup) {
		this.utilizarMarkup = utilizarMarkup;
	}

	public void setUnidadeProduto(UnidadeProduto unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public void setMarkup(Markup markup) {
		this.markup = markup;
	}

	public void setCategoria(ProductCategory categoria) {
		this.categoria = categoria;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setTributacao(String tributacao) {
		this.tributacao = tributacao;
	}

	public void setNcm(Integer ncm) {
		this.ncm = ncm;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setEAN_GTIN(String eAN_GTIN) {
		EAN_GTIN = eAN_GTIN;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
