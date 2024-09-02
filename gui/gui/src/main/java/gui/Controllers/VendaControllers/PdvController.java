package gui.Controllers.VendaControllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.ClienteDto;
import gui.Dtos.ItensVendaDto;
import gui.Dtos.ProdutoDto;
import gui.Dtos.VendaDto;
import gui.Utilities.Mascaras;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PdvController {

	private static PdvController pdvController;

	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");

	Toolkit tk = Toolkit.getDefaultToolkit();

	Dimension d = tk.getScreenSize();

	private String url = PrincipalController.getUrl();

	private String token = PrincipalController.getAccessToken();

	@FXML
	private Pane root;

	@FXML
	private BorderPane painelPesquisa;

	@FXML
	private BorderPane header;

	@FXML
	private TextField produto;

	@FXML
	private ImageView imgCodBarras;

	@FXML
	private TextField quantidade;

	@FXML
	private TextField valorUnitario;

	@FXML
	private TextField totalIten;

	@FXML
	private TextField totalVenda;

	@FXML
	private Label labelSubtotal;

	@FXML
	private Label labelDesconto;

	@FXML
	private BorderPane telaBase;

	@FXML
	private Button btnLancar;

	@FXML
	private Button btnCliente;

	@FXML
	private TextField clienteText;

	@FXML
	private Label clienteLabel;

	@FXML
	private Button btnDesconto;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnConcluirDepois;

	@FXML
	private Button btnSair;

	private Button btnExcluir;

	private TableView<ItensVendaDto> tabelaItens;

	private TableView<ProdutoDto> tabelaProdutosPesquisa;

	private TableView<ClienteDto> tabelaClientesPesquisa;

	public static ObservableList<ProdutoDto> observableListProduto;

	public static ObservableList<ClienteDto> observableListCliente;

	private VendaDto venda = new VendaDto();

	private ItensVendaDto iten;

	private ClienteDto clienteSelecionado;

	private String pesquisaProduto = "";

	private String pesquisaCliente = "";

	public DecimalFormat getRealFormato() {

		return realFormato;
	}

	public Toolkit getTk() {
		return tk;
	}

	public static PdvController getPdvController() {
		return pdvController;
	}

	public static void setPdvController(PdvController pdvController) {
		PdvController.pdvController = pdvController;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(Button btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

	public Button getBtnConcluirDepois() {
		return btnConcluirDepois;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnConcluirDepois(Button btnConcluirDepois) {
		this.btnConcluirDepois = btnConcluirDepois;
	}

	public Button getBtnDesconto() {
		return btnDesconto;
	}

	public Label getLabelSubtotal() {
		return labelSubtotal;
	}

	public Label getLabelDesconto() {
		return labelDesconto;
	}

	public void setLabelSubtotal(Label labelSubtotal) {
		this.labelSubtotal = labelSubtotal;
	}

	public void setLabelDesconto(Label labelDesconto) {
		this.labelDesconto = labelDesconto;
	}

	public void setBtnDesconto(Button btnDesconto) {
		this.btnDesconto = btnDesconto;
	}

	public Dimension getD() {
		return d;
	}

	public String getUrl() {
		return url;
	}

	public String getToken() {
		return token;
	}

	public Pane getRoot() {
		return root;
	}

	public Button getBtnCliente() {
		return btnCliente;
	}

	public ClienteDto getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setBtnCliente(Button btnCliente) {
		this.btnCliente = btnCliente;
	}

	public void setClienteSelecionado(ClienteDto clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public BorderPane getPainelPesquisa() {
		return painelPesquisa;
	}

	public BorderPane getHeader() {
		return header;
	}

	public TextField getProduto() {
		return produto;
	}

	public ImageView getImgCodBarras() {
		return imgCodBarras;
	}

	public TextField getQuantidade() {
		return quantidade;
	}

	public TextField getValorUnitario() {
		return valorUnitario;
	}

	public TextField getTotalIten() {
		return totalIten;
	}

	public TextField getTotalVenda() {
		return totalVenda;
	}

	public BorderPane getTelaBase() {
		return telaBase;
	}

	public Button getBtnLancar() {
		return btnLancar;
	}

	public Button getCliente() {
		return btnCliente;
	}

	public TextField getClienteText() {
		return clienteText;
	}

	public Label getClienteLabel() {
		return clienteLabel;
	}

	public Button getBtnSair() {
		return btnSair;
	}

	public TableView<ItensVendaDto> getTabelaItens() {
		return tabelaItens;
	}

	public TableView<ClienteDto> getTabelaClientesPesquisa() {
		return tabelaClientesPesquisa;
	}

	public TableView<ProdutoDto> getTabelaProdutosPesquisa() {
		return tabelaProdutosPesquisa;
	}

	public static ObservableList<ProdutoDto> getObservableListProduto() {
		return observableListProduto;
	}

	public static ObservableList<ClienteDto> getObservableListCliente() {
		return observableListCliente;
	}

	public ItensVendaDto getIten() {
		return iten;
	}

	public String getPesquisaProduto() {
		return pesquisaProduto;
	}

	public String getPesquisaCliente() {
		return pesquisaCliente;
	}

	public void setRealFormato(DecimalFormat realFormato) {
		this.realFormato = realFormato;
	}

	public void setTk(Toolkit tk) {
		this.tk = tk;
	}

	public void setD(Dimension d) {
		this.d = d;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public void setPainelPesquisa(BorderPane painelPesquisa) {
		this.painelPesquisa = painelPesquisa;
	}

	public void setHeader(BorderPane header) {
		this.header = header;
	}

	public void setProduto(TextField produto) {
		this.produto = produto;
	}

	public void setImgCodBarras(ImageView imgCodBarras) {
		this.imgCodBarras = imgCodBarras;
	}

	public void setQuantidade(TextField quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorUnitario(TextField valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setTotalIten(TextField totalIten) {
		this.totalIten = totalIten;
	}

	public void setTotalVenda(TextField totalVenda) {
		this.totalVenda = totalVenda;
	}

	public void setTelaBase(BorderPane telaBase) {
		this.telaBase = telaBase;
	}

	public void setBtnLancar(Button btnLancar) {
		this.btnLancar = btnLancar;
	}

	public void setCliente(Button btnCliente) {
		this.btnCliente = btnCliente;
	}

	public void setClienteText(TextField clienteText) {
		this.clienteText = clienteText;
	}

	public void setClienteLabel(Label clienteLabel) {
		this.clienteLabel = clienteLabel;
	}

	public void setBtnSair(Button btnSair) {
		this.btnSair = btnSair;
	}

	public void setTabelaItens(TableView<ItensVendaDto> tabelaItens) {
		this.tabelaItens = tabelaItens;
	}

	public void setTabelaProdutosPesquisa(TableView<ProdutoDto> tabelaProdutosPesquisa) {
		this.tabelaProdutosPesquisa = tabelaProdutosPesquisa;
	}

	public void setTabelaClientesPesquisa(TableView<ClienteDto> tabelaClientesPesquisa) {
		this.tabelaClientesPesquisa = tabelaClientesPesquisa;
	}

	public static void setObservableListProduto(ObservableList<ProdutoDto> observableListProduto) {
		PdvController.observableListProduto = observableListProduto;
	}

	public static void setObservableListCliente(ObservableList<ClienteDto> observableListCliente) {
		PdvController.observableListCliente = observableListCliente;
	}

	public void setIten(ItensVendaDto iten) {
		this.iten = iten;
	}

	public void setPesquisaProduto(String pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}

	public void setPesquisaCliente(String pesquisaCliente) {
		this.pesquisaCliente = pesquisaCliente;
	}

	public VendaDto getVenda() {
		return venda;
	}

	public void setVenda(VendaDto venda) {
		this.venda = venda;
	}

	public void initialize() throws Exception {
		pdvController = this;

		Mascaras.monetaryField(getValorUnitario());
		Mascaras.numericField(getQuantidade());

		getRoot().requestFocus();

		// REDIMENSIONA OS CAMPOS
		getHeader().setPrefWidth(d.getWidth());
		getProduto().setPrefWidth(d.getWidth() / 2);
		getImgCodBarras().setLayoutX(d.getWidth() / 2 - 40);
		getTelaBase().setPrefWidth((d.getWidth() - 20));
		getTelaBase().setPrefHeight(d.getHeight() - 160);

		// CONTROI TABELA DE ITENS
		getTelaBase().setCenter(construirTabelaItens());

		// INICIA OS CAMPOS DESABILITADO
		getValorUnitario().setEditable(false);
		getQuantidade().setEditable(false);
		getClienteText().setVisible(false);

		getTabelaItens().setOnMouseClicked((mouseEvent) -> {
			if (getTabelaProdutosPesquisa() != null) {
				getTabelaProdutosPesquisa().setVisible(false);
			}
			if (getTabelaClientesPesquisa() != null) {
				getTabelaClientesPesquisa().setVisible(false);
				getClienteText().setVisible(false);
			}

		});

		// MUDA FOCO DA QUANTIDADE PARA O VALOR UNITÁRIO
		getQuantidade().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getQuantidade().setStyle(null);
				getValorUnitario().setStyle("-fx-border-color: red;");
				getValorUnitario().setText(getIten().getProduto().getValorVenda().replace("R$ ", ""));
				getIten().setQuantidade(Integer.parseInt(getQuantidade().getText()));
			}

		});

		// LIMPA O CAMPO VALOR UNITÁRIO ATRAVÉS DO CLIQUE
		getValorUnitario().setOnMouseClicked((mouseEvent) -> {
			if (!getValorUnitario().getText().isEmpty()) {
				getValorUnitario().setEditable(true);
				getValorUnitario().setText("");
			}
		});

		// MUDA FOCO DO VALOR UNITARIO PARA TOTAL ITEM
		getValorUnitario().setOnKeyPressed((keyEvent) -> {
			// LIMPA CAMPO VALOR UNITÁRIO ATRAVÉS DO TECLADO
			if (keyEvent.getCode() == KeyCode.BACK_SPACE || keyEvent.getCode() == KeyCode.DELETE) {
				getValorUnitario().setEditable(true);
				getValorUnitario().setText("");

			}

			// SETA VALOR UNITÁRIO E CALCULA VALOR TOTAL DO ITEN
			if (!getValorUnitario().getText().isEmpty()) {
				if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
					getTotalIten().setText(String.valueOf(realFormato
							.format(getIten().getTotalItenDouble() * Double.parseDouble(getQuantidade().getText()))));
					getTotalIten().requestFocus();
					getTotalIten().deselect();
					getValorUnitario().setStyle(null);
					getTotalIten().setStyle("-fx-border-color: red;");

				}
			}
		});

		// FAZ O LANÇAMENTO DO ITEN NA VENDA
		getTotalIten().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				lancarIten();
				limparCampos();
				selecionarItens();
			}
		});

	}

	int numItem = 0;

	private void lancarIten() {
		if (!getProduto().getText().isEmpty() && !getQuantidade().getText().isEmpty()
				&& !getValorUnitario().getText().isEmpty() && !getTotalIten().getText().isEmpty()) {
			getTabelaItens().getItems().add(selecionarItens());
			numItem++;
			getVenda().setTotalFinal(getTabelaItens().getItems().get(numItem - 1).getTotalItenDouble());
			atualizarIndiceEsoma();

			// HABILITA OS BOTÕES AO LANÇAR A PRIMEIRA VENDA
			getBtnSalvar().setDisable(false);
			getBtnDesconto().setDisable(false);
			getBtnConcluirDepois().setDisable(false);
			limparCampos();

		}
	}

	public void removerIten(int index) {
		getTabelaItens().getItems().remove(index);
		atualizarIndiceEsoma();

	}

	public void atualizarIndiceEsoma() {
		Double soma = 0D;
		for (int i = 0; i < getTabelaItens().getItems().size(); i++) {
			getTabelaItens().getItems().get(i).setId(i);
			soma += getTabelaItens().getItems().get(i).getTotalItenDouble();
			getVenda().setTotalFinal(soma);
		}
		atualizarTotalVenda();

	}

	public void atualizarTotalVenda() {
		getTotalVenda().setText(realFormato.format(getVenda().getTotalFinalDouble()));

	}

	public void atualizarQuantidade(int index, int quantidade) {
		ItensVendaDto iten = getTabelaItens().getItems().get(index);
		iten.setQuantidade(quantidade);
		iten.getSpinner().getValueFactory().setValue(iten.getQuantidade());
		iten.setTotalIten(iten.getValorUnitarioDouble() * iten.getQuantidade());
		atualizarIndiceEsoma();
		getTabelaItens().refresh();

	}

	public void criarVenda() {
		getVenda().setCliente(clienteSelecionado);
		getVenda().setDataVenda(LocalDateTime.now());
		getVenda().setItens(getTabelaItens().getItems());
		getVenda().setMeioPgto(null);
		getVenda().setStatus(null);
		getVenda().setTotalFinal(
				Double.valueOf(getTotalIten().getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
		getVenda().setTroco(null);
		getVenda().setUsuario(null);

	}

	public void fecharTabelaPesquisaProduto() {
		getPainelPesquisa().setVisible(false);
		limparCampos();
	}

	public void fecharTabelaPesquisaCliente() {
		getPainelPesquisa().setVisible(false);
		getClienteText().setVisible(false);
		getClienteText().clear();

	}

	private void limparCampos() {
		getProduto().clear();
		getQuantidade().clear();
		getValorUnitario().clear();
		getTotalIten().clear();
		getValorUnitario().setStyle(null);
		getTotalIten().setStyle(null);
		setPesquisaProduto("");
		getRoot().requestFocus();
	}

	private void posicionarTabelaProdutos() throws Exception {
		getPainelPesquisa().setLayoutX(0);
		getPainelPesquisa().setLayoutY(160);
		getPainelPesquisa().setLayoutX(10);

	}

	// CARREGA TABELA COM TODOS CLIENTES
	public void clicarCampoPesquisaProdutos(MouseEvent event) throws Exception {
		getClienteText().setVisible(false);
		getPainelPesquisa().setVisible(true);
		posicionarTabelaProdutos();
		getPainelPesquisa().setLeft(construirTabelaProdutosPesquisa());
		popularTabelaPesquisaAllProdutos();

		// SELECIONA O PRODUTO ATRAVÉZ DO DUPLO CLIQUE
		getTabelaProdutosPesquisa().setOnMouseClicked((mouseEvent) -> {
			if (mouseEvent.getClickCount() == 2) {
				selecionarItens();
			}
		});

		// SELECIONA O PRODUTO ATRAVÉZ DA TECLA ENTER
		getTabelaProdutosPesquisa().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				selecionarItens();
			}
		});
	}

	public void pesquisarProduto(KeyEvent event) throws Exception {
		String produto = "";
		setPesquisaProduto(getProduto().getText().replace(" ", "+"));
		posicionarTabelaProdutos();
		getPainelPesquisa().setLeft(construirTabelaProdutosPesquisa());
		popularTabelaPesquisaProdutos(getPesquisaProduto());
		produto += getPesquisaProduto();
		// PESQUISA CLIENTES POR ID
		if (produto.matches("[0-9]+")) {
			popularTabelaPesquisaProdutosByCodigo(produto);

		}
		else {
			popularTabelaPesquisaProdutos(getPesquisaProduto());
		}

		// SELECIONA O PRODUTO ATRAVÉZ DO DUPLO CLIQUE
		getTabelaProdutosPesquisa().setOnMouseClicked((mouseEvent) -> {
			if (mouseEvent.getClickCount() == 2) {
				selecionarItens();
			}
		});

		// SELECIONA O PRODUTO ATRAVÉZ DA TECLA ENTER
		getTabelaProdutosPesquisa().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				selecionarItens();
			}
		});

		if (event.getCode() == KeyCode.ESCAPE) {
			fecharTabelaPesquisaProduto();
		}
	}

	private ItensVendaDto selecionarItens() {
		iten = new ItensVendaDto();
		iten.setId(numItem);
		iten.setProduto(getTabelaProdutosPesquisa().getSelectionModel().getSelectedItem());
		iten.setCodigo(iten.getProduto().getCodigo());
		iten.setDescricao(iten.getProduto().getDescricao());
		iten.setValorUnitario(iten.getProduto().getValorVendas());
		iten.setQuantidade(1);
		iten.setTotalIten(iten.getQuantidade() * iten.getValorUnitarioDouble());
		getProduto().setText(iten.getProduto().getDescricao());
		getQuantidade().setText("1");
		getQuantidade().requestFocus();
		getQuantidade().setEditable(true);
		getQuantidade().deselect();
		getPainelPesquisa().setVisible(false);
		return iten;
	}

	private void selecionarCliente() {
		setClienteSelecionado(getTabelaClientesPesquisa().getSelectionModel().getSelectedItem());
		getClienteText().setText(getClienteSelecionado().getName());
		getClienteLabel().setText(clienteSelecionado.getName());
		getPainelPesquisa().setVisible(false);
		getClienteText().setVisible(false);
		getClienteLabel().setVisible(true);
	}

	public void pesquisarCliente(KeyEvent event) throws Exception {
		String pesquisa = "";
		setPesquisaCliente(getClienteText().getText().replace(" ", "+"));
		posicionarTabelaClientes();
		getPainelPesquisa().setLeft(construirTabelaCientesPesquisa());
		pesquisa += getClienteText().getText().replace(" ", "+");

		// PESQUISA CLIENTES POR ID
		if (pesquisa.matches("[0-9]+")) {
			popularTabelaPesquisaClienteById(Integer.parseInt(pesquisa));

		}
		// PESQUISA CLIENTES POR DESCRIÇÃO
		else {
			popularTabelaPesquisaClientes(getPesquisaCliente());
		}

		// SELECIONA O CLIENTE ATRAVÉZ DO DUPLO CLIQUE
		getTabelaClientesPesquisa().setOnMouseClicked((mouseEvent) -> {
			if (mouseEvent.getClickCount() == 2) {
				selecionarCliente();
			}
		});

		// SELECIONA O CLIENTE ATRAVÉZ DA TECLA ENTER
		getTabelaClientesPesquisa().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				selecionarCliente();
			}
		});

		if (event.getCode() == KeyCode.ESCAPE) {
			fecharTabelaPesquisaCliente();
		}

		// SELECIONA CLIENTE ATRAVÉZ DO DUPLO CLIQUE
		getTabelaClientesPesquisa().setOnMouseClicked((mouseEvent) -> {
			if (mouseEvent.getClickCount() == 2) {
				selecionarCliente();
			}
		});

	}

	public void posicionarTabelaClientes() throws Exception {
		getClienteText().setVisible(true);
		getPainelPesquisa().setLayoutX(210);
		getPainelPesquisa().setLayoutY(60);
		getClienteText().setPrefWidth(d.getWidth() / 2);
		getClienteLabel().setPrefWidth(d.getWidth() / 2);

	}

	/**
	 * Popula a tabela de produtos com a descrição passada por parâmetro
	 * 
	 * @param descricao
	 * @throws Exception
	 */
	private void popularTabelaPesquisaProdutos(String descricao) throws Exception {
		List<ProdutoDto> produtos = gui.Controllers.ProdutoControllers.ProdutosController
				.pesquisarProdutoByDescricao(descricao);
		setObservableListProduto(FXCollections.observableArrayList(produtos));
		getTabelaProdutosPesquisa().setItems(getObservableListProduto());
	}
	
	/**
	 * Popula a tabela de produtos com código ean_gtin
	 * @param codigo
	 * @throws Exception
	 */
	private void popularTabelaPesquisaProdutosByCodigo(String codigo) throws Exception {
		List<ProdutoDto> produtos = gui.Controllers.ProdutoControllers.ProdutosController
				.pesquisarProdutoByCodigo(codigo);
		if(produtos.isEmpty()){
			getTabelaProdutosPesquisa().setPlaceholder(new Label("Nenhum Produto localizado com o Código Ean/Gtin \"" + codigo + "\""));
		}
		setObservableListProduto(FXCollections.observableArrayList(produtos));
		getTabelaProdutosPesquisa().setItems(getObservableListProduto());
	}

	/**
	 * Popula a tabela de produtos com todos registros
	 * 
	 * @throws Exception
	 */
	private void popularTabelaPesquisaAllProdutos() throws Exception {
		List<ProdutoDto> produtos = gui.Controllers.ProdutoControllers.ProdutosController.getAllProducts();
		setObservableListProduto(FXCollections.observableArrayList(produtos));
		getTabelaProdutosPesquisa().setItems(getObservableListProduto());
	}

	/**
	 * Popula a tabela de clientes com a descrição passada por parâmetro
	 * 
	 * @param descricao
	 * @throws Exception
	 */
	private void popularTabelaPesquisaClientes(String descricao) throws Exception {
		List<ClienteDto> clientes = gui.Controllers.ClienteControllers.ClientesController
				.pesquisarClientesByDescricao(descricao);
		setObservableListCliente(FXCollections.observableArrayList(clientes));
		getTabelaClientesPesquisa().setItems(getObservableListCliente());
	}

	/**
	 * Popula a tabela de clientes pesquisando por id
	 * 
	 * @param id
	 * @throws Exception
	 */
	private void popularTabelaPesquisaClienteById(int id) throws Exception {
		ClienteDto cliente = gui.Controllers.ClienteControllers.ClientesController.pesquisarClienteById(id);
		if (cliente == null) {
			getTabelaClientesPesquisa()
					.setPlaceholder(new Label("Nenhum Cliente localizado com o Código \"" + id + "\""));
		} else {
			getTabelaClientesPesquisa().getItems().add(cliente);
		}
	}

	/**
	 * Popula a tabela de clientes com todos registros
	 * 
	 * @throws Exception
	 */
	private void popularTabelaPesquisaAllClientes() throws Exception {
		List<ClienteDto> clientes = gui.Controllers.ClienteControllers.ClientesController.getAllClients();
		setObservableListCliente(FXCollections.observableArrayList(clientes));
		getTabelaClientesPesquisa().setItems(getObservableListCliente());
	}

	/**
	 * Contrói a tabela de itens
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TableView<ItensVendaDto> construirTabelaItens() throws Exception {
		setTabelaItens(new TableView<ItensVendaDto>());

		TableColumn<ItensVendaDto, Integer> colunaID = new TableColumn<ItensVendaDto, Integer>("Iten nº");
		colunaID.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, Integer>("id"));
		colunaID.setPrefWidth(((d.getWidth()) * 5) / 100);

		TableColumn<ItensVendaDto, String> colunaCodigo = new TableColumn<ItensVendaDto, String>("Código");
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, String>("codigo"));
		colunaCodigo.setPrefWidth(((d.getWidth()) * 10) / 100);

		TableColumn<ItensVendaDto, String> colunaDescricao = new TableColumn<ItensVendaDto, String>("Descrição");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, String>("descricao"));
		colunaDescricao.setPrefWidth(((d.getWidth()) * 44) / 100);

		TableColumn<ItensVendaDto, Spinner<Integer>> colunaQuantidade = new TableColumn<ItensVendaDto, Spinner<Integer>>(
				"Quantidade");
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, Spinner<Integer>>("spinner"));
		colunaQuantidade.setPrefWidth(((d.getWidth()) * 10) / 100);

		TableColumn<ItensVendaDto, String> colunaValor = new TableColumn<ItensVendaDto, String>("Valor Unitário");
		colunaValor.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, String>("valorUnitario"));
		colunaValor.setPrefWidth(((d.getWidth()) * 10) / 100);

		TableColumn<ItensVendaDto, String> colunaTotalFinal = new TableColumn<ItensVendaDto, String>("Total do Iten");
		colunaTotalFinal.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, String>("totalIten"));
		colunaTotalFinal.setPrefWidth(((d.getWidth()) * 10) / 100);

		TableColumn<ItensVendaDto, Button> colunaExcluir = new TableColumn<ItensVendaDto, Button>("Excluir Iten");
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<ItensVendaDto, Button>("excluir"));
		colunaExcluir.setPrefWidth(((d.getWidth()) * 10) / 100);

		// ADICIONA AS COLUNAS
		getTabelaItens().getColumns().addAll(colunaID, colunaCodigo, colunaDescricao, colunaQuantidade, colunaValor,
				colunaTotalFinal, colunaExcluir);

		if (getTabelaItens().getItems().isEmpty()) {
			getTabelaItens().setPlaceholder(new Label("Nenhum item adicionado a venda!."));
		}
		getTabelaItens().getStylesheets().add("styleTableItens.css");

		return getTabelaItens();
	}

	/**
	 * Constrói a tabela de pesquisa de produtos
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TableView<ProdutoDto> construirTabelaProdutosPesquisa() throws Exception {
		setTabelaProdutosPesquisa(new TableView<ProdutoDto>());

		TableColumn<ProdutoDto, String> colunaCodigo = new TableColumn<ProdutoDto, String>("Código");
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("EAN_GTIN"));
		colunaCodigo.setPrefWidth(((d.getWidth() / 2) * 20) / 100);

		TableColumn<ProdutoDto, String> colunaDescricao = new TableColumn<ProdutoDto, String>("Descrição");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("descricao"));
		colunaDescricao.setPrefWidth(((d.getWidth() / 2) * 50) / 100);

		TableColumn<ProdutoDto, String> colunaEstoque = new TableColumn<ProdutoDto, String>("Estoque");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("estoque"));
		colunaEstoque.setPrefWidth(((d.getWidth() / 2) * 12.5) / 100);

		TableColumn<ProdutoDto, String> colunaValor = new TableColumn<ProdutoDto, String>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<ProdutoDto, String>("valorVenda"));
		colunaValor.setPrefWidth(((d.getWidth() / 2) * 12.5) / 100);

		// ADICIONA AS COLUNAS
		getTabelaProdutosPesquisa().getColumns().addAll(colunaCodigo, colunaDescricao, colunaEstoque, colunaValor);

		if (getTabelaProdutosPesquisa().getItems().isEmpty()) {
			getTabelaProdutosPesquisa().setPlaceholder(new Label("Nenhum produto localizado!."));
		}

		getTabelaProdutosPesquisa().setPrefHeight(d.getHeight() - 260);
		getTabelaProdutosPesquisa().setLayoutX(5);
		getTabelaProdutosPesquisa().setPrefWidth(d.getWidth() / 2);
		getTabelaProdutosPesquisa().setPrefWidth(d.getWidth() / 2);

		return getTabelaProdutosPesquisa();
	}

	/**
	 * Constrói a tablea de pesquisa de clientes
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TableView<ClienteDto> construirTabelaCientesPesquisa() throws Exception {
		setTabelaClientesPesquisa(new TableView<ClienteDto>());

		TableColumn<ClienteDto, String> colunaCodigo = new TableColumn<ClienteDto, String>("Código");
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("id"));
		colunaCodigo.setPrefWidth(((d.getWidth() / 2) * 10) / 100);

		TableColumn<ClienteDto, String> colunaDescricao = new TableColumn<ClienteDto, String>("Nome");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("name"));
		colunaDescricao.setPrefWidth(((d.getWidth() / 2) * 48) / 100);

		TableColumn<ClienteDto, String> colunaTipo = new TableColumn<ClienteDto, String>("Tipo");
		colunaTipo.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("tipo"));
		colunaTipo.setPrefWidth(((d.getWidth() / 2) * 13) / 100);

		TableColumn<ClienteDto, String> colunaCpf_Cnpj = new TableColumn<ClienteDto, String>("CPF / CNPJ");
		colunaCpf_Cnpj.setCellValueFactory(new PropertyValueFactory<ClienteDto, String>("cpf_cnpj"));
		colunaCpf_Cnpj.setPrefWidth(((d.getWidth() / 2) * 25) / 100);

		// ADICIONA AS COLUNAS
		getTabelaClientesPesquisa().getColumns().addAll(colunaCodigo, colunaDescricao, colunaTipo, colunaCpf_Cnpj);

		if (getTabelaClientesPesquisa().getItems().isEmpty()) {
			getTabelaClientesPesquisa().setPlaceholder(new Label("Nenhum cliente localizado!."));
		}

		getTabelaClientesPesquisa().setPrefHeight(d.getHeight());
		getTabelaClientesPesquisa().setPrefWidth(d.getWidth() / 2);
		getTabelaClientesPesquisa().setPrefWidth(d.getWidth() / 2);

		return getTabelaClientesPesquisa();
	}

	// ---------------------- FUNÇÕES DOS BOTÕES --------------------------------

	/**
	 * Função bottão Cliente
	 * 
	 * @param action
	 * @throws Exception
	 */
	public void btnCliente(ActionEvent action) throws Exception {
		getClienteLabel().setVisible(false);
		limparCampos();
		getPainelPesquisa().setVisible(true);
		posicionarTabelaClientes();
		getPainelPesquisa().setLeft(construirTabelaCientesPesquisa());
		popularTabelaPesquisaAllClientes();
	}

	/**
	 * Função bottão Sair
	 * 
	 * @param action
	 */
	public void sair(ActionEvent action) {
		// VERIFICA SE HÁ ITENS
		if (!getTabelaItens().getItems().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(getRoot().getScene().getWindow());
			alert.setTitle("BR-Emissor - Vendas");
			alert.setHeaderText(null);
			alert.setContentText("Há itens na venda. Deseja sair ?");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.CANCEL) {
				alert.close();
				return;
			} else {
				Stage stage = (Stage) getBtnSair().getScene().getWindow();
				stage.close();
			}
		} else {
			Stage stage = (Stage) getBtnSair().getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * Função botão Salvar
	 * 
	 * @param action
	 */
	public void btnSalvar(ActionEvent action) {

	}

	/**
	 * Função botão Desconto
	 * 
	 * @param action
	 * @throws IOException
	 */
	public void btnDesconto(ActionEvent action) throws IOException {

		if (!getTabelaItens().getItems().isEmpty()) {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(App.class.getResource("VendaViews/Desconto.fxml"));
			Scene scene = new Scene(painel, 400, 250);
			stage.setTitle("Desconto");
			stage.setScene(scene);
			stage.initOwner(getRoot().getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		}
	}

	public void btnConcluirDepois(ActionEvent action) {

	}
}
