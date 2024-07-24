package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
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

public class EditarUnidadeController {

	private static String url = PrincipalController.getUrl();

	private static String token = PrincipalController.getAccessToken();

	private UnidadeProdutoDto unidadeParaEditar = null;

	@FXML
	private TextField unidade;

	@FXML
	private Label info;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@SuppressWarnings("exports")
	public TextField getUnidade() {
		return unidade;
	}

	@SuppressWarnings("exports")
	public void setUnidade(TextField unidade) {
		this.unidade = unidade;
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
	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void initialize() throws Exception {
		unidadeParaEditar = NovoOuEditarProdutoController.getUnidade().getSelectionModel().getSelectedItem();
		getUnidade().setText(unidadeParaEditar.getDescricao());

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
				JSONObject json = new JSONObject();
				json.put("id", unidadeParaEditar.getId());
				json.put("descricao", getUnidade().getText().toUpperCase());
				String endpoint = url + "unidade";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Unidade \"" + getUnidade().getText() + "\" atualizada com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
					stage.close();
					NovoOuEditarProdutoController.atualizarListaUnidades();
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
