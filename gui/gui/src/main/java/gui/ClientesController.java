package gui;



import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ClientesController {
	
	
	
	@FXML
	private Button btnVendas;
	
	@FXML
	private Button btnOrcamento;
	
	@FXML
	private Button btnClientes;
	
	@FXML
	private Button btnProdutos;
	
	@FXML
	private Button btnCaixa;
	
	@FXML
	private Button btnUsuarios;
	
	@FXML
	private Button btnEstatisticas;
	
		
	@FXML
    private void hover(MouseEvent event) {
	Color color = new Color(0.0, 0.2, 0.6, 0.9);
	InnerShadow shadow = new InnerShadow();
	shadow.setWidth(15);
	shadow.setHeight(15);
	shadow.setColor(color);
	
	btnVendas.addEventHandler(MouseEvent.MOUSE_ENTERED,
	    new EventHandler<MouseEvent>() {
	        @Override 
	        public void handle(MouseEvent e) {
	        	btnVendas.setEffect(shadow);
	        }
	        
	});
	
	btnVendas.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	btnVendas.setEffect(null);
		        }
		});
	
	btnOrcamento.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnOrcamento.setEffect(shadow);
		        }
		});
	
	btnOrcamento.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	btnOrcamento.setEffect(null);
		        }
		});
	
	btnClientes.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnClientes.setEffect(shadow);
		        }
		        
		});
		
	btnClientes.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btnClientes.setEffect(null);
			        }
			});
	
	btnProdutos.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnProdutos.setEffect(shadow);
		        }
		        
		});
		
	btnProdutos.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btnProdutos.setEffect(null);
			        }
			});
	
	btnCaixa.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnCaixa.setEffect(shadow);
		        }
		        
		});
		
	btnCaixa.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btnCaixa.setEffect(null);
			        }
			});
	
	btnUsuarios.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnUsuarios.setEffect(shadow);
		        }
		        
		});
		
	btnUsuarios.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btnUsuarios.setEffect(null);
			        }
			});
	
	btnEstatisticas.addEventHandler(MouseEvent.MOUSE_ENTERED,
		    new EventHandler<MouseEvent>() {
		        @Override 
		        public void handle(MouseEvent e) {
		        	btnEstatisticas.setEffect(shadow);
		        }
		        
		});
		
		btnEstatisticas.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	btnEstatisticas.setEffect(null);
			        }
			});
		
		
	}
	
}

    
