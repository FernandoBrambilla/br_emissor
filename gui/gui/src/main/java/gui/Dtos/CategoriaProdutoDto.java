package gui.Dtos;

public class CategoriaProdutoDto {

	private Integer id;
	
	private String descricao;

	public CategoriaProdutoDto(String descricao) {
	
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
