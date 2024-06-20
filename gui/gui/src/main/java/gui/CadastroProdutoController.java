package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CadastroProdutoController {
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;

	
	@FXML
	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {
	                                                            
	}
	
	
	
	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
public void btnAddCategoria(@SuppressWarnings("exports") ActionEvent action) {
		
	}
}
