package gui.Models;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;




public class Style {
	
	@SuppressWarnings("exports")
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
	
	@SuppressWarnings("exports")
	public void campoObrigatorio(TextField campo) {
		campo.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
	}
	
	@SuppressWarnings("exports")
	public void campoObrigatorioRemove(TextField campo) {
		campo.setStyle(null);
	}
	 

	
	

}
