package gui.Dtos;

import java.text.DecimalFormat;

public class ItensVendaDto {
	
	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");
	
	private Long id;

	private ProdutoDto produto;
	
	private String descricao;
	
	private Double valorUnitario;
	
	private Double desconto;
	
	private Integer quantidade;

	private Double totalIten;

	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return produto.getDescricao();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getValorUnitario() {
		return realFormato.format(valorUnitario);
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public String getDesconto() {
		return realFormato.format(desconto); 
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public ProdutoDto getProduto() {
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

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setTotalIten(Double totalIten) {
		this.totalIten = totalIten;
	}
	
	

}
