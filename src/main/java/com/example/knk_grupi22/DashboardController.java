package com.example.knk_grupi22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Button logout_Btn;

    public void logout(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == logout_Btn){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
            Parent homeRoot = fxmlLoader.load();

            // Create a FXMLLoader for the dashboard.fxml file
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Parent dashboardRoot = fxmlLoader1.load();

            // Create a Scene for the dashboard
            Scene scene = new Scene(dashboardRoot, 637, 425);

            // Create a Stage for the dashboard
            Stage stage = new Stage();
            stage.setScene(scene);

            // Hide the current window
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.hide();

            // Show the dashboard window
            stage.show();
        }
    }
}
