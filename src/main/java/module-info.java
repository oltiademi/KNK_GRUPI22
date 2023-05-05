module com.example.knk_grupi22 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml;


    opens com.example.knk_grupi22 to javafx.fxml;
    exports com.example.knk_grupi22;
    exports controllers;
    opens controllers to javafx.fxml;
}