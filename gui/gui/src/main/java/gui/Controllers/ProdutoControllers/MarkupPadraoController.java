package gui.Controllers.ProdutoControllers;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MarkupPadraoController {
	
	
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

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws Exception {
		

	}
	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
