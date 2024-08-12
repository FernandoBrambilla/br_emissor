package gui.Dtos;

import java.time.LocalDate;

public class NcmDto {

	private Long id;
	
	private Long ncm;

	private String ex;

	private String tipo;

	private String descricao;

	private Double nacionalfederal;

	private Double importadosfederal;

	private Double estadual;

	private Double municipal;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	private String chave;

	private String versao;

	private String fonte;

	public NcmDto(Long ncm, String ex, String tipo, String descricao, Double nacionalfederal,
			Double importadosfederal, Double estadual, Double municipal, LocalDate dataInicio, LocalDate dataFim,
			String chave, String versao, String fonte) {

		this.ncm = ncm;
		this.ex = ex;
		this.tipo = tipo;
		this.descricao = descricao;
		this.nacionalfederal = nacionalfederal;
		this.importadosfederal = importadosfederal;
		this.estadual = estadual;
		this.municipal = municipal;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.chave = chave;
		this.versao = versao;
		this.fonte = fonte;
	}

	public NcmDto() {

	}
	
	public NcmDto(NcmDto selectedItem) {
		this.ncm = selectedItem.getNcmObj();
		this.ex = selectedItem.getEx();
		this.tipo = selectedItem.getTipo();
		this.descricao = selectedItem.getDescricao();
		this.nacionalfederal = selectedItem.getNacionalfederal();
		this.importadosfederal = selectedItem.getImportadosfederal();
		this.estadual = selectedItem.getEstadual();
		this.municipal = selectedItem.getMunicipal();
		this.dataInicio = selectedItem.getDataInicio();
		this.dataFim = selectedItem.getDataFim();
		this.chave = selectedItem.getChave();
		this.versao = selectedItem.getVersao();
		this.fonte = selectedItem.getFonte();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNcmObj() {
		return ncm;
	}
	
	public String getNcm() {
		return id == 1 ? descricao : ncm.toString();
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

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
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

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
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
