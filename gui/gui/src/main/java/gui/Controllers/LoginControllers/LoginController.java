package gui.Controllers.LoginControllers;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.LoginDto;
import gui.Dtos.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LoginController {

	private LoginDto login = new LoginDto();
 
	@FXML
	private Pane painelLogin;
	
	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private TextField passwordText;

	@FXML
	private ImageView showPassword;

	@FXML
	private ImageView hiddenPassword;

	@FXML
	private Label info;

	@FXML
	private Button btnEntrar;

	public LoginDto getLogin() {
		return login;
	}

	@SuppressWarnings("exports")
	public TextField getUserName() {
		return userName;
	}

	@SuppressWarnings("exports")
	public PasswordField getPassword() {
		return password;
	}
	
	@SuppressWarnings("exports")
	public Pane getPainelLogin() {
		return painelLogin;
	}

	@SuppressWarnings("exports")
	public void setPainelLogin(Pane painelLogin) {
		this.painelLogin = painelLogin;
	}

	@SuppressWarnings("exports")
	public TextField getPasswordText() {
		return passwordText;
	}

	@SuppressWarnings("exports")
	public ImageView getShowPassword() {
		return showPassword;
	}

	@SuppressWarnings("exports")
	public ImageView getHiddenPassword() {
		return hiddenPassword;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public Button getBtnEntrar() {
		return btnEntrar;
	}

	public void setLogin(LoginDto login) {
		this.login = login;
	}

	@SuppressWarnings("exports")
	public void setUserName(TextField userName) {
		this.userName = userName;
	}

	@SuppressWarnings("exports")
	public void setPassword(PasswordField password) {
		this.password = password;
	}

	@SuppressWarnings("exports")
	public void setPasswordText(TextField passwordText) {
		this.passwordText = passwordText;
	}

	@SuppressWarnings("exports")
	public void setShowPassword(ImageView showPassword) {
		this.showPassword = showPassword;
	}

	@SuppressWarnings("exports")
	public void setHiddenPassword(ImageView hiddenPassword) {
		this.hiddenPassword = hiddenPassword;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	@SuppressWarnings("exports")
	public void setBtnEntrar(Button btnEntrar) {
		this.btnEntrar = btnEntrar;
	}
	
	@FXML
	private void initialize() throws IOException {
		getBtnEntrar().setStyle("-fx-background-color:  #0C70F2;");
		getPasswordText().setVisible(false);
		getHiddenPassword().setVisible(false);
		getBtnEntrar().setOnAction((event) -> {
			PrincipalController principalController = new PrincipalController();
			try {
				fazerLogin();
				if (login.isAuthenticated()) { 
					principalController.getLogin(fazerLogin());
					App.setRoot("PrincipalViews/Root");
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	

	@SuppressWarnings("exports")
	@FXML
	public void login(ActionEvent action) {
		fazerLogin();
	}
	
	

	private Boolean checarParametrosNull() {
		Style efeito = new Style();
		if (getPassword().isVisible()) {
			if (getUserName().getText().isEmpty() || getPassword().getText().isEmpty()) {
				efeito.campoObrigatorio(getUserName());
				efeito.campoObrigatorio(getPassword());
				getInfo().setText("*Campos Obrigatórios");
				return false;
			}
		}
		if (getPasswordText().isVisible()) {
			if (getUserName().getText().isEmpty() || getPasswordText().getText().isEmpty()) {
				efeito.campoObrigatorio(getUserName());
				efeito.campoObrigatorio(getPasswordText());
				getInfo().setText("*Campos Obrigatórios");
				return false;
			}
		}

		return true;

	}

	/**
	 * Método responsável por verificar os campos e fazer a requisição de login,
	 * retornando autenticação.
	 */
	@FXML
	private LoginDto fazerLogin() {
		Style efeito = new Style();
		
		if (checarParametrosNull()) {
			try { 
				String username = getUserName().getText();
				String password = null;
				if (getPassword().isVisible()) {
					password = getPassword().getText();
				}
				if (getPasswordText().isVisible()) {
					password = getPasswordText().getText();
				}

				// FAZER LOGIN
				String urlLogin = "http://localhost:8080/auth/signin";
				HttpClient client = HttpClient.newHttpClient();
				String body = "{\r\n" + "    \"username\" : \"" + username + "\",\r\n" + "    \"password\" : \""
						+ password + "\"\r\n" + "}";
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlLogin))
						.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)) 
						.build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				JSONObject responseJson = new JSONObject(response.body());

				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String create = responseJson.getString("create").substring(0, 19).replaceAll("T", " ");
				String expirtation = responseJson.getString("expiration").substring(0, 19).replaceAll("T", " ");

				// CRIA UM OBJETO LOGIN
				login.setUserName(responseJson.getString("userName"));
				login.setAuthenticated(responseJson.getBoolean("authenticated"));
				login.setCreate(LocalDateTime.parse(create, format));
				login.setExpiration(LocalDateTime.parse(expirtation, format));
				login.setAccessToken(responseJson.getString("accessToken"));
				login.setRefleshToken(responseJson.getString("refleshToken"));

				if (login.isAuthenticated()) {
					PrincipalController principalController = new PrincipalController();
					principalController.getLogin(login);

				}
				return login;

			} catch (Exception e) {
				efeito.campoObrigatorio(getUserName());
				efeito.campoObrigatorio(getPassword());
				efeito.campoObrigatorio(getPasswordText());
				getInfo().setText("*Usuário ou Senha inválidos. Verifique os campos e tente novamente!");
			}
		}
		return null;
	}
 
	/**
	 * Torna visível os caracteres do compo senha.
	 * 
	 * @param event
	 */
	@SuppressWarnings("exports")
	@FXML
	public void show(MouseEvent event) {
		getPasswordText().setVisible(true);
		getHiddenPassword().setVisible(true);
		getPasswordText().setText(getPassword().getText());
		getPassword().setVisible(false);
		getShowPassword().setVisible(false);
	}

	/**
	 * Oculta os caracteres do compo senha.
	 *  
	 * @param event
	 */
	@SuppressWarnings("exports")
	@FXML
	public void hidden(MouseEvent event) {
		getPassword().setVisible(true);
		getShowPassword().setVisible(true);
		getPassword().setText(getPasswordText().getText());
		getPasswordText().setVisible(false);
		getHiddenPassword().setVisible(false);
	}

}