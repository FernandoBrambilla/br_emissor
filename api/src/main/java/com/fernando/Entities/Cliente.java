package com.fernando.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;


@Entity 
@Table(name = "clients")
public class Clients extends RepresentationModel<Clients> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

        private String tipo;
        
	@Column (nullable = false)
	private String name;
	
	
	private String phone;
	
	
	private String email;
	
        
	@Column (name = "cpf/cnpj")
	private String cpf_cnpj;
	
        
	@Column (name = "rg/ie")
	private String rg_ie;
	
    @JsonFormat(pattern="dd-MM-yyyy")
	@Column (name = "date_nasc/const")
	@Temporal(TemporalType.DATE)
	private LocalDate dateNasc_const;
	
        
    @JsonFormat(pattern="dd-MM-yyyy")
	@Column (name = "date_exp")
	@Temporal(TemporalType.DATE)
	private LocalDate dateExp;
	
	
	private String address;
	
	@Column (name = "address_number")
	private String addressNumber;
	
	@Column (name = "address_complement")
	private String addressComplement;
        
        private String bairro;
	
	private String city;
	
	private String uf;
	
	private String cep;	

        private String obs;
        
    public Clients() {
    }

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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getRg_ie() {
        return rg_ie;
    }

    public void setRg_ie(String rg_ie) {
        this.rg_ie = rg_ie;
    }

    public LocalDate getDateNasc_const() {
        return dateNasc_const;
    }

    public void setDateNasc_const(LocalDate dateNasc_const) {
        this.dateNasc_const = dateNasc_const;
    }

    public LocalDate getDateExp() {
        return dateExp;
    }

    public void setDateExp(LocalDate dateExp) {
        this.dateExp = dateExp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}