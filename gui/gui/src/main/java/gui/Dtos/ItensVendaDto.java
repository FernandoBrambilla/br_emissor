package gui.Dtos;

import java.text.DecimalFormat;

import gui.Controllers.VendaControllers.PdvController;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItensVendaDto {

	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");

	private Integer id;

	private ProdutoDto produto;

	private String descricao;

	private String codigo;

	private Double valorUnitario;

	private Integer quantidade;

	private Double totalIten;

	private Button excluir;

	private Spinner<Integer> spinner = new Spinner<Integer>();

	public String getId() {
		return String.valueOf(id + 1); // << ID +1 PARA O NUMERO DE ITENS NÃO COMEÇAR EM 0
	}

	public Integer getIdInt() {
		return id;
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

	public Double getValorUnitarioDouble() {
		return valorUnitario;
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

	public ProdutoDto getProduto() {
		return produto;
	}

	public Button getExcluir() {
		excluir = new Button();
		excluir.setId(getId());
		excluir.setStyle("-fx-background-color: none; -fx-border: none;");
		excluir.setCursor(Cursor.HAND);
		Image imagem = new Image(this.getClass().getResource("excluir.png").toString());
		ImageView excluirr = new ImageView(imagem);
		excluir.setGraphic(excluirr);
		excluirr.setFitHeight(30);
		excluirr.setFitWidth(30);

		excluir.setOnMouseClicked((mouseEvent) -> {
			int index = Integer.parseInt(excluir.getId()) - 1;
			PdvController.getPdvController().removerIten(index);
		});

		return excluir;
	}

	public void setExcluir(Button excluir) {
		this.excluir = excluir;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Spinner<Integer> getSpinner() {
		Spinner<Integer> spinner = new Spinner<>(1, 9999, 1);
		spinner.setPrefWidth(100);
		spinner.setId(getId());
		spinner.getValueFactory().setValue(quantidade);
		spinner.setOnMouseClicked((mouseEvent) -> {
			int index = Integer.parseInt(spinner.getId()) - 1;
			PdvController.getPdvController().atualizarQuantidade(index, spinner.getValue());
		});
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

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setTotalIten(Double totalIten) {
		this.totalIten = totalIten;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
