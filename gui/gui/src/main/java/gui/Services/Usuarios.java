package gui.Services;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Usuarios {
	
	private TableView<User> tabelaUsuarios;	
	
	@FXML
	private BorderPane telaBase;
	
	@FXML
	private BorderPane menu;
	
	private Button btnNovoUsuario;
	
	private Button btnEditar;
	
	private Button btnInativar;
	
	private TextField fullName;
	
	private TextField username;

	private TextField email;
	
	private TextField senha1;
	
	private TextField senha2;
	
	private Button btnCancelar;
	
	private Button btnSalvar;
	
	private Label info;

	public TableView<User> getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public Button getBtnNovoUsuario() {
		return btnNovoUsuario;
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public Button getBtnInativar() {
		return btnInativar;
	}

	public TextField getFullName() {
		return fullName;
	}

	public TextField getUsername() {
		return username;
	}

	public TextField getEmail() {
		return email;
	}

	public TextField getSenha1() {
		return senha1;
	}

	public TextField getSenha2() {
		return senha2;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Label getInfo() {
		return info;
	}

	public void setTabelaUsuarios(TableView<User> tabelaUsuarios) {
		this.tabelaUsuarios = tabelaUsuarios;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void setBtnNovoUsuario(Button btnNovoUsuario) {
		this.btnNovoUsuario = btnNovoUsuario;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void setBtnInativar(Button btnInativar) {
		this.btnInativar = btnInativar;
	}

	public void setFullName(TextField fullName) {
		this.fullName = fullName;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public void setSenha1(TextField senha1) {
		this.senha1 = senha1;
	}

	public void setSenha2(TextField senha2) {
		this.senha2 = senha2;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setInfo(Label info) {
		this.info = info;
	}

	public BorderPane getMenu() {
		return menu;
	}

	public void setMenu(BorderPane menu) {
		this.menu = menu;
	}
	
	

}
