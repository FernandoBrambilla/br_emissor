package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import gui.Services.Effects;
import gui.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class LoginController extends AnchorPane{
    
	private Login login = new Login();
	
	@FXML
	BorderPane telaBase;
	
	@FXML 
   	private TextField userName;
   
	@FXML
	private PasswordField password;
	
	@FXML
	private Label info;
	
	@FXML
	private Button btnEntrar;
   
	@SuppressWarnings("exports")
	public TextField getUserName() {
        return userName;
    }

	@SuppressWarnings("exports")
	public TextField getPassword() {
        return password;
    }

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
    @SuppressWarnings("exports")
	public Button getBtnEntrar() {
		return btnEntrar;
	}

	@SuppressWarnings("exports")
	public void setBtnEntrar(Button btnEntrar) {
		this.btnEntrar = btnEntrar;
	}

	@SuppressWarnings("exports")
	public void login(ActionEvent action) {
       fazerLogin();
    }  
    
    @FXML
    private Login fazerLogin() {
    	Effects efeito = new Effects();
    	 try {
             if(getUserName().getText().isEmpty() || getPassword().getText().isEmpty()){
            	 
            	 efeito.campoObrigatorio(getUserName());
            	 efeito.campoObrigatorio(getPassword());
            	 getInfo().setText("*Campos Obrigatórios");
            	 
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
             		PrincipalController principalController = new PrincipalController();
             		principalController.getLogin(login);
             		
             	}
             	return login;
             }
         } 
         catch (Exception e) {
        	 efeito.campoObrigatorio(getUserName());
        	 efeito.campoObrigatorio(getPassword());
        	 getInfo().setText("*Usuário ou Senha inválidos. Verifique os campos e tente novamente");
         }
         return null;
    } 

    @FXML
    private void initialize() throws IOException {
    	
    	getBtnEntrar().setOnAction((event) -> {
    		
    		PrincipalController principalController = new PrincipalController();
            try {
            	fazerLogin();
          		
          		if(login.isAuthenticated()) {
          		principalController.getLogin(fazerLogin());
                Parent telaPrincipal = FXMLLoader.load(PrincipalController.class.getResource("PrincipalViews/Principal.fxml"));
                this.telaBase.setCenter(telaPrincipal);
          		}
          		else {
          			return;
          		}
          		} catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}