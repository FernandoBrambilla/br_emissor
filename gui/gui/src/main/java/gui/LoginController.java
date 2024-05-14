package gui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {
    
	private Login login = new Login();
	
	@FXML 
   	TextField userName;
   
	@FXML
	private TextField password;

   
	public TextField getUserName() {
        return userName;
    }

	public TextField getPassword() {
        return password;
    }
	

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	
	/*
     * @MÉTODO RESPONSÁVEL POR CAPTURAR A ENTRADA DIGITADO NA TELA DE LOGIN E BUSCAR NO BANCO SE HÁ CADASTRO
     */
	
    public void login(ActionEvent action) {
       fazerLogin();
    }  
    
    @FXML
    private Login fazerLogin() {
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
             	login.setUserName(responseJson.getString("userName"));
             	login.setAuthenticated(responseJson.getBoolean("authenticated"));
             	login.setCreate(responseJson.getString("create"));
             	login.setExpiration(responseJson.getString("expiration"));
             	login.setAccessToken(responseJson.getString("accessToken"));
             	login.setRefleshToken(responseJson.getString("refleshToken"));
                         
             	if(login.isAuthenticated()) {
             		PrincipalController principal = new PrincipalController();
             		principal.loadTelaPrincipal(login.getAccessToken());	
             	}
             	return login;
             }
         } 
         catch (Exception e) {
         	Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Usuário "+ userName.getText() + " não cadastrado! " + e.getMessage());
             alert.showAndWait(); 
   
         }
         return null;
    }   
}