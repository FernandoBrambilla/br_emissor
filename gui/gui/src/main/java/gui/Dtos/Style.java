package gui.Dtos;

import java.math.BigDecimal;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;

public class Style {

	@SuppressWarnings("exports")
	public void adicinarCorBotaoSelecionado(Button btn) {
			btn.setStyle("-fx-background-color:  #171B36; ");
	}
	
	@SuppressWarnings("exports")
	public void removerCorBotaoSelecionado(Button btn) {
			btn.setStyle("-fx-background-color:  #323639; ");
	}
	
	public void hoverBtnsInternos(Button btn) {
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btn.setStyle("-fx-background-color: #D3D3D3; -fx-cursor: hand;");
			}
		});

		btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btn.setStyle("-fx-background-color:  white;");
			}
		});
	}
	

	@SuppressWarnings("exports")
	public void hover(Button btn) {
		ColorAdjust color = new ColorAdjust();
		color.setBrightness(-0.20);

		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btn.setEffect(color);
			}
		});

		btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btn.setEffect(null);
			}
		});
	}

	@SuppressWarnings("exports")
	public void campoObrigatorio(TextField campo) {
		campo.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public void campoObrigatorio(ChoiceBox campo) {
		campo.setStyle("-fx-border-color: red ;");
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public void campoObrigatorio(ComboBox campo) {
		campo.setStyle("-fx-border-color: red ;");
	}

	@SuppressWarnings("exports")
	public void campoObrigatorioRemove(TextField campo) {
		campo.setStyle(null);
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public void campoObrigatorioRemove(ChoiceBox campo) {
		campo.setStyle(null);
	}

	@SuppressWarnings({ "exports", "rawtypes" })
	public void campoObrigatorioRemove(ComboBox campo) {
		campo.setStyle(null);
	}

}
