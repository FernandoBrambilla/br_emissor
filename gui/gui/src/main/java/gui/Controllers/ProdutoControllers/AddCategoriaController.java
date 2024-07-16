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
import gui.Dtos.Markup;
import gui.Dtos.Style;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AddCategoriaController {

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

	public TextField getCategoria() {
		return categoria;
	}

	public Label getInfo() {
		return info;
	}

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

	public static ObservableList<String> buscarCategoriasProduto() throws Exception {
		try {
			String endpoint = url + "category";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONArray responseJson = new JSONArray(response.body());
			List<String> listaCategorias = new ArrayList<>();

			for (int i = 0; i < responseJson.length(); i++) {
				JSONObject jsonObj = responseJson.getJSONObject(i);
				listaCategorias.add(jsonObj.getString("descricao"));
			}

			ObservableList<String> observableListaCategorias = FXCollections.observableArrayList(listaCategorias);
			return observableListaCategorias;
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
					NovoOuEditarProdutoController.getCategoria().setSkin(null);
					NovoOuEditarProdutoController.getCategoria().setItems(buscarCategoriasProduto());
					NovoOuEditarProdutoController.getCategoria().setSkin(NovoOuEditarProdutoController.criarSkin());
				
					
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
