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

import gui.Models.Clients;
import gui.Models.Produtos;
import gui.Models.Style;
import gui.Models.Uf_Enum;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ProdutosController {
	
	private static String token = PrincipalController.getAccessToken();
	
	@FXML
	private BorderPane telaBase;
	
	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;
	
	static TableView<Produtos> tabelaprodutos = null;

	static ObservableList<Produtos> observableList;


	public static TableView<Produtos> getTabelaprodutos() {
		return tabelaprodutos;
	}

	public static ObservableList<Produtos> getObservableList() {
		return observableList;
	}

	public static void setTabelaprodutos(TableView<Produtos> tabelaprodutos) {
		ProdutosController.tabelaprodutos = tabelaprodutos;
	}

	public static void setObservableList(ObservableList<Produtos> observableList) {
		ProdutosController.observableList = observableList;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
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

	public void setBtnNovo(Button btnNovo) {
		this.btnNovo = btnNovo;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void setBtnApagar(Button btnApagar) {
		this.btnApagar = btnApagar;
	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());
	
	}
	
	 
	@SuppressWarnings("unchecked")
	
	public TableView<Produtos> construirTabela() throws Exception {
		
		setTabelaprodutos(new TableView<Produtos>());
/*
		TableColumn<Produtos, Integer> colunaID = new TableColumn<Produtos, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("id"));

		TableColumn<Produtos, String> colunaDesc = new TableColumn<Produtos, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		TableColumn<Produtos, Integer> colunaName = new TableColumn<Produtos, Integer>("Estoque");
		colunaName.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("estoque"));
		
		TableColumn<Produtos, String> colunaCategoria = new TableColumn<Produtos, String>("Categoria");
		colunaCategoria.setCellValueFactory(new PropertyValueFactory<Produtos, String>("categoria"));
		

		TableColumn<Clients, String> colunaPhone = new TableColumn<Clients, String>("Telefone");
		colunaPhone.setCellValueFactory(new PropertyValueFactory<Clients, String>("phone"));
		colunaPhone.setMinWidth(120);

		TableColumn<Clients, String> colunaCpf_Cnpj = new TableColumn<Clients, String>("Cpf/Cnpj");
		colunaCpf_Cnpj.setCellValueFactory(new PropertyValueFactory<Clients, String>("cpf_cnpj"));

		TableColumn<Clients, String> colunaRg_Ie = new TableColumn<Clients, String>("Rg/Ie");
		colunaRg_Ie.setCellValueFactory(new PropertyValueFactory<Clients, String>("rg_ie"));

		TableColumn<Clients, LocalDate> colunaDateNasc_cons = new TableColumn<Clients, LocalDate>("Data Nasc/Const.");
		colunaDateNasc_cons.setCellValueFactory(new PropertyValueFactory<Clients, LocalDate>("dateNasc_const"));

		TableColumn<Clients, LocalDate> colunaDateExp = new TableColumn<Clients, LocalDate>("Data de Expedição");
		colunaDateExp.setCellValueFactory(new PropertyValueFactory<Clients, LocalDate>("dateExp"));

		TableColumn<Clients, String> colunaEndereco = new TableColumn<Clients, String>("Endereço");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Clients, String>("address"));

		TableColumn<Clients, String> colunaNum = new TableColumn<Clients, String>("Número");
		colunaNum.setCellValueFactory(new PropertyValueFactory<Clients, String>("addressNumber"));

		TableColumn<Clients, String> colunaComplement = new TableColumn<Clients, String>("Complemento");
		colunaComplement.setCellValueFactory(new PropertyValueFactory<Clients, String>("addressComplement"));

		TableColumn<Clients, String> colunaCity = new TableColumn<Clients, String>("Cidade");
		colunaCity.setCellValueFactory(new PropertyValueFactory<Clients, String>("city"));

		TableColumn<Clients, String> colunaUf = new TableColumn<Clients, String>("UF");
		colunaUf.setCellValueFactory(new PropertyValueFactory<Clients, String>("uf"));

		TableColumn<Clients, String> colunaCep = new TableColumn<Clients, String>("Cep");
		colunaCep.setCellValueFactory(new PropertyValueFactory<Clients, String>("cep"));

		TableColumn<Clients, String> colunaObs = new TableColumn<Clients, String>("Obs");
		colunaObs.setCellValueFactory(new PropertyValueFactory<Clients, String>("obs"));

		// POPULA A TABELA
		popularTabela();
		

		// ADICIONA AS COLUNAS
		getTabelaClientes().getColumns().addAll(colunaID, colunaTipo, colunaName, colunaEmail, colunaPhone,
				colunaCpf_Cnpj, colunaRg_Ie, colunaDateNasc_cons, colunaDateExp, colunaEndereco, colunaNum,
				colunaComplement, colunaCity, colunaUf, colunaCep, colunaObs);

		if (getTabelaClientes() == null) {
			getTabelaClientes().setPlaceholder(new Label("Nenhum Cliente Cadastrado."));
		}*/
		return getTabelaprodutos();
		
	}

	public static void popularTabela() throws Exception {
		/*
		List<Clients> clientes = getAllClients();
		setObservableList(FXCollections.observableArrayList(clientes));
		getTabelaClientes().setItems(observableList);
		*/
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
				cliente.setTipo(jsonObj.getString("tipo"));
				cliente.setName(jsonObj.getString("name"));
				cliente.setPhone(jsonObj.getString("phone"));
				cliente.setEmail(jsonObj.getString("email"));
				cliente.setCpf_cnpj(jsonObj.getString("cpf_cnpj"));
				cliente.setRg_ie(jsonObj.getString("rg_ie"));
				cliente.setDateNasc_const(jsonObj.getString("dateNasc_const"));
				cliente.setDateExp(jsonObj.getString("dateExp"));
				cliente.setAddress(jsonObj.getString("address"));
				cliente.setAddressNumber(jsonObj.getString("addressNumber"));
				cliente.setAddressComplement(jsonObj.getString("addressComplement"));
				cliente.setCity(jsonObj.getString("city"));
				cliente.setUf(Uf_Enum.valueOf(jsonObj.getString("uf")));
				cliente.setCep(jsonObj.getString("cep"));
				cliente.setObs(jsonObj.getString("obs"));
				clientes.add(cliente);
			}
			
			return clientes;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
		
	}

	@SuppressWarnings("exports")
	public void novo(ActionEvent action) throws IOException {
	}
	
	@SuppressWarnings("exports")
	public void editar(ActionEvent action) throws IOException {
	}
	
	@SuppressWarnings("exports")
	public void apagar(ActionEvent action) throws IOException {
	}
	
	
	
}
