package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.Services.Style;
import gui.Services.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class UsuariosController extends Application {

	PrincipalController principalController;

	static EditarUsuarioController editar = null;

	static User user;

	Style effects = new Style();

	private static String token = PrincipalController.getAccessToken();

	static ObservableList<User> observableList;

	@SuppressWarnings("rawtypes")
	static TableView tabelaUsuarios = null;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnUsuario;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;

	@FXML
	private Button btnInativar;

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
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Label info;

	public PrincipalController getPrincipalController() {
		return principalController;
	}

	public static EditarUsuarioController getEditar() {
		return editar;
	}

	public static User getUser() {
		return user;
	}

	public Style getEffects() {
		return effects;
	}

	public static String getToken() {
		return token;
	}

	public static ObservableList<User> getObservableList() {
		return observableList;
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public static TableView getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	@SuppressWarnings("exports")
	public BorderPane getTelaBase() {
		return telaBase;
	}

	@SuppressWarnings("exports")
	public Button getBtnUsuario() {
		return btnUsuario;
	}

	@SuppressWarnings("exports")
	public Button getBtnNovo() {
		return btnNovo;
	}

	@SuppressWarnings("exports")
	public Button getBtnEditar() {
		return btnEditar;
	}

	@SuppressWarnings("exports")
	public Button getBtnApagar() {
		return btnApagar;
	}

	@SuppressWarnings("exports")
	public Button getBtnInativar() {
		return btnInativar;
	}

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
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	@SuppressWarnings("exports")
	public Button getBtnSalvar() {
		return btnSalvar;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	public void setPrincipalController(PrincipalController principalController) {
		this.principalController = principalController;
	}

	public static void setEditar(EditarUsuarioController editar) {
		UsuariosController.editar = editar;
	}

	public static void setUser(User user) {
		UsuariosController.user = user;
	}

	public void setEffects(Style effects) {
		this.effects = effects;
	}

	public static void setToken(String token) {
		UsuariosController.token = token;
	}

	public static void setObservableList(ObservableList<User> observableList) {
		UsuariosController.observableList = observableList;
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public static void setTabelaUsuarios(TableView tabelaUsuarios) {
		UsuariosController.tabelaUsuarios = tabelaUsuarios;
	}

	@SuppressWarnings("exports")
	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	@SuppressWarnings("exports")
	public void setBtnUsuario(Button btnUsuario) {
		this.btnUsuario = btnUsuario;
	}

	@SuppressWarnings("exports")
	public void setBtnNovo(Button btnNovo) {
		this.btnNovo = btnNovo;
	}

	@SuppressWarnings("exports")
	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	@SuppressWarnings("exports")
	public void setBtnApagar(Button btnApagar) {
		this.btnApagar = btnApagar;
	}

	@SuppressWarnings("exports")
	public void setBtnInativar(Button btnInativar) {
		this.btnInativar = btnInativar;
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
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@SuppressWarnings("exports")
	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());
		efeitos.hover(getBtnInativar());
	}

	@SuppressWarnings({ "unchecked" })
	public TableView<User> construirTabela() throws Exception {
		setTabelaUsuarios(new TableView<User>());

		TableColumn<User, Integer> colunaID = new TableColumn<User, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		colunaID.setPrefWidth(70);

		TableColumn<User, String> colunaName = new TableColumn<User, String>("Nome");
		colunaName.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
		colunaName.setPrefWidth(400);

		TableColumn<User, String> colunaEmail = new TableColumn<User, String>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		colunaEmail.setPrefWidth(400);

		TableColumn<User, String> colunaUsername = new TableColumn<User, String>("Nome de Usuário");
		colunaUsername.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		colunaUsername.setPrefWidth(310);

		TableColumn<User, Boolean> colunaStatus = new TableColumn<User, Boolean>("Status");
		colunaStatus.setCellValueFactory(new PropertyValueFactory<User, Boolean>("enabled"));
		colunaStatus.setPrefWidth(150);

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaUsuarios().getColumns().addAll(colunaID, colunaName, colunaEmail, colunaUsername, colunaStatus);

		if (getTabelaUsuarios() == null) {
			getTabelaUsuarios().setPlaceholder(new Label("Nenhum usuário cadastrado."));

		}
		return getTabelaUsuarios();

	}

	@SuppressWarnings("unchecked")
	public static void popularTabela() throws Exception {
		List<User> users = getAllUsers();
		setObservableList(FXCollections.observableArrayList(users));
		getTabelaUsuarios().setItems(observableList);
	}

	public static List<User> getAllUsers() throws Exception {
		try {
			// BUSCA TODOS USUARIOS
			String url = "http://localhost:8080/users";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray usuarios = new JSONArray(response.body());

			User user;
			List<User> users = new ArrayList<>();

			// LOOP CONVERTE JSON EM USER
			for (int i = 0; i < usuarios.length(); i++) {
				JSONObject jsonObj = usuarios.getJSONObject(i);
				user = new User();
				user.setCredentialsNonExpired(jsonObj.getBoolean("credentialsNonExpired"));
				user.setFullName(jsonObj.getString("fullName"));
				user.setUserName(jsonObj.getString("userName"));
				if (jsonObj.getBoolean("enabled") == true) {
					user.setEnabled("Ativo");
				} else {
					user.setEnabled("Inativo");
				}
				user.setPassword(jsonObj.getString("password"));
				user.setAccountNonExpired(jsonObj.getBoolean("accountNonExpired"));
				user.setId(jsonObj.getInt("id"));
				user.setEmail(jsonObj.getString("email"));
				user.setAccountNonLocked(jsonObj.getBoolean("accountNonLocked"));
				users.add(user);
			}

			return users;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
	}

	// FUNÇÃO BOTÃO SALVAR
	@SuppressWarnings("exports")
	@FXML
	public void salvar(ActionEvent action) throws Exception {
		try {
			// VERIFICA CAMPOS VAZIOS
			if (getFullName().getText().isEmpty() || getUsername().getText().isEmpty() || getEmail().getText().isEmpty()
					|| getPassword1().getText().isEmpty() || getPassword2().getText().isEmpty()) {
				getInfo().setText("*Campos Obrigatórios!");
				effects.campoObrigatorio(getFullName());
				effects.campoObrigatorio(getUsername());
				effects.campoObrigatorio(getEmail());
				effects.campoObrigatorio(getPassword1());
				effects.campoObrigatorio(getPassword2());
				return;
			}
			// VERFICA SE SENHAS DIGITADAS SÃO DIFERENTES
			if (!getPassword1().getText().equals(getPassword2().getText())) {
				effects.campoObrigatorio(getPassword1());
				effects.campoObrigatorio(getPassword2());
				getInfo().setText("*Senhas digitada não conferem!");
				return;

			} else {
				effects.campoObrigatorioRemove(getFullName());
				effects.campoObrigatorioRemove(getUsername());
				effects.campoObrigatorioRemove(getEmail());
				effects.campoObrigatorioRemove(getPassword1());
				effects.campoObrigatorioRemove(getPassword2());
				getInfo().setText(null);

				String fullName = getFullName().getText();
				String username = getUsername().getText();
				String email = getEmail().getText();
				String password2 = getPassword2().getText();

				// PADRÃO PARA NOVOS USUÁRIOS
				boolean accountNonExpired = true;
				boolean accountNonLocked = true;
				boolean credentialsNonExpired = true;
				boolean enabled = true;
				String permissions[] = {};
				String authorities[] = {};
				String roles[] = {};

				JSONObject json = new JSONObject();
				json.put("fullName", fullName);
				json.put("userName", username);
				json.put("password", password2);
				json.put("email", email);
				json.put("accountNonExpired", accountNonExpired);
				json.put("accountNonLocked", accountNonLocked);
				json.put("credentialsNonExpired", credentialsNonExpired);
				json.put("enabled", enabled);
				json.put("permissions", permissions);
				json.put("authorities", authorities);
				json.put("roles", roles);

				// REQUIÇÃO
				String urlLogin = "http://localhost:8080/users";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlLogin))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String message = response.body().substring(83, 152);
				int status = response.statusCode();

				if (status == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Usuário \"" + getUsername().getText() + "\" cadastrado com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) btnCancelar.getScene().getWindow();
					stage.close();

					// ATUALIZA A TABELA
					popularTabela();
				}

				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText(
							"Usuário \"" + getUsername().getText() + "\" não cadastrado! Motivo: " + message);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
				}

			}

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Erro. " + e.getMessage() + e.getCause());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();

		}

	}

	@FXML
	private void cancelar(ActionEvent action) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	// CHAMA TELA DE CADSTRAR USUÁRIO
	@SuppressWarnings("exports")
	public void novo(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("UsuarioViews/NovoUsuario.fxml"));
		Scene scene = new Scene(painel, 600, 450);
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
		stage.show();
	}

	// CHAMA TELA DE EDITAR USUÁRIO
	@SuppressWarnings({ "exports" })
	@FXML
	public void editar(ActionEvent action) throws Exception {
		editar = new EditarUsuarioController();
		// VERIFICA SE FOIS SELECIONADO UM USUARIO PARA EDITAR
		if (editar.isNull()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Usuário para editar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		else {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(getClass().getResource("UsuarioViews/EditarUsuario.fxml"));
			Scene scene = new Scene(painel, 600, 450);
			stage.setTitle("Editar Usuário");
			stage.setScene(scene);
			stage.show();

		}

	}

	@SuppressWarnings("exports")
	public void apagar(ActionEvent action) throws IOException {

		// VERIFICA E NAO DEIXA APAGAR TODOS USUÁRIOS
		if (PrincipalController.tabelaUsuarios.getItems().size() == 1) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setContentText("Não é permitido apagar todos os usuários!");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		// VERIFICA SE FOI SELECIONADO UM USUÁRIO PARA APAGAR
		if (PrincipalController.tabelaUsuarios.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Usuário para apagar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		} else {
			int id = (int) PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getId();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Deseja apagar o usuário \""
					+ PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getUserName() + "\"?");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			try {
				// REQUIÇÃO PARA DELETAR
				String urlDelete = "http://localhost:8080/users/" + id;
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().header("Authorization", "Bearer " + token)
						.header("Content-Type", "application/json").DELETE().uri(URI.create(urlDelete)).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				int status = response.statusCode();

				if (status == 204) {
					Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
					alert1.setHeaderText(null);
					alert1.setContentText("Usuário apagado com sucesso! ");
					alert1.showAndWait();
					UsuariosController.popularTabela();
				}

			} catch (Exception e) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setContentText("Erro. " + e.getMessage());
				alert2.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert2.showAndWait();
			}
		}
	}

	@SuppressWarnings("exports")
	public void inativar(ActionEvent action) throws IOException {

		// RECEBE O USUARIO SELECIONADO
		user = PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem();

		// VERIFICA SE USUÁRIO JÁ ESTA INATIVO
		if (user.getEnabled() == "Inativo") {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Usuário \"" + user.getFullName() + "\" já está inativo!");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;

		}

		// VERIFICA E NAO DEIXA INATIVAR TODOS USUÁRIOS
		if (PrincipalController.tabelaUsuarios.getItems().size() == 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Não é permitido inativar todos os usuários!");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		// VERIFICA SE FOI SELECIONADO UM USUÁRIO PARA APAGAR
		if (PrincipalController.tabelaUsuarios.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Usuário para inativar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		} else {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Deseja inativar o usuário \""
					+ PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem().getUserName() + "\"?");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();

			try {
				user = PrincipalController.tabelaUsuarios.getSelectionModel().getSelectedItem();
				String permissions[] = {};
				String authorities[] = {};
				String roles[] = {};
				boolean status = false;

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

				System.out.println(json);

				String urlUpdate = "http://localhost:8080/users";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlUpdate))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				int statusCode = response.statusCode();

				if (statusCode == 200) {
					Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
					alert1.setHeaderText(null);
					alert1.setContentText("Usuário \"" + user.getFullName() + "\" inativado com sucesso! ");
					alert1.showAndWait();
					popularTabela();
				}

			} catch (Exception e) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setContentText("Erro. " + e.getMessage());
				alert2.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert2.showAndWait();
			}
		}

	}

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UsuarioViews/Usuarios.fxml"));
		UsuariosController usuariosController = new UsuariosController();
		getTelaBase().setCenter(usuariosController.construirTabela());

	}

}
