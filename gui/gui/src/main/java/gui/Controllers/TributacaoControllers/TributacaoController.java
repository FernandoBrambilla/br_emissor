package gui.Controllers.TributacaoControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TributacaoController implements Initializable {
	
	public static TributacaoController INSTANCE;
	
	@FXML
	private TextField ncm;
	
	@FXML
	private ImageView pesquisarNcm;

	@FXML
	private TextField cest;

	@FXML
	private ImageView pesquisarCest;

	@FXML
	private TextField tributacao;

	@FXML
	private ImageView pesquisarTributacao;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;
	
	
	public TextField getNcm() {
		return ncm;
	}
	
	
	public ImageView getPesquisarNcm() {
		return pesquisarNcm;
	}

	public TextField getCest() {
		return cest;
	}

	public ImageView getPesquisarCest() {
		return pesquisarCest;
	}

	public TextField getTributacao() {
		return tributacao;
	}

	public ImageView getPesquisarTributacao() {
		return pesquisarTributacao;
	}
	
	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setNcm(TextField ncm) {
		this.ncm = ncm;
	}


	public void setPesquisarNcm(ImageView pesquisarNcm) {
		this.pesquisarNcm = pesquisarNcm;
	}

	public void setCest(TextField cest) {
		this.cest = cest;
	}

	public void setPesquisarCest(ImageView pesquisarCest) {
		this.pesquisarCest = pesquisarCest;
	}

	public void setTributacao(TextField tributacao) {
		this.tributacao = tributacao;
	}

	public void setPesquisarTributacao(ImageView pesquisarTributacao) {
		this.pesquisarTributacao = pesquisarTributacao;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	INSTANCE = this;
	}
	
	public void pesquisarNCM(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("TributacaoViews/NCM.fxml"));
		Scene scene = new Scene(painel);
		stage.setTitle("NCM");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void pesquisarCEST(MouseEvent event) {

	} 

	public void pesquisarTributacao(MouseEvent event) {

	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {

	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

	

}
