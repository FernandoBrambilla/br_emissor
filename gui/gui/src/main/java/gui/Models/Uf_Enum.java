package gui.Models;

public enum Uf_Enum {

		AM("Amazonas"), 
		AL("Alagoas"), 
		AC("Acre"),
		AP("Amapá"), 
		BH("Bahia"), 
		PA("Pará"),
		MT("Mato Grosso"), 
		MG("Minas Gerais"),
		MS("Mato Grosso do Sul"), 
		GO("Goiás"),
		MA("Maranhão"), 
		RS("Rio Grande do Sul"),
		TO("Tocantins"), 
		PI("Piauí"), 
		SP("São Paulo"),
		RO("Rondônia"), 
		RR("Roraima"),
		PR("Paraná"), 
		CE("Ceará"), 
		PE("Pernambuco"),
		SC("Santa Catarina"), 
		PB("Paraíba"),
		RN("Rio Grande do Norte"), 
		ES("Espírito Santo"),
		RJ("Rio de Janeiro"), 
		SE("Sergipe"),
		DF("Distrito Federal");

		private String descricao; 
		
		Uf_Enum(String descricao) {
			this.descricao = descricao;
		}

		public String descricao() {
			return descricao;
		}

		public String getDescricao() {
			return descricao;
		}

}

