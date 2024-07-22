package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.CategoriaProdutoDto;
import gui.Dtos.Style;
import gui.Dtos.UnidadeProdutoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class NovaUnidadeController {

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	@FXML
	private TextField unidade;

	@FXML
	private Label info;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;


	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
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
	public TextField getUnidade() {
		return unidade;
	}

	@SuppressWarnings("exports")
	public void setUnidade(TextField unidade) {
		this.unidade = unidade;
	}

	@SuppressWarnings("exports")
	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void initialize() throws Exception {

	}

	public static List<UnidadeProdutoDto> buscarUnidadesProduto() throws Exception {
		try {
			String endpoint = url + "unity";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			List<UnidadeProdutoDto> listaUnidades = new ArrayList<>();
			UnidadeProdutoDto unidadeProdutoDto;

			for (int i = 0; i < responseJson.length(); i++) {
				unidadeProdutoDto = new UnidadeProdutoDto();
				JSONObject jsonObj = responseJson.getJSONObject(i);
				unidadeProdutoDto.setId(jsonObj.getInt("id"));
				unidadeProdutoDto.setDescricao(jsonObj.getString("descricao"));
				listaUnidades.add(unidadeProdutoDto);

			}
			return listaUnidades;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static UnidadeProdutoDto buscarUnidadeProduto(int id) throws Exception {
		try {
			String endpoint = url + "unity/" + id;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			UnidadeProdutoDto unidadeProdutoDto = new UnidadeProdutoDto();

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				unidadeProdutoDto.setId(jsonObj.getInt("id"));
				unidadeProdutoDto.setDescricao(jsonObj.getString("descricao"));
			}
			return unidadeProdutoDto;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static int criarUnidade(UnidadeProdutoDto unidade) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("descricao", unidade.getDescricao());
			String endpoint = url + "unity";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			int statusCode = response.statusCode();
			return statusCode;
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar a operação!");
		}

	}

	public static void apagarUnidadae(UnidadeProdutoDto unidade) {
		try {
			// REQUIÇÃO PARA DELETAR
			String endpoint = url + "unity/" + unidade.getId();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().header("Authorization", "Bearer " + token)
					.header("Content-Type", "application/json").DELETE().uri(URI.create(endpoint)).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 204) {
				Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
				alert1.setHeaderText(null);
				alert1.setContentText("Unidade apagada com sucesso! ");
				alert1.showAndWait();
				NovoOuEditarProdutoController.getUnidade().getItems().remove(unidade);
				NovoOuEditarProdutoController.getUnidade().show();
			}

		} catch (Exception e) {
			Alert alert2 = new Alert(Alert.AlertType.ERROR);
			alert2.setHeaderText(null);
			alert2.setContentText("Erro. " + e.getMessage());
			alert2.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert2.showAndWait();
		}
	}

	public static int atualizarUnidade(UnidadeProdutoDto unidade) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("id", unidade.getId());
			json.put("descricao", unidade.getDescricao());
			String endpoint = url + "unity";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			int statusCode = response.statusCode();
			return statusCode;
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar a operação!");
		}

	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {
		Style effect = new Style();
		try {
			// VERIFICA CAMPOS VAZIOS
			if (getUnidade().getText().isEmpty()) {
				getInfo().setText("*Campo Obrigatórios!");
				effect.campoObrigatorio(getUnidade());
				return;
			} else {
				UnidadeProdutoDto novaUnidade = new UnidadeProdutoDto();
				novaUnidade.setDescricao(getUnidade().getText().toUpperCase());

				JSONObject json = new JSONObject();
				json.put("descricao", novaUnidade.getDescricao());
				int statusCode = criarUnidade(novaUnidade);
				if (statusCode == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Unidade \"" + novaUnidade.getDescricao() + "\" cadastrada com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
					stage.close();
					NovoOuEditarProdutoController.atualizarLista();
					NovoOuEditarProdutoController.getUnidade().show();
				}
			}

		} catch (

		Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Erro. " + e.getMessage() + e.getCause());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		}

	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
