package gui.Services;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;

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

}
