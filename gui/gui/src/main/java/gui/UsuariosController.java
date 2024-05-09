package gui;



import java.io.IOException;

import gui.Services.Effects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class UsuariosController  extends AnchorPane{
	
	
	@FXML
	private BorderPane telaUsuarios;

	@FXML
	private Button btnNovoUsuario;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnDeletar;
	
	@FXML
	private TableView tabelaUsuarios;
	
	@FXML
	private Button btnCancelar;
	

	public Button getBtnNovoUsuario() {
		return btnNovoUsuario;
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public Button getBtnDeletar() {
		return btnDeletar;
	}

	public void setBtnNovoUsuario(Button btnNovoUsuario) {
		this.btnNovoUsuario = btnNovoUsuario;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void seBtnDeletar(Button btnDeletar) {
		this.btnDeletar = btnDeletar;
	}

	public TableView getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public void setBtnDeletar(Button btnDeletar) {
		this.btnDeletar = btnDeletar;
	}

	public void setTabelaUsuarios(TableView tabelaUsuarios) {
		this.tabelaUsuarios = tabelaUsuarios;
	}

	public void aplicaEfeitos() {
		Effects efeitos = new Effects();
		efeitos.hover(getBtnNovoUsuario());
		efeitos.hover(getBtnEditar());
		efeitos.hover(getBtnDeletar());
		
	}
	
	public void populaTabela() {
		//String accessToken = new Login().getAccessToken();
		
	}
	
	
	public void cadastrar(ActionEvent action) throws IOException {
		Stage stage = new Stage();
		Parent painel = FXMLLoader.load(getClass().getResource("NovoUsuario.fxml"));
		Scene scene = new Scene(painel, 600, 450);
		
		stage.setTitle("Cadasto de Usuários");
		stage.setScene(scene);
        stage.show();
       
        
	}
	
	private void salvar(ActionEvent action) {
		
	}
	
	@FXML
	private void cancelar(ActionEvent action) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow(); 
	    stage.close(); 
		
	}
	
}

    
