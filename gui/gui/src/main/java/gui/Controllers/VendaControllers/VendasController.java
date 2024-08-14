package gui.Controllers.VendaControllers;

import gui.Controllers.PrincipalControllers.PrincipalController;
import gui.Dtos.Style;
import gui.Dtos.VendaDto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class VendasController {
	
	private static String token = PrincipalController.getAccessToken();

	private static ObservableList<VendaDto> observableList;

	private static TableView tabelaVendas = null;
	
	@FXML
	private Button btnNovaVenda;
	
	@FXML
	private Button btnEditarVenda;
	
	@FXML
	private Button btnCancelarVenda;

	public Button getBtnNovaVenda() {
		return btnNovaVenda;
	}

	public Button getBtnEditarVenda() {
		return btnEditarVenda;
	}

	public Button getBtnCancelarVenda() {
		return btnCancelarVenda;
	}

	public void setBtnNovaVenda(Button btnNovaVenda) {
		this.btnNovaVenda = btnNovaVenda;
	}

	public void setBtnEditarVenda(Button btnEditarVenda) {
		this.btnEditarVenda = btnEditarVenda;
	}

	public void setBtnCancelarVenda(Button btnCancelarVenda) {
		this.btnCancelarVenda = btnCancelarVenda;
	}
	
	public void aplicaEfeitos() {
		Style efeitos = new Style();
		efeitos.hover(getBtnNovaVenda());
		efeitos.hover(getBtnEditarVenda());
		efeitos.hover(getBtnCancelarVenda());
		
	}
	

}
