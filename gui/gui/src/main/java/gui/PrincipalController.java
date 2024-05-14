package gui;

import java.io.IOException;

import gui.Services.Effects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PrincipalController extends AnchorPane{
	
	static String accessToken = null;
	
	@FXML
	BorderPane telaBase;

	@FXML
	private Button btnVendas;
	
	@FXML
	private Button btnOrcamento;
	
	@FXML
	private Button btnClientes;
	
	@FXML
	private Button btnProdutos;
	
	@FXML
	private Button btnCaixa;
	
	@FXML
	private Button btnUsuarios;
	
	@FXML
	private Button btnEstatisticas;
	

	
	public Button getBtnVendas() {
		return btnVendas;
	}

	public Button getBtnOrcamento() {
		return btnOrcamento;
	}

	public Button getBtnClientes() {
		return btnClientes;
	}

	public Button getBtnProdutos() {
		return btnProdutos;
	}

	public Button getBtnCaixa() {
		return btnCaixa;
	}

	public Button getBtnUsuarios() {
		return btnUsuarios;
	}

	public Button getBtnEstatisticas() {
		return btnEstatisticas;
	}

	public Pane getTelaBase() {
		return telaBase;
	}

	public void setBtnVendas(Button btnVendas) {
		this.btnVendas = btnVendas;
	}

	public void setBtnOrcamento(Button btnOrcamento) {
		this.btnOrcamento = btnOrcamento;
	}

	public void setBtnClientes(Button btnClientes) {
		this.btnClientes = btnClientes;
	}

	public void setBtnProdutos(Button btnProdutos) {
		this.btnProdutos = btnProdutos;
	}

	public void setBtnCaixa(Button btnCaixa) {
		this.btnCaixa = btnCaixa;
	}

	public void setBtnUsuarios(Button btnUsuarios) {
		this.btnUsuarios = btnUsuarios;
	}

	public void setBtnEstatisticas(Button btnEstatisticas) {
		this.btnEstatisticas = btnEstatisticas;
	}
	
	

	public void aplicaEfeitos() {
		Effects efeitos = new Effects();
		efeitos.hover(getBtnVendas());
		efeitos.hover(getBtnOrcamento());
		efeitos.hover(getBtnClientes());
		efeitos.hover(getBtnProdutos());
		efeitos.hover(getBtnCaixa());
		efeitos.hover(getBtnUsuarios());
		efeitos.hover(getBtnEstatisticas());
	}
	
	
	public void loadTelaPrincipal(String token) throws IOException{
		App.setRoot("Principal");
		this.accessToken = token;
		
		
	}
	
	 private void loadTelaUsuario() throws IOException {
		UsuariosController usuario = new UsuariosController();
		Parent root = FXMLLoader.load(getClass().getResource("Usuarios.fxml"));
		telaBase.setCenter(root);
		
	 }
	 
	 public void btnUsuario(ActionEvent action) throws IOException{
		 loadTelaUsuario();
		 
	    }
	 
	 
	 public String getAccessToken() {
		return accessToken;
	 }
	 
	 
}

    
