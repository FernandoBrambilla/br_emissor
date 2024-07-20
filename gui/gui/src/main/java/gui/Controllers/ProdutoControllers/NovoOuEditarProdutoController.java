package gui.Controllers.ProdutoControllers;

import java.awt.Cursor;
import java.io.IOException;
import java.math.BigDecimal;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Markup;
import gui.Dtos.Style;
import gui.Utilities.Mascaras;
import javafx.application.Application;
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
import javafx.scene.input.KeyCode;
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

	private static Markup markup = new Markup();

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

	private static TextField markupText;

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
	public Button getBtnMarkup() {
		return btnMarkup;
	}

	@SuppressWarnings("exports")
	public static TextField getMarkupText() {
		return markupText;
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

	@SuppressWarnings("exports")
	public static void setCheckBoxMarkupPane(BorderPane checkBoxMarkupPane) {
		NovoOuEditarProdutoController.checkBoxMarkupPane = checkBoxMarkupPane;
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
	public static void setMarkupText(TextField markupText) {
		NovoOuEditarProdutoController.markupText = markupText;
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
		markup = MarkupPadraoController.buscarMarkup();
		Mascaras.monetaryField(getValor());
		Mascaras.monetaryField(getCusto());

		getRoot().getChildren().add(criarComboBoxCategorias());
		getRoot().getChildren().add(criarCheckBoxMarkup());
		getRoot().getChildren().add(criarMarkupText());
		getMarkupText().setEditable(markup.isUtilizar() ? false : true);

		getCheckBox().setOnAction(e -> {
			try {
				mostrarTelaMarkup(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		getEan_getin().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getDescricao().requestFocus();
		});

		getDescricao().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				getCategoria().requestFocus();
				getCategoria().show();
			}
		});

		getCategoria().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getCusto().requestFocus();
		});

		getCusto().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getValor().requestFocus();
		});

		getValor().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getAuto().requestFocus();
		});

		getAuto().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				calcularValorVenda();
			getMarkupText().requestFocus();
		});

		getMarkupText().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getBtnMarkup().requestFocus();
		});

		getBtnMarkup().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getObs().requestFocus();
		});

		getObs().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER)
				getBtnSalvar().requestFocus();
		});

		getBtnSalvar().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.F2)
				System.out.println("savar");
				getBtnSalvar().arm();
				//salvarProduto();
		});

	}

	@SuppressWarnings("exports")
	public static TextField criarMarkupText() throws Exception {
		setMarkupText(new TextField());
		Mascaras.onlyDecimal(getMarkupText());
		getMarkupText().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		getMarkupText().setId("markupText");
		getMarkupText().setLayoutX(140);
		getMarkupText().setLayoutY(275);
		getMarkupText().setPrefHeight(30);
		getMarkupText().setPrefWidth(190);
		getMarkupText().setText(markup.isUtilizar() ? Mascaras.decimal(markup.getMarkup()) : "");
		getMarkupText().setEditable(markup.isUtilizar() ? false : true);
		getMarkupText().setFont(new Font("Calibri", 15));
		return getMarkupText();
	}

	/**
	 * Carrega tela de gerenciamento de Markup
	 * 
	 * @param action
	 * @throws IOException
	 */
	@SuppressWarnings("exports")
	public void mostrarTelaMarkup(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(App.class.getResource("ProdutoViews/MarkupPadrao.fxml"));
		Scene scene = new Scene(painel, 450, 300);
		stage.setTitle("Margem de Lucro");
		stage.setScene(scene);
		stage.show();
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
	 * /** Calcula Valor de vanda automaticamento utilizando o markup
	 * 
	 */
	private void calcularValorVenda() {
		Style style = new Style();
		if (getMarkupText().getText().isEmpty() || getCusto().getText().isEmpty()) {
			getInfo().setText(
					"Para calcular o valor de venda automaticamente é obrigatório fornecer o custo e o % markup!");

			style.campoObrigatorio(getCusto());
			style.campoObrigatorio(getMarkupText());
			getAuto().setSelected(false);
			return;
		}
		style.campoObrigatorioRemove(getCusto());
		style.campoObrigatorioRemove(getMarkupText());
		getInfo().setText("");
		if (getAuto().isSelected()) {
			BigDecimal percentualMarkup = new BigDecimal(getMarkupText().getText().replace(",", "."));
			percentualMarkup = percentualMarkup.divide(new BigDecimal(100));
			BigDecimal custo = new BigDecimal(getCusto().getText().replace(",", "."));
			BigDecimal venda = custo.add(custo.multiply(percentualMarkup));
			getValor().setText(Mascaras.decimal(venda));
		}

	}
	/**
	 * Salva Produto no Banco de dados
	 */
	public void salvarProduto() {
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
	public void calcularValorVendaAutomatico(ActionEvent action) {
		calcularValorVenda();

	}
	
	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		salvarProduto();
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
