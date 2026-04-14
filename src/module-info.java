module OdontoSys {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires org.controlsfx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml, javafx.base;
	opens model to javafx.base;
}
