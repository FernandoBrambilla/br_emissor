package gui.Controllers.LoginControllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import gui.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class RootController {

	@FXML
	private BorderPane root;

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	@FXML
	private void initialize() throws IOException {
		Parent painel = FXMLLoader.load(App.class.getResource("LoginViews/Login.fxml"));
		getRoot().setCenter(painel);
		getRoot().getCenter().maxWidth(700);
		getRoot().getCenter().maxHeight(700);
		
	}

}
