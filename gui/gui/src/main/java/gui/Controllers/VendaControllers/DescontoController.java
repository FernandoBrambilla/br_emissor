package gui.Controllers.VendaControllers;

import java.text.DecimalFormat;

import gui.Utilities.Mascaras;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DescontoController {
	
	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");

	@FXML
	private Label info;

	@FXML
	private TextField descontoValor;

	@FXML
	private TextField descontoPercentual;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;
	
	private Double totalVenda = PdvController.getPdvController().getVenda().getTotalFinalDouble();

	public Label getInfo() {
		return info;
	}

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

	public void setInfo(Label info) {
		this.info = info;
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
		getInfo().setText("");
		getDescontoPercentual().setText("");
		getDescontoValor().setText("");
		
		
		if(PdvController.getPdvController().getVenda().getDescontoDouble() != null) {
			Double descontoValor = PdvController.getPdvController().getVenda().getDescontoDouble();
			getDescontoValor().setText(realFormato.format(Double.valueOf(PdvController.getPdvController().getVenda().getDescontoDouble())));
			getDescontoPercentual().setText(Mascaras.percentual((descontoValor * 100) / (totalVenda + descontoValor)) + " %");
		}

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

	public Double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}

	private void calcularPercentual() {
		
		
		Double descontoValor = Double.parseDouble(getDescontoValor().getText().isEmpty() ? "0D"
				: getDescontoValor().getText().replace("R$ ", "").replace(".", "").replace(",", "."));
		if (descontoValor > totalVenda) {
			getDescontoValor().setText("");
			getDescontoPercentual().setText("");
			getInfo().setText("O desconto não pode ser maior que " + realFormato.format(totalVenda) + "!");
		} else {
			getDescontoPercentual().setText(Mascaras.percentual((descontoValor * 100) / totalVenda) + " %");

		}

	}

	private void calcularValorDesconto() {
		Double descontoPercentual = Double.parseDouble(getDescontoPercentual().getText().isEmpty() ? "0D"
				: getDescontoPercentual().getText().replace(",", ".").replace(" %", ""));

		if (descontoPercentual > 100) {
			getDescontoPercentual().setText("");
			getDescontoValor().setText("");
			getInfo().setText("O percentual de desconto não pode ser maior que 100 %!");
		} else {
			getDescontoValor().setText(realFormato.format((totalVenda * descontoPercentual) / 100));
		}

	}

	public void btnSalvar(ActionEvent action) {
		// SETA VALOR DE DESCONTO NA VENDA
		PdvController.getPdvController().getVenda().setDesconto(
				Double.parseDouble(getDescontoValor().getText().replace("R$", "").replace(".", "").replace(",", ".")));

		Double descontoValor = PdvController.getPdvController().getVenda().getDescontoDouble();
		PdvController.getPdvController().getVenda().setDesconto(descontoValor);
		PdvController.getPdvController().getVenda().setTotalFinal(totalVenda - descontoValor);

		// SETA O TEXTO SUBTOTAL
		PdvController.getPdvController().getLabelSubtotal()
				.setText("SubTotal = " + String.valueOf(realFormato.format(totalVenda)));

		// SETA O TEXTO DESCONTO
		PdvController.getPdvController().getLabelDesconto()
				.setText("Desconto (" + (Mascaras.percentual((descontoValor * 100) / totalVenda) + " %") + ")" + " = "
						+ "R$ " + realFormato.format(descontoValor));

		PdvController.getPdvController().atualizarTotalVenda();

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
