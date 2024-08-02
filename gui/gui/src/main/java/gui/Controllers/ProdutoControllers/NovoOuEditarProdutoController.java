package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import gui.App;
import gui.Controllers.ClienteControllers.ClientesController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.MarkupDto;
import gui.Dtos.Style;
import gui.Dtos.UnidadeProdutoDto;
import gui.Utilities.Mascaras;
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

public class NovoOuEditarProdutoController {

	private static ObservableList<CategoriaProdutoDto> listCategorias;

	private static BorderPane checkBoxMarkupPane;

	private static CheckBox checkBox;

	private static BorderPane categoriaPane;

	private static ComboBox<CategoriaProdutoDto> categoria;

	private static ComboBox<UnidadeProdutoDto> unidade;

	private static MarkupDto markup = new MarkupDto();

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

	public static ComboBox<UnidadeProdutoDto> getUnidade() {
		return unidade;
	}

	public static MarkupDto getMarkup() {
		return markup;
	}

	public static void setUnidade(ComboBox<UnidadeProdutoDto> unidade) {
		NovoOuEditarProdutoController.unidade = unidade;
	}

	public static void setMarkup(MarkupDto markup) {
		NovoOuEditarProdutoController.markup = markup;
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

		getRoot().getChildren().add(3, criarComboBoxCategorias());
		getRoot().getChildren().add(4, criarComboBoxUnidades());
		getRoot().getChildren().add(7, criarMarkupText());
		getRoot().getChildren().add(8, criarCheckBoxMarkup());

		getMarkupText().setEditable(markup.isUtilizar() ? false : true);
		getCheckBox().setOnAction(e -> {
			try {
				mostrarTelaMarkup(e);
			} catch (IOException e1) {
				e1.printStackTrace(); 
			}
		});

		getRoot().setOnKeyReleased((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.F2)
				salvarProduto();
		});

		getRoot().setOnKeyReleased((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE)
				fecharTela();
		});

		getEan_getin().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getDescricao().requestFocus();
		});

		getDescricao().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getCategoria().requestFocus();
				getCategoria().show();
			}
		});

		getCategoria().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getUnidade().requestFocus();
				getUnidade().show();
			}
		});

		getUnidade().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getCusto().requestFocus();
			}
		});

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

		getValor().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getAuto().requestFocus();
				if (!markup.isUtilizar())
					calcularMarkup();
			}

		});

		getAuto().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getMarkupText().requestFocus();

			}
		});

		getMarkupText().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
				getBtnMarkup().requestFocus();
				calcularValorVenda();
			}
 
		});

		getBtnMarkup().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getObs().requestFocus();
		});

		getObs().setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB)
				getBtnSalvar().requestFocus();
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
			BigDecimal custo = new BigDecimal(getCusto().getText().replace(".", "").replace(",", "."));
			BigDecimal venda = custo.add(custo.multiply(new BigDecimal(percentualMarkup)));
			getValor().setText(Mascaras.decimal(venda));
		} else {
			return;
		}

	}

	private void calcularMarkup() {
		getMarkupText().clear();
		if (!getValor().getText().isEmpty()) {
			BigDecimal custo = new BigDecimal(getCusto().getText().replace(".", "").replace(",", "."));
			BigDecimal venda = new BigDecimal(getValor().getText().replace(".", "").replace(",", "."));
			Double percentual = venda.subtract(custo).doubleValue();
			percentual = percentual * 100;
			percentual = percentual / custo.doubleValue();
			getMarkupText().setText(Mascaras.percentual(percentual) + " %");
		}
	}

	/**
	 * Salva Produto no Banco de dados
	 */
	public void salvarProduto() {
		Style style = new Style();
		if (getEan_getin().getText().isEmpty() || getDescricao().getText().isEmpty()) {
			getInfo().setText("Campos Obrigatórios!");
			
			style.campoObrigatorio(getEan_getin());
			style.campoObrigatorio(getDescricao());
		}else {
			style.campoObrigatorioRemove(getEan_getin());
			style.campoObrigatorioRemove(getDescricao());
			getInfo().setText(null);
			/*
			JSONObject json = new JSONObject();
			json.put("name", getName().getText()); 
			json.put("tipo", tipo);
			json.put("phone", getPhone().getText());
			json.put("email", getEmail().getText());
			json.put("cpf_cnpj", getCpf_cnpj().getText());
			json.put("rg_ie", getRg_ie().getText());
			json.put("dateNasc_const", dateNascConst.format(formatter));
			json.put("dateExp", dateExp.format(formatter));
			json.put("address", getAdress().getText());
			json.put("addressNumber", getNum().getText());
			json.put("addressComplement", getCompl().getText());
			json.put("city", getCity().getText());
			json.put("bairro" , getBairro().getText());
			json.put("uf", getUf().getValue());
			json.put("cep", getCep().getText());
			json.put("obs", getObs().getText());

			// REQUIÇÃO
			String url = "http://localhost:8080/clients";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
 					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String message = response.body();
			int status = response.statusCode();

			if (status == 200) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setContentText("Cliente \"" + getName().getText() + "\" cadastrado com sucesso! ");
				alert.showAndWait();
				Stage stage = (Stage) btnCancelar.getScene().getWindow();
				stage.close();

				// ATUALIZA A TABELA
				ClientesController.popularTabela();
			} else {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Cliente \"" + getName().getText() + "\" não cadastrado! Motivo: " + message);
				alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert.showAndWait();

			}
		}
	} catch (Exception e) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Erro. " + e.getMessage() + e.getCause());
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
		*/
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
		salvarProduto();
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		fecharTela();
	}

}
