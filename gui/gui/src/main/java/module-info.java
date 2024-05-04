module gui {
    
	opens gui to javafx.fxml;
    exports gui;
    
    requires javafx.fxml;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.controlsEmpty;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
	requires java.net.http;
	requires org.apache.httpcomponents.core5.httpcore5;
	requires org.json;
   
    
}
