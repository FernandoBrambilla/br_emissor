
package com.fernando.Entities;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ncm")
public class NCM extends RepresentationModel<NCM> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Long ncm;	
	
	private String ex;

	private String tipo;

	@Column(length=512)
	private String descricao;

	private Double nacionalfederal;

	private Double importadosfederal;

	private Double estadual;

	private Double municipal;

	@Column(name = "data_inicio")
	@Temporal(TemporalType.DATE)
	private LocalDate vigenciainicio;

	@Column(name = "data_fim")
	@Temporal(TemporalType.DATE)
	private LocalDate vigenciafim;

	private String chave;

	private String versao;

	private String fonte;

	public Long getId() {
		return id;
	}

	public Long getNcm() {
		return ncm;
	}

	public String getEx() {
		return ex;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getNacionalfederal() {
		return nacionalfederal;
	}

	public Double getImportadosfederal() {
		return importadosfederal;
	}

	public Double getEstadual() {
		return estadual;
	}

	public Double getMunicipal() {
		return municipal;
	}

	public LocalDate getVigenciainicio() {
		return vigenciainicio;
	}

	public LocalDate getVigenciafim() {
		return vigenciafim;
	}

	public String getChave() {
		return chave;
	}

	public String getVersao() {
		return versao;
	}

	public String getFonte() {
		return fonte;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNcm(Long ncm) {
		this.ncm = ncm;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNacionalfederal(Double nacionalfederal) {
		this.nacionalfederal = nacionalfederal;
	}

	public void setImportadosfederal(Double importadosfederal) {
		this.importadosfederal = importadosfederal;
	}

	public void setEstadual(Double estadual) {
		this.estadual = estadual;
	}

	public void setMunicipal(Double municipal) {
		this.municipal = municipal;
	}

	public void setVigenciainicio(LocalDate vigenciainicio) {
		this.vigenciainicio = vigenciainicio;
	}

	public void setVigenciafim(LocalDate vigenciafim) {
		this.vigenciafim = vigenciafim;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

}
