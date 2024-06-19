package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.Models.Cliente;
import gui.Models.Style;
import gui.Models.Uf_Enum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ClientesController {

	private static String token = PrincipalController.getAccessToken();

	static TableView<Cliente> tabelaClientes = null;

	static ObservableList<Cliente> observableList;

	static NovoClienteController novoClient = null;

	static EditarClienteController editarClient = null;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;

	@FXML
	private Button btnInativar;

	public static EditarClienteController getEditarClient() {
		return editarClient;
	}

	public static void setEditarClient(EditarClienteController editarClient) {
		ClientesController.editarClient = editarClient;
	}

	public static String getToken() {
		return token;
	}

	public static TableView<Cliente> getTabelaClientes() {
		return tabelaClientes;
	}

	public static ObservableList<Cliente> getObservableList() {
		return observableList;
	}

	public static NovoClienteController getNovoClient() {
		return novoClient;
	}

	@SuppressWarnings("exports")
	public BorderPane getTelaBase() {
		return telaBase;
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

	public static void setToken(String token) {
		ClientesController.token = token;
	}

	public static void setTabelaClientes(TableView<Cliente> tabelaClientes) {
		ClientesController.tabelaClientes = tabelaClientes;
	}

	public static void setObservableList(ObservableList<Cliente> observableList) {
		ClientesController.observableList = observableList;
	}

	public static void setNovoClient(NovoClienteController novoClient) {
		ClientesController.novoClient = novoClient;
	}

	@SuppressWarnings("exports")
	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
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

	public void initialize() {

	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());
		efeitos.hover(getBtnInativar());
	}

	@SuppressWarnings("unchecked")
	public TableView<Cliente> construirTabela() throws Exception {
		setTabelaClientes(new TableView<Cliente>()); 

		TableColumn<Cliente, Integer> colunaID = new TableColumn<Cliente, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));

		TableColumn<Cliente, String> colunaTipo = new TableColumn<Cliente, String>("Pessoa");
		colunaTipo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipo"));

		TableColumn<Cliente, String> colunaName = new TableColumn<Cliente, String>("Nome");
		colunaName.setCellValueFactory(new PropertyValueFactory<Cliente, String>("name"));
		colunaName.setMinWidth(300);

		TableColumn<Cliente, String> colunaEmail = new TableColumn<Cliente, String>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
		colunaEmail.setMinWidth(300);

		TableColumn<Cliente, String> colunaPhone = new TableColumn<Cliente, String>("Telefone");
		colunaPhone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("phone"));
		colunaPhone.setMinWidth(120);

		TableColumn<Cliente, String> colunaCpf_Cnpj = new TableColumn<Cliente, String>("Cpf/Cnpj");
		colunaCpf_Cnpj.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf_cnpj"));

		TableColumn<Cliente, String> colunaRg_Ie = new TableColumn<Cliente, String>("Rg/Ie");
		colunaRg_Ie.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rg_ie"));

		TableColumn<Cliente, LocalDate> colunaDateNasc_cons = new TableColumn<Cliente, LocalDate>("Data Nasc/Const.");
		colunaDateNasc_cons.setCellValueFactory(new PropertyValueFactory<Cliente, LocalDate>("dateNasc_const"));

		TableColumn<Cliente, LocalDate> colunaDateExp = new TableColumn<Cliente, LocalDate>("Data de Expedição");
		colunaDateExp.setCellValueFactory(new PropertyValueFactory<Cliente, LocalDate>("dateExp"));

		TableColumn<Cliente, String> colunaEndereco = new TableColumn<Cliente, String>("Endereço");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Cliente, String>("address"));

		TableColumn<Cliente, String> colunaNum = new TableColumn<Cliente, String>("Número");
		colunaNum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("addressNumber"));

		TableColumn<Cliente, String> colunaComplement = new TableColumn<Cliente, String>("Complemento");
		colunaComplement.setCellValueFactory(new PropertyValueFactory<Cliente, String>("addressComplement"));

		TableColumn<Cliente, String> colunaCity = new TableColumn<Cliente, String>("Cidade");
		colunaCity.setCellValueFactory(new PropertyValueFactory<Cliente, String>("city"));

		TableColumn<Cliente, String> colunaUf = new TableColumn<Cliente, String>("UF");
		colunaUf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("uf"));

		TableColumn<Cliente, String> colunaCep = new TableColumn<Cliente, String>("Cep");
		colunaCep.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cep"));

		TableColumn<Cliente, String> colunaObs = new TableColumn<Cliente, String>("Obs");
		colunaObs.setCellValueFactory(new PropertyValueFactory<Cliente, String>("obs"));

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaClientes().getColumns().addAll(colunaID, colunaTipo, colunaName, colunaEmail, colunaPhone,
				colunaCpf_Cnpj, colunaRg_Ie, colunaDateNasc_cons, colunaDateExp, colunaEndereco, colunaNum,
				colunaComplement, colunaCity, colunaUf, colunaCep, colunaObs);

		if (getTabelaClientes() == null) {
			getTabelaClientes().setPlaceholder(new Label("Nenhum Cliente Cadastrado."));
		}
		return getTabelaClientes();
	}

	public static void popularTabela() throws Exception {
		List<Cliente> clientes = getAllClients();
		setObservableList(FXCollections.observableArrayList(clientes));
		getTabelaClientes().setItems(observableList);
	} 

	private static List<Cliente> getAllClients() throws Exception {
		try {
			// BUSCA TODOS CLIENTES
			String url = "http://localhost:8080/clients";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			Cliente cliente;
			List<Cliente> clientes = new ArrayList<>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dataNascCont = jsonObj.getString("dateNasc_const").substring(0, 19).replaceAll("T", " ");
				String dateExp = jsonObj.getString("dateExp").substring(0, 19).replaceAll("T", " ");

				
				
				
				cliente = new Cliente();
				cliente.setId(jsonObj.getLong("id"));
				cliente.setTipo(jsonObj.getString("tipo").length() > 11 ? "Jurídica" : "Física");
				cliente.setName(jsonObj.getString("name").isEmpty() ? "" : jsonObj.getString("name"));
				cliente.setPhone(jsonObj.getString("phone").isEmpty() ? "" : jsonObj.getString("phone") );
				cliente.setEmail(jsonObj.getString("email").isEmpty() ? "" : jsonObj.getString("email"));
				cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj").isEmpty() ? "" :jsonObj.getString("cpf_cnpj"));
				cliente.setRg_ie(jsonObj.getString("rg_ie").isEmpty() ? "" : jsonObj.getString("rg_ie"));
				cliente.setDateNasc_const(LocalDate.parse(dataNascCont, format).equals(null) ? LocalDate.of(1900, 1, 1) : LocalDate.parse(dataNascCont, format));
				cliente.setDateExp(LocalDate.parse(dateExp, format).equals(null) ? LocalDate.of(1900, 1, 1) : LocalDate.parse(dateExp, format));
				cliente.setAddress(jsonObj.getString("address").isEmpty() ? "" : jsonObj.getString("address"));
				cliente.setAddressNumber(jsonObj.getString("addressNumber").isEmpty() ? "" : jsonObj.getString("addressNumber")); 
				cliente.setAddressComplement(jsonObj.getString("addressComplement").isEmpty() ? "" : jsonObj.getString("addressComplement"));
				cliente.setCity(jsonObj.getString("city").isEmpty() ? "" : jsonObj.getString("city"));
				cliente.setUf(Uf_Enum.valueOf(jsonObj.getString("uf")));
				cliente.setCep(jsonObj.getString("cep").isEmpty() ? "" : jsonObj.getString("cep"));
				cliente.setObs(jsonObj.getString("obs").isEmpty() ? "" : jsonObj.getString("obs"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
	}

	// CHAMA TELA DE CADSTRAR USUÁRIO
	@SuppressWarnings("exports")

	public void novo(ActionEvent action) throws IOException {
		setNovoClient(new NovoClienteController());
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("ClienteViews/NovoCliente.fxml"));
		Scene scene = new Scene(painel, 800, 610);
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
		stage.show();

	}

	@SuppressWarnings("exports")
	public void editar(ActionEvent action) throws IOException {
		setEditarClient(new EditarClienteController());
		// VERIFICA SE FOIS SELECIONADO UM USUARIO PARA EDITAR
		if (PrincipalController.getTabelaClients().getSelectionModel().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Cliente para editar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		else {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(getClass().getResource("ClienteViews/EditarCliente.fxml"));
			Scene scene = new Scene(painel, 800, 610);
			stage.setTitle("Editar Cliente");
			stage.setScene(scene);
			stage.show();

		}

	}

	@SuppressWarnings("exports")
	public void apagar(ActionEvent action) throws IOException {

		// VERIFICA SE FOI SELECIONADO UM CLIENTE PARA APAGAR
		if (ClientesController.getTabelaClientes().getSelectionModel().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Cliente!.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		} else {
			Long id = ClientesController.getTabelaClientes().getSelectionModel().getSelectedItem().getId();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Deseja apagar o cliente \""
					+ ClientesController.getTabelaClientes().getSelectionModel().getSelectedItem().getName() + "\"?");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.CANCEL) {
				alert.close();
				return;
			}
			try {
				// REQUIÇÃO PARA DELETAR
				String urlDelete = "http://localhost:8080/clients/" + id;
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().header("Authorization", "Bearer " + token)
						.header("Content-Type", "application/json").DELETE().uri(URI.create(urlDelete)).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				int status = response.statusCode();

				if (status == 204) {
					Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
					alert1.setHeaderText(null);
					alert1.setContentText("Cliente apagado com sucesso! ");
					alert1.showAndWait();
					ClientesController.popularTabela();
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

	}

}
