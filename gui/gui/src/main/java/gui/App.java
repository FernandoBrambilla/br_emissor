package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	public static Scene scene;
	Parent root;
	public static Stage stage;

	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws IOException {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		root = loadFXML("LoginViews/Root");
		stage = primaryStage;
		stage.setTitle("Br Sistemas de Gestão");
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Style/style.css").toExternalForm());
		stage.setScene(scene);
		stage.setMaximized(true); 
		stage.show();

	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	@SuppressWarnings("exports")
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();

	}

}