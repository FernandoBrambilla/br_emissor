package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {
    
   @FXML 
   private TextField userName;
   
   @FXML
   private TextField password;

   
    
    public TextField getUserName() {
        return userName;
    }

    public void setUserName(TextField userName) {
        this.userName = userName;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    

     
    @FXML
    private void login(ActionEvent action) throws IOException, ClassNotFoundException {
        try {
            if(this.getUserName().getText().isEmpty() || this.getPassword().getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Os dados informados estão incorretos. Verifique e tente novamente.");
                alert.showAndWait(); 
            }
            else {
            	String username = this.getUserName().getText();
            	String password = this.getPassword().getText();
            
            	//FAZER LOGIN
            	String urlLogin = "http://localhost:8080/auth/signin";
            	HttpClient client = HttpClient.newHttpClient();
            	String body = "{\r\n"
            			+ "    \"username\" : \""+username+"\",\r\n"
            			+ "    \"password\" : \""+password+"\"\r\n"
            			+ "}";
            	HttpRequest request = HttpRequest.newBuilder()
				  .uri(URI.create(urlLogin))
				  .header("Content-Type", "application/json")
				  .POST(HttpRequest.BodyPublishers.ofString(body))
				  .build();
            	HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            	JSONObject responseJson = new JSONObject(response.body());
            	//String userName = responseJson.getString("userName");
            	boolean authenticated = responseJson.getBoolean("authenticated");
            	//String create = responseJson.getString("create");
            	//String expiration = responseJson.getString("expiration");
            	String accessToken = responseJson.getString("accessToken");
        
            	if(authenticated) {
            		App.setRoot("principal");
                   
            	}
            }
        } 
        catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Usuário "+ userName.getText() + " não cadastrado! " + e.getMessage());
            alert.showAndWait(); 
        }
    }   
}