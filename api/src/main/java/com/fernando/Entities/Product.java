
package com.fernando.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@JsonPropertyOrder(value = {"id","descricao","codigo","valorVenda","custo","estoque","utilizarMarkup",
		"unidadeProduto","markup","categoria","fornecedor","tributacao","ncm","cest","dataInclusao",
		"EAN_GTIN", "obs", "status"})
public class Product extends RepresentationModel<Product> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private String codigo;

	@Column(precision = 13, scale = 2)
	private BigDecimal valorVenda;

	@Column(precision = 13, scale = 2)
	@NumberFormat(pattern = "R$ #,###.00")
	private BigDecimal custo;

	private Integer estoque;

	@Column(name = "use_markup")
	private boolean utilizarMarkup = false;

	@ManyToOne()
	@JoinColumn(name = "unidade_produto_id")
	private ProductUnity unidadeProduto;

	@ManyToOne()
	@JoinColumn(name = "markup_id")
	private Markup markup;

	@ManyToOne()
	@JoinColumn(name = "categoria_id")
	private ProductCategory categoria;

	private String fornecedor;

	private String tributacao;

	@ManyToOne()
	@JoinColumn(name = "ncm_id")
	private NCM ncm;

	private String cest;

	private LocalDateTime dataInclusao;

	private String EAN_GTIN;
	
	private String obs;

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

	public ProductUnity getUnidadeProduto() {
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

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
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

	public void setUnidadeProduto(ProductUnity unidadeProduto) {
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
