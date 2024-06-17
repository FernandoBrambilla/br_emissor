package gui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gui.Models.Clients;
import gui.Models.Login;
import gui.Models.Produtos;
import gui.Models.Style;
import gui.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalController {

	static Login usuarioLogado = null;

	static String accessToken = null;

	static ConfiguracoesController configuracoes = null;

	static UsuariosController usuariosController = null;

	static ClientesController clientesController = null;

	static ProdutosController produtosController = null;

	static TableView<User> tabelaUsuarios = null;

	static TableView<Clients> tabelaClients = null;

	static TableView<Produtos> tabelaprodutos = null;

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

	@FXML
	private Button btnConfiguracoes;

	@FXML
	private Label usuario;

	@FXML
	private Label licenca;

	@FXML
	private Label secao;

	public Label getSecao() {
		return secao;
	}

	public void setSecao(Label secao) {
		this.secao = secao;
	}

	public static Login getUsuarioLogado() {
		return usuarioLogado;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static ConfiguracoesController getConfiguracoes() {
		return configuracoes;
	}

	public static void setConfiguracoes(ConfiguracoesController configuracoes) {
		PrincipalController.configuracoes = configuracoes;
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

	public static ProdutosController getProdutosController() {
		return produtosController;
	}

	public static TableView<Produtos> getTabelaprodutos() {
		return tabelaprodutos;
	}

	public static void setProdutosController(ProdutosController produtosController) {
		PrincipalController.produtosController = produtosController;
	}

	public static void setTabelaprodutos(TableView<Produtos> tabelaprodutos) {
		PrincipalController.tabelaprodutos = tabelaprodutos;
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

	@SuppressWarnings("exports")
	public Button getBtnConfiguracoes() {
		return btnConfiguracoes;
	}

	@SuppressWarnings("exports")
	public Label getUsuario() {
		return usuario;
	}

	@SuppressWarnings("exports")
	public Label getLicenca() {
		return licenca;
	}

	@SuppressWarnings("exports")
	public void setBtnConfiguracoes(Button btnConfiguracoes) {
		this.btnConfiguracoes = btnConfiguracoes;
	}

	@SuppressWarnings("exports")
	public void setUsuario(Label usuario) {
		this.usuario = usuario;
	}

	@SuppressWarnings("exports")
	public void setLicenca(Label licenca) {
		this.licenca = licenca;
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
		efeitos.hover(getBtnConfiguracoes());
	}

	public void getLogin(Login login) throws IOException {
		setUsuarioLogado(login);
		setAccessToken(login.getAccessToken());

	}

	@SuppressWarnings("exports")
	public void configuracoes(ActionEvent event) throws IOException {
		setConfiguracoes(new ConfiguracoesController());
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("ConfiguracoesViews/Configuracoes.fxml"));
		Scene scene = new Scene(painel, 800, 680);
		stage.setTitle("Configurações");
		stage.setScene(scene);
		stage.show();
		getStyle().adicinarCorBotaoSelecionado(getBtnConfiguracoes());
	}

	Style style = new Style();

	@SuppressWarnings({ "static-access" })
	@FXML
	private void initialize() throws IOException, ParseException{
		getBtnUsuarios().setOnAction((event) -> {
			try {
				setUsuariosController(new UsuariosController());
				getStyle().adicinarCorBotaoSelecionado(getBtnUsuarios());
				getStyle().removerCorBotaoSelecionado(getBtnCaixa());
				getStyle().removerCorBotaoSelecionado(getBtnClientes());
				getStyle().removerCorBotaoSelecionado(getBtnEstatisticas());
				getStyle().removerCorBotaoSelecionado(getBtnOrcamento());
				getStyle().removerCorBotaoSelecionado(getBtnProdutos());
				getStyle().removerCorBotaoSelecionado(getBtnVendas());
				getStyle().removerCorBotaoSelecionado(getBtnConfiguracoes());
				
				

				// CARREGA O MENU DE USUARIOS
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("UsuarioViews/Usuarios.fxml"));
				getMenu().setTop(loader.load());
				// CARREGA A TABELA DE USUARIOS
				setTabelaUsuarios(getUsuariosController().construirTabela());
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
				setClientesController(new ClientesController());
				getStyle().adicinarCorBotaoSelecionado(getBtnClientes());
				getStyle().removerCorBotaoSelecionado(getBtnCaixa());
				getStyle().removerCorBotaoSelecionado(getBtnUsuarios());
				getStyle().removerCorBotaoSelecionado(getBtnEstatisticas());
				getStyle().removerCorBotaoSelecionado(getBtnOrcamento());
				getStyle().removerCorBotaoSelecionado(getBtnProdutos());
				getStyle().removerCorBotaoSelecionado(getBtnVendas());
				getStyle().removerCorBotaoSelecionado(getBtnConfiguracoes());

				// CARREGA O MENU DE USUARIOS
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ClienteViews/Clientes.fxml"));
				getMenu().setTop(loader.load());

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

		getBtnProdutos().setOnAction((event) -> {
			try {

				setProdutosController(new ProdutosController());
				getStyle().adicinarCorBotaoSelecionado(getBtnProdutos());
				getStyle().removerCorBotaoSelecionado(getBtnCaixa());
				getStyle().removerCorBotaoSelecionado(getBtnClientes());
				getStyle().removerCorBotaoSelecionado(getBtnEstatisticas());
				getStyle().removerCorBotaoSelecionado(getBtnOrcamento());
				getStyle().removerCorBotaoSelecionado(getBtnUsuarios());
				getStyle().removerCorBotaoSelecionado(getBtnVendas());
				getStyle().removerCorBotaoSelecionado(getBtnConfiguracoes());
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ProdutoViews/Produtos.fxml"));
				getMenu().setTop(loader.load());

				setTabelaprodutos(produtosController.construirTabela());
				getTelaBase().setCenter(getTabelaprodutos());

				getProdutosController().setTelaBase(getTelaBase());
				getProdutosController().setTabelaprodutos(getTabelaprodutos());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
