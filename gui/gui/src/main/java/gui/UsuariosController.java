package gui;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Services.Effects;
import gui.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class UsuariosController  extends AnchorPane{
	
	PrincipalController controller = new PrincipalController();
	
	Effects effects = new Effects();
	
	@FXML
	private BorderPane telaUsuarios;

	@FXML
	private Button btnNovoUsuario;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnInativar;
	
	@FXML
	private TableView tabelaUsuarios;
	
	@FXML
	private TextField fullName;
	
	@FXML
	private TextField username;

	@FXML
	private TextField email;
	
	@FXML
	private TextField senha1;
	
	@FXML
	private TextField senha2;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Label info;

	public BorderPane getTelaUsuarios() {
		return telaUsuarios;
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

	public TableView getTabelaUsuarios() {
		return tabelaUsuarios;
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

	public void setTelaUsuarios(BorderPane telaUsuarios) {
		this.telaUsuarios = telaUsuarios;
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

	public void setTabelaUsuarios(TableView tabelaUsuarios) {
		this.tabelaUsuarios = tabelaUsuarios;
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

	 public Label getInfo() {
		return info;
	}

	public void setInfo(Label info) {
		this.info = info;
	}
	
	@FXML
	 public void loadTelaUsuarios(Login logado) throws IOException{
	 
	    
	}
	
	public void aplicaEfeitos() {
		Effects efeitos = new Effects();
		efeitos.hover(getBtnNovoUsuario());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnInativar());
		
	}
	
	public void populaTabela() {
		//String accessToken = new Login().getAccessToken();
		
	}
	
	//CHAMA TELA DE CADSTRAR USUÁRIO
	public void cadastrar(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("NovoUsuario.fxml"));
		Scene scene = new Scene(painel, 600, 450);
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void salvar(ActionEvent action) throws IOException, InterruptedException {
		//GET O TOKEN
		String token = controller.getAccessToken();
		
		//PADRÃO PARA NOVOS USUÁRIOS
		boolean accountNonExpired = true;
		boolean accountNonLocked = true;
		boolean credentialsNonExpired = true;
		boolean enabled = true;
		String permissions[] = {};
		String authorities [] = {};
		String roles [] = {};
		
		try {
			//GERA O JSON 
			String username = getUsername().getText();
			String fullName = getFullName().getText();
			String email = getEmail().getText();
			String password1 = getSenha1().getText();
			String password2 = getSenha2().getText();
			
			if(username.isEmpty()) {
				effects.campoObrigatorio(getUsername());
				getInfo().setText("*Campos Obrigatórios!");
			}
			if(fullName.isEmpty()) {
				effects.campoObrigatorio(getFullName());
				getInfo().setText("*Campos Obrigatórios!");
			}
			if(email.isEmpty()) {
				effects.campoObrigatorio(getEmail());
				getInfo().setText("*Campos Obrigatórios!");
			}
			if(password1.isEmpty()) {
				effects.campoObrigatorio(getSenha1());
				getInfo().setText("*Campos Obrigatórios!");
			}	
			if(password2.isEmpty()) {
				effects.campoObrigatorio(getSenha2());
				getInfo().setText("*Campos Obrigatórios!");
			}
			else {
				effects.campoObrigatorioRemove(getFullName());
				effects.campoObrigatorioRemove(getUsername());
				effects.campoObrigatorioRemove(getEmail());
				effects.campoObrigatorioRemove(getSenha1());
				effects.campoObrigatorioRemove(getSenha2());
				getInfo().setText(null);
				
				JSONObject json = new JSONObject();
			    	json.put("userName", username);
				    json.put("fullName", fullName);
				    json.put("password", password2);
				    json.put("email", email);
				    json.put("accountNonExpired",accountNonExpired);
				    json.put("accountNonLocked",accountNonLocked);
				    json.put("credentialsNonExpired",credentialsNonExpired);
				    json.put("enabled",enabled);
				    json.put("permissions", permissions);
				    json.put("authorities", authorities);
				    json.put("roles", roles);
			    
				    //REQUIÇÃO
				    String urlLogin = "http://localhost:8080/users";
				    HttpClient client = HttpClient.newHttpClient();
				    HttpRequest request = HttpRequest.newBuilder()
				    		.uri(URI.create(urlLogin))
				    		.header("Authorization", "Bearer "+token)
				    		.header("Content-Type", "application/json")
				    		.POST(HttpRequest.BodyPublishers.ofString(json.toString()))
				    		.build();
				    HttpResponse<String> response= client.send(request,HttpResponse.BodyHandlers.ofString());
				    String message = response.body().substring(83, 152);
				    int status = response.statusCode();
			    
				    if(status == 200) {
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			            alert.setHeaderText(null);
			            alert.setContentText("Usuário "+ getUsername().getText() + " cadastrado com sucesso! ");
			            alert.showAndWait(); 
				    }
				    else {Alert alert = new Alert(Alert.AlertType.ERROR);
			            alert.setHeaderText(null);
			            alert.setContentText("Usuário "+ getUsername().getText() + " não cadastrado! Motivo: " +message);
			            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			            alert.showAndWait(); 
				    }
				    getUsername().setText(null);
				    getFullName().setText(null);
				    getEmail().setText(null);
				    getSenha1().setText(null);
				    getSenha2().setText(null);
			}		
		}
		
		catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Erro. " + e.getMessage());
            alert.showAndWait();
		}
	}
	
	
	@FXML
	private void cancelar(ActionEvent action) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow(); 
	    stage.close(); 
	}
	
	//CHAMA TELA DE EDITAR USUÁRIO
	public void editar(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("EditarUsuario.fxml"));
		Scene scene = new Scene(painel, 600, 450);
		stage.setTitle("Editar Usuário");
		stage.setScene(scene);
        stage.show();  
	}
	

	
}

    
