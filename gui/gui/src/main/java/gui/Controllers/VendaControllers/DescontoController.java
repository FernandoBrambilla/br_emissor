package gui.Controllers.VendaControllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import gui.Utilities.Mascaras;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DescontoController {

	@FXML
	private TextField descontoValor;

	@FXML
	private TextField descontoPercentual;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	public TextField getDescontoValor() {
		return descontoValor;
	}

	public TextField getDescontoPercentual() {
		return descontoPercentual;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setDescontoValor(TextField descontoValor) {
		this.descontoValor = descontoValor;
	}

	public void setDescontoPercentual(TextField descontoPercentual) {
		this.descontoPercentual = descontoPercentual;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void initialize() {
		Mascaras.percentualField(getDescontoPercentual());

		getDescontoPercentual().setText("");
		getDescontoValor().setText("");

		// PRENCHE O PERCENTUAL DE DESCONTO CASO FOR INFORMADO O VALOR DO DESCONTO
		getDescontoValor().setOnKeyReleased((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.DELETE || keyEvent.getCode() == KeyCode.BACK_SPACE) {
				getDescontoValor().setText("");
			} else {
				calcularPercentual();
			}

		});

		// PRENCHE O VALOR DE DESCONTO CASO FOR INFORMADO O PERCENTUAL DO DESCONTO
		getDescontoPercentual().setOnKeyReleased((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.DELETE || keyEvent.getCode() == KeyCode.BACK_SPACE) {
				getDescontoPercentual().setText("");
			} else {
				calcularValorDesconto();
			}

		});

	}

	private void calcularPercentual() {
		Double totalVenda = PdvController.getPdvController().getSomaTotalVenda();
		Double descontoValor = Double.parseDouble(getDescontoValor().getText().isEmpty() ? "0D"
				: getDescontoValor().getText().replace("R$ ", "").replace(".", "").replace(",", "."));
		if (descontoValor > totalVenda) {
			getDescontoValor().setText("");
			getDescontoPercentual().setText("");
		} else {
			getDescontoPercentual().setText(Mascaras.percentual((descontoValor * 100) / totalVenda) + " %");

		}

	}

	private void calcularValorDesconto() {
		DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");
		Double totalVenda = PdvController.getPdvController().getSomaTotalVenda();
		Double descontoPercentual = Double.parseDouble(getDescontoPercentual().getText().isEmpty() ? "0D"
				: getDescontoPercentual().getText().replace(",", ".").replace(" %", ""));

		if (descontoPercentual > 100) {
			getDescontoPercentual().setText("");
			getDescontoValor().setText("");
		} else {
			getDescontoValor().setText(realFormato.format((totalVenda * descontoPercentual) / 100));
		}

	}

	@SuppressWarnings("exports")
	public void btnSalvar(ActionEvent action) {
		DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");
		// SETA VALOR DE DESCONTO NA VENDA
		PdvController.getPdvController().getVenda().setDesconto(
				Double.parseDouble(getDescontoValor().getText().replace("R$", "").replace(".", "").replace(",", ".")));

		Double totalVenda = PdvController.getPdvController().getSomaTotalVenda();
		Double descontoValor = PdvController.getPdvController().getVenda().getDescontoDouble();

		// SETA O TEXTO SUBTOTAL
		PdvController.getPdvController().getLabelSubtotal()
				.setText("SubTotal = " + String.valueOf(realFormato.format(totalVenda - descontoValor)));

		// SETA O TEXTO DESCONTO
		PdvController.getPdvController().getLabelDesconto()
				.setText("Desconto (" + (Mascaras.percentual((descontoValor * 100) / totalVenda) + " %") + ")" + " = "
						+ "R$ " + realFormato.format(descontoValor));
		
		PdvController.getPdvController().setarValorTotalVenda();
		
		Stage stage = (Stage) getBtnSalvar().getScene().getWindow();
		stage.close();
		
	}

	/**
	 * Função botão Cancelar
	 * 
	 * @param action
	 */
	public void btnCancelar(ActionEvent action) {
		Stage stage = (Stage) getBtnCancelar().getScene().getWindow();
		stage.close();
	}

}
