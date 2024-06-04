package gui.Models;

public class Clients {
	
	private Long id;
	
	private String tipo;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String cpf_cnpj;
	
	private String rg_ie;
	
	private String dateNasc_const;
	
	private String dateExp;
	
	private String address;
	
	private String addressNumber;
	
	private String addressComplement;
	
	private String city;
	
	private Uf_Enum uf;
	
	private String cep;
	
	private String obs;

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

	public String getDateNasc_const() {
		return dateNasc_const;
	}

	public String getDateExp() {
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

	public void setDateNasc_const(String dateNasc_const) {
		this.dateNasc_const = dateNasc_const;
	}

	public void setDateExp(String dateExp) {
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

	public void setUf(Uf_Enum uf) {
		this.uf = uf;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
