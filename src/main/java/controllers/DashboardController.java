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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController {
    @FXML
    private Button logout_Btn;
    @FXML
    private Button dashboard_Btn;
    @FXML
    private Button manage_Btn;
    @FXML
    private Button dashboard_Btn1;
    @FXML
    private Button manage_Btn1;
    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private AnchorPane managePane;
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
    public void switchForm(ActionEvent actionEvent){
        if(actionEvent.getSource() == dashboard_Btn){
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
        }else if(actionEvent.getSource() == manage_Btn){
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
        }else if(actionEvent.getSource() == dashboard_Btn1){
            dashboardPane.setVisible(true);
            managePane.setVisible(false);
        }else if(actionEvent.getSource() == manage_Btn1){
            dashboardPane.setVisible(false);
            managePane.setVisible(true);
        }
    }
}