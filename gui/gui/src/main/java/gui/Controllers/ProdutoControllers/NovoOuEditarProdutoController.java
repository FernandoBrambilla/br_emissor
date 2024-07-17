package gui.Controllers.ProdutoControllers;

import java.io.IOException;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Markup;
import gui.Dtos.Style;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NovoOuEditarProdutoController {

	private static ObservableList<CategoriaProdutoDto> listCategorias;

	private static BorderPane checkBoxMarkupPane;

	private static CheckBox checkBox;

	private static BorderPane categoriaPane;

	private static ComboBox<CategoriaProdutoDto> categoria;

	public static NovoOuEditarProdutoController novoProdutoController;

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

	@SuppressWarnings("exports")
	public static BorderPane getCheckBoxMarkupPane() {
		return checkBoxMarkupPane;
	}

	@SuppressWarnings("exports")
	public static CheckBox getCheckBox() {
		return checkBox;
	}

	@SuppressWarnings("exports")
	public static BorderPane getCategoriaPane() {
		return categoriaPane;
	}

	public static ComboBox<CategoriaProdutoDto> getCategoria() {
		return categoria;
	}

	@SuppressWarnings("exports")
	public Pane getRoot() {
		return root;
	}

	@SuppressWarnings("exports")
	public Button getBtnSalvar() {
		return btnSalvar;
	}

	@SuppressWarnings("exports")
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	@SuppressWarnings("exports")
	public TextField getId() {
		return id;
	}

	@SuppressWarnings("exports")
	public TextField getEan_getin() {
		return ean_getin;
	}

	@SuppressWarnings("exports")
	public TextField getDescricao() {
		return descricao;
	}

	@SuppressWarnings("exports")
	public TextField getValor() {
		return valor;
	}

	@SuppressWarnings("exports")
	public CheckBox getAuto() {
		return auto;
	}

	@SuppressWarnings("exports")
	public TextField getCusto() {
		return custo;
	}

	@SuppressWarnings("exports")
	public TextField getMarkupText() {
		return markupText;
	}

	@SuppressWarnings("exports")
	public Button getBtnMarkup() {
		return btnMarkup;
	}

	@SuppressWarnings("exports")
	public TextArea getObs() {
		return obs;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	public static void setListCategorias(ObservableList<CategoriaProdutoDto> listCategorias) {
		NovoOuEditarProdutoController.listCategorias = listCategorias;
	}

	public static NovoOuEditarProdutoController getNovoProdutoController() {
		return novoProdutoController;
	}

	public static void setCheckBoxMarkupPane(BorderPane checkBoxMarkupPane) {
		NovoOuEditarProdutoController.checkBoxMarkupPane = checkBoxMarkupPane;
	}

	public static void setNovoProdutoController(NovoOuEditarProdutoController novoProdutoController) {
		NovoOuEditarProdutoController.novoProdutoController = novoProdutoController;
	}

	@SuppressWarnings("exports")
	public static void setCheckBox(CheckBox checkBox) {
		NovoOuEditarProdutoController.checkBox = checkBox;
	}

	@SuppressWarnings("exports")
	public static void setCategoriaPane(BorderPane categoriaPane) {
		NovoOuEditarProdutoController.categoriaPane = categoriaPane;
	}

	public static void setCategoria(ComboBox<CategoriaProdutoDto> categoria) {
		NovoOuEditarProdutoController.categoria = categoria;
	}

	@SuppressWarnings("exports")
	public void setRoot(Pane root) {
		this.root = root;
	}

	@SuppressWarnings("exports")
	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@SuppressWarnings("exports")
	public void setId(TextField id) {
		this.id = id;
	}

	@SuppressWarnings("exports")
	public void setEan_getin(TextField ean_getin) {
		this.ean_getin = ean_getin;
	}

	@SuppressWarnings("exports")
	public void setDescricao(TextField descricao) {
		this.descricao = descricao;
	}

	@SuppressWarnings("exports")
	public void setValor(TextField valor) {
		this.valor = valor;
	}

	@SuppressWarnings("exports")
	public void setAuto(CheckBox auto) {
		this.auto = auto;
	}

	@SuppressWarnings("exports")
	public void setCusto(TextField custo) {
		this.custo = custo;
	}

	@SuppressWarnings("exports")
	public void setMarkupText(TextField markupText) {
		this.markupText = markupText;
	}

	@SuppressWarnings("exports")
	public void setBtnMarkup(Button btnMarkup) {
		this.btnMarkup = btnMarkup;
	}

	@SuppressWarnings("exports")
	public void setObs(TextArea obs) {
		this.obs = obs;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	public void initialize() throws Exception {
		getRoot().getChildren().add(criarCheckBoxMarkup());
		getRoot().getChildren().add(criarComboBoxCategorias());
		carregarDados();

	}

	@FXML
	protected void carregarDados() throws Exception {
		Markup markup = new Markup(MarkupPadraoController.buscarMarkup());
		TextField valor = new TextField();
		valor.setText(markup.isUtilizar() ? markup.getMarkup().toString() : "");
		getMarkupText().textProperty().bind(valor.textProperty());
	}

	/**
	 * Carrega tela de gerenciamento de Markup
	 * 
	 * @param action
	 * @throws IOException
	 */
	@SuppressWarnings("exports")
	public void mostrarTelaMarkup(ActionEvent action) throws IOException {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Stage stage = new Stage();
				Parent painel = null;
				try {
					painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MarkupPadrao.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene = new Scene(painel, 450, 300);
				stage.setTitle("Margem de Lucro");
				stage.setScene(scene);
				stage.show();

			}
		});

	}

	/**
	 * Atualiza lista de Categorias do Produto
	 * 
	 * @throws Exception
	 */

	public static void atualizarLista() throws Exception {
		getCategoria().getItems().clear();
		getCategoria().setSkin(null);
		getCategoria().getItems().addAll(NovaCategoriaController.buscarCategoriasProduto());
		getCategoria().setSkin(criarSkin());
	}

	/**
	 * Cria uma skin de botões no comboBox categoria
	 * 
	 * @return ComboBoxListViewSkin<CategoriaProdutoDto>
	 */
	private static ComboBoxListViewSkin<CategoriaProdutoDto> criarSkin() {
		getCategoria().getItems().add(0, null);
		ComboBoxListViewSkin<CategoriaProdutoDto> skin = new ComboBoxListViewSkin<CategoriaProdutoDto>(getCategoria());
		skin.setHideOnClick(false);
		getCategoria().setCellFactory(lv -> new ListCell<>() {
			private ButtonBar buttons = criarButtonBar();

			@Override
			protected void updateItem(CategoriaProdutoDto item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText("");
					setGraphic(null);
				} else if (item == null) {
					setText("");
					setGraphic(buttons);
				} else {
					setText(item.getDescricao());
					setGraphic(null);
				}
			}
		});
		return skin;
	}

	/**
	 * Cria o ComboBox de categorias
	 * 
	 * @return ComboBox<CategoriaProdutoDto>
	 * @throws Exception
	 */
	public static ComboBox<CategoriaProdutoDto> criarComboBoxCategorias() throws Exception {
		setCategoria(new ComboBox<CategoriaProdutoDto>());
		getCategoria().setLayoutX(140);
		getCategoria().setLayoutY(125);
		getCategoria().setPrefWidth(245);
		getCategoria().setPrefHeight(30);
		getCategoria().getItems().addAll(NovaCategoriaController.buscarCategoriasProduto());
		getCategoria().setSkin(criarSkin());
		return getCategoria();
	}

	/**
	 * Estiliza os botões do ComboBox
	 * 
	 * @param button
	 */
	private static void estilizarButton(Button button) {
		Style style = new Style();
		style.hoverBtnsInternos(button);
		Font font = new Font("Calibri", 14);
		button.setFont(font);
		button.setStyle("-fx-background-color: white;");

	}

	/**
	 * Cria os botões no ComboBox e chama suas funcões
	 * 
	 * @return ButtonBar
	 */
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
		buttonBar.getButtons().addAll(buttonApagar, buttonEditar, buttonNovo);
		buttonBar.setStyle("-fx-background-color:  #2484BF; -fx-border-color: #2484BF; -fx-padding:20;");
		buttonBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		buttonBar.setPrefHeight(45);
		buttonBar.setPrefWidth(110);

		// AÇÃO DO BOTÃO NOVA CATEGORIA
		buttonNovo.setOnAction(e -> {
			Stage stage = new Stage();
			Parent painel = null;
			try {
				painel = FXMLLoader.load(App.class.getResource("ProdutoViews/NovaCategoria.fxml"));
				Scene scene = new Scene(painel, 500, 200);
				stage.setTitle("Cadasto de Categorias de Produtos");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// AÇÃO DO BOTÃO EDITAR CATEGORIA
		buttonEditar.setOnAction(e -> {
			Stage stage = new Stage();
			Parent painel = null;
			try {
				if (getCategoria().getSelectionModel().getSelectedItem() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Selecione uma Categoria para para editar.");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					getCategoria().show();
					return;
				}
				painel = FXMLLoader.load(App.class.getResource("ProdutoViews/EditarCategoria.fxml"));
				Scene scene = new Scene(painel, 500, 200);
				stage.setTitle("Editar Categoria");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// AÇÃO DO BOTÃO APAGAR CATEGORIA
		buttonApagar.setOnAction(e -> {
			try {
				if (getCategoria().getSelectionModel().getSelectedItem() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Selecione uma Categoria para apagar.");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					getCategoria().show();
					return;
				} else {
					CategoriaProdutoDto categoriaParaApagar = getCategoria().getSelectionModel().getSelectedItem();

					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Deseja apagar a categoria \"" + categoriaParaApagar.getDescricao() + "\"?");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.CANCEL) {
						alert.close();
						return;
					} else {
						NovaCategoriaController.apagarCategoria(categoriaParaApagar);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		// AÇÃO AO CLICAR EM UMA CATEGORIA
		getCategoria().setOnAction(e -> {
			try {
				getCategoria().getConverter().fromString("");
				getCategoria().hide();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		return buttonBar;
	}

	/**
	 * Cria o CheckBox do markup
	 * 
	 * @return BorderPane
	 * @throws Exception
	 */
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

	/**
	 * Calcula Valor de vanda automaticamento utilizando o markup
	 * 
	 * @param action
	 */
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

	/**
	 * Salva Produto no Banco de dados
	 * 
	 * @param action
	 * @throws Exception
	 */
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

	/**
	 * Fecha a tela manipulação de produtos
	 * 
	 * @param action
	 * @throws IOException
	 */
	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
