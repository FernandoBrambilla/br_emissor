package gui.Controllers.ProdutoControllers;

import java.io.IOException;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Style;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NovoOuEditarProdutoController {

	private static String token = PrincipalController.getAccessToken();

	private static String url = PrincipalController.getUrl();

	private static ObservableList<CategoriaProdutoDto> listCategorias;

	private static BorderPane checkBoxMarkupPane;

	private static CheckBox checkBox;

	private static BorderPane categoriaPane;

	private static ComboBox<String> categoria;

	@FXML
	private Pane root;

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

	public static BorderPane getCheckBoxMarkupPane() {
		return checkBoxMarkupPane;
	}

	public static CheckBox getCheckBox() {
		return checkBox;
	}

	public static BorderPane getCategoriaPane() {
		return categoriaPane;
	}

	public static ComboBox<String> getCategoria() {
		return categoria;
	}

	public Pane getRoot() {
		return root;
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

	public TextArea getObs() {
		return obs;
	}

	public Label getInfo() {
		return info;
	}

	public static void setListCategorias(ObservableList<CategoriaProdutoDto> listCategorias) {
		NovoOuEditarProdutoController.listCategorias = listCategorias;
	}

	public static void setCheckBoxMarkupPane(BorderPane checkBoxMarkupPane) {
		NovoOuEditarProdutoController.checkBoxMarkupPane = checkBoxMarkupPane;
	}

	public static void setCheckBox(CheckBox checkBox) {
		NovoOuEditarProdutoController.checkBox = checkBox;
	}

	public static void setCategoriaPane(BorderPane categoriaPane) {
		NovoOuEditarProdutoController.categoriaPane = categoriaPane;
	}

	public static void setCategoria(ComboBox<String> categoria) {
		NovoOuEditarProdutoController.categoria = categoria;
	}

	public void setRoot(Pane root) {
		this.root = root;
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

	public void setBtnMarkup(Button btnMarkup) {
		this.btnMarkup = btnMarkup;
	}

	public void setObs(TextArea obs) {
		this.obs = obs;
	}

	public void setInfo(Label info) {
		this.info = info;
	}

	public void initialize() throws Exception {
		getRoot().getChildren().add(criarCheckBoxMarkup());
		getRoot().getChildren().add(criarComboBoxCategorias());
		getCategoria().setSkin(criarSkin());

	}

	@SuppressWarnings("exports")
	public void mostrarTelaMarkup(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MarkupPadrao.fxml"));
		Scene scene = new Scene(painel, 450, 300);
		stage.setTitle("Margem de Lucro");
		stage.setScene(scene);
		stage.show();

	}
	
	public static ComboBoxListViewSkin<String> criarSkin() {
		getCategoria().getItems().add(0, null);
		ComboBoxListViewSkin<String> skin = new ComboBoxListViewSkin<String>(getCategoria());
		skin.setHideOnClick(false);
		getCategoria().setCellFactory(lv -> new ListCell<>() {
			private ButtonBar buttons = criarButtonBar();
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText("");
					setGraphic(null);
				} else if (item == null) {
					setText("");
					setGraphic(buttons);
				} else {
					setText(item);
					setGraphic(null);
				}
			}
		});
		return skin;
	}

	public  static ComboBox<String> criarComboBoxCategorias() throws Exception {
		setCategoria(new ComboBox<String>());
		getCategoria().setLayoutX(140);
		getCategoria().setLayoutY(125);
		getCategoria().setPrefWidth(245);
		getCategoria().setPrefHeight(30);
		getCategoria().setItems(AddCategoriaController.buscarCategoriasProduto());
		return getCategoria();
	}

	private static void estilizarButton(Button button) {
		Style style = new Style();
		style.hoverBtnsInternos(button);
		Font font = new Font("Calibri", 14);
		button.setFont(font);
		button.setStyle("-fx-background-color: white; ");

	}

	private static ButtonBar criarButtonBar() {

		ButtonBar buttonBar = new ButtonBar();
		Button buttonNovo = new Button("Novo");
		estilizarButton(buttonNovo);
		Button buttonEditar = new Button("Editar");
		estilizarButton(buttonEditar);
		Button buttonApagar = new Button("Apagar");
		estilizarButton(buttonApagar);
		ButtonBar.setButtonData(buttonNovo, null);
		ButtonBar.setButtonData(buttonEditar, null);
		ButtonBar.setButtonData(buttonApagar, null);
		buttonBar.getButtons().addAll(buttonNovo,buttonEditar, buttonApagar);
		//buttonBar.setPrefHeight(40);
		//buttonBar.setPrefWidth(110);
		

		buttonNovo.setOnAction(e -> {
			Stage stage = new Stage();
			Parent painel = null;
			try {
				painel = FXMLLoader.load(App.class.getResource("ProdutoViews/AddCategoria.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene scene = new Scene(painel, 500, 200);
			stage.setTitle("Cadasto de Categorias de Produtos");
			stage.setScene(scene);
			stage.show();
		});

		return buttonBar;
	}

	@SuppressWarnings("exports")
	public static BorderPane criarCheckBoxMarkup() throws Exception {
		setCheckBox(new CheckBox());
		getCheckBox().setSelected(MarkupPadraoController.buscarMarkup().isUtilizar() ? true : false);
		getCheckBox().setFont(new Font("Calibri", 15));
		setCheckBoxMarkupPane(new BorderPane());
		getCheckBoxMarkupPane().setLayoutX(330);
		getCheckBoxMarkupPane().setLayoutY(275);
		getCheckBoxMarkupPane().setPrefHeight(30);
		getCheckBoxMarkupPane().setPrefWidth(40);
		getCheckBoxMarkupPane().setVisible(true);
		getCheckBoxMarkupPane().setCenter(checkBox);
		return getCheckBoxMarkupPane();
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



}
