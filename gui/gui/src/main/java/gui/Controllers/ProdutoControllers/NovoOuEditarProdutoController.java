package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Markup;
import gui.Dtos.ProdutoDto;
import gui.Dtos.Style;
import gui.Utilities.Mascaras;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class NovoOuEditarProdutoController {

	private static String token = PrincipalController.getAccessToken();

	private static String url = PrincipalController.getUrl();

	private static ObservableList<CategoriaProdutoDto> listCategorias;

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
	private TextField markup;

	@FXML
	private CheckBox markupPadrao;

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

	public Button getBtnAddCategoria() {
		return btnAddCategoria;
	}

	public void setBtnAddCategoria(Button btnAddCategoria) {
		this.btnAddCategoria = btnAddCategoria;
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

	public TextField getValor() {
		return valor;
	}

	public CheckBox getAuto() {
		return auto;
	}

	public TextField getCusto() {
		return custo;
	}

	public TextField getMarkup() {
		return markup;
	}

	public CheckBox getMarkupPadrao() {
		return markupPadrao;
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

	public void setValor(TextField valor) {
		this.valor = valor;
	}

	public void setAuto(CheckBox auto) {
		this.auto = auto;
	}

	public void setCusto(TextField custo) {
		this.custo = custo;
	}

	public void setMarkup(TextField markup) {
		this.markup = markup;
	}

	public void setMarkupPadrao(CheckBox markupPadrao) {
		this.markupPadrao = markupPadrao;
	}

	public void setObs(TextArea obs) {
		this.obs = obs;
	}

	public void setInfo(Label info) {
		this.info = info;
	}

	public void initialize() throws Exception {
		Mascaras.monetaryField(getCusto());
		Mascaras.monetaryField(getValor());
		

		// EXECUTADO SOMENTE SE FOR SELECIONADO UM PRODUTO
		if (ProdutosController.tabelaprodutos.getSelectionModel().getSelectedItem() != null) {
			ProdutoDto produtoParaEditar = new ProdutoDto(
					ProdutosController.tabelaprodutos.getSelectionModel().getSelectedItem());
			getEan_getin().setText(produtoParaEditar.getEAN_GTIN());
			getDescricao().setText(produtoParaEditar.getDescricao());
			//produtoParaEditar.getCategoria().getDescricao().equals(null) ? criarNovaCategoriaProduto("")	: produtoParaEditar.getCategoria().getDescricao());
			getValor().setText(produtoParaEditar.getValorVenda().toString());
			getCusto().setText(produtoParaEditar.getCusto().toString());
			getDescricao().setText(produtoParaEditar.getDescricao());

		} else {
			getCategoria().setItems(preencherListaDeCategorias());

			getMarkupPadrao().setSelected(getMarkupSalvo().isUtilizar());
			getMarkup();
		}
	}

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

	private Markup getMarkupSalvo() throws Exception {

		try {
			MarkupPadraoController markupController = new MarkupPadraoController();
			Markup markup = markupController.buscarMarkup();
			if (markup.equals(null)) {
				return null;
			} else {
				return markup;
			}
		} catch (Exception e) {
			throw new Exception("Não foi localizado o Markup padrão!");
		}

	}

	public static ObservableList<String> preencherListaDeCategorias() throws Exception {
		List<String> list = ProdutosController.buscarTodasCategoriasProduto();
		ObservableList<String> listaCategorias = FXCollections.observableArrayList(list);
		return listaCategorias;

	}

	@SuppressWarnings("exports")
	public void seguirMarkupPadrao(ActionEvent action) throws IOException {
		if (getMarkupPadrao().isSelected()) {
			Stage stage = new Stage();
			Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MarkupPadrao.fxml"));
			Scene scene = new Scene(painel, 450, 300);
			stage.setTitle("Margem de Lucro");
			stage.setScene(scene);
			stage.show();

		}

	}

	@SuppressWarnings("exports")
	public void calcularValorVendaAutomatico(ActionEvent action) {
		if (getMarkup().getText().isEmpty() && getCusto().getText().isEmpty()) {
			getInfo().setText(
					"Para calcular o valor de venda automaticamente é obrigatório fornecer o custo e o % markup!");
			Style style = new Style();
			style.campoObrigatorio(getCusto());
			style.campoObrigatorio(getMarkup());
			return;
		}
		if (getAuto().isSelected()) {
			Double markup = Double.parseDouble(getMarkup().getText());
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
