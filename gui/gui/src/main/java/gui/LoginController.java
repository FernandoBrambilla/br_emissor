package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Models.Login;
import gui.Models.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginController extends AnchorPane {

	private Login login = new Login();

	@FXML
	BorderPane telaBase;

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

	public Login getLogin() {
		return login;
	}

	@SuppressWarnings("exports")
	public BorderPane getTelaBase() {
		return telaBase;
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

	public void setLogin(Login login) {
		this.login = login;
	}

	@SuppressWarnings("exports")
	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
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
	private void login(ActionEvent action) {
		fazerLogin();
	}

	/**
	 * Método responsável por verificar os campos e fazer a requisição de login,
	 * retornando autenticação.
	 */
	@FXML
	private Login fazerLogin() {
		Style efeito = new Style();
		try {
			if (getUserName().getText().isEmpty() || getPassword().getText().isEmpty()) {

				efeito.campoObrigatorio(getUserName());
				efeito.campoObrigatorio(getPassword());
				getInfo().setText("*Campos Obrigatórios");

			} else {
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

				// CRIA UM OBJETO LOGIN
				login.setUserName(responseJson.getString("userName"));
				login.setAuthenticated(responseJson.getBoolean("authenticated"));
				login.setCreate(responseJson.getString("create"));
				login.setExpiration(responseJson.getString("expiration"));
				login.setAccessToken(responseJson.getString("accessToken"));
				login.setRefleshToken(responseJson.getString("refleshToken"));

				if (login.isAuthenticated()) {
					PrincipalController principalController = new PrincipalController();
					principalController.getLogin(login);

				}
				return login;
			}
		} catch (Exception e) {
			efeito.campoObrigatorio(getUserName());
			efeito.campoObrigatorio(getPassword());

			getInfo().setText("*Usuário ou Senha inválidos. Verifique os campos e tente novamente");
		}
		return null;
	}

	@FXML
	private void initialize() throws IOException {
		getPasswordText().setVisible(false);
		getHiddenPassword().setVisible(false);

		getBtnEntrar().setOnAction((event) -> {

			PrincipalController principalController = new PrincipalController();
			try {
				fazerLogin();

				if (login.isAuthenticated()) {
					principalController.getLogin(fazerLogin());
					Parent telaPrincipal = FXMLLoader
							.load(PrincipalController.class.getResource("PrincipalViews/Principal.fxml"));
					this.telaBase.setCenter(telaPrincipal);
				} else {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
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