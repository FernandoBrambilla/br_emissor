package gui.Models;

public enum RegimeTributario {
	
	
	MEI("MEI - Micro Empreendedor Individual"),
	SN("Simples Nacional"),
	RN("Regime Normal");
	

	private String descricao; 
	
	RegimeTributario(String descricao) {
		this.descricao = descricao;
	}

	public String descricao() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
