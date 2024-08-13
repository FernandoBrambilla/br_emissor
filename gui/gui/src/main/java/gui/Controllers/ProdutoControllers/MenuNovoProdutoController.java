package gui.Controllers.ProdutoControllers;

import java.io.IOException;

import gui.App;
import gui.Controllers.TributacaoControllers.NCMController;
import gui.Dtos.ProdutoDto;
import gui.Dtos.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuNovoProdutoController {

	public static MenuNovoProdutoController menuNovoProdutoController;

	public static ProdutoDto novoProduto;

	@FXML
	BorderPane telaBase;

	@FXML
	private ImageView imagem;

	@FXML
	private Label titulo;

	@FXML
	private Button btnCadastro;

	@FXML
	private Button btnTributacao;

	@FXML
	private Button btnFornecedor;

	@FXML
	private Button btnOpcoes;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public ImageView getImagem() {
		return imagem;
	}

	public Label getTitulo() {
		return titulo;
	}

	public Button getBtnCadastro() {
		return btnCadastro;
	}

	public Button getBtnTributacao() {
		return btnTributacao;
	}

	public Button getBtnFornecedor() {
		return btnFornecedor;
	}

	public Button getBtnOpcoes() {
		return btnOpcoes;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void setImagem(ImageView imagem) {
		this.imagem = imagem;
	}

	public void setTitulo(Label titulo) {
		this.titulo = titulo;
	}

	public void setBtnCadastro(Button btnCadastro) {
		this.btnCadastro = btnCadastro;
	}

	public void setBtnTributacao(Button btnTributacao) {
		this.btnTributacao = btnTributacao;
	}

	public void setBtnFornecedor(Button btnFornecedor) {
		this.btnFornecedor = btnFornecedor;
	}

	public void setBtnOpcoes(Button btnOpcoes) {
		this.btnOpcoes = btnOpcoes;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void initialize() throws IOException {
		menuNovoProdutoController = this;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ProdutoViews/NovoProduto.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnCadastro().setStyle("-fx-background-color:  deedfc; ");
		
	}

	@SuppressWarnings("exports")
	public void btnCadastro(ActionEvent action) throws Exception {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ProdutoViews/NovoProduto.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnCadastro().setStyle("-fx-background-color:  deedfc; ");
		capturarNovoProduto();

	}

	@SuppressWarnings("exports")
	public void btnTributacao(ActionEvent action) throws Exception {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("TributacaoViews/Tributacao.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnTributacao().setStyle("-fx-background-color:  deedfc; ");
		capturarNovoProduto();

	}

	private void capturarNovoProduto() throws Exception {
		novoProduto = new ProdutoDto(); 
		novoProduto.setEAN_GTIN(NovoProdutoController.novoProdutoController.getEan_getin().getText());
		novoProduto.setDescricao(NovoProdutoController.novoProdutoController.getDescricao().getText());
		novoProduto.setCategoria(NovoProdutoController.getCategoria().getSelectionModel().getSelectedItem());
		novoProduto.setUnidadeProduto(NovoProdutoController.getUnidade().getSelectionModel().getSelectedItem());

		novoProduto.setCusto(NovoProdutoController.novoProdutoController.getCusto().getText().isEmpty() ? 0D
				: Double.parseDouble(NovoProdutoController.novoProdutoController.getCusto().getText().replace("R$ ", "")
						.replace(",", ".")));

		novoProduto.setValorVenda(NovoProdutoController.novoProdutoController.getValor().getText().isEmpty() ? 0D
				: Double.parseDouble(NovoProdutoController.novoProdutoController.getValor().getText().replace("R$ ", "")
						.replace(",", ".")));

		novoProduto.setEstoque(NovoProdutoController.novoProdutoController.getEstoque().getText().isEmpty() ? 0
				: Integer.parseInt(NovoProdutoController.novoProdutoController.getEstoque().getText()));

		novoProduto.setObs(NovoProdutoController.novoProdutoController.getObs().getText());

		// SELECIONA NCM PADRÃO OU NCM SELECIONADO
		if (NCMController.ncmSelecionado == null) {
			novoProduto.setNcm(NCMController.buscarNcmById(1L));
		} else {
			novoProduto.setNcm(NCMController.ncmSelecionado);
		}

	}

	@SuppressWarnings("exports")
	public void btnFornecedor(ActionEvent action) throws Exception {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ProdutoViews/Fornecedor.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnFornecedor().setStyle("-fx-background-color:  deedfc; ");
		capturarNovoProduto();

	}

	@SuppressWarnings("exports")
	public void btnOpcoes(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ProdutoViews/Opcoes.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnOpcoes().setStyle("-fx-background-color:  deedfc; ");

	}

	private void limparSelecaoBtns() {
		getBtnCadastro().setStyle("-fx-background-color:  white;");
		getBtnTributacao().setStyle("-fx-background-color:  white;");
		getBtnFornecedor().setStyle("-fx-background-color:  white;");
		getBtnOpcoes().setStyle("-fx-background-color:  white;");

	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		NovoProdutoController.novoProdutoController.criarProduto();
	
	} 
	
	public void fecharTela() {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) {
		fecharTela();
	}

}
