package controllers;

import com.example.knk_grupi22.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController {
    @FXML
    private Button logout_Btn;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public void logout() throws IOException {
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Are you sure you want to log out?");
            if(alert.showAndWait().get()== ButtonType.OK) {
                // Create a FXMLLoader for the dashboard.fxml file
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Parent dashboardRoot = fxmlLoader1.load();

                // Create a Scene for the dashboard
                Scene scene = new Scene(dashboardRoot, 637, 425);

                // Create a Stage for the dashboard
                Stage stage = new Stage();
                stage.setScene(scene);

                Node source = (Node) logout_Btn;
                // Hide the current window
                Stage currentStage = (Stage) source.getScene().getWindow();
                currentStage.hide();

                // Show the dashboard window
                stage.show();
            }else{
                return;
            }
        }
    }
}
