package gui;

import java.io.IOException;

import gui.Services.Clients;
import gui.Services.Login;
import gui.Services.Style;
import gui.Services.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PrincipalController {

	static Login usuarioLogado = null;

	static String accessToken = null;

	static UsuariosController usuariosController = null;

	static ClientesController clientesController = null;

	static TableView<User> tabelaUsuarios = null;

	static TableView<Clients> tabelaClients = null;

	@FXML
	private BorderPane menu;

	@FXML
	private BorderPane telaBase;

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

	public static Login getUsuarioLogado() {
		return usuarioLogado;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static UsuariosController getUsuariosController() {
		return usuariosController;
	}

	public static ClientesController getClientesController() {
		return clientesController;
	}

	public static TableView<User> getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public static TableView<Clients> getTabelaClients() {
		return tabelaClients;
	}

	@SuppressWarnings("exports")
	public BorderPane getMenu() {
		return menu;
	}

	@SuppressWarnings("exports")
	public BorderPane getTelaBase() {
		return telaBase;
	}

	@SuppressWarnings("exports")
	public Button getBtnVendas() {
		return btnVendas;
	}

	@SuppressWarnings("exports")
	public Button getBtnOrcamento() {
		return btnOrcamento;
	}

	@SuppressWarnings("exports")
	public Button getBtnClientes() {
		return btnClientes;
	}

	@SuppressWarnings("exports")
	public Button getBtnProdutos() {
		return btnProdutos;
	}

	@SuppressWarnings("exports")
	public Button getBtnCaixa() {
		return btnCaixa;
	}

	@SuppressWarnings("exports")
	public Button getBtnUsuarios() {
		return btnUsuarios;
	}

	@SuppressWarnings("exports")
	public Button getBtnEstatisticas() {
		return btnEstatisticas;
	}

	public Style getStyle() {
		return style;
	}

	public static void setUsuarioLogado(Login usuarioLogado) {
		PrincipalController.usuarioLogado = usuarioLogado;
	}

	public static void setAccessToken(String accessToken) {
		PrincipalController.accessToken = accessToken;
	}

	public static void setUsuariosController(UsuariosController usuariosController) {
		PrincipalController.usuariosController = usuariosController;
	}

	public static void setClientesController(ClientesController clientesController) {
		PrincipalController.clientesController = clientesController;
	}

	public static void setTabelaUsuarios(TableView<User> tabelaUsuarios) {
		PrincipalController.tabelaUsuarios = tabelaUsuarios;
	}

	public static void setTabelaClients(TableView<Clients> tabelaClients) {
		PrincipalController.tabelaClients = tabelaClients;
	}

	@SuppressWarnings("exports")
	public void setMenu(BorderPane menu) {
		this.menu = menu;
	}

	@SuppressWarnings("exports")
	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	@SuppressWarnings("exports")
	public void setBtnVendas(Button btnVendas) {
		this.btnVendas = btnVendas;
	}

	@SuppressWarnings("exports")
	public void setBtnOrcamento(Button btnOrcamento) {
		this.btnOrcamento = btnOrcamento;
	}

	@SuppressWarnings("exports")
	public void setBtnClientes(Button btnClientes) {
		this.btnClientes = btnClientes;
	}

	@SuppressWarnings("exports")
	public void setBtnProdutos(Button btnProdutos) {
		this.btnProdutos = btnProdutos;
	}

	@SuppressWarnings("exports")
	public void setBtnCaixa(Button btnCaixa) {
		this.btnCaixa = btnCaixa;
	}

	@SuppressWarnings("exports")
	public void setBtnUsuarios(Button btnUsuarios) {
		this.btnUsuarios = btnUsuarios;
	}

	@SuppressWarnings("exports")
	public void setBtnEstatisticas(Button btnEstatisticas) {
		this.btnEstatisticas = btnEstatisticas;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnVendas());
		efeitos.hover(getBtnOrcamento());
		efeitos.hover(getBtnClientes());
		efeitos.hover(getBtnProdutos());
		efeitos.hover(getBtnCaixa());
		efeitos.hover(getBtnUsuarios());
		efeitos.hover(getBtnEstatisticas());
	}

	public void getLogin(Login login) throws IOException {
		usuarioLogado = login;
		setAccessToken(login.getAccessToken());

	}

	Style style = new Style();

	@SuppressWarnings("static-access")
	@FXML
	private void initialize() throws IOException {
		getBtnUsuarios().setOnAction((event) -> {
			try {
				usuariosController = new UsuariosController();

				// CARREGA O MENU DE USUARIOS
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("UsuarioViews/Usuarios.fxml"));
				getMenu().setCenter(loader.load());

				// CARREGA A TABELA DE USUARIOS
				tabelaUsuarios = usuariosController.construirTabela();
				getTelaBase().setCenter(tabelaUsuarios);
				usuariosController.setTelaBase(telaBase);
				UsuariosController.setTabelaUsuarios(tabelaUsuarios);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		getBtnClientes().setOnAction((event) -> {
			try {
				clientesController = new ClientesController();

				// CARREGA O MENU DE USUARIOS
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ClienteViews/Clientes.fxml"));
				getMenu().setCenter(loader.load());

				// CARREGA A TABELA DE CLIENTES
				tabelaClients = clientesController.construirTabela();
				getTelaBase().setCenter(tabelaClients);
				clientesController.setTelaBase(telaBase);
				clientesController.setTabelaClientes(tabelaClients);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
