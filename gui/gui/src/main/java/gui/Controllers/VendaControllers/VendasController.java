package gui.Controllers.VendaControllers;

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
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.ClienteDto;
import gui.Dtos.ItensVendaDto;
import gui.Dtos.MarkupDto;
import gui.Dtos.MeioPagamentoDto;
import gui.Dtos.NcmDto;
import gui.Dtos.ProdutoDto;
import gui.Dtos.Style;
import gui.Dtos.UnidadeProdutoDto;
import gui.Dtos.UserDto;
import gui.Dtos.VendaDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VendasController {

	private static VendasController vendasController;

	private static String token = PrincipalController.getAccessToken();

	private static ObservableList<VendaDto> observableList;

	private static TableView tabelaVendas = null;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnNovaVenda;

	@FXML
	private Button btnEditarVenda;

	@FXML
	private Button btnCancelarVenda;

	public static String getToken() {
		return token;
	}

	public static ObservableList<VendaDto> getObservableList() {
		return observableList;
	}

	public static TableView getTabelaVendas() {
		return tabelaVendas;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public Button getBtnNovaVenda() {
		return btnNovaVenda;
	}

	public Button getBtnEditarVenda() {
		return btnEditarVenda;
	}

	public Button getBtnCancelarVenda() {
		return btnCancelarVenda;
	}

	public static void setToken(String token) {
		VendasController.token = token;
	}

	public static void setObservableList(ObservableList<VendaDto> observableList) {
		VendasController.observableList = observableList;
	}

	public static void setTabelaVendas(TableView tabelaVendas) {
		VendasController.tabelaVendas = tabelaVendas;
	}

	public void setBtnNovaVenda(Button btnNovaVenda) {
		this.btnNovaVenda = btnNovaVenda;
	}

	public void setBtnEditarVenda(Button btnEditarVenda) {
		this.btnEditarVenda = btnEditarVenda;
	}

	public void setBtnCancelarVenda(Button btnCancelarVenda) {
		this.btnCancelarVenda = btnCancelarVenda;
	}

	public void initialize() throws Exception {
		vendasController = this;
	}

	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovaVenda());
		efeitos.hover(getBtnEditarVenda());
		efeitos.hover(getBtnCancelarVenda());

	}

	@SuppressWarnings("unchecked")
	public TableView<VendaDto> construirTabela() throws Exception {
		setTabelaVendas(new TableView<VendaDto>()); 

		TableColumn<VendaDto, Integer> colunaID = new TableColumn<VendaDto, Integer>("ID");
		colunaID.setCellValueFactory(new PropertyValueFactory<VendaDto, Integer>("id"));

		TableColumn<VendaDto, LocalDateTime> colunaDate = new TableColumn<VendaDto, LocalDateTime>("Data / Hora");
		colunaDate.setCellValueFactory(new PropertyValueFactory<VendaDto, LocalDateTime>("dataVenda"));

		TableColumn<VendaDto, String> colunaItens = new TableColumn<VendaDto, String>("Itens");
		colunaItens.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("itens"));
		colunaItens.setMaxWidth(400);
		colunaItens.setMinWidth(300);

		TableColumn<VendaDto, String> colunaCliente = new TableColumn<VendaDto, String>("Cliente");
		colunaCliente.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("cliente"));

		TableColumn<VendaDto, String> colunaValor = new TableColumn<VendaDto, String>("Total");
		colunaValor.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("valorTotal"));

		TableColumn<VendaDto, String> colunaDesconto = new TableColumn<VendaDto, String>("Desconto");
		colunaDesconto.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("desconto"));

		colunaDesconto.setPrefWidth(100);
		colunaDesconto.setMaxWidth(100); 

		TableColumn<VendaDto, String> colunaTotalFinal = new TableColumn<VendaDto, String>("Total Final");
		colunaTotalFinal.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("totalFinal"));

		TableColumn<VendaDto, String> colunaSituacao = new TableColumn<VendaDto, String>("Status");
		colunaSituacao.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("situacao"));

		TableColumn<VendaDto, String> colunaMeioPgto = new TableColumn<VendaDto, String>("Meio de Pagamento");
		colunaMeioPgto.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("meioPgto"));
		colunaMeioPgto.setPrefWidth(150);
		colunaMeioPgto.setMaxWidth(150);

		TableColumn<VendaDto, String> colunaTroco = new TableColumn<VendaDto, String>("Troco");
		colunaTroco.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("troco"));

		TableColumn<VendaDto, String> colunaVendedor = new TableColumn<VendaDto, String>("Operador");
		colunaVendedor.setCellValueFactory(new PropertyValueFactory<VendaDto, String>("usuario"));
		colunaVendedor.setPrefWidth(150);

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaVendas().getColumns().addAll(colunaID, colunaDate, colunaItens, colunaCliente, colunaValor,
				colunaDesconto, colunaTotalFinal, colunaSituacao, colunaMeioPgto, colunaTroco, colunaVendedor);

		if (getTabelaVendas() == null) {
			getTabelaVendas().setPlaceholder(new Label("Nenhuma venda realizada!."));
		}
		return getTabelaVendas();
	}

	@SuppressWarnings("unchecked")
	public static void popularTabela() throws Exception {
		List<VendaDto> vendas = getAllClients();
		setObservableList(FXCollections.observableArrayList(vendas));
		getTabelaVendas().setItems(getObservableList());
	}

	private static List<VendaDto> getAllClients() throws Exception {

		// BUSCA TODOS CLIENTES
		String url = "http://localhost:8080/venda";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		JSONArray responseJson = new JSONArray(response.body());

		VendaDto venda;
		List<VendaDto> vendas = new ArrayList<>();
		DateTimeFormatter formatoDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatoDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// OBJETOS QUE COMPÕEM UMA VENDA
		ProdutoDto produto;
		MeioPagamentoDto meio;
		ClienteDto cliente;
		UserDto usuario;
		ItensVendaDto iten_da_venda;

		List<ItensVendaDto> itens_da_venda;

		// LOOP CONVERTE JSON EM CLIENTS
		for (int i = 0; i < responseJson.length(); i++) {
			itens_da_venda = new ArrayList<>();
			JSONObject vendaJson = responseJson.getJSONObject(i);
			// OBJETOS QUE COMPÕE UMA VENDA
			produto = new ProdutoDto();
			meio = new MeioPagamentoDto();
			cliente = new ClienteDto();
			usuario = new UserDto();
			venda = new VendaDto();

			// JSONS DOS OBJETOS
			JSONArray itensArray = (JSONArray) vendaJson.get("itensVendas");
			JSONObject meioJson = (JSONObject) vendaJson.get("meioPgto");
			JSONObject clienteJson = (JSONObject) vendaJson.get("cliente");
			JSONObject usuarioJson = (JSONObject) vendaJson.get("usuario");

			// OBJETOS QUE COMPÕEM UM PRODUTO
			UnidadeProdutoDto unidadeProduto;
			CategoriaProdutoDto categoriaProduto;
			MarkupDto markup;
			NcmDto ncm;

			if (vendaJson.has("itensVendas")) {
				for (int j = 0; j < itensArray.length(); j++) {
					JSONObject produtoSJson = itensArray.getJSONObject(j);
					JSONObject produtoJson = produtoSJson.getJSONObject("produto");
					// JSONS DOS OBJETOS DENTRO DE PRODUTOS
					JSONObject unidadeJson = (JSONObject) produtoJson.get("unidadeProduto");
					JSONObject markupJson = (JSONObject) produtoJson.get("markup");
					JSONObject categoriaJson = (JSONObject) produtoJson.get("categoria");
					JSONObject ncmJson = (JSONObject) produtoJson.get("ncm");

					// PRENCHE O PRODUTO DO ITEN DA VENDA
					// PRENCHE A UNIDADE DO PRODUTO
					unidadeProduto = new UnidadeProdutoDto();
					unidadeProduto.setId(unidadeJson.getInt("id"));
					unidadeProduto.setDescricao(unidadeJson.getString("descricao"));

					// PRENCHE MARKUP PRODUTO
					markup = new MarkupDto();
					markup.setId(markupJson.getInt("id"));
					markup.setMarkup(new BigDecimal(markupJson.getBigInteger("markup")));
					markup.setUtilizar(markupJson.getBoolean("utilizar"));

					// PRENCHE CATEGORIA PRODUTO
					categoriaProduto = new CategoriaProdutoDto();
					categoriaProduto.setId(categoriaJson.getInt("id"));
					categoriaProduto.setDescricao(categoriaJson.getString("descricao"));

					// PRENCHE NCM PRODUTO
					ncm = new NcmDto();
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

					// PRENCHE O PRODUTO DO ITEN
					produto = new ProdutoDto();
					produto.setId(produtoJson.getLong("id"));
					produto.setDescricao(produtoJson.getString("descricao").toUpperCase());
					produto.setCodigo(produtoJson.getString("codigo"));
					produto.setValorVenda(produtoJson.getDouble("valorVenda"));
					produto.setCusto(produtoJson.getDouble("custo"));
					produto.setEstoque(produtoJson.getInt("estoque"));
					produto.setUtilizarMarkup(produtoJson.getBoolean("utilizarMarkup"));
					produto.setUnidadeProduto(unidadeProduto);
					produto.setMarkup(markup);
					produto.setCategoria(categoriaProduto);
					produto.setFornecedor(produtoJson.getString("fornecedor"));
					produto.setTributacao(produtoJson.getString("tributacao"));
					produto.setNcm(ncm);
					produto.setCest(produtoJson.getString("cest"));
					String dataInclusao = produtoJson.getString("dataInclusao").substring(0, 19).replaceAll("T", " ");
					produto.setDataInclusao(LocalDateTime.parse(dataInclusao, formatoDateTime));
					produto.setObs(produtoJson.getString("obs"));
					produto.setStatus(produtoJson.getBoolean("status"));
					produto.setEAN_GTIN(produtoJson.getString("ean_GTIN"));

					// PRENCHE O ITEM DA VENDA EM SI
					iten_da_venda = new ItensVendaDto();
					iten_da_venda.setId(produtoSJson.getLong("id"));
					iten_da_venda.setProduto(produto);
					iten_da_venda.setQuantidade(produtoSJson.getInt("quantidade"));
					iten_da_venda.setTotalIten(produtoSJson.getDouble("totalIten"));

					// ADICIONA O ITEM NA LISTA DE ITENS
					itens_da_venda.add(iten_da_venda);
				}
			}

			// PRECHE MEIO PAGAMENTO
			meio.setId(meioJson.getLong("id"));
			meio.setMeio(meioJson.getString("meio"));

			// PRENCHE CLIENTE
			cliente.setId(clienteJson.getLong("id"));
			cliente.setTipo(clienteJson.getString("tipo"));
			cliente.setName(clienteJson.getString("name"));
			cliente.setPhone(clienteJson.getString("phone"));
			cliente.setEmail(clienteJson.getString("email"));
			cliente.setCpf_cnpj(clienteJson.getString("cpf_cnpj"));
			cliente.setRg_ie(clienteJson.getString("rg_ie"));
			cliente.setDateNasc_const(LocalDate.parse(clienteJson.getString("dateNasc_const"), formatoDate));
			cliente.setDateExp(LocalDate.parse(clienteJson.getString("dateExp"), formatoDate));
			cliente.setAddress(clienteJson.getString("address"));
			cliente.setAddressNumber(clienteJson.getString("addressNumber"));
			cliente.setAddressComplement(clienteJson.getString("addressComplement"));
			cliente.setBairro(clienteJson.getString("bairro"));
			cliente.setCity(clienteJson.getString("city"));
			cliente.setUf(clienteJson.getString("uf"));
			cliente.setCep(clienteJson.getString("cep"));
			cliente.setObs(clienteJson.getString("obs"));

			// USUARIO PRENCHE SOMENTE NOME COMPLETO
			usuario.setFullName(usuarioJson.getString("fullName"));

			// PRENCHE OS ITENS DA VENDA
			venda.setId(vendaJson.getLong("id"));
			String dataVenda = vendaJson.getString("dataVenda").substring(0, 19).replaceAll("T", " ");
			venda.setDataVenda(LocalDateTime.parse(dataVenda, formatoDateTime));
			venda.setCliente(cliente);
			venda.setDesconto(vendaJson.getDouble("desconto"));
			venda.setTotalFinal(vendaJson.getDouble("valorPago"));
			venda.setMeioPgto(meio);
			venda.setTroco(vendaJson.getDouble("troco"));
			venda.setUsuario(usuario);
			venda.setStatus(vendaJson.getBoolean("status"));
			venda.setItens(itens_da_venda);

			// ADICIONA A VENDA NA LITA DE VENDAS
			vendas.add(venda);
		}
		return vendas;
	}
	
	@SuppressWarnings("exports")
	public void novaVenda(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("VendaViews/PDV.fxml"));
		Scene scene = new Scene(painel, 1500, 800);
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setTitle("PDV - Vendas");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
			
	}
}
