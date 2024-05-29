package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    Parent root;
    Stage stage;
    
 


	@Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws IOException {
		
		root = loadFXML("LoginViews/login");
    	stage = primaryStage;
    	stage.setTitle("BR Emissor");
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