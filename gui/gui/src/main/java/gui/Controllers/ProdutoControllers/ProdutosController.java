package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.math.BigDecimal;
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

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Controllers.TributacaoControllers.NCMController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.MarkupDto;
import gui.Dtos.NcmDto;
import gui.Dtos.ProdutoDto;
import gui.Dtos.Style;
import gui.Dtos.UnidadeProdutoDto;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProdutosController {

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	public static TableView<ProdutoDto> tabelaprodutos;

	public static ObservableList<ProdutoDto> observableList;

	public static ProdutosController produtosController;

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

	@FXML
	public void initialize() throws Exception {
		produtosController = this;

		// CRIA UMA CATEGORIA PADRÃO CASO O BANCO SEJA NULL
		if (NovaCategoriaController.buscarCategoriasProduto().isEmpty()) {
			CategoriaProdutoDto categoria = new CategoriaProdutoDto();
			categoria.setDescricao(" ");
			NovaCategoriaController.criarCategoria(categoria);
		}

		// CRIA UMA UNIDADE PADRÃO CASO O BANCO SEJA NULL
		if (NovaUnidadeController.buscarUnidadesProduto().isEmpty()) {
			UnidadeProdutoDto unidade = new UnidadeProdutoDto();
			unidade.setDescricao(" ");
			NovaUnidadeController.criarUnidade(unidade);
		}

		// CRIA UM MARKUP PADRÃO CASO O BANCO SEJA NULL
		if (MarkupPadraoController.buscarMarkup() == null) {
			MarkupDto markup = new MarkupDto();
			markup.setId(1);
			markup.setMarkup(new BigDecimal(0));
			markup.setUtilizar(false);
			MarkupPadraoController.criarMarkup(markup);
		}

		// CRIA UM NCM VAZIO
		if (NCMController.buscarNcms().isEmpty()) {
			NCMController.criarNcmVazio();
		}

	}

	/**
	 * Método aplica efeitos nos botões
	 */
	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovo());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnApagar());

	}

	/**
	 * Método constrói e popula tabela de produtos
	 * 
	 * @return TableView de produtos
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public TableView<ProdutoDto> construirTabela() throws Exception {

		setTabelaprodutos(new TableView<ProdutoDto>());

		TableColumn<ProdutoDto, Long> colunaId = new TableColumn<ProdutoDto, Long>("Id");
		colunaId.setCellValueFactory(new PropertyValueFactory<ProdutoDto, Long>("id"));

		TableColumn<ProdutoDto, String> colunaCod = new TableColumn<ProdutoDto, String>("Código");
		colunaCod.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("codigo"));

		TableColumn<ProdutoDto, String> colunaDesc = new TableColumn<ProdutoDto, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("descricao"));
		colunaDesc.setPrefWidth(450);

		TableColumn<ProdutoDto, Integer> colunaEstoque = new TableColumn<ProdutoDto, Integer>("Estoque");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<ProdutoDto, Integer>("estoque"));
		colunaEstoque.setMinWidth(90);

		TableColumn<ProdutoDto, String> colunaCusto = new TableColumn<ProdutoDto, String>("Custo");
		colunaCusto.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("custo"));

		TableColumn<ProdutoDto, String> colunaValor = new TableColumn<ProdutoDto, String>("Preço");
		colunaValor.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("valorVenda"));

		TableColumn<ProdutoDto, String> colunaUni = new TableColumn<ProdutoDto, String>("Unidade");
		colunaUni.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("unidadeProduto"));

		TableColumn<ProdutoDto, String> colunaCateg = new TableColumn<ProdutoDto, String>("Categoria");
		colunaCateg.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("categoria"));

		TableColumn<ProdutoDto, NcmDto> colunaNcm = new TableColumn<ProdutoDto, NcmDto>("NCM");
		colunaNcm.setCellValueFactory(new PropertyValueFactory<ProdutoDto, NcmDto>("ncm"));

		TableColumn<ProdutoDto, String> colunaObs = new TableColumn<ProdutoDto, String>("Observações");
		colunaObs.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("obs"));

		TableColumn<ProdutoDto, String> colunaFornecedor = new TableColumn<ProdutoDto, String>("Fornecedor");
		colunaFornecedor.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("fornecedor"));

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaprodutos().getColumns().addAll(colunaId, colunaCod, colunaDesc, colunaEstoque, colunaCusto,
				colunaValor, colunaUni, colunaCateg, colunaNcm, colunaObs, colunaFornecedor);

		if (getTabelaprodutos().getItems().isEmpty()) {
			getTabelaprodutos().setPlaceholder(new Label("Nenhum Produto Cadastrado."));
		}
		return getTabelaprodutos();

	}

	public static void popularTabela() throws Exception {
		List<ProdutoDto> produtos = getAllProducts();
		setObservableList(FXCollections.observableArrayList(produtos));
		getTabelaprodutos().setItems(observableList);

	}

	public static List<ProdutoDto> getAllProducts() throws Exception {
		try {
			// BUSCA TODOS PRODUTOS
			String endpoint = url + "products";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			List<ProdutoDto> produtos = new ArrayList<>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			// OBJETOS QUE COMPÕEM UM PRODUTO
			ProdutoDto produto;
			UnidadeProdutoDto unidadeProduto;
			CategoriaProdutoDto categoriaProduto;
			MarkupDto markup;
			NcmDto ncm;

			for (int i = 0; i < responseJson.length(); i++) {
				produto = new ProdutoDto();
				unidadeProduto = new UnidadeProdutoDto();
				categoriaProduto = new CategoriaProdutoDto();
				markup = new MarkupDto();
				ncm = new NcmDto();

				JSONObject jsonObj = responseJson.getJSONObject(i);
				JSONObject unidadeJson = (JSONObject) jsonObj.get("unidadeProduto");
				JSONObject categoriaJson = (JSONObject) jsonObj.get("categoria");
				JSONObject markupJson = (JSONObject) jsonObj.get("markup");
				JSONObject ncmJson = jsonObj.getJSONObject("ncm");

				String dataInclusao = jsonObj.getString("dataInclusao").substring(0, 19).replaceAll("T", " ");

				// PRECHE UNIDADE PRODUTO
				unidadeProduto.setId(unidadeJson.getInt("id"));
				unidadeProduto.setDescricao(unidadeJson.getString("descricao"));

				// PRENCHE CATEGORIA PRODUTO
				categoriaProduto.setId(categoriaJson.getInt("id"));
				categoriaProduto.setDescricao(categoriaJson.getString("descricao"));

				// PRENCHE MARKUP PRODUTO
				markup.setId(markupJson.getInt("id"));
				markup.setMarkup(new BigDecimal(markupJson.getBigInteger("markup")));
				markup.setUtilizar(markupJson.getBoolean("utilizar"));

				// PRENCHE NCM PRODUTO
				ncm.setId(ncmJson.getLong("id"));
				ncm.setNcm(ncmJson.getLong("ncm"));
				ncm.setEx(ncmJson.getString("ex"));
				ncm.setTipo(ncmJson.getString("tipo"));
				ncm.setDescricao(ncmJson.getString("descricao"));
				ncm.setNacionalfederal(ncmJson.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(ncmJson.getDouble("importadosfederal"));
				ncm.setEstadual(ncmJson.getDouble("estadual"));
				ncm.setMunicipal(ncmJson.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(ncmJson.getString("vigenciainicio")));
				ncm.setDataFim(LocalDate.parse(ncmJson.getString("vigenciafim")));
				ncm.setChave(ncmJson.getString("chave"));
				ncm.setVersao(ncmJson.getString("versao"));
				ncm.setFonte(ncmJson.getString("fonte"));

				// PRENCHE O PRODUTO EM SI
				produto.setId(jsonObj.getLong("id"));
				produto.setDescricao(jsonObj.getString("descricao").toUpperCase());
				produto.setCodigo(jsonObj.getString("codigo"));
				produto.setValorVenda(jsonObj.getDouble("valorVenda"));
				produto.setCusto(jsonObj.getDouble("custo"));
				produto.setEstoque(jsonObj.getInt("estoque"));
				produto.setUnidadeProduto(unidadeProduto);
				produto.setCategoria(categoriaProduto);
				produto.setMarkup(markup);
				produto.setTributacao(jsonObj.getString("tributacao"));
				produto.setNcm(ncm);
				produto.setCest(jsonObj.getString("cest"));
				produto.setDataInclusao(LocalDateTime.parse(dataInclusao, format));
				produto.setEAN_GTIN(jsonObj.getString("ean_GTIN"));
				produto.setObs(jsonObj.getString("obs"));
				produtos.add(produto);

			}
			return produtos;
		} catch (Exception e) {
			throw new Exception("Não foi possível encontrar produtos! " + e.getMessage() + e.getCause());
		}

	}

	public static List<ProdutoDto> pesquisarProdutoByDescricao(String descricao) throws Exception {
		try {
			String endpoint = url + "products/descricao?desc=" + descricao;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			List<ProdutoDto> produtos = new ArrayList<>();
			ProdutoDto produto;
			UnidadeProdutoDto unidadeProduto;
			CategoriaProdutoDto categoriaProduto;
			MarkupDto markup;
			NcmDto ncm;

			// DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			for (int i = 0; i < responseJson.length(); i++) {
				produto = new ProdutoDto();
				unidadeProduto = new UnidadeProdutoDto();
				categoriaProduto = new CategoriaProdutoDto();
				markup = new MarkupDto();
				ncm = new NcmDto();

				JSONObject jsonObj = responseJson.getJSONObject(i);
				JSONObject unidadeJson = (JSONObject) jsonObj.get("unidadeProduto");
				JSONObject categoriaJson = (JSONObject) jsonObj.get("categoria");
				JSONObject markupJson = (JSONObject) jsonObj.get("markup");
				JSONObject ncmJson = jsonObj.getJSONObject("ncm");

				String dataInclusao = jsonObj.getString("dataInclusao").substring(0, 19).replaceAll("T", " ");

				// PRECHE UNIDADE PRODUTO
				unidadeProduto.setId(unidadeJson.getInt("id"));
				unidadeProduto.setDescricao(unidadeJson.getString("descricao"));

				// PRENCHE CATEGORIA PRODUTO
				categoriaProduto.setId(categoriaJson.getInt("id"));
				categoriaProduto.setDescricao(categoriaJson.getString("descricao"));

				// PRENCHE MARKUP PRODUTO
				markup.setId(markupJson.getInt("id"));
				markup.setMarkup(new BigDecimal(markupJson.getBigInteger("markup")));
				markup.setUtilizar(markupJson.getBoolean("utilizar"));

				// PRENCHE NCM PRODUTO
				ncm.setId(ncmJson.getLong("id"));
				ncm.setNcm(ncmJson.getLong("ncm"));
				ncm.setEx(ncmJson.getString("ex"));
				ncm.setTipo(ncmJson.getString("tipo"));
				ncm.setDescricao(ncmJson.getString("descricao"));
				ncm.setNacionalfederal(ncmJson.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(ncmJson.getDouble("importadosfederal"));
				ncm.setEstadual(ncmJson.getDouble("estadual"));
				ncm.setMunicipal(ncmJson.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(ncmJson.getString("vigenciainicio")));
				ncm.setDataFim(LocalDate.parse(ncmJson.getString("vigenciafim")));
				ncm.setChave(ncmJson.getString("chave"));
				ncm.setVersao(ncmJson.getString("versao"));
				ncm.setFonte(ncmJson.getString("fonte"));

				// PRENCHE O PRODUTO EM SI
				produto.setId(jsonObj.getLong("id"));
				produto.setDescricao(jsonObj.getString("descricao").toUpperCase());
				produto.setCodigo(jsonObj.getString("codigo"));
				produto.setValorVenda(jsonObj.getDouble("valorVenda"));
				produto.setCusto(jsonObj.getDouble("custo"));
				produto.setEstoque(jsonObj.getInt("estoque"));
				produto.setUnidadeProduto(unidadeProduto);
				produto.setCategoria(categoriaProduto);
				produto.setMarkup(markup);
				produto.setTributacao(jsonObj.getString("tributacao"));
				produto.setNcm(ncm);
				produto.setCest(jsonObj.getString("cest"));
				produto.setDataInclusao(LocalDateTime.parse(dataInclusao, format));
				produto.setEAN_GTIN(jsonObj.getString("ean_GTIN"));
				produto.setObs(jsonObj.getString("obs"));
				produtos.add(produto);
			}

			return produtos;
		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}

	}
	
	public static List<ProdutoDto> pesquisarProdutoByCodigo(String codigo) throws Exception {
		try {
			String endpoint = url + "products/codigo?ean_gtin=" + codigo;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			List<ProdutoDto> produtos = new ArrayList<>();
			ProdutoDto produto;
			UnidadeProdutoDto unidadeProduto;
			CategoriaProdutoDto categoriaProduto;
			MarkupDto markup;
			NcmDto ncm;

			// DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			for (int i = 0; i < responseJson.length(); i++) {
				produto = new ProdutoDto();
				unidadeProduto = new UnidadeProdutoDto();
				categoriaProduto = new CategoriaProdutoDto();
				markup = new MarkupDto();
				ncm = new NcmDto();

				JSONObject jsonObj = responseJson.getJSONObject(i);
				JSONObject unidadeJson = (JSONObject) jsonObj.get("unidadeProduto");
				JSONObject categoriaJson = (JSONObject) jsonObj.get("categoria");
				JSONObject markupJson = (JSONObject) jsonObj.get("markup");
				JSONObject ncmJson = jsonObj.getJSONObject("ncm");

				String dataInclusao = jsonObj.getString("dataInclusao").substring(0, 19).replaceAll("T", " ");

				// PRECHE UNIDADE PRODUTO
				unidadeProduto.setId(unidadeJson.getInt("id"));
				unidadeProduto.setDescricao(unidadeJson.getString("descricao"));

				// PRENCHE CATEGORIA PRODUTO
				categoriaProduto.setId(categoriaJson.getInt("id"));
				categoriaProduto.setDescricao(categoriaJson.getString("descricao"));

				// PRENCHE MARKUP PRODUTO
				markup.setId(markupJson.getInt("id"));
				markup.setMarkup(new BigDecimal(markupJson.getBigInteger("markup")));
				markup.setUtilizar(markupJson.getBoolean("utilizar"));

				// PRENCHE NCM PRODUTO
				ncm.setId(ncmJson.getLong("id"));
				ncm.setNcm(ncmJson.getLong("ncm"));
				ncm.setEx(ncmJson.getString("ex"));
				ncm.setTipo(ncmJson.getString("tipo"));
				ncm.setDescricao(ncmJson.getString("descricao"));
				ncm.setNacionalfederal(ncmJson.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(ncmJson.getDouble("importadosfederal"));
				ncm.setEstadual(ncmJson.getDouble("estadual"));
				ncm.setMunicipal(ncmJson.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(ncmJson.getString("vigenciainicio")));
				ncm.setDataFim(LocalDate.parse(ncmJson.getString("vigenciafim")));
				ncm.setChave(ncmJson.getString("chave"));
				ncm.setVersao(ncmJson.getString("versao"));
				ncm.setFonte(ncmJson.getString("fonte"));

				// PRENCHE O PRODUTO EM SI
				produto.setId(jsonObj.getLong("id"));
				produto.setDescricao(jsonObj.getString("descricao").toUpperCase());
				produto.setCodigo(jsonObj.getString("codigo"));
				produto.setValorVenda(jsonObj.getDouble("valorVenda"));
				produto.setCusto(jsonObj.getDouble("custo"));
				produto.setEstoque(jsonObj.getInt("estoque"));
				produto.setUnidadeProduto(unidadeProduto);
				produto.setCategoria(categoriaProduto);
				produto.setMarkup(markup);
				produto.setTributacao(jsonObj.getString("tributacao"));
				produto.setNcm(ncm);
				produto.setCest(jsonObj.getString("cest"));
				produto.setDataInclusao(LocalDateTime.parse(dataInclusao, format));
				produto.setEAN_GTIN(jsonObj.getString("ean_GTIN"));
				produto.setObs(jsonObj.getString("obs"));
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
		Scene scene = new Scene(painel, 800, 720);
		stage.setTitle("Cadasto de Produtos");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

	}

	@SuppressWarnings("exports")
	public void editar(ActionEvent action) throws IOException {

		ProdutoDto produtoParaEditar = ProdutosController.getTabelaprodutos().getSelectionModel().getSelectedItem();

		// VERIFICA SE FOIS SELECIONADO UM CLIENTE PARA EDITAR
		if (produtoParaEditar == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Produto para editar.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			return;
		}

		else {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MenuEditarProduto.fxml"));
			Scene scene = new Scene(painel, 800, 720);
			stage.setTitle("Cadasto de Produtos");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		}

	}

	@SuppressWarnings("exports")
	public void apagar(ActionEvent action) throws IOException {

	}

}
