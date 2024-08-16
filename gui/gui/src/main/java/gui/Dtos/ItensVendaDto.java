package gui.Dtos;

public class ItensVendaDto {
	
	private Long id;

	private ProdutoDto produto;
	
	private Integer quantidade;

	private Double totalIten;

	public Long getId() {
		return id;
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
