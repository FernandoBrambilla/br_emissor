
package com.fernando.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "products")
public class Product extends RepresentationModel<Product> implements Serializable{
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id;
    
    private String descricao;
    
    private String codigo;
    
    private Double valorVenda;
    
    private Double custo;
    
    private Integer estoque;
    
    private String unidadeProduto;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ProductCategory categoria;
    
    private String fornecedor;
    
    private String tributacao;
    
    private Integer ncm;
    
    private String descNcm;
    
    private String cest;
    
    private LocalDateTime dataInclusao;
   
    private String EAN_GTIN;

    public ProductCategory getCategoria() {
		return categoria;
	}

	public void setCategoria(ProductCategory categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTributacao() {
        return tributacao;
    }

    public void setTributacao(String tributacao) {
        this.tributacao = tributacao;
    }

    public Integer getNcm() {
        return ncm;
    }

    public void setNcm(Integer ncm) {
        this.ncm = ncm;
    }

    public String getDescNcm() {
        return descNcm;
    }

    public void setDescNcm(String descNcm) {
        this.descNcm = descNcm;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getEAN_GTIN() {
        return EAN_GTIN;
    }

    public void setEAN_GTIN(String EAN_GTIN) {
        this.EAN_GTIN = EAN_GTIN;
    }
    
    
    
    
    
    
    
}
