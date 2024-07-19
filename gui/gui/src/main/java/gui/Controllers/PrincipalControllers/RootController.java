package gui.Controllers.PrincipalControllers;

import java.io.IOException;

import gui.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class RootController {

	@FXML
	private BorderPane root;

	@SuppressWarnings("exports")
	public BorderPane getRoot() {
		return root;
	}

	@SuppressWarnings("exports")
	public void setRoot(BorderPane root) {
		this.root = root;
	}

	@FXML
	private void initialize() throws IOException {
		Parent painel = FXMLLoader.load(App.class.getResource("PrincipalViews/Principal.fxml"));
		getRoot().setCenter(painel);
		

	}

}
