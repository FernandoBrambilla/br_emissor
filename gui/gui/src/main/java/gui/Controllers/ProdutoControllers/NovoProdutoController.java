package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import org.json.JSONObject;

import gui.App;
import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Controllers.TributacaoControllers.NCMController;
import gui.Controllers.TributacaoControllers.TributacaoController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.MarkupDto;
import gui.Dtos.NcmDto;
import gui.Dtos.ProdutoDto;
import gui.Dtos.Style;
import gui.Dtos.UnidadeProdutoDto;
import gui.Utilities.Mascaras;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
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

public class NovoProdutoController {

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	private static ObservableList<CategoriaProdutoDto> listCategorias;

	private static BorderPane checkBoxMarkupPane;

	private static CheckBox checkBox;

	private static BorderPane categoriaPane;

	private static ComboBox<CategoriaProdutoDto> categoria;

	private static ComboBox<UnidadeProdutoDto> unidade;

	private static MarkupDto markup = new MarkupDto();

	private static NcmDto ncm = new NcmDto();

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
	private TextField estoque;

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

	public static ComboBox<UnidadeProdutoDto> getUnidade() {
		return unidade;
	}

	public static MarkupDto getMarkup() {
		return markup;
	}

	public static void setUnidade(ComboBox<UnidadeProdutoDto> unidade) {
		NovoProdutoController.unidade = unidade;
	}

