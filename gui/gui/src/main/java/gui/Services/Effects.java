package gui.Services;

import java.awt.Color;

import javax.swing.BorderFactory;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;




public class Effects {
	
	public void hover(Button btn) {
		ColorAdjust color = new ColorAdjust();
		color.setBrightness(-0.13);
		
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override 
					public void handle(MouseEvent e) {
						btn.setEffect(color);
				    }
			});
		
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, 
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent e) {
						btn.setEffect(null);
				    }
			});
	}
	
	public void campoObrigatorio(TextField campo) {
		campo.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
	}
	
	public void campoObrigatorioRemove(TextField campo) {
		campo.setStyle(null);
	}
	

}
