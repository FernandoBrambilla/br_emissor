package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MenuNovoProdutoController {
	
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

	public void initialize() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProdutoViews/NovoProduto.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnCadastro().setStyle("-fx-background-color:  deedfc; ");

	}
	
	@SuppressWarnings("exports")
	public void btnCadastro(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProdutoViews/NovoProduto.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnCadastro().setStyle("-fx-background-color:  deedfc; ");

	}
	
	@SuppressWarnings("exports")
	public void btnTributacao(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProdutoViews/Tributacao.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnTributacao().setStyle("-fx-background-color:  deedfc; ");

	}
	
	@SuppressWarnings("exports")
	public void btnFornecedor(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProdutoViews/Fornecedor.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnFornecedor().setStyle("-fx-background-color:  deedfc; ");

	}
	
	@SuppressWarnings("exports")
	public void btnOpcoes(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProdutoViews/Opcoes.fxml"));
		getTelaBase().setCenter(loader.load());
		getBtnOpcoes().setStyle("-fx-background-color:  deedfc; ");

	}

	
	private void limparSelecaoBtns() {
		getBtnCadastro().setStyle("-fx-background-color:  white;");
		getBtnTributacao().setStyle("-fx-background-color:  white;");
		getBtnFornecedor().setStyle("-fx-background-color:  white;");
		getBtnOpcoes().setStyle("-fx-background-color:  white;");
		
	}

}
