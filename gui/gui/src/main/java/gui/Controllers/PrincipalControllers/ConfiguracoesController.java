package gui.Controllers.PrincipalControllers;

import java.io.IOException;

import gui.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class  ConfiguracoesController {

	@FXML
	BorderPane telaBase;

	@FXML
	private ImageView imagem;

	@FXML
	private Label titulo;

	@FXML
	private Button btnIdentificacao;

	@FXML
	private Button btnLicenca;

	@FXML
	private Button btnTabelaIBPT;

	@FXML
	private Button btnCertificado;

	public ImageView getImagem() {
		return imagem;
	}

	public Label getTitulo() {
		return titulo;
	}

	public Button getBtnIdentificacao() {
		return btnIdentificacao;
	}

	public Button getBtnLicenca() {
		return btnLicenca;
	}

	public Button getBtnTabelaIBPT() {
		return btnTabelaIBPT;
	}

	public Button getBtnCertificado() {
		return btnCertificado;
	}

	public void setImagem(ImageView imagem) {
		this.imagem = imagem;
	}

	public void setTitulo(Label titulo) {
		this.titulo = titulo;
	}

	public void setBtnIdentificacao(Button btnIdentificacao) {
		this.btnIdentificacao = btnIdentificacao;
	}

	public void setBtnLicenca(Button btnLicenca) {
		this.btnLicenca = btnLicenca;
	}

	public void setBtnTabelaIBPT(Button btnTabelaIBPT) {
		this.btnTabelaIBPT = btnTabelaIBPT;
	}

	public void setBtnCertificado(Button btnCertificado) {
		this.btnCertificado = btnCertificado;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void initialize() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ConfiguracoesViews/Identificacao.fxml"));
		getTelaBase().setCenter(loader.load());
		getTitulo().setText("Identificação");
		getBtnIdentificacao().setStyle("-fx-background-color:  deedfc; ");
		

	}

	@SuppressWarnings("exports")
	public void btnIdentificacao(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ConfiguracoesViews/Identificacao.fxml"));
		getTelaBase().setCenter(loader.load());
		getTitulo().setText("Identificação");
		getBtnIdentificacao().setStyle("-fx-background-color:  deedfc; ");

	}

	@SuppressWarnings("exports")
	public void btnLicenca(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ConfiguracoesViews/Licenca.fxml"));
		getTelaBase().setCenter(loader.load());
		getTitulo().setText("Licença");
		getBtnLicenca().setStyle("-fx-background-color:  deedfc; ");

	}

	@SuppressWarnings("exports")
	public void btnTabelaIBPT(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ConfiguracoesViews/TabelaIBPT.fxml"));
		getTelaBase().setCenter(loader.load());
		getTitulo().setText("Tabela IBPT");
		getBtnTabelaIBPT().setStyle("-fx-background-color:  deedfc; ");
	}

	@SuppressWarnings("exports")
	public void btnCertificado(ActionEvent action) throws IOException {
		limparSelecaoBtns();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("ConfiguracoesViews/Certificado.fxml"));
		getTelaBase().setCenter(loader.load());
		getTitulo().setText("Certificado Digital");
		getBtnCertificado().setStyle("-fx-background-color:  deedfc; ");
	}

	private void limparSelecaoBtns() {
		getBtnIdentificacao().setStyle("-fx-background-color:  white;");
		getBtnLicenca().setStyle("-fx-background-color:  white;");
		getBtnTabelaIBPT().setStyle("-fx-background-color:  white;");
		getBtnCertificado().setStyle("-fx-background-color:  white;");
	}

}
