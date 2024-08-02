package gui.Controllers.TributacaoControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.NcmDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NCMController {

	TributacaoController controller = TributacaoController.INSTANCE;

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	private static TableView<NcmDto> tabelaNCM;

	public static ObservableList<NcmDto> observableList;

	@FXML
	private TextField ncm;

	@FXML
	private ImageView pesquisarNcm;

	@FXML
	private Button importNcm;

	@FXML
	private BorderPane base;

	@FXML
	private Button btnSelecionar;

	@FXML
	private Button btnCancelar;

	public TextField getNcm() {
		return ncm;
	}

	public ImageView getPesquisarNcm() {
		return pesquisarNcm;
	}

	public Button getImportNcm() {
		return importNcm;
	}

	public BorderPane getBase() {
		return base;
	}

	public Button getBtnSelecionar() {
		return btnSelecionar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setNcm(TextField ncm) {
		this.ncm = ncm;
	}

	public void setPesquisarNcm(ImageView pesquisarNcm) {
		this.pesquisarNcm = pesquisarNcm;
	}

	public void setImportNcm(Button importNcm) {
		this.importNcm = importNcm;
	}

	public void setBase(BorderPane base) {
		this.base = base;
	}

	public void setBtnSelecionar(Button btnSelecionar) {
		this.btnSelecionar = btnSelecionar;
	}

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

	public void initialize() throws Exception {
		getBase().setTop(construirTabela());

		getTabelaNCM().setOnMouseClicked((mouseEvent) -> {
			NcmDto ncmSelecionado = getTabelaNCM().getSelectionModel().getSelectedItem();
			if (mouseEvent.getClickCount() == 2) {
				controller.getNcm().setText(ncmSelecionado.getNcm() + "   |   " + ncmSelecionado.getDescricao());
				Stage stage = (Stage) getBtnSelecionar().getScene().getWindow();
				stage.close();
			}
		});

	}

	@SuppressWarnings("unchecked")
	public TableView<NcmDto> construirTabela() throws Exception {

		setTabelaNCM(new TableView<NcmDto>());
		getTabelaNCM().setMaxHeight(340);

		TableColumn<NcmDto, String> colunaCod = new TableColumn<NcmDto, String>("Código");
		colunaCod.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("ncm"));

		TableColumn<NcmDto, String> colunaDesc = new TableColumn<NcmDto, String>("Descrição");
		colunaDesc.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("descricao"));
		colunaDesc.setPrefWidth(400);

		TableColumn<NcmDto, LocalDate> colunaDataInicio = new TableColumn<NcmDto, LocalDate>("Data Início");
		colunaDataInicio.setCellValueFactory(new PropertyValueFactory<NcmDto, LocalDate>("dataInicio"));

		TableColumn<NcmDto, LocalDate> colunaDataFim = new TableColumn<NcmDto, LocalDate>("Data Fim");
		colunaDataFim.setCellValueFactory(new PropertyValueFactory<NcmDto, LocalDate>("dataFim"));

		TableColumn<NcmDto, String> colunaAto = new TableColumn<NcmDto, String>("Versão");
		colunaAto.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("versao"));

		TableColumn<NcmDto, String> colunaNum = new TableColumn<NcmDto, String>("Fonte");
		colunaNum.setCellValueFactory(new PropertyValueFactory<NcmDto, String>("fonte"));

		// POPULA A TABELA
		popularTabela();

		// ADICIONA AS COLUNAS
		getTabelaNCM().getColumns().addAll(colunaCod, colunaDesc, colunaDataInicio, colunaDataFim, colunaAto,
				colunaNum);

		if (getTabelaNCM() == null) {
			getTabelaNCM().setPlaceholder(new Label("Nenhum NCM Cadastrado."));
		}
		return getTabelaNCM();
	}

	public static void popularTabela() throws Exception {
		List<NcmDto> ncms = getAllNCM();
		setObservableList(FXCollections.observableArrayList(ncms));
		getTabelaNCM().setItems(observableList);

	}

	private static List<NcmDto> getAllNCM() throws Exception {

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
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				String dataInicio = jsonObj.getString("vigenciainicio");
				String dataFim = jsonObj.getString("vigenciafim");

				ncm = new NcmDto();
				ncm.setId(jsonObj.getLong("id"));
				ncm.setNcm(jsonObj.getString("ncm"));
				ncm.setEx(jsonObj.getString("ex"));
				ncm.setTipo(jsonObj.getString("tipo"));
				ncm.setDescricao(jsonObj.getString("descricao"));
				ncm.setNacionalfederal(jsonObj.getDouble("nacionalfederal"));
				ncm.setImportadosfederal(jsonObj.getDouble("importadosfederal"));
				ncm.setEstadual(jsonObj.getDouble("estadual"));
				ncm.setMunicipal(jsonObj.getDouble("municipal"));
				ncm.setDataInicio(LocalDate.parse(dataInicio, format));
				ncm.setDataFim(LocalDate.parse(dataFim, format));
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

	public void salvarTabelaBanco() throws Exception {
		Stage stage = (Stage) getBase().getScene().getWindow();
		List<NcmDto> ncms = load(stage);
		JSONObject json = new JSONObject();
		String endpoint = url + "ncm";
		HttpClient client = HttpClient.newHttpClient();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		for (int i = 0; i < ncms.size(); i++) {
			json.put("ncm", ncms.get(i).getNcm());
			json.put("ex", ncms.get(i).getEx());
			json.put("tipo", ncms.get(i).getTipo());
			json.put("descricao", ncms.get(i).getDescricao());
			json.put("nacionalfederal", ncms.get(i).getNacionalfederal());
			json.put("importadosfederal", ncms.get(i).getImportadosfederal());
			json.put("estadual", ncms.get(i).getEstadual());
			json.put("municipal", ncms.get(i).getMunicipal());
			json.put("vigenciainicio", ncms.get(i).getDataInicio().format(format));
			json.put("vigenciafim", ncms.get(i).getDataFim().format(format));
			json.put("chave", ncms.get(i).getChave());
			json.put("versao", ncms.get(i).getVersao());
			json.put("fonte", ncms.get(i).getFonte());

			System.out.println(json);
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		}
	}

	private List<NcmDto> load(Stage stage) throws Exception {
		FileChooser caixaDialogo = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo (*.csv)", "*.csv");
		// FileChooser.ExtensionFilter encoding = new
		// FileChooser.ExtensionFilter("Arquivo (*.csv)", "*.csv");
		caixaDialogo.getExtensionFilters().add(extFilter);
		caixaDialogo.setTitle("Importar tablea IBPT");
		caixaDialogo.setSelectedExtensionFilter(extFilter);
		try {
			File csv = caixaDialogo.showOpenDialog(stage);
			BufferedReader br = null;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			try {
				br = new BufferedReader(new FileReader(csv));
				// for(int i = 0 ; (linha= br.readLine()) != null; i++) {
				var entity = Files.lines(csv.toPath()).skip(1).map(line -> line.split(";"))
						.map(col -> new NcmDto(col[0], col[1], col[2], col[3].replace("\"", ""),
								Double.valueOf(col[4]).doubleValue(), Double.valueOf(col[5]).doubleValue(),
								Double.valueOf(col[6]).doubleValue(), Double.valueOf(col[7]),
								LocalDate.parse(col[8], format), LocalDate.parse(col[9], format), col[10], col[11],
								col[12]))
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
		} catch (Exception e) {
			throw new FileNotFoundException("Erro ao importar dados: " + e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("exports")
	public void importarNcm(ActionEvent action) throws Exception {
		try {
			salvarTabelaBanco();
		} catch (Exception e) {
			throw new Exception("Erro ao importar Tabela de NCM: " + e.getMessage());
		}
	}

	public static NcmDto retornarNcmSelecionado() {
		NcmDto ncm = new NcmDto(getTabelaNCM().getSelectionModel().getSelectedItem());
		return ncm;
	}

	@SuppressWarnings("exports")
	public void selecionar(ActionEvent action) throws Exception {
		controller.getNcm().setText(getTabelaNCM().getSelectionModel().getSelectedItem().getNcm());
		Stage stage = (Stage) getBtnSelecionar().getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
