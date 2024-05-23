package gui.Services;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Principal {
	
	@FXML
	private BorderPane telaBase;

	@FXML
	private BorderPane menu;
	
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

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public BorderPane getMenu() {
		return menu;
	}

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

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void setMenu(BorderPane menu) {
		this.menu = menu;
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
	
	
	 @FXML
	    private void initialize() {
	        getBtnUsuarios().setOnAction((event) -> {
	            try {
	                FXMLLoader loader = new FXMLLoader();
	                loader.setLocation(getClass().getResource("PrincipalViews/Principal.fxml"));
	                AnchorPane anchorTest = (AnchorPane) loader.load();
	                getTelaBase().setCenter(anchorTest);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        });
	 }
}
