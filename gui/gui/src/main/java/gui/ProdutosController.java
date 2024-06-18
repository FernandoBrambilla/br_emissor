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
import gui.Models.Produto;
import gui.Models.Style;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	static TableView<Produto> tabelaprodutos = null;

	static ObservableList<Produto> observableList;


	public static TableView<Produto> getTabelaprodutos() {
		return tabelaprodutos;
	}

	public static ObservableList<Produto> getObservableList() {
		return observableList;
	}

	public static void setTabelaprodutos(TableView<Produto> tabelaprodutos) {
		ProdutosController.tabelaprodutos = tabelaprodutos;
	}

	public static void setObservableList(ObservableList<Produto> observableList) {
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
	
	public TableView<Produto> construirTabela() throws Exception {
		
		setTabelaprodutos(new TableView<Produto>());

		TableColumn<Produto, Integer> colunaID = new TableColumn<Produto, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));

		TableColumn<Produto, String> colunaDesc = new TableColumn<Produto, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		
		TableColumn<Produto, String> colunaCod = new TableColumn<Produto, String>("Código");
		colunaCod.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
		
		TableColumn<Produto, Integer> colunaEstoque = new TableColumn<Produto, Integer>("Estoque");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("estoque"));
		

		TableColumn<Produto, Double> colunaCusto = new TableColumn<Produto, Double>("Custo");
		colunaCusto.setCellValueFactory(new PropertyValueFactory<Produto, Double>("custo"));


		TableColumn<Produto, Double> colunaValor = new TableColumn<Produto, Double>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorVenda"));
		
		/*
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
		 */
		// POPULA A TABELA
		popularTabela();
		

		// ADICIONA AS COLUNAS
		getTabelaprodutos().getColumns().addAll(colunaID, colunaDesc,colunaCod, colunaEstoque, colunaCusto, colunaValor);

		if (getTabelaprodutos() == null) {
			getTabelaprodutos().setPlaceholder(new Label("Nenhum Produto Cadastrado."));
		}
		return getTabelaprodutos();
		
	}

	public static void popularTabela() throws Exception {
		List<Produto> produtos = getAllProducts();
		setObservableList(FXCollections.observableArrayList(produtos));
		getTabelaprodutos().setItems(observableList);
		
	}

	private static List<Produto> getAllProducts() throws Exception {

		try {
			// BUSCA TODOS PRODUTOS
			String url = "http://localhost:8080/products";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			Produto produto;
			List<Produto> produtos = new ArrayList<>();

			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				produto = new Produto();  
				produto.setId(jsonObj.getLong("id"));
				produto.setCodigo(jsonObj.getString("codigo"));
				produto.setDescricao(jsonObj.getString("descricao"));
				produto.setEstoque(jsonObj.getInt("estoque"));
				produto.setValorVenda(jsonObj.getDouble("valorVenda"));
				produto.setCusto((jsonObj.getDouble("custo") == 0 ? jsonObj.getDouble("custo"): 0));
				produtos.add(produto);
			}
			System.out.println(produtos);
			
			
			return produtos;
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
