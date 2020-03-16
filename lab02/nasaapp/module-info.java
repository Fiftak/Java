module nasaapp {
	opens ctlr; 
	exports ctlr;
	exports gui;
	requires javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	requires java.net.http;
	requires org.json;
}