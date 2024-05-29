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

import gui.Services.Clients;
import gui.Services.Effects;
import gui.Services.Uf_Enum;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientesController {

	static String token = PrincipalController.getAccessToken();

	static TableView<Clients> tabelaClientes = null;

	static ObservableList<Clients> observableList;

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
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;

	@FXML
	private RadioButton pf;

	@FXML
	private RadioButton pj;

	@FXML
	private TextField name;

	@FXML
	private TextField cpf_cnpj;

	@FXML
	private TextField email;

	@FXML
	private TextField rg_ie;

	@FXML
	private TextField phone;

	@FXML
	private DatePicker dataNasc_Const;

	@FXML
	private TextField adress;

	@FXML
	private DatePicker dataEmis;

	@FXML
	private TextField num;

	@FXML
	private TextField compl;

	@FXML
	private TextField city;

	@FXML
	private ChoiceBox<Uf_Enum> uf;


	public static String getToken() {
		return token;
	}

	public static TableView<Clients> getTabelaClientes() {
		return tabelaClientes;
	}

	public static ObservableList<Clients> getObservableList() {
		return observableList;
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

	public Button getBtnInativar() {
		return btnInativar;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public RadioButton getPf() {
		return pf;
	}

	public RadioButton getPj() {
		return pj;
	}

	public TextField getName() {
		return name;
	}

	public TextField getCpf_cnpj() {
		return cpf_cnpj;
	}

	public TextField getEmail() {
		return email;
	}

	public TextField getRg_ie() {
		return rg_ie;
	}

	public TextField getPhone() {
		return phone;
	}

	public DatePicker getDataNasc_Const() {
		return dataNasc_Const;
	}

	public TextField getAdress() {
		return adress;
	}

	public DatePicker getDataEmis() {
		return dataEmis;
	}

	public TextField getNum() {
		return num;
	}

	public TextField getCompl() {
		return compl;
	}

	public TextField getCity() {
		return city;
	}

	public ChoiceBox<Uf_Enum> getUf() {
		return uf;
	}

	public static void setToken(String token) {
		ClientesController.token = token;
	}

	public static void setTabelaClientes(TableView<Clients> tabelaClientes) {
		ClientesController.tabelaClientes = tabelaClientes;
	}

	public static void setObservableList(ObservableList<Clients> observableList) {
		ClientesController.observableList = observableList;
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

	public void setBtnInativar(Button btnInativar) {
		this.btnInativar = btnInativar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setPf(RadioButton pf) {
		this.pf = pf;
	}

	public void setPj(RadioButton pj) {
		this.pj = pj;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public void setCpf_cnpj(TextField cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public void setRg_ie(TextField rg_ie) {
		this.rg_ie = rg_ie;
	}

	public void setPhone(TextField phone) {
		this.phone = phone;
	}

	public void setDataNasc_Const(DatePicker dataNasc_Const) {
		this.dataNasc_Const = dataNasc_Const;
	}

	public void setAdress(TextField adress) {
		this.adress = adress;
	}

	public void setDataEmis(DatePicker dataEmis) {
		this.dataEmis = dataEmis;
	}

	public void setNum(TextField num) {
		this.num = num;
	}

	public void setCompl(TextField compl) {
		this.compl = compl;
	}

	public void setCity(TextField city) {
		this.city = city;
	}

	public void setUf(ChoiceBox<Uf_Enum> uf) {
		this.uf = uf;
	}
	
	public void initialize() {
		System.out.println("inicianto novo cliente");
	}

	public void aplicaEfeitos() {
		Effects efeitos = new Effects();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());
		efeitos.hover(getBtnInativar());
	}

	@SuppressWarnings("unchecked")
	public TableView<Clients> construirTabela() throws Exception {
		setTabelaClientes(new TableView<Clients>());

		Effects efeito = new Effects();
		efeito.styleTable(getTabelaClientes());

		TableColumn<Clients, Integer> colunaID = new TableColumn<Clients, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
		colunaID.setPrefWidth(50);

		TableColumn<Clients, String> colunaName = new TableColumn<Clients, String>("Nome");
		colunaName.setCellValueFactory(new PropertyValueFactory<Clients, String>("name"));
		colunaName.setPrefWidth(250);

		TableColumn<Clients, String> colunaEmail = new TableColumn<Clients, String>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<Clients, String>("email"));
		colunaEmail.setPrefWidth(250);

		TableColumn<Clients, String> colunaPhone = new TableColumn<Clients, String>("Telefone");
		colunaPhone.setCellValueFactory(new PropertyValueFactory<Clients, String>("phone"));
		colunaPhone.setPrefWidth(80);

		TableColumn<Clients, String> colunaCpf_Cnpj = new TableColumn<Clients, String>("Cpf/Cnpj");
		colunaCpf_Cnpj.setCellValueFactory(new PropertyValueFactory<Clients, String>("cpf_cnpj"));
		colunaCpf_Cnpj.setPrefWidth(80);
		efeito.corLinhaTabela(colunaCpf_Cnpj);

		TableColumn<Clients, String> colunaRg_Ie = new TableColumn<Clients, String>("Rg/Ie");
		colunaRg_Ie.setCellValueFactory(new PropertyValueFactory<Clients, String>("rg_ie"));
		colunaRg_Ie.setPrefWidth(80);

		TableColumn<Clients, String> colunaDateNasc_cons = new TableColumn<Clients, String>(
				"Data de Nascimento/Constituição");
		colunaDateNasc_cons.setCellValueFactory(new PropertyValueFactory<Clients, String>("dateNasc_const"));
		colunaRg_Ie.setPrefWidth(80);

		TableColumn<Clients, String> colunaDateExp = new TableColumn<Clients, String>("Data de Expedição");
		colunaDateExp.setCellValueFactory(new PropertyValueFactory<Clients, String>("dateExp"));
		colunaDateExp.setPrefWidth(80);

		TableColumn<Clients, String> colunaEndereco = new TableColumn<Clients, String>("Endereço");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Clients, String>("address"));
		colunaEndereco.setPrefWidth(150);

		TableColumn<Clients, String> colunaNum = new TableColumn<Clients, String>("Número");
		colunaNum.setCellValueFactory(new PropertyValueFactory<Clients, String>("addressNumber"));
		colunaNum.setPrefWidth(20);

		TableColumn<Clients, String> colunaComplement = new TableColumn<Clients, String>("Complemento");
		colunaComplement.setCellValueFactory(new PropertyValueFactory<Clients, String>("addressComplement"));
		colunaComplement.setPrefWidth(80);

		TableColumn<Clients, String> colunaCity = new TableColumn<Clients, String>("Cidade");
		colunaCity.setCellValueFactory(new PropertyValueFactory<Clients, String>("city"));
		colunaCity.setPrefWidth(150);

		TableColumn<Clients, String> colunaUf = new TableColumn<Clients, String>("UF");
		colunaUf.setCellValueFactory(new PropertyValueFactory<Clients, String>("uf"));
		colunaUf.setPrefWidth(20);

		TableColumn<Clients, String> colunaCep = new TableColumn<Clients, String>("Cep");
		colunaCep.setCellValueFactory(new PropertyValueFactory<Clients, String>("cep"));
		colunaCep.setPrefWidth(80);

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaClientes().getColumns().addAll(colunaID, colunaName, colunaEmail, colunaPhone, colunaCpf_Cnpj,
				colunaRg_Ie, colunaDateNasc_cons, colunaDateExp, colunaEndereco, colunaNum, colunaComplement,
				colunaCity, colunaUf, colunaCep);

		if (getTabelaClientes() == null) {
			getTabelaClientes().setPlaceholder(new Label("Nenhum usuário cadastrado."));

		}

		return getTabelaClientes();
	}

	public static void popularTabela() throws Exception {
		List<Clients> clientes = getAllClients();
		setObservableList(FXCollections.observableArrayList(clientes));
		getTabelaClientes().setItems(observableList);
	}

	private static List<Clients> getAllClients() throws Exception {
		try {
			// BUSCA TODOS CLIENTES
			String url = "http://localhost:8080/clients";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			Clients cliente;
			List<Clients> clientes = new ArrayList<>();

			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				cliente = new Clients();
				cliente.setId(jsonObj.getLong("id"));
				cliente.setName(jsonObj.getString("name"));
				cliente.setPhone(jsonObj.getString("phone"));
				cliente.setEmail(jsonObj.getString("email"));
				cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj"));
				cliente.setRg_ie(jsonObj.getString("rg_ie"));
				cliente.setDateNasc_const(jsonObj.getString("dateNasc_const").toString());
				cliente.setDateExp(jsonObj.getString("dateExp").toString());
				cliente.setAddress(jsonObj.getString("address"));
				cliente.setAddressNumber(jsonObj.getString("addressNumber"));
				cliente.setAddressComplement(jsonObj.getString("addressComplement"));
				cliente.setCity(jsonObj.getString("city"));
				cliente.setUf(jsonObj.getString("uf"));
				cliente.setCep(jsonObj.getString("cep"));
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
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("ClienteViews/NovoCliente.fxml"));
		Scene scene = new Scene(painel, 800, 525);
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
		stage.show();
	}

	
	@SuppressWarnings("exports")
	public void editar(ActionEvent action) throws IOException {
		
	}
	
	@SuppressWarnings("exports")
	public void apagar(ActionEvent action) throws IOException {
		
	}
	
	@SuppressWarnings("exports")
	public void inativar(ActionEvent action) throws IOException {
		
	}
	
	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {
		
	}
	
	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}
	
	

}
