module com.example.knk_grupi22 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.knk_grupi22 to javafx.fxml;
    exports com.example.knk_grupi22;
    exports controllers;
    opens controllers to javafx.fxml;
}