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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class NovaCategoriaController {

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	@FXML
	private TextField categoria;

	@FXML
	private Label info;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@SuppressWarnings("exports")
	public TextField getCategoria() {
		return categoria;
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
	public Button getBtnSalvar() {
		return btnSalvar;
	}

	@SuppressWarnings("exports")
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	@SuppressWarnings("exports")
	public void setCategoria(TextField categoria) {
		this.categoria = categoria;
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

	public static List<CategoriaProdutoDto> buscarCategoriasProduto() throws Exception {
		try {
			String endpoint = url + "category";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());

			List<CategoriaProdutoDto> listaCategorias = new ArrayList<>();
			CategoriaProdutoDto categoriaProdutoDto;

			for (int i = 0; i < responseJson.length(); i++) {
				categoriaProdutoDto = new CategoriaProdutoDto();
				JSONObject jsonObj = responseJson.getJSONObject(i);
				categoriaProdutoDto.setId(jsonObj.getInt("id"));
				categoriaProdutoDto.setDescricao(jsonObj.getString("descricao"));
				listaCategorias.add(categoriaProdutoDto);

			}
			return listaCategorias;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static CategoriaProdutoDto buscarCategoriaProduto(int id) throws Exception {
		try {
			String endpoint = url + "category/" + id;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			CategoriaProdutoDto categoriaProdutoDto = new CategoriaProdutoDto();

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				categoriaProdutoDto.setId(jsonObj.getInt("id"));
				categoriaProdutoDto.setDescricao(jsonObj.getString("descricao"));
			}
			return categoriaProdutoDto;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static int criarCategoria(CategoriaProdutoDto categoria) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("descricao", categoria.getDescricao());
			String endpoint = url + "category";
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

	public static void apagarCategoria(CategoriaProdutoDto categoria) {
		try {
			// REQUIÇÃO PARA DELETAR
			String endpoint = url + "category/" + categoria.getId();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().header("Authorization", "Bearer " + token)
					.header("Content-Type", "application/json").DELETE().uri(URI.create(endpoint)).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			if (response.statusCode() == 204) {
				Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
				alert1.setHeaderText(null);
				alert1.setContentText("Categoria apagada com sucesso! ");
				alert1.showAndWait();
				NovoOuEditarProdutoController.getCategoria().getItems().remove(categoria);
				NovoOuEditarProdutoController.getCategoria().show();
			}

		} catch (Exception e) {
			Alert alert2 = new Alert(Alert.AlertType.ERROR);
			alert2.setHeaderText(null);
			alert2.setContentText("Erro. " + e.getMessage());
			alert2.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert2.showAndWait();
		}
	}

	public static int atualizarCategoria(CategoriaProdutoDto categoria) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("id", categoria.getId());
			json.put("descricao", categoria.getDescricao());
			String endpoint = url + "category";
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
			if (getCategoria().getText().isEmpty()) {
				getInfo().setText("*Campo Obrigatórios!");
				effect.campoObrigatorio(getCategoria());
				return;
			} else {
				CategoriaProdutoDto novaCategoria = new CategoriaProdutoDto();
				novaCategoria.setDescricao(getCategoria().getText().toUpperCase());

				JSONObject json = new JSONObject();
				json.put("descricao", novaCategoria.getDescricao());
				int statusCode = criarCategoria(novaCategoria);
				if (statusCode == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Categoria \"" + novaCategoria.getDescricao() + "\" cadastrada com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
					stage.close();
					NovoOuEditarProdutoController.atualizarLista();
					NovoOuEditarProdutoController.getCategoria().show();
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
