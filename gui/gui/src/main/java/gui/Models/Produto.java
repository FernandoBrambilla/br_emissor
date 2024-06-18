package gui.Models;

import java.time.LocalDateTime;

public class Produto {
	
	private Long id;
    
    private String descricao;
    
    private String codigo;
    
    private Double valorVenda;
    
    private Double custo;
    
    private Integer estoque;
    
    private String unidadeProduto;
    
    private String categoria;
    
    private String fornecedor;
    
    private String tributacao;
    
    private String ncm;
    
    private String descNcm;
    
    private String cest;
    
    private LocalDateTime dataInclusao;
   
    private String EAN_GTIN;

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public Double getCusto() {
		return custo;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public String getUnidadeProduto() {
		return unidadeProduto;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getTributacao() {
		return tributacao;
	}

	public String getNcm() {
		return ncm;
	}

	public String getDescNcm() {
		return descNcm;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setUnidadeProduto(String unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setTributacao(String tributacao) {
		this.tributacao = tributacao;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public void setDescNcm(String descNcm) {
		this.descNcm = descNcm;
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
    
    

}
