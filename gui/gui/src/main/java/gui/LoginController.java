package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginController {
    
	@FXML
	private void initialize(){
	}
	
   @FXML 
   private TextField userName;
   
   @FXML
   private TextField password;

   
	public TextField getUserName() {
        return userName;
    }

	public TextField getPassword() {
        return password;
    }
	

	Login logado = new Login();
    
    public Login getLogin() {
    	return this.logado;
    }

    /*
     * @MÉTODO RESPONSÁVEL POR CAPTURAR A ENTRADA DIGITADO NA TELA DE LOGIN E BUSCAR NO BANCO SE HÁ CADASTRO
     */
    @SuppressWarnings("exports")
	@FXML
    public Login login(ActionEvent action) throws IOException, ClassNotFoundException {
        try {
            if(getUserName().getText().isEmpty() || getPassword().getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Os dados informados estão incorretos. Verifique e tente novamente.");
                alert.showAndWait(); 
            }
            else {
            	String username = getUserName().getText();
            	String password = getPassword().getText();
            
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
            	
            	//CRIA UM OBJETO LOGIN
            	logado.setUserName(responseJson.getString("userName"));
            	logado.setAuthenticated(responseJson.getBoolean("authenticated"));
            	logado.setCreate(responseJson.getString("create"));
            	logado.setExpiration(responseJson.getString("expiration"));
            	logado.setAccessToken(responseJson.getString("accessToken"));
            	logado.setRefleshToken(responseJson.getString("refleshToken"));
            	
            
            	if(logado.isAuthenticated()) {
            		PrincipalController principal = new PrincipalController();
            		principal.loadTelaPrincipal(logado);
        
            	}
            }
        } 
        catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Usuário "+ userName.getText() + " não cadastrado! " + e.getMessage());
            alert.showAndWait(); 
        }
		return logado;
    }   
}