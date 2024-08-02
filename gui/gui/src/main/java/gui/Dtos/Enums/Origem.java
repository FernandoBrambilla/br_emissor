package gui.Dtos.Enums;

public enum Origem {
	//ORIGEM DA MERCADORIA:
	
	NACIONAL_0(0, "Nacional, exceto as indicadas nos códigos 3 a 5"),
	
	ESTRANGEIRA_1(1, " Estrangeira - Importação direta, exceto a indicada no código 6"),
	
	ESTRANGEIRA_2(2, "Adquirida no mercado interno, exceto a indicada no código 7"),
	
	NACIONAL_3(3, "Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40%"),
	
	NACIONAL_4(4 , "Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam o Decreto-Lei nº 288/1967 , e as Leis nºs 8.248/1991, 8.387/1991, 10.176/2001 e 11.484/2007"),

	NACIONAL_5(5, "Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%"),

	ESTRANGEIRA_6 (6, "Estrangeira - Importação direta, sem similar nacional, constante em lista de Resolução Camex e gás natural"),
		
	ESTRANGEIRA_7 (7 , "Estrangeira - Adquirida no mercado interno, sem similar nacional, constante em lista de Resolução Camex e gás natural");
	
	private final int valor;
    private final String descricao;
	
    Origem(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor(){
	return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

