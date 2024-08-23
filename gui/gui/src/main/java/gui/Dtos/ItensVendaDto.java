package gui.Dtos;

import java.text.DecimalFormat;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;

public class ItensVendaDto {
	
	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");
	
	private Long id;

	private ProdutoDto produto;
	
	private String descricao;
	
	private String codigo;
	
	private Double valorUnitario;
	
	private Double desconto;
	
	private Integer quantidade;

	private Double totalIten;
	
	private Button excluir;
	
	private Spinner<Integer> spinner = new Spinner<Integer>();

	public String getId() {
		return String.valueOf(id);
	}
	
	public String getDescricao() {
		return produto.getDescricao();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getValorUnitario() {
		return realFormato.format(valorUnitario);
	}

	public String getCodigo() {
		return produto.getCodigo();
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public String getDesconto() {
		return realFormato.format(desconto); 
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public ProdutoDto getProduto() {
		return produto;
	}


	public Button getExcluir() {

		return excluir;
	}

	public void setExcluir(Button excluir) {
		this.excluir = excluir;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Spinner<Integer> getSpinner() {
		spinner.setPrefWidth(100);
		return spinner;
		
	}

	public void setSpinner(Spinner<Integer> spinner) {
		this.spinner = spinner;
	}

	public String getTotalIten() {
		return realFormato.format(totalIten); 
	}
	
	public Double getTotalItenDouble() {
		return totalIten; 
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
	}

	public void setTotalIten(Double totalIten) {
		this.totalIten = totalIten;
	}
	
	

}
