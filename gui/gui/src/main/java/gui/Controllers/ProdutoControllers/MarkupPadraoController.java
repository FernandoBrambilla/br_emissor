package gui.Controllers.ProdutoControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.MarkupDto;
import gui.Utilities.Mascaras;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MarkupPadraoController {
	
	private static String token = PrincipalController.getAccessToken();

	private static String url = PrincipalController.getUrl();

	@FXML
	private TextField markup;

	@FXML
	private CheckBox utilizar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@SuppressWarnings("exports")
	public TextField getMarkup() {
		return markup;
	}

	@SuppressWarnings("exports")
	public CheckBox getUtilizar() {
		return utilizar;
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
	public void setMarkup(TextField markup) {
		this.markup = markup;
	}

	@SuppressWarnings("exports")
	public void setUtilizar(CheckBox utilizar) {
		this.utilizar = utilizar;
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
		Mascaras.onlyDecimal(getMarkup());
		MarkupDto markup = new MarkupDto(buscarMarkup());
		getMarkup().setText(Mascaras.decimal(markup.getMarkup()));
		getUtilizar().setSelected(markup.isUtilizar() ? true : false);
	}

	public static MarkupDto buscarMarkup() throws Exception {

		try {
			String endpoint = url + "markup";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Accept", "application/json").build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject responseJson = new JSONObject(response.body());
			MarkupDto markup = new MarkupDto();
			markup.setId(responseJson.getInt("id"));
			markup.setMarkup(responseJson.getBigDecimal("markup"));
			markup.setUtilizar(responseJson.getBoolean("utilizar"));
			return markup;

		} catch (Exception e) {
			throw new Exception("Não foi localizado o Markup padrão!");
		}
	}
	
	

	public static void criarMarkup(MarkupDto markup) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("id", 1);
			json.put("markup", markup.getMarkup());
			json.put("utilizar", markup.isUtilizar());
			String endpoint = url + "markup";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar a operação!");
		}

	}

	private void atualizarMarkup(MarkupDto markup) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("id", 1);
			json.put("markup", markup.getMarkup());
			json.put("utilizar", markup.isUtilizar());

			String endpoint = url + "markup";
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
					.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(json.toString())).build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
			Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			throw new Exception("Não foi possível salvar a operação!");
		}
	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		MarkupDto markup = new MarkupDto();
		markup.setId(1);
		markup.setMarkup(new BigDecimal(getMarkup().getText().replace(",", ".")));
		markup.setUtilizar(getUtilizar().isSelected() ? true : false);
		atualizarMarkup(markup);
		Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
		stage.close();
		NovoOuEditarProdutoController.getCheckBox().setSelected(buscarMarkup().isUtilizar()? true : false);
		NovoOuEditarProdutoController.getMarkupText().setText(markup.isUtilizar() ? (Mascaras.decimal(markup.getMarkup()) + " %") : "");
		NovoOuEditarProdutoController.getMarkupText().setEditable(markup.isUtilizar()? false : true);
		
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
