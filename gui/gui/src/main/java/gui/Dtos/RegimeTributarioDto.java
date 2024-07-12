package gui.Dtos;

public enum RegimeTributarioDto {
	
	
	MEI("MEI - Micro Empreendedor Individual"),
	SN("Simples Nacional"),
	RN("Regime Normal");
	

	private String descricao; 
	
	RegimeTributarioDto(String descricao) {
		this.descricao = descricao;
	}

	public String descricao() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
