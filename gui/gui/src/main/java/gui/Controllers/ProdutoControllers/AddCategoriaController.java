package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
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
	
	private static String token = PrincipalController.getAccessToken();
	
	private static NovoOuEditarProdutoController novoProduto;

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

	@FXML
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
				JSONObject json = new JSONObject();
				json.put("descricao", getCategoria().getText().toUpperCase());

				// REQUIÇÃO
				String url = "http://localhost:8080/category";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText(
							"Categoria de Produto \"" + getCategoria().getText() + "\" cadastrado com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
					stage.close();
					NovoOuEditarProdutoController.preencherListaDeCategorias();
					
				}
			}
		} catch (Exception e) {
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
