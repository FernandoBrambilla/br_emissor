package gui.Controllers.ProdutoControllers;

import java.awt.Event;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.swing.Action;

import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Markup;
import gui.Dtos.Style;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NovoOuEditarProdutoController {

	private static String token = PrincipalController.getAccessToken();

	private static String url = PrincipalController.getUrl();

	private static ObservableList<CategoriaProdutoDto> listCategorias;

	@FXML
	private BorderPane campoBtnMarkup;
	
	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField id;

	@FXML
	private TextField ean_getin;

	@FXML
	private TextField descricao;

	@FXML
	private ComboBox<String> categoria;

	@FXML
	private Button btnAddCategoria;

	@FXML
	private TextField valor;

	@FXML
	private CheckBox auto;

	@FXML
	private TextField custo;

	@FXML
	private TextField markupText;

	@FXML
	private Button btnMarkup;

	@FXML
	private TextArea obs;

	@FXML
	private Label info;

	public static ObservableList<CategoriaProdutoDto> getListCategorias() {
		return listCategorias;
	}

	public static void setListCategorias(ObservableList<CategoriaProdutoDto> listCategorias) {
		NovoOuEditarProdutoController.listCategorias = listCategorias;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public TextField getId() {
		return id;
	}

	public TextField getEan_getin() {
		return ean_getin;
	}

	public TextField getDescricao() {
		return descricao;
	}

	public ComboBox<String> getCategoria() {
		return categoria;
	}

	public Button getBtnAddCategoria() {
		return btnAddCategoria;
	}

	public TextField getValor() {
		return valor;
	}

	public CheckBox getAuto() {
		return auto;
	}

	public TextField getCusto() {
		return custo;
	}

	public TextField getMarkupText() {
		return markupText;
	}

	public Button getBtnMarkup() {
		return btnMarkup;
	}

	public void setBtnMarkup(Button btnMarkup) {
		this.btnMarkup = btnMarkup;
	}

	public TextArea getObs() {
		return obs;
	}

	public Label getInfo() {
		return info;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setId(TextField id) {
		this.id = id;
	}

	public void setEan_getin(TextField ean_getin) {
		this.ean_getin = ean_getin;
	}

	public void setDescricao(TextField descricao) {
		this.descricao = descricao;
	}

	public void setCategoria(ComboBox<String> categoria) {
		this.categoria = categoria;
	}

	public void setBtnAddCategoria(Button btnAddCategoria) {
		this.btnAddCategoria = btnAddCategoria;
	}

	public void setValor(TextField valor) {
		this.valor = valor;
	}

	public void setAuto(CheckBox auto) {
		this.auto = auto;
	}

	public void setCusto(TextField custo) {
		this.custo = custo;
	}

	public void setMarkupText(TextField markupText) {
		this.markupText = markupText;
	}

	public void setObs(TextArea obs) {
		this.obs = obs;
	}

	public void setInfo(Label info) {
		this.info = info;
	}
	
	public void initialize() throws Exception {
		Button button = criarBtnMarkup();
		campoBtnMarkup.setCenter(button);
		button.setOnAction(seguirMarkupPadrao());
		
		 
		
		/*
		 * // EXECUTADO SOMENTE SE FOR SELECIONADO UM PRODUTO if
		 * (ProdutosController.tabelaprodutos.getSelectionModel().getSelectedItem() !=
		 * null) { ProdutoDto produtoParaEditar = new ProdutoDto(
		 * ProdutosController.tabelaprodutos.getSelectionModel().getSelectedItem());
		 * getEan_getin().setText(produtoParaEditar.getEAN_GTIN());
		 * getDescricao().setText(produtoParaEditar.getDescricao());
		 * //produtoParaEditar.getCategoria().getDescricao().equals(null) ?
		 * criarNovaCategoriaProduto("") :
		 * produtoParaEditar.getCategoria().getDescricao());
		 * getValor().setText(produtoParaEditar.getValorVenda().toString());
		 * getCusto().setText(produtoParaEditar.getCusto().toString());
		 * getDescricao().setText(produtoParaEditar.getDescricao());
		 * 
		 * } else { /* getCategoria().setItems(preencherListaDeCategorias());
		 * 
		 * getMarkupPadrao().setSelected(getMarkupSalvo().isUtilizar()); getMarkup();
		 */

	}
	
	
	private static Button criarBtnMarkup() throws Exception {
		Button button = new Button();
		button.setId("btnMarkup");
		button.setLayoutX(340);
		button.setLayoutY(275);
		button.setMnemonicParsing(false);
		button.setPrefHeight(30);
		button.setPrefWidth(187);
		button.setStyle("-fx-background-color: white;");
		button.setText("Seguir Markup Padrão");
		button.setFont(new Font("Calibri", 15));
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(MarkupPadraoController.buscarMarkup().isUtilizar() ? true : false);
		button.setGraphic(checkBox);
		return button;
	}


	@SuppressWarnings("unused")
	private void criarNovaCategoriaProduto(String descricao) throws Exception {
		CategoriaProdutoDto novaCategoria = new CategoriaProdutoDto(descricao);
		try {
			JSONObject json = new JSONObject();
			json.put("descricao", novaCategoria.getDescricao());
			String endpoint = url + "category";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar a nova Categoria do produto!");
		}
	}

	public static ObservableList<String> preencherListaDeCategorias() throws Exception {
		List<String> list = ProdutosController.buscarTodasCategoriasProduto();
		ObservableList<String> listaCategorias = FXCollections.observableArrayList(list);
		return listaCategorias;

	}

	public static EventHandler<ActionEvent> seguirMarkupPadrao() throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MarkupPadrao.fxml"));
		Scene scene = new Scene(painel, 450, 300);
		stage.setTitle("Margem de Lucro");
		stage.setScene(scene);
		stage.show();
		return null;
		
	}

	@SuppressWarnings("exports")
	public void calcularValorVendaAutomatico(ActionEvent action) {
		if (getMarkupText().getText().isEmpty() && getCusto().getText().isEmpty()) {
			getInfo().setText(
					"Para calcular o valor de venda automaticamente é obrigatório fornecer o custo e o % markup!");
			Style style = new Style();
			style.campoObrigatorio(getCusto());
			style.campoObrigatorio(getMarkupText());
			return;
		}
		if (getAuto().isSelected()) {
			Double markup = Double.parseDouble(getMarkupText().getText());
			Double custo = Double.parseDouble(getCusto().getText());
			Double venda = custo += custo * (markup / 100);
			getValor().setText(venda.toString());

		}

	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		if (getEan_getin().getText().isEmpty() || getDescricao().getText().isEmpty() || getValor().getText().isEmpty()
				|| getCusto().getText().isEmpty()) {
			getInfo().setText("Campos Obrigatórios!");
			Style style = new Style();
			style.campoObrigatorio(getEan_getin());
			style.campoObrigatorio(getDescricao());
			style.campoObrigatorio(getValor());
			style.campoObrigatorio(getCusto());
		}

	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

	public void btnAddCategoria(@SuppressWarnings("exports") ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("ProdutoViews/AddCategoria.fxml"));
		Scene scene = new Scene(painel, 500, 200);
		stage.setTitle("Cadasto de Categorias de Produtos");
		stage.setScene(scene);
		stage.show();

	}

}
