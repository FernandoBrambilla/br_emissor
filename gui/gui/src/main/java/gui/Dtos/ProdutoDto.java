package gui.Dtos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class ProdutoDto {

	Locale brasil = new Locale("pt", "BR");

	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");

	private Long id;

	private String descricao;

	private String codigo;

	private Double valorVenda;

	private Double custo;

	private Integer estoque;

	private UnidadeProdutoDto unidadeProduto;

	private CategoriaProdutoDto categoria;

	private MarkupDto markup;

	private String fornecedor;

	private String tributacao;

	private NcmDto ncm;

	private String cest;

	private LocalDateTime dataInclusao;

	private String EAN_GTIN;
	
	private String obs; 

	public ProdutoDto(ProdutoDto p) {
		this.descricao = p.getDescricao();
		this.codigo = p.getCodigo();
		this.valorVenda = Double.valueOf(p.getValorVenda());
		this.custo = Double.valueOf(p.getCusto());
		this.estoque = p.getEstoque();
		this.unidadeProduto = p.getUnidadeProduto();
		this.categoria = p.getCategoria();
		this.markup = p.getMarkup();
		this.fornecedor = p.getFornecedor();
		this.tributacao = p.getTributacao();
		this.ncm = p.getNcmObjDto();
		this.cest = p.getCest();
		this.dataInclusao = p.getDataInclusao();
		this.EAN_GTIN = p.getEAN_GTIN();
		this.obs = p.getObs();
	}

	public ProdutoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao.toString();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getValorVenda() {
		return realFormato.format(valorVenda);
	}

	public String getCusto() {
		return realFormato.format(custo);
	}

	public Integer getEstoque() {
		return estoque;
	}

	public UnidadeProdutoDto getUnidadeProduto() {
		return unidadeProduto;
	}

	public CategoriaProdutoDto getCategoria() {
		return categoria;
	}

	public MarkupDto getMarkup() {
		return markup;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getTributacao() {
		return tributacao;
	}

	public String getCest() {
		return cest;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public String getEAN_GTIN() {
		return EAN_GTIN;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setUnidadeProduto(UnidadeProdutoDto unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public void setCategoria(CategoriaProdutoDto categoria) {
		this.categoria = categoria;
	}

	public void setMarkup(MarkupDto markup) {
		this.markup = markup;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setTributacao(String tributacao) {
		this.tributacao = tributacao;
	}

	public NcmDto getNcmObjDto() {
		return  ncm;
	}
	

	public String getNcm() {
		return id == 1 ? ncm.getDescricao() : ncm.getNcm().toString() ;
	}
 
	public void setNcm(NcmDto ncm) {
		this.ncm = ncm;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setEAN_GTIN(String eAN_GTIN) {
		EAN_GTIN = eAN_GTIN;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}