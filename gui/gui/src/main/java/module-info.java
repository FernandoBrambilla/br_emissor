module gui{
    
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
	requires java.desktop;
	requires org.yaml.snakeyaml;

	
   
	
	opens gui to javafx.base, javafx.fxml, application.model;
	opens gui.Controllers.LoginControllers;
	opens gui.Controllers.PrincipalControllers;
	opens gui.Controllers.ClienteControllers;
	opens gui.Controllers.ProdutoControllers;
	opens gui.Controllers.UsuarioControllers;
	opens gui.Controllers.VendaControllers;
	
    exports gui;
    exports gui.Models;
    exports gui.Controllers.LoginControllers;

    exports gui.Controllers.PrincipalControllers;
    exports gui.Controllers.ProdutoControllers;
    exports gui.Controllers.UsuarioControllers;
    exports gui.Controllers.VendaControllers;

   
}
