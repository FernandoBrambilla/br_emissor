package gui.Controllers.TributacaoControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Controllers.ProdutoControllers.MenuNovoProdutoController;
import gui.Dtos.NcmDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NCMController {

	TributacaoController controller = TributacaoController.tributacaoController;

	public static NCMController ncmController;

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	private static TableView<NcmDto> tabelaNCM;

	public static ObservableList<NcmDto> observableList;

	public static NcmDto ncmSelecionado;

	List<NcmDto> allNcms = new ArrayList<>();

	double progresso;

	@FXML
	private TextField ncmPesquisa;

	@FXML
	private ImageView pesquisarNcm;

	@FXML
	private ProgressBar barraProgresso;

	@FXML
	private Label info;

	@FXML
	private Button importNcm;

	@FXML
	private BorderPane base;

	@FXML
	private Button btnSelecionar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnCancelarImport;

	@SuppressWarnings("exports")
	public ImageView getPesquisarNcm() {
		return pesquisarNcm;
	}

	@SuppressWarnings("exports")
	public Button getImportNcm() {
		return importNcm;
	}

	@SuppressWarnings("exports")
	public ProgressBar getBarraProgresso() {
		return barraProgresso;
	}

	@SuppressWarnings("exports")
	public void setBarraProgresso(ProgressBar barraProgresso) {
		this.barraProgresso = barraProgresso;
	}

	@SuppressWarnings("exports")
	public Button getBtnCancelarImport() {
		return btnCancelarImport;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelarImport(Button btnCancelarImport) {
		this.btnCancelarImport = btnCancelarImport;
	}

	@SuppressWarnings("exports")
	public BorderPane getBase() {
		return base;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	@SuppressWarnings("exports")
	public Button getBtnSelecionar() {
		return btnSelecionar;
	}

	@SuppressWarnings("exports")
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	@SuppressWarnings("exports")
	public TextField getNcmPesquisa() {
		return ncmPesquisa;
	}

	@SuppressWarnings("exports")
	public void setNcmPesquisa(TextField ncmPesquisa) {
		this.ncmPesquisa = ncmPesquisa;
	}

	@SuppressWarnings("exports")
	public void setPesquisarNcm(ImageView pesquisarNcm) {
		this.pesquisarNcm = pesquisarNcm;
	}

	@SuppressWarnings("exports")
	public void setImportNcm(Button importNcm) {
		this.importNcm = importNcm;
	}

	@SuppressWarnings("exports")
	public void setBase(BorderPane base) {
		this.base = base;
	}

	@SuppressWarnings("exports")
	public void setBtnSelecionar(Button btnSelecionar) {
		this.btnSelecionar = btnSelecionar;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public static TableView<NcmDto> getTabelaNCM() {
		return tabelaNCM;
	}

	public static ObservableList<NcmDto> getObservableList() {
		return observableList;
	}

	public static void setTabelaNCM(TableView<NcmDto> tabelaNCM) {
		NCMController.tabelaNCM = tabelaNCM;
	}

	public static void setObservableList(ObservableList<NcmDto> observableList) {
		NCMController.observableList = observableList;
	}

	public static String getUrl() {
		return url;
	}

	public static String getToken() {
		return token;
	}

	public static void setUrl(String url) {
		NCMController.url = url;
	}

	public static void setToken(String token) {
		NCMController.token = token;
	}

	List<NcmDto> ncms;
	int size = 0;
	double progressso = 0;

	@SuppressWarnings("unchecked")
	public void initialize() throws Exception {
		ncmController = this;
		ncmSelecionado = null;
		// CRIA UM NCM VAZIO

		// CONSTROI E PRENCHE A TABELA DE NCM
		getBase().setTop(construirTabela());

		// SELECIONA O NCM ATRAVÉ DO DUPLO CLIQUE
		getTabelaNCM().setOnMouseClicked((mouseEvent) -> {
			if (mouseEvent.getClickCount() == 2) {
				ncmSelecionado = getTabelaNCM().getSelectionModel().getSelectedItem();
				MenuNovoProdutoController.novoProduto.setNcm(ncmSelecionado);
				controller.getNcm().setText(ncmSelecionado.getNcm() + "   |   " + ncmSelecionado.getDescricao());
				Stage stage = (Stage) getBtnSelecionar().getScene().getWindow();
				stage.close();
			}
		});

		// ABRA A TELA DE IMPORTAR NCM ATRAVÉ DO BOTÃO IMPORTAR TABELA
		getImportNcm().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) getBase().getScene().getWindow();
				try {
					ncms = load(stage);
					allNcms = buscarNcms();
					if (ncms == null) {
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				HttpClient client = HttpClient.newHttpClient();
				String endpoint = url + "ncm";
				esconderControles();
				Service<Object> service = new Service<Object>() {
					@SuppressWarnings("rawtypes")
					@Override
					protected Task createTask() {
						return new Task() {
							@Override
							protected ObservableList<NcmDto> call() throws Exception {
								// SALVA TABELA CASO BANCO ESTEJA VAZIO
								if (allNcms.size() == 1) {
									JSONObject toSalvar = new JSONObject();
									for (int i = 0; i < ncms.size(); i++) {
										toSalvar.put("ncm", ncms.get(i).getNcm());
										toSalvar.put("ex", ncms.get(i).getEx());
										toSalvar.put("tipo", ncms.get(i).getTipo());
										toSalvar.put("descricao", ncms.get(i).getDescricao());
										toSalvar.put("nacionalfederal", ncms.get(i).getNacionalfederal());
										toSalvar.put("importadosfederal", ncms.get(i).getImportadosfederal());
										toSalvar.put("estadual", ncms.get(i).getEstadual());
										toSalvar.put("municipal", ncms.get(i).getMunicipal());
										toSalvar.put("vigenciainicio", ncms.get(i).getDataInicio());
										toSalvar.put("vigenciafim", ncms.get(i).getDataFim());
										toSalvar.put("chave", ncms.get(i).getChave());
										toSalvar.put("versao", ncms.get(i).getVersao());
										toSalvar.put("fonte", ncms.get(i).getFonte());
										HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
												.header("Authorization", "Bearer " + token)
												.header("Content-Type", "application/json")
												.POST(HttpRequest.BodyPublishers.ofString(toSalvar.toString())).build();
										HttpResponse<String> response = client.send(request,
												HttpResponse.BodyHandlers.ofString());
										size++;
										progresso = (size * 100) / (ncms.size());
										updateProgress(progresso, 100);
										if (response.statusCode() != 200) {
											System.out.println(response.body());
										}

									}
									mostrarControles();
									updateMessage("Finished.");
									popularTabela();
									return FXCollections.observableArrayList();
								} else {
									// ATUALIZA BANCO COM TABELA NOVA
									JSONObject toUpdate = new JSONObject();

									progresso = 0;
									NcmDto ncm;
									int tamanhoBanco = allNcms.size();
									int tamanhoTbela = ncms.size();
									for (int i = 0; i < allNcms.size(); i++) {
										ncm = new NcmDto(allNcms.get(i));
										toUpdate.put("id", ncm.getId());
										toUpdate.put("ncm", ncm.getNcm());
										toUpdate.put("ex", ncms.get(i).getEx());
										toUpdate.put("tipo", ncms.get(i).getTipo());
										toUpdate.put("descricao", ncms.get(i).getDescricao());
										toUpdate.put("nacionalfederal", ncms.get(i).getNacionalfederal());
										toUpdate.put("importadosfederal", ncms.get(i).getImportadosfederal());
										toUpdate.put("estadual", ncms.get(i).getEstadual());
										toUpdate.put("municipal", ncms.get(i).getMunicipal());
										toUpdate.put("vigenciainicio", ncms.get(i).getDataInicio());
										toUpdate.put("vigenciafim", ncms.get(i).getDataFim());
										toUpdate.put("chave", ncms.get(i).getChave());
										toUpdate.put("versao", ncms.get(i).getVersao());
										toUpdate.put("fonte", ncms.get(i).getFonte());
										HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
												.header("Authorization", "Bearer " + token)
												.header("Content-Type", "application/json")
												.PUT(HttpRequest.BodyPublishers.ofString(toUpdate.toString())).build();
										client.send(request, HttpResponse.BodyHandlers.ofString());
										tamanhoTbela--;
										tamanhoBanco--;
										size++;
										progresso = (size * 100) / (ncms.size());
										updateProgress(progresso, 100);

									}
									System.out.println("banco :" + tamanhoBanco);
									System.out.println("tabela: " + tamanhoTbela);
									if (tamanhoTbela != 0) {
										// JSONObject toSalvar = new JSONObject();
										for (int i = 0; i < ncms.size(); i++) {
											int posicao = ncms.size() - tamanhoTbela;
											System.out.println(posicao);
											/*
											 * toSalvar.put("ncm", ncms.get(i).getNcm()); toSalvar.put("ex",
											 * ncms.get(i).getEx()); toSalvar.put("tipo", ncms.get(i).getTipo());
											 * toSalvar.put("descricao", ncms.get(i).getDescricao());
											 * toSalvar.put("nacionalfederal", ncms.get(i).getNacionalfederal());
											 * toSalvar.put("importadosfederal", ncms.get(i).getImportadosfederal());
											 * toSalvar.put("estadual", ncms.get(i).getEstadual());
											 * toSalvar.put("municipal", ncms.get(i).getMunicipal());
											 * toSalvar.put("vigenciainicio", ncms.get(i).getDataInicio());
											 * toSalvar.put("vigenciafim", ncms.get(i).getDataFim());
											 * toSalvar.put("chave", ncms.get(i).getChave()); toSalvar.put("versao",
											 * ncms.get(i).getVersao()); toSalvar.put("fonte", ncms.get(i).getFonte());
											 * HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
											 * .header("Authorization", "Bearer " + token) .header("Content-Type",
											 * "application/json")
											 * .POST(HttpRequest.BodyPublishers.ofString(toSalvar.toString())).build();
											 * HttpResponse<String> response = client.send(request,
											 * HttpResponse.BodyHandlers.ofString());
											 */
										}

									}
									mostrarControles();
									updateMessage("Finished.");
									popularTabela();
									return FXCollections.observableArrayList();
								}
							}
						};
					}
				};
				getBarraProgresso().progressProperty().bind(service.progressProperty());
				service.stateProperty().addListener(new ChangeListener<Worker.State>() {
					@Override
					public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState,
							Worker.State newState) {
						if (newState == Worker.State.SUCCEEDED) {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setHeaderText(null);
							alert.setContentText("Tabela IBPT importada com sucesso! " + String.valueOf(size)
									+ " Ncm(s) importados.");
							alert.showAndWait();
							try {
								popularTabela();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
				service.start();
				getBtnCancelarImport().setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setHeaderText(null);
						alert.setContentText("Deseja cancelar a importação da tabela IBPT?");
						alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
						alert.showAndWait();
						if (alert.getResult() == ButtonType.CANCEL) {
							alert.close();
						}
						service.cancel();
						mostrarControles();
						try {
							popularTabela();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

	}

	public static void criarNcmVazio() throws IOException, InterruptedException {
		NcmDto ncmVazio = new NcmDto();
		ncmVazio.setChave("");
		ncmVazio.setDataFim(LocalDate.of(2100, 01, 01));
		ncmVazio.setDataInicio(LocalDate.of(1900, 01, 01));
		ncmVazio.setDescricao("NCM Não Informado");
		ncmVazio.setEstadual(0D);
		ncmVazio.setEx("");
		ncmVazio.setFonte("");
		ncmVazio.setImportadosfederal(0D);
		ncmVazio.setMunicipal(0D);
		ncmVazio.setNacionalfederal(0D);
		ncmVazio.setNcm(00000000L);
		ncmVazio.setTipo("");
		ncmVazio.setVersao("");

		JSONObject json = new JSONObject();
		json.put("ncm", ncmVazio.getNcm());
		json.put("ex", ncmVazio.getEx());
		json.put("tipo", ncmVazio.getTipo());
		json.put("descricao", ncmVazio.getDescricao());
		json.put("nacionalfederal", ncmVazio.getNacionalfederal());
		json.put("importadosfederal", ncmVazio.getImportadosfederal());
		json.put("estadual", ncmVazio.getEstadual());
		json.put("municipal", ncmVazio.getMunicipal());
		json.put("vigenciainicio", ncmVazio.getDataInicio());
		json.put("vigenciafim", ncmVazio.getDataFim());
		json.put("chave", ncmVazio.getChave());
		json.put("versao", ncmVazio.getVersao());
		json.put("fonte", ncmVazio.getFonte());

		HttpClient client = HttpClient.newHttpClient();
		String endpoint = url + "ncm";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
				.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
		client.send(request, HttpResponse.BodyHandlers.ofString());
	}

	private void esconderControles() {
		getBase().setVisible(false);
		getInfo().setVisible(true);
		getBarraProgresso().setVisible(true);
		getBtnSelecionar().setDisable(true);
		getImportNcm().setDisable(true);
		getNcmPesquisa().setEditable(false);
		getInfo().setText("Importando tabela IBPT. Aguarde...");
		getBtnCancelarImport().setVisible(true);

	}

	private void mostrarControles() {
		getBase().setVisible(true);
		getInfo().setVisible(false);
		getBarraProgresso().setVisible(false);
		getBtnSelecionar().setDisable(false);
		getImportNcm().setDisable(false);
		getNcmPesquisa().setEditable(true);
		getBtnCancelarImport().setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public TableView<NcmDto> construirTabela() throws Exception {

		setTabelaNCM(new TableView<NcmDto>());
		getTabelaNCM().setMaxHeight(340);

		TableColumn<NcmDto, Long> colunaCod = new TableColumn<NcmDto, Long>("Código");
		colunaCod.setCellValueFactory(new PropertyValueFactory<NcmDto, Long>("ncm"));
		colunaCod.setPrefWidth(90);

		TableColumn<NcmDto, String> colunaDesc = new TableColumn<NcmDto, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("descricao"));
		colunaDesc.setPrefWidth(380);

		TableColumn<NcmDto, String> colunaDataInicio = new TableColumn<NcmDto, String>("Data Início");
		colunaDataInicio.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("dataInicio"));

		TableColumn<NcmDto, String> colunaDataFim = new TableColumn<NcmDto, String>("Data Fim");
		colunaDataFim.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("dataFim"));

		TableColumn<NcmDto, String> colunaVersao = new TableColumn<NcmDto, String>("Versão");
		colunaVersao.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("versao"));

		TableColumn<NcmDto, String> colunaFonte = new TableColumn<NcmDto, String>("Fonte");
		colunaFonte.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("fonte"));
		colunaFonte.setPrefWidth(170);

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaNCM().getColumns().addAll(colunaCod, colunaDesc, colunaDataInicio, colunaDataFim, colunaVersao,
				colunaFonte);

		if (getTabelaNCM() == null) {
			getTabelaNCM().setPlaceholder(new Label("Nenhum NCM Cadastrado."));
		}
		return getTabelaNCM();
	}

	public static void popularTabela() throws Exception {
		List<NcmDto> ncms = buscarNcms();
		setObservableList(FXCollections.observableArrayList(ncms));
		getTabelaNCM().setItems(observableList);

	}

	public static List<NcmDto> buscarNcms() throws Exception {

		try {
			// BUSCA TODOS NCMs
			String endpoint = url + "ncm";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + getToken()).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			List<NcmDto> ncms = new ArrayList<>();
			NcmDto ncm;
			// DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dataInicio = jsonObj.getString("vigenciainicio");
				String dataFim = jsonObj.getString("vigenciafim");
				ncm = new NcmDto();
				ncm.setId(jsonObj.getLong("id"));
				ncm.setNcm(jsonObj.getLong("ncm"));
				ncm.setEx(jsonObj.getString("ex"));
				ncm.setTipo(jsonObj.getString("tipo"));
				ncm.setDescricao(jsonObj.getString("descricao"));
				ncm.setNacionalfederal(jsonObj.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(jsonObj.getDouble("importadosfederal"));
				ncm.setEstadual(jsonObj.getDouble("estadual"));
				ncm.setMunicipal(jsonObj.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(dataInicio));
				ncm.setDataFim(LocalDate.parse(dataFim));
				ncm.setChave(jsonObj.getString("chave"));
				ncm.setVersao(jsonObj.getString("versao"));
				ncm.setFonte(jsonObj.getString("fonte"));
				ncms.add(ncm);
			}
			return ncms;

		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}
	}

	public static NcmDto buscarNcmById(Long id) throws Exception {
		try {
			String endpoint = url + "ncm/" + id;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + getToken()).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonObj = new JSONObject(response.body());
			NcmDto ncm = new NcmDto();
			String dataInicio = jsonObj.getString("vigenciainicio");
			String dataFim = jsonObj.getString("vigenciafim");
			ncm.setId(jsonObj.getLong("id"));
			ncm.setNcm(jsonObj.getLong("ncm"));
			ncm.setEx(jsonObj.getString("ex"));
			ncm.setTipo(jsonObj.getString("tipo"));
			ncm.setDescricao(jsonObj.getString("descricao"));
			ncm.setNacionalfederal(jsonObj.getDouble("nacionalfederal"));
			ncm.setImportadosfederal(jsonObj.getDouble("importadosfederal"));
			ncm.setEstadual(jsonObj.getDouble("estadual"));
			ncm.setMunicipal(jsonObj.getDouble("municipal"));
			ncm.setDataInicio(LocalDate.parse(dataInicio));
			ncm.setDataFim(LocalDate.parse(dataFim));
			ncm.setChave(jsonObj.getString("chave"));
			ncm.setVersao(jsonObj.getString("versao"));
			ncm.setFonte(jsonObj.getString("fonte"));
			return ncm;

		} catch (Exception e) {
			throw new Exception("NCM não encontrado! " + e.getMessage() + e.getCause());
		}
	}

	private List<NcmDto> load(Stage stage) throws Exception {
		FileChooser caixaDialogo = new FileChooser();
		FileChooser.ExtensionFilter filtroCsv = new FileChooser.ExtensionFilter("Arquivo (*.csv)", "*.csv");
		caixaDialogo.getExtensionFilters().add(filtroCsv);
		caixaDialogo.setTitle("Importar tablea IBPT");
		caixaDialogo.setSelectedExtensionFilter(filtroCsv);

		try {
			File csv = caixaDialogo.showOpenDialog(stage);
			if (csv != null) {
				BufferedReader br = null;
				Locale localeBr = new Locale("pt", "BR");
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy", localeBr);
				try {
					br = new BufferedReader(new FileReader(csv));
					var entity = Files.lines(csv.toPath()).skip(1).map(line -> line.split(";"))
							.map(col -> new NcmDto(Long.parseLong(col[0]), col[1], col[2], col[3].replace("\"", ""),
									Double.valueOf(col[4]).doubleValue(), Double.valueOf(col[5]).doubleValue(),
									Double.valueOf(col[6]).doubleValue(), Double.valueOf(col[7]),
									LocalDate.parse(col[8].replace("/", "-"), format),
									LocalDate.parse(col[9].replace("/", "-"), format), col[10], col[11], col[12]))
							.collect(Collectors.toList());
					return entity;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new FileNotFoundException("Erro ao importar dados: " + e.getMessage());
		}
		return null;
	}

	public static NcmDto retornarNcmSelecionado() {
		NcmDto ncm = new NcmDto(getTabelaNCM().getSelectionModel().getSelectedItem());
		return ncm;
	}
	
	/**
	 * Método para buscar no banco ncm que contenha palavra digitada
	 * @param event
	 * @throws Exception 
	 */
	public List<NcmDto> buscarByDescricao(String descricao) throws Exception {
		try {
			String endpoint = url + "ncm/descricao?desc=" + descricao;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + getToken()).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			List<NcmDto> ncms = new ArrayList<>();
			NcmDto ncm;
			// DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dataInicio = jsonObj.getString("vigenciainicio");
				String dataFim = jsonObj.getString("vigenciafim");
				ncm = new NcmDto();
				ncm.setId(jsonObj.getLong("id"));
				ncm.setNcm(jsonObj.getLong("ncm"));
				ncm.setEx(jsonObj.getString("ex"));
				ncm.setTipo(jsonObj.getString("tipo"));
				ncm.setDescricao(jsonObj.getString("descricao"));
				ncm.setNacionalfederal(jsonObj.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(jsonObj.getDouble("importadosfederal"));
				ncm.setEstadual(jsonObj.getDouble("estadual"));
				ncm.setMunicipal(jsonObj.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(dataInicio));
				ncm.setDataFim(LocalDate.parse(dataFim));
				ncm.setChave(jsonObj.getString("chave"));
				ncm.setVersao(jsonObj.getString("versao"));
				ncm.setFonte(jsonObj.getString("fonte"));
				ncms.add(ncm);
			}
			return ncms;

		} catch (Exception e) {
			throw new Exception(e.getMessage() + e.getCause());
		}

	}
	
	@SuppressWarnings("exports")
	public void pesquisarNcmByName(KeyEvent event) throws Exception {
		String descricao = getNcmPesquisa().getText();
		setObservableList(FXCollections.observableArrayList(buscarByDescricao(descricao)));
		getTabelaNCM().setItems(observableList);
		
	}

	@SuppressWarnings("exports")
	public void selecionar(ActionEvent action) throws Exception {
		TributacaoController.ncmS = getTabelaNCM().getSelectionModel().getSelectedItem();
		controller.getNcm()
				.setText(TributacaoController.ncmS.getNcm() + "   |   " + TributacaoController.ncmS.getDescricao());
		Stage stage = (Stage) getBtnSelecionar().getScene().getWindow();
		stage.close();

	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
