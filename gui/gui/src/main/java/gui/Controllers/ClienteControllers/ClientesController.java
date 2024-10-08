package gui.Controllers.ClienteControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.ClienteDto;
import gui.Dtos.Style;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientesController {

	private static String token = PrincipalController.getAccessToken();

	private static String url = PrincipalController.getUrl();

	static TableView<ClienteDto> tabelaClientes;

	static ObservableList<ClienteDto> observableList;

	static NovoClienteController novoClient;

	static EditarClienteController editarClient;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;

	public static EditarClienteController getEditarClient() {
		return editarClient;
	}

	public static void setEditarClient(EditarClienteController editarClient) {
		ClientesController.editarClient = editarClient;
	}

	public static String getToken() {
		return token;
	}

	public static TableView<ClienteDto> getTabelaClientes() {
		return tabelaClientes;
	}

	public static ObservableList<ClienteDto> getObservableList() {
		return observableList;
	}

	public static NovoClienteController getNovoClient() {
		return novoClient;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public Button getBtnNovo() {
		return btnNovo;
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public Button getBtnApagar() {
		return btnApagar;
	}

	public static void setToken(String token) {
		ClientesController.token = token;
	}

	public static void setTabelaClientes(TableView<ClienteDto> tabelaClientes) {
		ClientesController.tabelaClientes = tabelaClientes;
	}

	public static void setObservableList(ObservableList<ClienteDto> observableList) {
		ClientesController.observableList = observableList;
	}

	public static void setNovoClient(NovoClienteController novoClient) {
		ClientesController.novoClient = novoClient;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void setBtnNovo(Button btnNovo) {
		this.btnNovo = btnNovo;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void setBtnApagar(Button btnApagar) {
		this.btnApagar = btnApagar;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ClientesController.url = url;
	}

	public void initialize() {

	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());
	}

	@SuppressWarnings("unchecked")
	public TableView<ClienteDto> construirTabela() throws Exception {
		setTabelaClientes(new TableView<ClienteDto>());

		TableColumn<ClienteDto, Integer> colunaID = new TableColumn<ClienteDto, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<ClienteDto, Integer>("id"));

		TableColumn<ClienteDto, String> colunaName = new TableColumn<ClienteDto, String>("Nome");
		colunaName.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("name"));
		colunaName.setMinWidth(300);

		TableColumn<ClienteDto, String> colunaEmail = new TableColumn<ClienteDto, String>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("email"));
		colunaEmail.setMinWidth(300);

		TableColumn<ClienteDto, String> colunaPhone = new TableColumn<ClienteDto, String>("Telefone");
		colunaPhone.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("phone"));
		colunaPhone.setMinWidth(120);

		TableColumn<ClienteDto, String> colunaCpf_Cnpj = new TableColumn<ClienteDto, String>("Cpf/Cnpj");
		colunaCpf_Cnpj.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("cpf_cnpj"));

		TableColumn<ClienteDto, String> colunaRg_Ie = new TableColumn<ClienteDto, String>("Rg/Ie");
		colunaRg_Ie.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("rg_ie"));

		TableColumn<ClienteDto, LocalDate> colunaDateNasc_cons = new TableColumn<ClienteDto, LocalDate>(
				"Data Nasc/Const.");
		colunaDateNasc_cons.setCellValueFactory(new PropertyValueFactory<ClienteDto, LocalDate>("dateNasc_const"));

		TableColumn<ClienteDto, LocalDate> colunaDateExp = new TableColumn<ClienteDto, LocalDate>("Data de Expedição");
		colunaDateExp.setCellValueFactory(new PropertyValueFactory<ClienteDto, LocalDate>("dateExp"));

		TableColumn<ClienteDto, String> colunaEndereco = new TableColumn<ClienteDto, String>("Endereço");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("address"));

		TableColumn<ClienteDto, String> colunaNum = new TableColumn<ClienteDto, String>("Número");
		colunaNum.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("addressNumber"));

		TableColumn<ClienteDto, String> colunaComplement = new TableColumn<ClienteDto, String>("Complemento");
		colunaComplement.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("addressComplement"));

		TableColumn<ClienteDto, String> colunaBairro = new TableColumn<ClienteDto, String>("Bairro");
		colunaBairro.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("bairro"));

		TableColumn<ClienteDto, String> colunaCity = new TableColumn<ClienteDto, String>("Cidade");
		colunaCity.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("city"));

		TableColumn<ClienteDto, String> colunaUf = new TableColumn<ClienteDto, String>("UF");
		colunaUf.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("uf"));

		TableColumn<ClienteDto, String> colunaCep = new TableColumn<ClienteDto, String>("Cep");
		colunaCep.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("cep"));

		TableColumn<ClienteDto, String> colunaTipo = new TableColumn<ClienteDto, String>("Pessoa");
		colunaTipo.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("tipo"));

		TableColumn<ClienteDto, String> colunaObs = new TableColumn<ClienteDto, String>("Obs");
		colunaObs.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("obs"));

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaClientes().getColumns().addAll(colunaID, colunaName, colunaEmail, colunaPhone, colunaCpf_Cnpj,
				colunaRg_Ie, colunaEndereco, colunaNum, colunaComplement, colunaCity, colunaUf, colunaBairro, colunaCep,
				colunaDateNasc_cons, colunaDateExp, colunaTipo, colunaObs);

		if (getTabelaClientes() == null) {
			getTabelaClientes().setPlaceholder(new Label("Nenhum Cliente Cadastrado."));
		}
		return getTabelaClientes();
	}

	public static void popularTabela() throws Exception {
		List<ClienteDto> clientes = getAllClients();
		setObservableList(FXCollections.observableArrayList(clientes));
		getTabelaClientes().setItems(observableList);
	}

	public static List<ClienteDto> getAllClients() throws Exception {
		try {
			// BUSCA TODOS CLIENTES
			String url = getUrl() + "clients";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			ClienteDto cliente;
			List<ClienteDto> clientes = new ArrayList<>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dateNasc_const = jsonObj.getString("dateNasc_const");
				String dateExp = jsonObj.getString("dateExp");

				cliente = new ClienteDto();
				cliente.setId(jsonObj.getLong("id"));
				cliente.setTipo(jsonObj.getString("tipo"));
				cliente.setName(jsonObj.getString("name"));
				cliente.setPhone(jsonObj.getString("phone"));
				cliente.setEmail(jsonObj.getString("email"));
				cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj"));
				cliente.setRg_ie(jsonObj.getString("rg_ie"));
				cliente.setDateNasc_const(LocalDate.parse(dateNasc_const, format));
				cliente.setDateExp(LocalDate.parse(dateExp, format));
				cliente.setAddress(jsonObj.getString("address"));
				cliente.setAddressNumber(jsonObj.getString("addressNumber"));
				cliente.setAddressComplement(jsonObj.getString("addressComplement"));
				cliente.setBairro(jsonObj.getString("bairro"));
				cliente.setCity(jsonObj.getString("city"));
				cliente.setUf(jsonObj.getString("uf"));
				cliente.setCep(jsonObj.getString("cep"));
				cliente.setObs(jsonObj.getString("obs"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
	}

	public static List<ClienteDto> pesquisarClientesByDescricao(String descricao) throws Exception {
		try {
			// BUSCA TODOS CLIENTES
			String endpoint = getUrl() + "clients/descricao?name=" + descricao;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			ClienteDto cliente;
			List<ClienteDto> clientes = new ArrayList<>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dateNasc_const = jsonObj.getString("dateNasc_const");
				String dateExp = jsonObj.getString("dateExp");
				cliente = new ClienteDto();
				cliente.setId(jsonObj.getLong("id"));
				cliente.setTipo(jsonObj.getString("tipo"));
				cliente.setName(jsonObj.getString("name"));
				cliente.setPhone(jsonObj.getString("phone"));
				cliente.setEmail(jsonObj.getString("email"));
				cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj"));
				cliente.setRg_ie(jsonObj.getString("rg_ie"));
				cliente.setDateNasc_const(LocalDate.parse(dateNasc_const, format));
				cliente.setDateExp(LocalDate.parse(dateExp, format));
				cliente.setAddress(jsonObj.getString("address"));
				cliente.setAddressNumber(jsonObj.getString("addressNumber"));
				cliente.setAddressComplement(jsonObj.getString("addressComplement"));
				cliente.setBairro(jsonObj.getString("bairro"));
				cliente.setCity(jsonObj.getString("city"));
				cliente.setUf(jsonObj.getString("uf"));
				cliente.setCep(jsonObj.getString("cep"));
				cliente.setObs(jsonObj.getString("obs"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
	}

	public static ClienteDto pesquisarClienteById(int id) throws Exception {
		
			// BUSCA TODOS CLIENTES
			String endpoint = getUrl() + "clients/" + id;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonObj = new JSONObject(response.body());
			ClienteDto cliente = new ClienteDto();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			if(response.statusCode()== 200) {
			cliente = new ClienteDto();
			cliente.setId(jsonObj.getLong("id"));
			cliente.setTipo(jsonObj.getString("tipo"));
			cliente.setName(jsonObj.getString("name"));
			cliente.setPhone(jsonObj.getString("phone"));
			cliente.setEmail(jsonObj.getString("email"));
			cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj"));
			cliente.setRg_ie(jsonObj.getString("rg_ie"));
			cliente.setDateNasc_const(LocalDate.parse(jsonObj.getString("dateNasc_const"), format));
			cliente.setDateExp(LocalDate.parse(jsonObj.getString("dateExp"), format));
			cliente.setAddress(jsonObj.getString("address"));
			cliente.setAddressNumber(jsonObj.getString("addressNumber"));
			cliente.setAddressComplement(jsonObj.getString("addressComplement"));
			cliente.setBairro(jsonObj.getString("bairro"));
			cliente.setCity(jsonObj.getString("city"));
			cliente.setUf(jsonObj.getString("uf"));
			cliente.setCep(jsonObj.getString("cep"));
			cliente.setObs(jsonObj.getString("obs"));
			return cliente;
			}
			return null;
		
	}

	public void novo(ActionEvent action) throws IOException {
		setNovoClient(new NovoClienteController());
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("ClienteViews/NovoCliente.fxml"));
		Scene scene = new Scene(painel, 800, 610);
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

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
			Parent painel = FXMLLoader.load(App.class.getResource("ClienteViews/EditarCliente.fxml"));
			Scene scene = new Scene(painel, 800, 610);
			stage.setTitle("Editar Cliente");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();

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
}