	public static void setMarkup(MarkupDto markup) {
		NovoProdutoController.markup = markup;
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

	public TextField getEstoque() {
		return estoque;
	}

	public void setEstoque(TextField estoque) {
		this.estoque = estoque;
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
		NovoProdutoController.listCategorias = listCategorias;
	}

	@SuppressWarnings("exports")
	public static void setCheckBoxMarkupPane(BorderPane checkBoxMarkupPane) {
		NovoProdutoController.checkBoxMarkupPane = checkBoxMarkupPane;
	}

	@SuppressWarnings("exports")
	public static void setCheckBox(CheckBox checkBox) {
		NovoProdutoController.checkBox = checkBox;
	}

	@SuppressWarnings("exports")
	public static void setCategoriaPane(BorderPane categoriaPane) {
		NovoProdutoController.categoriaPane = categoriaPane;
	}

	public static void setCategoria(ComboBox<CategoriaProdutoDto> categoria) {
		NovoProdutoController.categoria = categoria;
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
		NovoProdutoController.markupText = markupText;
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
		// INICIA MARKUP AO CARREGAR A TELA
		markup = MarkupPadraoController.buscarMarkup();
		ncm = NCMController.ncmSelecionado;

		// SETA MÁSCARAS NOS CAMPOS
		Mascaras.monetaryField(getValor());
		Mascaras.monetaryField(getCusto());
		Mascaras.numericField(getEstoque());

		// CRIA E ADICIONA NA TELA CAMPOS
		getRoot().getChildren().add(3, criarComboBoxCategorias());
		getRoot().getChildren().add(4, criarComboBoxUnidades());
		getRoot().getChildren().add(7, criarMarkupText());
		getRoot().getChildren().add(8, criarCheckBoxMarkup());

		// SELECIONA COMBOBOX SE UTILIZAR MARKUP
		getMarkupText().setEditable(markup.isUtilizar() ? false : true);

		// CARREGA TELA MARKUP SE AÇÃO NO COMBOBOX
		getCheckBox().setOnAction(e -> {
			try {
				mostrarTelaMarkup(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// MUDA FOCUS PARA DESCRIÇÃO
		getEan_getin().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getDescricao().requestFocus();
		});

		// MUDA FOCUS PARA CATEGORIA E ABRE A ABA
		getDescricao().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getCategoria().requestFocus();
				getCategoria().show();
			}
		});

		// MUDA FOCUS PARA UNIDADE E ABRE A ABA
		getCategoria().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getUnidade().requestFocus();
				getUnidade().show();
			}
		});

		// MUDA FOCUS PARA CUSTO
		getUnidade().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getCusto().requestFocus();
			}

		});

		// CALCULA VALOR DE VENDA QUANDO FOCUS SAI DO CAMPO CUSTO
		getCusto().focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue) {
					calcularValorVenda();
				}
			}
		});

		// CALCULA PERCENTUAL DE MARKUP QUANDO FOCUS SAI DO VALOR VENDA
		getValor().focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue) {
					if (!getCheckBox().isSelected())
						calcularMarkup();
				}
			}
		});

		// MUDA FOCUS PARA VALOR E CALCULA VALOR DE VENDA E MARKUP
		getCusto().setOnKeyPressed((keyEvent) -> {
			if (getAuto().isSelected()) {
				calcularValorVenda();
			}
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getValor().requestFocus();
				calcularValorVenda();
				calcularMarkup();
			}
		});

		// MUDA FOCUS PARA CHECKBOX AUTOMÁTICO
		getValor().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getAuto().requestFocus();
				if (!markup.isUtilizar())
					calcularMarkup();
			}
		});

		// MUDA FOCUS PARA VALOR MARKUP
		getAuto().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getMarkupText().requestFocus();

			}
		});

		// MUDA FOCUS PARA BOTÃO BARKUP
		getMarkupText().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getBtnMarkup().requestFocus();
				calcularValorVenda();
			}
		});

		// MUDA FOCUS PARA ESTOQUE
		getBtnMarkup().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getEstoque().requestFocus();
		});

		// MUDA FOCUS PARA OBSERVAÇÕES
		getEstoque().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getObs().requestFocus();
		});

		// MUDA FOCUS PARA BOTÃO SALVAR
		getObs().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getBtnSalvar().requestFocus();
		});

		// EVENTO ATRAVÉS DAS TECLAS F2 OU ESC
		getRoot().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.F2)
				try {
					criarProduto();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			if (keyEvent.getCode() == KeyCode.ESCAPE)
				fecharTela();
		});
	}

	@SuppressWarnings("exports")
	public static TextField criarMarkupText() throws Exception {
		setMarkupText(new TextField());
		getMarkupText().setAlignment(Pos.CENTER_RIGHT);
		getMarkupText().setId("markupText");
		getMarkupText().setLayoutX(140);
		getMarkupText().setLayoutY(275);
		getMarkupText().setPrefHeight(30);
		getMarkupText().setPrefWidth(190);
		getMarkupText().setText(markup.isUtilizar() ? markup.getMarkup().toString() + " %" : "");
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
	 * Atualiza lista de categorias
	 * 
	 * @throws Exception
	 */
	public static void atualizarListaCategorias() throws Exception {
		int indice = getCategoria().getItems().size();
		do {
			indice--;
			getCategoria().getItems().remove(indice);
		} while (indice > 1);
		getCategoria().getItems().addAll(NovaCategoriaController.buscarCategoriasProduto());
	}

	/**
	 * Atualiza lista de unidades
	 * 
	 * @throws Exception
	 */
	public static void atualizarListaUnidades() throws Exception {
		int indice = getUnidade().getItems().size();
		do {
			indice--;
			getUnidade().getItems().remove(indice);
		} while (indice > 1);
		getUnidade().getItems().addAll(NovaUnidadeController.buscarUnidadesProduto());
	}

	/**
	 * Cria uma skin de botões no comboBox categoria
	 * 
	 * @return ComboBoxListViewSkin<CategoriaProdutoDto>
	 */
	public static ComboBoxListViewSkin<CategoriaProdutoDto> criarSkinCategoria() {
		getCategoria().getItems().add(0, null);
		ComboBoxListViewSkin<CategoriaProdutoDto> skin = new ComboBoxListViewSkin<CategoriaProdutoDto>(getCategoria());
		skin.setHideOnClick(false);
		getCategoria().setCellFactory(lv -> new ListCell<>() {
			private ButtonBar buttons = criarButtonBarCategorias();

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

	public static ComboBoxListViewSkin<UnidadeProdutoDto> criarSkinUnidade() {
		getUnidade().getItems().add(0, null);
		ComboBoxListViewSkin<UnidadeProdutoDto> skin = new ComboBoxListViewSkin<UnidadeProdutoDto>(getUnidade());
		skin.setHideOnClick(false);
		getUnidade().setCellFactory(lv -> new ListCell<>() {
			private ButtonBar buttons = criarButtonBarUnidade();

			@Override
			protected void updateItem(UnidadeProdutoDto item, boolean empty) {
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
		getCategoria().setPrefWidth(230);
		getCategoria().setPrefHeight(30);
		getCategoria().getItems().addAll(NovaCategoriaController.buscarCategoriasProduto());
		getCategoria().setSkin(criarSkinCategoria());
		return getCategoria();
	}

	/**
	 * Cria o ComboBox de unidade
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ComboBox<UnidadeProdutoDto> criarComboBoxUnidades() throws Exception {
		setUnidade(new ComboBox<UnidadeProdutoDto>());
		getUnidade().setLayoutX(450);
		getUnidade().setLayoutY(125);
		getUnidade().setPrefWidth(230);
		getUnidade().setPrefHeight(30);
		getUnidade().getItems().addAll(NovaUnidadeController.buscarUnidadesProduto());
		getUnidade().setSkin(criarSkinUnidade());
		return getUnidade();
	}

	/**
	 * Estiliza os botões do ComboBox
	 * 
	 * @param button
	 */
	private static void estilizarButton(Button button) {
		Style style = new Style();
		style.hoverBtnsInternos(button);
		Font font = new Font("Calibri", 12);
		button.setFont(font);
		button.setStyle("-fx-background-color: white;");

	}

	/**
	 * Cria os botões no ComboBox e chama suas funcões
	 * 
	 * @return ButtonBar
	 */
	private static ButtonBar criarButtonBarCategorias() {
		ButtonBar buttonBarCat = new ButtonBar();
		Button buttonNovoCat = new Button("Novo");
		estilizarButton(buttonNovoCat);
		Button buttonEditarCat = new Button("Editar");
		estilizarButton(buttonEditarCat);
		Button buttonApagarCat = new Button("Apagar");
		estilizarButton(buttonApagarCat);
		ButtonBar.setButtonData(buttonNovoCat, null);
		ButtonBar.setButtonData(buttonEditarCat, null);
		ButtonBar.setButtonData(buttonApagarCat, null);
		buttonBarCat.getButtons().addAll(buttonApagarCat, buttonEditarCat, buttonNovoCat);
		buttonBarCat.setStyle("-fx-background-color:  #2484BF; -fx-border-color: #2484BF; -fx-padding: 6;");
		buttonBarCat.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		// buttonBar.setPrefHeight(30);
		// buttonBar.setPrefWidth(50);

		// AÇÃO DO BOTÃO NOVA CATEGORIA
		buttonNovoCat.setOnAction(e -> {
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
		buttonEditarCat.setOnAction(e -> {
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
		buttonApagarCat.setOnAction(e -> {
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

		});
		return buttonBarCat;
	}

	private static ButtonBar criarButtonBarUnidade() {
		ButtonBar buttonBarUni = new ButtonBar();
		Button buttonNovoUni = new Button("Novo");
		estilizarButton(buttonNovoUni);
		Button buttonEditarUni = new Button("Editar");
		estilizarButton(buttonEditarUni);
		Button buttonApagarUni = new Button("Apagar");
		estilizarButton(buttonApagarUni);
		ButtonBar.setButtonData(buttonNovoUni, null);
		ButtonBar.setButtonData(buttonEditarUni, null);
		ButtonBar.setButtonData(buttonApagarUni, null);
		buttonBarUni.getButtons().addAll(buttonApagarUni, buttonEditarUni, buttonNovoUni);
		buttonBarUni.setStyle("-fx-background-color:  #2484BF; -fx-border-color: #2484BF; -fx-padding: 6;");
		buttonBarUni.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		// buttonBar.setPrefHeight(30);
		// buttonBar.setPrefWidth(50);

		// AÇÃO DO BOTÃO NOVA UNIDADE
		buttonNovoUni.setOnAction(e -> {
			Stage stage = new Stage();
			Parent painel = null;
			try {
				painel = FXMLLoader.load(App.class.getResource("ProdutoViews/NovaUnidade.fxml"));
				Scene scene = new Scene(painel, 500, 200);
				stage.setTitle("Cadasto de Unidades de medidas de Produtos");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// AÇÃO DO BOTÃO EDITAR UNIDADE
		buttonEditarUni.setOnAction(e -> {
			Stage stage = new Stage();
			Parent painel = null;
			try {
				if (getUnidade().getSelectionModel().getSelectedItem() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Selecione uma Unidade para para editar.");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					getCategoria().show();
					return;
				}
				painel = FXMLLoader.load(App.class.getResource("ProdutoViews/EditarUnidade.fxml"));
				Scene scene = new Scene(painel, 500, 200);
				stage.setTitle("Editar Unidade");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// AÇÃO DO BOTÃO APAGAR UNIDADE
		buttonApagarUni.setOnAction(e -> {
			System.out.println("apagar");
			try {
				if (getUnidade().getSelectionModel().getSelectedItem() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Selecione uma Unidade para apagar.");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					getCategoria().show();
					return;
				} else {
					UnidadeProdutoDto unidadeParaApagar = getUnidade().getSelectionModel().getSelectedItem();

					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Deseja apagar a unidade \"" + unidadeParaApagar.getDescricao() + "\"?");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.CANCEL) {
						alert.close();
						return;
					} else {
						NovaUnidadeController.apagarUnidade(unidadeParaApagar);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		buttonApagarUni.setOnAction(e -> {
			try {
				if (getUnidade().getSelectionModel().getSelectedItem() == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Selecione uma Unidade para apagar.");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					getUnidade().show();
					return;
				} else {
					UnidadeProdutoDto unidadeParaApagar = getUnidade().getSelectionModel().getSelectedItem();
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Deseja apagar a unidade \"" + unidadeParaApagar.getDescricao() + "\"?");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.CANCEL) {
						alert.close();
						return;
					} else {
						NovaUnidadeController.apagarUnidade(unidadeParaApagar);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		return buttonBarUni;
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
		if (!getMarkupText().getText().isEmpty() && !getCusto().getText().isEmpty()) {
			Double percentualMarkup = Double.parseDouble(getMarkupText().getText().replace(",", ".").replace("%", ""));
			percentualMarkup = percentualMarkup / 100;
			Double custo = Double.parseDouble(getCusto().getText().replace("R$ ", "").replace(",", "."));
			Double venda = (custo * percentualMarkup) + custo;
			getValor().setText("R$ " + String.valueOf(Mascaras.monetario(venda)));
		} else {
			return;
		}

	}

	private void calcularMarkup() {
		getMarkupText().clear();
		if (!getValor().getText().isEmpty()) {
			Double custo = Double.parseDouble(getCusto().getText().replace("R$ ", "").replace(",", "."));
			Double venda = Double.parseDouble(getValor().getText().replace("R$ ", "").replace(",", "."));
			Double percentual = ((venda - custo) * 100) / custo;
			getMarkupText().setText(Mascaras.percentual(percentual) + " %");
		}
	}

	/**
	 * Salva Produto no Banco de dados
	 * 
	 * @throws Exception
	 */

	public void criarProduto() throws Exception {
		Style style = new Style();
		if (getDescricao().getText().isEmpty()) {
			getInfo().setText("Campo Obrigatório!");
			style.campoObrigatorio(getDescricao());
		} else {
			style.campoObrigatorioRemove(getDescricao());
			getInfo().setText(null);

			// CRIAR NOVO PRODUTO
			ProdutoDto p = new ProdutoDto();
			
			//SETA EAN_GETIN COM VAZIO CASO NAO SEJA PASSADO VALOR
			p.setEAN_GTIN(getEan_getin().getText().isEmpty() ? "" : getEan_getin().getText());
			
			p.setDescricao(getDescricao().getText());
			
			p.setCodigo("00789");
			
			//SETA VALOR DE VENDA COM 0 CASO NAO SEJA PASSADO VALOR
			p.setValorVenda(getValor().getText().isEmpty() ? 0d
					: Double.parseDouble(getValor().getText().replace("R$ ", "").replace(",", ".")));
			
			//SETA CUSTO COM 0 CASO NAO SEJA PASSADO VALOR
			p.setCusto(getCusto().getText().isEmpty() ? 0d
					: Double.parseDouble(getCusto().getText().replace("R$ ", "").replace(",", ".")));
			
			System.out.println(p.getCusto());
			
			//SETA ESTOQUE COM 0 CASO NÃO SEJA PASSADO VALOR
			p.setEstoque(getEstoque().getText().isBlank() ? 0 : Integer.parseInt(getEstoque().getText()));

			// SELECIONA UNIDADE VAZIA OU A SELECIONADA
			p.setUnidadeProduto(getUnidade().getSelectionModel().getSelectedItem() == null
					? NovaUnidadeController.buscarUnidadeProduto(1)
					: getUnidade().getSelectionModel().getSelectedItem());

			// SELECIONA CATEGORIA VAZIA OU A SELECIONADA
			p.setCategoria(getCategoria().getSelectionModel().getSelectedItem() == null
					? NovaCategoriaController.buscarCategoriaProduto(1)
					: getCategoria().getSelectionModel().getSelectedItem());
			
			p.setMarkup(getMarkup());
			p.setFornecedor("Reval");
			p.setTributacao("1");
			p.setObs(getObs().getText().isEmpty() ? "" : getObs().getText());

			// SETA NCM VAZIO CASO NAO SEJA INFORMADO NENHUM NCM
			p.setNcm(TributacaoController.tributacaoController.getNcm().getText() == null ? TributacaoController.ncmS
					: NCMController.ncmSelecionado);

			p.setCest("");
			p.setDataInclusao(LocalDateTime.now());

			try {
				JSONObject unidade = new JSONObject();
				unidade.put("id", p.getUnidadeProduto().getId());

				JSONObject markup = new JSONObject();
				markup.put("id", p.getMarkup().getId());

				JSONObject categoria = new JSONObject();
				categoria.put("id", p.getCategoria().getId());

				JSONObject ncm = new JSONObject();
				ncm.put("id", 3);

				JSONObject json = new JSONObject();
				json.put("descricao", p.getDescricao());
				json.put("codigo", p.getCodigo());
				json.put("valorVenda", p.getValorVenda().replace("R$ ", "").replace(",", "."));
				json.put("custo", p.getCusto().replace("R$ ", "").replace(",", "."));
				json.put("estoque", p.getEstoque());
				json.put("utilizarMarkup", p.getMarkup().isUtilizar());
				json.put("unidadeProduto", unidade);
				json.put("markup", markup);
				json.put("categoria", categoria);
				json.put("fornecedor", 1); // <<<<<<<<<<<<<<<<<<<<<<<VER FORNCECEDOR
				json.put("tributacao", 1);
				json.put("ncm", ncm);
				json.put("cest", 1);
				json.put("dataInclusao", p.getDataInclusao());
				json.put("status", 1);
				json.put("ean_GTIN", p.getEAN_GTIN());
				json.put("obs", p.getObs());
				System.out.println(json);

				// REQUIÇÃO
				String endpoint = url + "products";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String message = response.body();
				int status = response.statusCode();
				System.out.println(response.body());

				if (status == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Produto \"" + getDescricao().getText() + "\" cadastrado com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) btnCancelar.getScene().getWindow();
					stage.close();

					// ATUALIZA A TABELA
					ProdutosController.popularTabela();

				} else {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText(
							"Produto \"" + getDescricao().getText() + "\" não cadastrado! Motivo: " + message);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@SuppressWarnings("exports")
	public void calcularValorVendaAutomatico(ActionEvent action) {
		calcularValorVenda();
	}

	private void fecharTela() {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		criarProduto();
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		fecharTela();
	}

}
