package gui.Dtos;

import java.time.LocalDate;

public class ClienteDto {

	private Long id;

	private String tipo;

	private String name;

	private String phone;

	private String email;

	private String cpf_cnpj;

	private String rg_ie;

	private LocalDate dateNasc_const;

	private LocalDate dateExp;

	private String address;

	private String addressNumber;

	private String addressComplement;

	private String bairro;

	private String city;

	private Uf_Enum uf;

	private String cep;

	private String obs;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public String getRg_ie() {
		return rg_ie;
	}

	public LocalDate getDateNasc_const() {
		return dateNasc_const;
	}

	public LocalDate getDateExp() {
		return dateExp;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public String getCity() {
		return city;
	}

	public Uf_Enum getUf() {
		return uf;
	}

	public String getCep() {
		return cep;
	}

	public String getObs() {
		return obs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public void setRg_ie(String rg_ie) {
		this.rg_ie = rg_ie;
	}

	public void setDateNasc_const(LocalDate dateNasc_const) {
		this.dateNasc_const = dateNasc_const;
	}

	public void setDateExp(LocalDate dateExp) {
		this.dateExp = dateExp;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setUf(String uf) {
		switch (uf) {
		case "Amazonas":
			this.uf = Uf_Enum.AM;
			break;
		case "Alagoas":
			this.uf = Uf_Enum.AL;
			break;
		case "Acre":
			this.uf = Uf_Enum.AC;
			break;
		case "Amapá":
			this.uf = Uf_Enum.AP;
			break;
		case "Bahia":
			this.uf = Uf_Enum.BH;
			break;
		case "Pará":
			this.uf = Uf_Enum.PA;
			break;
		case "Mato Grosso":
			this.uf = Uf_Enum.MT;
			break;
		case "Minas Gerais":
			this.uf = Uf_Enum.MG;
			break;
		case "Mato Grosso do Sul":
			this.uf = Uf_Enum.MS;
			break;
		case "Goiás":
			this.uf = Uf_Enum.GO;
			break;
		case "Maranhão":
			this.uf = Uf_Enum.MA;
			break;
		case "Rio Grande do Sul":
			this.uf = Uf_Enum.RS;
			break;
		case "Tocantins":
			this.uf = Uf_Enum.TO;
			break;
		case "Piauí":
			this.uf = Uf_Enum.PI;
			break;
		case "São Paulo":
			this.uf = Uf_Enum.SP;
			break;
		case "Rondônia":
			this.uf = Uf_Enum.RO;
			break;
		case "Roraima":
			this.uf = Uf_Enum.RR;
			break;
		case "Paraná":
			this.uf = Uf_Enum.PR;
			break;
		case "Ceará":
			this.uf = Uf_Enum.CE;
			break;
		case "Pernambuco":
			this.uf = Uf_Enum.PE;
			break;
		case "Santa Catarina":
			this.uf = Uf_Enum.SC;
			break;
		case "Paraíba":
			this.uf = Uf_Enum.PB;
			break;
		case "Rio Grande do Norte":
			this.uf = Uf_Enum.RN;
			break;
		case "Espírito Santo":
			this.uf = Uf_Enum.ES;
			break;
		case "Rio de Janeiro":
			this.uf = Uf_Enum.RJ;
			break;
		case "ergipe":
			this.uf = Uf_Enum.SE;
			break;
		case "Distrito Federal":
			this.uf = Uf_Enum.DF;
			break;

		default:
			this.uf = Uf_Enum.PR;
			break;
		}
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
