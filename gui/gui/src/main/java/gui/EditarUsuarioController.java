package gui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Services.Style;
import gui.Services.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class EditarUsuarioController {

	Style effects = new Style();

	private String token = PrincipalController.getAccessToken();

	static User user = null;

	@FXML
	private TextField fullName;

	@FXML
	private TextField username;

	@FXML
	private TextField email;

	@FXML
	private TextField password1;

	@FXML
	private TextField password2;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label info;

	@SuppressWarnings("exports")
	public TextField getFullName() {
		return fullName;
	}

	@SuppressWarnings("exports")
	public TextField getUsername() {
		return username;
	}

	@SuppressWarnings("exports")
	public TextField getEmail() {
		return email;
	}

	@SuppressWarnings("exports")
	public TextField getPassword1() {
		return password1;
	}

	@SuppressWarnings("exports")
	public TextField getPassword2() {
		return password2;
	}

	@SuppressWarnings("exports")
	public void setFullName(TextField fullName) {
		this.fullName = fullName;
	}

	@SuppressWarnings("exports")
	public void setUsername(TextField username) {
		this.username = username;
	}

	@SuppressWarnings("exports")
	public void setEmail(TextField email) {
		this.email = email;
	}

	@SuppressWarnings("exports")
	public void setPassword1(TextField password1) {
		this.password1 = password1;
	}

	@SuppressWarnings("exports")
	public void setPassword2(TextField password2) {
		this.password2 = password2;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	public void initialize() throws Exception {
		getFullName().setText(PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getFullName());
		getUsername().setText(PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getUserName());
		getEmail().setText(PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getEmail());
		user = PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem();

	}

	public boolean isNull() {
		return PrincipalController.tabelaUsuarios.getSelectionModel().isEmpty();
	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) {
		/*
		 * if (PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().
		 * getUserName().equals("admin")) { Alert alert = new
		 * Alert(Alert.AlertType.ERROR); alert.setHeaderText(null);
		 * alert.setContentText("Usuário ADMIN não pode ser alterado!");
		 * alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		 * alert.showAndWait(); return;
		 */

		// VERIFICA SE CAMPOS NOME, USUARIO E SENHA SÃO VAZIOS
		if (fullName.getText().isEmpty() || username.getText().isEmpty() || email.getText().isEmpty()) {
			getInfo().setText("*Campos Obrigatórios!");
			effects.campoObrigatorio(getFullName());
			effects.campoObrigatorio(getUsername());
			effects.campoObrigatorio(getEmail());
			return;
		}
		// VERIFICA SE FOI DIGITADO NOVA SENHA
		if (!password1.getText().isEmpty() && !password2.getText().isEmpty()) {
			setPassword1(password1);
			setPassword2(password2);

			// VERIFICA SE NOVA SENHA SÃO IGUAIS
			if (password1.getText().equals(password2.getText())) {
				user.setFullName(fullName.getText());
				user.setUserName(username.getText());
				user.setEmail(email.getText());
				user.setPassword(password2.getText());
				effects.campoObrigatorioRemove(getPassword1());
				effects.campoObrigatorioRemove(getPassword2());
				fazerRequisicao(user);

			} else {
				effects.campoObrigatorio(getPassword1());
				effects.campoObrigatorio(getPassword2());
				getInfo().setText("*Senhas digitada não conferem.");
			}
		} else {
			effects.campoObrigatorioRemove(getFullName());
			effects.campoObrigatorioRemove(getUsername());
			effects.campoObrigatorioRemove(getEmail());
			user.setFullName(fullName.getText());
			user.setUserName(username.getText());
			user.setEmail(email.getText());
			fazerRequisicao(user);
		}

	}

	private void fazerRequisicao(User user) {
		String permissions[] = {};
		String authorities[] = {};
		String roles[] = {};
		boolean status = true;

		try {
			JSONObject json = new JSONObject();
			json.put("id", user.getId());
			json.put("fullName", user.getFullName());
			json.put("userName", user.getUserName());
			json.put("email", user.getEmail());
			json.put("password", user.getPassword());
			json.put("accountNonExpired", user.getAccountNonExpired());
			json.put("accountNonLocked", user.getAccountNonLocked());
			json.put("credentialsNonExpired", user.getCredentialsNonExpired());
			json.put("enabled", status);
			json.put("permissions", permissions);
			json.put("authorities", authorities);
			json.put("roles", roles);

			// REQUIÇÃO PARA ATUALIZAR
			String urlUpdate = "http://localhost:8080/users";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlUpdate))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String message = response.body().substring(83, 152);
			int statusCode = response.statusCode();

			if (statusCode == 200) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setContentText("Usuário \"" + getUsername().getText() + "\" atualizado com sucesso! ");
				alert.showAndWait();
				Stage stage = (Stage) btnCancelar.getScene().getWindow();
				stage.close();
				UsuariosController.popularTabela();
			}

			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Usuário \"" + getUsername().getText() + "\" não alterado! Motivo: " + message);
				alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert.showAndWait();
			}

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Erro. " + e.getMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		}
	}

	@FXML
	private void cancelar(ActionEvent action) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

}
