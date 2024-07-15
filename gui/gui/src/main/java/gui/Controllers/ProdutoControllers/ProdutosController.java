package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.Markup;
import gui.Dtos.ProdutoDto;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ProdutosController {

	private static String url = PrincipalController.getUrl();
	
	private static String token = PrincipalController.getAccessToken();

	public static TableView<ProdutoDto> tabelaprodutos;

	public static ObservableList<ProdutoDto> observableList;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;

	public static TableView<ProdutoDto> getTabelaprodutos() {
		return tabelaprodutos;
	}

	public static ObservableList<ProdutoDto> getObservableList() {
		return observableList;
	}

	public static void setTabelaprodutos(TableView<ProdutoDto> tabelaprodutos) {
		ProdutosController.tabelaprodutos = tabelaprodutos;
	}

	public static void setObservableList(ObservableList<ProdutoDto> observableList) {
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

	public void initialize() throws Exception{
		
		//CRIA UM MARKUP PADRÃO CASO O BANCO SEJA NULL
		if (MarkupPadraoController.buscarMarkup() == null) {
			Markup markup = new Markup();
			markup.setId(1);
			markup.setMarkup(new BigDecimal(0));
			markup.setUtilizar(false);
			MarkupPadraoController.criarMarkup(markup);
		}
	

	}
	
	
 
	

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());

	}

	public static List<String> buscarTodasCategoriasProduto() throws Exception {
		try {
			String url = "http://localhost:8080/category";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			List<String> listaCategorias = new ArrayList<>();

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				listaCategorias.add(jsonObj.getString("descricao"));
			}
			return listaCategorias;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public TableView<ProdutoDto> construirTabela() throws Exception {

		setTabelaprodutos(new TableView<ProdutoDto>());

		TableColumn<ProdutoDto, String> colunaCod = new TableColumn<ProdutoDto, String>("Código");
		colunaCod.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("codigo"));

		TableColumn<ProdutoDto, String> colunaDesc = new TableColumn<ProdutoDto, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("descricao"));

		TableColumn<ProdutoDto, Integer> colunaEstoque = new TableColumn<ProdutoDto, Integer>("Estoque");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<ProdutoDto, Integer>("estoque"));
		colunaEstoque.setPrefWidth(100);

		TableColumn<ProdutoDto, String> colunaCusto = new TableColumn<ProdutoDto, String>("Custo R$");
		colunaCusto.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("custo"));

		TableColumn<ProdutoDto, String> colunaValor = new TableColumn<ProdutoDto, String>("Preço R$");
		colunaValor.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("valorVenda"));

		TableColumn<ProdutoDto, String> colunaUni = new TableColumn<ProdutoDto, String>("Unidade");
		colunaUni.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("unidadeProduto"));

		TableColumn<ProdutoDto, String> colunaCateg = new TableColumn<ProdutoDto, String>("Categoria");
		colunaCateg.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("categoria"));

		TableColumn<ProdutoDto, String> colunaFornecedor = new TableColumn<ProdutoDto, String>("Fornecedor");
		colunaFornecedor.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("fornecedor"));

		/*
		 * TableColumn<Clients, String> colunaRg_Ie = new TableColumn<Clients,
		 * String>("Rg/Ie"); colunaRg_Ie.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("rg_ie"));
		 * 
		 * TableColumn<Clients, LocalDate> colunaDateNasc_cons = new
		 * TableColumn<Clients, LocalDate>("Data Nasc/Const.");
		 * colunaDateNasc_cons.setCellValueFactory(new PropertyValueFactory<Clients,
		 * LocalDate>("dateNasc_const"));
		 * 
		 * TableColumn<Clients, LocalDate> colunaDateExp = new TableColumn<Clients,
		 * LocalDate>("Data de Expedição"); colunaDateExp.setCellValueFactory(new
		 * PropertyValueFactory<Clients, LocalDate>("dateExp"));
		 * 
		 * TableColumn<Clients, String> colunaEndereco = new TableColumn<Clients,
		 * String>("Endereço"); colunaEndereco.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("address"));
		 * 
		 * TableColumn<Clients, String> colunaNum = new TableColumn<Clients,
		 * String>("Número"); colunaNum.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("addressNumber"));
		 * 
		 * TableColumn<Clients, String> colunaComplement = new TableColumn<Clients,
		 * String>("Complemento"); colunaComplement.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("addressComplement"));
		 * 
		 * TableColumn<Clients, String> colunaCity = new TableColumn<Clients,
		 * String>("Cidade"); colunaCity.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("city"));
		 * 
		 * TableColumn<Clients, String> colunaUf = new TableColumn<Clients,
		 * String>("UF"); colunaUf.setCellValueFactory(new PropertyValueFactory<Clients,
		 * String>("uf"));
		 * 
		 * TableColumn<Clients, String> colunaCep = new TableColumn<Clients,
		 * String>("Cep"); colunaCep.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("cep"));
		 * 
		 * TableColumn<Clients, String> colunaObs = new TableColumn<Clients,
		 * String>("Obs"); colunaObs.setCellValueFactory(new
		 * PropertyValueFactory<Clients, String>("obs"));
		 */
		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaprodutos().getColumns().addAll(colunaCod, colunaDesc, colunaEstoque, colunaCusto, colunaValor,
				colunaUni, colunaCateg, colunaFornecedor);

		if (getTabelaprodutos() == null) {
			getTabelaprodutos().setPlaceholder(new Label("Nenhum Produto Cadastrado."));
		}
		return getTabelaprodutos();

	}

	public static void popularTabela() throws Exception {
		List<ProdutoDto> produtos = getAllProducts();
		setObservableList(FXCollections.observableArrayList(produtos));
		getTabelaprodutos().setItems(observableList);

	}

	private static List<ProdutoDto> getAllProducts() throws Exception {

		try {
			// BUSCA TODOS PRODUTOS
			String url = "http://localhost:8080/products";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			ProdutoDto produto;
			List<ProdutoDto> produtos = new ArrayList<>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			// LOOP CONVERTE JSON EM CLIENTS
			for (int i = 0; i < responseJson.length(); i++) {

				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dataInclusao = jsonObj.getString("dataInclusao").substring(0, 19).replaceAll("T", " ");

				produto = new ProdutoDto();
				produto.setId(jsonObj.getLong("id"));
				produto.setDescricao(jsonObj.getString("descricao"));
				produto.setCodigo(jsonObj.getString("codigo"));
				produto.setValorVenda(jsonObj.getDouble("valorVenda"));
				produto.setCusto(jsonObj.getDouble("custo"));
				produto.setEstoque(jsonObj.getInt("estoque"));
				produto.setUnidadeProduto(jsonObj.getString("unidadeProduto"));
				// produto.setCategoria(jsonObj.get("categoria") isEmpty() ? "" :
				// produto.setCategoria(jsonObj.getString("categoria"));
				produto.setTributacao(jsonObj.getString("tributacao"));
				produto.setNcm(jsonObj.getInt("ncm"));
				produto.setDescNcm(jsonObj.getString("descNcm"));
				produto.setCest(jsonObj.getString("cest"));
				produto.setDataInclusao(LocalDateTime.parse(dataInclusao, format));
				produto.setEAN_GTIN(jsonObj.getString("ean_GTIN"));
				produtos.add(produto);

			}
			return produtos;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}

	}

	@SuppressWarnings("exports")
	public void novo(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MenuNovoProduto.fxml"));
		Scene scene = new Scene(painel, 800, 680);
		stage.setTitle("Cadasto de Produtos");
		stage.setScene(scene);
		stage.showAndWait();
		

	}
	

	@SuppressWarnings("exports")
	public void editar(ActionEvent action) throws IOException {
		// VERIFICA SE FOIS SELECIONADO UM CLIENTE PARA EDITAR
		if (ProdutosController.getTabelaprodutos().getSelectionModel().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Produto para editar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		else {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MenuNovoProduto.fxml"));
			Scene scene = new Scene(painel, 800, 680);
			stage.setTitle("Cadasto de Produtos");
			stage.setScene(scene);
			stage.show();

			/*
			 * Stage stage = new Stage(); Parent painel =
			 * FXMLLoader.load(App.class.getResource("ProdutoViews/MenuEditarProduto.fxml"))
			 * ; Scene scene = new Scene(painel, 800, 680);
			 * stage.setTitle("Editar Produto"); stage.setScene(scene); stage.show();
			 */

		}

	}

	public void apagar(ActionEvent action) throws IOException {

	}

}
