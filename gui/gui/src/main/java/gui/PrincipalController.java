package gui;

import java.io.IOException;

import gui.Services.Effects;
import gui.Services.Login;
import gui.Services.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class PrincipalController{
	
	static Login usuarioLogado = null;
	
	static String accessToken = null;
	
	static UsuariosController usuariosController = null;
	
	static TableView<User> tabelaUsuarios = null;
	
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
	
	


	public static String getAccessToken() {
		return accessToken;
	}
	
	@SuppressWarnings("exports")
	public BorderPane getMenu() {
		return menu;
	}

	@SuppressWarnings("exports")
	public void setMenu(BorderPane menu) {
		this.menu = menu;
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

	public static void setAccessToken(String accessToken) {
		PrincipalController.accessToken = accessToken;
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
	
	public void getLogin(Login login) throws IOException{
		usuarioLogado = login;
		setAccessToken(login.getAccessToken());
		
	}
	
	@FXML
    private void initialize() throws IOException {
		getBtnUsuarios().setOnAction((event) -> {
            try {
            	usuariosController = new UsuariosController();
            	
            	//CARREGA O MENU DE USUARIOS
            	 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("UsuarioViews/Usuarios.fxml"));
                 getMenu().setCenter(loader.load());
                 
                 //CARREGA A TABELA DE USUARIOS
                 tabelaUsuarios = usuariosController.construirTabela();
                 getTelaBase().setCenter(tabelaUsuarios);
                 usuariosController.setTelaBase(telaBase);
                 usuariosController.setTabelaUsuarios(tabelaUsuarios);
           
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
				e.printStackTrace();
			}
        });
		
		getBtnVendas().setOnAction((event) -> {
           System.out.println("vendas");
			
        });
	}

}

    
